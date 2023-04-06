let stompClient = null;
let gameID;
let userName; // felhasználó neve
let player;
let enemy;
let others;
let game;
let inventory;
let tradingPosts;
let myMiniGame;

function connect(gameid, name) {

    let socket = new SockJS('/game_lobby');
    gameID = gameid;
    userName = name;


    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/update/' + gameid, function (message) {
            game = JSON.parse(message.body);

            let players = game.players;
            let posts = game.posts;
            getPlayerByName(players);
            showtradingposts(posts);
        });
        stompClient.subscribe('/topic/minigame/' + gameid, function (message) {
            let miniGames = JSON.parse(message.body);
            searchMyMinigame(miniGames);
        });
        waitstatus();
    });

}

function startTimer() {
    var timeLeft = 120;
    var countdownTimer = setInterval(function() {
        timeLeft--;
        document.getElementById("timer").innerHTML = "Hátralévő idő: " + timeLeft + " másodperc.";

        if (timeLeft <= 0) {
            clearInterval(countdownTimer);
            window.location.href = "/statistic/" + gameID;
        }
    }, 1000);
}

function searchMyMinigame(miniGames) {
    miniGames.forEach(function (game) {
        if (game.player1 === player.name) {
            player.fightWith = game.player2;
            myMiniGame = game;
            if (game.player1Choose == null || game.player2Choose == null) {
                fight();
            } else {
                minigameEnd();
            }
        } else if (game.player2 === player.name) {
            player.fightWith = game.player1
            myMiniGame = game;
            if (game.player1Choose == null || game.player2Choose == null) {
                fight();
            } else {
                minigameEnd();
            }
        }
    })
}

function minigameEnd() {
    switch (myMiniGame.player1Choose) {
        case "rock":
            switch (myMiniGame.player2Choose) {
                case "rock":
                    minigameWin("tie");
                    break;
                case "paper":
                    minigameWin(myMiniGame.player2)
                    break;
                case "ollo":
                    minigameWin(myMiniGame.player1)
                    break;
            }
            break;
        case "paper":
            switch (myMiniGame.player2Choose) {
                case "rock":
                    minigameWin(myMiniGame.player1)
                    break;
                case "paper":
                    minigameWin("tie");
                    break;
                case "ollo":
                    minigameWin(myMiniGame.player2)
                    break;
            }
            break;
        case "ollo":
            switch (myMiniGame.player2Choose) {
                case "rock":
                    minigameWin(myMiniGame.player2)
                    break;
                case "paper":
                    minigameWin(myMiniGame.player1)
                    break;
                case "ollo":
                    minigameWin("tie");
                    break;
            }
            break;
    }
}

function minigameWin(winner) {
    let minGame = document.getElementById('minGame');
    minGame.style.display = 'none';
    switch (winner) {
        case "tie":
            let tie = document.getElementById('tie');
            tie.style.display = 'block';
            myMiniGame = null;
            break;
        case player.name:
            let win = document.getElementById('win');
            win.style.display = 'block';
            for (const currPlayer of others) {
                if (currPlayer.name.toString() === myMiniGame.player1) {
                    enemy = currPlayer;
                } else if(currPlayer.name.toString() === myMiniGame.player2){
                    enemy = currPlayer;
                }
            }
            player.inventory.tree += enemy.inventory.tree;
            player.fightWith = '';
            sendStatus();
            enemy.inventory.tree = 0;
            enemy.fightWith = '';
            stompClient.send("/app/refresh/" + gameID, {}, JSON.stringify({
                id: gameID,
                playerName: enemy.name,
                x: enemy.coordinateX,
                y: enemy.coordinateY,
                onShop: enemy.onShop,
                fightWith: enemy.fightWith,
                tree: enemy.inventory.tree,
                money: enemy.inventory.money
            }));
            stompClient.send("/app/minigame/end", {}, JSON.stringify({
                gameID: gameID,
                player1: myMiniGame.player1,
                player2: myMiniGame.player2,
                player1Choose: myMiniGame.player1Choose,
                player2Choose: myMiniGame.player2Choose
            }))
            myMiniGame = null;
            break;
        default:
            myMiniGame = null;
            let lose = document.getElementById('lose')
            lose.style.display = 'block'
    }

}

function waitstatus() {
    stompClient.send("/app/boot/" + gameID, {}, JSON.stringify({
        name: userName,
        id: gameID

    }));
}

function sendStatus() {
    stompClient.send("/app/refresh/" + gameID, {}, JSON.stringify({
        id: gameID,
        playerName: player.name,
        x: player.coordinateX,
        y: player.coordinateY,
        onShop: player.onShop,
        fightWith: player.fightWith,
        tree: player.inventory.tree,
        money: player.inventory.money
    }));

}

function getPlayerByName(players) {
    let otherPlayers = [];
    for (const currPlayer of players) {
        if (currPlayer.name.toString() === userName.toString()) {
            player = currPlayer;
            console.log(currPlayer.inventory);
            inventory = currPlayer.inventory;
            showinventory();
            const table = document.getElementById("table");
            var string = 'translate(' + -player.coordinateX + 'px,' + -player.coordinateY + 'px)';
            table.style.transform = string;
        } else {
            if (currPlayer.fightWith === userName) {
                player.fightWith = currPlayer.name;
            }
            otherPlayers.push(currPlayer);
        }
    }
    others = otherPlayers;
    addOtherPlayersToPage(otherPlayers, player)
}

function okey() {
    let lose =document.getElementById('lose');
    let win =document.getElementById('win');
    let tie =document.getElementById('tie');
    lose.style.display ='none';
    win.style.display ='none';
    tie.style.display ='none';
    let rock = document.getElementById('rock');
    let paper = document.getElementById('paper');
    let ollo = document.getElementById('ollo');
    rock.style.display = 'inline';
    paper.style.display = 'inline';
    ollo.style.display = 'inline';
}
function addOtherPlayersToPage(otherPlayers) {
    let others = document.getElementById('others');
    others.innerHTML = '';
    otherPlayers.forEach(function (other) {
        let image = document.createElement('img');
        image.src = '/Character/yellowShirtBoy.png';
        image.style.position = 'absolute';
        image.setAttribute('id', other.name);
        let pozition = 'translate(' + (other.coordinateX - player.coordinateX) + 'px, ' + (other.coordinateY - player.coordinateY) + 'px)';
        image.addEventListener('click', function () {
            let postId = this.getAttribute('id');
            let enemy;

            for (const currPlayer of otherPlayers) {
                if (currPlayer.name.toString() === postId) {
                    enemy = currPlayer;
                    console.log(enemy)

                }

            }
            console.log(enemy)
            if (!(player.onShop || !(player.fightWith == ''))) {
                if (!(enemy.onShop || !(enemy.fightWith == ''))) {
                    stompClient.send("/app/new/minigame/" + gameID, {}, JSON.stringify({
                        gameID: gameID,
                        player1: player.name,
                        player2: enemy.name
                    }));
                }
            }
        });
        image.style.transform = pozition;
        others.appendChild(image);
    });
}

function showtradingposts(places) {
    tradingPosts = places;
    console.log(tradingPosts);
    let posts = document.getElementById('posts');
    posts.innerHTML = '';
    tradingPosts.forEach(function (post) {
        let image = document.createElement('img');
        image.src = '/Kereskedőpont/village.png';
        image.setAttribute('id', post.identifier);
        image.style.position = 'absolute';
        let position = 'translate(' + (post.coordinateX - player.coordinateX) + 'px, ' + (post.coordinateY - player.coordinateY) + 'px)';
        image.style.transform = position;
        image.addEventListener('click', function () {
            if (!(player.onShop || !(player.fightWith == ''))) {
                let postId = this.getAttribute('id');
                player.onShop = true;
                showShop(postId);
                sendStatus();
            }
        });
        posts.appendChild(image);
    });
}

function showShop(id) {
    let city = searchCotyById(id);
    let shop = document.getElementById('shop');
    let treeCost = document.getElementById('treecost');
    let treenumber = document.getElementById('treenumber');
    treenumber.setAttribute('min', -player.inventory.tree);
    console.log(player.inventory.money / parseInt(city.treePrice))
    treenumber.setAttribute('max', player.inventory.money / parseInt(city.treePrice));
    treeCost.innerText = city.treePrice;
    shop.style.display = 'block'
}

function searchCotyById(id) {
    for (let i = 0; i < tradingPosts.length; i++) {
        if (tradingPosts[i].identifier === id) {
            return tradingPosts[i];
        }
    }
    return null;
}

function choose(choose) {
    console.log(choose);
    if (myMiniGame.player1 === player.name) {
        myMiniGame.player1Choose = choose;
    }
    if (myMiniGame.player2 === player.name) {
        myMiniGame.player2Choose = choose;
    }
    stompClient.send("/app/minigame/" + gameID, {}, JSON.stringify({
        gameID: gameID,
        player1: myMiniGame.player1,
        player2: myMiniGame.player2,
        player1Choose: myMiniGame.player1Choose,
        player2Choose: myMiniGame.player2Choose
    }))
    console.log(choose)
    let paper = document.getElementById('paper');
    let ollo = document.getElementById('ollo');
    let rock = document.getElementById('rock');
    switch (choose) {
        case 'rock':
            paper.style.display = 'none';
            ollo.style.display = 'none';
            break;
        case 'paper':
            rock.style.display = 'none';
            ollo.style.display = 'none';
            break;
        case 'ollo':
            rock.style.display = 'none';
            paper.style.display = 'none';
            break;
    }

}

function shoping() {
    let shop = document.getElementById('shop');
    let treenumber = parseInt(document.getElementById('treenumber').value);
    let cost = parseInt(document.getElementById('treecost').innerText);
    player.inventory.tree += treenumber;
    console.log(player.inventory.tree)
    player.inventory.money -= treenumber * cost;
    player.onShop = false;
    sendStatus();
    shop.style.display = 'none';
}

function fight() {
    let mini = document.getElementById('minGame');
    mini.style.display = 'block';
}

function showinventory() {
    let tree = document.getElementById('tree');
    let money = document.getElementById('money');

    tree.innerText = inventory.tree;
    money.innerText = inventory.money;

}

document.addEventListener("keydown", (event) => {
    console.log(player.fightWith)
    if (!(player.onShop || !(player.fightWith == ''))) {
        switch (event.key) {
            case "ArrowUp":

                player.coordinateY -= 10;
                player.viewY += 10;
                break;
            case "ArrowDown":
                player.coordinateY += 10;
                player.viewY -= 10;
                break;
            case "ArrowLeft":
                player.coordinateX -= 10;
                player.viewX += 10;
                break;
            case "ArrowRight":
                player.coordinateX += 10;
                player.viewX -= 10;
                break;
            default:
                return;
        }
        if (player.coordinateX < 0) {
            player.coordinateX = 0;
        }
        if (player.coordinateX > 3200) {
            player.coordinateX = 3200;
        }
        if (player.coordinateY < 0) {
            player.coordinateY = 0;
        }
        if (player.coordinateY > 3200) {
            player.coordinateY = 3200;
        }


        const table = document.getElementById("table");
        var string = 'translate(' + -player.coordinateX + 'px,' + -player.coordinateY + 'px)';
        table.style.transform = string;

        sendStatus();

    }
});


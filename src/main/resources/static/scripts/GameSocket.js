let stompClient = null;
let gameID;
let userName; // felhasználó neve
let player;
let game;
let inventory;
let tradingPosts;

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
        waitstatus();
    });

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
        onFight: player.onFight,
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
        } else {
            if(currPlayer.onFight === userName){
                player.onFight = currPlayer.name;
            }
            otherPlayers.push(currPlayer);
        }
    }
    addOtherPlayersToPage(otherPlayers, player)
}

function addOtherPlayersToPage(otherPlayers) {
    let others = document.getElementById('others');
    others.innerHTML = '';
    otherPlayers.forEach(function (other) {
        let image = document.createElement('img');
        image.src = '/OneColorBackgrounds/burgundy.png';
        image.style.position = 'absolute';
        image.setAttribute('id', other.name);
        let pozition = 'translate(' + (other.coordinateX - player.coordinateX) + 'px, ' + (other.coordinateY - player.coordinateY) + 'px)';
        image.addEventListener('click', function() {
            let postId = this.getAttribute('id');
            let enemy;
            for (const currPlayer of players) {
                if (currPlayer.name.toString() === userName.toString()) {
                    enemy = currPlayer;
                }

            }
            if(!(player.onShop || player.onFight == null)){
                if(!(enemy.onShop || enemy.onFight)){
                    player.onFight == enemy.name;
                    sendStatus();
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
        image.src = '/OneColorBackgrounds/gold.png';
        image.setAttribute('id', post.identifier);
        image.style.position = 'absolute';
        let position = 'translate(' + (post.coordinateX - player.coordinateX) + 'px, ' + (post.coordinateY - player.coordinateY) + 'px)';
        image.style.transform = position;
        image.addEventListener('click', function() {
            if(!(player.onShop || player.onFight)) {
                let postId = this.getAttribute('id');
                player.onShop = true;
                showShop(postId);
                sendStatus();
            }
        });
        posts.appendChild(image);
    });
}

function showShop(id){
    let city = searchCotyById(id);
    let shop = document.getElementById('shop');
    let treeCost = document.getElementById('treecost');
    let  treenumber =document.getElementById('treenumber');
    treenumber.setAttribute('min', -player.inventory.tree);
    console.log(player.inventory.money / parseInt(city.treePrice))
    treenumber.setAttribute('max', player.inventory.money / parseInt(city.treePrice));
    treeCost.innerText = city.treePrice;
    shop.style.display = 'block'
}

function  searchCotyById(id){
    for(let i = 0; i < tradingPosts.length; i ++){
        if( tradingPosts[i].identifier === id){
            return tradingPosts[i];
        }
        console.log(tradingPosts[i])

    }
    return null;
}

function shoping(){
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

function fight(enemy) {

}

function showinventory() {
    let tree = document.getElementById('tree');
    let money = document.getElementById('money');

    tree.innerText = inventory.tree;
    money.innerText = inventory.money;

}

document.addEventListener("keydown", (event) => {
    console.log(player.onShop)
    if(!(player.onShop || player.onFight == null)){
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
    if (player.coordinateX > 3280) {
        player.coordinateX = 3280;
    }
    if (player.coordinateY < 0) {
        player.coordinateY = 0;
    }
    if (player.coordinateY > 3280) {
        player.coordinateY = 3280;
    }


    const table = document.getElementById("table");
    var string = 'translate(' + -player.coordinateX + 'px,' + -player.coordinateY + 'px)';
    table.style.transform = string;

    sendStatus();

}});


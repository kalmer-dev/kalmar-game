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
        y: player.coordinateY
    }));

}

function getPlayerByName(players) {
    let otherPlayers = [];
    for (const currPlayer of players) {
        if (currPlayer.name.toString() === userName.toString()) {
            player = currPlayer;
            console.log(currPlayer.inventory);
            inventory = currPlayer.inventory;
            console.log(inventory);
            showinventory();
        } else {
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
            console.log(postId);
        });
        image.style.transform = pozition;
        others.appendChild(image);
        console.log(player)
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
            let postId = this.getAttribute('id');
            showShop(postId);
        });
        posts.appendChild(image);
    });
}

function showShop(id){
    let city = searchCotyById(id);
    console.log(city)
    let shop = document.getElementById('shop');
    let treeCost = document.getElementById('treecost');
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
    shop.style.display = 'none';
}

function fight(enemy) {

}

function showinventory() {
    console.log('itt vagyok');
    let tree = document.getElementById('tree');
    let money = document.getElementById('money');

    tree.innerText = inventory.tree;
    money.innerText = inventory.money;

}

document.addEventListener("keydown", (event) => {
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

});


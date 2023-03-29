let stompClient = null;
let gameID;
let userName; // felhasználó neve
let player;
let game;
let inventory;

function connect(gameid, name) {

    let socket = new SockJS('/game_lobby');
    gameID = gameid;
    userName = name;


    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/update/' + gameid, function (message) {
            game = JSON.parse(message.body);
            let players = game.players;
            getPlayerByName(players)
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
    otherPlayers.forEach(function(other) {
        let image = document.createElement('img');
        image.src = '/OneColorBackgrounds/burgundy.png';
         image.style.position = 'absolute';

        let pozition = 'translate(' + (other.coordinateX - player.coordinateX) + 'px, ' + (other.coordinateY  - player.coordinateY) + 'px)';
        console.log(pozition);
        image.style.transform = pozition;
        others.appendChild(image);
        console.log(player)
    });


}


function showinventory()
{
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

    const table = document.getElementById("table");
    var string = 'translate(' + -player.coordinateX + 'px,' + -player.coordinateY + 'px)';
    table.style.transform = string;

    sendStatus();

});


let stompClient = null;
let gameID;
let userName; // felhasználó neve
let player;
let game;

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
        } else {
            otherPlayers.push(currPlayer);
        }
    }
    addOtherPlayersToPage(otherPlayers)
}

function addOtherPlayersToPage(otherPlayers) {
    const otherPlayersList = document.getElementById("other-players");
    otherPlayersList.innerHTML = ""; // töröljük a korábbi listaelemeket
    for (const currPlayer of otherPlayers) {
        const listItem = document.createElement("li");
        listItem.innerText = currPlayer.name;
        otherPlayersList.appendChild(listItem);
    }
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

    const myCharacter = document.getElementById("mycharacter");
    myCharacter.style.left = player.coordinateX + "px";
    myCharacter.style.top = player.coordinateY + "px";

    sendStatus();

});


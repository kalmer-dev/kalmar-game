let stompClient = null;
let gameID;
let userName; // felhasználó neve
let player;

function connect(gameid, name) {

    let socket = new SockJS('/game_lobby');
    gameID = gameid;
    userName = name;


    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/update/' + gameid, function (message) {
            let players = JSON.parse(message.body);
            getPlayerByName(players)
        });
        waitstatus();
    });

}

function waitstatus() {
    stompClient.send("/app/boot/" + gameID, {},JSON.stringify({
        name: userName,
        id: gameID

    }));
}
function sendStatus(){
    stompClient.send("/app/game/refresh/" + gameID, {}, JSON.stringify({
        player: player
    }));

}

function getPlayerByName(players) {
    console.log(players);

    for (const currPlayer of players) {
        if (currPlayer.name.toString() === userName.toString()) {
            player = currPlayer;
            console.log(player);
        }
    }
}



document.addEventListener("keydown", (event) => { //Billentyűzet hallgoató
    switch (event.key) {
        case "ArrowUp":     //FEL GOMB
            player.coordinateY -= 10;
            sendStatus()
            break;
        case "ArrowDown":    //LE GOMB
            player.coordinateY += 10;
            sendStatus()
            break;
        case "ArrowLeft":    //BAL GOMB
            player.coordinateX -= 10;
            sendStatus()
            break;
        case "ArrowRight":    //JOBB GOMB
            player.coordinateX += 10;
            sendStatus()
            break;
        default:
            return;
    }
});


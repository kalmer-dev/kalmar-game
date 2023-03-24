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
        stompClient.subscribe('/game/update/' + gameid, function (message) {
            let game = JSON.parse(message.body);

        });
    });
    sendStatus()
}

function sendStatus(){
    stompClient.send("/game/refresh/" + gameID, {}, JSON.stringify({
        player: player
    }));

}

function getPlayerByName(players) {

    for (const currPlayer of players) {
        if (currPlayer.name.toString() === userName.toString()) {
            player = currPlayer;
            console.log(player);
        }
    }
}



document.addEventListener("keydown", (event) => { //Billentyűzet hallgoató
   console.log(player)
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


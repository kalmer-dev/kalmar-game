var stompClient = null;
var gameID;
var userName; // felhasználó neve

function connect(gameid, name) {
    var context = window.spring_security_context;
    if (context && context.authentication && context.authentication.principal) {
        userName = context.authentication.principal.username;
    }
    var socket = new SockJS('/game');
    gameID = gameid;
    userName = name;
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/game/' + gameid, function (message) {
            var game = JSON.parse(message.body);

        });
        sendStatus()
    });
}

function sendStatus(){
    stompClient.send("/game/refresh/" + gameID, {}, JSON.stringify({
        'name': userName,
        "id" :gameID
    }));

}
var stompClient = null;
var gameID;
var userName; // felhasználó neve

function connect(gameid) {
    var context = window.spring_security_context;
    if (context && context.authentication && context.authentication.principal) {
        userName = context.authentication.principal.username;
    }
    var socket = new SockJS('/game_lobby');
    gameID = gameid;
    userName = name;
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/game/' + gameid, function (message) {
            var game = JSON.parse(message.body);

        });

    });
}
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
        stompClient.subscribe('/topic/' + gameid, function (message) {
            var members = JSON.parse(message.body);
            displayMembers(members);
        });
        stompClient.subscribe('/topic/start-game/' + gameID, function (message) {
            // Load new template when game is started
            window.location.href = '/game/' + gameID;
        });
        sendName(gameid); // A sendName függvény hívása itt történik.
    });
}

function sendName(gameid) {

    stompClient.send("/app/join/" + gameid, {}, JSON.stringify({
        'name': userName,
        'id': gameid
    }));
}

function displayMembers(members) {
    var userList = document.getElementById("user-list");
    userList.innerHTML = "";
    members.forEach(function (member) {
        var li = document.createElement("li");
        li.appendChild(document.createTextNode(member));
        userList.appendChild(li);
    });
}

function startGame() {
    stompClient.send('/app/start-game/' + gameID, {},JSON.stringify({
        'name': userName, // felhasználó neve
        'id': gameID
    }));
}

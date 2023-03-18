var stompClient = null;

function connect(gameid) {
    var socket = new SockJS('/game_lobby');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/' + gameid, function (message) {
            var members = JSON.parse(message.body);
            displayMembers(members);
        });
        sendName(gameid); // A sendName függvény hívása itt történik.
    });
}

function sendName(gameid) {
    const userName = prompt("Kérem, adja meg a nevét:");
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

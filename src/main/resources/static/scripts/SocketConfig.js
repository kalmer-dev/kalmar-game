var stompClient = null;

function connect(gameid) {
    var socket = new SockJS('/game_lobby');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/' + gameid, function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
    sendName();
}

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

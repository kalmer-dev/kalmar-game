var ws = new WebSocket("ws://localhost:8080/lobby");

ws.onopen = function(event) {
    console.log("WebSocket kapcsolat létrehozva");
};

ws.onmessage = function(event) {
    console.log("WebSocket üzenet érkezett: " + event.data);
    var session = event.data;
    var listItem = document.createElement("li");
    listItem.innerHTML = session;
    document.getElementById("lobby-sessions").appendChild(listItem);
};

ws.onclose = function(event) {
    console.log("WebSocket kapcsolat lezárult: " + event.code + " - " + event.reason);
};
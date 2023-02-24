const canvas = document.getElementById('canvas');
const context = canvas.getContext('2d');
const img = new Image();

img.onload = function() {
    drawCharacter();
};

function updatePosition(newX, newY) {
    x = newX;
    y = newY;
}

function drawCharacter() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.drawImage(img, x, y, 50, 50);
}

function startWebSocket() {
    const socket = new WebSocket('ws://localhost:8080/movement');

    socket.onopen = function() {
        console.log('WebSocket connected');
    };

    socket.onmessage = function(event) {
        const position = JSON.parse(event.data);
        updatePosition(position.x, position.y);
        drawCharacter();
    };
}

startWebSocket();
setInterval(function() {
    const position = {x: x + 5, y: y + 5};
    const socket = new WebSocket('ws://localhost:8080/movement');
    socket.onopen = function() {
        socket.send(JSON.stringify(position));
    };
}, 50);

img.src = 'http://localhost:8080/character-image'; // Replace with your image endpoint URL

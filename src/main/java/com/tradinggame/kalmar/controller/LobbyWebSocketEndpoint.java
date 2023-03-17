package com.tradinggame.kalmar.controller;
import jakarta.websocket.OnMessage;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;



@ServerEndpoint("/lobby/{id}")
public class LobbyWebSocketEndpoint {

    @OnMessage
    public void onMessage(@PathParam("id") String id, String message) {
        // Ide jön a WebSocket üzenet feldolgozása
    }
}


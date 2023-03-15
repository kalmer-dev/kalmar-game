package com.tradinggame.kalmar.controller;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;


import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/game_lobby")
public class LobbyWebSocket {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        broadcast("A new user has joined the lobby.");
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        broadcast("A user has left the lobby.");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        broadcast(session.getId() + ": " + message);
    }

    private void broadcast(String message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

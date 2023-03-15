package com.tradinggame.kalmar.configuration;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class LobbyHandler extends TextWebSocketHandler {

    private final Map<String, List<WebSocketSession>> lobbySessions = new ConcurrentHashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        if (StringUtils.hasText(payload)) {
            String[] parts = payload.split(",", 2);
            if (parts.length == 2 && StringUtils.hasText(parts[0]) && StringUtils.hasText(parts[1])) {
                String lobbyId = parts[0];
                String messagePayload = parts[1];
                session.getAttributes().put("lobbyId", lobbyId);

                List<WebSocketSession> sessions = lobbySessions.computeIfAbsent(lobbyId, k -> new CopyOnWriteArrayList<>());
                sessions.add(session);
                try {
                    for (WebSocketSession s : sessions) {
                        s.sendMessage(new TextMessage(messagePayload));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        throw new IllegalArgumentException("Invalid payload: " + payload);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String lobbyId = (String) session.getAttributes().get("lobbyId");
        if (StringUtils.hasText(lobbyId)) {
            List<WebSocketSession> sessions = lobbySessions.get(lobbyId);
            if (sessions != null) {
                sessions.remove(session);
                if (sessions.isEmpty()) {
                    lobbySessions.remove(lobbyId);
                }
            }
        }
    }

}

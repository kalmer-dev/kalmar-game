package com.tradinggame.kalmar.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/lobby")
    @SendTo("/lobby")
    public String handleLobbyMessage(String message) {
        return "Új felhasználó csatlakozott: " + message;
    }
}

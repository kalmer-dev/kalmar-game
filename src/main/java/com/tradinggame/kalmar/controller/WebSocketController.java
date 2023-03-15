package com.tradinggame.kalmar.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/join")
    @SendTo("/topic/game_lobby")
    public String join(String username) {
        return username + " joined the lobby";
    }

    @MessageMapping("/send")
    @SendTo("/topic/game_lobby")
    public String sendMessage(String message) {
        return message;
    }
}

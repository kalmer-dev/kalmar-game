package com.tradinggame.kalmar.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebSocketController {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, List<String>> gameLobby = new HashMap<>();

    @MessageMapping("/join/{id}")
    @SendTo("/topic/{id}")
    public List<String> joinGame(NameId message) {

        List<String> members = gameLobby.get(message.getId());
        if(members == null){
            members = new ArrayList<>();
        }
        members.add(message.name);
        gameLobby.put(message.id, members);
        return members;
    }

    @MessageMapping("/start-game/{id}")
    @SendTo("/topic/start-game/{id}")
    public String startGame(@Payload NameId message){
        return "";
    }

}
@AllArgsConstructor
@Data
class NameId{
    String id;
    String name;
}

package com.tradinggame.kalmar.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradinggame.kalmar.game.model.Game;
import com.tradinggame.kalmar.game.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebSocketController {
    List<Game> games = new ArrayList<>();

    @MessageMapping("/join/{id}")
    @SendTo("/topic/{id}")
    public List<Player> joinGame(NameId message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Game game = searchGame(message.getId());
        game.putPlayer(new Player(message.name));
        return game.getPlayers();
    }



    @PostMapping("/connect")
    public String compareInput(@RequestParam("inputValue") String inputValue, Model model) {
        Game game = searchGame(inputValue);
        if (game != null) {
            return "redirect:/lobby/" + game.getIdentifier();
        }
        return "/connect";

    }

    @GetMapping("/game/{id}")
    public String getGame(@PathVariable String id, Model model) {
        Game game = searchGame(id);
        model.addAttribute("game", game);
        return "Map";
    }

    @GetMapping("/lobby/{id}")
    public String getLobby(@PathVariable String id, Model model, Principal principal) {
        Game game = searchGame(id);
        model.addAttribute("game", game);
        model.addAttribute("userName", principal.getName());
        return "lobby";
    }

        @PostMapping("/initialize-game")
    public String newGame(Model model) {
        Game game = new Game();
        games.add(game);
        return "redirect:/lobby/" + game.getIdentifier();
    }

    private Game searchGame(String id) {
        for (Game game : games) {
            if (game.getIdentifier().equals(id)) {
                return game;
            }
        }
        return null;
    }

    @MessageMapping("/start-game/{id}")
    @SendTo("/topic/start-game/{id}")
    public String startGame(@Payload NameId message) {
        return "";
    }

}

@AllArgsConstructor
@Data
class NameId {
    String id;
    String name;
}

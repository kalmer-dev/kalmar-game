package com.tradinggame.kalmar.controller;

import com.tradinggame.kalmar.game.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    private List<Game> games = new ArrayList<>();

    @GetMapping(path = "/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/lobby")
    public String getLobby(){
        return "lobby";
    }

    @GetMapping("/connect")
    public String connectPage(){
        return "connect";
    }

    @GetMapping("/game")
    public String getGame(){
        return "game";
    }

    @PostMapping("/connect")
    public String connectToGame(){
        return "lobby";
    }


}
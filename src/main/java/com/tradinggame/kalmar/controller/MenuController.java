package com.tradinggame.kalmar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuController {

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
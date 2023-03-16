package com.tradinggame.kalmar.controller;

import com.tradinggame.kalmar.game.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import java.util.ArrayList;

@Controller
public class MenuController {
    private final List<Game> games = new ArrayList<>();

    @GetMapping(path = "/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/lobby")
    public String getLobby(Model model){
        System.out.println(model.getAttribute("game"));
        return "lobby";
    }

    @GetMapping("/connect")
    public String connectPage(){
        return "connect";
    }



    @PostMapping("/connect")
    public String compareInput(@RequestParam("inputValue") String inputValue, Model model) {
        for (Game game:games) {
            if(game.getIdentifier().equals(inputValue)){
                model.addAttribute("game", game);
                return "redirect:/lobby";
            }
        }
        return "/connect";
    }

    @PostMapping("/initialize-game")
    public String newGame(Model model){
        Game game = new Game();
        games.add(game);
        model.addAttribute("game", game);
        return "redirect:/lobby";
    }
}
package com.tradinggame.kalmar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {


    @GetMapping("/game")
    public String getGamePage() {
        return "game";
    }

    @GetMapping(value = { "/play"})
    public String getGameLogic(Model model) {


        String winner = "";


        model.addAttribute("winner", winner);
        return "game";
    }
}

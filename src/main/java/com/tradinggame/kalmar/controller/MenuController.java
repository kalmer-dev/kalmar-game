package com.tradinggame.kalmar.controller;

import com.tradinggame.kalmar.game.model.Game;
import com.tradinggame.kalmar.game.model.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    private final List<Game> games = new ArrayList<>();

    @GetMapping(path = "/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/lobby")
    public String getLobby(@RequestParam("game") Game game, Model model){
        System.out.println(game.getIdentifier());
        model.addAttribute("game", game);
        model.addAttribute("gameID", game.getIdentifier());
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
                return "/lobby";
            }
        }
        return "/connect";
    }

    @PostMapping("/initialize-game")
    public String newGame(Model model){
        Game game = new Game();
        games.add(game);
        model.addAttribute("game", game);
        return "/lobby";
    }

   @RequestMapping(value = "/game/{játékazonosító}/{játékosazonosító}", method = RequestMethod.GET)
        public String game(@PathVariable("játékazonosító") String gameId,
                           @PathVariable("játékosazonosító") String playerId) {
        return "Map";
   }
}
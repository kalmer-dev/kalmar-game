package com.tradinggame.kalmar.controller;

import com.tradinggame.kalmar.game.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.ArrayList;


@Controller
public class MenuController {
    private final List<Game> games = new ArrayList<>();

    @GetMapping(path = "/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/lobby/{id}")
    public String getLobby(@PathVariable String id, Model model){
        Game game = searchGame(id);
        model.addAttribute("game", game);
        return "lobby";
    }

    @GetMapping("/connect")
    public String connectPage(){
        return "connect";
    }



    @PostMapping("/connect")
    public String compareInput(@RequestParam("inputValue") String inputValue, Model model) {
        Game game = searchGame(inputValue);
        if(game != null){
            return "redirect:/lobby/" + game.getIdentifier();
        }
        return "/connect";

    }



    @PostMapping("/initialize-game")
    public String newGame(Model model){
        Game game = new Game();
        games.add(game);
        return "redirect:/lobby/" + game.getIdentifier();
    }

    private Game searchGame(String id){
        for (Game game:games) {
            if(game.getIdentifier().equals(id)){
                return game;
            }
        }
        return null;
    }

   @RequestMapping(value = "/game/{játékazonosító}/{játékosazonosító}", method = RequestMethod.GET)
        public String game(@PathVariable("játékazonosító") String gameId,
                           @PathVariable("játékosazonosító") String playerId) {
        return "Map";
   }
}
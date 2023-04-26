package com.tradinggame.kalmar.controller;

import com.tradinggame.kalmar.game.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import java.util.ArrayList;


@Controller
public class MenuController {

    @GetMapping(path = "/home")
    public String getHome(){
        return "home";
    }



    @GetMapping("/connect")
    public String connectPage(){
        return "connect";
    }





//   @RequestMapping(value = "/game/{játékazonosító}/{játékosazonosító}", method = RequestMethod.GET)
//        public String game(@PathVariable("játékazonosító") String gameId,
//                           @PathVariable("játékosazonosító") String playerId) {
//        return "Map";
//   }
}
package com.tradinggame.kalmar.Controller_DB_SC;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller_goals {
    @GetMapping(path = {"/","/testing"})
    public String homepage(){return "testing";}



}

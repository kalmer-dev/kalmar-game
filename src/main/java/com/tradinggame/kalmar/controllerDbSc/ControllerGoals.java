package com.tradinggame.kalmar.controllerDbSc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerGoals {
    @GetMapping(path = {"/","/testing"})
    public String homepage(){return "testing";}



}

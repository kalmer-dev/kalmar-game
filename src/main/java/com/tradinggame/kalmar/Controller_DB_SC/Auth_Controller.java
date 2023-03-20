package com.tradinggame.kalmar.Controller_DB_SC;

import com.tradinggame.kalmar.Model_Database.Game_User;
import com.tradinggame.kalmar.Model_Database.User_Repository;
import com.tradinggame.kalmar.security_model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Auth_Controller {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private User_Repository user_repository;

    @Autowired
    private UserService userService;


    @PostMapping("/registration")
    public String saveUser(
            @ModelAttribute("newUser")
            @Validated
            RegistrationForm reg,
            BindingResult result
    ) {
        try {
            if (userService.loadUserByUsername(reg.getName()) != null) {
                result.rejectValue("name", "NotUnique", "Ezzel mar regisztraltak");
            }
        } catch (UsernameNotFoundException e) {
            // it is ok if not found
        }

        if (result.hasErrors()) {
            return "reg-form";
        }

        Game_User nu = new Game_User();

        nu.setName(reg.getName());
        nu.setPassword(passwordEncoder.encode(reg.getPassword()));
        user_repository.save(nu);

        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String saveUserForm(Model model) {
        model.addAttribute("newUser", new RegistrationForm());
        return "reg-form";
    }
}


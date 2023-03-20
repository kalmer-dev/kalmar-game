package com.tradinggame.kalmar.controllerDbSc;



import com.tradinggame.kalmar.database.GameUser;
import com.tradinggame.kalmar.database.UserRepository;
import com.tradinggame.kalmar.model.UserService;
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
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository user_repository;

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

        GameUser nu = new GameUser();

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

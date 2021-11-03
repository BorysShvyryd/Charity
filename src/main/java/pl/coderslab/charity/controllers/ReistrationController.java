package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReistrationController {

    @GetMapping("/register")
    public String registrationForm() {
        return "register";
    }
}

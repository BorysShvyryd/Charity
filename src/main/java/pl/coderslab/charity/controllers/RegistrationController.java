package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.UserServiceImpl;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserServiceImpl userService;
    private final EmailService emailService;

    public RegistrationController(UserServiceImpl userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registrationSubmit(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!user.getPassword().equals(user.getPassword2())) {
            return "register";
        }

        if (userService.findByUserName(user.getName()) != null) {
            return "register";
        }

        userService.save(user);

        emailService.SendEmail(user.getUsername()
                , "Service CHARITY"
                , "Dziękujemy za rejestrację na naszej stronie.");

        return "redirect:/login";
    }
}

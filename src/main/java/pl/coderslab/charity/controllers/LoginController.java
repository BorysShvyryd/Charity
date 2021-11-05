package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

//    @PostMapping("/login")
//    public String messageSend(Model model,
//                              @RequestParam String name,
//                              @RequestParam String surname,
//                              @RequestParam String message) {
//
//        charityMessageService.charityMessageSend(name, surname, message);
//
//        model.addAttribute("textMessage", "Twoja wiadomość została wysłana poprawnie.");
//        return "form-confirmation";
//    }
}

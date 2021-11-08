package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.component.JwtProvider;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;

    public LoginController(UserService userService, EmailService emailService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.emailService = emailService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping
    public String loginForm() {
        return "login";
    }

    @GetMapping("/forgot")
    public String forgotPassForm() {
        return "forgot-password-form";
    }

    @PostMapping("/forgot")
    public String forgotPassSend(@RequestParam("email") String email, Model model, HttpServletRequest request) {

        User restoreUser = userService.findByEmail(email);

        if (restoreUser == null) {
            model.addAttribute("textMessage", "<p>Nie znaleziono użytkownika z tym adresem e-mail</p>"
                    + "<p><a href=\"/login/forgot\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }

        String tokenEmail = jwtProvider.generateToken(email);

        model.addAttribute("sendEmail",
                emailService.SendEmail(restoreUser.getEmail(),
                        "Odzyskiwanie hasła",
                        "Aby zresetować hasło, kliknij link: "
                                + request.getHeader("referer")
                                + "/"
                                + tokenEmail)
        );

        model.addAttribute("textMessage", "<p>Link do resetowania hasła został</p>" +
                "<p>wysłany na Twój adres e-mail.</p>");

        return "form-confirmation";
    }

    @GetMapping("/forgot/{token}")
    public String forgotPassForm(Model model, @PathVariable String token) {

        if (token == null) {
            return "redirect:/login";
        } else {
            if (!jwtProvider.validateToken(token)) {
                model.addAttribute("textMessage", "<p>Błąd linku lub link jest nieprawidłowy.</p>"
                        + "<p><a href=\"/login/forgot\" class=\"btn btn--without-border\">Powtarzać</a></p>");
                return "form-confirmation";
            }
        }

        User restoreUser = userService.findByEmail(jwtProvider.getLoginFromToken(token));
        model.addAttribute("user", restoreUser);

        return "register-new-password";
    }

    @PostMapping("/forgot/{token}")
    public String forgotPassSubmit(Model model, User user, @PathVariable String token) {

        if (!user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("textMessage", "<p>Hasła nie pasują do siebie.</p>"
                    + "<p><a href=\"/login/forgot/" + token +
                    "\" class=\"btn btn--without-border\">Powtarzać</a></p>");
            return "form-confirmation";
        }

        if (user.getPassword().length() < 8) {
            model.addAttribute("textMessage", "<p>Złe hasło.</p>"
                    + "<p><a href=\"/login/forgot/" + token +
                    "\" class=\"btn btn--without-border\">Powtarzać</a></p>");
            return "form-confirmation";
        }

        User restoreUser = userService.findByEmail(jwtProvider.getLoginFromToken(token));
        if (restoreUser != null) {
            restoreUser.setPassword(user.getPassword());
            userService.saveNewPassUser(restoreUser);
        }

        return "redirect:/login";
    }
}

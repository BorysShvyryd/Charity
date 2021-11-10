package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.component.JwtProvider;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    private final UserServiceImpl userService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;

    public RegistrationController(UserServiceImpl userService, EmailService emailService, JwtProvider jwtProvider, RoleRepository roleRepository) {
        this.userService = userService;
        this.emailService = emailService;
        this.jwtProvider = jwtProvider;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/register")
    public String preRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pre_registration", true);
        return "register";
    }

    @PostMapping("/register")
    public String preRegistrationSubmit(User user,
                                        Model model,
                                        HttpServletRequest request) {

        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("textMessage", "<p>Użytkownik z tym adresem e-mail jest już zarejestrowany</p>"
                    + "<p><a href=\"/register\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }

        if (!user.getEmail().matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}")) {
            model.addAttribute("textMessage", "<p>Niepoprawny adres email</p>"
                    + "<p><a href=\"/register\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }

        String token = jwtProvider.generateToken(user.getEmail());
        emailService.SendEmail(user.getEmail()
                , "Service CHARITY"
                , "Aby uzyskać dalszą rejestrację, kliknij link: https://" +
                        request.getHeader("host") +
                        "/register/" +
                        token);

        model.addAttribute("textMessage", "<p>Na Twój adres e-mail został wysłany link</p>" +
                "<p>do dalszej rejestracji</p>");

        return "form-confirmation";
    }

    @GetMapping("/register/{token}")
    public String registrationForm(Model model,
                                   @PathVariable String token) {

        if (!jwtProvider.validateToken(token)) {
            model.addAttribute("textMessage", "<p>Nieprawidłowy lub nieaktualny token</p>"
                    + "<p>Spróbuj zarejestrować się ponownie</p>"
                    + "<p><a href=\"/register\" class=\"btn btn--without-border\">Powtarzać</a></p>");
            return "form-confirmation";
        }

        if (roleRepository.count() == 0)
            initializationRole();

        User registersUser = new User();
        registersUser.setEmail(jwtProvider.getLoginFromToken(token));
        model.addAttribute("user", registersUser);
        model.addAttribute("pre_registration", false);
        return "register";
    }

    private void initializationRole() {

        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);
        role.setName("ROLE_ADMIN");
        roleRepository.save(role);
    }

    @PostMapping("/register/{token}")
    public String registrationSubmit(Model model,
                                     User user,
                                     @PathVariable String token) {

        if (!jwtProvider.validateToken(token)) {
            model.addAttribute("textMessage", "<p>Nieprawidłowy lub nieaktualny token</p>"
                    + "<p>Spróbuj zarejestrować się ponownie</p>"
                    + "<p><a href=\"/register\" class=\"btn btn--without-border\">Powtarzać</a></p>");
            return "form-confirmation";
        }

        if (!user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("textMessage", "<p>Hasła nie pasują do siebie</p>"
                    + "<p><a href=\"/register/" + token +"\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }

        if (user.getPassword().length() < 8) {
            model.addAttribute("textMessage", "<p>Krótkie hasło</p>"
                    + "<p><a href=\"/register/" + token +"\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }

        userService.save(user);

        emailService.SendEmail(user.getEmail()
                , "Service CHARITY"
                , "Dziękujemy za rejestrację na naszej stronie.");

        return "redirect:/login";
    }
}

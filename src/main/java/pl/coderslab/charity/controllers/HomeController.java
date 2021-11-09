package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.CurrentUserDetails;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.CharityMessageService;
import pl.coderslab.charity.service.UserService;

@Controller
@SessionAttributes("currentUserName")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final CharityMessageService charityMessageService;
    private final UserService userService;

    public HomeController(InstitutionService institutionService,
                          DonationService donationService,
                          CharityMessageService charityMessageService,
                          UserService userService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.charityMessageService = charityMessageService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String homeAction(Model model, @AuthenticationPrincipal CurrentUserDetails currentUser) {
        User user = currentUser.getUser();
        if (user != null)
            model.addAttribute("currentUserName", user.getName());

        model.addAttribute("institutions", institutionService.lastFourInstitutions());
        model.addAttribute("allBagsReturned", donationService.sumOfAllBagsReturned());
        model.addAttribute("countDonations", donationService.count());
        return "index";
    }

    @PostMapping("/")
    public String sendMessageSubmit(Model model,
                                    @RequestParam String name,
                                    @RequestParam String surname,
                                    @RequestParam String message) {

        charityMessageService.charityMessageSend(name, surname, message);
        model.addAttribute("textMessage", "Twoja wiadomość została wysłana poprawnie.");
        return "form-confirmation";
    }
}

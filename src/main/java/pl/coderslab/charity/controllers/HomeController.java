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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("currentUserName")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final CharityMessageService charityMessageService;

    public HomeController(InstitutionService institutionService,
                          DonationService donationService,
                          CharityMessageService charityMessageService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.charityMessageService = charityMessageService;
    }

    @GetMapping
    public String homeAction(Model model,
                             @AuthenticationPrincipal CurrentUserDetails currentUser,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        if (currentUser != null) {
            User user = currentUser.getUser();
            model.addAttribute("currentUserName", user.getName());
        }

//        if (request.getParameter("lang") != null) {
//            Cookie cookie = new Cookie("lang", request.getParameter("lang"));
//            response.addCookie(cookie);
//        }

        model.addAttribute("institutions", institutionService.lastFourInstitutions());
        model.addAttribute("allBagsReturned", donationService.sumOfAllBagsReturned());
        model.addAttribute("countDonations", donationService.count());
        return "index";
    }

    @PostMapping
    public String sendMessageSubmit(Model model,
                                    @RequestParam String name,
                                    @RequestParam String surname,
                                    @RequestParam String message) {

        charityMessageService.charityMessageSend(name, surname, message);
        model.addAttribute("textMessage", "Twoja wiadomość została wysłana poprawnie.");
        return "form-confirmation";
    }
}

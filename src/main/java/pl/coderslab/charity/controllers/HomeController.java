package pl.coderslab.charity.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.component.Messages;
import pl.coderslab.charity.entity.CurrentUserDetails;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.CookiesService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.CharityMessageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final CharityMessageService charityMessageService;
    private final CookiesService cookiesService;
    private final Messages messages;


    @GetMapping
    public String homeAction(Model model,
                             @AuthenticationPrincipal CurrentUserDetails currentUser,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        if (currentUser != null) {
            User user = currentUser.getUser();
            model.addAttribute("currentUserName", user.getName());
        }

        String lang = cookiesService.getLocationByCookie(request);
        if ("".equals(lang)) {
            lang = request.getLocale().toString();
            cookiesService.setLocationInCookie(request.getLocale(), response);
//            lang = "en";
        }
        messages.setLocale(lang);

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

package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.CurrentUserDetails;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.*;

import java.util.List;

@Controller
public class MainController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final EmailService emailService;
    private final UserService userService;

    public MainController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService, EmailService emailService, UserService userService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/charity")
    public String charity(@AuthenticationPrincipal CurrentUserDetails currentUser, Model model) {

        model.addAttribute("categories", categoryService.findAllActive());
        model.addAttribute("institutions", institutionService.findAllActive());
        model.addAttribute("donation", new Donation());
        model.addAttribute("loggedUser", currentUser.getUser());
        return "form";
    }

    @PostMapping("/charity")
    public String charitySubmit(Donation donation, @AuthenticationPrincipal CurrentUserDetails currUser, Model model) {

        User user = currUser.getUser();
        donation.setUser(user);
        donationService.save(donation);

        emailService.SendEmail(currUser.getUsername(), "Service CHARITY"
                , "\t\tPodsumowanie Twojej darowizny\n"
                        + "\t Oddajesz:\n"
                        + "\t\t" + donation.getQuantity() + " worki ubrań w dobrym stanie dla dzieci\n"
                        + "\t\t" + donation.getInstitution().getName() + "\n"
                        + "\t\t" + "Adres odbioru:\t\tTermin odbioru:\n"
                        + "\t\t" + donation.getStreet() + "\t\t\t" + donation.getPickUpDate() + "\n"
                        + "\t\t" + donation.getCity() + "\t\t\t" + donation.getPickUpTime() + "\n"
                        + "\t\t" + donation.getZipCode() + "\t\t\t" + donation.getPickUpComment() + "\n"
                        + "\t\t" + donation.getPhone() + "\n");

        model.addAttribute("textMessage"
                , "<p>Dziękujemy za przesłanie formularza.</p>" +
                        "<p>Na maila prześlemy wszelkie informacje o odbiorze.</p>");

        return "form-confirmation";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

    @GetMapping("/charity/profile")
    public String profileForm(@AuthenticationPrincipal CurrentUserDetails currentUser, Model model) {

        model.addAttribute("user", currentUser.getUser());

        return "profile";
    }

    @PostMapping("/charity/profile")
    public String profileSubmit(User user) {

        User changeUser = userService.findById(user.getId());
        changeUser.setName(user.getName());
        changeUser.setSurname(user.getSurname());
        changeUser.setAddress(user.getAddress());
        changeUser.setPhone(user.getPhone());

        userService.update(changeUser);

        return "redirect:/charity/profile";
    }

    @GetMapping("/charity/change-pass")
    public String changePassForm() {
        return "change-pass-form";
    }

    @PostMapping("/charity/change-pass")
    public String changePassSubmit(User user, @AuthenticationPrincipal CurrentUserDetails currUser, Model model) {

        if (!user.getPassword().equals(user.getPassword2())) return "change-pass-form";

        User currentUser = currUser.getUser();
        currentUser.setPassword(user.getPassword());
        userService.saveNewPassUser(currentUser);

        model.addAttribute("textMessage", "Hasło zostało pomyślnie zmienione.");

        return "form-confirmation";
    }

    @GetMapping("/charity/list-bag")
    public String listBagsForm(Model model, @AuthenticationPrincipal CurrentUserDetails currUser) {

        User user = currUser.getUser();
        List<Donation> donations = donationService.findAllByUserSortByStatus(user);
        model.addAttribute("donations", donations);
        model.addAttribute("title_page", "Lista wszystkich moich darów");

        return "admin-donations-list";
    }
}

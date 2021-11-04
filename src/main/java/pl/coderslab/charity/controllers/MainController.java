package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.InstitutionService;

import java.security.Principal;

@Controller
public class MainController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final EmailService emailService;

    public MainController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService, EmailService emailService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.emailService = emailService;
    }

    @GetMapping("/charity")
    public String charity(Model model) {

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/charity")
    public String charitySubmit(Donation donation, Principal principal) {

        donationService.save(donation);
        if (principal != null) {
            emailService.SendEmail(principal.getName(), "Service CHARITY"
                    ,"\t\tPodsumowanie Twojej darowizny\n"
            + "\t Oddajesz:\n"
            + "\t\t" + donation.getQuantity() + " worki ubrań w dobrym stanie dla dzieci\n"
            + "\t\t" + donation.getInstitution() + "\n"
            + "\t\t" + "Adres odbioru:\t\tTermin odbioru:\n"
            + "\t\t" + donation.getStreet() + "\t\t\t" + donation.getPickUpDate() + "\n"
            + "\t\t" + donation.getCity() + "\t\t\t" + donation.getPickUpTime() + "\n"
            + "\t\t" + donation.getZipCode() + "\t\t\t" + donation.getPickUpComment() + "\n"
            + "\t\t" + donation.getPhone() + "\n");
        }
        return "form-confirmation";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

    @GetMapping("/main")
    public String test() {
        return "403";
    }
}

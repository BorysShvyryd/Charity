package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.component.JwtProvider;
import pl.coderslab.charity.entity.*;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final CharityMessageService charityMessageService;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;

    public AdminController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService, UserService userService, CharityMessageService charityMessageService, RoleRepository roleRepository, EmailService emailService, JwtProvider jwtProvider) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.charityMessageService = charityMessageService;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/category/list")
    public String mainPage(Model model) {

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin-category-list";
    }

    @GetMapping("/category/status")
    public String statusCategoryChange(@RequestParam Long id) {

        Category category = categoryService.getById(id);
        category.setStatus(!category.getStatus());
        categoryService.save(category);
        return "redirect:/admin/category-list";
    }

    @GetMapping("/category/add")
    public String categoryAddForm(Model model) {

        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("title_form", "Dodaj kategorii");
        return "category-add-form";
    }

    @PostMapping("/category/add")
    public String categoryAddSubmit(@Valid Category category, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/category/add";
        }
        category.setStatus(true);
        categoryService.save(category);

        return "redirect:/admin/category-list";
    }

    @GetMapping("/category/edit")
    public String categoryEditForm(@RequestParam Long id, Model model) {

        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        model.addAttribute("title_form", "Edytuj kategorii");
        return "category-add-form";
    }

    @PostMapping("/category/edit")
    public String categoryEditSubmit(@Valid Category category, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/category/edit";
        }
        categoryService.save(category);

        return "redirect:/admin/category-list";
    }

    @GetMapping("/category/delete")
    public String categoryDeleteForm(@RequestParam Long id, Model model) {

        Category category = categoryService.getById(id);

        try {
            categoryService.delete(category);
            return "redirect:/admin/category-list";
        } catch (RuntimeException ex) {
            model.addAttribute("textMessage", "<p>Ta kategoria zawiera powiązane wpisy.</p>" +
                    "<p>Można go dezaktywować.</p>" +
                    "<p><a href=\"/admin/category/list\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }
    }

    //***************************************
    @GetMapping("/institution/list")
    public String institutionListForm(Model model) {

        List<Institution> institutions = institutionService.findAll();
        model.addAttribute("institutions", institutions);
        return "admin-institution-list";
    }

    @GetMapping("/institution/status")
    public String statusInstitutionChange(@RequestParam Long id) {

        Institution institution = institutionService.getById(id);
        institution.setStatus(!institution.getStatus());
        institutionService.save(institution);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/institution/add")
    public String institutionAddForm(Model model) {

        Institution institution = new Institution();
        model.addAttribute("institution", institution);
        model.addAttribute("title_form", "Dodaj fundacji");
        return "institution-add-form";
    }

    @PostMapping("/institution/add")
    public String institutionAddSubmit(@Valid Institution institution, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/institution/add";
        }
        institution.setStatus(true);
        institutionService.save(institution);

        return "redirect:/admin/institution/list";
    }

    @GetMapping("/institution/edit")
    public String institutionEditForm(@RequestParam Long id, Model model) {

        Institution institution = institutionService.getById(id);
        model.addAttribute("institution", institution);
        model.addAttribute("title_form", "Edytuj fundacji");
        return "institution-add-form";
    }

    @PostMapping("/institution/edit")
    public String institutionEditSubmit(@Valid Institution institution, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/institution/edit";
        }
        institutionService.save(institution);

        return "redirect:/admin/institution/list";
    }

    @GetMapping("/institution/delete")
    public String institutionDeleteForm(@RequestParam Long id, Model model) {

        Institution institution = institutionService.getById(id);

        try {
            institutionService.delete(institution);
            return "redirect:/admin/institution/list";
        } catch (RuntimeException ex) {
            model.addAttribute("textMessage", "<p>Ta fundacja zawiera powiązane wpisy.</p>" +
                    "<p>Można go dezaktywować.</p>" +
                    "<p><a href=\"/admin/institution/list\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }
    }

    //***************************************
    @GetMapping("/users/list")
    public String usersListForm(Model model) {

        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin-users-list";
    }

    @GetMapping("/users/list/{stream_change}")
    public String usersListForm(Model model, @PathVariable String stream_change) {

        String[] operations = stream_change.split(";");

        Stream<User> userStream = userService.findAll().stream();

        for (String operation : operations) {
            switch (operation.split("=")[0]) {
                case "filter":
                    switch (operation.split("=")[1]) {
                        case "admin":
                            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
                            userStream = userStream.filter(o -> o.getRoleSet().contains(roleAdmin));
                            break;
                        case "status_1":
                            userStream = userStream.filter(o -> o.getEnabled() == 1);
                            break;
                        case "status_0":
                            userStream = userStream.filter(o -> o.getEnabled() == 0);
                            break;
                    }
                    break;
                case "sort":
                    switch (operation.split("=")[1]) {
                        case "id_up":
                            userStream = userStream.sorted((o1, o2) -> (int) (o1.getId() - o2.getId()));
                            break;
                        case "id_down":
                            userStream = userStream.sorted((o1, o2) -> (int) (o2.getId() - o1.getId()));
                            break;
                        case "name_up":
                            userStream = userStream.sorted(Comparator.comparing(User::getName));
                            break;
                        case "name_down":
                            userStream = userStream.sorted((o1, o2) -> (o2.getName().compareTo(o1.getName())));
                            break;
                        case "surname_up":
                            userStream = userStream.sorted(Comparator.comparing(User::getSurname));
                            break;
                        case "surname_down":
                            userStream = userStream.sorted((o1, o2) -> (o2.getName().compareTo(o1.getSurname())));
                            break;
                    }
            }
        }

        List<User> users = userStream.collect(Collectors.toList());

        model.addAttribute("users", users);
        return "admin-users-list";
    }

    @GetMapping("/users/status")
    public String statusUserChange(@RequestParam Long id, @AuthenticationPrincipal CurrentUserDetails currUser, Model model) {

        User user = userService.findById(id);
        User currentUser = currUser.getUser();

        if (user.getId().equals(currentUser.getId())) {
            model.addAttribute("textMessage", "<p>Nie możesz zablokować swojego profilu.</p>" +
                    "<p><a href=\"/admin/users/list\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }

        if (user.getEnabled() == 1) {
            user.setEnabled(0);
        } else {
            user.setEnabled(1);
        }

        userService.update(user);
        return "redirect:/admin/users/list";
    }

    @GetMapping("/users/delete")
    public String userDeleteForm(@RequestParam Long id, Model model,  @AuthenticationPrincipal CurrentUserDetails currUser) {

        User user = userService.findById(id);
        User currentUser = currUser.getUser();

        if (user.getId().equals(currentUser.getId())) {
            model.addAttribute("textMessage", "<p>Nie możesz usunąć swojego profilu.</p>" +
                    "<p><a href=\"/admin/users/list\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }

        try {
            userService.delete(user);
            return "redirect:/admin/users/list";
        } catch (RuntimeException ex) {
            model.addAttribute("textMessage", "<p>Ten użytkownik ma powiązane wpisy.</p>" +
                    "<p><a href=\"/admin/users/list\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }
    }

    @GetMapping("/users/role")
    public String changeAdminRole(@RequestParam Long id, Model model,  @AuthenticationPrincipal CurrentUserDetails currUser) {

        User user = userService.findById(id);
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> roleSet = user.getRoleSet();
        String emailMessage = "";

        if (roleSet.contains(adminRole)) {
            if (userService.countAdmin() <= 1) {
                model.addAttribute("textMessage", "<p>Jesteś jedynym administratorem.</p>" +
                        "<p>To nie jest dla Ciebie dostępne.</p>" +
                        "<p><a href=\"/admin/users/list\" class=\"btn btn--without-border\">Powrót</a></p>");
                return "form-confirmation";
            } else {
                roleSet.remove(adminRole);
                emailMessage = "Rola administratora została dla Ciebie anulowana.";
            }
        } else {
            roleSet.add(adminRole);
            emailMessage = currUser.getUsername() + " mianował Cię administratorem Charity.";
        }

        user.setRoleSet(roleSet);
        userService.update(user);
        emailService.SendEmail(user.getName(), "Service CHARITY", emailMessage);
        return "redirect:/admin/users/list";
    }

    @GetMapping("/users/forgot")
    public String forgotPassSendMail(@RequestParam String email, HttpServletRequest request) {

        String tokenEmail = jwtProvider.generateToken(email);

        emailService.SendEmail(email,
                "Odzyskiwanie hasła",
                "Aby zresetować hasło, kliknij link: https://"
                        + request.getHeader("host")
                        + "/login/forgot/"
                        + tokenEmail);

        return "redirect:/admin/users/list";
    }

    //***************************************
    @GetMapping("/messages/list")
    public String messagesListForm(Model model) {

        List<CharityMessage> charityMessages = charityMessageService.findAll();
        model.addAttribute("messages", charityMessages);
        return "admin-messages-list";
    }

    @GetMapping("/messages/view")
    public String messagesViewForm(@RequestParam Long id, Model model) {

        CharityMessage message = charityMessageService.getById(id);
        model.addAttribute("message", message);
        message.setRead(true);
        charityMessageService.save(message);
        return "messages-view-form";
    }

    @PostMapping("/messages/view")
    public String messagesViewSubmit() {
        return "redirect:/admin/messages/list";
    }

    @GetMapping("/messages/delete")
    public String messagesDeleteForm(@RequestParam Long id, Model model) {

        CharityMessage message = charityMessageService.getById(id);

        if (message.getRead()) {
            charityMessageService.delete(message);
            return "redirect:/admin/messages/list";
        } else {
            model.addAttribute("textMessage", "<p>Ta wiadomość nie została jeszcze przeczytana.</p>" +
                    "<p>Nie możesz go usunąć.</p>" +
                    "<p><a href=\"/admin/messages/list\" class=\"btn btn--without-border\">Powrót</a></p>");
            return "form-confirmation";
        }
    }

    //***************************************
    @GetMapping("/donations/list")
    public String donationsListForm(Model model) {

        List<Donation> donations = donationService.findAllSortByStatus();
        model.addAttribute("donations", donations);
        model.addAttribute("title_page", "Lista darów");
        return "admin-donations-list";
    }

    @GetMapping("/donations/list/{stream_change}")
    public String donationsListSort(Model model,
                                     @PathVariable String stream_change) {

        String[] operations = stream_change.split(";");

        Stream<Donation> donationsStream = donationService.findAll().stream();

        for (String operation : operations) {
            if ("sort".equals(operation.split("=")[0])) {
                switch (operation.split("=")[1]) {
                    case "institution_up":
                        donationsStream = donationsStream.sorted((o1, o2) ->
                                o1.getInstitution().getName().compareTo(o2.getInstitution().getName()));
                        break;
                    case "institution_down":
                        donationsStream = donationsStream.sorted((o1, o2) ->
                                o2.getInstitution().getName().compareTo(o1.getInstitution().getName()));
                        break;
                    case "address_up":
                        donationsStream = donationsStream.sorted((o1, o2) ->
                                o1.getStreet().compareTo(o2.getStreet()));
                        break;
                    case "address_down":
                        donationsStream = donationsStream.sorted((o1, o2) ->
                                o2.getStreet().compareTo(o1.getStreet()));
                        break;
                    case "username_up":
                        donationsStream = donationsStream.sorted((o1, o2) ->
                                (o1.getUser().getName() + o1.getUser().getSurname())
                                        .compareTo(o2.getUser().getName() + o2.getUser().getSurname()));
                        break;
                    case "username_down":
                        donationsStream = donationsStream.sorted((o1, o2) ->
                                (o2.getUser().getName() + o2.getUser().getSurname())
                                        .compareTo(o1.getUser().getName() + o1.getUser().getSurname()));
                        break;
                }
            }
        }

        List<Donation> donations = donationsStream.collect(Collectors.toList());

        model.addAttribute("donations", donations);
        model.addAttribute("title_page", "Lista darów");
        return "admin-donations-list";
    }

    @PostMapping("/donations/list/{stream_change}")
    public String donationsListFiltr(Model model,
                                     @PathVariable String stream_change,
                                     @RequestParam String querySearch) {

        String[] operations = stream_change.split(";");

        Stream<Donation> donationsStream = donationService.findAllSortByStatus().stream();

        if (querySearch.trim().equals("")) {
            List<Donation> donations = donationsStream.collect(Collectors.toList());

            model.addAttribute("donations", donations);
            model.addAttribute("title_page", "Lista darów");
            return "admin-donations-list";
        }

        for (String operation : operations) {
            if ("filter".equals(operation.split("=")[0])) {
                switch (operation.split("=")[1]) {
                    case "username":
                        donationsStream = donationsStream.filter(o ->
                                (o.getUser().getSurname() + o.getUser().getName()).toLowerCase()
                                        .contains(querySearch.toLowerCase()));
                        break;
                    case "institution":
                        donationsStream = donationsStream.filter(o ->
                                o.getInstitution().getName().toLowerCase()
                                        .contains(querySearch.toLowerCase()));
                        break;
                    case "address":
                        donationsStream = donationsStream.filter(o ->
                                o.getStreet().toLowerCase()
                                        .contains(querySearch.toLowerCase()));
                        break;
                }
            }
        }

        List<Donation> donations = donationsStream.collect(Collectors.toList());

        model.addAttribute("donations", donations);
        model.addAttribute("title_page", "Lista darów");
        return "admin-donations-list";
    }

    @GetMapping("/donations/devoted")
    public String devotedSave(@RequestParam Long id) {

        Donation donation = donationService.getById(id);
        donation.setStatus((byte) 1);
        donation.setDateTimeReceived(LocalDateTime.now());
        donationService.save(donation);
        emailService.SendEmail(donation.getUser().getName(),
                "Service CHARITY",
                "Twój prezent został odebrany: " + donation.getDateTimeReceived()
                        + "\nDziękuję, że nie jesteś obojętny.");
        return "redirect:/admin/donations/list";
    }

    @GetMapping("/donations/transfer")
    public String transferSave(@RequestParam Long id) {

        Donation donation = donationService.getById(id);
        donation.setStatus((byte) 2);
        donation.setDateTimeTransmitted(LocalDateTime.now());
        donationService.save(donation);
        emailService.SendEmail(donation.getUser().getName(),
                "Service CHARITY",
                "Twój prezent został dostarczony do " + donation.getInstitution().getName()
                        + ": " + donation.getDateTimeTransmitted()
                        + "\nDziękuję, że nie jesteś obojętny.");
        return "redirect:/admin/donations/list";
    }

}

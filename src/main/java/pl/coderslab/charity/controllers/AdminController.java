package pl.coderslab.charity.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.component.JwtProvider;
import pl.coderslab.charity.component.Messages;
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

    private static final Logger log = LogManager.getLogger(AdminController.class);

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final CharityMessageService charityMessageService;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;
    private final CookiesService cookiesService;
    private final Messages messages;

    public AdminController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService, UserService userService, CharityMessageService charityMessageService, RoleRepository roleRepository, EmailService emailService, JwtProvider jwtProvider, CookiesService cookiesService, Messages messages) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.charityMessageService = charityMessageService;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.jwtProvider = jwtProvider;
        this.cookiesService = cookiesService;
        this.messages = messages;
    }

    @GetMapping()
    public String adminPage() {
        return "redirect:/";
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
        return "redirect:/admin/category/list";
    }

    @GetMapping("/category/add")
    public String categoryAddForm(Model model,
                                  HttpServletRequest request) {

        Category category = new Category();
        model.addAttribute("category", category);
        setLocateByCookie(request);
        model.addAttribute("title_form", messages.get("map-admin-controller-get-category-add"));

        return "category-add-form";
    }

    @PostMapping("/category/add")
    public String categoryAddSubmit(@Valid Category category, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/category/add";
        }
        category.setStatus(true);
        categoryService.save(category);

        return "redirect:/admin/category/list";
    }

    @GetMapping("/category/edit")
    public String categoryEditForm(@RequestParam Long id,
                                   Model model,
                                   HttpServletRequest request) {

        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        setLocateByCookie(request);
        model.addAttribute("title_form", messages.get("map-admin-controller-get-category-edit"));

        return "category-add-form";
    }

    @PostMapping("/category/edit")
    public String categoryEditSubmit(@Valid Category category, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/category/edit";
        }
        categoryService.save(category);

        return "redirect:/admin/category/list";
    }

    @GetMapping("/category/delete")
    public String categoryDeleteForm(@RequestParam Long id,
                                     Model model,
                                     HttpServletRequest request) {

        Category category = categoryService.getById(id);

        try {
            categoryService.delete(category);
            log.info("Category: " + category.getName() + " - deleted");

            return "redirect:/admin/category/list";
        } catch (RuntimeException ex) {
            setLocateByCookie(request);
            model.addAttribute("textMessage",  messages.get("map-admin-controller-get-category-delete"));
            log.error("Category: " + category.getName() + " - " + ex.getMessage());

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
    public String institutionAddForm(Model model,
                                     HttpServletRequest request) {

        Institution institution = new Institution();
        model.addAttribute("institution", institution);
        setLocateByCookie(request);
        model.addAttribute("title_form", messages.get("map-admin-controller-get-institution-add"));

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
    public String institutionEditForm(@RequestParam Long id,
                                      Model model,
                                      HttpServletRequest request) {

        Institution institution = institutionService.getById(id);
        model.addAttribute("institution", institution);
        setLocateByCookie(request);
        model.addAttribute("title_form", messages.get("map-admin-controller-get-institution-edit"));

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
    public String institutionDeleteForm(@RequestParam Long id,
                                        Model model,
                                        HttpServletRequest request) {

        Institution institution = institutionService.getById(id);

        try {
            institutionService.delete(institution);
            log.info("Institution: " + institution.getName() + " - deleted");
            return "redirect:/admin/institution/list";
        } catch (RuntimeException ex) {
            log.info("Institution: " + institution.getName() + " - " + ex.getMessage());
            setLocateByCookie(request);
            model.addAttribute("textMessage", messages.get("map-admin-controller-get-institution-delete"));

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
                        case "email_up":
                            userStream = userStream.sorted((o1, o2) -> (o2.getEmail().compareTo(o1.getEmail())));
                            break;
                        case "email_down":
                            userStream = userStream.sorted(Comparator.comparing(User::getEmail));
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

    @PostMapping("/users/list/{stream_change}")
    public String usersListFiltr(Model model,
                                     @PathVariable String stream_change,
                                     @RequestParam String querySearch) {

        String[] operations = stream_change.split(";");

        Stream<User> usersStream = userService.findAll().stream();

        if (querySearch.trim().equals("")) {
            List<User> users = usersStream.collect(Collectors.toList());

            model.addAttribute("users", users);
            return "admin-users-list";
        }

        for (String operation : operations) {
            if ("filter".equals(operation.split("=")[0])) {
                switch (operation.split("=")[1]) {
                    case "email":
                        usersStream = usersStream.filter(o ->
                                o.getEmail().toLowerCase()
                                        .contains(querySearch.toLowerCase()));
                        break;
                    case "name":
                        usersStream = usersStream.filter(o -> o.getName() != null)
                                .filter(o ->
                                o.getName().toLowerCase()
                                        .contains(querySearch.toLowerCase()));
                        break;
                    case "surname":
                        usersStream = usersStream.filter(o -> o.getSurname() != null)
                                .filter(o ->
                                o.getSurname().toLowerCase()
                                        .contains(querySearch.toLowerCase()));
                        break;
                }
            }
        }

        List<User> users = usersStream.collect(Collectors.toList());

        model.addAttribute("users", users);
        return "admin-users-list";
    }

    @GetMapping("/users/status")
    public String statusUserChange(@RequestParam Long id,
                                   @AuthenticationPrincipal CurrentUserDetails currUser,
                                   Model model,
                                   HttpServletRequest request) {

        User user = userService.findById(id);
        User currentUser = currUser.getUser();

        if (user.getId().equals(currentUser.getId())) {
            setLocateByCookie(request);
            model.addAttribute("textMessage", messages.get("map-admin-controller-get-users-status"));
            return "form-confirmation";
        }

        if (user.getEnabled() == 1) {
            user.setEnabled(0);
            log.info("User: " + user.getEmail() + " - unblocked");
        } else {
            user.setEnabled(1);
            log.info("User: " + user.getEmail() + " - blocked");
        }

        userService.update(user);
        return "redirect:/admin/users/list";
    }

    @GetMapping("/users/delete")
    public String userDeleteForm(@RequestParam Long id,
                                 Model model,
                                 @AuthenticationPrincipal CurrentUserDetails currUser,
                                 HttpServletRequest request) {

        User user = userService.findById(id);
        User currentUser = currUser.getUser();

        if (user.getId().equals(currentUser.getId())) {
            setLocateByCookie(request);
            model.addAttribute("textMessage", messages.get("map-admin-controller-get-users-delete"));

            return "form-confirmation";
        }

        try {
            userService.delete(user);
            log.info("User: " + user.getEmail() + " - deleted");
            return "redirect:/admin/users/list";
        } catch (RuntimeException ex) {
            log.info("User: " + user.getEmail() + " - " + ex.getMessage());
            setLocateByCookie(request);
            model.addAttribute("textMessage", messages.get("map-admin-controller-get-users-delete-catch"));

            return "form-confirmation";
        }
    }

    @GetMapping("/users/role")
    public String changeAdminRole(@RequestParam Long id,
                                  Model model,
                                  @AuthenticationPrincipal CurrentUserDetails currUser,
                                  HttpServletRequest request) {

        User user = userService.findById(id);
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> roleSet = user.getRoleSet();
        String emailMessage = "";

        setLocateByCookie(request);

        if (roleSet.contains(adminRole)) {
            if (userService.countAdmin() <= 1) {
                model.addAttribute("textMessage", messages.get("map-admin-controller-get-users-role-confirm"));
                return "form-confirmation";
            } else {
                roleSet.remove(adminRole);
                emailMessage = messages.get("map-admin-controller-get-users-role-emailMessage-del");
            }
        } else {
            roleSet.add(adminRole);
            emailMessage = currUser.getUsername() + messages.get("map-admin-controller-get-users-role-emailMessage-add");
        }

        user.setRoleSet(roleSet);
        userService.update(user);
        emailService.SendEmail(user.getEmail(), "Service CHARITY", emailMessage);
        return "redirect:/admin/users/list";
    }

    @GetMapping("/users/forgot")
    public String forgotPassSendMail(@RequestParam String email,
                                     HttpServletRequest request) {

        String tokenEmail = jwtProvider.generateToken(email);

        setLocateByCookie(request);

        emailService.SendEmail(email,
                messages.get("map-admin-controller-get-users-forgot-topic"),
                messages.get("map-admin-controller-get-users-forgot-textEmail")
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
    public String messagesDeleteForm(@RequestParam Long id,
                                     Model model,
                                     HttpServletRequest request) {

        CharityMessage message = charityMessageService.getById(id);

        if (message.getRead()) {
            charityMessageService.delete(message);
            return "redirect:/admin/messages/list";
        } else {
            setLocateByCookie(request);
            model.addAttribute("textMessage",  messages.get("map-admin-controller-get-messages-delete"));

            return "form-confirmation";
        }
    }

    //***************************************
    @GetMapping("/donations/list")
    public String donationsListForm(Model model,
                                    HttpServletRequest request) {

        List<Donation> donations = donationService.findAllSortByStatus();
        model.addAttribute("donations", donations);
        setLocateByCookie(request);
        model.addAttribute("title_page", messages.get("map-admin-controller-get-donations-list"));

        return "admin-donations-list";
    }

    @GetMapping("/donations/list/{stream_change}")
    public String donationsListSort(Model model,
                                    @PathVariable String stream_change,
                                    HttpServletRequest request) {

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
        setLocateByCookie(request);
        model.addAttribute("title_page", messages.get("map-admin-controller-get-donations-list"));

        return "admin-donations-list";
    }

    @PostMapping("/donations/list/{stream_change}")
    public String donationsListFiltr(Model model,
                                     @PathVariable String stream_change,
                                     @RequestParam String querySearch,
                                     HttpServletRequest request) {

        String[] operations = stream_change.split(";");

        Stream<Donation> donationsStream = donationService.findAllSortByStatus().stream();

        setLocateByCookie(request);

        if (querySearch.trim().equals("")) {
            List<Donation> donations = donationsStream.collect(Collectors.toList());
            model.addAttribute("donations", donations);
            model.addAttribute("title_page", messages.get("map-admin-controller-get-donations-list"));

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
        model.addAttribute("title_page", messages.get("map-admin-controller-get-donations-list"));
        return "admin-donations-list";
    }

    @GetMapping("/donations/devoted")
    public String devotedSave(@RequestParam Long id,
                              HttpServletRequest request) {

        Donation donation = donationService.getById(id);
        donation.setStatus((byte) 1);
        donation.setDateTimeReceived(LocalDateTime.now());
        donationService.save(donation);

        setLocateByCookie(request);

        emailService.SendEmail(donation.getUser().getEmail(),
                "Service CHARITY",
                messages.get("map-admin-controller-get-donations-devoted-textEmail1") + donation.getDateTimeReceived()
                        + messages.get("map-admin-controller-get-donations-devoted-textEmail2"));
        return "redirect:/admin/donations/list";
    }

    @GetMapping("/donations/transfer")
    public String transferSave(@RequestParam Long id,
                               HttpServletRequest request) {

        Donation donation = donationService.getById(id);
        donation.setStatus((byte) 2);
        donation.setDateTimeTransmitted(LocalDateTime.now());
        donationService.save(donation);

        setLocateByCookie(request);

        emailService.SendEmail(donation.getUser().getEmail(),
                "Service CHARITY",
                messages.get("map-admin-controller-get-donations-transfer-textEmail1") + donation.getInstitution().getName()
                        + ": " + donation.getDateTimeTransmitted()
                        + messages.get("map-admin-controller-get-donations-devoted-textEmail1"));
        return "redirect:/admin/donations/list";
    }

    private void setLocateByCookie(HttpServletRequest request) {
        String lang = cookiesService.getLocationByCookie(request);
        if ("".equals(lang)) lang = "en";
        messages.setLocale(lang);
    }

}

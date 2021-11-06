package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.CharityMessageService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final UserService userService;
    private final CharityMessageService charityMessageService;

    public AdminController(CategoryService categoryService, InstitutionService institutionService, UserService userService, CharityMessageService charityMessageService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.userService = userService;
        this.charityMessageService = charityMessageService;
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
                    "<p><a href=\"/admin/category-list\" class=\"btn btn--without-border\">Powrót</a></p>");
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

//    @GetMapping("/category/edit")
//    public String categoryEditForm(@RequestParam Long id, Model model) {
//
//        Category category = categoryService.getById(id);
//        model.addAttribute("category", category);
//        model.addAttribute("title_form", "Edytuj kategorii");
//        return "category-add-form";
//    }
//
//    @PostMapping("/category/edit")
//    public String categoryEditSubmit(@Valid Category category, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "redirect:/admin/category/edit";
//        }
//        categoryService.save(category);
//
//        return "redirect:/admin/category-list";
//    }
//
//    @GetMapping("/category/delete")
//    public String categoryDeleteForm(@RequestParam Long id, Model model) {
//
//        Category category = categoryService.getById(id);
//
//        try {
//            categoryService.delete(category);
//            return "redirect:/admin/category-list";
//        } catch (RuntimeException ex) {
//            model.addAttribute("textMessage", "<p>Ta kategoria zawiera powiązane wpisy.</p>" +
//                    "<p>Można go dezaktywować.</p>" +
//                    "<p><a href=\"/admin/category-list\" class=\"btn btn--without-border\">Powrót</a></p>");
//            return "form-confirmation";
//        }
//    }
}

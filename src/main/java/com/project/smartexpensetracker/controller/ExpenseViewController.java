package com.project.smartexpensetracker.controller;

import com.project.smartexpensetracker.service.ExpenseService;
import com.project.smartexpensetracker.model.expense;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExpenseViewController {

    private final ExpenseService service;

    public ExpenseViewController(ExpenseService service) {
        this.service = service;
    }

    // ✅ HOME PAGE (ONLY ONE)
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("expenses", service.getAll());
        model.addAttribute("total", service.total());

        // ✅ CATEGORY WISE DATA
        model.addAttribute("categories", service.categoryWise());

        return "index";
    }

    // EDIT PAGE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        expense e = service.getAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);

        model.addAttribute("expense", e);
        return "edit";
    }

    // UPDATE
    @PostMapping("/update")
    public String update(@ModelAttribute expense e) {
        service.update(e);
        return "redirect:/";
    }

    // ADD PAGE
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("expense", new expense());
        return "add";
    }

    // SAVE
    @PostMapping("/save")
    public String save(@ModelAttribute expense e) {
        service.save(e);
        return "redirect:/";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("categories", service.categoryWise());
        return "category";
    }
}
package com.project.smartexpensetracker.controller;

import com.project.smartexpensetracker.model.expense;
import com.project.smartexpensetracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping
    public List<expense> getAll() {
        return service.getAll();
    }

    @PostMapping
    public String add(@RequestBody expense e) {
        service.save(e);
        return "Added";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Deleted";
    }

    @GetMapping("/total")
    public Double total() {
        return service.total();
    }
}
package com.project.smartexpensetracker.service;

import com.project.smartexpensetracker.DAO.ExpenseDao;
import com.project.smartexpensetracker.model.expense;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    private final ExpenseDao dao;

    public ExpenseService(ExpenseDao dao) {
        this.dao = dao;
    }

    public List<expense> getAll() {
        return dao.getAll();
    }

    public void save(expense e) {
        dao.save(e);
    }

    public void update(expense e) {
        dao.update(e);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public Double total() {
        return dao.total();
    }

    public List<Map<String, Object>> categoryWise() {
        return dao.getCategoryWiseExpense();
    }
}
package com.project.smartexpensetracker.DAO;

import com.project.smartexpensetracker.model.expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExpenseDao {

    private final JdbcTemplate jdbcTemplate;

    public ExpenseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<expense> getAll() {
        return jdbcTemplate.query("SELECT * FROM expense",
                (rs, rowNum) -> {
                    expense e = new expense();
                    e.setId(rs.getInt("id"));
                    e.setTitle(rs.getString("title"));
                    e.setAmount(rs.getDouble("amount"));
                    e.setCategory(rs.getString("category"));
                    e.setDate(rs.getString("date"));
                    return e;
                });
    }

    public int save(expense e) {
        return jdbcTemplate.update(
                "INSERT INTO expense(title,amount,category,date) VALUES(?,?,?,?)",
                e.getTitle(), e.getAmount(), e.getCategory(), e.getDate()
        );
    }

    public int update(expense e) {
        return jdbcTemplate.update(
                "UPDATE expense SET title=?, amount=?, category=?, date=? WHERE id=?",
                e.getTitle(), e.getAmount(), e.getCategory(), e.getDate(), e.getId()
        );
    }
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM expense WHERE id=?", id);
    }


    public Double total() {
        return jdbcTemplate.queryForObject("SELECT SUM(amount) FROM expense", Double.class);
    }

    public List<Map<String, Object>> getCategoryWiseExpense() {
        return jdbcTemplate.queryForList(
                "SELECT category, SUM(amount) AS total FROM expense GROUP BY category"
        );
    }
}
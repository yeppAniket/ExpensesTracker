package com.ExpenseApp.webApp.service;

import com.ExpenseApp.webApp.exception.ResourceNotFoundException;
import com.ExpenseApp.webApp.model.Expense;
import com.ExpenseApp.webApp.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));
    }

    public Expense createExpense(Long groupId, Expense expense) {
        if (expense.getAmount() == null || expense.getCategory() == null || expense.getDate() == null) {
            throw new IllegalArgumentException("Expense details are incomplete");
        }

        // Implement validation if needed
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existingExpense = getExpenseById(id);
        // Implement validation if needed
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDate(updatedExpense.getDate());
        existingExpense.setDescription(updatedExpense.getDescription());
        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }
}
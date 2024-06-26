package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.dto.request.ExpenseRequest;
import com.nutrimeal.nutrimeal.model.Expense;
import com.nutrimeal.nutrimeal.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;


    public List<Expense> getAllExpense() {
        return expenseRepository.findAllByIsActiveTrue();
    }

    public Expense getExpenseById(int id) {
        return expenseRepository.findByExpenseIdAndIsActiveTrue(id).orElse(null);
    }

    public Expense addExpense(ExpenseRequest expenseRequest) {
        Expense expense = new Expense();
        expense.setIngredientName(expenseRequest.getIngredientName());
        expense.setQuantity(expenseRequest.getQuantity());
        expense.setUnitPrice(expenseRequest.getUnitPrice());
        expense.setSupplier(expenseRequest.getSupplier());
        expense.setPurchaseDate(expenseRequest.getPurchaseDate());
        expense.setExpirationDate(expenseRequest.getExpirationDate());
        expense.setIsActive(true);
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(int id, ExpenseRequest expenseRequest) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense == null) {
            return null;
        }
        expense.setIngredientName(expenseRequest.getIngredientName());
        expense.setQuantity(expenseRequest.getQuantity());
        expense.setUnitPrice(expenseRequest.getUnitPrice());
        expense.setSupplier(expenseRequest.getSupplier());
        expense.setPurchaseDate(expenseRequest.getPurchaseDate());
        expense.setExpirationDate(expenseRequest.getExpirationDate());
        return expenseRepository.save(expense);
    }

    public void deleteExpense(int id) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense == null) {
            return;
        }
        expense.setIsActive(false);
        expenseRepository.save(expense);
    }
}

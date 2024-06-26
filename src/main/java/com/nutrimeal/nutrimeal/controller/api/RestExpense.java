package com.nutrimeal.nutrimeal.controller.api;

import com.nutrimeal.nutrimeal.dto.request.ExpenseRequest;
import com.nutrimeal.nutrimeal.dto.response.ExpenseResponse;
import com.nutrimeal.nutrimeal.model.Expense;
import com.nutrimeal.nutrimeal.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class RestExpense {

    private final ExpenseService expenseService;

    @GetMapping()
    public ResponseEntity<List<ExpenseResponse>> getAllExpense() {
        return ResponseEntity.ok(expenseService.getAllExpense());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable int id) {
        if (expenseService.getExpense(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PostMapping()
    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseRequest expenseRequest) {
        return ResponseEntity.ok(expenseService.addExpense(expenseRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable int id, @RequestBody ExpenseRequest expenseRequest) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expenseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id) {
        Expense expense = expenseService.getExpense(id);
        if (expense == null) {
            return ResponseEntity.notFound().build();
        }
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense with id " + id + " deleted");
    }

}

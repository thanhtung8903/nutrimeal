package com.nutrimeal.nutrimeal.controller.manager.expense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerExpenseController {
    @GetMapping("/expense")
    public String manageExpense() {
        return "manager/expense/expenseMan";
    }
    @GetMapping("/expense/add")
    public String addExpense() {
        return "manager/expense/addExpense";
    }
}

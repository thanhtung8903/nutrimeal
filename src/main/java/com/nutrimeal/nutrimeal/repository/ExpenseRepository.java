package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    List<Expense> findAllByIsActiveTrue();

    Optional<Expense> findByExpenseIdAndIsActiveTrue(int id);
}

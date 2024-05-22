package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer> {
}

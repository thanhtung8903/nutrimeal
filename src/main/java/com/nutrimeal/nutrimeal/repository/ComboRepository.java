package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer> {

    List<Combo> findAllByComboCaloriesBetweenAndIsActiveTrue(Float min, Float max);


    List<Combo> findAllByIsActiveTrue();
}

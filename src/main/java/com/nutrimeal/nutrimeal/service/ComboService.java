package com.nutrimeal.nutrimeal.service;


import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.model.ComboType;
import com.nutrimeal.nutrimeal.repository.ComboRepository;
import com.nutrimeal.nutrimeal.repository.ComboTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComboService {
    private final ComboRepository comboRepository;
    private final ComboTypeRepository comboTypeRepository;

    public List<Combo> getAllComboActive() {
        return comboRepository.findAllByIsActiveTrue();
    }

    public Combo getComboById(int id) {
        return comboRepository.findById(id).orElse(null);
    }

    public ComboType getComboTypeById(int id) {
        return comboTypeRepository.findById(id).orElse(null);
    }

    public Combo addCombo(Combo combo) {
        return comboRepository.save(combo);
    }

    public void updateCombo(int id, Combo combo) {
        Combo oldCombo = comboRepository.findById(id).orElse(null);
        if (oldCombo != null) {
            oldCombo.setComboName(combo.getComboName());
            oldCombo.setComboDescription(combo.getComboDescription());
            oldCombo.setComboImage(combo.getComboImage());
            oldCombo.setComboCalories(combo.getComboCalories());
            oldCombo.setComboType(combo.getComboType());
            oldCombo.setComboPrice7Days(combo.getComboPrice7Days());
            oldCombo.setComboPrice30Days(combo.getComboPrice30Days());
            oldCombo.setComboTime(combo.getComboTime());
            comboRepository.save(oldCombo);
        }
    }

    public ComboType addComboType(ComboType comboType) {
        return comboTypeRepository.save(comboType);
    }

    public void deleteCombo(int id) {
        Combo combo = comboRepository.findById(id).orElse(null);
        if (combo != null) {
            combo.setIsActive(false);
            comboRepository.save(combo);
        }
    }

    public void deleteComboType(int id) {
        comboTypeRepository.deleteById(id);
    }

    public List<Combo> getCombosByCalories(int min, int max) {
        return comboRepository.findAllByComboCaloriesBetweenAndIsActiveTrue(min, max);
    }
}

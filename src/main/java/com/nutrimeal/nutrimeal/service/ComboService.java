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

    public List<Combo> getAllCombos() {
        return comboRepository.findAll();
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

    public ComboType addComboType(ComboType comboType) {
        return comboTypeRepository.save(comboType);
    }

    public void deleteCombo(int id) {
        comboRepository.deleteById(id);
    }

    public void deleteComboType(int id) {
        comboTypeRepository.deleteById(id);
    }

    public List<Combo> getCombosByCalories(float min, float max) {
        return comboRepository.findAllByComboCaloriesBetween(min, max);
    }
}

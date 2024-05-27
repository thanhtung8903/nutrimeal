package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Dish;
import com.nutrimeal.nutrimeal.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public List<Dish> findAllDish() {
        return dishRepository.findAll();
    }

    public Page<Dish> findAllDish(Pageable pageable) {
        return dishRepository.findAll(pageable);
    }

    public void save(Dish dish) {
        dishRepository.save(dish);
    }

    public Dish findDishById(Integer id) {
        return dishRepository.findById(id).orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    public void updateDish(Integer id, Dish dish) {
        Dish oldDish = dishRepository.findById(id).orElseThrow();
        oldDish.setDishName(dish.getDishName());
        oldDish.setDishDescription(dish.getDishDescription());
        oldDish.setDishImage(dish.getDishImage());
        oldDish.setDishProteins(dish.getDishProteins());
        oldDish.setDishFats(dish.getDishFats());
        oldDish.setDishCarbs(dish.getDishCarbs());
        oldDish.setDishCalories(dish.getDishCalories());
        oldDish.setDishType(dish.getDishType());
        dishRepository.save(oldDish);
    }

    public void deleteDish(Integer id) {
        dishRepository.deleteById(id);
    }
}

package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    Page<Dish> findAll(Pageable pageable);

}

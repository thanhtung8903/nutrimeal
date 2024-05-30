package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d WHERE d.isActive = true")
    Page<Dish> findAll(Pageable pageable);

    @Query("SELECT d FROM Dish d WHERE d.dishName LIKE %:name% AND d.dishType LIKE %:type% AND d.isActive = true")
    Page<Dish> findByDishNameContainingAndDishType_TypeNameContaining(
            @Param("name") String name,
            @Param("type") String type,
            Pageable pageable
    );

    @Query("SELECT d FROM Dish d WHERE d.isActive = true")
    List<Dish> findAllActiveDish();

//    find all dish have type
    @Query("SELECT d FROM Dish d WHERE d.dishType = :type AND d.isActive = true")
    List<Dish> findAllByDishType(@Param("type") String type);
}

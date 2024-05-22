package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer>{
}

package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.ComboType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboTypeRepository extends JpaRepository<ComboType, Integer>{
}

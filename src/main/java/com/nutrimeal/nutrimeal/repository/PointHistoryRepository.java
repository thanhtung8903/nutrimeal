package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.PointHistory;
import com.nutrimeal.nutrimeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, Integer> {

    List<PointHistory> findAllByUserOrderByPointHistoryIdDesc(User user);
}
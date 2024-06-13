package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.ForgetToken;
import com.nutrimeal.nutrimeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgetTokenRepository extends JpaRepository<ForgetToken, Integer> {
    ForgetToken findByUser(User user);
}

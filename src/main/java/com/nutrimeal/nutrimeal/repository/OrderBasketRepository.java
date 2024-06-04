package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.model.OrderBasket;
import com.nutrimeal.nutrimeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBasketRepository extends JpaRepository<OrderBasket, Integer> {

    OrderBasket findByUserAndCombo(User user, Combo combo);

    void deleteByUserAndCombo(User user, Combo combo);

    @Query("UPDATE OrderBasket orderBasket SET orderBasket.quantity = ?1 WHERE orderBasket.combo.comboId = ?2 " +
            "AND orderBasket.user.userId = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Integer comboId, String userId);
}

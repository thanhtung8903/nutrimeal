package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.model.OrderBasket;
import com.nutrimeal.nutrimeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBasketRepository extends JpaRepository<OrderBasket, Integer> {

    OrderBasket findByUserAndComboAndDay(User user, Combo combo, int day);

    @Query("UPDATE OrderBasket orderBasket SET orderBasket.quantity = ?1 WHERE orderBasket.combo.comboId = ?2 " +
            "AND orderBasket.user.userId = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Integer comboId, String userId);

    List<OrderBasket> findAllByUser(User user);

    OrderBasket findByOrderBasketIdAndUser(int orderBasketId, User user);
}

package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.model.OrderBasket;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.ComboRepository;
import com.nutrimeal.nutrimeal.repository.OrderBasketRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderBasketService {

    private final ComboRepository comboRepository;
    private final OrderBasketRepository orderBasketRepository;
    private final ComboService comboService;

    public List<OrderBasket> findAllByUser(User user) {
        return orderBasketRepository.findAllByUser(user);
    }


    public int addComboToBasket(int comboId, User user, int day) {
        Combo combo = comboRepository.findById(comboId).orElseThrow();
        OrderBasket orderBasket = orderBasketRepository.findByUserAndComboAndDay(user, combo, day);
        if (orderBasket != null && orderBasket.getQuantity() >= 5) {
            return 5;
        } else {
            orderBasket = orderBasketRepository.findByUserAndComboAndDay(user, combo, day);

            if (orderBasket != null) {
                orderBasket.setQuantity(orderBasket.getQuantity() + 1);
            } else {
                orderBasket = new OrderBasket();
                orderBasket.setCombo(combo);
                orderBasket.setUser(user);
                orderBasket.setQuantity(1);
                orderBasket.setDay(day);
            }
            orderBasketRepository.save(orderBasket);
            return orderBasket.getQuantity();
        }
    }

    public int updateQuantity(int quantity, int comboId, User user) {
        orderBasketRepository.updateQuantity(quantity, comboId, user.getUserId());
        Combo combo = comboRepository.findById(comboId).orElseThrow();
        return combo.getComboPrice30Days() * quantity;
    }


    public void delete(OrderBasket orderBasket) {
        orderBasketRepository.delete(orderBasket);
    }
}
package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Combo;
import com.nutrimeal.nutrimeal.model.OrderBasket;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.ComboRepository;
import com.nutrimeal.nutrimeal.repository.OrderBasketRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderBasketService {

    private final ComboRepository comboRepository;
    private final OrderBasketRepository orderBasketRepository;

    public int addComboToBasket(int comboId, User user) {
        Combo combo = comboRepository.findById(comboId).orElseThrow();

        OrderBasket orderBasket = orderBasketRepository.findByUserAndCombo(user, combo);

        if (orderBasket != null) {
            orderBasket.setQuantity(orderBasket.getQuantity() + 1);
        } else {
            orderBasket = new OrderBasket();
            orderBasket.setCombo(combo);
            orderBasket.setUser(user);
            orderBasket.setQuantity(1);
        }
        orderBasketRepository.save(orderBasket);
        return orderBasket.getQuantity();
    }

    public int updateQuantity(int quantity, int comboId, User user) {
        orderBasketRepository.updateQuantity(quantity, comboId, user.getUserId());
        Combo combo = comboRepository.findById(comboId).orElseThrow();
        return combo.getComboPrice30Days() * quantity;
    }

    public void removeComboFromBasket(int comboId, User user) {
        orderBasketRepository.deleteByUserAndCombo(user, comboRepository.findById(comboId).orElseThrow());
    }
}

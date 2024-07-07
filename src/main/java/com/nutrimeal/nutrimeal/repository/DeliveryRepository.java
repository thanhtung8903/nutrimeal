package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Delivery;
import com.nutrimeal.nutrimeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    List<Delivery> findDeliveriesByDeliveryStatus(String deliveryStatus);

    Delivery findByDeliveryId(Integer deliveryId);

    List<Delivery> findDeliveriesByShipperAndDeliveryStatus(User shipper, String deliveryStatus);

    Delivery findByDeliveryIdAndShipper(Integer deliveryId, User shipper);

    List<Delivery> findAllByUserOrderByDeliveryDateAsc(User user);


    List<Delivery> findAllByShipperOrderByDeliveryDateAsc(User user);

}

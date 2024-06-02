package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.Address;
import com.nutrimeal.nutrimeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findByAddressIdAndUserAndIsActiveTrue(Integer addressId, User user);

    Optional<List<Address>> findAllByIsActiveTrueAndUserOrderByDefaultAddressDesc(User user);

}
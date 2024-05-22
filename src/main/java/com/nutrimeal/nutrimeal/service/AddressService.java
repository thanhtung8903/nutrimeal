package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.dto.request.AddressRequest;
import com.nutrimeal.nutrimeal.model.Address;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.AddressRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public void saveAddress(Address address, String name) {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
        address.setUser(user);
        addressRepository.save(address);
    }

    public void deleteAddress(Integer addressId, String name) {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
        Address address = addressRepository.findByAddressIdAndUser(addressId, user).orElseThrow(() -> new RuntimeException("Address not found"));
        addressRepository.delete(address);
    }

    public List<Address> findAllAddress(String name) {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
        return addressRepository.findAllByUser(user).orElseThrow(() -> new RuntimeException("Address not found"));
    }
}

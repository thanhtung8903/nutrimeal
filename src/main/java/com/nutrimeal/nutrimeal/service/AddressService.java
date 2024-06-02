package com.nutrimeal.nutrimeal.service;

import com.nutrimeal.nutrimeal.model.Address;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.AddressRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public void saveAddress(Address address, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        address.setUser(user);
        if (address.getDefaultAddress() == null) {
            address.setDefaultAddress(false);
        }

        if (address.getDefaultAddress()) {
            for (Address address1 :addressRepository.findAllByIsActiveTrueAndUserOrderByDefaultAddressDesc(user).orElseThrow(() -> new RuntimeException("Address not found"))) {
                address1.setDefaultAddress(false);
                addressRepository.save(address1);
            }
        }
        addressRepository.save(address);
    }

    public void deleteAddress(Integer addressId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Address address = addressRepository.findByAddressIdAndUserAndIsActiveTrue(addressId, user).orElseThrow(() -> new RuntimeException("Address not found"));
        address.setIsActive(false);
        addressRepository.save(address);
    }

    public List<Address> findAllAddressByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return addressRepository.findAllByIsActiveTrueAndUserOrderByDefaultAddressDesc(user).orElseThrow(() -> new RuntimeException("Address not found"));
    }


    public void updateAddress(Address address, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        address.setUser(user);
        address.setFullName(address.getFullName());
        address.setPhone(address.getPhone());
        address.setGender(address.getGender());
        address.setDistrict(address.getDistrict());
        address.setWard(address.getWard());
        address.setApartmentNumber(address.getApartmentNumber());

        if (address.getDefaultAddress() == null) {
            address.setDefaultAddress(false);
        }

        if (address.getDefaultAddress()) {
            for (Address address2 :addressRepository.findAllByIsActiveTrueAndUserOrderByDefaultAddressDesc(user).orElseThrow(() -> new RuntimeException("Address not found"))) {
                address2.setDefaultAddress(false);
                addressRepository.save(address2);
            }
        }
        address.setDefaultAddress(address.getDefaultAddress());
        addressRepository.save(address);
    }

    public void setDefaultAddress(Integer addressId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Address address = addressRepository.findByAddressIdAndUserAndIsActiveTrue(addressId, user).orElseThrow(() -> new RuntimeException("Address not found"));
        for (Address address1 :addressRepository.findAllByIsActiveTrueAndUserOrderByDefaultAddressDesc(user).orElseThrow(() -> new RuntimeException("Address not found"))) {
            address1.setDefaultAddress(false);
            addressRepository.save(address1);
        }
        address.setDefaultAddress(true);
        addressRepository.save(address);
    }
}
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

    public void saveAddress(Address address, String name) {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
        address.setUser(user);
        if (address.getDefaultAddress() == null) {
            address.setDefaultAddress(false);
        }

        if (address.getDefaultAddress()) {
            for (Address address1 :addressRepository.findAllByUserOrderByDefaultAddressDesc(user).orElseThrow(() -> new RuntimeException("Address not found"))) {
                address1.setDefaultAddress(false);
                addressRepository.save(address1);
            }
        }
        addressRepository.save(address);
    }

    public void deleteAddress(Integer addressId, String name) {
        User user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
        Address address = addressRepository.findByAddressIdAndUser(addressId, user).orElseThrow(() -> new RuntimeException("Address not found"));
        addressRepository.delete(address);
    }

    public List<Address> findAllAddressByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return addressRepository.findAllByUserOrderByDefaultAddressDesc(user).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public void updateAddress(Address address, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
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
            for (Address address2 :addressRepository.findAllByUserOrderByDefaultAddressDesc(user).orElseThrow(() -> new RuntimeException("Address not found"))) {
                address2.setDefaultAddress(false);
                addressRepository.save(address2);
            }
        }
        address.setDefaultAddress(address.getDefaultAddress());
        addressRepository.save(address);
    }
}
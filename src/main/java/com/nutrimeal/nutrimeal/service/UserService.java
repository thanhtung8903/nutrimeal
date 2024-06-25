package com.nutrimeal.nutrimeal.service;


import com.nutrimeal.nutrimeal.dto.request.AddressRequest;
import com.nutrimeal.nutrimeal.dto.request.ChangePasswordRequest;
import com.nutrimeal.nutrimeal.dto.request.SignupRequest;
import com.nutrimeal.nutrimeal.dto.request.UpdateUserRequest;
import com.nutrimeal.nutrimeal.dto.response.OrderResponse;
import com.nutrimeal.nutrimeal.dto.response.ShipperResponse;
import com.nutrimeal.nutrimeal.dto.response.UserInfoResponse;
import com.nutrimeal.nutrimeal.model.Order;
import com.nutrimeal.nutrimeal.model.Role;
import com.nutrimeal.nutrimeal.model.RoleName;
import com.nutrimeal.nutrimeal.model.User;
import com.nutrimeal.nutrimeal.repository.RoleRepository;
import com.nutrimeal.nutrimeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmailOrUsername(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(UpdateUserRequest updateUserRequest, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if (updateUserRequest.getEmail().equals(user.getEmail())) {
            user.setEmail(updateUserRequest.getEmail());
        } else {
            if (userRepository.existsByEmail(updateUserRequest.getEmail())) {
                throw new RuntimeException("Email already exists");
            } else {
                user.setEmail(updateUserRequest.getEmail());
            }
        }

        user.setFullName(updateUserRequest.getFullName());
        user.setPhone(updateUserRequest.getPhone());
        user.setGender(updateUserRequest.getGender());
        user.setDob(updateUserRequest.getDob());
        user.setImage(updateUserRequest.getImage());
        return userRepository.save(user);
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(changePasswordRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password not match");
        } else if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new RuntimeException("Password not match");
        } else if (!changePasswordRequest.getNewPassword().matches("^.{8,}$")) {
            throw new RuntimeException("Password must be at least 8 characters");
        }


        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

    public void changePasswordForget(String newPassword, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> findAllCustomer() {
        return userRepository.findAllCustomer();
    }

    public List<User> findAllManager() {
        return userRepository.findAllManager();
    }

    public List<User> findAllAdmin() {
        return userRepository.findAllAdmin();
    }

    public List<ShipperResponse> findAllShipper() {
        List<User> users = userRepository.findAllShipper();
        return users.stream().map(user -> {
            ShipperResponse response = new ShipperResponse();
            response.setFullName(user.getFullName());
            response.setId(user.getUserId());
            return response;
        }).collect(Collectors.toList());
    }

    public List<OrderResponse> findOrdersByCustomerId(String customerId) {
        User user = userRepository.findById(customerId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Order> orders = user.getOrders();
        return orders.stream().map(order -> {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getOrderId());
            response.setOrderStatus(order.getOrderStatus());
            response.setOrderTotalPrice(order.getOrderTotalPrice());
            response.setOrderDate(order.getOrderDate().toString());
            response.setPaymentMethod(order.getPaymentMethod().getPaymentMethodName());
            response.setDeliveryTime(order.getDeliveryTime().getDeliveryTime());
            response.setAddress(order.getAddress().getFullAddress());
            response.setFullName(order.getUser().getFullName());
            response.setPhone(order.getAddress().getPhone());
            response.setOrderNote(order.getOrderNote());
            response.setOrderTempPrice(order.getOrderTempPrice());
            response.setOrderDeliveryPrice(order.getOrderDeliveryPrice());
            response.setOrderDiscount(order.getOrderDiscount());
            return response;
        }).toList();
    }

    public List<UserInfoResponse> findAllCustomers() {
        List<User> users = userRepository.findAllCustomer();

        return users.stream().map(user -> {
            UserInfoResponse response = new UserInfoResponse(
                    user.getUserId(),
                    user.getEmail(),
                    user.getFullName(),
                    user.getPhone(),
                    user.getImage(),
                    user.getGender(),
                    user.getDob(),
                    user.getPoint(),
                    user.getRoles().stream().map(role -> role.getRoleName().name()).collect(Collectors.toList())
            );
            return response;
        }).collect(Collectors.toList());
    }

    public void save(User user) {
        userRepository.save(user);
    }
}


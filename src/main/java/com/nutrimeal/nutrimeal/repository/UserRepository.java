package com.nutrimeal.nutrimeal.repository;

import com.nutrimeal.nutrimeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByEmailOrUsername(String email, String username);


    @Query(value = "SELECT * FROM users u WHERE u.user_id IN (SELECT ur.user_id FROM user_roles ur WHERE ur.role_id = :roleId)", nativeQuery = true)
    public List<User> findAllUsersByRoleId(@Param("roleId") int roleId);

    default List<User> findAllCustomer() {
        return findAllUsersByRoleId(3);
    }

    default List<User> findAllManager() {
        return findAllUsersByRoleId(2);
    }

    default List<User> findAllAdmin() {
        return findAllUsersByRoleId(1);
    }

    default List<User> findAllShipper() {
        return findAllUsersByRoleId(4);
    }
}
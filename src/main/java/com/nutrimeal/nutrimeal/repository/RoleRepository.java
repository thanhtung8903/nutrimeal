package com.nutrimeal.nutrimeal.repository;


import com.nutrimeal.nutrimeal.model.Role;
import com.nutrimeal.nutrimeal.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
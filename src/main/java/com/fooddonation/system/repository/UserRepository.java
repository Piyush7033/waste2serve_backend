package com.fooddonation.system.repository;

import com.fooddonation.system.entity.Role;
import com.fooddonation.system.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    // ==========================================
    // FIND USER BY EMAIL
    // ==========================================
    Optional<User> findByEmail(
            String email
    );

    // ==========================================
    // CHECK EMAIL EXISTS
    // ==========================================
    boolean existsByEmail(
            String email
    );

    // ==========================================
    // FIND USERS BY ROLE
    // ==========================================
    List<User> findByRole(
            Role role
    );

    // ==========================================
    // FIND ACTIVE USERS
    // ==========================================
    List<User> findByActive(
            boolean active
    );

    // ==========================================
    // FIND ACTIVE USERS BY ROLE
    // ==========================================
    List<User> findByRoleAndActive(

            Role role,

            boolean active
    );

    // ==========================================
    // COUNT USERS BY ROLE
    // ==========================================
    long countByRole(
            Role role
    );

    // ==========================================
    // COUNT ACTIVE USERS
    // ==========================================
    long countByActive(
            boolean active
    );

    // ==========================================
    // COUNT ACTIVE USERS BY ROLE
    // ==========================================
    long countByRoleAndActive(

            Role role,

            boolean active
    );
}
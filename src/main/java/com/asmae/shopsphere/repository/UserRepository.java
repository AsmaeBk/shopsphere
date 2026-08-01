package com.asmae.shopsphere.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asmae.shopsphere.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
    
}

package com.example.licentabackend.repository;

import java.util.Optional;

import com.example.licentabackend.model.ERole;
import com.example.licentabackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

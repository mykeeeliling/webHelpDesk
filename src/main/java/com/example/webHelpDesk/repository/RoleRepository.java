package com.example.webHelpDesk.repository;

import com.example.webHelpDesk.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}

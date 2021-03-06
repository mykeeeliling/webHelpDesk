package com.example.webHelpDesk.repository;

import com.example.webHelpDesk.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByuserName(String username);
}

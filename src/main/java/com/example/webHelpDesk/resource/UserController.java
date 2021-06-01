package com.example.webHelpDesk.resource;

import com.example.webHelpDesk.domain.entity.User;
import com.example.webHelpDesk.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/add")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public String addUserByAdmin(@RequestBody User user){
        String regularPassword = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(regularPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return "User added";
    }
}

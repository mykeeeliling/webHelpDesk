package com.example.webHelpDesk.resource;

import com.example.webHelpDesk.domain.entity.Employee;
import com.example.webHelpDesk.domain.entity.User;
import com.example.webHelpDesk.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/landing")


    @GetMapping("/list")
    public List<User> list(){
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()){
            throw new IllegalStateException("Employee list is empty");
        }
        return userList;
    }

    @PostMapping("/add")
    public String addUserByAdmin(@RequestBody User user){
        String regularPassword = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(regularPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return "User added";
    }
}

package com.example.webHelpDesk.service;

import com.example.webHelpDesk.domain.entity.User;
import com.example.webHelpDesk.repository.UserRepository;
import com.example.webHelpDesk.service.impl.UserDetailsServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found " + userName));
        return user.map(UserDetailsServiceServiceImpl::new).get();
    }
}

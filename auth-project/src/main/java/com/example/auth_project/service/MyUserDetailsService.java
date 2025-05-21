package com.example.auth_project.service;

import com.example.auth_project.model.UserPrincipal;
import com.example.auth_project.model.entity.User;
import com.example.auth_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null){
            System.out.println("user not found.");
            throw new UsernameNotFoundException("Username is not found.");
        }

        return new UserPrincipal(user);
    }
}

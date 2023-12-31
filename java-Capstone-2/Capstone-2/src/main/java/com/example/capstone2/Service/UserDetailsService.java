package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiExeption;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=authRepository.findUserByUsername(username);
        if (user==null){
            throw new ApiExeption("Wrong username or password");
        }
        return user;    }
}

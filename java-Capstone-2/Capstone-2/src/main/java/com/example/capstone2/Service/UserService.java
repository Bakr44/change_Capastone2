package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiExeption;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user){
        User user1=userRepository.findUserById(id);

        if (user1==null){
            throw new ApiExeption("ID Not Found");
        }
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
    }


    public void deleteUser(Integer id){
        User user1=userRepository.findUserById(id);
        if (user1==null){
            throw new ApiExeption("Id Not Found");
        }
        userRepository.delete(user1);
    }


    public List<User> getUsersByNameContaining(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }



    public List<User> getUsersByUsername(String username){
        return userRepository.findUserByUsername(username);
    }


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}

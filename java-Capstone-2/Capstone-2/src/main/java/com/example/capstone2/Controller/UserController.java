package com.example.capstone2.Controller;

import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user) {
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user) {
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

    @GetMapping("/search/{name}")
    public List<User> getUsersByNameContaining(@PathVariable String name) {
        return userService.getUsersByNameContaining(name);
    }

    @GetMapping("/search-username/{username}")
    public List<User> getUsersByUsername(@PathVariable String username) {
        return userService.getUsersByUsername(username);
    }

}

package com.example.capstone2.Controller;


import com.example.capstone2.DTO.CompanyDto;
import com.example.capstone2.DTO.StudentDto;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/student")
    public ResponseEntity register(@RequestBody @Valid StudentDto studentDto){

        authService.register(studentDto);
        return ResponseEntity.status(200).body("user registered");
    }

    @PostMapping("/register/company")
    public ResponseEntity registerC(@RequestBody @Valid CompanyDto companyDto){

        authService.registerC(companyDto);
        return ResponseEntity.status(200).body("Company registered");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout successfully");
    }

}

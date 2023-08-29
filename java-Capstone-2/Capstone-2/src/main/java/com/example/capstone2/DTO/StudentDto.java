package com.example.capstone2.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @NotEmpty(message = "username cant be empty")
    @Column(unique = true,columnDefinition = "varchar(20) not null")
    private String username;

    @NotEmpty(message = "password cant be empty")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",message = "Password must be at least 8 characters and contain both letters and numbers")
    private  String password;

    @NotNull(message = "email cant be empty")
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "name cant be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;


}

package com.example.capstone2.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null", unique = true)
    private String name;

    @NotEmpty
    @Column(columnDefinition = "varchar(1028) not null")
    private String description;

    @NotNull
    @Positive
    private Integer numberOfEmployees;

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


}

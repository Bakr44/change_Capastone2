package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


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

    @NotEmpty(message = "should not be empty")
    @Pattern(regexp = "^(STUDENT|COMPANY)$", message = "Role must be 'STUDENT' or 'COMPANY'")
    private String role;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Student student;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Company company;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

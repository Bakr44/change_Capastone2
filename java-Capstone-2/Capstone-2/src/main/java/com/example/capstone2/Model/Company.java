package com.example.capstone2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null", unique = true)
    private String name;

    @NotEmpty
    @Column(columnDefinition = "varchar(1028) not null")
    private String description;

    @NotNull
    @Positive
    private Integer numberOfEmployees;


    @OneToOne(cascade = CascadeType.DETACH)
    @MapsId
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<JobListing> jobListings;
}

package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null",unique = true)
    private String companyName;

    @NotEmpty(message = "Title can't be Empty")
    @Column(columnDefinition = "varchar(40) not null")
    private String title;

    @NotEmpty
    @Column(columnDefinition = "varchar(1028) not null")
    private String description;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null check(jobtype='Full-time' or jobtype='Part-time' or jobtype='Remote')")
    private String jobtype;

    @NotNull
    private Boolean isActive;

    private Integer ApplicationCount=0;


    @CreationTimestamp
    private Date createdAt;
}

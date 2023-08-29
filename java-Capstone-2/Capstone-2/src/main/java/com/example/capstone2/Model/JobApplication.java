package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "username cant be empty")
    private String username;

    @NotNull(message = "jobListingId cant be empty")
    private Integer jobListingId;


    @NotNull(message = "applicantId cant be empty")
    private Integer applicantId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(20) not null check(status='ACCEPTED' or status='REJECTED' or status='PENDING') default 'PENDING'")
    private ApplicationStatus status= ApplicationStatus.valueOf("PENDING");


}

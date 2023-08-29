package com.example.capstone2.Repository;

import com.example.capstone2.Model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Integer> {


    @Query("select a from JobApplication a where a.id=?1")
    JobApplication findJobApplicationById(Integer id);
}

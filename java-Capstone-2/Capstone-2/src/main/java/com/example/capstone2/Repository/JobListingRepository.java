package com.example.capstone2.Repository;

import com.example.capstone2.Model.Company;
import com.example.capstone2.Model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobListingRepository extends JpaRepository<JobListing,Integer> {

    JobListing findJobListingById(Integer id);

    @Query("delete from JobListing j where j.id = :id")
    void deleteById(Integer id);


    @Query("select j from  JobListing j where j.jobtype=?1")
    List<JobListing> findJobListingByJobType(String jobType);


    @Query("select j from JobListing j where j.isActive=true")
    List<JobListing> findByIsActiveTrue();

//    @Query("SELECT jl FROM JobListing jl WHERE jl.companyName = ?1 ORDER BY jl.createdAt DESC")
//    List<JobListing> findLatestJobListingsByCompany( Company company);

}

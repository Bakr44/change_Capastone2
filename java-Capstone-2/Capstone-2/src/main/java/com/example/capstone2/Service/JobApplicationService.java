package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiExeption;
import com.example.capstone2.Model.JobApplication;
import com.example.capstone2.Repository.JobApplicationRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobListingService jobListingService;
    private final UserService userService;
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public JobApplication addJobApplication(JobApplication jobApplication) {
        // Check if the JobListing exists
        if (!jobListingService.existsJobListingById(jobApplication.getJobListingId())) {
            throw new ApiExeption("JobListing with ID " + jobApplication.getJobListingId() + " not found.");
        }
        // Check if the Job Listing is active
        if (!jobListingService.isJobListingActive(jobApplication.getJobListingId())) {
            throw new ApiExeption("Job Listing is not available");
        }
        // Check if the provided username exists in User
        if (!userService.existsByUsername(jobApplication.getUsername())) {
            throw new ApiExeption("User with username " + jobApplication.getUsername() + " not found.");
        }
        jobListingService.incrementApplicationCount(jobApplication.getJobListingId());
        return jobApplicationRepository.save(jobApplication);
    }

    public void updateJobApplication(Integer id,JobApplication jobApplication){
        JobApplication jobApplication1=jobApplicationRepository.findJobApplicationById(id);
        if (jobApplication1==null){
            throw new ApiExeption("Id not Found");
        }
        jobApplication1.setApplicantId(jobApplication.getApplicantId());
        jobApplication1.setJobListingId(jobApplication.getJobListingId());
        jobApplication1.setStatus(jobApplication.getStatus());
        jobApplicationRepository.save(jobApplication1);
    }

    public void deleteJobApplication(Integer id) {
        jobApplicationRepository.deleteById(id);
    }

    public JobApplication getJobApplicationById(Integer id) {
        return jobApplicationRepository.findJobApplicationById(id);
    }





}

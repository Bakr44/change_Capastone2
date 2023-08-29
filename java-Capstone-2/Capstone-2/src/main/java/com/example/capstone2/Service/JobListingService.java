package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiExeption;
import com.example.capstone2.Model.Company;
import com.example.capstone2.Model.JobListing;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.AuthRepository;
import com.example.capstone2.Repository.JobListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobListingService {
    private final JobListingRepository jobListingRepository;
    private final AuthRepository authRepository;

    public List<JobListing> getAllJobListings() {
        return jobListingRepository.findAll();
    }

    public void addJobListing(Integer userId,JobListing jobListing) {
        // Check if the company name already exists
        User user = authRepository.findUserById(userId);

        if (user == null) {
            throw new ApiExeption("User not found");
        }
        jobListing.setCompany(user.getCompany());
        jobListingRepository.save(jobListing);
    }

    public void updateJobListing(Integer id, JobListing jobListing) {
        JobListing jobListing1 = jobListingRepository.findJobListingById(id);

        if (jobListing1 == null) {
            throw new ApiExeption("ID Not Found");
        }
//        jobListing1.setCompanyName(jobListing.getCompanyName());
        jobListing1.setTitle(jobListing.getTitle());
        jobListing1.setDescription(jobListing.getDescription());
        jobListing1.setJobtype(jobListing.getJobtype());
        jobListing1.setJobtype(jobListing.getJobtype());
        jobListing1.setIsActive(jobListing.getIsActive());
        jobListingRepository.save(jobListing1);
    }


//    public void deleteJobListing(Integer id){
//        JobListing jobListing1=jobListingRepository.findJobListingById(id);
//        if (jobListing1==null){
//            throw new ApiExeption("Id Not Found");
//        }
//        jobListingRepository.delete(jobListing1);
//    }

    public void deleteJobListingById(Integer id) {
        jobListingRepository.deleteById(id);
    }

    public JobListing getJobListingById(Integer id) {
        return jobListingRepository.findJobListingById(id);
    }

    public List<JobListing> getJobListingsByJobType(String jobType) {
        return jobListingRepository.findJobListingByJobType(jobType);
    }


    public void incrementApplicationCount(Integer jobListingId) {
        JobListing jobListing = jobListingRepository.findJobListingById(jobListingId);
        if (jobListing != null) {
            jobListing.setApplicationCount(jobListing.getApplicationCount() + 1);
            jobListingRepository.save(jobListing);
        }
    }

    public List<JobListing> getActiveJobListings() {
        return jobListingRepository.findByIsActiveTrue();
    }

    public Boolean existsJobListingById(Integer id) {
        return jobListingRepository.existsById(id);
    }

    public boolean isJobListingActive(Integer jobListingId) {
        JobListing jobListing = jobListingRepository.findById(jobListingId)
                .orElseThrow(() -> new ApiExeption("Job Listing not found"));

        return jobListing.getIsActive();
    }

//    public List<JobListing> getLatestJobListings(Integer companyId) {
//        Company company = companyService.getCompanyById(companyId);
//        if (company == null) {
//            throw new ApiExeption("Company not found");
//        }
//
//        List<JobListing> latestJobListings = jobListingRepository.findLatestJobListingsByCompany(company);
//        return latestJobListings;
//    }

}
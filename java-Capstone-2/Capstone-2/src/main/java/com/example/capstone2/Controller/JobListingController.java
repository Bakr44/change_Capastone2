package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.JobListing;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.JobListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-listing")
public class JobListingController {

    private final JobListingService jobListingService;

    @GetMapping("/get")
    public ResponseEntity getAllJobListings() {
        return ResponseEntity.status(200).body(jobListingService.getAllJobListings());
    }

    @PostMapping("/add")
    public ResponseEntity addJobListing(@AuthenticationPrincipal User user, @RequestBody @Valid JobListing jobListing) {
        jobListingService.addJobListing(user.getId(),jobListing);
        return ResponseEntity.status(200).body(new ApiResponse("Job listing added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobListing(
            @PathVariable Integer id,
            @RequestBody JobListing jobListing) {
        jobListingService.updateJobListing(id, jobListing);
        return ResponseEntity.status(200).body(new ApiResponse("Job listing updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobListing(@PathVariable Integer id) {
        jobListingService.deleteJobListingById(id);
        return ResponseEntity.status(200).body(new ApiResponse("Job listing deleted successfully"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity getJobListingById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(jobListingService.getJobListingById(id));
    }

    @GetMapping("/search-job-type")
    public ResponseEntity getJobListingsByJobType(@RequestParam String jobType) {

        return ResponseEntity.status(200).body(jobListingService.getJobListingsByJobType(jobType));
    }



    @GetMapping("/get-active")
    public ResponseEntity getActiveJobListings() {
        return ResponseEntity.status(200).body(jobListingService.getActiveJobListings());
    }
}

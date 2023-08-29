package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.JobApplication;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-applications")
public class JobApplicationController {


    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getAllJobApplications() {
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/add")
    public ResponseEntity addJobApplication( @RequestBody @Valid JobApplication jobApplication,@AuthenticationPrincipal User user) {
      jobApplicationService.addJobApplication(jobApplication,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Applied Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobApplication(
            @PathVariable Integer id,
            @RequestBody JobApplication jobApplication) {
        jobApplicationService.updateJobApplication(id, jobApplication);
        return ResponseEntity.status(200).body(new ApiResponse("Job application updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobApplication(@PathVariable Integer id) {
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.status(200).body(new ApiResponse("Job application deleted successfully"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<JobApplication> getJobApplicationById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(jobApplicationService.getJobApplicationById(id));
    }

}


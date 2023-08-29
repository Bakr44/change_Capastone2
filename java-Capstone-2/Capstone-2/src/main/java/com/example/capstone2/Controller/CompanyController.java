package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Company;
import com.example.capstone2.Model.JobListing;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/get")
    public ResponseEntity getAllCompanies() {
        return ResponseEntity.status(200).body(companyService.getAllCompanies());
    }

    @GetMapping("/search-id/{id}")
    public ResponseEntity getCompanyById(@PathVariable Integer id) {
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.status(200).body(company);
    }

    @PostMapping("/add")
    public ResponseEntity addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
        return ResponseEntity.status(200).body(new ApiResponse("Company added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCompany(@PathVariable Integer id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
        return ResponseEntity.status(200).body(new ApiResponse("Company updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(200).body(new ApiResponse("Company deleted successfully"));
    }

    @GetMapping("/search-name/{name}")
    public ResponseEntity getCompaniesByNameContaining(@PathVariable String name) {
        return ResponseEntity.status(200).body(companyService.getCompaniesByNameContaining(name));
    }

    @GetMapping("/search-description")
    public ResponseEntity getCompaniesByDescriptionContaining(@RequestParam String description) {
        return ResponseEntity.status(200).body(companyService.getCompaniesByDescriptionContaining(description));
    }

    @GetMapping("/check-name")
    public ResponseEntity isCompanyNameTaken(@RequestParam String name) {
        return ResponseEntity.status(200).body(companyService.isCompanyNameTaken(name));
    }

    @GetMapping("/employees-greater-than")
    public ResponseEntity getCompaniesWithNumberOfEmployeesGreaterThan(@RequestParam Integer num) {
        return ResponseEntity.status(200).body(companyService.getNumberOfEmployeesByCompany(num));
    }

    @GetMapping("/get-employees-count")
    public ResponseEntity getNumberOfEmployeesByCompany(@AuthenticationPrincipal User user, @RequestParam  String companyName) {
        return ResponseEntity.status(200).body(companyService.getNumberOfEmployeesByCompany(companyName));
    }

    @GetMapping("/sort-by-employees")
    public ResponseEntity getAllCompaniesSortedByNumberOfEmployees() {
        return ResponseEntity.status(200).body(companyService.getAllCompaniesSortedByNumberOfEmployees());
    }


}

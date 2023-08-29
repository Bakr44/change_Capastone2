package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiExeption;
import com.example.capstone2.Model.Company;
import com.example.capstone2.Model.JobListing;
import com.example.capstone2.Repository.CompanyRepository;
import com.example.capstone2.Repository.JobListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final JobListingRepository jobListingRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.findCompaniesById(id);
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public void updateCompany(Integer id, Company company) {
        Company company1 = companyRepository.findCompaniesById(id);
        if (company1==null) {
            throw new ApiExeption("ID Not Found");
        }
        company1.setName(company.getName());
        company1.setDescription(company.getDescription());
        company1.setNumberOfEmployees(company.getNumberOfEmployees());
        companyRepository.save(company1);
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    public List<Company> getCompaniesByNameContaining(String name) {
        return companyRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Company> getCompaniesByDescriptionContaining(String description) {
        return companyRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public String isCompanyNameTaken(String name) {
        if(companyRepository.existsByName(name)){
            return ("Company name exit");
        }
        return "Company name not exit";
    }

    public List<Company> getNumberOfEmployeesByCompany(Integer num){
        return companyRepository.findByNumberOfEmployeesGreaterThan(num);
    }

    public Integer getNumberOfEmployeesByCompany(String companyName) {
        return companyRepository.getNumberOfEmployeesByCompanyIgnoreCase(companyName);
    }


    public List<Company> getAllCompaniesSortedByNumberOfEmployees() {
        return companyRepository.findAllOrderByNumberOfEmployeesDesc();
    }

}

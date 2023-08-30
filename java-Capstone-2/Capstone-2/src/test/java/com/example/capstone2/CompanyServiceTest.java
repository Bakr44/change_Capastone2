package com.example.capstone2;

import com.example.capstone2.Model.ApplicationStatus;
import com.example.capstone2.Model.Company;
import com.example.capstone2.Model.JobApplication;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.AuthRepository;
import com.example.capstone2.Repository.CompanyRepository;
import com.example.capstone2.Repository.JobApplicationRepository;
import com.example.capstone2.Service.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    CompanyService companyService;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    AuthRepository authRepository;


    User user;
    Company company1;

    Company company2;

    List<Company> companies;

    @BeforeEach
    void setUp(){
        user = new User(null,"bakr24","bakrSAlem1234@","mmmm" +
                "@gmail.com","STUDENT",null,null);

        company1=new Company(null,"company3","this jhfjghahoashiosjiojk",109,user,null);
        company2=new Company(null,"company4","this jhfjghahopoashiosjiojk",1090,user,null);

        companies=new ArrayList<>();
        companies.add(company1);
        companies.add(company2);

    }


    @Test
    public void getCompanyTest(){

        when(companyRepository.findAll()).thenReturn(companies);

        List<Company> companyList=companyService.getAllCompanies();
        Assertions.assertEquals(companyList,companies);

        verify(companyRepository,times(1)).findAll();

    }

    @Test
    public void updateCompanyTest(){

        when(companyRepository.findCompaniesById(company1.getId())).thenReturn(company1);

        companyService.updateCompany(company1.getId(),company2);

        verify(companyRepository,times(1)).findCompaniesById(company1.getId());
        verify(companyRepository,times(1)).save(company1);

    }



    @Mock
    JobApplicationRepository jobApplicationRepository;

    @Test
    public void testAcceptApplicationStatus() {
        JobApplication jobApplication1 = new JobApplication();
        jobApplication1.setStatus(ApplicationStatus.PENDING);
        when(jobApplicationRepository.findJobApplicationById(1)).thenReturn(jobApplication1);

        CompanyService companyService1 = new CompanyService(companyRepository,jobApplicationRepository);
        companyService1.AcceptApplicationStatus(1);

        Assertions.assertEquals(ApplicationStatus.ACCEPTED, jobApplication1.getStatus());
        verify(jobApplicationRepository,times(1)).save(jobApplication1);
    }

    @Test
    public void testRejectedApplicationStatus() {
        JobApplication jobApplication1 = new JobApplication();
        jobApplication1.setStatus(ApplicationStatus.PENDING);
        when(jobApplicationRepository.findJobApplicationById(1)).thenReturn(jobApplication1);

        CompanyService companyService1 = new CompanyService(companyRepository,jobApplicationRepository);
        companyService1.RejectedApplicationStatus(1);

        Assertions.assertEquals(ApplicationStatus.REJECTED, jobApplication1.getStatus());
    }

    @Test
    public void getCompanyByIdTest(){
        when(companyRepository.findCompaniesById(company1.getId())).thenReturn(company1);

        Company company=companyService.getCompanyById(company1.getId());
        Assertions.assertEquals(company,company1);

        verify(companyRepository,times(1)).findCompaniesById(company1.getId());

    }


}

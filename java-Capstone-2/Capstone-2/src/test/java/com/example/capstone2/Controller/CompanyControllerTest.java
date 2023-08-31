package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Company;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CompanyController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class CompanyControllerTest {

    @MockBean
    CompanyService companyService;

    @Autowired
    MockMvc mockMvc;

    Company company1,company2;

    User user;

    ApiResponse apiResponse;

    List<Company> companies,companyList;

    @BeforeEach
    void setUp(){
        user = new User(1,"bakr24","bakrSAlem1234@","mmmm" +
                "@gmail.com","STUDENT",null,null);

        company1=new Company(1,"company3","this jhfjghahoashiosjiojk",109,user,null);
        company2=new Company(2,"company4","this jhfjghahopoashiosjiojk",1090,user,null);

        companies= Arrays.asList(company1);
        companyList=Arrays.asList(company2);

    }


    @Test
    public void GetAllCompany() throws Exception {
        Mockito.when(companyService.getAllCompanies()).thenReturn(companies);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company/get"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("company3"));
    }

    @Test
    public void testAddCompany() throws  Exception {
        mockMvc.perform(post("/api/v1/company/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(company1)))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateCompany() throws Exception{
        mockMvc.perform(put("/api/v1/company/update/{id}",company1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(company1)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAcceptCompany() throws  Exception {
        mockMvc.perform(post("/api/v1/company/accept/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    public void testDeleteCompany() throws Exception{
        mockMvc.perform(delete("/api/v1/company/delete/{id}",company1.getId()))
                .andExpect(status().isOk());

    }



}
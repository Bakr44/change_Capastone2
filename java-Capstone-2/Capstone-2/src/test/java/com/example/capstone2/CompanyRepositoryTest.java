package com.example.capstone2;

import com.example.capstone2.Model.Company;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.CompanyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;


    User user1;

    Company company;
    Company company1;

    Company company2;

    List<Company> companyList;

    @BeforeEach
    void setUp() {
        user1 = new User(null,"bakr24","bakrSAlem1234@","mmmm" +
                "@gmail.com","STUDENT",null,null);

        company1=new Company(null,"company3","this jhfjghahoashiosjiojk",109,user1,null);
        company2=new Company(null,"company4","this jhfjghahopoashiosjiojk",1090,user1,null);

    }

    @Test
    public void findCompaniesByIdTest(){
        companyRepository.save(company1);

        company=companyRepository.findCompaniesById(company1.getId());

        Assertions.assertThat(company.getUser().getId()).isEqualTo(user1.getId());
    }

    @Test
    public void findByNameContainingIgnoreCaseTest(){
        companyRepository.save(company1);

        companyList=companyRepository.findByNameContainingIgnoreCase(company1.getName());

        Assertions.assertThat(companyList.get(0).getUser().getId()).isEqualTo(user1.getId());
    }

    @Test
    public void findByDescriptionContainingIgnoreCaseTest(){
        companyRepository.save(company1);

        Integer com=companyRepository.getNumberOfEmployeesByCompanyIgnoreCase(company1.getName());

        Assertions.assertThat(com).isEqualTo(company1.getNumberOfEmployees());
    }

}

package com.example.capstone2.Service;

import com.example.capstone2.DTO.CompanyDto;
import com.example.capstone2.DTO.StudentDto;
import com.example.capstone2.Model.Company;
import com.example.capstone2.Model.Student;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.AuthRepository;
import com.example.capstone2.Repository.CompanyRepository;
import com.example.capstone2.Repository.StudentRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService  {


    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;

    public void register(StudentDto studentDto){
        User user = new User(null,studentDto.getUsername(),studentDto.getPassword(),studentDto.getEmail(),"STUDENT",null,null);
        Student student=new Student(null,studentDto.getName(),user,null);

        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        studentRepository.save(student);
    }

    public void registerC(CompanyDto companyDto){
        User user = new User(null,companyDto.getUsername(),companyDto.getPassword(), companyDto.getEmail(),"COMPANY",null,null);
        Company company =new Company(null, companyDto.getName(), companyDto.getDescription(), companyDto.getNumberOfEmployees(),user,null);

        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        companyRepository.save(company);
    }
}

package com.example.capstone2.Repository;

import com.example.capstone2.Model.Company;
import com.example.capstone2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {


    List<Company> findByUser(User user);


    @Query("select c from Company c where c.id=?1")
    Company findCompaniesById(Integer id);


    @Query("delete from Company c where c.id = :id")
    void deleteById(Integer id);


    @Query("select c from Company c where lower(c.name) like %:name%")
    List<Company> findByNameContainingIgnoreCase(String name);

    @Query("select c from Company  c where c.description like %:description%")
    List<Company> findByDescriptionContainingIgnoreCase(String description);


    @Query("select count(c) > 0 from Company c where lower(c.name) = lower(:name) ")
    boolean existsByName(String name);


    @Query("select c from Company c where c.numberOfEmployees > :minEmployees")
    List<Company> findByNumberOfEmployeesGreaterThan(int minEmployees);


    @Query("select c.numberOfEmployees from Company c where lower(c.name) = lower(:name) ")
    Integer getNumberOfEmployeesByCompanyIgnoreCase(String name);

    @Query("select c.numberOfEmployees from Company c where  c.user.company.name=?1")
    Integer getNumberOfEmployeesByCompany(User user);


    @Query("select c from Company c order by  c.numberOfEmployees DESC")
    List<Company> findAllOrderByNumberOfEmployeesDesc();

    @Query("select case when count(c) > 0 then true else false end from Company c where c.name = ?1")
    boolean existsByCompanyName(String companyName);

}



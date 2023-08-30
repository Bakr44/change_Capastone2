package com.example.capstone2;


import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.AuthRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthRepositoryTest {
    @Autowired
    AuthRepository authRepository;

    User user;

    User user1;


    @BeforeEach
    void setUp() {
        user1 = new User(null,"bakr24","bakrSAlem1234@","mmmm" +
                "@gmail.com","STUDENT",null,null);

    }

    @Test
    public void findUserByUsernameTest(){
        authRepository.save(user1);

        user=authRepository.findUserByUsername(user1.getUsername());
        Assertions.assertThat(user).isEqualTo(user1);

    }

    @Test
    public void findUserByIdTest(){
        authRepository.save(user1);

        user=authRepository.findUserById(user1.getId());
        Assertions.assertThat(user).isEqualTo(user1);

    }

}

package com.example.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder pe= new BCryptPasswordEncoder();
        String encode = pe.encode("123");
        System.out.println(encode);
        boolean matches = pe.matches("123", encode);
        System.out.println(matches);
    }

}

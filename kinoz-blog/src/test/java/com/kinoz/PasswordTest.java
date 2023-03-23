package com.kinoz;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
public class PasswordTest {


    @Test
    public void TestPass(){
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //生成MD5密码
        String encode = bCryptPasswordEncoder.encode("12345");
        System.out.println(encode);

        //名文和MD5进行校验
        boolean tof = bCryptPasswordEncoder.
                matches("Hao521005","$2a$10$8pNrIBLY793Bl2.oP3.iJOjBPwKM8ppiYG2HwZPglVHRGOBDoU4C.");
        System.out.println(tof);
    }
}

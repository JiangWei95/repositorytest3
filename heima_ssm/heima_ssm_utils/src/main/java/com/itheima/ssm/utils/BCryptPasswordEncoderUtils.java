package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public  static String encodePassword(String password){

        return bCryptPasswordEncoder.encode(password);


    }

    public static void main(String[] args) {
        String password = "123";
        //$2a$10$ux3HLADvR9zFsGJdEPcooe6vsGQ4GzlMyzEUSNB55cU38vgSjTRUa
        String s = encodePassword(password);

        System.out.println(s);
    }

}

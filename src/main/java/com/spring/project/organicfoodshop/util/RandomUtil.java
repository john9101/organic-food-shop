package com.spring.project.organicfoodshop.util;

import java.security.SecureRandom;

public class RandomUtil {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String generate0TP() {
        int otp = 1000 + SECURE_RANDOM.nextInt(9000);
        return String.valueOf(otp);
    }

}

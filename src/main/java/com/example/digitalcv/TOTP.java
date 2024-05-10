package com.example.digitalcv;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Random;


public class TOTP {
    public static final String SECRET_KEY = "my_secret_key";
    public static String generateTOTP(String key) {
        try {
            long time = System.currentTimeMillis() / 30000; //
            byte[] keyBytes = key.getBytes();
            byte[] msg = new byte[8];
            for (int i = 7; i >= 0; i--) {
                msg[i] = (byte) time;
                time >>>= 8;
            }


            Mac hmac = Mac.getInstance("HmacSHA1");
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "RAW");
            hmac.init(keySpec);
            byte[] hash = hmac.doFinal(msg);


            int offset = hash[hash.length - 1] & 0xf;
            int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) | ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);
            int otp = binary % 1000000;
            return String.format("%06d", otp);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static void main(String[] args){
        final String SECRET_KEY = "my_secret_key";
        System.out.println(generateTOTP(SECRET_KEY));
    }
}

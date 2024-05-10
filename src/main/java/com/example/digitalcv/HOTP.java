package com.example.digitalcv;



// OTP Algorithm
// OATH Initiative
// HOTP one time password algorithm


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * This class contains static methods that are used to calculate the
 * One-Time Password (OTP) using
 * JCE to provide the HMAC-SHA-1.
 */

public class HOTP {
    private HOTP(){}

    // calculate the check-sum digits

    private static final int [] doubleDigits = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9 };

/**
 * Calculates the checksum using the credit card algorithm.
 * This algorithm has the advantage that it detects any single
 * mistyped digit and any single transposition of
 * adjacent digits.
 */
    public static int calcChecksum (long num, int digits){
        boolean doubleDigit = true;
        int total = 0;
        while (0 < digits--){
            int digit = (int) (num % 10); // modulus för räkna 0 till 9
            num /= 10;
                if(doubleDigit){
                    digit = doubleDigits[digit];
                }
                total += digit;
                doubleDigit = !doubleDigit;
        }
        int result = total % 10;
        if(result > 0){
            result = 10 - result;
        }
        return result;
    }

/*
 * This method uses the JCE to provide the HMAC-SHA-1
 * algorithm.
 * HMAC computes a Hashed Message Authentication Code and
 * in this case SHA1 is the hash algorithm used.
 */

    public static byte[] hmac_sha1(byte[] keybytes, byte[] text)throws NoSuchAlgorithmException, InvalidKeyException{
        try {
            Mac hmacSha1;
            try{
                hmacSha1 = Mac.getInstance("HmacSHA1");
            }catch (NoSuchAlgorithmException e){
                hmacSha1 = Mac.getInstance("HMAC-SHA-256");
            }
            SecretKeySpec macKey = new SecretKeySpec(keybytes, "RAW");
            hmacSha1.init(macKey);
            return hmacSha1.doFinal(text);
        }catch (GeneralSecurityException gse){
            throw new UndeclaredThrowableException(gse);
        }
    }

    private static final int[] DIGITS_POWER = {1, 10,100,1000,10000,100000,1000000,10000000,100000000};

    static public String generateOTP(byte[] secret, long movingFactor, int codeDigits, boolean addchecksum, int truncationOffset)
        throws NoSuchAlgorithmException, InvalidKeyException{
        // put movingFactor value into text byte array
        String result = null;
        int digits = addchecksum ? (codeDigits + 1): codeDigits;
        byte[] text = new byte[8];
        for(int i = text.length - 1; i >= 0; i--){
            text[i] = (byte) (movingFactor & 0xff);
            movingFactor >>= 8;
        }
        //compute hmac hash
        byte[] hash = hmac_sha1(secret, text);

        //put selectrion bytes into result int
        int offset = hash[hash.length - 1] & 0xf;
        if(( 0 <=truncationOffset)&& (truncationOffset<(hash.length-4))){
            offset = truncationOffset;
        }
        int binary =
                ((hash[offset] & 0x7f) << 24)
                        | ((hash[offset + 1] & 0xff) << 16)
                        | ((hash[offset + 2] & 0xff) << 8)
                        | (hash[offset + 3] & 0xff);
        int otp = binary & DIGITS_POWER[codeDigits];
        if(addchecksum){
            otp = (otp * 10) + calcChecksum(otp, codeDigits);
        }
        result = Integer.toString(otp);
        while (result.length() < digits){
            result = "0" + result;
        }
        return result;
    }


    public static void main(String[] args) {
        byte[] secret = "Dinkey".getBytes();
        long movingFactor = 1234567890L;
        int codeDigits = 6;
        boolean addChecksum = true;
        int truncationOffset = 0;
        try {
            System.out.println(generateOTP(secret, movingFactor, codeDigits, addChecksum, truncationOffset));

            for ( movingFactor = 1234567890L; movingFactor <= 1234567900L; movingFactor++) {

                    String otp = generateOTP(secret, movingFactor, codeDigits, addChecksum, truncationOffset);
                    System.out.println("OTP for movingFactor " + movingFactor + ": " + otp);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        /*try {
            String OTP = HOTP.generateOTP(secret, movingFactor, codeDigits, addChecksum, truncationOffset);
            System.out.println("Password: " + OTP);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Write Password");
            String inputPassword = scanner.nextLine();


            if(OTP.equals(inputPassword)){
                System.out.println("I like you");
            }else{
                System.out.println("fel password");
            }
        }catch (Exception e){
            System.out.println("Ett fel :" + e.getMessage());
        }*/
    }
 }

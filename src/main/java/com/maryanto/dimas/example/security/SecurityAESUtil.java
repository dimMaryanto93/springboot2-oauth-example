package com.maryanto.dimas.example.security;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class SecurityAESUtil {

    private static final String algorithm = "AES/ECB/PKCS5Padding";
    private static final String sha1 = "SHA-1";

    public static SecretKey getKeyFromPassword(String salt) {
        try {
            MessageDigest sha = MessageDigest.getInstance(sha1);
            byte[] key = sha.digest(salt.getBytes());
            key = Arrays.copyOf(key, 16);
            return new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException nae) {
            log.error("can't generate/handle secret key", nae);
        }
        return null;
    }

    public static String encrypt(String input, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherText = cipher.doFinal(input.getBytes());
            return Base64.getEncoder()
                    .encodeToString(cipherText);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException ex) {
            log.error("can't encrypt text", ex);
        }
        return null;
    }

    public static String decrypt(String cipherText, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(cipherText));
            return new String(plainText);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException ex) {
            log.error("can't decrypt text", ex);
        }
        return null;
    }
}

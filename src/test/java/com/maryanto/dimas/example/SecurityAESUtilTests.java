package com.maryanto.dimas.example;

import com.maryanto.dimas.example.security.SecurityAESUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

@Slf4j
public class SecurityAESUtilTests {

    String input = "dimasm93";

    @Test
    public void testGivenString_whenEncrypt_thenSuccess() {
        SecretKey key = SecurityAESUtil.getKeyFromPassword("test123456789012");
        String cipherText = SecurityAESUtil.encrypt(input, key);
        String plainText = SecurityAESUtil.decrypt(cipherText, key);
        log.info("encrypt: {}", cipherText);
        Assertions.assertEquals(input, plainText);
    }

    @Test
    public void testGivenCipherText_whenDecrypt_thenSuccess() {
        SecretKey key = SecurityAESUtil.getKeyFromPassword("test123456789012");
        String cipherText = "dN6bshzDRVvK7/pjqDAYEw==";
        String plainText = SecurityAESUtil.decrypt(cipherText, key);
        Assertions.assertEquals(input, plainText);
    }

}

package com.springProject.Bookora;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springProject.Bookora.ServiceDetails.JwtService;

@SpringBootTest
class JwtAuthenticationTest {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void jwtServiceShouldGenerateAndValidateToken() {
        String token = jwtService.generateToken("demo-user");

        assertNotNull(token);
        assertTrue(token.length() > 20);
        assertTrue(jwtService.validateToken(token));
        assertTrue("demo-user".equals(jwtService.extractUsername(token)));

        String encoded = passwordEncoder.encode("123456");
        assertTrue(passwordEncoder.matches("123456", encoded));
    }
}

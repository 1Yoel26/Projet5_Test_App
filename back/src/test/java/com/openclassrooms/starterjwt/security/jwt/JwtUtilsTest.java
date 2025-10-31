package com.openclassrooms.starterjwt.security.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;

@SpringBootTest
public class JwtUtilsTest {

	@Autowired
    private JwtUtils jwtUtils;

    @Test
    void testGenerateAndValidateToken() {
    	
    	 UserDetailsImpl userDetails = new UserDetailsImpl(
    		        1L, "yoel", "Yoel", "Illouz", false, "test1234"
    		    );
    	
    	Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    	 
        String token = jwtUtils.generateJwtToken(authentication);

        assertNotNull(token);
        assertTrue(jwtUtils.validateJwtToken(token));
        assertEquals("yoel", jwtUtils.getUserNameFromJwtToken(token));
    }

    @Test
    void testInvalidToken() {
        assertFalse(jwtUtils.validateJwtToken("bad.token.value"));
    }
}

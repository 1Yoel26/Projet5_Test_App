package com.openclassrooms.starterjwt.security.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserDetailImplTest {

	@Test
	void testGetAdmin() {
	    UserDetailsImpl user = new UserDetailsImpl(1L, "yoel", "Yoel", "Illouz", true, "pass123");
	    assertTrue(user.getAdmin());

	    UserDetailsImpl user2 = new UserDetailsImpl(2L, "toto", "Toto", "Tata", false, "pass123");
	    assertFalse(user2.getAdmin());
	}
	
	
	@Test
	void testEquals() {
	    UserDetailsImpl user1 = new UserDetailsImpl(1L, "yoel", "Yoel", "Illouz", false, "pass123");
	    UserDetailsImpl user2 = new UserDetailsImpl(1L, "yoel", "Yoel", "Illouz", false, "pass123");
	    UserDetailsImpl user3 = new UserDetailsImpl(2L, "yoel", "Yoel", "Illouz", false, "pass123");

	    // 1️ même objet
	    assertTrue(user1.equals(user1));

	    // 2️ null
	    assertFalse(user1.equals(null));

	    // 3️ type différent
	    assertFalse(user1.equals("not a user"));

	    // 4️ id différent
	    assertFalse(user1.equals(user3));

	    // 5️ id identique
	    assertTrue(user1.equals(user2));
	}
	
	
	
	@Test
	void testUserDetailsImplBuilder() {
	    // On utilise le builder pour créer un utilisateur
	    UserDetailsImpl.UserDetailsImplBuilder builder = UserDetailsImpl.builder()
	            .id(1L)
	            .username("yoel")
	            .firstName("Yoel")
	            .lastName("Illouz")
	            .password("pass123")
	            .admin(true);

	    // Test de admin(Boolean)
	    UserDetailsImpl user = builder.build();
	    assertTrue(user.getAdmin());
	    assertEquals("yoel", user.getUsername());

	    // Test de toString()
	    String builderString = builder.toString();
	    assertNotNull(builderString);
	    assertTrue(builderString.contains("yoel"));
	    assertTrue(builderString.contains("true")); 
	}



}

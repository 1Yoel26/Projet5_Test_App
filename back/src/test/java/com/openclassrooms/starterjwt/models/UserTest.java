package com.openclassrooms.starterjwt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class UserTest {

	 @Test
	 void testAllArgsConstructorAndGetters() {
	        LocalDateTime now = LocalDateTime.now();

	        User user = new User(
	                1L,
	                "test@email.com",
	                "Doe",
	                "John",
	                "password123",
	                true,
	                now,
	                now
	        );

	        assertEquals(1L, user.getId());
	        assertEquals("test@email.com", user.getEmail());
	        assertEquals("Doe", user.getLastName());
	        assertEquals("John", user.getFirstName());
	        assertEquals("password123", user.getPassword());
	        assertTrue(user.isAdmin());
	        assertEquals(now, user.getCreatedAt());
	        assertEquals(now, user.getUpdatedAt());
	        
	        
	    }
	 
	 	@Test
	 	void testSetter() {
	 		User user = new User();
	 	    assertSame(user, user.setEmail("same@email.com"));
	 	    assertSame(user, user.setFirstName("John"));
	 	    assertSame(user, user.setLastName("Doe"));
	 	    assertSame(user, user.setPassword("pass"));
	 	}

	    @Test
	    void testNoArgsConstructorAndSetters() {
	        User user = new User();
	        user.setId(2L);
	        user.setEmail("new@email.com");
	        user.setLastName("Smith");
	        user.setFirstName("Alice");
	        user.setPassword("abc123");
	        user.setAdmin(false);

	        assertEquals(2L, user.getId());
	        assertEquals("new@email.com", user.getEmail());
	        assertEquals("Smith", user.getLastName());
	        assertEquals("Alice", user.getFirstName());
	        assertEquals("abc123", user.getPassword());
	        assertFalse(user.isAdmin());
	    }

	    @Test
	    void testRequiredArgsConstructor() {
	        User user = new User("req@email.com", "Roe", "Jane", "reqPass", true);
	        assertEquals("req@email.com", user.getEmail());
	        assertEquals("Roe", user.getLastName());
	        assertEquals("Jane", user.getFirstName());
	        assertEquals("reqPass", user.getPassword());
	        assertTrue(user.isAdmin());
	    }

	    @Test
	    void testBuilder() {
	        LocalDateTime created = LocalDateTime.now();
	        LocalDateTime updated = created.plusDays(1);

	        User user = User.builder()
	                .id(3L)
	                .email("builder@email.com")
	                .lastName("BuilderLast")
	                .firstName("BuilderFirst")
	                .password("builderPass")
	                .admin(true)
	                .createdAt(created)
	                .updatedAt(updated)
	                .build();

	        assertEquals("builder@email.com", user.getEmail());
	        assertEquals("BuilderLast", user.getLastName());
	        assertEquals("BuilderFirst", user.getFirstName());
	        assertTrue(user.isAdmin());
	        assertEquals(created, user.getCreatedAt());
	        assertEquals(updated, user.getUpdatedAt());
	    }

	    @Test
	    void testEqualsAndHashCode() {
	        User user1 = User.builder()
	                .id(10L)
	                .email("same@email.com")
	                .lastName("Doe")
	                .firstName("John")
	                .password("p")
	                .admin(false)
	                .build();

	        User user2 = User.builder()
	                .id(10L)
	                .email("other@email.com")
	                .lastName("Smith")
	                .firstName("Alice")
	                .password("x")
	                .admin(true)
	                .build();

	        User user3 = User.builder()
	                .id(11L)
	                .email("diff@email.com")
	                .lastName("Diff")
	                .firstName("Person")
	                .password("diff")
	                .admin(false)
	                .build();

	        assertEquals(user1, user2);  // même id → equals doit être vrai
	        assertNotEquals(user1, user3); // id différent → false
	        assertEquals(user1.hashCode(), user2.hashCode());
	    }

	    @Test
	    void testToString() {
	        User user = new User("string@email.com", "Doe", "John", "pass", true);
	        String str = user.toString();
	        assertNotNull(str);
	        assertTrue(str.contains("string@email.com"));
	        assertTrue(str.contains("John"));
	    }
}

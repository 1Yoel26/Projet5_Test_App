package com.openclassrooms.starterjwt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SessionTest {

	@Test
    void testAllArgsConstructorAndGetters() {
        LocalDateTime created = LocalDateTime.now().minusDays(1);
        LocalDateTime updated = LocalDateTime.now();
        Date sessionDate = new Date();

        Teacher teacher = new Teacher(1L, "Dupont", "Jean", created, updated);
        List<User> users = new ArrayList();
        User user1 = new User(1L, "test@test.fr", "Illouz", "Yoel", "pwd123", true, created, updated);
        users.add(user1);

        Session session = new Session();
        session.setId(10L)
               .setName("Yoga session")
               .setDate(sessionDate)
               .setDescription("Cours de yoga pour débutants")
               .setTeacher(teacher)
               .setUsers(users)
               .setCreatedAt(created)
               .setUpdatedAt(updated);

        assertEquals(10L, session.getId());
        assertEquals("Yoga session", session.getName());
        assertEquals(sessionDate, session.getDate());
        assertEquals("Cours de yoga pour débutants", session.getDescription());
        assertEquals(teacher, session.getTeacher());
        assertEquals(users, session.getUsers());
        assertEquals(created, session.getCreatedAt());
        assertEquals(updated, session.getUpdatedAt());
    }

    @Test
    void testSettersChainingAndGetters() {
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime updated = LocalDateTime.now();
        Date sessionDate = new Date();

        Teacher teacher = new Teacher();
        List<User> users = new ArrayList<>();

        Session session = new Session();

        assertSame(session, session.setId(5L));
        assertSame(session, session.setName("Pilates session"));
        assertSame(session, session.setDate(sessionDate));
        assertSame(session, session.setDescription("Cours avancé"));
        assertSame(session, session.setTeacher(teacher));
        assertSame(session, session.setUsers(users));
        assertSame(session, session.setCreatedAt(created));
        assertSame(session, session.setUpdatedAt(updated));

        assertEquals(5L, session.getId());
        assertEquals("Pilates session", session.getName());
        assertEquals(sessionDate, session.getDate());
        assertEquals("Cours avancé", session.getDescription());
        assertEquals(teacher, session.getTeacher());
        assertEquals(users, session.getUsers());
        assertEquals(created, session.getCreatedAt());
        assertEquals(updated, session.getUpdatedAt());
    }

    @Test
    void testBuilder() {
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime updated = LocalDateTime.now();
        Date sessionDate = new Date();

        Teacher teacher = new Teacher();
        List<User> users = new ArrayList<>();

        Session session = Session.builder()
                .id(1L)
                .name("Meditation session")
                .date(sessionDate)
                .description("Séance de méditation")
                .teacher(teacher)
                .users(users)
                .createdAt(created)
                .updatedAt(updated)
                .build();

        assertEquals(1L, session.getId());
        assertEquals("Meditation session", session.getName());
        assertEquals(sessionDate, session.getDate());
        assertEquals("Séance de méditation", session.getDescription());
        assertEquals(teacher, session.getTeacher());
        assertEquals(users, session.getUsers());
        assertEquals(created, session.getCreatedAt());
        assertEquals(updated, session.getUpdatedAt());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();
        Date date = new Date();

        Session s1 = new Session();
        s1.setId(1L);

        Session s2 = new Session();
        s2.setId(1L);

        Session s3 = new Session();
        s3.setId(2L);

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(s1, s3);
        assertNotEquals(s1.hashCode(), s3.hashCode());
        assertNotEquals(s1, null);
        assertNotEquals(s1, "autre type");
    }

    @Test
    void testToStringNotNull() {
        Session session = new Session();
        assertNotNull(session.toString());
    }

    @Test
    void testNoArgsConstructor() {
        Session session = new Session();
        assertNull(session.getId());
        assertNull(session.getName());
        assertNull(session.getDate());
        assertNull(session.getDescription());
        assertNull(session.getTeacher());
        assertNull(session.getUsers());
        assertNull(session.getCreatedAt());
        assertNull(session.getUpdatedAt());
    }
}

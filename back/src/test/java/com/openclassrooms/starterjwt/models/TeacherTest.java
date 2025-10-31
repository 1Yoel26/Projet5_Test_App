package com.openclassrooms.starterjwt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TeacherTest {
	
	@Test
    void testAllArgsConstructorAndGetters() {
        LocalDateTime created = LocalDateTime.now().minusDays(1);
        LocalDateTime updated = LocalDateTime.now();

        Teacher teacher = new Teacher(
                1L,
                "Dupont",
                "Jean",
                created,
                updated
        );

        assertEquals(1L, teacher.getId());
        assertEquals("Dupont", teacher.getLastName());
        assertEquals("Jean", teacher.getFirstName());
        assertEquals(created, teacher.getCreatedAt());
        assertEquals(updated, teacher.getUpdatedAt());
    }

    @Test
    void testSettersAndChaining() {
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime updated = LocalDateTime.now().plusDays(1);

        Teacher teacher = new Teacher();

        // vérifier que les setters renvoient le même objet (chaînage)
        assertSame(teacher, teacher.setId(10L));
        assertSame(teacher, teacher.setLastName("Martin"));
        assertSame(teacher, teacher.setFirstName("Paul"));
        assertSame(teacher, teacher.setCreatedAt(created));
        assertSame(teacher, teacher.setUpdatedAt(updated));

        // vérifier les valeurs
        assertEquals(10L, teacher.getId());
        assertEquals("Martin", teacher.getLastName());
        assertEquals("Paul", teacher.getFirstName());
        assertEquals(created, teacher.getCreatedAt());
        assertEquals(updated, teacher.getUpdatedAt());
    }

    @Test
    void testBuilder() {
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime updated = LocalDateTime.now();

        Teacher teacher = Teacher.builder()
                .id(5L)
                .lastName("Durand")
                .firstName("Claire")
                .createdAt(created)
                .updatedAt(updated)
                .build();

        assertEquals(5L, teacher.getId());
        assertEquals("Durand", teacher.getLastName());
        assertEquals("Claire", teacher.getFirstName());
        assertEquals(created, teacher.getCreatedAt());
        assertEquals(updated, teacher.getUpdatedAt());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();

        Teacher t1 = new Teacher(1L, "Nom", "Prenom", now, now);
        Teacher t2 = new Teacher(1L, "Nom", "Prenom", now, now);
        Teacher t3 = new Teacher(2L, "Autre", "Nom", now, now);

        // equals/hashCode basé sur id
        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
        assertNotEquals(t1, t3);
        assertNotEquals(t1.hashCode(), t3.hashCode());
        assertNotEquals(t1, null);
        assertNotEquals(t1, "autre type");
    }

    @Test
    void testToStringNotNull() {
        Teacher teacher = new Teacher();
        assertNotNull(teacher.toString());
    }

    @Test
    void testNoArgsConstructor() {
        Teacher teacher = new Teacher();
        assertNull(teacher.getId());
        assertNull(teacher.getLastName());
        assertNull(teacher.getFirstName());
    }


}

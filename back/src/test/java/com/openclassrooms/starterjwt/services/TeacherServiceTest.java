package com.openclassrooms.starterjwt.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
	
	@Mock
	TeacherRepository teacherRepository;
	
	@InjectMocks
	TeacherService teacherService;
	
	@Test
	public void testFindAll(){
		
		Teacher teacher1 = new Teacher();
		
		teacher1.setId(1L);
		teacher1.setFirstName("Yoel");
		teacher1.setLastName("ILLOUZ");
		
		Teacher teacher2 = new Teacher();
		
		teacher2.setId(2L);
		teacher2.setFirstName("Camille");
		teacher2.setLastName("Dupond");
		
		List<Teacher> listTeachers = Arrays.asList(teacher1, teacher2);
		
		when(teacherRepository.findAll()).thenReturn(listTeachers);
		
		List<Teacher> listTeachersAttendu = teacherService.findAll();
		
		assertEquals(listTeachers, listTeachersAttendu);
		
		
		
	}
	
	
	@Test
	public void testFindById(){
		
		Teacher teacher1 = new Teacher();
		
		teacher1.setId(1L);
		teacher1.setFirstName("Yoel");
		teacher1.setLastName("ILLOUZ");
		
		
		when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher1));
		
		Teacher teacher2 = teacherService.findById(1L);
		
		assertEquals(teacher1, teacher2);
		
		
		
	}

}

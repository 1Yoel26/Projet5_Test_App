package com.openclassrooms.starterjwt.controllers.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.starterjwt.mapper.TeacherMapper;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import com.openclassrooms.starterjwt.services.TeacherService;

@AutoConfigureMockMvc
@SpringBootTest
class TeacherControllerIntTest {
	
	@MockBean
	TeacherMapper teacherMapper;
	
	@MockBean
	TeacherService teacherService;
	
	@MockBean
	TeacherRepository teacherRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(username = "yoga@studio.com")
	@Test
	public void findById() throws Exception {
		
		Teacher teacher1 = new Teacher();
		teacher1.setId(1L);
		teacher1.setFirstName("Yoel");
		
		when(teacherService.findById(1L)).thenReturn(teacher1);
		
		mockMvc.perform(get("/api/teacher/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		when(teacherService.findById(2L)).thenReturn(null);
		
		mockMvc.perform(get("/api/teacher/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
		
		
		when(teacherService.findById(44L)).thenReturn(null);
		
		mockMvc.perform(get("/api/teacher/AAA")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@WithMockUser
	@Test
	public void findAll() throws Exception {
		
		List<Teacher> listTeacher = new ArrayList<>();
		
		Teacher teacher1 = new Teacher();
		teacher1.setId(1L);
		teacher1.setFirstName("Yoel");
		
		listTeacher.add(teacher1);
		
		when(teacherService.findAll()).thenReturn(listTeacher);
		
		mockMvc.perform(get("/api/teacher")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	

}

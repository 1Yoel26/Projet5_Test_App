package com.openclassrooms.starterjwt.controllers.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.services.SessionService;

@AutoConfigureMockMvc
@SpringBootTest
public class SessionControllerIntTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	SessionService sessionService;
	
	@Autowired
	SessionMapper sessionMapper;
	
	@WithMockUser
	@Test
	public void findById() throws Exception{
		
		Session session1 = new Session();
		
		session1.setName("Session test");
		
		when(sessionService.getById(1L)).thenReturn(session1);
		
		mockMvc.perform(get("/api/session/1")
				.contentType(MediaType.APPLICATION_JSON)).
		andExpect(status().isOk());
		
		// test 2 avec erreur:
		when(sessionService.getById(1L)).thenReturn(null);
		
		mockMvc.perform(get("/api/session/1")
				.contentType(MediaType.APPLICATION_JSON)).
		andExpect(status().isNotFound());
		
	}
	
	@WithMockUser
	@Test
	public void findAll() throws Exception{
		
		Session session1 = new Session();
		
		session1.setName("Session test");
		
		List<Session> listSession = new ArrayList<>();
		listSession.add(session1);
		
		when(sessionService.findAll()).thenReturn(listSession);
		
		mockMvc.perform(get("/api/session")
				.contentType(MediaType.APPLICATION_JSON)).
		andExpect(status().isOk());
		
	}
	
	@WithMockUser
	@Test
	public void create() throws Exception {
		
		SessionDto sessionDto1 = new SessionDto();
	    sessionDto1.setName("Session test");
	    sessionDto1.setDescription("Description de test");
	    sessionDto1.setDate(new Date());
	    sessionDto1.setTeacher_id(1L); // ID d’un prof existant
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    String json = objectMapper.writeValueAsString(sessionDto1);

	    mockMvc.perform(post("/api/session")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).
		andExpect(status().isOk());
		
	}
	
	@WithMockUser
	@Test
	public void update() throws Exception {
		
		SessionDto sessionDto1 = new SessionDto();
	    sessionDto1.setName("Session test");
	    sessionDto1.setDescription("Description de test");
	    sessionDto1.setDate(new Date());
	    sessionDto1.setTeacher_id(1L); // ID d’un prof existant
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    String json = objectMapper.writeValueAsString(sessionDto1);

	    mockMvc.perform(put("/api/session/22")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).
		andExpect(status().isOk());
		
	}
	
	@WithMockUser
	@Test
	public void suppression() throws Exception {
		
		Session session1 = new Session();
		
		Teacher teacher1 = new Teacher();
		teacher1.setFirstName("Teacher1");
		teacher1.setLastName("Last name test");
		
		session1.setName("Session test");
		session1.setDate(new Date());
		session1.setDescription("Session test");
		session1.setTeacher(teacher1);
		
		when(sessionService.getById(22L)).thenReturn(session1);
		
		mockMvc.perform(delete("/api/session/22")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	@WithMockUser
	@Test
	public void participate() throws Exception {
		
		mockMvc.perform(post("/api/session/5/participate/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	
	@WithMockUser
	@Test
	public void noParticipate() throws Exception {
		
		mockMvc.perform(delete("/api/session/5/participate/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		verify(sessionService).noLongerParticipate(5L, 1L);
	}
	
	

}

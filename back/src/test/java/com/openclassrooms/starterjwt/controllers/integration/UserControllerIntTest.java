package com.openclassrooms.starterjwt.controllers.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.UserService;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerIntTest {
	
	@MockBean
	UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser
	@Test
	public void findById() throws Exception{
		User user1 = new User();
		user1.setId(2L);
		user1.setFirstName("Yoel");
		user1.setLastName("ILLOUZ");
		user1.setEmail("test@test.fr");
		
		when(userService.findById(2L)).thenReturn(user1);
		
		mockMvc.perform(get("/api/user/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		when(userService.findById(2L)).thenReturn(null);
		
		mockMvc.perform(get("/api/user/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
		
		when(userService.findById(44L)).thenReturn(null);
		
		mockMvc.perform(get("/api/user/AAA")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
		
	}
	
	@WithMockUser(username = "yoga@studio.com")
	@Test
	public void deleteUser() throws Exception {
		
		User user1 = new User();
		user1.setId(2L);
		user1.setFirstName("Yoel");
		user1.setLastName("ILLOUZ");
		user1.setEmail("yoga@studio.com");
		
		// test 1 sans erreur:
		when(userService.findById(4L)).thenReturn(user1);
		
		mockMvc.perform(delete("/api/user/4")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		// test 2 en cas d'erreur de compte n'appartenant pas à l'user connecté actuellement:
		user1.setEmail("yoga123@studio.com");
		when(userService.findById(4L)).thenReturn(user1);
		
		mockMvc.perform(delete("/api/user/4")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
		
	}
	
	

}

package com.openclassrooms.starterjwt.controllers.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntTest {
	
	@MockBean
	UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser
	@Test
	public void findById() throws Exception{
		
		User user1 = new User();
		user1.setEmail("paul.dupont@example.com");
		user1.setFirstName("Paul");
		user1.setLastName("Dupont");
		user1.setPassword("password123");
		user1.setAdmin(false);
		
		when(this.userService.findById(2L)).thenReturn(user1);
		
		mockMvc.perform(get("/api/user/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		
		when(this.userService.findById(99L)).thenReturn(null);
		
		// cas erreur introuvé:
		mockMvc.perform(get("/api/user/99")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
		
	
		
		mockMvc.perform(get("/api/user/AAA")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
		
	}
	
	@WithMockUser(username = "paul.dupont@example.com")
	@Test
	public void deleteUser() throws Exception {
		
		User user1 = new User();
		user1.setEmail("paul.dupont@example.com");
		user1.setFirstName("Paul");
		user1.setLastName("Dupont");
		user1.setPassword("password123");
		user1.setAdmin(false);
		
		
		when(userService.findById(1L)).thenReturn(user1);
		
		mockMvc.perform(delete("/api/user/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		
		// test  en cas d'erreur de suppression de compte non trouvé:
		when(userService.findById(5555L)).thenReturn(null);
		mockMvc.perform(delete("/api/user/5555")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
		
	}
	
	
	@WithMockUser(username = "email.erreur@example.com")
	@Test
	public void deleteUserPasAutorise() throws Exception {
		
		User user1 = new User();
		user1.setEmail("paul.dupont@example.com");
		user1.setFirstName("Paul");
		user1.setLastName("Dupont");
		user1.setPassword("password123");
		user1.setAdmin(false);
		
		
		when(userService.findById(1L)).thenReturn(user1);
		
		mockMvc.perform(delete("/api/user/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
		
	}
	
	

}

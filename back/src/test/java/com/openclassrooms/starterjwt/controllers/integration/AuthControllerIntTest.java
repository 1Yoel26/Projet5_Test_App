package com.openclassrooms.starterjwt.controllers.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.payload.request.LoginRequest;
import com.openclassrooms.starterjwt.payload.request.SignupRequest;
import com.openclassrooms.starterjwt.repository.UserRepository;
import com.openclassrooms.starterjwt.security.jwt.JwtUtils;
import com.openclassrooms.starterjwt.services.SessionService;
import com.openclassrooms.starterjwt.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Test
	public void login() throws Exception{
		
		Optional<User> user1 = userRepository.findByEmail("test.mdp@user.com");
		
		// si l'user n'existe pas le créer en Bdd:
		if(user1.isPresent() == false) {
			
			User userNouveau = new User();
			userNouveau.setAdmin(true);
			userNouveau.setEmail("test.mdp@user.com");
			userNouveau.setFirstName("First name de test");
			userNouveau.setLastName("Last name de test");
			userNouveau.setPassword(passwordEncoder.encode("test!1234"));
			
			userRepository.save(userNouveau);
			
		}
		
		// test en cas de succès de connection :
		
		LoginRequest loginRequest1 = new LoginRequest();
		loginRequest1.setEmail("test.mdp@user.com");
		loginRequest1.setPassword("test!1234");
		
		
		ObjectMapper objectMapper = new ObjectMapper();
	    String json = objectMapper.writeValueAsString(loginRequest1);

	   
		mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).
		andExpect(status().isOk());
		
		
		
		// test en cas d'erreur de connection :
		loginRequest1.setEmail("yoga@studio.com");
		loginRequest1.setPassword("mdpFauxAvecErreurPourLeTest");
		
	    json = objectMapper.writeValueAsString(loginRequest1);

	   
		mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).
		andExpect(status().isUnauthorized());
		
	}
	
	
	@Test
	public void register() throws Exception{
		
		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setEmail("test.mdp@user.com");
		signupRequest.setFirstName("First name de test");
		signupRequest.setLastName("Last name de test");
		signupRequest.setPassword("test!1234");
		
		ObjectMapper objectMapper = new ObjectMapper();
	    String json = objectMapper.writeValueAsString(signupRequest);

	    mockMvc.perform(post("/api/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).
		andExpect(status().isBadRequest());
	    
	}
	
	

}

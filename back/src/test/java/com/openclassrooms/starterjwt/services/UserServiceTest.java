package com.openclassrooms.starterjwt.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	@Test
	void testFindById() {
		
		User user = new User();
		
		user.setId(1L);
		user.setLastName("Yoel");
		
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		
		User userAttendu = userService.findById(1L);
		
		assertEquals(user, userAttendu);
		
		
		// test en cas de non trouvé:
		when(userRepository.findById(1L)).thenReturn(Optional.empty());
		
		User userAttendu2 = userService.findById(1L);
		
		assertEquals(null, userAttendu2);
		
	}
	
	@Test
	public void testDelete() {
		
		userService.delete(1L);
		
		verify(userRepository).deleteById(1L);
	}

	
}

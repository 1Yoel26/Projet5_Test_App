package com.openclassrooms.starterjwt.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.starterjwt.exception.NotFoundException;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
	
	@Mock
	SessionRepository sessionRepository;
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	SessionService sessionService;
	 
	@Test
	public void create() {
		
		Session session1 = new Session();
		session1.setId(1L);
		session1.setName("Session de yoga de test1");
		
		when(sessionRepository.save(session1)).thenReturn(session1);
		
		Session sessionDeRetourAttendu = sessionService.create(session1);
		
		verify(sessionRepository).save(session1);
		
		assertEquals(sessionDeRetourAttendu, session1);
		
	}
	
	@Test
	public void delete() {
		
		sessionService.delete(1L);
		verify(sessionRepository).deleteById(1L);
	}
	
	@Test
	public void findAll() {
		sessionService.findAll();
		verify(sessionRepository).findAll();
	}
	
	@Test
	public void getById() {
		
		Session sessionAttendu = new Session();
		sessionAttendu.setId(1L);
		sessionAttendu.setName("Session de yoga de test1");
		
		// test 1 en cas de session trouvé :
		when(sessionRepository.findById(1L)).thenReturn(Optional.of(sessionAttendu));
		
		sessionService.getById(1L);
		
		verify(sessionRepository).findById(1L);
		
		// test 2 en cas de session non trouvé :
		when(sessionRepository.findById(102L)).thenReturn(Optional.empty());
		
		Session sessionRecuperer2 =  sessionService.getById(102L);
		
		verify(sessionRepository).findById(102L);
		
		assertNull(sessionRecuperer2);
	}
	
	@Test
	public void update() {
		
		Session sessionAModifier = new Session();
		sessionAModifier.setId(5L);
		sessionAModifier.setName("Session de yoga de test1");
		
		sessionService.update(5L, sessionAModifier);
		
		verify(sessionRepository).save(sessionAModifier);
	}
	
	@Test
	public void participate() {
		
		Session session1 = new Session();
		session1.setId(1L);
		session1.setName("test participate");
		session1.setUsers(new ArrayList<>());
		
		User user1 = new User();
		user1.setFirstName("Yoel");
		
		// test1 pour tester sans erreur:
		when(sessionRepository.findById(10L)).thenReturn(Optional.of(session1));
		
		when(userRepository.findById(10L)).thenReturn(Optional.of(user1));
		
		sessionService.participate(10L, 10L);
		
		verify(sessionRepository).save(session1);
		
		// test2 pour tester avec erreur dans user et session:
		when(sessionRepository.findById(100L)).thenReturn(Optional.empty());
		
		when(userRepository.findById(100L)).thenReturn(Optional.empty());
		
		
		assertThrows(NotFoundException.class, () -> sessionService.participate(100L, 100L));
		
	}
	
	@Test
	public void noParticipate() {
		
		Session session1 = new Session();
		session1.setId(1L);
		session1.setName("test participate");
		session1.setUsers(new ArrayList<>());
		
		User user1 = new User();
		user1.setId(22L);
		user1.setFirstName("Yoel");
		
		session1.getUsers().add(user1);
		
		when(sessionRepository.findById(10L)).thenReturn(Optional.of(session1));
		
		sessionService.noLongerParticipate(10L, 22L);
		
		verify(sessionRepository).save(session1);
		
	}

}

package br.com.eventos.eventos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.eventos.eventos.controller.EventsController;
import br.com.eventos.eventos.dto.EventsDto;
import br.com.eventos.eventos.form.UpdateEventsForm;
import br.com.eventos.eventos.model.Events;
import br.com.eventos.eventos.model.Users;
import br.com.eventos.eventos.repository.EventsRepository;
import br.com.eventos.eventos.service.EventsService;


@SpringBootTest
class EventsApplicationTests {

	@Autowired
	private EventsController eventsController;

	@MockBean
	private EventsService eventsService;
	
	@MockBean
	private EventsRepository eventsRepository;
	
	private Events event = null;
	private Events event2 = null;
	private Users user = null;
	private List<Users> participants = null;
	private UpdateEventsForm updateEventsForm = null;
	
	@BeforeEach
	void mockAll() {
		user = new Users("Teste", "teste@email.com", "1155558888", "1234");
		
		Users participant1 = new Users(1L, "participante 1", "participante1@email.com", "1188885555", "1234");
		Users participant2 = new Users(2L, "participante 2", "participante2@email.com", "1122225555", "1234");
		Users participant3 = new Users(3L, "participante 3", "participante3@email.com", "1122225555", "1234");
		
		participants = Arrays.asList(participant1, participant2, participant3);
		
		event = new Events(1L, LocalDateTime.now(), "Evento de teste", "Descrição de evento de teste", user, participants);
		event2 = new Events(2L, LocalDateTime.now(), "Evento de teste 2", "Descrição de evento de teste 2", user, participants);
		
		updateEventsForm = new UpdateEventsForm(LocalDateTime.now(), "Evento Forme de Teste para Update", "Descrição eventoForm Teste", participants);
	}
	
	@Test
	void listAllEventsTest() {
		
		Mockito.when(eventsRepository.findAll()).thenReturn(Stream.of(event, event2).collect(Collectors.toList()));
		
		List<EventsDto> eventsDto = eventsController.listAllEvents();
		assertEquals(2, eventsDto.size() );
	}
	
	@Test
	void getEventsByIdTest() {
		
		Optional<Events> optionalEvents = Optional.of(event2);
		
		Mockito.when(eventsRepository.findById(2L)).thenReturn(optionalEvents);
		ResponseEntity<EventsDto> response = eventsController.getEventsById(2L);
				
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void updateEventTest() {
		
		//Optional<Events> optionalEvents = Optional.of(event2);
		
		//Mockito.when(eventsService.updateEvent(event, updateEventsForm)).thenReturn(optionalEvents);
		//ResponseEntity<EventsDto> response = eventsController.getEventsById(2L);
				
		//assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}

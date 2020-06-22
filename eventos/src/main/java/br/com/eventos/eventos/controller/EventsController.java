package br.com.eventos.eventos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.eventos.eventos.dto.EventsDto;
import br.com.eventos.eventos.form.EventsForm;
import br.com.eventos.eventos.form.UpdateEventsForm;
import br.com.eventos.eventos.model.Events;
import br.com.eventos.eventos.service.EventsService;

@RestController
@RequestMapping("/events")
public class EventsController {
	
	@Autowired
	private EventsService eventsService;
	
	@GetMapping
	public List<EventsDto> listAllEvents(){
		List<EventsDto> listEventsDto = eventsService.listAllEvents();
		
		listEventsDto.forEach(e -> {
			e.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventsController.class).getEventsById(e.getId())).withSelfRel());
		});
		
		return listEventsDto;
		
	}
	
	@PostMapping
	public ResponseEntity<EventsDto> createEvents(@RequestBody @Valid EventsForm eventForm, UriComponentsBuilder uriBuilder){

		Events event = eventsService.createEvents(eventForm);
		
		URI uriNewEvent = uriBuilder.path("/events/{id}").buildAndExpand(event.getId()).toUri();
		return ResponseEntity.created(uriNewEvent).body(new EventsDto(event));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EventsDto> getEventsById(@PathVariable Long id){
		Optional<Events> optionalEvents = eventsService.getEventsById(id);
		if(optionalEvents.isPresent()) {
			return ResponseEntity.ok(new EventsDto(optionalEvents.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEvent(@PathVariable Long id, @RequestBody @Valid UpdateEventsForm updateEventForm){
		Optional<Events> optionalEvents = eventsService.getEventsById(id);
		if(optionalEvents.isPresent()) {
			Events events = eventsService.updateEvent(optionalEvents.get(), updateEventForm);
			
			if(events==null)
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Proibido alterar a data do evento 48h antes de seu inicio!");
			
			return ResponseEntity.ok(new EventsDto(events));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable Long id){
		Optional<Events> optionalEvents = eventsService.getEventsById(id);
		if(optionalEvents.isPresent()) {
			eventsService.deleteEvent(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}

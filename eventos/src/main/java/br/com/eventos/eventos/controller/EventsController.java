package br.com.eventos.eventos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.eventos.eventos.model.Events;
import br.com.eventos.eventos.model.dto.EventsDto;
import br.com.eventos.eventos.model.form.EventsForm;
import br.com.eventos.eventos.model.form.UpdateEventsForm;
import br.com.eventos.eventos.repository.EventsRepository;

@RestController
@RequestMapping("/events")
public class EventsController {
	
	@Autowired
	private EventsRepository eventsRepository;

	@GetMapping
	@Cacheable(value="listAllEvents")
	public List<EventsDto> listAllEvents(){
		List<Events> events = eventsRepository.findAll();
		return EventsDto.convert(events);
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="listAllEvents")
	public ResponseEntity<EventsDto> createEvents(@RequestBody @Valid EventsForm eventForm, UriComponentsBuilder uriBuilder){
		Events event = eventForm.convert();
		eventsRepository.save(event);
		
		URI uriNewEvent = uriBuilder.path("/events/{id}").buildAndExpand(event.getId()).toUri();
		return ResponseEntity.created(uriNewEvent).body(new EventsDto(event));
	}
	
	@GetMapping("/{id}")
	@Cacheable(value="getEventsById")
	public ResponseEntity<EventsDto> getEventsById(@PathVariable Long id){
		Optional<Events> optionalEvents = eventsRepository.findById(id);
		if(optionalEvents.isPresent()) {
			return ResponseEntity.ok(new EventsDto(optionalEvents.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value={ "listAllEvents", "getEventsById" })
	public ResponseEntity<EventsDto> updateEvent(@PathVariable Long id, @RequestBody @Valid UpdateEventsForm updateEventForm){
		Optional<Events> optionalEvents = eventsRepository.findById(id);
		if(optionalEvents.isPresent()) {
			Events events = updateEventForm.convertToUpdate(id, eventsRepository);
			return ResponseEntity.ok(new EventsDto(events));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value={ "listAllEvents", "getEventsById" })
	public ResponseEntity<?> deleteEvent(@PathVariable Long id){
		Optional<Events> optionalEvents = eventsRepository.findById(id);
		if(optionalEvents.isPresent()) {
			eventsRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}

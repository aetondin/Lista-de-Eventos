package br.com.eventos.eventos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.eventos.eventos.dto.EventsDto;
import br.com.eventos.eventos.form.EventsForm;
import br.com.eventos.eventos.form.UpdateEventsForm;
import br.com.eventos.eventos.message.EmailSender;
import br.com.eventos.eventos.message.MessageSender;
import br.com.eventos.eventos.message.SMSSender;
import br.com.eventos.eventos.model.Events;
import br.com.eventos.eventos.repository.EventsRepository;
import br.com.eventos.eventos.repository.NotifyRepository;

@Service
public class EventsService {

	@Autowired
	private EventsRepository eventsRepository;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private NotifyRepository notifyRepository;
	
	@Cacheable(value="listAllEvents")
	public List<EventsDto> listAllEvents(){
		List<Events> events = eventsRepository.findAll();
		return EventsDto.convert(events);
	}
	
	@CacheEvict(value={ "listAllEvents", "getEventsById" }, allEntries=true)
	@Transactional
	public Events createEvents(EventsForm eventForm){
		
		List<MessageSender> listMessageSender = new ArrayList<>();
		listMessageSender.add(new EmailSender(notifyRepository));
		listMessageSender.add(new SMSSender(notifyRepository));
		
		Events event = eventForm.convert();
		
		event.getUsersParticipants().forEach(p -> {
			if(usersService.getUsersByEmail(p.getEmail()).isEmpty())
				usersService.saveUsers(p);
		});
		
		eventsRepository.save(event);
		
		listMessageSender.forEach(m -> m.send(event));
		
		return event;
	}
	
	@Cacheable(value="getEventsById")
	public Optional<Events> getEventsById(Long id){
		Optional<Events> optionalEvents = eventsRepository.findById(id);
		
		return optionalEvents;
	}

	@CacheEvict(value={ "listAllEvents", "getEventsById" }, allEntries=true)
	@Transactional
	public Events updateEvent(Events event, UpdateEventsForm updateEventsForm) {
		
		if(updateEventsForm.getDtEvent().isBefore(event.getDtEvent().minusDays(2))) {
			if(updateEventsForm.getDescription()!=null && !updateEventsForm.getDescription().isEmpty()) 
				event.setDescription(updateEventsForm.getDescription());
			if(updateEventsForm.getDtEvent()!=null) 
				event.setDtEvent(updateEventsForm.getDtEvent());
			if(updateEventsForm.getTitle()!=null && !updateEventsForm.getTitle().isEmpty()) 
				event.setTitle(updateEventsForm.getTitle());
			if(updateEventsForm.getUsersParticipants()!=null && !updateEventsForm.getUsersParticipants().isEmpty()) 
				event.setUsersParticipants(updateEventsForm.getUsersParticipants());
			
			return event;
		}
		
		return null;
	}

	@CacheEvict(value={ "listAllEvents", "getEventsById" }, allEntries=true)
	@Transactional
	public void deleteEvent(Long id) {
		eventsRepository.deleteById(id);
	}

	
}

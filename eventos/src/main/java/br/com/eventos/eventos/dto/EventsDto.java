package br.com.eventos.eventos.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import br.com.eventos.eventos.model.Events;
import br.com.eventos.eventos.model.Users;

public class EventsDto extends RepresentationModel<EventsDto>{

	private Long id;
	private LocalDateTime dtEvent;
	private String title;
	private String description;
	private String userOwnerName;
	private List<Users> usersParticipants;
	
	public EventsDto(Events events) {
		this.id = events.getId();
		this.dtEvent = events.getDtEvent();
		this.title = events.getTitle();
		this.description = events.getDescription();
		this.userOwnerName = events.getUserOwner().getName();
		this.usersParticipants = events.getUsersParticipants();
	}
	
	public Long getId() {
		return id;
	}
	public LocalDateTime getDtEvent() {
		return dtEvent;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getUserOwnerName() {
		return userOwnerName;
	}
	public List<Users> getUsersParticipants() {
		return usersParticipants;
	}
	
	public static List<EventsDto> convert(List<Events> events) {
		return events.stream().map(EventsDto::new).collect(Collectors.toList());
	}
	
}

package br.com.eventos.eventos.model.form;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.eventos.eventos.model.Events;
import br.com.eventos.eventos.model.Users;

public class EventsForm {

	@NotNull @FutureOrPresent
	private LocalDateTime dtEvent;
	
	@NotNull @NotEmpty
	private String title;
	
	private String description;
	
	@NotNull 
	private Users userOwner;
	
	@NotNull @NotEmpty @Size(min=1)
	private List<Users> usersParticipants;
	
	public LocalDateTime getDtEvent() {
		return dtEvent;
	}
	public void setDtEvent(LocalDateTime dtEvent) {
		this.dtEvent = dtEvent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Users> getUsersParticipants() {
		return usersParticipants;
	}
	public void setUsersParticipants(List<Users> usersParticipants) {
		this.usersParticipants = usersParticipants;
	}
	public Users getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(Users usersOwner) {
		this.userOwner = usersOwner;
	}
	public Events convert() {
		return new Events(dtEvent, title, description, userOwner, usersParticipants);
	}
	
}

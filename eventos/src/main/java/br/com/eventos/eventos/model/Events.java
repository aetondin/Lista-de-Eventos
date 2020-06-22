package br.com.eventos.eventos.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Audited
public class Events {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dtEvent;
	private String title;
	private String description;
	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Users userOwner;
	@ManyToMany
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private List<Users> usersParticipants;

	public Events() {
		
	}
	
	public Events(LocalDateTime dtEvent, String title, String description, Users userOwner, List<Users> usersParticipants) {
		 this.dtEvent = dtEvent;
		 this.title = title;
		 this.description = description;
		 this.userOwner = userOwner;
		 this.usersParticipants = usersParticipants;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Users getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(Users userOwner) {
		this.userOwner = userOwner;
	}
	public List<Users> getUsersParticipants() {
		return usersParticipants;
	}
	public void setUsersParticipants(List<Users> usersParticipants) {
		this.usersParticipants = usersParticipants;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Events other = (Events) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

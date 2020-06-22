package br.com.eventos.eventos.message;

import java.time.LocalDateTime;

import br.com.eventos.eventos.model.Events;
import br.com.eventos.eventos.model.NotificationType;
import br.com.eventos.eventos.model.Notify;
import br.com.eventos.eventos.repository.NotifyRepository;

public class EmailSender implements MessageSender {

	private NotifyRepository notifyRepository;
	
	public EmailSender(NotifyRepository notifyRepository) {
		this.notifyRepository = notifyRepository;
	}
	
	@Override
	public void send(Events events) {
		
		Notify notify = new Notify();
		
		notify.setDtSend(LocalDateTime.now());
		notify.setEvents(events);
		notify.setMessage(events.getDescription());
		notify.setSubject(events.getTitle());
		notify.setNotification(NotificationType.EMAIL);
		
		notifyRepository.save(notify);
		
	}

}

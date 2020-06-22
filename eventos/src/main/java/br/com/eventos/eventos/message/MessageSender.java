package br.com.eventos.eventos.message;

import br.com.eventos.eventos.model.Events;

public interface MessageSender {

	void send(Events event);
	
}

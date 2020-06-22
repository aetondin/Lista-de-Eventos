package br.com.eventos.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eventos.eventos.model.Events;

public interface EventsRepository extends JpaRepository<Events, Long>{

}

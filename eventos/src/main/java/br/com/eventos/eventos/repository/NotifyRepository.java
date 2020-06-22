package br.com.eventos.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eventos.eventos.model.Notify;

public interface NotifyRepository extends JpaRepository<Notify, Long>{

	
}

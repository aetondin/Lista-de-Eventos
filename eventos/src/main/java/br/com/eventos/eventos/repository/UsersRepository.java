package br.com.eventos.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eventos.eventos.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

}

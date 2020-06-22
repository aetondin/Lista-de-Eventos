package br.com.eventos.eventos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eventos.eventos.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByEmail(String email);
	
}

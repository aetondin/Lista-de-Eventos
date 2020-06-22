package br.com.eventos.eventos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.eventos.eventos.model.Users;
import br.com.eventos.eventos.repository.UsersRepository;

@Service
public class AutenticationService implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user = usersRepository.findByEmail(username);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UsernameNotFoundException("User not found!");
	}

}

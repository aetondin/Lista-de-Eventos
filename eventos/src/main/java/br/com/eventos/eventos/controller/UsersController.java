package br.com.eventos.eventos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.eventos.eventos.dto.UsersDto;
import br.com.eventos.eventos.form.UpdateUsersForm;
import br.com.eventos.eventos.form.UsersForm;
import br.com.eventos.eventos.model.Users;
import br.com.eventos.eventos.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;

	@GetMapping
	public List<UsersDto> listAllUsers(){
		return usersService.listAllUsers();
	}
	
	@PostMapping
	public ResponseEntity<UsersDto> createUser(@RequestBody @Valid UsersForm usersForm, UriComponentsBuilder uriBuilder){
		Users user = usersService.createUsers(usersForm);
		
		URI uriNewUser = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uriNewUser).body(new UsersDto(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsersDto> getUsersById(@PathVariable Long id){
		Optional<Users> optionalUsers = usersService.getUsersById(id);
		if(optionalUsers.isPresent()) {
			return ResponseEntity.ok(new UsersDto(optionalUsers.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsersDto> updateUser(@PathVariable Long id, @RequestBody UpdateUsersForm updateUsersForm){
		Optional<Users> optionalUsers = usersService.getUsersById(id);
		if(optionalUsers.isPresent()) {
			Users users = usersService.updateUsers(optionalUsers.get(), updateUsersForm);
			return ResponseEntity.ok(new UsersDto(users));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUsers(@PathVariable Long id){
		Optional<Users> optionalUsers = usersService.getUsersById(id);
		if(optionalUsers.isPresent()) {
			usersService.deleteUsers(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
}

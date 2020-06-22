package br.com.eventos.eventos.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.eventos.eventos.dto.UsersDto;
import br.com.eventos.eventos.form.UpdateUsersForm;
import br.com.eventos.eventos.form.UsersForm;
import br.com.eventos.eventos.model.Users;
import br.com.eventos.eventos.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Cacheable(value="listAllUsers")
	public List<UsersDto> listAllUsers() {
		List<Users> users = usersRepository.findAll();

		return UsersDto.convert(users);
	}

	@Transactional
	@CacheEvict(value="listAllUsers", allEntries=true)
	public Users createUsers(UsersForm usersForm) {
		Users user = usersForm.convertToUsers();
		usersRepository.save(user);
		
		return user;
	}
	
	@Transactional
	@CacheEvict(value="listAllUsers", allEntries=true)
	public Users saveUsers(Users users) {
		usersRepository.save(users);
		usersRepository.flush();
		return users;
	}

	@Cacheable(value="getUsersById")
	public Optional<Users> getUsersById(Long id) {
		Optional<Users> optionalUsers = usersRepository.findById(id);

		return optionalUsers;
	}
	
	@Cacheable(value="getUsersByEmail")
	public Optional<Users> getUsersByEmail(String email) {
		Optional<Users> optionalUsers = usersRepository.findByEmail(email);

		return optionalUsers;
	}
	
	@Transactional
	@CacheEvict(value={ "listAllUsers", "getUsersById", "getUsersByEmail" }, allEntries=true)
	public Users updateUsers(Users users, UpdateUsersForm updateUsersForm) {
		
		if(updateUsersForm.getEmail()!=null && !updateUsersForm.getEmail().isEmpty())
			users.setEmail(updateUsersForm.getEmail());
		if(updateUsersForm.getPhone()!=null && !updateUsersForm.getPhone().isEmpty())
			users.setPhone(updateUsersForm.getPhone());
		if(updateUsersForm.getPassword()!=null && !updateUsersForm.getPassword().isEmpty())
			users.setPassword(updateUsersForm.getPassword());
		
		return users;
	}

	@Transactional
	@CacheEvict(value={ "listAllUsers", "getUsersById", "getUsersByEmail" }, allEntries=true)
	public void deleteUsers(Long id) {
		usersRepository.deleteById(id);
		
	}
	
}

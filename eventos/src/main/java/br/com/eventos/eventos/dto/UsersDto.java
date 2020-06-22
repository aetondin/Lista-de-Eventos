package br.com.eventos.eventos.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.eventos.eventos.model.Users;

public class UsersDto {

	private Long id;
	private String name;
	private String email;
	private String phone;
	
	public UsersDto(Users users) {
		this.id = users.getId();
		this.name = users.getName();
		this.email = users.getEmail();
		this.phone = users.getPhone();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public static List<UsersDto> convert(List<Users> users) {
		return users.stream().map(UsersDto::new).collect(Collectors.toList());
	}
	
}

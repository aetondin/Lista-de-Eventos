package br.com.eventos.eventos.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.eventos.eventos.model.Users;

public class UsersForm {

	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String email;
	
	private String phone;
	
	private String password;
	
	public UsersForm() {
		
	}
	
	public UsersForm(String name, String email, String phone, String password) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}
	public Users convertToUsers() {
		return new Users(this.name, this.email, this.phone, this.password);
	}
	public UsersForm convertToUsersForm(Users users) {
		return new UsersForm(users.getName(), users.getEmail(), users.getPhone(), users.getPassword());
	}
}

package br.com.eventos.eventos.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.sun.istack.NotNull;

public class LoginForm {

	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String password;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
	
	
}

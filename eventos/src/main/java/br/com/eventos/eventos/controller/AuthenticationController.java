
package br.com.eventos.eventos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eventos.eventos.dto.tokenDto;
import br.com.eventos.eventos.form.LoginForm;
import br.com.eventos.eventos.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<tokenDto> Authenticate(@RequestBody @Valid LoginForm loginForm){
		
		UsernamePasswordAuthenticationToken usernamePasswordAuth = loginForm.convert();
		try {
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuth);
			String token = tokenService.getToken(authentication);
			
			return ResponseEntity.ok(new tokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
	
}

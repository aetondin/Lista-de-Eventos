package br.com.eventos.eventos.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.eventos.eventos.model.Users;
import br.com.eventos.eventos.service.TokenService;
import br.com.eventos.eventos.service.UsersService;

public class AuthenticationTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UsersService usersService;
	
	public AuthenticationTokenFilter(TokenService tokenService, UsersService usersService) {
		this.tokenService = tokenService;
		this.usersService = usersService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String token = getToken(request);
		boolean validToken = tokenService.isValidToken(token);
		if(validToken) {
			authenticateUser(token);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private void authenticateUser(String token) {
		Long userId = this.tokenService.getUserIdByToken(token);
		Users user =  this.usersService.getUsersById(userId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");
		if(token != null && token.contains("Bearer ")) {
			return token.substring(7, token.length());
		}
		
		return null;
	}

}

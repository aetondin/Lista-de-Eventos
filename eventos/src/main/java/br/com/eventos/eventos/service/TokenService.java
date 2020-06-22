package br.com.eventos.eventos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.eventos.eventos.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${auth.jwt.expiration}")
	private String tokenExpiration;
	
	@Value("${auth.jwt.secretkey}")
	private String secretKey;
	
	public String getToken(Authentication authentication) {
		
		Date dateNow = new Date();
		@SuppressWarnings("deprecation")
		Date dateExpiration = new Date(dateNow.getTime() + Long.parseLong(tokenExpiration));
		
		Users user = (Users) authentication.getPrincipal();

		return Jwts.builder()
			.setId("API Eventos")
			.setSubject(user.getId().toString())
			.setIssuedAt(dateNow)
			.setExpiration(dateExpiration)
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
		
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserIdByToken(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
}

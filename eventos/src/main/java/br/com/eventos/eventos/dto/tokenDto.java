package br.com.eventos.eventos.dto;

public class tokenDto {

	
	private String token;
	private String typeAuthorization;

	public tokenDto(String token, String typeAuthorization) {
		this.token = token;
		this.typeAuthorization = typeAuthorization;
	}

	public String getToken() {
		return token;
	}

	public String getTypeAuthorization() {
		return typeAuthorization;
	}

}

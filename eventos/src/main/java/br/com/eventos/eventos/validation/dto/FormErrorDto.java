package br.com.eventos.eventos.validation.dto;

public class FormErrorDto {

	private String field;
	private String errorMessage;
	
	public FormErrorDto(String field, String errorMessage) {
		this.field = field;
		this.errorMessage = errorMessage;
	}

	public String getField() {
		return field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	
}

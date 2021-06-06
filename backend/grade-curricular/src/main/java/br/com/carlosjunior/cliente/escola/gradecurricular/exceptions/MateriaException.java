package br.com.carlosjunior.cliente.escola.gradecurricular.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class MateriaException extends RuntimeException {

	private static final long serialVersionUID = -2736437554813195674L;
	
	private final HttpStatus httpStatus;


	public MateriaException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem);
		this.httpStatus = httpStatus;
	}

}

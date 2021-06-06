package br.com.carlosjunior.cliente.escola.gradecurricular.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.carlosjunior.cliente.escola.gradecurricular.exceptions.MateriaException;
import br.com.carlosjunior.cliente.escola.gradecurricular.models.ErrorResponse;
import br.com.carlosjunior.cliente.escola.gradecurricular.models.ErrorResponse.ErrorResponseBuilder;

@ControllerAdvice
public class ResourceHandler {

	@ExceptionHandler(MateriaException.class)
	public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException m) {
		ErrorResponseBuilder erro = ErrorResponse.builder();
		erro.httpStatus(m.getHttpStatus().value());
		erro.mensagem(m.getMessage());
		erro.timeStamp(System.currentTimeMillis());
		return ResponseEntity.status(m.getHttpStatus()).body(erro.build());
	}

}

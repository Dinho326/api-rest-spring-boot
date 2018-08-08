package br.com.agendaAPI.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.agendaAPI.model.InfoError;
import br.com.agendaAPI.service.exceptions.ContatoNotFound;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ContatoNotFound.class)
	public ResponseEntity<InfoError> handleContatoNotFoundException
	(ContatoNotFound e, HttpServletRequest request){
		
		InfoError erro = new InfoError();
		
		erro.setStatus(404l);
		erro.setTitulo("O Contato não foi encontrado");
		erro.setMsg("Maiores informações entre em contato em http://edilsontomas.com.br");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
}

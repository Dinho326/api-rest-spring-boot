package br.com.agendaAPI.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.agendaAPI.model.InfoError;
import br.com.agendaAPI.service.exceptions.ContactNotFound;
import br.com.agendaAPI.service.exceptions.GroupNotFound;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ContactNotFound.class)
	public ResponseEntity<InfoError> handleContatoNotFoundException
	(ContactNotFound e, HttpServletRequest request){
		
		InfoError erro = new InfoError();
		erro.setStatus(404l);
		erro.setTitle("O Contato não foi encontrado");
		erro.setMessage("Maiores informações entre em contato em http://edilsontomas.com.br");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	
	@ExceptionHandler(GroupNotFound.class)
	public ResponseEntity<InfoError> handleContatoNotFoundException
	(GroupNotFound e, HttpServletRequest request){
		
		InfoError erro = new InfoError();
		erro.setStatus(404l);
		erro.setTitle("O Grupo não foi encontrado");
		erro.setMessage("Maiores informações entre em contato em http://edilsontomas.com.br");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<InfoError> handleDataIntegrityViolationException
							(DataIntegrityViolationException e, HttpServletRequest request) {
		
		InfoError erro = new InfoError();
		erro.setStatus(400l);
		erro.setTitle("Requisição inválida");
		erro.setMessage("Maiores informações entre em contato em http://edilsontomas.com.br");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
}

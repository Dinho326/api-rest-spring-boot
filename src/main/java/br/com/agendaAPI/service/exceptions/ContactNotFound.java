package br.com.agendaAPI.service.exceptions;

public class ContactNotFound extends RuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2426058236132643557L;

	public ContactNotFound(String mensagem) {
		super(mensagem);
	}

	public ContactNotFound(String mensagem, Throwable error) {
		super(mensagem, error);
	}
}

package br.com.agendaAPI.service.exceptions;

public class ContatoNotFound extends RuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2426058236132643557L;

	public ContatoNotFound(String mensagem) {
		super(mensagem);
	}

	public ContatoNotFound(String mensagem, Throwable error) {
		super(mensagem, error);
	}
}

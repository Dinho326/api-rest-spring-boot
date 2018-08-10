package br.com.agendaAPI.service.exceptions;

public class GroupNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4558832815664004945L;

	public GroupNotFound(String message) {
		super(message);
	}

	public GroupNotFound(String message, Throwable error) {
		super(message, error);
	}
	
	
	
}

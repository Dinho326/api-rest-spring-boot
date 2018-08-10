package br.com.agendaAPI.model;

public class InfoError {

	private String title;
	
	private Long status;
	
	private Long timestamp;
	
	private String message;

	public String getTitle() {
		return title;
	}

	public void setTitle(String titulo) {
		this.title = titulo;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}
	
	
}

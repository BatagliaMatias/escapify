package ar.gob.hcdn.ticket.dto;

public class AplicacionDTO {
	private String from;
	private String nombre;
	private String token;
	
	public AplicacionDTO() {
	}
	
	public String getFrom() {
		return from;
	}
	public AplicacionDTO setFrom(String from) {
		this.from = from;
		return this;
	}
	public String getNombre() {
		return nombre;
	}
	public AplicacionDTO setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	public String getToken() {
		return token;
	}
	public AplicacionDTO setToken(String token) {
		this.token = token;
		return this;
	}
	
	
}

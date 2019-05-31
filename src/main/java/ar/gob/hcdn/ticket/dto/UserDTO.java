package ar.gob.hcdn.ticket.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{

	private String nombre;
	private String userID;
	private String nombreDominio;
	
	public UserDTO() {
	}

	public String getNombre() {
		return nombre;
	}

	public UserDTO setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getUserID() {
		return userID;
	}

	public UserDTO setUserID(String userID) {
		this.userID = userID;
		return this;
	}

	public String getNombreDominio() {
		return nombreDominio;
	}

	public UserDTO setNombreDominio(String nombreDominio) {
		this.nombreDominio = nombreDominio;
		return this;
	}

	
}

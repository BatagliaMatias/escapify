package ar.gob.hcdn.ticket.common;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Representa la respuesta que se devolvera desde los servicios en forma
 * generica. Para tener una abstraccion que pueda ser manipulada internamente a
 * modo de marker
 * 
 * @author slucena
 */
public class HcdnResponse<T> implements Persistible, Serializable {

	private static final long serialVersionUID = 7085314586666805112L;
	private ErrorDeServicioDTO error;
	private T data;

	public HcdnResponse() {

	}

	public HcdnResponse(ErrorDeServicioDTO error) {
		this.error = error;
	}

	public HcdnResponse<T> setData(T data) {
		this.data = data;
		return this;
	}

	public T getData() {
		return data;
	}

	public ErrorDeServicioDTO getError() {
		return this.error;
	}

	@Override
	@JsonIgnore
	public String getObjectID() {
		return ((Persistible) data).getObjectID();
	}
}

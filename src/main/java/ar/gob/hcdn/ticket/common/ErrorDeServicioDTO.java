package ar.gob.hcdn.ticket.common;

import java.io.Serializable;

/**
 * Manejo de errores para los servicios, es la forma en que se comunica un error a la aplicacion
 * invocante.
 *  
 * @author slucena
 */
public class ErrorDeServicioDTO implements Serializable {

	private String codigo = "ERROR-";
	private String descCorta;
	private String descLarga;

	public ErrorDeServicioDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ErrorDeServicioDTO(Long codigo, String descCorta, String descLarga) {
		this.codigo+=codigo.toString();
		this.descCorta=descCorta;
		this.descLarga=descLarga;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescCorta() {
		return descCorta;
	}
	public void setDescCorta(String descCorta) {
		this.descCorta = descCorta;
	}
	public String getDescLarga() {
		return descLarga;
	}
	public void setDescLarga(String descLarga) {
		this.descLarga = descLarga;
	}
}

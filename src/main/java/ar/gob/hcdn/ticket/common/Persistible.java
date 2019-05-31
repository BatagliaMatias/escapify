package ar.gob.hcdn.ticket.common;

/**
 * Marker para las clases de dominio.
 * 
 * @author sgcoco
 */
public interface Persistible {

	/**
	 * El dato por el cual van a ser identificados univocamente los objetos de
	 * dominio que seran persistidos
	 * 
	 * @return
	 */
	String getObjectID();

}

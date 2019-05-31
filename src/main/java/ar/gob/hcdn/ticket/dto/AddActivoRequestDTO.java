package ar.gob.hcdn.ticket.dto;

import java.io.Serializable;

public class AddActivoRequestDTO implements Serializable{

	private String ticketID;
	private String activoNro;
	
	public AddActivoRequestDTO() {
	}

	public AddActivoRequestDTO(String ticketID, String activoID) {
		this.ticketID=ticketID;
		this.activoNro=activoID;
	}

	public String getTicketID() {
		return ticketID;
	}
	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}
	public String getActivoNro() {
		return activoNro;
	}
	public void setActivoNro(String activoNro) {
		this.activoNro = activoNro;
	}
	
}

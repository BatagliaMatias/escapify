package ar.gob.hcdn.ticket.exception;

public class OperationNotAllowedException extends Exception {

	private static final long serialVersionUID = 5151060755076768914L;

	public OperationNotAllowedException() {
	}

	public OperationNotAllowedException(String mensaje) {
		super(mensaje);
	}

}

package me.citasmedicas.restservice;

public class MedicoNoEncontradoException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MedicoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MedicoNoEncontradoException() {
    }

}

package cz.cvut.fel.vyzkumodolnosti.security.exception;

public class CantUpdatePasswordException extends RuntimeException{
	public CantUpdatePasswordException(String message) {
		super(message);
	}
}

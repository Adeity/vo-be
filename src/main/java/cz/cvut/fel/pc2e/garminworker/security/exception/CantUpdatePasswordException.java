package cz.cvut.fel.pc2e.garminworker.security.exception;

public class CantUpdatePasswordException extends RuntimeException{
	public CantUpdatePasswordException(String message) {
		super(message);
	}
}

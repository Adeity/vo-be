package cz.cvut.fel.vyzkumodolnosti.handler;

public class EntryNotFoundException extends RuntimeException{
	public EntryNotFoundException() {
	}

	public EntryNotFoundException(String message) {
		super(message);
	}
}

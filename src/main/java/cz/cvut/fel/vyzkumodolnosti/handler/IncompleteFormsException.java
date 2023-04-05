package cz.cvut.fel.vyzkumodolnosti.handler;

public class IncompleteFormsException extends RuntimeException{

    public IncompleteFormsException() {
    }

    public IncompleteFormsException(Throwable cause) {
        super(cause);
    }

    public IncompleteFormsException(String message) {
        super(message);
    }
}

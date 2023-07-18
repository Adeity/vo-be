package cz.cvut.fel.vyzkumodolnosti.handler;

public class IllegalTimeWindowException extends RuntimeException {

    public IllegalTimeWindowException() {
    }

    public IllegalTimeWindowException(Throwable cause) {
        super(cause);
    }

    public IllegalTimeWindowException(String message) {
        super(message);
    }

}

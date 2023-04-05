package cz.cvut.fel.vyzkumodolnosti.handler;

public class IllegalMeqValueException extends RuntimeException{

    public IllegalMeqValueException() {
    }

    public IllegalMeqValueException(Throwable cause) {
        super(cause);
    }

    public IllegalMeqValueException(String message) {
        super(message);
    }
}

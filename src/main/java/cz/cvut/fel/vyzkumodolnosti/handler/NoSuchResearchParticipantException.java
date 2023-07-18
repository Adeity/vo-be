package cz.cvut.fel.vyzkumodolnosti.handler;

public class NoSuchResearchParticipantException extends RuntimeException {

    public NoSuchResearchParticipantException() {
    }

    public NoSuchResearchParticipantException(Throwable cause) {
        super(cause);
    }

    public NoSuchResearchParticipantException(String message) {
        super(message);
    }
}

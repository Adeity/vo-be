package cz.cvut.fel.vyzkumodolnosti.controllers.errormodel;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private List<ErrorModel> errorMessage;


    public List<ErrorModel> getErrorMessage() {
        if (errorMessage == null) {
            errorMessage = new ArrayList<>();
        }
        return errorMessage;
    }

    public void setErrorMessage(List<ErrorModel> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public static class ErrorResponseBuilder {
        private final ErrorResponse errorResponse;

        public ErrorResponseBuilder() {
            this.errorResponse = new ErrorResponse();
        }

        public ErrorResponseBuilder errorMessage(List<ErrorModel> models) {
            errorResponse.getErrorMessage().addAll(models);
            return this;
        }

        public ErrorResponse build() {
            return errorResponse;
        }
    }
}

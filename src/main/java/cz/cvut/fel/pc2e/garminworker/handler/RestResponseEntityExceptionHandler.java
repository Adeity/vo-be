package cz.cvut.fel.pc2e.garminworker.handler;

import cz.cvut.fel.pc2e.garminworker.security.exception.CantUpdatePasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {
	private static void logException(RuntimeException ex) {
//		log.error("Exception caught:", ex);
	}

	private static ErrorInfo errorInfo(HttpServletRequest request, Throwable e) {
		return new ErrorInfo(e.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(CantUpdatePasswordException.class)
	public ResponseEntity<ErrorInfo> validation(HttpServletRequest request, CantUpdatePasswordException e) {
		logException(e);
		return new ResponseEntity<>(errorInfo(request, e), HttpStatus.BAD_REQUEST);
	}
}
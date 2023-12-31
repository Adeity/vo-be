package cz.cvut.fel.vyzkumodolnosti.handler;

import cz.cvut.fel.vyzkumodolnosti.security.exception.CantUpdatePasswordException;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {
	private static void logException(RuntimeException ex) {
		log.error("Exception caught:", ex);
	}

	private static ErrorInfo errorInfo(HttpServletRequest request, Throwable e) {
		return new ErrorInfo(e.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler(CantUpdatePasswordException.class)
	public ResponseEntity<ErrorInfo> validation(HttpServletRequest request, CantUpdatePasswordException e) {
//		logException(e);
		return new ResponseEntity<>(errorInfo(request, e), HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<ErrorInfo> validation(HttpServletRequest request, RuntimeException e) {
//		logException(e);
//		return new ResponseEntity<>(errorInfo(request, e), HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(EntryNotFoundException.class)
	public ResponseEntity<ErrorInfo> entryNotFound(HttpServletRequest request, RuntimeException e) {
		return new ResponseEntity<>(errorInfo(request, e), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResearchNumberRegisteredException.class)
	public ResponseEntity<ErrorInfo> eresearchNumberRegistered(HttpServletRequest request, RuntimeException e) {
		return new ResponseEntity<>(errorInfo(request, e), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PSQLException.class)
	public ResponseEntity<ErrorInfo> idk(HttpServletRequest request, PSQLException e) {
		return new ResponseEntity<>(errorInfo(request, e), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> idk(HttpServletRequest request, ConstraintViolationException e) {
		return new ResponseEntity<>(errorInfo(request, e), HttpStatus.BAD_REQUEST);
	}
}

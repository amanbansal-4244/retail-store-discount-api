package com.retail.store.discount.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
		ErrorDetail errorDetails = new ErrorDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorList);
		return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
	}

	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetail apiError = new ErrorDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
				ex.getParameterName() + " parameter is missing");
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<>(1);
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}

		ErrorDetail apiError = new ErrorDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ CustomException.class })
	public ResponseEntity<Object> handleCustomExceptionAll(CustomException ex, WebRequest request) {

		ErrorDetail apiError = new ErrorDetail(ex.getHttpStatus(), ex.getHttpStatus().value(), ex.getMessage(),
				ex.getDescription());
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

		ErrorDetail apiError = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),
				"Exception occurred.");
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorDetail handleResourceNotFoundException(final ResourceNotFoundException exception,
			final HttpServletRequest request) {
		log.error(
				"ResourceNotFoundException occurred CustomExceptionHandler:handleResourceNotFoundException in  method {}:",
				exception);

		return new ErrorDetail(exception.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND,
				HttpStatus.NOT_FOUND.name());
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomException errorDetails = (CustomException) ex.getCause().getCause();
		ErrorDetail errorDetail = new ErrorDetail(errorDetails.getHttpStatus(), errorDetails.getMessage(),
				errorDetails.getDescription());
		return new ResponseEntity<>(errorDetail, new HttpHeaders(), errorDetails.getHttpStatus());
	}

}

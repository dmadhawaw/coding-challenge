package com.anz.codingchallenge.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.anz.codingchallenge.api.ApiResponse;
import com.anz.codingchallenge.api.ErrorResponse;
import com.anz.codingchallenge.exception.ErrorCodes;
import com.anz.codingchallenge.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiResponse<List<ErrorResponse>> handleConflict(ResourceNotFoundException ex, WebRequest request) {
		ErrorResponse errorResponse = ErrorResponse.builder().description(ex.getMessage()).errorCode(ex.getErrorCode())
				.build();
		ApiResponse<List<ErrorResponse>> myDataError = ApiResponse.<List<ErrorResponse>>builder()
				.errors(Arrays.asList(errorResponse)).build();
		return myDataError;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
		List<ErrorResponse> errors = ex.getConstraintViolations().stream().map(error -> convertToApiError(error))
				.collect(Collectors.toList());
		return new ApiResponse(null, errors);

	}

	private ErrorResponse convertToApiError(ConstraintViolation error) {
		return ErrorResponse.builder().errorCode(ErrorCodes.BAD_REQUEST.toString()).description(error.getMessage())
				.build();

	}

}

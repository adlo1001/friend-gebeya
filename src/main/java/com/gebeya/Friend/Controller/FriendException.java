package com.gebeya.Friend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gebeya.Friend.Exceptions.ErrorMessage;

public class FriendException extends RuntimeException {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public FriendException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	ErrorMessage exceptionHandler(RuntimeException e) {
		return new ErrorMessage(e.getMessage(), "400");
	}
}

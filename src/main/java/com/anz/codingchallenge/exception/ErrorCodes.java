package com.anz.codingchallenge.exception;

public enum ErrorCodes {
	NOT_FOUND("404"), BAD_REQUEST("400");

	private String code;

	ErrorCodes(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}

package com.anz.codingchallenge.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

	private String errorCode;

	private String description;

}

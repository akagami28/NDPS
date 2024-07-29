package com.ndps.ots.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This Class is used as a body for exceptions in a JSON format to the client.
 * 
 * @author AkhilK
 *
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

	private String messageString;
	private int status;
	private long timestamp;

}

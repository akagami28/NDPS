package com.ndps.ots.smsservice.model;

import lombok.Data;

/**
 * Request Body for SMS.
 * @author AkhilK
 *
 */
@Data
public class SMSRequest {
	
	private String To;
	private String message;
}

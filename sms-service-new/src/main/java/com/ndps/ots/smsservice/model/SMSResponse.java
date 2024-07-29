package com.ndps.ots.smsservice.model;

import java.util.List;

import lombok.Data;

/**
 * Response Body for SMS.
 * @author AkhilK
 *
 */
@Data
public class SMSResponse {

	private String status;
	private List<SMSData> data;
	private String message;	
}

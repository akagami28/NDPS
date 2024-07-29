package com.ndps.ots.smsservice.model;

import lombok.Data;
/**
 * Response Body for data field in SMS Body.
 * @author AkhilK
 *
 */
@Data
public class SMSData {

	private String id;
	private String customid;
	private String customid1;
	private String customid2;
	private String mobile;
	private String status;	
}

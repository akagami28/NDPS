package com.ndps.ots.smsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndps.ots.smsservice.model.SMSRequest;
import com.ndps.ots.smsservice.model.SMSResponse;
import com.ndps.ots.smsservice.service.SMSService;
import com.ndps.ots.smsservice.utils.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/sms")
public class SMSController {

	/**
	 *  Injecting SMS Service using field injection.
	 */
	@Autowired
	private SMSService smsService;
	
	public final static String tracerId = String.valueOf(System.currentTimeMillis());
	
	/**
	 * The method calls the sendSMS() in Service and returns a SMSResponse to the client.
	 * @param request
	 * @return SMS Response
	 */
	@GetMapping("/send/")
	public SMSResponse sendSms(@RequestBody SMSRequest request)
	{
		 log.info("{}_{} Inside sendSms() - Request >> {}",Constant.SERVICE_1,tracerId,request);
		 log.info("{}_{} Inside sendSms(), SMS sent successfully....",Constant.SERVICE_1,tracerId);
		 SMSResponse smsResponse = smsService.sendSMS(request,tracerId);
		 log.info("{}_{} Inside sendSms() - Response >> {}",Constant.SERVICE_1,tracerId,smsResponse);
		 return smsResponse;
	}
}
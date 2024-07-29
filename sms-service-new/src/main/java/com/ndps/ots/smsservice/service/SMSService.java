package com.ndps.ots.smsservice.service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ndps.ots.smsservice.model.SMSRequest;
import com.ndps.ots.smsservice.model.SMSResponse;
import com.ndps.ots.smsservice.utils.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SMSService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${kaleyra.apiKey}")
	private String apiKey;
	
	@Value("${kaleyra.senderId}")
	private String senderId;
	
	@Value("${kaleyra.apiUrl}")
	private String apiUrl;
	
	@Value("${kaleyra.dlrurl}")
	private String dlrurl;
	
	/**
     * This method calls the SMS API using RestTemplate and returns a SMSResponse body to the client.
     * @param smsRequest
     * @param tracerId
     * @return SMSResponse
     */
	public SMSResponse sendSMS(SMSRequest smsRequest, String tracerId)
	{
		String url2="https://www.nttdatapay.com/";
		String message = "Dear+Sir,+kindly+click+on+the+below+link+to+make+online+payment,"
				+ "+Regards+ATOM+TECHNOLOGIES+LIMITED+_Hitachi+Network+URL+"+url2;
//		String format = "json";
//		SMSResponse smsResponse = null;
		log.info("{}_{} Inside sendSMS() - Request >> {}",Constant.SERVICE_1,tracerId,smsRequest);
		String url = apiUrl+"?method=sms&api_key="+apiKey+"&to="+smsRequest.getTo()+"&sender="+senderId+
				"&message="+message+"&format=json&dlrurl="+dlrurl;
//		log.info("{}_{} Inside sendSMS() - URL >> {}",Constant.SERVICE_1,tracerId,url);
		SMSResponse smsResponse = restTemplate.getForObject(url, SMSResponse.class);
		
		String encodedURL = URLEncoder.encode(url);
		String decodedURL = URLDecoder.decode(url);
		/*
		 * https%3A%2F%2Falerts.kaleyra.com%2Fapi%2Fv4%2F%3Fmethod%3Dsms%26api_key
		 * %3DA003f26b834c2a6c1778d54a954d18179%26to%3Dnull%26sender%3DATOMMO%26message
		 * %3DDear%2BSir%2C%2Bkindly%2Bclick%2Bon%2Bthe%2Bbelow%2Blink%2Bto%2Bmake
		 * %2Bonline%2Bpayment%2C%2BRegards%2BATOM%2BTECHNOLOGIES%2BLIMITED%2B_Hitachi
		 * %2BNetwork%2BURL%2Bhttps%3A%2F%2Fwww.nttdatapay.com%2F%26format%3Djson%26dlrurl
		 * %3Dhttp%3A%2F%2Fatomz.atomtech.in%2FmGalla_vitalchain%2Ftest
		 */
		log.info("{}_{} Inside sendSMS() - URL >> {}",Constant.SERVICE_1,tracerId,encodedURL);
		log.info("{}_{} Inside sendSMS() - URL >> {}",Constant.SERVICE_1,tracerId,decodedURL);
		log.info("{}_{} Inside sendSMS() - Response >> {}",Constant.SERVICE_1,tracerId,smsResponse);
		
		return smsResponse;
	}
	
	/**
	 * This static block is used to disable SSL verification for calling the SMS API.
	 */
	static {
        disableSslVerification();
    }
    private static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
	
    
}

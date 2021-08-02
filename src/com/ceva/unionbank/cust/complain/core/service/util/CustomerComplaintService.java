/**
 * 
 */
package com.ceva.unionbank.cust.complain.core.service.util;

import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ceva.unionbank.cust.complain.core.service.dao.CustomerComplainDao;
import com.ceva.unionbank.cust.complain.service.util.RequestManager;
import com.ceva.unionbank.services.core.ServiceManager;
import com.ceva.unionbank.services.utils.Constants;
import com.ceva.unionbank.services.utils.PropertyReader;

/**
 * @author Dipak kumar
 *
 */
public class CustomerComplaintService implements Constants {

	private static Logger logger = Logger.getLogger( CustomerComplaintService.class );

	public static Map<String, String> map=null;
	
	static {
		map = PropertyReader.read();
	}

	public static JSONObject registerComplaint(JSONObject jrequest)
	{

		JSONObject responseJson = null;
		JSONObject jresponse = new JSONObject();
		ServiceManager obj = new ServiceManager();
		boolean flag=false;

		try
		{
			JSONObject bankReqObj  = new JSONObject();
			bankReqObj =  RequestManager.getReqBody(jrequest, "bankdata");
			System.out.println("Bank Request ::" + bankReqObj);
			String userid=RequestManager.getReqHeaderValue(jrequest,"userid");
			String channel=RequestManager.getReqHeaderValue(jrequest,"channel");
			
		flag=	CustomerComplainDao.registerComplaint(bankReqObj, jresponse,userid,channel);
		
		if(flag) {
			jresponse.put("respcode", "00");
			jresponse.put("respdesc", "SUCCESS");
			jresponse.put("servrespcode", "00");
			jresponse.put("servrespdesc", "SUCCESS");
			
		}else {
			jresponse.put("respcode", "01");
			jresponse.put("respdesc", "Sorry, we are unable to complete your request at this time. Please try again later.");
			jresponse.put("servrespcode", "01");
			jresponse.put("servrespdesc", "Sorry, we are unable to complete your request at this time. Please try again later.");
		}
			
			


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jresponse;

	}
	
	
	
	
	public static JSONObject listOfCard(JSONObject jrequest)
	{

		JSONObject responseJson = null;
		JSONObject jresponse = new JSONObject();
		ServiceManager obj = new ServiceManager();

		try
		{
			JSONObject bankReqObj  = new JSONObject();
			bankReqObj =  RequestManager.getReqBody(jrequest, "bankdata");
			System.out.println("Bank Request ::" + bankReqObj);
			String userid=RequestManager.getReqHeaderValue(jrequest,"userid");
			String channel=RequestManager.getReqHeaderValue(jrequest,"channel");
			bankReqObj.put("channel", channel);
			
			
			
			jresponse= DebitCardListService.getFullDebitCardsByUsername(bankReqObj);
			System.out.println("Response "+jresponse);
			


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jresponse;

	}
	
	

	public static JSONObject getListOfComplain(JSONObject jrequest)
	{

		JSONObject responseJson = null;
		JSONObject jresponse = new JSONObject();
		ServiceManager obj = new ServiceManager();

		try
		{
			JSONObject bankReqObj  = new JSONObject();
			bankReqObj =  RequestManager.getReqBody(jrequest, "bankdata");
			System.out.println("Bank Request ::" + bankReqObj);
			String userid=RequestManager.getReqHeaderValue(jrequest,"userid");
			String channel=RequestManager.getReqHeaderValue(jrequest,"channel");
			
			jresponse=CustomerComplainDao.getListOfComplain(jrequest, jresponse);;
			System.out.println("Response "+jresponse);
			


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jresponse;

	}
	
	
	
	
	
	
	
	
}

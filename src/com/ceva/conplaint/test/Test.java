/**
 * 
 */
package com.ceva.conplaint.test;

import org.json.JSONObject;

import com.ceva.unionbank.cust.complain.core.service.call.CustomerComplaintServiceCall;

/**
 * @author Dipak kumar
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		getListOfCardtype();
		regComplaint();
//		getListOfComplain();
		
	}

	
	public static void getListOfCardtype()
	{

		try
		{

			JSONObject jrequest = new JSONObject();

			JSONObject requestHeader = new JSONObject();
			requestHeader.put("reqtype", "GET_LIST_OF_CARD" );
			requestHeader.put("flowid", System.currentTimeMillis()+"" );
			requestHeader.put("channel", "MOBILE" );
			requestHeader.put("transdate", System.currentTimeMillis()+"" );
			requestHeader.put("servicetype", "CUSTOMER_COMPLAIN");
			requestHeader.put("userid", "AODUNAYO" );

			jrequest.put("requestheader", requestHeader);

			JSONObject requestBody = new JSONObject();

			JSONObject bankDataObj = new JSONObject();
			bankDataObj.put("userid", "0043163857" );
			bankDataObj.put("accountno", "0043163857" );
//			bankDataObj.put("userid", "0043163857" );
			
			
			JSONObject extradataObj = new JSONObject();


			requestBody.put("bankdata" , bankDataObj );
			requestBody.put("extradata", extradataObj );

			jrequest.put("requestbody", requestBody);

			System.out.println("Request ::" + jrequest);
			JSONObject respJson = CustomerComplaintServiceCall.route(jrequest);
			System.out.println("Response Json ::"+respJson);


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public static void getListOfComplain()
	{

		try
		{

			JSONObject jrequest = new JSONObject();

			JSONObject requestHeader = new JSONObject();
			requestHeader.put("reqtype", "GET_LIST_OF_COMPLAINT" );
			requestHeader.put("flowid", System.currentTimeMillis()+"" );
			requestHeader.put("channel", "MOBILE" );
			requestHeader.put("transdate", System.currentTimeMillis()+"" );
			requestHeader.put("servicetype", "CUSTOMER_COMPLAIN");
			requestHeader.put("userid", "AODUNAYO" );

			jrequest.put("requestheader", requestHeader);

			JSONObject requestBody = new JSONObject();

			JSONObject bankDataObj = new JSONObject();

			JSONObject extradataObj = new JSONObject();

			
			
			
			requestBody.put("bankdata" , bankDataObj );
			requestBody.put("extradata", extradataObj );

			jrequest.put("requestbody", requestBody);

			System.out.println("Request ::" + jrequest);
			JSONObject respJson = CustomerComplaintServiceCall.route(jrequest);
			System.out.println("Response Json ::"+respJson);


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	
	
	public static void regComplaint()
	{

		try
		{

			JSONObject jrequest = new JSONObject();

			JSONObject requestHeader = new JSONObject();
			requestHeader.put("reqtype", "REG_COMPLAINT" );
			requestHeader.put("flowid", System.currentTimeMillis()+"" );
			requestHeader.put("channel", "MOBILE" );
			requestHeader.put("transdate", System.currentTimeMillis()+"" );
			requestHeader.put("servicetype", "CUSTOMER_COMPLAIN");
			requestHeader.put("userid", "AODUNAYO" );

			jrequest.put("requestheader", requestHeader);

			JSONObject requestBody = new JSONObject();

			JSONObject bankDataObj = new JSONObject();
			bankDataObj.put("accountno", "0043163857");
			bankDataObj.put("dateoftrans", "14062021");
			bankDataObj.put("phoneno", "23408154115738");
			bankDataObj.put("email", "david@unionbank.com");
			bankDataObj.put("transAmount", "2000");
			bankDataObj.put("comments", "Money debited not credited");
			bankDataObj.put("complaintype", "NON_CARD_TRANS");
//			bankDataObj.put("cardType", "Master Card");
//			bankDataObj.put("firstFourdigits", "1234");
//			bankDataObj.put("lastfourdigits ", "7896");
			
			JSONObject extradataObj = new JSONObject();


			requestBody.put("bankdata" , bankDataObj );
			requestBody.put("extradata", extradataObj );

			jrequest.put("requestbody", requestBody);

			System.out.println("Request ::" + jrequest);
			JSONObject respJson = CustomerComplaintServiceCall.route(jrequest);
			System.out.println("Response Json ::"+respJson);


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
}

package com.ceva.unionbank.cust.complain.service.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

public class RequestManager implements CommonConstants {



	public static void logRequest(JSONObject request)
	{

		try {

			JSONObject requestHeader = request.getJSONObject("requestheader");
			String reqtype = requestHeader.has("reqtype")?requestHeader.getString("reqtype"):"";
			String flowid = requestHeader.has("flowid")? requestHeader.getString("flowid"):"";
			String channel = requestHeader.has("channel")? requestHeader.getString("channel"):"";
			// String transdate = requestHeader.has("transdate")? requestHeader.getString("transdate"):"";
			String userid = requestHeader.has("userid")? requestHeader.getString("userid"):"";	
			String servicetype = requestHeader.has("userid")? requestHeader.getString("userid"):"";	
			System.out.println("userid ["+userid+"] reqtype ["+reqtype+"] servicetype ["+servicetype+"] "
					+ " flowid["+flowid+"] channel["+channel+"]"
							// + " transdate["+transdate+"] "
					+ " Request ["+request+"]");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static JSONObject replaceEncriptedPin( JSONObject request )
	{


		try {

			if( isTagExtReqBody( request , "extradata" , "pin") )
			{
				String enpin = b64_sha256( getReqBodyValue(request, "extradata" , "pin" ) );
				JSONObject extraData =  request.getJSONObject("requestbody").getJSONObject("extradata");
				extraData.put("pin", enpin );
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return request ;

	}

	public static  String  b64_sha256(String inputString){ 

		String outputString="";

		if(inputString!=null){
			outputString=Base64.encodeBase64String(DigestUtils.sha256(inputString)).trim();
			//System.out.println("b64_sha256 outputString::"+outputString);
		}
		else{
			System.out.println("Input String Missing for b64_sha256");
		}
		outputString=outputString.substring(0, outputString.length()-1);

		return outputString;

	}

	public static String getReqBodyValue(JSONObject request , String typeOfreq ,String tagname)
	{

		String value="";

		try {

			JSONObject requestBodyObj = request.getJSONObject("requestbody");
			
			if(requestBodyObj.has(typeOfreq)) {
				
				JSONObject typeOfReqObj = requestBodyObj.getJSONObject(typeOfreq);
				value = typeOfReqObj.has(tagname)?typeOfReqObj.getString(tagname):"";
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return value;

	}
	
	public static void changeValue(JSONObject request , String typeOfreq ,String tagname , String updatevalue)
	{


		try {

			JSONObject requestBodyObj = request.getJSONObject("requestbody");
			
			if(requestBodyObj.has(typeOfreq)) {
				
				JSONObject typeOfReqObj = requestBodyObj.getJSONObject(typeOfreq);
				if(typeOfReqObj.has(tagname) ) 
					typeOfReqObj.put(tagname, updatevalue );
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}
	
	public static JSONObject getReqBody(JSONObject request , String typeOfreq)
	{

		JSONObject typeOfReqObj = new JSONObject();
		try {
			JSONObject requestBodyObj = request.getJSONObject("requestbody");
			if(requestBodyObj.has(typeOfreq)) {
				typeOfReqObj = requestBodyObj.getJSONObject(typeOfreq);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return typeOfReqObj;
	}

	public static boolean isTagExtReqBody(JSONObject request , String typeOfreq ,String tagname)
	{

		boolean value=false;

		try {

			JSONObject requestBodyObj = request.getJSONObject("requestbody");

			if(requestBodyObj.has(typeOfreq)) {
				JSONObject typeOfReqObj = requestBodyObj.getJSONObject(typeOfreq);
				value = typeOfReqObj.has(tagname);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return value;

	}

	public static String getReqHeaderValue(JSONObject request , String tagname)
	{

		String value="";

		try {

			JSONObject requestHeader = request.getJSONObject("requestheader");
			value = requestHeader.has(tagname)?requestHeader.getString(tagname):"";

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return value;

	}
	
	public static String getChannelCode( String channel  )
	{

		String channelcode="-";
		try {

			if("MOBILE".equals(channel))
			{
				channelcode="02";

			} else if("USSD".equals(channel))
			{
				channelcode="03";
			} else if("WEB".equals(channelcode))
			{
				channelcode="01";
			}

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return channelcode;

	}
	
	
	public static boolean getReqBodybooleanValue(JSONObject request , String typeOfreq ,String tagname)
	{

		boolean getvalue=false;
		try {

			JSONObject requestBodyObj = request.getJSONObject("requestbody");
			
			if(requestBodyObj.has(typeOfreq)) {
				
				JSONObject typeOfReqObj = requestBodyObj.getJSONObject(typeOfreq);
				getvalue = typeOfReqObj.has(tagname)?typeOfReqObj.getBoolean(tagname):false;
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return getvalue;

	}

	public static void main(String[] args) {

		try {

			JSONObject jrequest = new JSONObject();

			JSONObject requestHeader = new JSONObject();
			requestHeader.put("reqtype", "PAYDAYLOAN_STATUS" );
			requestHeader.put("flowid", System.currentTimeMillis()+"" );
			requestHeader.put("channel", "MOBILE" );
			requestHeader.put("transdate", System.currentTimeMillis()+"" );
			requestHeader.put("servicetype", "LOANS");
			requestHeader.put("userid", "sravan" );

			jrequest.put("requestheader", requestHeader);

			JSONObject requestBody = new JSONObject();

			JSONObject bankDataObj = new JSONObject();
			JSONObject extradataObj = new JSONObject();

			requestBody.put("bankdata" , bankDataObj );
			requestBody.put("extradata", extradataObj );

			jrequest.put("requestbody", requestBody);


			// logRequest(jrequest);

			System.out.println(getReqHeaderValue(jrequest, "channel"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}

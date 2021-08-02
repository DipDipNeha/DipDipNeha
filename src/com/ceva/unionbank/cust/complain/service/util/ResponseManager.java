package com.ceva.unionbank.cust.complain.service.util;

import org.json.JSONObject;

public class ResponseManager {
	
	
	public static JSONObject buildResponseJson ( String respcode , String respdesc , JSONObject responseJson ) {
		
		
		try {
			
			if(responseJson == null )
				responseJson = new JSONObject();
			
			responseJson.put( "respcode" , respcode  );
			responseJson.put( "respdesc" , respdesc );
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return responseJson;
		
	}

}

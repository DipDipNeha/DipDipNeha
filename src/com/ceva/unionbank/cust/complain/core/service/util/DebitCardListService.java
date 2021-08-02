/**
 * 
 */
package com.ceva.unionbank.cust.complain.core.service.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ceva.unionbank.services.core.ObjectCreatorImpl;
import com.ceva.unionbank.services.utils.Constants;
import com.ceva.unionbank.services.utils.PropertyReader;
import com.unionbank.channel.retail.controller.FullCardDetailList;
import com.unionbank.channel.retail.controller.FullCardDetails;
import com.unionbank.channel.retail.controller.ServiceControllers;
import com.unionbank.channel.retail.controller.ServiceControllers_Service;

/**
 * @author DipakKumar
 *
 */
public class DebitCardListService implements Constants{

	
	private static Logger log = Logger.getLogger(DebitCardListService.class);

	public static Map<String, String> map=null;
	
	static {
		map = PropertyReader.read();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static JSONObject getFullDebitCardsByAccountNo(JSONObject request) 
	{

		JSONObject responseJson = null;

		try {

			ServiceControllers_Service service = new ServiceControllers_Service(new URL(map.get(SERVICE_CONTROLLER_URL)));
			ServiceControllers port =	service.getServiceControllersPort();

			responseJson = new JSONObject();
			
			responseJson.put("respcode", "88");
			responseJson.put("respdesc", "Problem in Service");
			
			String userid = request.getString("userid") ;
			String actNo = request.getString("accountno");
			String channel = request.getString("channel");
			
			System.out.println( "UserId|"+userid+"|channel|"+channel+"|request|"+request );

			// List<CardAccountDetail>  responseList = port.getCardDetailsByAccountNo(actNo);

			FullCardDetailList response =  port.getFullCardDetailsByAccountNo(actNo);
			
			System.out.println("Response List |"+response);
			
			if( response != null )
			{
			
				List<FullCardDetails> responseList = response.getFullCardDetails();
				System.out.println("responseList list "+responseList.size() );
				
				if(responseList.size()!=0)
				{
					
					JSONArray jsonArray=new ObjectCreatorImpl().getJsonArray(responseList);
					responseJson.put("respcode", "00" );
					responseJson.put("respdesc", "SUCCESS" );
					// responseJson.put("deditcardlist", jsonArray );
					responseJson.put("creditcardlist", jsonArray);
					responseJson.put("pandet", jsonArray );
					
				}
				else
				{
					responseJson.put("respcode", "01");
					responseJson.put("respdesc", "No Data Found");
				}
				
			}else{
				
				responseJson.put("respcode", "02");
				responseJson.put("respdesc", "Something Went Wrong");
				
			}
			
			System.out.println("UserId|"+userid+"|Response - "+responseJson.toString());


		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return responseJson;
		
	}
	
	
	public static JSONObject getFullDebitCardsByUsername(JSONObject request) {

		JSONObject responseJson = null;

		try {

			ServiceControllers_Service service = new ServiceControllers_Service(new URL(map.get(SERVICE_CONTROLLER_URL)));
			ServiceControllers port =	service.getServiceControllersPort();

			responseJson = new JSONObject();
			
			responseJson.put("respcode", "88");
			responseJson.put("respdesc", "Problem in Service");
			String userid = request.getString("userid") ;
			
			System.out.println( "UserId|"+userid+"|request|"+request );

			List<FullCardDetails> responseList = port.getFullDebitCardsByUsername(userid);
			
			System.out.println("Response List |"+responseList);
			
			if( responseList != null )
			{
			
				System.out.println("responseList list "+responseList.size() );
				
				if(responseList.size()!=0)
				{
					JSONArray jsonArray=new ObjectCreatorImpl().getJsonArray(responseList);
					
					JSONArray jcardlist= new JSONArray();
					for (int i = 0; i < responseList.size() ; i++) {
						
						FullCardDetails carddata = responseList.get(i);
						
						JSONObject cardinfo = new JSONObject();
						cardinfo.put("pan", carddata.getPAN());
						cardinfo.put("expirydate", carddata.getExpiryDate() );
						cardinfo.put("type", carddata.getCardProgram());
						cardinfo.put("status", carddata.getStatus() );
						cardinfo.put("accountno", carddata.getAccountNumber());
						jcardlist.put(cardinfo);
						
					}
					
					
					responseJson.put("respcode", "00");
					responseJson.put("respdesc", "SUCCESS");
					responseJson.put("creditcardlist", jsonArray );
					responseJson.put("pandet", jcardlist);
					
				}
				else
				{
					responseJson.put("respcode", "01");
					responseJson.put("respdesc", "No Data Found");
				}
				
			}else{
				
				responseJson.put("respcode", "02");
				responseJson.put("respdesc", "Something Went Wrong");
				
			}
			
			System.out.println("UserId|"+userid+"|Response - "+responseJson.toString());


		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return responseJson;
		
	}
	
}

/**
 * 
 */
package com.ceva.unionbank.cust.complain.core.service.call;

import org.json.JSONException;
import org.json.JSONObject;

import com.ceva.unionbank.cust.complain.core.service.dao.CustomerComplainAudit;
import com.ceva.unionbank.cust.complain.core.service.util.CustomerComplaintService;
import com.ceva.unionbank.cust.complain.service.util.RequestManager;


/**
 * @author Dipak kumar
 *
 */
public class CustomerComplaintServiceCall {
	public static JSONObject route(JSONObject request)
	{

		JSONObject response = new JSONObject();

		try {

			RequestManager.replaceEncriptedPin(request);
			RequestManager.logRequest(request);
			String requestType = RequestManager.getReqHeaderValue(request, "reqtype");

			response.put("respcode", "99");
			response.put("respdesc", "INTERNAL ERROR");

			switch (requestType) 
			{


			case "REG_COMPLAINT":
			{
				response = CustomerComplaintService.registerComplaint(request);
			}
			break;
			case "GET_LIST_OF_CARD":
			{
				response = CustomerComplaintService.listOfCard(request);
			}
			break;
			case "GET_LIST_OF_COMPLAINT":
			{
				response = CustomerComplaintService.getListOfComplain(request);
			}
			break;
			
		
			
			default:
			{
				response.put("respcode", "88");
				response.put("respdesc", "UNKNOWN SERVICE REQUEST");
			}
			break;

			}

			CustomerComplainAudit.auditData(request, response);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return response;

	}
}

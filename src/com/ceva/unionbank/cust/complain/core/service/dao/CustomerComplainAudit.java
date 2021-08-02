/**
 * 
 */
package com.ceva.unionbank.cust.complain.core.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ceva.unionbank.cust.complain.service.util.RequestManager;
import com.ceva.unionbank.util.ConnectionHelper;

/**
 * @author kumar
 *
 */
public class CustomerComplainAudit {

	
	private static Logger logger = Logger.getLogger(CustomerComplainAudit.class);

	public static void auditData ( JSONObject request , JSONObject response ) {

		String channel = null;
		String userId=null, transCode=null , respCode=null , respDesc=null , message = null , ip=null , deviceDet1=null , det2=null , det3 = null  ;

		try {

			userId=RequestManager.getReqHeaderValue(request,"userid");
			channel=RequestManager.getReqHeaderValue(request,"channel");
			transCode=RequestManager.getReqHeaderValue(request,"reqtype");
			// String servicetype=RequestManager.getReqHeaderValue(request,"servicetype");
			// String transdate=RequestManager.getReqHeaderValue(request,"transdate");
			// String flowid=RequestManager.getReqHeaderValue(request,"flowid");

			respCode = response.has("respcode") ? response.getString("respcode") : "" ;
			respDesc = response.has("respdesc") ? response.getString("respdesc") : "";
			message = "Response Code ["+respCode+"] Response Message ["+respDesc+"]";
			ip = request.has("ip") ? request.getString("ip") : "" ;
			deviceDet1 = request.has("devicedet") ? request.getString("devicedet") : "";






			//	mobileAuditTrailInsert(userId , transCode , channel, message, ip, deviceDet1 , det2 , det3 );


			if( "MOBILE".equals(channel) ) {

				mobileAuditTrailInsert(userId , transCode , channel, message, ip, deviceDet1 , det2 , det3 );

			} else {

				ussdAuditTrailInsert(userId , transCode , channel, message, ip, deviceDet1 , det2, det3);

			}


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static JSONObject mobileAuditTrailInsert( String userid , String transCode , String channel ,
			String message , String ip , String det1 , String det2 , String det3  )
	{

		Connection connection = null;
		PreparedStatement pstmt = null;
		String auditQry=null;
		int i=0;		
		JSONObject responseJson = new JSONObject();
		PreparedStatement transdescPstmt=null;
		ResultSet transrs = null;
		String trascodedesc = null;

		try {

			connection =  ConnectionHelper.getConnection();

			auditQry= "INSERT INTO MOBILE_AUDIT_DATA(ID,TRANSCODE,TRANSCODE_DESC,TXNDATE,NET_ID,CHANNEL,MESSAGE,IP,DETAIL_1,DETAIL_2,DETAIL_3) "+
					" VALUES (TO_CHAR(SYSTIMESTAMP,'DDISSSSSFF'),?,?,sysdate,?,?,?,?,?,?,?)";


			logger.info("Audit Data Query ["+auditQry+"]");

			pstmt = connection.prepareStatement(auditQry);
			pstmt.setString(1,transCode); 
			pstmt.setString(2,trascodedesc);
			pstmt.setString(3, userid );
			pstmt.setString(4, channel); 
			pstmt.setString(5, message); 
			pstmt.setString(6,ip);
			pstmt.setString(7,det1);
			pstmt.setString(8,det2);
			pstmt.setString(9,det3);

			i = pstmt.executeUpdate();
			connection.commit();

			if (i == 1) {

				responseJson.put("status", "S");
				responseJson.put("message", "Successfully Inserted" );


			} else {

				responseJson.put("status", "E");
				responseJson.put("message", "Insertion failed due to some error" );

			}



		} catch (Exception ex) {

			try {

				responseJson.put("status", "E");
				responseJson.put("message","Internal Error Occured While Executing.");

				if(connection!=null)
					connection.rollback();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (Exception e) {

				// TODO: handle exception
				e.printStackTrace();

			}
			ex.printStackTrace();
			logger.error("Error Occured..!" + ex.getLocalizedMessage());

		} finally {

			ConnectionHelper.closePreparedStatement(pstmt);
			ConnectionHelper.closePreparedStatement(transdescPstmt);
			ConnectionHelper.closeResultSet(transrs);
			ConnectionHelper.closeConnection(connection);
			trascodedesc = null;
			auditQry=null;

		}

		return responseJson;

	}

	public static JSONObject ussdAuditTrailInsert(  String userid , String transCode , String channel ,
			String message , String ip , String det1 , String det2 , String det3 )
	{

		Connection connection = null;
		PreparedStatement pstmt = null;
		String auditQry=null;
		int i=0;		
		JSONObject responseJson = new JSONObject();
		PreparedStatement transdescPstmt=null;
		ResultSet transrs = null;
		String trascodedesc = null;

		try {

			connection =  ConnectionHelper.getConnection();

			auditQry= "INSERT INTO USSD_AUDIT_DATA(ID,TRANSCODE,TRANSCODE_DESC,TXNDATE,NET_ID,CHANNEL,MESSAGE,IP,DETAIL_1,DETAIL_2,DETAIL_3) "+
					" VALUES (TO_CHAR(SYSTIMESTAMP,'DDISSSSSFF'),?,?,sysdate,?,?,?,?,?,?,?)";


			logger.info("Audit Data Query ["+auditQry+"]");

			pstmt = connection.prepareStatement(auditQry);
			pstmt.setString(1,transCode); 
			pstmt.setString(2,trascodedesc);
			pstmt.setString(3, userid );
			pstmt.setString(4, channel); 
			pstmt.setString(5, message); 
			pstmt.setString(6,ip);
			pstmt.setString(7,det1);
			pstmt.setString(8,det2);
			pstmt.setString(9,det3);

			i = pstmt.executeUpdate();
			connection.commit();

			if (i == 1) {

				responseJson.put("status", "S");
				responseJson.put("message", "Successfully Inserted" );

			} else {

				responseJson.put("status", "E");
				responseJson.put("message", "Insertion failed due to some error" );

			}



		} catch (Exception ex) {

			try {

				responseJson.put("status", "E");
				responseJson.put("message","Internal Error Occured While Executing.");

				if(connection!=null)
					connection.rollback();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (Exception e) {

				// TODO: handle exception
				e.printStackTrace();

			}
			ex.printStackTrace();
			logger.error("Error Occured..!" + ex.getLocalizedMessage());

		} finally {

			ConnectionHelper.closePreparedStatement(pstmt);
			ConnectionHelper.closePreparedStatement(transdescPstmt);
			ConnectionHelper.closeResultSet(transrs);
			ConnectionHelper.closeConnection(connection);
			trascodedesc = null;
			auditQry=null;

		}

		return responseJson;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

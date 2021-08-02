/**
 * 
 */
package com.ceva.unionbank.cust.complain.core.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ceva.unionbank.cust.complain.service.util.RequestManager;
import com.ceva.unionbank.util.ConnectionHelper;

/**
 * @author DipakKumar
 *
 */
public class CustomerComplainDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

private static Logger logger = Logger.getLogger( CustomerComplainDao.class );
	
	public  static boolean  registerComplaint(JSONObject request,JSONObject response,String userid, String channel) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		 String reqtype="",complaintype="",respCode="",respDesc="",accountno="",transAmount="",comments="",firstFourdigits="",lastfourdigits ="",cardType="",phoneno="",email="",dateoftrans="";
		 boolean flag=false;
		int i=0;
		try {
			
			

			complaintype=request.has("complaintype")?request.getString("complaintype"):"";
			respDesc =response.has("respdesc")?response.getString("respdesc"):"";
			respCode=response.has("respcode")?response.getString("respcode"):"";
			accountno=request.has("accountno")?request.getString("accountno"):"";
			
			transAmount=request.has("transAmount")?request.getString("transAmount"):"";
			comments=request.has("comments")?request.getString("comments"):"";
			firstFourdigits=request.has("firstFourdigits")?request.getString("firstFourdigits"):"";
			lastfourdigits =request.has("lastfourdigits")?request.getString("lastfourdigits"):"";
			cardType=request.has("cardType")?request.getString("cardType"):"";
			phoneno=request.has("phoneno")?request.getString("phoneno"):"";
			email=request.has("email")?request.getString("email"):"";
			dateoftrans=request.has("dateoftrans")?request.getString("dateoftrans"):"";
			
			
			
			
			
			
			
			String query="INSERT INTO  CUSTOMER_COMPLAIN_TBL (SNO ,USERID ,CHANNEL ,COMPLAIN_DATE ,COMPLAIN_TYPE ,JREQUEST ," + 
					"JRESPONSE ,JRESPONSE_CODE ,JRESPONSE_MSG ,COMPLAIN_STATUS,ACCOUNT_NO,TRANSAMOUNT,COMMENTS,FIRSTFOURDIGITS,CARDTYPE,PHONENO,EMAIL,DATEOFTRANS,LASTFOURDIGITS ) values(CUSTOMER_COMPLAIN_TBL_SEQ.NEXTVAL,?,?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			connection = ConnectionHelper.getConnection();
			logger.info("customer complain Open   query"+query);
			pstmt=connection.prepareStatement(query);
			
			pstmt.setString(1, userid.toUpperCase());
			pstmt.setString(2, channel);
			pstmt.setString(3, complaintype);
			pstmt.setString(4, request.toString());
			pstmt.setString(5, response.toString());
			pstmt.setString(6, respCode);
			pstmt.setString(7,respDesc);
			pstmt.setString(8,"OPEN");
			pstmt.setString(9,accountno);
			
			pstmt.setString(10,transAmount);
			pstmt.setString(11,comments);
			pstmt.setString(12,firstFourdigits);
			
			pstmt.setString(13,cardType);
			pstmt.setString(14,phoneno);
			pstmt.setString(15,email);
			pstmt.setString(16,dateoftrans);
			pstmt.setString(17,lastfourdigits);
			
			
			
			
			
			
			i=pstmt.executeUpdate();
			
			if(i>=0) {
				logger.info("Customer Complain successfully inserted");
				flag=true;
			}
			
			
			
			
			
			connection.commit();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{

			ConnectionHelper.closePreparedStatement(pstmt);
			ConnectionHelper.closeConnection(connection);
		}
		return flag;
		
	}
	public  static JSONObject  getListOfComplain(JSONObject request,JSONObject response) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		 String userid="";
		 ResultSet rs=null;
		 JSONObject jresponse =null;
		try {
			response =new JSONObject();
			
			userid=RequestManager.getReqHeaderValue(request,"userid");
			
			
			String query="select COMPLAIN_STATUS,JREQUEST,ASSIGNED_AGENT,ASSIGNEDDATE,RESOLVED_DESC,COMPLAIN_DATE from   CUSTOMER_COMPLAIN_TBL  where upper(USERID)=?";
			connection = ConnectionHelper.getConnection();
			logger.info("customer complain Open   query"+query);
			pstmt=connection.prepareStatement(query);
			
			pstmt.setString(1, userid.toUpperCase());
			rs=pstmt.executeQuery();
			
			JSONArray jsonArray=new JSONArray();
			jresponse=new JSONObject();
			while(rs.next()) {
				String jrequest= rs.getString("JREQUEST");
				JSONObject jsonObject=new  JSONObject(jrequest);
				String complainStatus= rs.getString("COMPLAIN_STATUS");
				

		         String resolvedComment=rs.getString("RESOLVED_DESC");
		         
		         if(resolvedComment==null) {
		        	 resolvedComment="";
		         }
		         
				String complainDate=String.valueOf(rs.getDate("COMPLAIN_DATE"));
				System.out.println(complainStatus);

				jsonObject.put("complainStatus", complainStatus);
		       jsonObject.put("resolvedComment", resolvedComment);
				jsonObject.put("complainDate", complainDate);
				
				jsonArray.put(jsonObject);
				
			}

			if(jsonArray.length()>0) {
				jresponse.put("respcode", "00");
				jresponse.put("respdesc", "SUCCESS");
				jresponse.put("complain",jsonArray);
			}
			
			
			 else {
				 jresponse.put("respcode", "02");
				 jresponse.put("respdesc", "NO DATA FOUND");
				}
			
			
         	logger.info("List of complain "+jresponse);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{

			ConnectionHelper.closePreparedStatement(pstmt);
			ConnectionHelper.closeConnection(connection);
			ConnectionHelper.closeResultSet(rs);
			
		}
		return jresponse;
		
		
	}

}

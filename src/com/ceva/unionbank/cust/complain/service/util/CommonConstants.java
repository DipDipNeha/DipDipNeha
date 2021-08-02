package com.ceva.unionbank.cust.complain.service.util;

public interface CommonConstants {

	// Request tag names
	
	String reqtype = "reqtype";
	String flowid = "flowid";
	String channel = "channel";
	String transdate = "transdate";
	String servicetype = "servicetype";
	String userid = "userid";
	
	String requestheader="requestheader";
	String requestbody = "requestbody";
	
	String bankdata="bankdata";
	String extradata="extradata";
	
	// Error Messages
	
	
	String FINANCIAL="FINANCIAL";
	String NONFINANCIAL="NONFINANCIAL";

   // financial
	String serverip="10.65.0.19";
	String UBN_SUNU_KAMPE="UBN_SUNU_KAMPE";
	
	String PAY_BILL ="PAY_BILL";
	
	
	String SUCCESS_RESP_CODE="00";
	String TRANSACTIONCURRENCY="NGN";
	String EXP = "EXP";
	String NO_EXP = "NO_EXP";
	String EXP_STAT="EXP_STAT";
	
}

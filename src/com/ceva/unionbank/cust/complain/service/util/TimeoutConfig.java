package com.ceva.unionbank.cust.complain.service.util;

import java.util.HashMap;

public enum TimeoutConfig {

	// NON FINNANICAL
	NONFINANCIAL(300000,300000),

	// FINNANICAL
	FINNANICAL(30000,30000),
	FUND_TRANS(30000,30000),
	FUND_TRANS_OTH(30000,30000);

	private int UssdValue=30000;
	private int MobileValue=30000;

	TimeoutConfig(int UssdValue , int MobileValue)
	{
		this.UssdValue = UssdValue;
		this.MobileValue = MobileValue;
	}

	public int getUssdValue() {
		return UssdValue;
	}

	public int getMobileValue() {
		return MobileValue;
	}

	public static int getTimeoutValue(HashMap<String, String> reqObj)
	{

		int timeoutValue = 3000;

		try {

			String channel = reqObj.get("channel");
			String serviceCode = reqObj.get("servicecode");
			
			if("MOBILE".equals(channel))
				timeoutValue = TimeoutConfig.valueOf(serviceCode).getMobileValue();
			else 
				timeoutValue = TimeoutConfig.valueOf(serviceCode).getUssdValue();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return timeoutValue;

	}
	public static void main ( String[] args ) {
		
		System.out.println("Welcome to test Program ");
		
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("channel", "MOBILE"  );
		hashMap.put("servicecode", "NONFINANCIAL");
		hashMap.put("channel", "USSD"  );
		
		// System.out.println( getTimeoutValue(  hashMap ) );
		
		hashMap.put("channel", "MOBILE"  );
		hashMap.put("servicecode", "FUND_TRANS");
		// hashMap.put("channel", "USSD"  );
		
		System.out.println( getTimeoutValue(  hashMap ) );
		
		
		System.out.println("End of test program ");
	}
}

package com.ceva.unionbank.cust.complain.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.JSONObject;

public class CommonHelper {

	/**
	 * 	WEB = 01,
		MOBILE   = 02,
		USSD = 03.

	 */

	public static String getChannelCode( JSONObject request)
	{

		String channelcode="-";
		try {

			String channel = request.getString("channel");

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
			} else 
			{
				channelcode="02";
			}

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return channelcode;

	}

	
	public static String getPaymentRefNo()
	{
		String result = null;
		try {
			
			Random random = new Random(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("mmss");
			result ="1"+sdf.format(new Date())+
					(Math.abs(new Random(Math.abs(random.nextLong())).nextLong())%100000L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static String getCurrentDate()
	{
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		// System.out.println(date);
		return date;
		
		
	}
	
	public static void main(String[] args) {
		
		try {
			
			String payRef = getPaymentRefNo();
			
			System.out.println("getPaymentRefNo ::"  + payRef + "| length |" +payRef.length() );
			
			// System.out.println(getCurrentDate());
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}

package com.span.expressextension8868.businesslogic.parsing;

import org.json.JSONObject;

import java.util.ArrayList;

public class Deleteparsing
{
	String osField,Uid,EM;
	
	ArrayList<String> message=new ArrayList<String>();
	
	public ArrayList<String> parse(String jsstring)
	{
		 if (jsstring != null) 
		    {
				try
				{
					JSONObject jsonObj = new JSONObject(jsstring);
					
					osField=jsonObj.getString("OS");

					EM=jsonObj.getString("EM");
					
					message.add(osField);
					
					message.add(EM);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
		    }
		   
		return message;
		
	}
}

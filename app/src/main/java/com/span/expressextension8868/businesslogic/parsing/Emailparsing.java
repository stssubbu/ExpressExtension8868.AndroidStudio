package com.span.expressextension8868.businesslogic.parsing;

import org.json.JSONObject;

public class Emailparsing
{
	String osField,EM;
	
	String returnobject;
	
	public String parse(String jsstring)
	{
		 if (jsstring != null) 
		    {
				try
				{
					System.out.println("Email Response String " + jsstring);

					JSONObject jsonObj = new JSONObject(jsstring);

					osField = jsonObj.getString("OS");

					EM = jsonObj.getString("EM");
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
		    }
		   
		return osField;
		
	}
}

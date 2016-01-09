package com.span.expressextension8868.businesslogic.parsing;


import com.span.expressextension8868.model.core.HoldingModel;

import org.json.JSONObject;

public class SaveHoldingCompanyDetailsParsing
{
	String OS,EM;
	
	HoldingModel holdingModelSave=new HoldingModel();
	
	public HoldingModel parse(String jsstring)
	{
		 if (jsstring != null) 
		    {
				try
				{
					JSONObject jsonObj = new JSONObject(jsstring);
					
					OS=jsonObj.getString("OS");
					
					EM=jsonObj.getString("EM");
					
					holdingModelSave.setOS(OS);
					
					holdingModelSave.setEM(EM);
					
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
		    }
		   
		return holdingModelSave;
		
	}
}

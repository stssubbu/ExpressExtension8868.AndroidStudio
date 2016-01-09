package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.HoldingModel;

import org.json.JSONObject;

public class GetHoldingCompanyCountParsing
{
	String OS,EM,HCC;
	
	HoldingModel holdingcompanycountModel=new HoldingModel();
	
	public HoldingModel parse(String jsstring)
	{
		 if (jsstring != null) 
		    {
				try
				{
					JSONObject jsonObj = new JSONObject(jsstring);
					
					OS=jsonObj.getString("OS");
					
					EM=jsonObj.getString("EM");
					
					HCC=jsonObj.getString("HCC");
					
					holdingcompanycountModel.setOS(OS);
					
					holdingcompanycountModel.setEM(EM);
					
					holdingcompanycountModel.setHCCount(HCC);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
		    }
		   
		return holdingcompanycountModel;
		
	}
}

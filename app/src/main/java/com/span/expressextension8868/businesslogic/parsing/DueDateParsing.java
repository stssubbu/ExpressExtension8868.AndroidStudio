package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.DatesModel;

import org.json.JSONObject;

public class DueDateParsing
{
	String ActualDueDate,ExtendedDueDate,ExtensionType;
	
	DatesModel datesModel=new DatesModel();
	
	public DatesModel parse(String jsstring)
	{
		 if (jsstring != null) 
		    {
				try
				{
					JSONObject jsonObj = new JSONObject(jsstring);
					
					ActualDueDate=jsonObj.getString("ACDD");
					
					ExtendedDueDate=jsonObj.getString("EXDD");
					
					ExtensionType=jsonObj.getString("ExtensionType");
					
					datesModel.setActualDueDate(ActualDueDate);
					
					datesModel.setExtendedDueDate(ExtendedDueDate);
					
					datesModel.setExtensionType(ExtensionType);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
		    }
		   
		return datesModel;
		
	}
}

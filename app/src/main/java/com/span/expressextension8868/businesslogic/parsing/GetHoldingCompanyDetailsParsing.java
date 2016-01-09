package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.HoldingModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;

public class GetHoldingCompanyDetailsParsing
{
	String RID,HoldingCompanyId,HoldingCompanyName,HoldingCompanyAddress1,HoldingCompanyAddress2, HoldingCompanyCity,HoldingCompanyState,HoldingCompanyCountry, HoldingCompanyZip,HoldingCompanyEIN;
	
	String HoldingCompanyIsForeignAddress,HoldingCompanyStateId,HoldingCompanyCountryId,OS,LGT,EM;
	
	Vector<HoldingModel> saveHoldingcompanyparsingVector=new Vector<HoldingModel>();
	
	HoldingModel holdingModelSave=new HoldingModel();
	
	public HoldingModel parse(String jsstring)
	{
		 if (jsstring != null) 
		    {
				try
				{
					JSONObject jsonObj = new JSONObject(jsstring);
					
					JSONArray jsonarrayObj = new JSONArray();
					
					jsonarrayObj = jsonObj.getJSONArray("HoldingCompaniesList");
					
					OS=jsonObj.getString("OS");
					
					EM=jsonObj.getString("EM");
					
					RID=jsonObj.getString("RID");
					
					holdingModelSave.setOS(OS);
					
					holdingModelSave.setEM(EM);
					
					holdingModelSave.setRID(RID);
					
					for (int x = 0; x < jsonarrayObj.length(); x++)
					{
						
					HoldingCompanyId=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyId");
					
					HoldingCompanyName=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyName");
					
					HoldingCompanyAddress1=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyAddress1");
					
					HoldingCompanyAddress2=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyAddress2");
					
					HoldingCompanyCity=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyCity");
					
					HoldingCompanyState=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyState");
					
					HoldingCompanyStateId=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyStateId");
					
					HoldingCompanyCountry=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyCountry");
					
					HoldingCompanyZip=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyZip");
					
					HoldingCompanyEIN=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyEIN");
					
					HoldingCompanyIsForeignAddress=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyIsForeignAddress");
					
					HoldingCompanyCountryId=jsonarrayObj.getJSONObject(x).getString("HoldingCompanyCountryId");
					
					HoldingModel holdingModel=new HoldingModel();
					
					
					holdingModel.setHoldingCompanyId(HoldingCompanyId);
					
					holdingModel.setHoldingCompanyName(HoldingCompanyName);
					
					holdingModel.setHoldingCompanyAddress1(HoldingCompanyAddress1);
					
					holdingModel.setHoldingCompanyAddress2(HoldingCompanyAddress2);
					
					holdingModel.setHoldingCompanyCity(HoldingCompanyCity);
					
					holdingModel.setHoldingCompanyState(HoldingCompanyState);
					
					holdingModel.setHoldingCompanyStateId(HoldingCompanyStateId);
					
					holdingModel.setHoldingCompanyZip(HoldingCompanyZip);
					
					holdingModel.setHoldingCompanyEIN(HoldingCompanyEIN);
					
					holdingModel.setHoldingCompanyCountry(HoldingCompanyCountry);
					
					holdingModel.setHoldingCompanyCountryId(HoldingCompanyCountryId);
					
					holdingModel.setHoldingCompanyIsForeignAddress(HoldingCompanyIsForeignAddress);
					
					saveHoldingcompanyparsingVector.add(holdingModel);
					
					}
					
					holdingModelSave.setHoldingmodelVector(saveHoldingcompanyparsingVector);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
		    }
		   
		return holdingModelSave;
		
	}
}

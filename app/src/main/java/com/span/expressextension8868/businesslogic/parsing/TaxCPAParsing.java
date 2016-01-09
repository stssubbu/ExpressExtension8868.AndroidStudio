package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.TaxCPAModel;

import org.json.JSONObject;

import java.util.Vector;

public class TaxCPAParsing
{
	String OS;
	
	String FN,LN,FIRN,PTIN,ADD1,ADD2,CTY,SID,CID,ZIP,PH,ISSEM,EIN,EA,FAX,TZID,AT,DID,UID,TPREID,EM,ISFORADD,SN;
	
	Vector<TaxCPAModel> taxCPAVector=new Vector<TaxCPAModel>();
	
	public Vector<TaxCPAModel> parse(String jsstring)
	{
		if (jsstring != null) 
	    {
			try
			{
				JSONObject jsonObj = new JSONObject(jsstring);
				
				OS=jsonObj.getString("OS");
				
				FN=jsonObj.getString("FN");
				
				LN=jsonObj.getString("LN");
				
				FIRN=jsonObj.getString("FIRN");
				
				PTIN=jsonObj.getString("PTIN");
				
				ADD1=jsonObj.getString("ADD1");
				
				ADD2=jsonObj.getString("ADD2");
				
				CTY=jsonObj.getString("CTY");
				
				SID=jsonObj.getString("SID");
				
				ZIP=jsonObj.getString("ZIP");
				
				ISSEM=jsonObj.getString("ISSEM");
				
				EIN=jsonObj.getString("EIN");
				
				EA=jsonObj.getString("EA");
				
				EM=jsonObj.getString("EM");
				
				ISFORADD=jsonObj.getString("ISFORADD");
				
				TPREID=jsonObj.getString("TPREID");
				
				CID=jsonObj.getString("CID");
				
				SN=jsonObj.getString("SN");
				
				
				TaxCPAModel taxCPAModel=new TaxCPAModel();
				
				taxCPAModel.setOS(OS);
				
				taxCPAModel.setFN(FN);
				
				taxCPAModel.setLN(LN);
				
				taxCPAModel.setFIRN(FIRN);
				
				taxCPAModel.setPTIN(PTIN);
				
				taxCPAModel.setADD1(ADD1);
				
				taxCPAModel.setADD2(ADD2);
				
				taxCPAModel.setCTY(CTY);
				
				taxCPAModel.setSID(SID);
				
				taxCPAModel.setCID(CID);
				
				taxCPAModel.setZIP(ZIP);
				
				taxCPAModel.setISSEM(ISSEM);
				
				taxCPAModel.setEIN(EIN);
				
				taxCPAModel.setEA(EA);
				
				taxCPAModel.setTPREID(TPREID);
				
				taxCPAModel.setEM(EM);
				
				taxCPAModel.setISFORADD(ISFORADD);
				
				taxCPAModel.setSN(SN);
				
				taxCPAVector.add(taxCPAModel);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
	    }
	   
	return taxCPAVector;
	
}
}

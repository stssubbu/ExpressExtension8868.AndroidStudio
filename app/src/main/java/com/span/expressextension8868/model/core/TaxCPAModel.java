package com.span.expressextension8868.model.core;

import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class TaxCPAModel 
{

	String FN,LN,FIRN,PTIN,ADD1,ADD2,CTY,SID,ZIP,PH,ISSEM,EIN,EA,FAX,TZID,AT,DID,UID,OS,TPREID,EM,SN,CID,ISFORADD;

	public String getISFORADD() {
		return ISFORADD;
	}

	public void setISFORADD(String iSFORADD) {
		ISFORADD = iSFORADD;
	}

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}

	public String getEM() {
		return EM;
	}

	public void setEM(String eM) {
		EM = eM;
	}

	public String getTPREID() {
		return TPREID;
	}

	public void setTPREID(String tPREID) {
		TPREID = tPREID;
	}

	public String getOS() {
		return OS;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public String getAT() {
		return AT;
	}

	public void setAT(String aT) {
		AT = aT;
	}

	public String getDID() {
		return DID;
	}

	public void setDID(String dID) {
		DID = dID;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public String getTZID() {
		return TZID;
	}

	public void setTZID(String tZID) {
		TZID = tZID;
	}

	public String getFN() {
		return FN;
	}

	public void setFN(String fN) {
		FN = fN;
	}

	public String getLN() {
		return LN;
	}

	public void setLN(String lN) {
		LN = lN;
	}

	public String getFIRN() {
		return FIRN;
	}

	public void setFIRN(String fIRN) {
		FIRN = fIRN;
	}

	public String getPTIN() {
		return PTIN;
	}

	public void setPTIN(String pTIN) {
		PTIN = pTIN;
	}

	public String getADD1() {
		return ADD1;
	}

	public void setADD1(String aDD1) {
		ADD1 = aDD1;
	}

	public String getADD2() {
		return ADD2;
	}

	public void setADD2(String aDD2) {
		ADD2 = aDD2;
	}

	public String getCTY() {
		return CTY;
	}

	public void setCTY(String cTY) {
		CTY = cTY;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	public String getZIP() {
		return ZIP;
	}

	public void setZIP(String zIP) {
		ZIP = zIP;
	}

	public String getPH() {
		return PH;
	}

	public void setPH(String pH) {
		PH = pH;
	}

	public String getISSEM() {
		return ISSEM;
	}

	public void setISSEM(String iSSEM) {
		ISSEM = iSSEM;
	}

	public String getEIN() {
		return EIN;
	}

	public void setEIN(String eIN) {
		EIN = eIN;
	}

	public String getEA() {
		return EA;
	}

	public void setEA(String eA) {
		EA = eA;
	}

	public String getFAX() {
		return FAX;
	}

	public void setFAX(String fAX) {
		FAX = fAX;
	}
	
	public String getJSONObj()
	{
		String jsonstring=null;
		
		JSONObject obj=new JSONObject();
		
		JSONObject obj1=new JSONObject();
		
		try 
		{
			
			obj.put("TPREID",this.TPREID);
			
			obj.put("FN",this.FN);
			
			obj.put("LN",this.LN);
			
			obj.put("FIRN",this.FIRN);
			
			obj.put("PTIN",this.PTIN);
			
			obj.put("ADD1",this.ADD1);
			
			obj.put("ADD2",this.ADD2);
			
			obj.put("CTY",this.CTY);
			
			obj.put("SID",this.SID);
			
			obj.put("ISSEM",this.ISSEM);
			
			obj.put("EIN",this.EIN);
			
			obj.put("EA",this.EA);
			
			obj.put("AT",this.AT);
			
			obj.put("DId",this.DID);
			
			obj.put("UId",this.UID);
			
			obj.put("CID",this.CID);
			
			obj.put("SN",this.SN);
			
			obj.put("ZIP",this.ZIP);
			
			obj.put("ISFORADD",this.ISFORADD);
			
			obj1.put("TaxPreparer", obj);
			
			jsonstring=obj1.toString();
			
			Log.i("Request JSON CPA ","Request JSON CPA "+jsonstring);
			Log.i("Request URL ","Request URL "+ ApplicationConfig.UPDATE_CPA_PROFILE);
			
		}
		catch (JSONException e) 
		{
		
			e.printStackTrace();
		}
		
		return jsonstring;
	}
	
	
	public String getEditJSONObj()
	{
		String jsonstring=null;
		
		JSONObject obj=new JSONObject();
		
		JSONObject obj1=new JSONObject();
		
		try 
		{
			
			obj.put("AT",AT);
			
			obj.put("DId",DID);
			
			obj.put("UId",UID);
			
			obj1.put("TaxPreparer", obj);
			
			jsonstring=obj1.toString();
			
			System.out.println("Request JSON CPA "+jsonstring);
			
		}
		catch (JSONException e) 
		{
		
			e.printStackTrace();
		}
		
		return jsonstring;
	}
	
}

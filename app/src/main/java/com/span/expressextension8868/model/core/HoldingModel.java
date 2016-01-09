package com.span.expressextension8868.model.core;

import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;

import org.json.JSONObject;

import java.util.Vector;

public class HoldingModel
{

	String HoldingCompanyId,HoldingCompanyName,HoldingCompanyAddress1,HoldingCompanyAddress2, HoldingCompanyCity,HoldingCompanyState,HoldingCompanyCountry, HoldingCompanyZip,HoldingCompanyEIN,IsNoEIN;
	
	String HoldingCompanyIsForeignAddress,HoldingCompanyStateId,MobNoEINReason,OS,LGT,EM,HoldingCompanyCountryId;
	
	String AT,DID,UID,RID,HCCount,EODId;
	
	public String getEODId()
	{
		return EODId;
	}

	public void setEODId(String eODId)
	{
		EODId = eODId;
	}

	public String getHCCount() {
		return HCCount;
	}

	public void setHCCount(String hCCount) {
		HCCount = hCCount;
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

	Vector<HoldingModel> holdingmodelVector;
	

	public String getHoldingCompanyCountryId() {
		return HoldingCompanyCountryId;
	}

	public void setHoldingCompanyCountryId(String holdingCompanyCountryId) {
		HoldingCompanyCountryId = holdingCompanyCountryId;
	}

	public Vector<HoldingModel> getHoldingmodelVector() {
		return holdingmodelVector;
	}

	public void setHoldingmodelVector(Vector<HoldingModel> holdingmodelVector) {
		this.holdingmodelVector = holdingmodelVector;
	}

	public String getHoldingCompanyIsForeignAddress() {
		return HoldingCompanyIsForeignAddress;
	}

	public void setHoldingCompanyIsForeignAddress(
			String holdingCompanyIsForeignAddress) {
		HoldingCompanyIsForeignAddress = holdingCompanyIsForeignAddress;
	}

	public String getHoldingCompanyStateId() {
		return HoldingCompanyStateId;
	}

	public void setHoldingCompanyStateId(String holdingCompanyStateId) {
		HoldingCompanyStateId = holdingCompanyStateId;
	}

	public String getOS() {
		return OS;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public String getLGT() {
		return LGT;
	}

	public void setLGT(String lGT) {
		LGT = lGT;
	}

	public String getEM() {
		return EM;
	}

	public void setEM(String eM) {
		EM = eM;
	}

	public String getRID() {
		return RID;
	}

	public void setRID(String rID) {
		RID = rID;
	}

	public String getHoldingCompanyId() {
		return HoldingCompanyId;
	}

	public void setHoldingCompanyId(String holdingCompanyId) {
		HoldingCompanyId = holdingCompanyId;
	}

	public String getHoldingCompanyName() {
		return HoldingCompanyName;
	}

	public void setHoldingCompanyName(String holdingCompanyName) {
		HoldingCompanyName = holdingCompanyName;
	}

	public String getHoldingCompanyAddress1() {
		return HoldingCompanyAddress1;
	}

	public void setHoldingCompanyAddress1(String holdingCompanyAddress1) {
		HoldingCompanyAddress1 = holdingCompanyAddress1;
	}

	public String getHoldingCompanyAddress2() {
		return HoldingCompanyAddress2;
	}

	public void setHoldingCompanyAddress2(String holdingCompanyAddress2) {
		HoldingCompanyAddress2 = holdingCompanyAddress2;
	}

	public String getHoldingCompanyCity() {
		return HoldingCompanyCity;
	}

	public void setHoldingCompanyCity(String holdingCompanyCity) {
		HoldingCompanyCity = holdingCompanyCity;
	}

	public String getHoldingCompanyState() {
		return HoldingCompanyState;
	}

	public void setHoldingCompanyState(String holdingCompanyState) {
		HoldingCompanyState = holdingCompanyState;
	}

	public String getHoldingCompanyCountry() {
		return HoldingCompanyCountry;
	}

	public void setHoldingCompanyCountry(String holdingCompanyCountry) {
		HoldingCompanyCountry = holdingCompanyCountry;
	}

	public String getHoldingCompanyZip() {
		return HoldingCompanyZip;
	}

	public void setHoldingCompanyZip(String holdingCompanyZip) {
		HoldingCompanyZip = holdingCompanyZip;
	}

	public String getHoldingCompanyEIN() {
		return HoldingCompanyEIN;
	}

	public void setHoldingCompanyEIN(String holdingCompanyEIN) {
		HoldingCompanyEIN = holdingCompanyEIN;
	}

	public String getHoldingCompanySaveRequest()
	{
		JSONObject obj=new JSONObject();
		JSONObject obj1=new JSONObject();
		
		try 
		{
			obj.put("AT",this.AT);
			obj.put("DId",this.DID);
			obj.put("UId",this.UID);
			obj.put("RID",this.RID);
			obj.put("EODId",this.EODId);
			obj.put("OS",this.OS);
			obj.put("HoldingCompanyId",this.HoldingCompanyId);
			obj.put("HoldingCompanyAddress1",this.HoldingCompanyAddress1);
			obj.put("HoldingCompanyAddress2",this.HoldingCompanyAddress2);
			obj.put("HoldingCompanyCity",this.HoldingCompanyCity);
			obj.put("HoldingCompanyCountryId",this.HoldingCompanyCountryId);
			obj.put("HoldingCompanyName",this.HoldingCompanyName);
			obj.put("HoldingCompanyEIN",this.HoldingCompanyEIN);
			obj.put("HoldingCompanyIsForeignAddress",this.HoldingCompanyIsForeignAddress);
			obj.put("HoldingCompanyStateId",this.HoldingCompanyStateId);
			obj.put("HoldingCompanyState",this.HoldingCompanyState);
			obj.put("HoldingCompanyZip",this.HoldingCompanyZip);
			obj.put("LGT",this.LGT);
			obj1.put("F8868POG", obj);
			
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		
		}
		Log.i("Save Holding Request", obj1.toString());
		//Log.i("Save Holding URL", ApplicationConfig.UPDATEFORM7004HOLDINGCOMPANYDETAILS);
		
		return obj1.toString();
	}
	
	
	public String getHoldingCompanyDeleteRequest()
	{
		JSONObject obj=new JSONObject();
		JSONObject obj1=new JSONObject();
		
		try 
		{
			obj.put("AT",this.AT);
			obj.put("DId",this.DID);
			obj.put("UId",this.UID);
			obj.put("LGT","DELETE");
			obj.put("HoldingCompanyId",this.HoldingCompanyId);
			obj1.put("F8868POG", obj);
			
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		
		}
		Log.i("Delete Holding Request", obj1.toString());
		//Log.i("Delete Holding URL", ApplicationConfig.UPDATEFORM7004HOLDINGCOMPANYDETAILS);
		
		return obj1.toString();
	}
	
	
	public String getHoldingListRequestByRID()
	{
		JSONObject obj=new JSONObject();
		JSONObject obj1=new JSONObject();
		
		try 
		{
			obj.put("AT",this.AT);
			obj.put("DId",this.DID);
			obj.put("UId",this.UID);
			obj.put("RID",this.RID);
			obj.put("EODId",this.EODId);
			obj1.put("Return", obj);
		}
		
		catch (Exception e) 
		{
			
			e.printStackTrace();
		
		}
		Log.i("Get Holding Request", obj1.toString());
		
		Log.i("Ger Holding List URL", ApplicationConfig.GET8868PARTOFGROUPLISTBYRETURNID);
		
		return obj1.toString();
	}
	
	
}




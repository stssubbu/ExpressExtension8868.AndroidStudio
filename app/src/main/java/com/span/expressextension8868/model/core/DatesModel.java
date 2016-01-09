package com.span.expressextension8868.model.core;

import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DatesModel
{

	String TYBD,TYED,FORMCODEID,ISUR,TY;
	
	String ActualDueDate,ExtendedDueDate,IsShortTaxYear,ExtensionType;
	
	public String getTY()
	{
		return TY;
	}

	public void setTY(String tY)
	{
		TY = tY;
	}

	public String getActualDueDate() {
		return ActualDueDate;
	}

	public void setActualDueDate(String actualDueDate) {
		ActualDueDate = actualDueDate;
	}

	public String getExtendedDueDate() {
		return ExtendedDueDate;
	}

	public void setExtendedDueDate(String extendedDueDate) {
		ExtendedDueDate = extendedDueDate;
	}

	public String getIsShortTaxYear() {
		return IsShortTaxYear;
	}

	public void setIsShortTaxYear(String isShortTaxYear) {
		IsShortTaxYear = isShortTaxYear;
	}

	public String getTYBD() {
		return TYBD;
	}

	public void setTYBD(String tYBD) {
		TYBD = tYBD;
	}

	public String getTYED() {
		return TYED;
	}

	public void setTYED(String tYED) {
		TYED = tYED;
	}

	public String getFORMCODEID() {
		return FORMCODEID;
	}

	public void setFORMCODEID(String fORMCODEID) {
		FORMCODEID = fORMCODEID;
	}

	public String getISUR() {
		return ISUR;
	}

	public void setISUR(String iSUR) {
		ISUR = iSUR;
	}
	
	public String getExtensionType()
	{
		return ExtensionType;
	}

	public void setExtensionType(String extensionType)
	{
		ExtensionType = extensionType;
	}

	public String getDuedateRequest()
	{
		JSONObject obj=new JSONObject();
		JSONObject obj1=new JSONObject();
		try 
		{
			obj.put("TYED", this.TYED);
			obj.put("FCID", this.FORMCODEID);
			obj.put("FC", this.FORMCODEID);
			obj.put("ExtensionType", this.ExtensionType);
			obj.put("TY", this.TY);
			obj1.put("MobDueDate", obj);
		}
		catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		Log.i("Date Request", obj1.toString());
		
		Log.i("Date Request URL", ApplicationConfig.GET8868DUEDATE);
		
		return obj1.toString();
	}
	
}


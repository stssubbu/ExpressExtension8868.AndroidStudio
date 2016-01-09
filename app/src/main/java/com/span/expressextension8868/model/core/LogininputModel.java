package com.span.expressextension8868.model.core;

import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class LogininputModel 
{

	String username,password,UQId,DN,DOS,MDN,MOD,Ver;

	public String getVer() {
		return Ver;
	}

	public void setVer(String ver) {
		Ver = ver;
	}

	public String getUQId() {
		return UQId;
	}

	public void setUQId(String dId) {
		UQId = dId;
	}

	public String getDN() {
		return DN;
	}

	public void setDN(String dN) {
		DN = dN;
	}

	public String getDOS() {
		return DOS;
	}

	public void setDOS(String dOS) {
		DOS = dOS;
	}

	public String getMDN() {
		return MDN;
	}

	public void setMDN(String mDN) {
		MDN = mDN;
	}

	public String getMOD() {
		return MOD;
	}

	public void setMOD(String mOD) {
		MOD = mOD;
	}

	public String getusername() 
	{
		return username;
	}

	public String getpassword()
	{
		return password;
	}
	public void setuserpassforobj(String uname,String pass) 
	{
		username = uname;
		password=pass;
	}
	public String getjsonobj()
	{
		String jsonstring = null;

		try
		{
			JSONObject obj = new JSONObject();

			JSONObject obj1=new JSONObject();
			
			obj.put("UN",username);
			
			obj.put("PWD", password);
			
			obj.put("UQId", this.UQId);
			
			obj.put("DN","5");
			
			obj.put("DOS",this.DOS);
			
			obj.put("MDN",this.MDN);	
			
			obj.put("MOD",this.MOD);
			
			obj.put("Ver",this.Ver);
			
			obj.put("COM","ANDROID");
			
			obj.put("APPTYPE","3");
			
			obj1.put("UserLogin",obj);
			
			jsonstring=obj1.toString();
			
			Log.i("LoginRequest", "LoginRequest "+jsonstring);
			
			Log.i("LoginURL", "LoginURL "+ ApplicationConfig.GET_USER_FOR_SIGNIN);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonstring;
	}
	
}

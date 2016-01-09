package com.span.expressextension8868.model.core;

import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupinputModel 
{

	String emailaddress, password, contactname, phonenumber, howufindus, usertype,MDN,DN,UQID,DOS,Ver,COM,MOD;
	
	public String getMDN() 
	{
		return MDN;
	}

	public void setMDN(String mDN) 
	{
		MDN = mDN;
	}

	public String getDN() 
	{
		return DN;
	}

	public void setDN(String dN) 
	{
		DN = dN;
	}

	public String getUQID() 
	{
		return UQID;
	}

	public void setUQID(String uQID) 
	{
		UQID = uQID;
	}

	public String getDOS() 
	{
		return DOS;
	}

	public void setDOS(String dOS) 
	{
		DOS = dOS;
	}

	public String getVer() 
	{
		return Ver;
	}

	public void setVer(String ver) 
	{
		Ver = ver;
	}

	public String getCOM() 
	{
		return COM;
	}

	public void setCOM(String cOM) 
	{
		COM = cOM;
	}

	public String getMOD() 
	{
		return MOD;
	}

	public void setMOD(String mOD) 
	{
		MOD = mOD;
	}

	public String getEmailAddress() 
	{
		return emailaddress;
	}

	public void setEmailAddress(String emailaddress)
	{
		this.emailaddress=emailaddress;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public String getpassword()
	{
		return password;
	}
	
	public void setContactName(String contactname)
	{
		this.contactname=contactname;
	}
	
	public String getcontactname()
	{
		return contactname;
	}
	
	public void setPhoneNumber(String phonenumber)
	{
		this.phonenumber=phonenumber;
	}
	
	public String getphonenumber()
	{
		return phonenumber;
	}
	
	public String gethowufindus()
	{
		return howufindus;
	}
	public void setHowuFindus(String howufindus)
	{
		this.howufindus=howufindus;
	}
	
	public String getusertype()
	{
		return usertype;
	}
	
	public void setUserType(String usertype)
	{
		this.usertype=usertype;
	}
	
	public String getjsonobj()
	{
		String jsonstring = null;
		try
		{
			JSONObject obj = new JSONObject();
			
			
			JSONObject obj1=new JSONObject();
			
			obj.put("EA", this.emailaddress);
			
			obj.put("PWD", this.password);
			
			obj.put("CN",this.contactname);
			
			obj.put("PH",this.phonenumber);
			
			obj.put("HYFUsId","6");
			
			obj.put("UT",this.usertype);
			
			obj.put("UQId",this.UQID);
			
			obj.put("DOS",this.DOS);
			
			obj.put("COM",this.COM);
			
			obj.put("MDN",this.MDN);
			
			obj.put("MOD",this.MOD);
			
			obj.put("Ver",this.Ver);
			
			obj.put("DN","5");
			
			obj1.put("registerUser",obj);
			
			jsonstring=obj1.toString();
			
			Log.i("SignUpRequest", "SignUpRequest "+jsonstring);
			
			Log.i("SignUpURL", "SignUpURL "+ ApplicationConfig.REGISTER_MOB_USER);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonstring;
	}
	
}

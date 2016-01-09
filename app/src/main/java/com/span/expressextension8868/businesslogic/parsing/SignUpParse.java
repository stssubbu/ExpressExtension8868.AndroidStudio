package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.SignupModel;

import org.json.JSONObject;

import java.util.Vector;

public class SignUpParse
{
	String osField,UID,IsAlreadyExist,cn,AT,UT,ISHPRO,DID;
	
	Vector<SignupModel> SignupParseVector=new Vector<SignupModel>();
	
	public Vector<SignupModel> parse(String jsstring)
	{
		 if (jsstring != null) 
		    {
				try
				{
					JSONObject jsonObj = new JSONObject(jsstring);
					
					AT=jsonObj.getString("AT");
					
					osField=jsonObj.getString("OS");
					
					UID=jsonObj.getString("UId");
					
					IsAlreadyExist=jsonObj.getString("IsAlreadyExist");
					
					cn=jsonObj.getString("CN");
					
					UT=jsonObj.getString("UT");
					
					ISHPRO=jsonObj.getString("ISHPRO");
					
					DID=jsonObj.getString("DId");
					
					System.out.println("OS value "+osField);
					
					SignupModel signupmodel=new SignupModel();
					
					signupmodel.setosfield(osField);
					
					signupmodel.setUID(UID);
					
					signupmodel.setDID(DID);
					
					signupmodel.setisAlreadyexist(IsAlreadyExist);
					
					signupmodel.setcontactname(cn);
					
					signupmodel.setAT(AT);
					
					signupmodel.setUT(UT);
					
					signupmodel.setISHPRO(ISHPRO);
					
					SignupParseVector.add(signupmodel);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
		    }
		   
		return SignupParseVector;
		
	}
}

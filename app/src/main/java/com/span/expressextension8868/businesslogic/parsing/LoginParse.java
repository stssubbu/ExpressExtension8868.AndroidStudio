package com.span.expressextension8868.businesslogic.parsing;

import android.util.Log;

import com.span.expressextension8868.model.core.LoginModel;

import org.json.JSONObject;

import java.util.Vector;


public class LoginParse
{
	String osField, Uid, UN, CN, AT, ISHPRO, UT, DID, ISBE, ph;

	Vector<LoginModel> loginParseVector = new Vector<LoginModel>();

	public Vector<LoginModel> parse(String jsstring)
	{
		if (jsstring != null)
		{
			try
			{
				Log.i("LoginJSONString ", "LoginJSONString "+ jsstring);

				JSONObject jsonObj = new JSONObject(jsstring);

				osField = jsonObj.getString("OS");
				
				Log.i("osField ", "osField "+ osField);

				UN = jsonObj.getString("UN");

				CN = jsonObj.getString("CN");

				Uid = jsonObj.getString("UId");

				AT = jsonObj.getString("AT");

				ISHPRO = jsonObj.getString("ISHPRO");

				UT = jsonObj.getString("UT");

				DID = jsonObj.getString("DId");

				ISBE = jsonObj.getString("ISBE");

				ph = jsonObj.getString("PH");

				LoginModel loginmodel = new LoginModel();

				loginmodel.setuid(Uid);

				loginmodel.setAT(AT);

				loginmodel.setosfield(osField);

				loginmodel.setCN(CN);

				loginmodel.setUN(UN);

				loginmodel.setISHPRO(ISHPRO);

				loginmodel.setDId(DID);

				loginmodel.setUT(UT);

				loginmodel.setISBE(ISBE);

				loginmodel.setPh(ph);

				loginParseVector.add(loginmodel);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}

		return loginParseVector;

	}
}

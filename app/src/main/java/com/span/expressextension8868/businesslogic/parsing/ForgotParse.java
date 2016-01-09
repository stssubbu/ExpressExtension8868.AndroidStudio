package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.ForgotModel;

import org.json.JSONObject;

import java.util.Vector;


public class ForgotParse
{
	String osField, Uid, EM;

	Vector<ForgotModel> forgetPasswordVector = new Vector<ForgotModel>();

	public Vector<ForgotModel> parse(String jsstring)
	{
		if (jsstring != null)
		{
			try
			{
				JSONObject jsonObj = new JSONObject(jsstring);

				osField = jsonObj.getString("OS");

				Uid = jsonObj.getString("UId");

				EM = jsonObj.getString("EM");

				ForgotModel forgotmodel = new ForgotModel();

				forgotmodel.setosfield(osField);

				forgotmodel.setEM(EM);

				forgetPasswordVector.add(forgotmodel);
				
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}

		return forgetPasswordVector;

	}
}

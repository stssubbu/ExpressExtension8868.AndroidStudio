package com.span.expressextension8868.webservice.webservices;

import java.util.Vector;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.SignupModel;
import com.span.expressextension8868.webservice.serviceclass.SignupService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;


public class SignupURL extends AsyncTask<String, Void, String>
{
	String jsondata;

	SignUpInterface signUpInterface;

	private ProgressDialog pd;

	Context mContext;

	Vector<SignupModel> SignupParseVector = new Vector<SignupModel>();

	public SignupURL(String jsstring, Context context)
	{
		this.jsondata = jsstring;

		this.mContext = context;
	}

	public void setOnResultListener(SignUpInterface onAsyncResult)
	{

		if (onAsyncResult != null)
		{
			this.signUpInterface = onAsyncResult;

		}
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();

		pd = MyCustomProgressDialog.ctor(mContext);

		pd.show();

	}

	@Override
	protected String doInBackground(String... params)
	{

		try
		{
			SignupService signupservice = new SignupService();

			SignupParseVector = signupservice.GetuserRegister(jsondata, ApplicationConfig.REGISTER_MOB_USER, mContext);

			if (signUpInterface != null)
			{
				signUpInterface.onResultSuccess(SignupParseVector);
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();

			new SendException(mContext, e);
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result)
	{
		super.onPostExecute(result);

		pd.dismiss();

	}

	public interface SignUpInterface
	{
		public abstract void onResultSuccess(Vector<SignupModel> SignupParseVector);

	}
}

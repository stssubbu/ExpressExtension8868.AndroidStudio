package com.span.expressextension8868.webservice.webservices;

import java.util.Vector;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.ForgotModel;
import com.span.expressextension8868.webservice.serviceclass.ForgotService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;


public class ForgotURL extends AsyncTask<String, Void, String>
{
	String jsondata;

	ForgotPassworInterface forgotPassworInterface;

	Vector<ForgotModel> forgetPasswordVector = new Vector<ForgotModel>();

	private ProgressDialog pd;

	Context mContext;

	public ForgotURL(String jsstring, Context context)
	{
		this.jsondata = jsstring;

		this.mContext = context;
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();

		pd = MyCustomProgressDialog.ctor(mContext);

		pd.show();

		
	}

	public void setOnResultListener(ForgotPassworInterface onAsyncResultForgot)
	{

		if (onAsyncResultForgot != null)
		{
			this.forgotPassworInterface = onAsyncResultForgot;

		}
	}

	@Override
	protected String doInBackground(String... params)
	{

		try
		{
			ForgotService forgotservice = new ForgotService();

			forgetPasswordVector = forgotservice.getOsFieldValue(jsondata, ApplicationConfig.FORGOT_PASSWORD, mContext);

			if (forgotPassworInterface != null)
			{
				forgotPassworInterface.onResultSuccess(forgetPasswordVector);
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

	public interface ForgotPassworInterface
	{
		public abstract void onResultSuccess(Vector<ForgotModel> forgetPasswordVector);

	}
}

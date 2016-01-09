package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.LoginModel;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.webservice.serviceclass.LoginService;

import java.util.Vector;


public class LoginURL extends AsyncTask<String, Void, String>
{
	String jsondata;

	LoginInterface loginInterface;

	Vector<LoginModel> loginParseVector = new Vector<LoginModel>();

	private ProgressDialog pd;

	Context mContext;

	public LoginURL(String jsstring, Context context)
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

	public void setOnResultListener(LoginInterface onAsyncResult)
	{
		if (onAsyncResult != null)
		{
			this.loginInterface = onAsyncResult;
		}
	}

	@Override
	protected String doInBackground(String... params)
	{

		try
		{
			LoginService loginservice = new LoginService();
			
			loginParseVector = loginservice.GetUserForSignIn(jsondata, ApplicationConfig.GET_USER_FOR_SIGNIN, mContext);
			
			if (loginInterface != null)
			{
				loginInterface.onResultSuccess(loginParseVector);
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

	public interface LoginInterface
	{
		public abstract void onResultSuccess(Vector<LoginModel> loginParseVector);

	}
}

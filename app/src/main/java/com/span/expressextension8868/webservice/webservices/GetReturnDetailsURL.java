package com.span.expressextension8868.webservice.webservices;

import java.util.Vector;

import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.webservice.serviceclass.SaveReturnDetailsService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class GetReturnDetailsURL extends AsyncTask<String, Void, String>
{
	String jsondata;

	GetReturnDetailsInterface getReturnDetailsInterface;

	Vector<ReturnModel> returnDetailsParseVector = new Vector<ReturnModel>();

	private ProgressDialog pd;

	Context mContext;

	public GetReturnDetailsURL(String jsstring, Context context)
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

	public void setOnResultListener(GetReturnDetailsInterface onAsyncResult)
	{
		if (onAsyncResult != null)
		{
			this.getReturnDetailsInterface = onAsyncResult;
		}
	}

	@Override
	protected String doInBackground(String... params)
	{

		try
		{
			SaveReturnDetailsService returnDetailsService = new SaveReturnDetailsService();
			
			returnDetailsParseVector = returnDetailsService.SaveReturnDetails(jsondata, ApplicationConfig.GETRETURNDETAILSBYRETURNID, mContext);
			
			if (getReturnDetailsInterface != null)
			{
				getReturnDetailsInterface.onResultSuccess(returnDetailsParseVector);
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

	public interface GetReturnDetailsInterface
	{
		public abstract void onResultSuccess(Vector<ReturnModel> returnDetailsParseVector);

	}
}

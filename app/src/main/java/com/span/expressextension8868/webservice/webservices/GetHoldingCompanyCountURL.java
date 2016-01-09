package com.span.expressextension8868.webservice.webservices;


import com.span.expressextension8868.model.core.HoldingModel;
import com.span.expressextension8868.webservice.serviceclass.GetHoldingCompanyCountService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

import android.content.Context;
import android.os.AsyncTask;


public class GetHoldingCompanyCountURL extends AsyncTask<String, Void, String>
{
	String jsondata;

	GetHoldingCompanyCountInterface getHoldingCompanyCountInterface;

	HoldingModel getHoldingcompanyCountmodel = new HoldingModel();

	Context mContext;

	public GetHoldingCompanyCountURL(String jsstring, Context context)
	{
		this.jsondata = jsstring;

		this.mContext = context;

	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();

	}

	public void setOnResultListener(GetHoldingCompanyCountInterface onAsyncResult)
	{
		if (onAsyncResult != null)
		{
			this.getHoldingCompanyCountInterface = onAsyncResult;
		}
	}

	@Override
	protected String doInBackground(String... params)
	{

		try
		{
			GetHoldingCompanyCountService getHoldingCompanyCountService = new GetHoldingCompanyCountService();
			
			getHoldingcompanyCountmodel = getHoldingCompanyCountService.GetHoldingCompanycount(jsondata, ApplicationConfig.GET8868PARTOFGROUPCOUNTBYRETURNID, mContext);
			
			if (getHoldingCompanyCountInterface != null)
			{
				getHoldingCompanyCountInterface.onResultSuccess(getHoldingcompanyCountmodel);
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
		
	}

	public interface GetHoldingCompanyCountInterface
	{
		public abstract void onResultSuccess(HoldingModel getHoldingcompanyModel);

	}
}

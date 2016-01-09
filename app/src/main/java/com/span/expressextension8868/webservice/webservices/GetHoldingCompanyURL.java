package com.span.expressextension8868.webservice.webservices;


import com.span.expressextension8868.model.core.HoldingModel;
import com.span.expressextension8868.webservice.serviceclass.GetHoldingCompanyService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class GetHoldingCompanyURL extends AsyncTask<String, Void, String>
{
	String jsondata;

	GetHoldingCompanyInterface getHoldingCompanyInterface;

	HoldingModel getHoldingcompanyParsing = new HoldingModel();

	private ProgressDialog pd;

	Context mContext;

	public GetHoldingCompanyURL(String jsstring, Context context)
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

	public void setOnResultListener(GetHoldingCompanyInterface onAsyncResult)
	{
		if (onAsyncResult != null)
		{
			this.getHoldingCompanyInterface = onAsyncResult;
		}
	}

	@Override
	protected String doInBackground(String... params)
	{

		try
		{
			GetHoldingCompanyService getHoldingCompanyService = new GetHoldingCompanyService();
			
			getHoldingcompanyParsing = getHoldingCompanyService.GetHoldingCompanyDetails(jsondata, ApplicationConfig.GET8868PARTOFGROUPLISTBYRETURNID, mContext);
			
			if (getHoldingCompanyInterface != null)
			{
				getHoldingCompanyInterface.onResultSuccess(getHoldingcompanyParsing);
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

	public interface GetHoldingCompanyInterface
	{
		public abstract void onResultSuccess(HoldingModel getHoldingcompanyModel);

	}
}

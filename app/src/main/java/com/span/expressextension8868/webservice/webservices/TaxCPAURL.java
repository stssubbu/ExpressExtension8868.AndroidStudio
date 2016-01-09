package com.span.expressextension8868.webservice.webservices;

import java.util.Vector;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.TaxCPAModel;
import com.span.expressextension8868.webservice.serviceclass.TaxCPAService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;


public class TaxCPAURL extends AsyncTask<String, Void, String>
{
	String jsondata;

	Context mContext;

	TaxCPAInterface taxCPAInterface;

	Vector<TaxCPAModel> taxCPAVector = new Vector<TaxCPAModel>();

	private ProgressDialog pd;

	public TaxCPAURL(String jsstring, Context context)
	{
		this.jsondata = jsstring;
		
		this.mContext = context;
	}

	public void setOnResultListener(TaxCPAInterface onAsyncResult)
	{

		if (onAsyncResult != null)
		{
			this.taxCPAInterface = onAsyncResult;

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
			TaxCPAService taxCPAService = new TaxCPAService();

			taxCPAVector = taxCPAService.getCPAosvalue(jsondata, ApplicationConfig.UPDATE_CPA_PROFILE, mContext);

			if (taxCPAInterface != null)
			{
				taxCPAInterface.onResultSuccess(taxCPAVector);
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

	public interface TaxCPAInterface
	{

		public abstract void onResultSuccess(Vector<TaxCPAModel> taxCPAVector);

	}
}

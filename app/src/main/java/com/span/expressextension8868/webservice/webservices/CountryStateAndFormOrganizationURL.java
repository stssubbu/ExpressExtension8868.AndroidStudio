package com.span.expressextension8868.webservice.webservices;

import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.webservice.serviceclass.CountryStateAndFormOrganization;

import java.util.Vector;


public class CountryStateAndFormOrganizationURL extends AsyncTask<String, Void, String>
{
	StaticDataInterface staticDataInterface;

	Context mContext;

	Vector<Vector<String>> countryStateOrganizationVector = new Vector<Vector<String>>();

	public CountryStateAndFormOrganizationURL(Context mContext)
	{
		super();

		this.mContext = mContext;
	}

	public void setOnResultListener(StaticDataInterface onAsyncResult)
	{

		if (onAsyncResult != null)
		{
			this.staticDataInterface = onAsyncResult;
		}
	}

	@Override
	protected String doInBackground(String... params)
	{

		try
		{
			CountryStateAndFormOrganization csfo = new CountryStateAndFormOrganization();

			countryStateOrganizationVector = csfo.GetCountryStateOrganization(mContext);

			if (staticDataInterface != null)
			{
				staticDataInterface.onResultSuccess(countryStateOrganizationVector);

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

	public interface StaticDataInterface
	{

		public abstract void onResultSuccess(Vector<Vector<String>> countryStateOrganizationVector);


	}
}
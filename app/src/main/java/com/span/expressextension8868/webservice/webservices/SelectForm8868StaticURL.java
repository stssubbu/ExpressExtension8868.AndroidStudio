package com.span.expressextension8868.webservice.webservices;

import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.webservice.serviceclass.Form8868StaticDataService;

import java.util.ArrayList;
import java.util.Vector;


public class SelectForm8868StaticURL extends AsyncTask<String, Void, String>
{
	StaticDataInterface8868 staticDataInterface8868;

	Context mContext;

	Vector<ArrayList<String>> GetFormTypestaticVector = new Vector<ArrayList<String>>();

	public SelectForm8868StaticURL(Context mContext)
	{
		super();

		this.mContext = mContext;
	}

	public void setOnResultListener(StaticDataInterface8868 onAsyncResult)
	{

		if (onAsyncResult != null)
		{
			this.staticDataInterface8868 = onAsyncResult;
		}
	}

	@Override
	protected String doInBackground(String... params)
	{

		try
		{
			Form8868StaticDataService csfo = new Form8868StaticDataService();

			GetFormTypestaticVector = csfo.GetFormTypeStaticData(mContext);

			if (staticDataInterface8868 != null)
			{
				staticDataInterface8868.onResultSuccess(GetFormTypestaticVector);
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

	public interface StaticDataInterface8868
	{

		public abstract void onResultSuccess(Vector<ArrayList<String>> GetFormTypestaticVector);


	}
}
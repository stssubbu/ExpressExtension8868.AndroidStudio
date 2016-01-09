package com.span.expressextension8868.webservice.webservices;

import java.util.Vector;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.span.expressextension8868.model.core.TaxCPAModel;
import com.span.expressextension8868.webservice.serviceclass.TaxCPAService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;


public class TaxCPAEditURL extends AsyncTask<String, Void, String>
{
	String jsondata;

	Context mContext;

	CPAEditInterface cPAEditInterface;

	Vector<TaxCPAModel> returnobject = new Vector<TaxCPAModel>();

	private ProgressDialog pd;

	public TaxCPAEditURL(String jsstring, Context context)
	{
		this.jsondata = jsstring;

		this.mContext = context;
	}

	public void setOnResultListener(CPAEditInterface onAsyncResult)
	{

		if (onAsyncResult != null)
		{
			System.out.println("OBJECT " + onAsyncResult);

			this.cPAEditInterface = onAsyncResult;

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

			returnobject = taxCPAService.getCPAosvalue(jsondata, ApplicationConfig.GET_CPA_PROFILE_BY_USERID, mContext);

			if (cPAEditInterface != null)
			{
				cPAEditInterface.onResultSuccess(returnobject);
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

	public interface CPAEditInterface
	{

		public abstract void onResultSuccess(Vector<TaxCPAModel> message);

		public abstract void onResultFail(int resultCode, String errorMessage);
	}
}

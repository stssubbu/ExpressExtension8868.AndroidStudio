package com.span.expressextension8868.webservice.webservices;

import com.span.expressextension8868.model.core.HoldingModel;
import com.span.expressextension8868.webservice.serviceclass.SaveHoldingCompanyService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class SaveHoldingCompanyURL extends AsyncTask<String, Void, String>
{
	String jsondata;

	SaveHoldingCompanyInterface saveHoldingCompanyInterface;

	HoldingModel holdingModel = new HoldingModel();

	private ProgressDialog pd;

	Context mContext;

	public SaveHoldingCompanyURL(String jsstring, Context context)
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

	public void setOnResultListener(SaveHoldingCompanyInterface onAsyncResult)
	{
		if (onAsyncResult != null)
		{
			this.saveHoldingCompanyInterface = onAsyncResult;
		}
	}

	@Override
	protected String doInBackground(String... params)
	{

		try
		{
			SaveHoldingCompanyService getHoldingCompanyService = new SaveHoldingCompanyService();
			
			holdingModel = getHoldingCompanyService.SaveHoldingCompanyDetails(jsondata, ApplicationConfig.UPDATEFORM8868PARTOFGROUP, mContext);
			
			if (saveHoldingCompanyInterface != null)
			{
				saveHoldingCompanyInterface.onResultSuccess(holdingModel);
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

	public interface SaveHoldingCompanyInterface
	{
		public abstract void onResultSuccess(HoldingModel returnDetailsParseVector);

	}
}

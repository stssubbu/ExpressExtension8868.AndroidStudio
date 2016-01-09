package com.span.expressextension8868.webservice.serviceclass;


import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.businesslogic.parsing.GetHoldingCompanyCountParsing;
import com.span.expressextension8868.model.core.HoldingModel;
import com.span.expressextension8868.utils.utility.SendException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class GetHoldingCompanyCountService
{

	String jsonString;

	HoldingModel holdinModel = new HoldingModel();

	Context mContext;

	HoldingModel getHoldingcompanyCountmodel = new HoldingModel();

	public HoldingModel GetHoldingCompanycount(String datainput, String URL, Context mContext)
	{

		try
		{

			this.mContext = mContext;

			HttpClient httpclient = new DefaultHttpClient();

			HttpContext httpContext = new BasicHttpContext();

			HttpPost httppost = new HttpPost(URL);

			StringEntity se = new StringEntity(datainput);

			httppost.setEntity(se);

			httppost.setHeader("Accept", "application/json");

			httppost.setHeader("Content-type", "application/json");

			HttpResponse response = httpclient.execute(httppost, httpContext); 

			HttpEntity entity = response.getEntity();

			jsonString = EntityUtils.toString(entity); // if response in JSON format

			GetHoldingCompanyCountParsing getHoldingCompanyCountParsing = new GetHoldingCompanyCountParsing();

			getHoldingcompanyCountmodel = getHoldingCompanyCountParsing.parse(jsonString);
			
			Log.i("Response Get Holding Company Count", jsonString);

		}
		catch (Exception e)
		{
			e.printStackTrace();

			new SendException(mContext, e);
		}

		return getHoldingcompanyCountmodel;
	}

}

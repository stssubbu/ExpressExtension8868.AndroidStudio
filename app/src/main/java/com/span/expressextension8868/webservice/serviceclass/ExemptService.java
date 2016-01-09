package com.span.expressextension8868.webservice.serviceclass;

import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.businesslogic.parsing.ExemptOrgParsing;
import com.span.expressextension8868.model.core.ExemptModel;
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


public class ExemptService
{
	Context mContext;

	String jsonString;

	ExemptModel exemptModel = new ExemptModel();

	public ExemptModel getPersonalResponse(String datainput, String URL, Context mContext)
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

			jsonString = EntityUtils.toString(entity); 
			
			Log.i("ExemptOrgParsing Response", jsonString);

			ExemptOrgParsing exemptOrgParsing = new ExemptOrgParsing();

			exemptModel = exemptOrgParsing.parse(jsonString);

		}
		catch (Exception e)
		{
			e.printStackTrace();

			new SendException(mContext, e);
		}
		return exemptModel;
	}

}

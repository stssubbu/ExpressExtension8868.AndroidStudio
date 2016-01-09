package com.span.expressextension8868.webservice.serviceclass;

import android.content.Context;

import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;


public class Form8868StaticDataService
{

	String jsonString;

	JSONArray jsonFormCodesList;

	ArrayList<String> FormCodeId = new ArrayList<String>();

	ArrayList<String> FormName = new ArrayList<String>();
	
	ArrayList<String> FormCode = new ArrayList<String>();

	ArrayList<String> FormCodesListEntityId = new ArrayList<String>();
	
	ArrayList<String> IsForm990List = new ArrayList<String>();
	
	Context mContext;
	
	Vector<ArrayList<String>> GetFormTypestaticVector=new Vector<ArrayList<String>>();

	public Vector<ArrayList<String>> GetFormTypeStaticData(Context mContext)
	{

		try
		{

			this.mContext = mContext;

			DefaultHttpClient httpclient = new DefaultHttpClient();

			HttpGet httpget = new HttpGet(ApplicationConfig.GET8868STATICDATA);

			httpget.setHeader("Accept", "application/json");

			httpget.setHeader("Content-type", "application/json");

			HttpResponse response = httpclient.execute(httpget); 

			HttpEntity entity = response.getEntity();

			jsonString = EntityUtils.toString(entity); // if response in JSON
													   // format

			if (jsonString != null)
			{
				try
				{

					JSONObject jsonObj = new JSONObject(jsonString);

					jsonFormCodesList = jsonObj.getJSONArray("FormCodesList");
					
					FormCodesListEntityId.add("0");
					
					FormCodeId.add("0");
					
					FormName.add("--Select Form--");
					
					FormCode.add("0");
					
					IsForm990List.add("false");

					for (int i = 0; i < jsonFormCodesList.length(); i++)
					{
						JSONObject c = jsonFormCodesList.getJSONObject(i);

						String EntityId = c.getString("EntityId");	

						if(EntityId.equalsIgnoreCase("8"))
						{
							String FormCodeIdString = c.getString("FormCodeId");
							
							String FormNameString = c.getString("FormName");
							
							String FormCodeString= c.getString("FormCode");
							
							String IsForm990= c.getString("IsForm990");

							FormCodesListEntityId.add(EntityId);

							FormCodeId.add(FormCodeIdString);

							FormName.add(FormNameString);
							
							FormCode.add(FormCodeString);
							
							IsForm990List.add(IsForm990);
						}

					}
					
					GetFormTypestaticVector.add(FormCodesListEntityId);
					
					GetFormTypestaticVector.add(FormCodeId);
					
					GetFormTypestaticVector.add(FormName);
					
					GetFormTypestaticVector.add(FormCode);
					
					GetFormTypestaticVector.add(IsForm990List);
					
				}
				catch (Exception e)
				{
					e.printStackTrace();

					new SendException(mContext, e);
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();

			new SendException(mContext, e);
		}

		return GetFormTypestaticVector;
	}

	
}
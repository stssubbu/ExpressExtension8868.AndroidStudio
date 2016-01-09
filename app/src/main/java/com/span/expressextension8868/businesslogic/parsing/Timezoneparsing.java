package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.Timezonemodel;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Vector;

public class Timezoneparsing
{
	String timezoneid,timezonetext;
	
	Vector<Timezonemodel> returnobject=new Vector<Timezonemodel>();
	
	ArrayList<String> timezonenamelist=new ArrayList<String>();
	
	Vector<String>  timezoneidlist=new Vector<String> ();
	
	public Vector<Timezonemodel> parse(String jsstring)
	{
		 if (jsstring != null) 
		    {
				try
				{
					JSONArray jsonObj = new JSONArray(jsstring);
					
					timezoneidlist.add("0");
					timezonenamelist.add("--Select Time Zone--");
					for(int x=0;x<jsonObj.length();x++)
					{
						timezoneid=jsonObj.getJSONObject(x).getString("TZVal");
						timezoneidlist.add(timezoneid);
						timezonetext=jsonObj.getJSONObject(x).getString("TZTxt");
						timezonenamelist.add(timezonetext);
							
					}
					
					Timezonemodel timezonemodel=new Timezonemodel();
					
					timezonemodel.setTimezoneid(timezoneidlist);
					
					timezonemodel.setTimezonetext(timezonenamelist);
					
					
					returnobject.add(timezonemodel);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
		    }
		   
		return returnobject;
		
	}
}

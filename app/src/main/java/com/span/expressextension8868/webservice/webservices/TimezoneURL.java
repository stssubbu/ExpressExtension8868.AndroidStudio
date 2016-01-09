package com.span.expressextension8868.webservice.webservices;

import java.util.Vector;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.Timezonemodel;
import com.span.expressextension8868.webservice.serviceclass.TimezoneService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;


public class TimezoneURL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    TimeZoneInterface timeZoneInterface;

    Vector<Timezonemodel> returnobject = new Vector<Timezonemodel>();


    public TimezoneURL(String jsstring, Context context) {
        this.jsondata = jsstring;

        this.mContext = context;
    }

    public void setOnResultListener(TimeZoneInterface onAsyncResult) {

        if (onAsyncResult != null) {
            this.timeZoneInterface = onAsyncResult;

        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected String doInBackground(String... params) {

        try {
            TimezoneService timezoneService = new TimezoneService();

            returnobject = timezoneService.gettimezone(jsondata, ApplicationConfig.GETTIMEZONEDETAILSBYSTATEID, mContext);

            if (timeZoneInterface != null) {
                timeZoneInterface.onResultSuccess(returnobject);
            }
        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


    }

    public interface TimeZoneInterface {

        public abstract void onResultSuccess(Vector<Timezonemodel> message);

    }
}

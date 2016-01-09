package com.span.expressextension8868.webservice.webservices;

import java.util.Vector;

import com.span.expressextension8868.model.core.DatesModel;
import com.span.expressextension8868.webservice.serviceclass.DueDateService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class DueDateURL extends AsyncTask<String, Void, String> {
    String jsondata;

    DueDateInterface dueDateInterface;

    DatesModel datesModel = new DatesModel();

    private ProgressDialog pd;

    Context mContext;


    public DueDateURL(String jsstring, Context context) {
        this.jsondata = jsstring;

        this.mContext = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = MyCustomProgressDialog.ctor(mContext);

        pd.show();
    }

    public void setOnResultListener(DueDateInterface onAsyncResult) {
        if (onAsyncResult != null) {
            this.dueDateInterface = onAsyncResult;
        }
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            DueDateService dueDateService = new DueDateService();

            datesModel = dueDateService.GetDueDateService(jsondata, ApplicationConfig.GET8868DUEDATE, mContext);

            if (dueDateInterface != null) {
                dueDateInterface.onResultSuccess(datesModel);
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

        pd.dismiss();
    }

    public interface DueDateInterface {
        public abstract void onResultSuccess(DatesModel datesModel);

    }
}

package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.span.expressextension8868.model.core.Transmitmodel;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

/**
 * Created by STS-099 on 12/11/2015.
 */
public class TransmitServiceURL extends AsyncTask<String, Void, String> {
    String jsondata;

    TransmitInterface AuditonAsyncResult;

    Transmitmodel returnobject = new Transmitmodel();

    private ProgressDialog pd;

    private ProgressBar progressBar;

    Context mContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = MyCustomProgressDialog.ctor(mContext);

        pd.show();


    }

    public TransmitServiceURL(String jsstring, Context context) {
        this.jsondata = jsstring;
        this.mContext = context;
    }

    public void setOnResultListener(TransmitInterface AuditonAsyncResult) {

        if (AuditonAsyncResult != null) {
            this.AuditonAsyncResult = AuditonAsyncResult;

        }
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            CommonWebserviceMethods commonWebserviceMethods = new CommonWebserviceMethods();

            returnobject = commonWebserviceMethods.getTransmitData(jsondata, ApplicationConfig.PROCESSCREDITCARDANDTRANSMITRETURN, mContext);

            if (AuditonAsyncResult != null) {
                AuditonAsyncResult.onResultSuccess(returnobject);
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

        // progressBar.setVisibility(View.GONE);

    }

    public interface TransmitInterface {
        public abstract void onResultSuccess(Transmitmodel message);


    }
}

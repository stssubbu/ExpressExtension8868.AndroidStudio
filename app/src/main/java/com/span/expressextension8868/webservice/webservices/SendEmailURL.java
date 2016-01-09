package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;


public class SendEmailURL extends AsyncTask<String, Void, String> {
    String jsondata;

    OnAsyncResultByConditionEmail sendforminterface;

    String returnobject, emailType;

    private ProgressDialog pd;

    Context mContext;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = MyCustomProgressDialog.ctor(mContext);

        pd.show();

    }

    public SendEmailURL(String emailType, String jsstring, Context context) {
        this.jsondata = jsstring;

        this.mContext = context;

        this.emailType = emailType;
    }

    public void setOnResultListener(OnAsyncResultByConditionEmail AuditonAsyncResult) {

        if (AuditonAsyncResult != null) {
            this.sendforminterface = AuditonAsyncResult;

        }
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            CommonWebserviceMethods commonWebserviceMethods = new CommonWebserviceMethods();

            if (emailType != null && emailType.equalsIgnoreCase("Approval"))

                returnobject = commonWebserviceMethods.getEmailOS(jsondata, ApplicationConfig.SENDAPPROVAL, mContext);

            else if (emailType != null && emailType.equalsIgnoreCase("Receipt"))

                returnobject = commonWebserviceMethods.getEmailOS(jsondata, ApplicationConfig.SENDRECEIPT, mContext);

            else

                returnobject = commonWebserviceMethods.getEmailOS(jsondata, ApplicationConfig.SENDFORM, mContext);


            if (sendforminterface != null) {
                sendforminterface.onResultSuccess(returnobject);
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

    public interface OnAsyncResultByConditionEmail {
        public abstract void onResultSuccess(String message);

        public abstract void onResultFail(int resultCode, String errorMessage);

    }
}

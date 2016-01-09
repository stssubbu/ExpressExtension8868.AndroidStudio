package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.AuditModel;
import com.span.expressextension8868.model.core.SummaryModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

/**
 * Created by STS-099 on 11/30/2015.
 */
public class AuditForm8868_URL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    OnAsyncResultAuditForm8868 onAsyncResult;

    AuditModel returnobject = new AuditModel();

    private ProgressDialog pd;

    DatabaseHandler db;

    public AuditForm8868_URL(String jsstring, Context context) {

        this.jsondata = jsstring;

        mContext = context;


        db = new DatabaseHandler(mContext);

        db.getWritableDatabase();


    }

    public void setOnResultListener(OnAsyncResultAuditForm8868 onAsyncResult) {

        if (onAsyncResult != null) {
            System.out.println("OBJECT " + onAsyncResult);

            this.onAsyncResult = onAsyncResult;

        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = MyCustomProgressDialog.ctor(mContext);

        pd.show();

    }

    @Override
    protected String doInBackground(String... params) {

        try {
            CommonWebserviceMethods commonWebserviceMethods = new CommonWebserviceMethods();

            returnobject = commonWebserviceMethods.auditForm8868(jsondata, ApplicationConfig.AUDIT_FORM_8868, mContext);


            if (onAsyncResult != null) {
                onAsyncResult.onResultSuccess(returnobject);
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


    public interface OnAsyncResultAuditForm8868 {

        public abstract void onResultSuccess(AuditModel message);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }
}

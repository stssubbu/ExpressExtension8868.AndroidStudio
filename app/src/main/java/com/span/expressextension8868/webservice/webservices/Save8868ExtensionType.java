package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.webservice.serviceclass.ReturnDetailsService;

import java.util.Vector;

/**
 * Created by STS-099 on 11/24/2015.
 */
public class Save8868ExtensionType extends AsyncTask<String, Void, String>

{
    String jsondata;

    SaveExtensionTypeDetailsInterface saveReturnDetailsInterface;

    Vector<ReturnModel> returnDetailsParseVector = new Vector<ReturnModel>();

    private ProgressDialog pd;

    Context mContext;

    public Save8868ExtensionType(String jsstring, Context context) {
        this.jsondata = jsstring;

        this.mContext = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = MyCustomProgressDialog.ctor(mContext);

        pd.show();
    }

    public void setOnResultListener(SaveExtensionTypeDetailsInterface onAsyncResult) {
        if (onAsyncResult != null) {
            this.saveReturnDetailsInterface = onAsyncResult;
        }
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            ReturnDetailsService returnDetailsService = new ReturnDetailsService();

            returnDetailsParseVector = returnDetailsService.save8868ExtensionType(jsondata, ApplicationConfig.SAVE8868EXTENSIONDETAILS, mContext);

            if (saveReturnDetailsInterface != null) {
                saveReturnDetailsInterface.onResultSuccess(returnDetailsParseVector);
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

    public interface SaveExtensionTypeDetailsInterface {
        public abstract void onResultSuccess(Vector<ReturnModel> returnDetailsParseVector);

    }
}

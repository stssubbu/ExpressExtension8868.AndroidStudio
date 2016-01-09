package com.span.expressextension8868.utils.utility;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.span.expressextension8868.controller.controller.LoginActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SendException {

    Context mContext;

    HttpResponse httpResponse;

    String jsonResponse;

    boolean isInternet;

    String mMethodName;

    String mException;

    List<String> userDetails = new ArrayList<String>();

    StringBuilder mExceptionString = new StringBuilder();

    AndroidDeviceInfo info;

    static ProgressDialog progress;

    public SendException(Context context, Exception e) {

        mException = e.toString();

        mContext = context;

        for (StackTraceElement st : e.getStackTrace()) {

            mMethodName = ("Class: " + st.getClassName() + " Method : " + st.getMethodName() + " line : " + st.getLineNumber());

            mExceptionString.append("<br/>" + mMethodName);

        }

        ConnectionDetector connectionDetector = new ConnectionDetector(mContext);

        isInternet = connectionDetector.isConnectingToInternet();

        if (isInternet) {

            Log.e("dd", mContext.getClass().getSimpleName());

            new exceptionSend().execute();
        }

    }

    public SendException(Context context, JSONObject jsonObject) {

        mException = "Invoice Sync";

        mContext = context;

        mExceptionString.append(jsonObject.toString());

        ConnectionDetector connectionDetector = new ConnectionDetector(mContext);

        isInternet = connectionDetector.isConnectingToInternet();

        if (isInternet) {

            Log.e("dd", mContext.getClass().getSimpleName());

            //new exceptionSend().execute();
        }

    }

    public void sendException(String mExceptionMessageString) {

        try {

            try {

                info = new AndroidDeviceInfo(mContext);

                DefaultHttpClient httpClient = new DefaultHttpClient();

                HttpPost postMethod = new HttpPost(ApplicationConfig.SENDERRORMAIL);

                postMethod.setHeader("Content-Type", "application/json");

                JSONObject json = new JSONObject();

                JSONObject jsonErrorLog = new JSONObject();

                json.put("UId", AppConfigManager.getLoggedUid(mContext));

                json.put("AT", AppConfigManager.getAccessToken(mContext));

                json.put("DId", AppConfigManager.getDID(mContext));

                json.put("DMaunfacture", info.getDeviceManufacturer(mContext));

                json.put("DSDK", info.getDeviceSdk(mContext));

                json.put("DOSVersion", info.getDeviceOsVersion(mContext));

                json.put("DN", info.getDeviceName(mContext));

                json.put("DModel", info.getDeviceModel(mContext));

                json.put("TFunctionality", "");

                json.put("EmailAddress", AppConfigManager.getUserName(mContext));

                json.put("Classname", "");

                json.put("Methodname", "");

                json.put("Technicaldetails", mException);

                json.put("Name", AppConfigManager.getContactname(mContext));

                json.put("UserId", AppConfigManager.getLoggedUid(mContext));

                json.put("Errormsg", mExceptionMessageString);

                jsonErrorLog.put("ErrorLog", json);

                Log.i("jsonErrorLog", "jsonErrorLog" + jsonErrorLog.toString());

                postMethod.setEntity(new ByteArrayEntity(jsonErrorLog.toString().getBytes("UTF8")));

                httpResponse = httpClient.execute(postMethod);

                jsonResponse = EntityUtils.toString(httpResponse.getEntity());

                Log.i("jsonResponse", "jsonResponse" + jsonResponse);

                JSONObject smJson = new JSONObject(jsonResponse);

            } catch (Exception e) {

                // new SendException(mContext, e);

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public class exceptionSend extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            sendException(mExceptionString.toString());

            return null;
        }

        public void execute(String string) {

            // TODO Auto-generated method stub
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String result) {

            if (progress != null) {

                if (progress.isShowing()) {

                    progress.dismiss();
                }
            }

            for (int i = 0; i < 2; i++) {

                Toast.makeText(mContext, "Oops! Something went wrong. Please Sign In again to continue", Toast.LENGTH_LONG).show();
            }

            Intent intent = new Intent(mContext.getApplicationContext(), LoginActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            mContext.startActivity(intent);

        }
    }

    public void showAlert(String heading, String message) {

        AlertDialog.Builder alertbox = new AlertDialog.Builder(mContext);

        alertbox.setTitle(heading);

        alertbox.setMessage(message);

        alertbox.setCancelable(false);

        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                arg0.dismiss();

            }
        });

        alertbox.show();

    }

    void Showprogress() {

        progress = new ProgressDialog(mContext);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("Loading...");
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.show();

    }
}

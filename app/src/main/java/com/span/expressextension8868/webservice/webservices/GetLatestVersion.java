package com.span.expressextension8868.webservice.webservices;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.span.expressextension8868.businesslogic.parsing.XMLParser;
import com.span.expressextension8868.utils.utility.ApplicationConfig;

import org.json.JSONObject;

public class GetLatestVersion extends AsyncTask {

    Context mContext;

    GetlatestVersionInterface getlatestVersionInterface;

    private static int SPLASH_TIME_OUT = 3000;

    public static boolean isInternetPresent;

    JSONObject latestVersionResponse;

    int ServiceVersion;

    ProgressDialog progress;

    AlertDialog.Builder alert;

    String mApplicationVersion;

    int ver;

    public GetLatestVersion(Context context) {
        super();
        this.mContext = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
    }

    public void setOnResultListener(GetlatestVersionInterface onAsyncResult) {
        if (onAsyncResult != null) {
            this.getlatestVersionInterface = onAsyncResult;
        }
    }

    @Override
    protected Object doInBackground(Object... params) {

        try {
            PackageManager mPackageManager = mContext.getPackageManager();

            mApplicationVersion = mPackageManager.getPackageInfo(mContext.getPackageName(), 0).versionName;

            Log.e("mAPP", mApplicationVersion);

            mApplicationVersion = mApplicationVersion.replace(".", "");

            Log.e("mAPP1", mApplicationVersion);

            mApplicationVersion = mApplicationVersion.replace("\"", "");

            ver = Integer.parseInt(mApplicationVersion);

            try {
                String catchedresponse;

                // InetAddress address =
                // InetAddress.getByName(ApplicationConfig.CHECKLATESTVERSION);

                XMLParser xmlparser = new XMLParser();

                catchedresponse = xmlparser.getXmlFromUrl(mContext, ApplicationConfig.CHECKLATESTVERSION);

                latestVersionResponse = new JSONObject(catchedresponse);

                handler.post(LatestRunnable);

                Log.i("catchedresponse", catchedresponse);

            } catch (Exception e) {

                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public Handler handler = new Handler();

    public Runnable LatestRunnable = new Runnable() {

        @Override
        public void run() {

            try {

                String serviceVer = latestVersionResponse.getString("AndriodE990AppVersion");

                serviceVer = serviceVer.replace(".", "");

                ServiceVersion = Integer.parseInt(serviceVer);

                // ServiceVersion = 101;

                Log.e("ser", String.valueOf(ServiceVersion));

                Log.e("Ver", String.valueOf(ver));

                // int AppVersion = Integer.parseInt(AppConstants.VERSION);

                if (getlatestVersionInterface != null) {
                    getlatestVersionInterface.onResultSuccess(ver, ServiceVersion);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

        }

    };

    public interface GetlatestVersionInterface {

        public abstract void onResultSuccess(int ver, int ServiceVersion);

    }

}

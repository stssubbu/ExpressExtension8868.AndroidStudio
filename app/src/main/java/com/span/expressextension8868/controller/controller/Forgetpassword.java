package com.span.expressextension8868.controller.controller;

import java.util.Vector;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.span.expressextension8868.model.core.ForgotModel;
import com.span.expressextension8868.utils.utility.MyApplication;
import com.span.expressextension8868.utils.utility.NetworkChangeReceiver;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.ForgotURL;
import com.span.expressextension8868.webservice.webservices.ForgotURL.ForgotPassworInterface;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.span.expressextension8868.R;


public class Forgetpassword extends Activity {
    EditText forgetemail;

    ForgotModel forgotmodel;

    android.support.v7.widget.CardView send, cancel;

    Context mContext = Forgetpassword.this;

    NetworkChangeReceiver receiver;


    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_forgot_password);

        initialization();

        ((Activity) mContext).getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        setonClickListener();

        // Get a Tracker (should auto-report)
        ((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);

        receiver = new NetworkChangeReceiver();

        this.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Get an Analytics tracker to report app starts & uncaught exceptions
        // etc.
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Stop the analytics tracking
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    public void setonClickListener() {

        try {
            send.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {

                        try {

                            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

                            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (!isValidEmail(forgetemail.getText().toString())) {
                            forgetemail.setError("Enter valid Email Address");
                        } else {

                            ForgetActivity();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }

                }
            });
            cancel.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                    finish();

                }
            });
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    private void ForgetActivity() {

        try {
            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("UN", forgetemail.getText().toString());

            obj1.put("UserLogin", obj);

            try {

                ForgotURL forgoturl = new ForgotURL(obj1.toString(), mContext);

                ForgotPassworInterface onasyncforgot = new ForgotPassworInterface() {

                    @Override
                    public void onResultSuccess(Vector<ForgotModel> forgetPasswordVector) {

                        Vector<ForgotModel> returnobject = new Vector<ForgotModel>();

                        returnobject = forgetPasswordVector;

                        forgotmodel = new ForgotModel();

                        forgotmodel = returnobject.get(0);

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                try {
                                    if (forgotmodel.getosfield().equalsIgnoreCase("Success")) {
                                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

                                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                                        utils.successMessage(mContext, "Your new Password has been sent to your email address");

                                        finish();

                                    } else if (forgotmodel.getosfield().equalsIgnoreCase("Failure")) {


                                        utils.errorMessage(mContext, "We don't have a record of your email address. Please use a different Email address or create new account with us");

                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();

                                    new SendException(mContext, e);
                                }

                            }
                        });
                    }


                };
                forgoturl.setOnResultListener(onasyncforgot);

                forgoturl.execute();

            } catch (Exception e) {
                new SendException(mContext, e);
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    public void initialization() {

        utils = new Utils();

        try {
            forgetemail = (EditText) findViewById(R.id.forgetemail);

            send = (android.support.v7.widget.CardView) findViewById(R.id.send);

            cancel = (android.support.v7.widget.CardView) findViewById(R.id.cancel);
        } catch (Exception e) {
            new SendException(mContext, e);
        }

    }

    public final static boolean isValidEmail(String target) {

        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
package com.span.expressextension8868.controller.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.span.expressextension8868.utils.utility.MyApplication;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.span.expressextension8868.R;
import com.span.expressextension8868.utils.utility.TypeFaceClass;

import org.w3c.dom.Text;


public class NewuserCongratulations extends Activity {

    android.support.v7.widget.CardView NewuserLoginalertButton;

    TextView congrazText, text1, text2, buttonText;

    Context mContext = NewuserCongratulations.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setFinishOnTouchOutside(false);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_congratulations);

        initialization();

        ((Activity) mContext).getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        setonClickListener();

        // Get a Tracker (should auto-report)
        ((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);

    }

    public void setonClickListener() {

        NewuserLoginalertButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {

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

    public void initialization() {
        try {

            congrazText = (TextView) findViewById(R.id.congrazText);
            text1 = (TextView) findViewById(R.id.text1);
            text2 = (TextView) findViewById(R.id.text2);
            buttonText = (TextView) findViewById(R.id.buttonText);

            TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

            congrazText.setTypeface(typeFaceClass.NotoSans_Bold());
            text1.setTypeface(typeFaceClass.NotoSans_Regular());
            text2.setTypeface(typeFaceClass.NotoSans_Regular());
            buttonText.setTypeface(typeFaceClass.NotoSans_Regular());


            NewuserLoginalertButton = (android.support.v7.widget.CardView) findViewById(R.id.NewuserLoginalertButton);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
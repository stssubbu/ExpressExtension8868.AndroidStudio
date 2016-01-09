package com.span.expressextension8868.controller.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.span.expressextension8868.R;
import com.span.expressextension8868.utils.utility.MyApplication;


public class WebActivity extends Activity {

    private WebView webView;

    Intent i;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.pdfweb);

        webView = (WebView) findViewById(R.id.pdfview);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        i = getIntent();

        webView.loadUrl(i.getStringExtra("URL"));

        // Get a Tracker (should auto-report)
        ((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);

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
}
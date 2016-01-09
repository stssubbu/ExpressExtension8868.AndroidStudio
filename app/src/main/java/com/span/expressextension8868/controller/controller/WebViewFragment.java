package com.span.expressextension8868.controller.controller;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.CreditModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.MyApplication;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.Utils;

/**
 * Created by STS-099 on 12/12/2015.
 */
public class WebViewFragment extends Fragment {


    Context mContext;

    View CommonTaxView;

    DatabaseHandler databasehandler;

    Utils utils;

    private WebView webView;

    String url;

    // progress

    private ProgressDialog pd;

    //


    public WebViewFragment(Context mContext, String url) {

        this.mContext = mContext;

        this.url = url;


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        try {


            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.pdfweb, container, false);


                webView = (WebView) CommonTaxView.findViewById(R.id.pdfview);

                webView.getSettings().setJavaScriptEnabled(true);

                webView.setWebViewClient(new WebViewClient());

                webView.loadUrl(url);

            }
            InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

            in.hideSoftInputFromWindow(CommonTaxView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(getActivity(), e);
        }

        return CommonTaxView;

    }

}

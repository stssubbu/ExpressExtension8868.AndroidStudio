package com.span.expressextension8868.controller.controller;

import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

public class CommonHeaderClass extends FragmentActivity {

    Context mmContext = CommonHeaderClass.this;

    TextView FormInterviewtextView, TaxDuetextView, SummarytextView, ReviewtextView, OurFeestextView, TransmittotheIRStextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mmContext = this;

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mmContext));


    }

    public void mappingWidgets(View v) {

        try {

            FormInterviewtextView = (TextView) v.findViewById(R.id.FormInterviewtextView);

            TaxDuetextView = (TextView) v.findViewById(R.id.TaxDuetextView);

            SummarytextView = (TextView) v.findViewById(R.id.SummarytextView);

            ReviewtextView = (TextView) v.findViewById(R.id.ReviewtextView);

            OurFeestextView = (TextView) v.findViewById(R.id.OurFeestextView);

            TransmittotheIRStextView = (TextView) v.findViewById(R.id.TransmittotheIRStextView);


        } catch (Exception e) {
            new SendException(mmContext, e);
        }

    }

}

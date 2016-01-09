package com.span.expressextension8868.controller.controller;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.utils.utility.SendException;

/**
 * Created by STS-099 on 11/14/2015.
 */
public class CommonTopHeaderClass extends AppCompatActivity implements View.OnClickListener {

    Context mmContext = CommonTopHeaderClass.this;

    ImageView businesslistview, menu;

    TextView DashBoardTitle, DashboardOrgName, DashboardOrgEin;

    TextView FormInterviewtextView, TaxDuetextView, SummarytextView, ReviewtextView, OurFeestextView, TransmittotheIRStextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mmContext = this;

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mmContext));


    }

    public void mappingWidgets(View v) {

        try {

            Typeface type = Typeface.createFromAsset(getAssets(), "NotoSans-Regular.ttf");


            businesslistview = (ImageView) v.findViewById(R.id.businesslistview);

            menu = (ImageView) v.findViewById(R.id.menu);

            DashBoardTitle = (TextView) v.findViewById(R.id.DashBoardTitle);

            DashboardOrgName = (TextView) v.findViewById(R.id.DashboardOrgName);

            DashboardOrgEin = (TextView) v.findViewById(R.id.DashboardOrgEin);

            DashBoardTitle.setTypeface(type, Typeface.BOLD);

            DashboardOrgName.setTypeface(type);

            DashboardOrgEin.setTypeface(type);


        } catch (Exception e) {
            new SendException(mmContext, e);
        }

    }

    @Override
    public void onClick(View v) {

    }
}

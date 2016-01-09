package com.span.expressextension8868.controller.controller;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.ExemptModel;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.SendException;

/**
 * Created by STS-099 on 11/14/2015.
 */
public class CommonTaxFragment extends Fragment {

    Context mContext;


    View CommonTaxView;


    String RID = "0", BId = "0";


    int SelectionId = 0;
    //Header

    LinearLayout rootlayout, logolayout, DashboardOrgDetailsLayout;

    TextView DashBoardTitle, DashboardOrgEin, DashboardOrgName, Dashboardlink;


    ImageView refresh, businesslistview;


    //

    LinearLayout leftFragment, rightFragment;

    String EIN, BN;

    public CommonTaxFragment(Context mContext) {

        this.mContext = mContext;


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            this.RID = AppConfigManager.getReturnRID(mContext);

            this.BId = AppConfigManager.getBID(mContext);

            this.EIN = AppConfigManager.getEIN(mContext);

            this.BN = AppConfigManager.getBusinessname(mContext);

            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.commontaxfragment, container, false);

                Initialization();

                pageRedirection(AppConfigManager.getFlag(mContext));


            }

            return CommonTaxView;

        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(mContext, e);
        }

        return null;
    }

    private void Initialization() {

        //Header

        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
        DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
        DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);
        Dashboardlink = (TextView) ((Activity) mContext).findViewById(R.id.Dashboardlink);

        Dashboardlink.setEnabled(true);

        rootlayout = (LinearLayout) CommonTaxView.findViewById(R.id.rootlayout);


        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        refresh = (ImageView) ((Activity) mContext).findViewById(R.id.refresh);

        businesslistview = (ImageView) ((Activity) mContext).findViewById(R.id.businesslistview);


        refresh.setVisibility(View.GONE);

        businesslistview.setVisibility(View.GONE);


        if (EIN != null && BN != null) {

            DashboardOrgDetailsLayout.setVisibility(View.VISIBLE);

            DashboardOrgEin.setText(EIN);
            DashboardOrgName.setText(BN);

        } else {

            DashboardOrgDetailsLayout.setVisibility(View.GONE);

        }


        //

        leftFragment = (LinearLayout) CommonTaxView.findViewById(R.id.leftFragment);

        rightFragment = (LinearLayout) CommonTaxView.findViewById(R.id.rightFragment);


    }

    private void onClick() {

        rootlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
    }

    private void pageRedirection(int flag) {


        switch (flag) {
            case 0:

                DashBoardTitle.setText("FORM SELECTION & TAX YEAR");
                addLeftFragment(0);
                addRightFragment(flag);
                break;
            case 1:

                DashBoardTitle.setText("SUMMARY");
                addLeftFragment(2);
                SummaryPageFragment();
                break;
            case 2:
                DashBoardTitle.setText("REVIEW");
                addLeftFragment(3);
                ReviewPageFragment();
                break;

            case 3:
                DashBoardTitle.setText("BOOKS ARE IN THE CARE OF");
                addLeftFragment(1);
                booksInCarePageFragment();
                break;

        }


    }

    private void addLeftFragment(int sId) {

//// TODO: 11/18/2015

        Fragment newFragment = new TaxLeftFragment(mContext, sId);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed

// Commit the transaction
        transaction.commit();

    }

    private void addRightFragment(int sId) {

        //// TODO: 11/18/2015


        Fragment newFragment = new NewTaxYearFragment(mContext, sId);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
        transaction.commit();

    }

    private void SummaryPageFragment() {

        //// TODO: 11/23/2015

        Fragment newFragment = new SummaryFragment(mContext);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.SUMMARY_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }


    private void ReviewPageFragment() {


        Fragment newFragment = new ReviewFragment(mContext);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.REVIEW_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }

    private void booksInCarePageFragment() {

        //// TODO: 11/23/2015

        Fragment newFragment = new BooksInCareOf(mContext, false);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }


}

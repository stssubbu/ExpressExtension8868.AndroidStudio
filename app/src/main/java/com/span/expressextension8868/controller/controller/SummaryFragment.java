package com.span.expressextension8868.controller.controller;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.BookIncareOfModel;
import com.span.expressextension8868.model.core.DatesModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.model.core.SummaryModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.DisableEdittext;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.DueDateURL;
import com.span.expressextension8868.webservice.webservices.GetBooksInCareOfURL;
import com.span.expressextension8868.webservice.webservices.Summary_Detail_URL;

import java.util.Calendar;
import java.util.Vector;

/**
 * Created by STS-099 on 11/26/2015.
 */
public class SummaryFragment extends Fragment {

    Context mContext;

    View CommonTaxView;

    DatabaseHandler databasehandler;

    Utils utils;
    // progress

    private ProgressDialog pd;

    String BN, EIN, Mode;

    // component

    //componenet

    LinearLayout logolayout, DashboardOrgDetailsLayout, WholeLayout;

    TextView ETCancelbutton, DashBoardTitle, DashboardOrgEin, DashboardOrgName;


    android.support.v7.widget.CardView SummaryReview, SummaryCancelbutton,
            sum_orgViewButton, sum_formViewButton, sum_typeViewButton, sum_otherVeiwButton, sum_bprincipalViewButton, sum_taxViewButton;

    TextView SummaryReviewText, SummaryCancelbuttonText, text1, text2, text3, text4, text5,
            sum_orgViewButtonText, sum_formViewButtonText, sum_typeViewButtonText, sum_otherVeiwButtonText, sum_bprincipalViewtButtonText, sum_taxViewButtonText;

    //

    TextView sum_orgName, sum_orgEIN, sum_orgAddress;

    TextView sum_fromExteded, sum_orgTaxYear, sum_fromTaxPeriod;

    TextView sum_FormActDueDate, sum_fromExtedDueDate;

    TextView sum_FormExtensionType, sum_otherDetails, sum_GEN, sum_Part_of_Group;

    TextView sum_bprincipalName, sum_bprincipalAddress;

    TextView sum_taxTentativeAmount, sum_taxCreditAmount, sum_taxBalanceAmount;

    ImageButton sum_orgEditButton, sum_formEditButton, sum_typeEditButton, sum_otherEditButton, sum_bprincipalEditButton, sum_taxEditButton;

    LinearLayout sum_GENLayout, sum_PartofGroupLayout;


    //get detail

    Summary_Detail_URL getDetail;

    SummaryModel getSummaryResponse;
//

    DatesModel datesModel, getdueDatesModel;

    Vector<ReturnModel> returnDetailsParseVector;

    public SummaryFragment(Context mContext) {

        this.mContext = mContext;

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            EIN = AppConfigManager.getEIN(mContext);

            BN = AppConfigManager.getBusinessname(mContext);

            Mode = AppConfigManager.getMode(mContext);


            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.summary, container, false);


                Initialization();

                onClick();

                setTypeFont();


                addLeftFragment();

                getSummaryDetailsById();


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

    private void Initialization() {

        utils = new Utils();

        databasehandler = new DatabaseHandler(mContext);


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
        DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
        DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

        DashBoardTitle.setText("SUMMARY");

        if (EIN != null && BN != null) {

            DashboardOrgEin.setText(EIN);

            DashboardOrgName.setText(BN);
        }


        WholeLayout = (LinearLayout) CommonTaxView.findViewById(R.id.WholeLayout);

        SummaryReview = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.SummaryReview);
        SummaryCancelbutton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.SummaryCancelbutton);


        SummaryReviewText = (TextView) CommonTaxView.findViewById(R.id.SummaryReviewText);
        SummaryCancelbuttonText = (TextView) CommonTaxView.findViewById(R.id.SummaryCancelbuttonText);
        text1 = (TextView) CommonTaxView.findViewById(R.id.text1);
        text2 = (TextView) CommonTaxView.findViewById(R.id.text2);
        text3 = (TextView) CommonTaxView.findViewById(R.id.text3);
        text4 = (TextView) CommonTaxView.findViewById(R.id.text4);
        text5 = (TextView) CommonTaxView.findViewById(R.id.text5);

        sum_orgName = (TextView) CommonTaxView.findViewById(R.id.sum_orgName);
        sum_orgEIN = (TextView) CommonTaxView.findViewById(R.id.sum_orgEIN);
        sum_orgAddress = (TextView) CommonTaxView.findViewById(R.id.sum_orgAddress);

        sum_fromExteded = (TextView) CommonTaxView.findViewById(R.id.sum_fromExteded);
        sum_orgTaxYear = (TextView) CommonTaxView.findViewById(R.id.sum_orgTaxYear);
        sum_fromTaxPeriod = (TextView) CommonTaxView.findViewById(R.id.sum_fromTaxPeriod);


        sum_FormActDueDate = (TextView) CommonTaxView.findViewById(R.id.sum_FormActDueDate);
        sum_fromExtedDueDate = (TextView) CommonTaxView.findViewById(R.id.sum_fromExtedDueDate);

        sum_FormExtensionType = (TextView) CommonTaxView.findViewById(R.id.sum_FormExtensionType);
        sum_otherDetails = (TextView) CommonTaxView.findViewById(R.id.sum_otherDetails);
        sum_GENLayout = (LinearLayout) CommonTaxView.findViewById(R.id.sum_GENLayout);
        sum_PartofGroupLayout = (LinearLayout) CommonTaxView.findViewById(R.id.sum_PartofGroupLayout);
        sum_GEN = (TextView) CommonTaxView.findViewById(R.id.sum_GEN);
        sum_Part_of_Group = (TextView) CommonTaxView.findViewById(R.id.sum_Part_of_Group);

        sum_bprincipalName = (TextView) CommonTaxView.findViewById(R.id.sum_bprincipalName);
        sum_bprincipalAddress = (TextView) CommonTaxView.findViewById(R.id.sum_bprincipalAddress);


        sum_taxTentativeAmount = (TextView) CommonTaxView.findViewById(R.id.sum_taxTentativeAmount);
        sum_taxCreditAmount = (TextView) CommonTaxView.findViewById(R.id.sum_taxCreditAmount);
        sum_taxBalanceAmount = (TextView) CommonTaxView.findViewById(R.id.sum_taxBalanceAmount);

        sum_orgEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_orgEditButton);
        sum_formEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_formEditButton);
        sum_typeEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_typeEditButton);
        sum_otherEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_otherEditButton);
        sum_bprincipalEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_bprincipalEditButton);
        sum_taxEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_taxEditButton);

        sum_orgViewButton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.sum_orgViewButton);
        sum_formViewButton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.sum_formViewButton);
        sum_typeViewButton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.sum_typeViewButton);
        sum_otherVeiwButton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.sum_otherVeiwButton);
        sum_bprincipalViewButton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.sum_bprincipalViewButton);
        sum_taxViewButton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.sum_taxViewButton);

        sum_orgViewButtonText = (TextView) CommonTaxView.findViewById(R.id.sum_orgViewButtonText);
        sum_formViewButtonText = (TextView) CommonTaxView.findViewById(R.id.sum_formViewButtonText);
        sum_typeViewButtonText = (TextView) CommonTaxView.findViewById(R.id.sum_typeViewButtonText);
        sum_typeViewButtonText = (TextView) CommonTaxView.findViewById(R.id.sum_typeViewButtonText);
        sum_bprincipalViewtButtonText = (TextView) CommonTaxView.findViewById(R.id.sum_bprincipalViewtButtonText);
        sum_taxViewButtonText = (TextView) CommonTaxView.findViewById(R.id.sum_taxViewButtonText);

        disableLayout();
    }

    private void onClick() {

        WholeLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });


        logolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                startActivity(new Intent(mContext, DashboardActivity.class).putExtra("TO_LAYOUT", "Dashboard"));

                ((Activity) mContext).finish();

            }
        });

        SummaryReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                TaxPageFragment();
            }
        });

        SummaryCancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

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
        });


        sum_orgEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 11/26/2015

                DashBoardTitle.setText("ADD EXEMPT ORGANIZATION DETAILS");

                Fragment newFragment = new AddExemptOrganization(mContext, true, true);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.wholevertical, newFragment);

                transaction.addToBackStack(FragmentNameConfig.ORG_DETAIL_FRAGMENT);

// Commit the transaction
                transaction.commit();


            }
        });

        sum_formEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.rightFragment, newFragment);

                //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                transaction.commit();


            }
        });

        sum_typeEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment newFragment = new ExtensionTypeFragment(mContext);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.rightFragment, newFragment);

                transaction.addToBackStack(FragmentNameConfig.EXTENSION_TYPE_FRAGMENT);

// Commit the transaction
                transaction.commit();


            }
        });

        sum_otherEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment newFragment = new ExtensionTypeFragment(mContext);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.rightFragment, newFragment);

                transaction.addToBackStack(FragmentNameConfig.EXTENSION_TYPE_FRAGMENT);

// Commit the transaction
                transaction.commit();


            }
        });


        sum_bprincipalEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new BooksInCareOf(mContext, false);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.rightFragment, newFragment);

                transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                transaction.commit();   // TODO: 11/26/2015


            }
        });

        sum_taxEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment newFragment = new BalanceDueFragment(mContext);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.rightFragment, newFragment);

                transaction.addToBackStack(FragmentNameConfig.BALANCE_DUE);

// Commit the transaction
                transaction.commit();


            }
        });

        sum_orgViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sum_orgEditButton.performClick();

            }
        });
        sum_formViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_formEditButton.performClick();
            }
        });
        sum_typeViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_typeEditButton.performClick();
            }
        });
        sum_otherVeiwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_otherEditButton.performClick();
            }
        });
        sum_bprincipalViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_bprincipalEditButton.performClick();
            }
        });
        sum_taxViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum_taxEditButton.performClick();
            }
        });


    }

    private void getSummaryDetailsById() {


        SummaryModel getSummaryModel = new SummaryModel();

        getSummaryModel.setAT(AppConfigManager.getAccessToken(mContext));

        getSummaryModel.setUId(AppConfigManager.getLoggedUid(mContext));

        getSummaryModel.setRID(AppConfigManager.getReturnRID(mContext));

        getSummaryModel.setDId(AppConfigManager.getDID(mContext));

        if (getDetail != null)

            getDetail.cancel(true);

        getDetail = new Summary_Detail_URL(getSummaryModel.getSummaryDetails(mContext), mContext);

        getDetail.setOnResultListener(getSummaryAsync);

        getDetail.execute();


    }

    Summary_Detail_URL.OnAsyncResultGetSummaryDetail getSummaryAsync = new Summary_Detail_URL.OnAsyncResultGetSummaryDetail() {


        @Override
        public void onResultSuccess(SummaryModel message) {

            getSummaryResponse = message;

            handler.post(getRunnable);

        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    Handler handler = new Handler();

    Runnable getRunnable = new Runnable() {
        @Override
        public void run() {

            if (getSummaryResponse != null && getSummaryResponse.getOS() != null && getSummaryResponse.getOS().equalsIgnoreCase("Success")) {


                if (getSummaryResponse.getBN() != null && !getSummaryResponse.getBN().equalsIgnoreCase("null") && getSummaryResponse.getBN().length() > 0) {

                    sum_orgName.setText(getSummaryResponse.getBN());
                }

                if (getSummaryResponse.getEIN() != null && !getSummaryResponse.getEIN().equalsIgnoreCase("null") && getSummaryResponse.getEIN().length() > 0) {

                    sum_orgEIN.setText(getSummaryResponse.getEIN());
                }
                if (getSummaryResponse.getADD1() != null && !getSummaryResponse.getADD1().equalsIgnoreCase("null") && getSummaryResponse.getADD1().length() > 0) {

                    String add = null;
                    try {

                        add = utils.addressOnly(mContext, getSummaryResponse.getADD1(), getSummaryResponse.getADD2());

                    } catch (Exception e) {

                        e.printStackTrace();

                        new SendException(mContext, e);

                    }

                    if (add != null && !add.equalsIgnoreCase("null")) {

                        sum_orgAddress.setText(add);

                    }
                }


                String add = null;
                try {


                    add = utils.BcoCityStateOnly(mContext, getSummaryResponse.getCITY(), getSummaryResponse.getSC(), getSummaryResponse.getSN(), getSummaryResponse.getBCOCountry(), getSummaryResponse.getZIP(), getSummaryResponse.ISFORADD());

                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);

                }

                if (add != null && !add.equalsIgnoreCase("null")) {

                    sum_orgAddress.append("\n" + add);

                }


                if (getSummaryResponse.getFNAME() != null && !getSummaryResponse.getFNAME().equalsIgnoreCase("null") && getSummaryResponse.getFNAME().length() > 0) {

                    sum_fromExteded.setText(getSummaryResponse.getFNAME());
                }

                if (getSummaryResponse.getTY() != null && !getSummaryResponse.getTY().equalsIgnoreCase("null") && getSummaryResponse.getTY().length() > 0) {

                    sum_orgTaxYear.setText(getSummaryResponse.getTY());
                }

                if (getSummaryResponse.getTYBD() != null && !getSummaryResponse.getTYBD().equalsIgnoreCase("null") && getSummaryResponse.getTYBD().length() > 0) {

                    sum_fromTaxPeriod.setText(getSummaryResponse.getTYBD());
                }

                if (getSummaryResponse.getTYED() != null && !getSummaryResponse.getTYED().equalsIgnoreCase("null") && getSummaryResponse.getTYED().length() > 0) {

                    sum_fromTaxPeriod.append(" - " + getSummaryResponse.getTYED());
                }

                if (getSummaryResponse.getExtendedDueDate() != null && !getSummaryResponse.getExtendedDueDate().equalsIgnoreCase("null") && getSummaryResponse.getExtendedDueDate().length() > 0) {

                    sum_fromExtedDueDate.setText(getSummaryResponse.getExtendedDueDate());

                }


                if (getSummaryResponse.getExtensionType() != null && getSummaryResponse.getExtensionType().length() > 0 && getSummaryResponse.getExtensionType().equalsIgnoreCase("THREEMONTH")) {

                    datesModel = new DatesModel();

                    String formCodeid = getSummaryResponse.getFC();

                    datesModel.setFORMCODEID(formCodeid);
                    datesModel.setTYED(getSummaryResponse.getTYED());
                    datesModel.setTY(getSummaryResponse.getTY());

                    if (formCodeid.equalsIgnoreCase("07") || formCodeid.equalsIgnoreCase("7")) {


                        sum_FormExtensionType.setText("Automatic 6 month Extension");

                    } else {


                        sum_FormExtensionType.setText("Automatic 3 month Extension");

                    }


                    datesModel.setExtensionType("0");

                    DueDateRequstMethod();


                } else {

                    datesModel = new DatesModel();

                    String formCodeid = getSummaryResponse.getFC();

                    datesModel.setFORMCODEID(formCodeid);
                    datesModel.setTYED(getSummaryResponse.getTYED());
                    datesModel.setTY(getSummaryResponse.getTY());

                    datesModel.setExtensionType("1");

                    sum_FormExtensionType.setText("Additional (Not Automatic) 3-Month Extension");

                    DueDateRequstMethod();
                }

                if (getSummaryResponse.isGroupReturn()) {

                } else {

                }


                if (getSummaryResponse.ISFC()) {
                    if (getSummaryResponse.getBN() != null && !getSummaryResponse.getBN().equalsIgnoreCase("null") && getSummaryResponse.getBN().length() > 0)

                        sum_otherDetails.setText(getSummaryResponse.getBN() + " is a Foreign Corporation.");


                } else {
                    if (getSummaryResponse.getBN() != null && !getSummaryResponse.getBN().equalsIgnoreCase("null") && getSummaryResponse.getBN().length() > 0)
                        sum_otherDetails.setText(getSummaryResponse.getBN() + " is a US Based Organization.");


                }
                if (getSummaryResponse.getGEN() != null && !getSummaryResponse.getGEN().equalsIgnoreCase("null") && getSummaryResponse.getGEN().length() > 0) {

                    sum_GENLayout.setVisibility(View.VISIBLE);

                    sum_GEN.setText(getSummaryResponse.getGEN());


                } else {


                    sum_GENLayout.setVisibility(View.GONE);

                }

                if (getSummaryResponse.getHoldingCompaniesList() != null && !getSummaryResponse.getHoldingCompaniesList().equalsIgnoreCase("null") && getSummaryResponse.getHoldingCompaniesList().length() > 0) {

                    sum_PartofGroupLayout.setVisibility(View.VISIBLE);

                    sum_Part_of_Group.setText(getSummaryResponse.getHoldingCompaniesList());

                } else {

                    sum_PartofGroupLayout.setVisibility(View.GONE);

                }

                if (getSummaryResponse.getBookInCareOf() != null && !getSummaryResponse.getBookInCareOf().equalsIgnoreCase("null") && getSummaryResponse.getBookInCareOf().length() > 0) {

                    sum_bprincipalName.setText(getSummaryResponse.getBookInCareOf());
                }

                if (getSummaryResponse.getBCOAddress1() != null && !getSummaryResponse.getBCOAddress1().equalsIgnoreCase("null") && getSummaryResponse.getBCOAddress1().length() > 0) {

                    add = null;
                    try {

                        add = utils.addressOnly(mContext, getSummaryResponse.getBCOAddress1(), getSummaryResponse.getBCOAddress2());

                    } catch (Exception e) {

                        e.printStackTrace();

                        new SendException(mContext, e);

                    }

                    if (add != null && !add.equalsIgnoreCase("null")) {

                        sum_bprincipalAddress.setText(add);

                    }
                }


                add = null;
                try {


                    add = utils.BcoCityStateOnly(mContext, getSummaryResponse.getBCOCity(), getSummaryResponse.getBCOStateCode(), getSummaryResponse.getBCOState(), getSummaryResponse.getBCOCountry(), getSummaryResponse.getBCOZip(), getSummaryResponse.isBCOIsForeignAddress());

                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);

                }

                if (add != null && !add.equalsIgnoreCase("null")) {

                    sum_bprincipalAddress.append("\n" + add);

                }


                if (getSummaryResponse.getTentativeTaxAmount() != null && !getSummaryResponse.getTentativeTaxAmount().equalsIgnoreCase("null") && getSummaryResponse.getTentativeTaxAmount().length() > 0) {
                    try {

                        sum_taxTentativeAmount.setText(utils.amountValue(mContext, getSummaryResponse.getTentativeTaxAmount()));

                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                }

                if (getSummaryResponse.getCreditAmount() != null && !getSummaryResponse.getCreditAmount().equalsIgnoreCase("null") && getSummaryResponse.getCreditAmount().length() > 0) {
                    try {

                        sum_taxCreditAmount.setText(utils.amountValue(mContext, getSummaryResponse.getCreditAmount()));

                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                }

                if (getSummaryResponse.getBalanceDue() != null && !getSummaryResponse.getBalanceDue().equalsIgnoreCase("null") && getSummaryResponse.getBalanceDue().length() > 0) {

                    try {

                        sum_taxBalanceAmount.setText(utils.amountValue(mContext, getSummaryResponse.getBalanceDue()));

                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                }

            } else if (getSummaryResponse.getOS().equalsIgnoreCase("Failure")) {

                if (getSummaryResponse.getEM() != null && !getSummaryResponse.getEM().equalsIgnoreCase("null")) {

                    if (getSummaryResponse.getEM().equalsIgnoreCase("Access Token is invalid")) {

                        utils.errorMessage(mContext, "Your session is Expired");
                        Logout.logout(mContext);

                    } else {

                        utils.errorMessage(mContext, getSummaryResponse.getEM());

                    }
                }
            }
        }
    };


    private void addLeftFragment() {

//// TODO: 11/18/2015

        Fragment newFragment = new TaxLeftFragment(mContext, 2);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.leftFragment, newFragment);


// Commit the transaction
        transaction.commit();

    }

    public void DueDateRequstMethod() {

        DueDateURL dueDateURL = new DueDateURL(datesModel.getDuedateRequest(), mContext);

        DueDateURL.DueDateInterface dueDateInterface = new DueDateURL.DueDateInterface() {

            @Override
            public void onResultSuccess(final DatesModel getdueDatesModel1) {

                getdueDatesModel = getdueDatesModel1;

                handler.post(duedateRunnable);
            }

        };

        dueDateURL.setOnResultListener(dueDateInterface);

        dueDateURL.execute();

    }

    Runnable duedateRunnable = new Runnable() {
        @Override
        public void run() {

            if (getdueDatesModel != null) {

                if (getdueDatesModel.getExtensionType().equalsIgnoreCase("0")) {

                    if (getdueDatesModel.getActualDueDate() != null && !getdueDatesModel.getActualDueDate().equalsIgnoreCase("null")) {
                        sum_FormActDueDate.setText(getdueDatesModel.getActualDueDate());
                    }

                    if (getdueDatesModel.getExtendedDueDate() != null && !getdueDatesModel.getExtendedDueDate().equalsIgnoreCase("null")) {
                        sum_fromExtedDueDate.setText(getdueDatesModel.getExtendedDueDate());
                    }

                } else if (getdueDatesModel.getExtensionType().equalsIgnoreCase("1")) {

                    if (getdueDatesModel.getActualDueDate() != null && !getdueDatesModel.getActualDueDate().equalsIgnoreCase("null")) {
                        sum_FormActDueDate.setText(getdueDatesModel.getActualDueDate());
                    }

                    if (getdueDatesModel.getExtendedDueDate() != null && !getdueDatesModel.getExtendedDueDate().equalsIgnoreCase("null")) {
                        sum_fromExtedDueDate.setText(getdueDatesModel.getExtendedDueDate());
                    }

                }
            }

        }
    };


    private void TaxPageFragment() {

        if (getSummaryResponse != null && getSummaryResponse.ISPAID() && Mode != null && Mode.equalsIgnoreCase("View")) {

            Fragment newFragment = new TransmitSuccessFragment(mContext);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

            transaction.replace(R.id.rightFragment, newFragment);

            transaction.addToBackStack(FragmentNameConfig.TRANSMIT_FRAGMENT);

            transaction.commit();

        } else {


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

    }

    private void setTypeFont() {

        Overridefonts.overrideFonts(mContext, WholeLayout);

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        text2.setTypeface(typeFaceClass.NotoSans_Bold());

        sum_orgName.setTypeface(typeFaceClass.NotoSans_Bold());
        sum_bprincipalName.setTypeface(typeFaceClass.NotoSans_Bold());

        SummaryReviewText.setTypeface(typeFaceClass.NotoSans_Regular());
        SummaryCancelbuttonText.setTypeface(typeFaceClass.NotoSans_Regular());
        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        text2.setTypeface(typeFaceClass.NotoSans_Bold());
        text3.setTypeface(typeFaceClass.NotoSans_Bold());
        text4.setTypeface(typeFaceClass.NotoSans_Bold());
        text5.setTypeface(typeFaceClass.NotoSans_Bold());
    }

    private void disableLayout() {

        //   DisableEdittext.disableAll(mContext, WholeLayout);

        if (Mode != null && Mode.equalsIgnoreCase("View")) {
            sum_bprincipalEditButton.setVisibility(View.GONE);
            sum_formEditButton.setVisibility(View.GONE);
            sum_orgEditButton.setVisibility(View.GONE);
            sum_otherEditButton.setVisibility(View.GONE);
            sum_taxEditButton.setVisibility(View.GONE);
            sum_typeEditButton.setVisibility(View.GONE);

            sum_orgViewButton.setVisibility(View.VISIBLE);
            sum_formViewButton.setVisibility(View.VISIBLE);
            sum_typeViewButton.setVisibility(View.VISIBLE);
            sum_otherVeiwButton.setVisibility(View.VISIBLE);
            sum_bprincipalViewButton.setVisibility(View.VISIBLE);
            sum_taxViewButton.setVisibility(View.VISIBLE);
        } else {
            sum_bprincipalEditButton.setVisibility(View.VISIBLE);
            sum_formEditButton.setVisibility(View.VISIBLE);
            sum_orgEditButton.setVisibility(View.VISIBLE);
            sum_otherEditButton.setVisibility(View.VISIBLE);
            sum_taxEditButton.setVisibility(View.VISIBLE);
            sum_typeEditButton.setVisibility(View.VISIBLE);

            sum_orgViewButton.setVisibility(View.GONE);
            sum_formViewButton.setVisibility(View.GONE);
            sum_typeViewButton.setVisibility(View.GONE);
            sum_otherVeiwButton.setVisibility(View.GONE);
            sum_bprincipalViewButton.setVisibility(View.GONE);
            sum_taxViewButton.setVisibility(View.GONE);
        }
//        sum_orgViewButtonText,sum_formViewButtonText, sum_typeViewButtonText, sum_otherVeiwButtonText, sum_bprincipalViewButtonText, sum_taxViewButtonText  ;

    }


}

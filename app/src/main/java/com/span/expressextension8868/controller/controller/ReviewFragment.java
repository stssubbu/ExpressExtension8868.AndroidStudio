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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.businesslogic.adapter.BusinessListAdapter;
import com.span.expressextension8868.businesslogic.adapter.ReviewCautionListAdapter;
import com.span.expressextension8868.businesslogic.adapter.ReviewErrorListAdapter;
import com.span.expressextension8868.model.core.AuditModel;
import com.span.expressextension8868.model.core.SummaryModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.DisableEdittext;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.AuditForm8868_URL;
import com.span.expressextension8868.webservice.webservices.Summary_Detail_URL;

/**
 * Created by STS-099 on 11/30/2015.
 */
public class ReviewFragment extends Fragment {

    Context mContext;

    View CommonTaxView;

    String BId, EIN, BN;

    DatabaseHandler databasehandler;

    LinearLayout logolayout, DashboardOrgDetailsLayout;

    TextView DashBoardTitle, DashboardOrgEin, DashboardOrgName;


    Utils utils;
    // progress

    private ProgressDialog pd;

    //component


    android.support.v7.widget.CardView ReviewCancelbutton, ReviewNext;

    TextView ReviewCancelbuttonText, ReviewNextText, text1, text2, text3;

    LinearLayout Error_layout, NoError_layout, caution_layout, NoCaution_layout;

    LinearLayout WholeLayout, wholeNoErrorLayout, wholeErrorLayout;

    com.span.expressextension8868.utils.utility.ExpandableHeightListView errorlist, cautionList;

    AuditForm8868_URL getAuditDetails;

    AuditModel getAuditResponse;

    public ReviewFragment(Context mContext)

    {

        this.mContext = mContext;


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            BId = AppConfigManager.getBID(mContext);
            EIN = AppConfigManager.getEIN(mContext);
            BN = AppConfigManager.getBusinessname(mContext);


            CommonTaxView = inflater.inflate(R.layout.review, container, false);

            InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

            in.hideSoftInputFromWindow(CommonTaxView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            initialization();

            onClick();

            setTypeFont();


            addLeftFragment();

            getReviewDetailsById();


        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(getActivity(), e);
        }

        return CommonTaxView;

    }

    private void initialization() {

        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
        DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
        DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

        DashBoardTitle.setText("REVIEW");

        if (EIN != null && BN != null) {

            DashboardOrgEin.setText(EIN);

            DashboardOrgName.setText(BN);

            DashboardOrgDetailsLayout.setVisibility(View.VISIBLE);

        } else

            DashboardOrgDetailsLayout.setVisibility(View.GONE);

        WholeLayout = (LinearLayout) CommonTaxView.findViewById(R.id.WholeLayout);
        ReviewCancelbutton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.ReviewCancelbutton);
        ReviewNext = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.ReviewNext);
        ReviewNext.setEnabled(false);

        ReviewCancelbuttonText = (TextView) CommonTaxView.findViewById(R.id.ReviewCancelbuttonText);
        ReviewNextText = (TextView) CommonTaxView.findViewById(R.id.ReviewNextText);
        text1 = (TextView) CommonTaxView.findViewById(R.id.text1);
        text2 = (TextView) CommonTaxView.findViewById(R.id.text2);
        text3 = (TextView) CommonTaxView.findViewById(R.id.text3);

        wholeNoErrorLayout = (LinearLayout) CommonTaxView.findViewById(R.id.wholeNoErrorLayout);
        wholeErrorLayout = (LinearLayout) CommonTaxView.findViewById(R.id.wholeErrorLayout);

        wholeNoErrorLayout.setVisibility(View.GONE);
        wholeErrorLayout.setVisibility(View.GONE);

        Error_layout = (LinearLayout) CommonTaxView.findViewById(R.id.Error_layout);
        Error_layout.setVisibility(View.GONE);
        NoError_layout = (LinearLayout) CommonTaxView.findViewById(R.id.NoError_layout);
        NoError_layout.setVisibility(View.GONE);
        caution_layout = (LinearLayout) CommonTaxView.findViewById(R.id.caution_layout);
        caution_layout.setVisibility(View.GONE);
        NoCaution_layout = (LinearLayout) CommonTaxView.findViewById(R.id.NoCaution_layout);
        NoCaution_layout.setVisibility(View.GONE);

        errorlist = (com.span.expressextension8868.utils.utility.ExpandableHeightListView) CommonTaxView.findViewById(R.id.errorlist);
        cautionList = (com.span.expressextension8868.utils.utility.ExpandableHeightListView) CommonTaxView.findViewById(R.id.cautionList);


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

        ReviewCancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

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
        });

        ReviewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                TaxPageFragment();


            }
        });

        errorlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                                 for (int i = 0; i < cautionList.getChildCount(); i++) {
                                                     View v = cautionList.getChildAt(i);
                                                     v.getTag();

                                                 }

                                                 TextView fix_me = (TextView) view.findViewById(R.id.fix_me);

                                                 fix_me.setOnClickListener(new View.OnClickListener() {
                                                                               @Override
                                                                               public void onClick(View v) {

                                                                                   if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("Create")) {


                                                                                       Fragment newFragment = new AddExemptOrganization(mContext, true, true);

                                                                                       FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                       transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                       transaction.replace(R.id.wholevertical, newFragment);

                                                                                       transaction.addToBackStack(FragmentNameConfig.ORG_DETAIL_FRAGMENT);

// Commit the transaction
                                                                                       transaction.commit();

                                                                                   } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("IRSPayment")) {

                                                                                       Fragment newFragment = new IRSPayment(mContext);

                                                                                       FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                       transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                       transaction.replace(R.id.rightFragment, newFragment);

                                                                                       transaction.addToBackStack(FragmentNameConfig.IRS_PAYMENT_FRAGMENT);

// Commit the transaction
                                                                                       transaction.commit();

                                                                                   } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("Step1")) {

                                                                                       Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                                                                                       FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                       transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                       transaction.replace(R.id.rightFragment, newFragment);

                                                                                       //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                                                                                       transaction.commit();

                                                                                   } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("Step2")) {
                                                                                       Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                                                                                       FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                       transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                       transaction.replace(R.id.rightFragment, newFragment);

                                                                                       //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                                                                                       transaction.commit();


                                                                                   } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("Step3")) {

                                                                                       Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                                                                                       FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                       transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                       transaction.replace(R.id.rightFragment, newFragment);

                                                                                       //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                                                                                       transaction.commit();


                                                                                   } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("BookInCareOf")) {

                                                                                       Fragment newFragment = new BooksInCareOf(mContext, true);

                                                                                       FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                       transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                       transaction.replace(R.id.rightFragment, newFragment);

                                                                                       transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                                                                                       transaction.commit();   // TODO: 11/26/2015

                                                                                   } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("PrimaryExemptPurpose")) {

                                                                                       Fragment newFragment = new BooksInCareOf(mContext, true);

                                                                                       FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                       transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                       transaction.replace(R.id.rightFragment, newFragment);

                                                                                       transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                                                                                       transaction.commit();   // TODO: 11/26/2015

                                                                                   } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("ProgramServiceAccomplishment")) {

                                                                                       Fragment newFragment = new BooksInCareOf(mContext, true);

                                                                                       FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                       transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                       transaction.replace(R.id.rightFragment, newFragment);

                                                                                       transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                                                                                       transaction.commit();   // TODO: 11/26/2015

                                                                                   } else {


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


                                                                               }
                                                                           }

                                                 );

                                             }
                                         }

        );

        cautionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                               @Override
                                               public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                                   TextView fix_me = (TextView) view.findViewById(R.id.fix_me);

                                                   fix_me.setOnClickListener(new View.OnClickListener() {
                                                                                 @Override
                                                                                 public void onClick(View v) {

                                                                                     if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("Create")) {


                                                                                         Fragment newFragment = new AddExemptOrganization(mContext, true, true);

                                                                                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                         transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                         transaction.replace(R.id.wholevertical, newFragment);

                                                                                         transaction.addToBackStack(FragmentNameConfig.ORG_DETAIL_FRAGMENT);

// Commit the transaction
                                                                                         transaction.commit();

                                                                                     } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("IRSPayment")) {

                                                                                         Fragment newFragment = new IRSPayment(mContext);

                                                                                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                         transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                         transaction.replace(R.id.rightFragment, newFragment);

                                                                                         transaction.addToBackStack(FragmentNameConfig.IRS_PAYMENT_FRAGMENT);

// Commit the transaction
                                                                                         transaction.commit();

                                                                                     } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("Step1")) {

                                                                                         Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                                                                                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                         transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                         transaction.replace(R.id.rightFragment, newFragment);

                                                                                         //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                                                                                         transaction.commit();

                                                                                     } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("Step2")) {
                                                                                         Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                                                                                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                         transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                         transaction.replace(R.id.rightFragment, newFragment);

                                                                                         //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                                                                                         transaction.commit();


                                                                                     } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("Step3")) {

                                                                                         Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                                                                                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                         transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                         transaction.replace(R.id.rightFragment, newFragment);

                                                                                         //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                                                                                         transaction.commit();


                                                                                     } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("BookInCareOf")) {

                                                                                         Fragment newFragment = new BooksInCareOf(mContext, true);

                                                                                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                         transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                         transaction.replace(R.id.rightFragment, newFragment);

                                                                                         transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                                                                                         transaction.commit();   // TODO: 11/26/2015

                                                                                     } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("PrimaryExemptPurpose")) {

                                                                                         Fragment newFragment = new BooksInCareOf(mContext, true);

                                                                                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                         transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                         transaction.replace(R.id.rightFragment, newFragment);

                                                                                         transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                                                                                         transaction.commit();   // TODO: 11/26/2015

                                                                                     } else if (getAuditResponse.getErrorList().get(position).getACT() != null && getAuditResponse.getErrorList().get(position).getACT().equalsIgnoreCase("ProgramServiceAccomplishment")) {

                                                                                         Fragment newFragment = new BooksInCareOf(mContext, true);

                                                                                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                                                         transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                                                         transaction.replace(R.id.rightFragment, newFragment);

                                                                                         transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                                                                                         transaction.commit();   // TODO: 11/26/2015

                                                                                     } else {


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


                                                                                 }
                                                                             }

                                                   );

                                               }
                                           }

        );


    }

    private void addLeftFragment() {

//// TODO: 11/18/2015

        Fragment newFragment = new TaxLeftFragment(mContext, 3);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.leftFragment, newFragment);


// Commit the transaction
        transaction.commit();

    }

    private void getReviewDetailsById() {


        AuditModel getAuditModel = new AuditModel();

        getAuditModel.setAT(AppConfigManager.getAccessToken(mContext));

        getAuditModel.setUId(AppConfigManager.getLoggedUid(mContext));

        getAuditModel.setRID(AppConfigManager.getReturnRID(mContext));

        getAuditModel.setDId(AppConfigManager.getDID(mContext));

        if (getAuditDetails != null)

            getAuditDetails.cancel(true);

        getAuditDetails = new AuditForm8868_URL(getAuditModel.getAuditDetails(mContext), mContext);

        getAuditDetails.setOnResultListener(getAuditAsync);

        getAuditDetails.execute();

    }

    AuditForm8868_URL.OnAsyncResultAuditForm8868 getAuditAsync = new AuditForm8868_URL.OnAsyncResultAuditForm8868() {


        @Override
        public void onResultSuccess(AuditModel message) {

            getAuditResponse = message;
            handler.post(auditRunnable);

        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    Handler handler = new Handler();

    Runnable auditRunnable = new Runnable() {
        @Override
        public void run() {

            if (getAuditResponse != null && getAuditResponse.getOS() != null && getAuditResponse.getOS().equalsIgnoreCase("Success")) {


                if (getAuditResponse.getErrorList() != null && getAuditResponse.getErrorList().size() > 0 || getAuditResponse.getCautionList() != null && getAuditResponse.getCautionList().size() > 0) {

                    wholeNoErrorLayout.setVisibility(View.GONE);
                    wholeErrorLayout.setVisibility(View.VISIBLE);

                    if (getAuditResponse.getErrorList() != null && getAuditResponse.getErrorList().size() > 0) {

                        Error_layout.setVisibility(View.VISIBLE);
                        NoError_layout.setVisibility(View.GONE);

                        ReviewErrorListAdapter adapter = new ReviewErrorListAdapter(mContext, getAuditResponse.getErrorList(), R.layout.error_adapter, getActivity(), BId, EIN, BN);

//                    LinearLayoutManager llm = new LinearLayoutManager(mContext);
//                    llm.setOrientation(LinearLayoutManager.VERTICAL);
//                    errorlist.setLayoutManager(llm);
                        errorlist.setAdapter(adapter);

                        errorlist.setExpanded(true);

                        adapter.notifyDataSetChanged();


                    } else {

                        ReviewNext.setEnabled(true);

                        Error_layout.setVisibility(View.GONE);
                        NoError_layout.setVisibility(View.VISIBLE);
                    }

                    if (getAuditResponse.getCautionList() != null && getAuditResponse.getCautionList().size() > 0) {

                        ReviewCautionListAdapter adapter = new ReviewCautionListAdapter(mContext, getAuditResponse.getCautionList(), R.layout.error_adapter, getActivity(), BId, EIN, BN);

//                    LinearLayoutManager llm = new LinearLayoutManager(mContext);
//                    llm.setOrientation(LinearLayoutManager.VERTICAL);
//                    cautionList.setLayoutManager(llm);
                        cautionList.setAdapter(adapter);

                        cautionList.setExpanded(true);

                        adapter.notifyDataSetChanged();

                        caution_layout.setVisibility(View.VISIBLE);
                        NoCaution_layout.setVisibility(View.GONE);

                    } else {

                        caution_layout.setVisibility(View.GONE);
                        NoCaution_layout.setVisibility(View.VISIBLE);
                    }
                } else {

                    ReviewNext.setEnabled(true);

                    wholeNoErrorLayout.setVisibility(View.VISIBLE);
                    wholeErrorLayout.setVisibility(View.GONE);


                }

            } else if (getAuditResponse.getOS().equalsIgnoreCase("Failure")) {

                if (getAuditResponse.getEM() != null && !getAuditResponse.getEM().equalsIgnoreCase("null")) {

                    if (getAuditResponse.getEM().equalsIgnoreCase("Access Token is invalid")) {

                        utils.errorMessage(mContext, "Your session is Expired");
                        Logout.logout(mContext);

                    } else {

                        utils.errorMessage(mContext, getAuditResponse.getEM());

                    }
                }
            }

        }
    };

    private void TaxPageFragment() {


        Fragment newFragment = new OrderDetailsFragment(mContext);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.ORDER_DETAIL_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }


    private void setTypeFont() {

        Overridefonts.overrideFonts(mContext, WholeLayout);

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        text2.setTypeface(typeFaceClass.NotoSans_Bold());
        text3.setTypeface(typeFaceClass.NotoSans_Bold());

    }

}

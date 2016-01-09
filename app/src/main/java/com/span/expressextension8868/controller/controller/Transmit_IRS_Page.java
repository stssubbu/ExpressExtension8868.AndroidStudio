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
import com.span.expressextension8868.model.core.CreditModel;
import com.span.expressextension8868.model.core.DatesModel;
import com.span.expressextension8868.model.core.OrderDetailModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.model.core.SummaryModel;
import com.span.expressextension8868.model.core.Transmitmodel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.DueDateURL;
import com.span.expressextension8868.webservice.webservices.OrderDetailURL;
import com.span.expressextension8868.webservice.webservices.SaveOrederDetailURL;
import com.span.expressextension8868.webservice.webservices.Summary_Detail_URL;
import com.span.expressextension8868.webservice.webservices.TransmitServiceURL;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Vector;

/**
 * Created by STS-099 on 12/11/2015.
 */
public class Transmit_IRS_Page extends Fragment {

    Context mContext;

    View CommonTaxView;

    DatabaseHandler databasehandler;

    String EIN, BN;

    Utils utils;

    Double AlertFeeDouble = 0.00, SummaryFeeDouble = 0.00, TotalFeeDouble = 0.00;
    // progress

    private ProgressDialog pd;

    // component

    //componenet

    LinearLayout logolayout, DashboardOrgDetailsLayout, WholeLayout;

    TextView text1, text2, text3, text4, text5, text6, nextText;

    TextView DashBoardTitle, DashboardOrgEin, DashboardOrgName;

    android.support.v7.widget.CardView next;

    TextView sum_ReturnNumber, sum_orgName, sum_orgEIN, sum_orgAddress;

    TextView sum_fromExteded, sum_orgTaxYear, sum_fromTaxPeriod;

    TextView sum_FormActDueDate, sum_fromExtedDueDate;

    TextView sum_FormExtensionType, sum_otherDetails;

    TextView sum_taxTentativeAmount, sum_taxCreditAmount, sum_taxBalanceAmount;

    TextView sum_SKUName, sum_SKUPrice, sum_textAlert, sum_faxAlert, sum_total, faxalerttext, textAlertText;

    TextView card_type, sum_cardNumber, sum_nameOnCard;


    ImageButton sum_orgEditButton, sum_formEditButton, sum_taxyearEditButton, sum_typeEditButton, sum_otherEditButton, sum_bprincipalEditButton, sum_taxEditButton;

    //get detail

    Summary_Detail_URL getDetail;

    SummaryModel getSummaryResponse;
//

    DatesModel datesModel, getdueDatesModel;

    Vector<ReturnModel> returnDetailsParseVector;

    //


    OrderDetailURL getPaymentDetail;

    SaveOrederDetailURL saveDetail;

    OrderDetailModel getOrderDetailResponse, saveOrderDetails, saveOrderDetailResponse;

    //

    CreditModel creditModel;

    Transmitmodel responseModel;

    public Transmit_IRS_Page(Context mContext, CreditModel creditModel) {

        this.mContext = mContext;

        this.creditModel = creditModel;

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            EIN = AppConfigManager.getEIN(mContext);

            BN = AppConfigManager.getBusinessname(mContext);


            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.transmit_irs_page, container, false);


                Initialization();

                onClick();

                setTypeFont();


                addLeftFragment();

                getSummaryDetailsById();

                getOrderDetailsById();

                getCreditCardDetails();


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

        DashBoardTitle.setText("ORDER SUMMARY");

        if (EIN != null && BN != null) {

            DashboardOrgEin.setText(EIN);

            DashboardOrgName.setText(BN);
        }


        next = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.next);

        WholeLayout = (LinearLayout) CommonTaxView.findViewById(R.id.WholeLayout);


        text1 = (TextView) CommonTaxView.findViewById(R.id.text1);
        text2 = (TextView) CommonTaxView.findViewById(R.id.text2);
        text3 = (TextView) CommonTaxView.findViewById(R.id.text3);
        text4 = (TextView) CommonTaxView.findViewById(R.id.text4);
        text5 = (TextView) CommonTaxView.findViewById(R.id.text5);
        text6 = (TextView) CommonTaxView.findViewById(R.id.text6);

        nextText = (TextView) CommonTaxView.findViewById(R.id.nextText);


        sum_ReturnNumber = (TextView) CommonTaxView.findViewById(R.id.sum_ReturnNumber);
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

        sum_taxTentativeAmount = (TextView) CommonTaxView.findViewById(R.id.sum_taxTentativeAmount);
        sum_taxCreditAmount = (TextView) CommonTaxView.findViewById(R.id.sum_taxCreditAmount);
        sum_taxBalanceAmount = (TextView) CommonTaxView.findViewById(R.id.sum_taxBalanceAmount);

        sum_SKUName = (TextView) CommonTaxView.findViewById(R.id.sum_SKUName);
        sum_SKUPrice = (TextView) CommonTaxView.findViewById(R.id.sum_SKUPrice);
        sum_textAlert = (TextView) CommonTaxView.findViewById(R.id.sum_textAlert);
        sum_faxAlert = (TextView) CommonTaxView.findViewById(R.id.sum_faxAlert);
        sum_total = (TextView) CommonTaxView.findViewById(R.id.sum_total);

        faxalerttext = (TextView) CommonTaxView.findViewById(R.id.faxalerttext);
        textAlertText = (TextView) CommonTaxView.findViewById(R.id.textAlertText);

        card_type = (TextView) CommonTaxView.findViewById(R.id.card_type);
        sum_cardNumber = (TextView) CommonTaxView.findViewById(R.id.sum_cardNumber);
        sum_nameOnCard = (TextView) CommonTaxView.findViewById(R.id.sum_nameOnCard);

        sum_orgEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_orgEditButton);
        sum_formEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_formEditButton);
        sum_typeEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_typeEditButton);
        sum_taxyearEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_taxyearEditButton);
        sum_otherEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_otherEditButton);
        sum_bprincipalEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_bprincipalEditButton);
        sum_taxEditButton = (ImageButton) CommonTaxView.findViewById(R.id.sum_taxEditButton);


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

        next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                                        in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                                        JSONObject obj = new JSONObject();
                                        JSONObject obj1 = new JSONObject();

                                        try {
                                            obj.put("AT", AppConfigManager.getAccessToken(mContext));

                                            obj.put("BId", AppConfigManager.getBID(mContext));

                                            obj.put("DId", AppConfigManager.getDID(mContext));

                                            obj.put("RID", AppConfigManager.getReturnRID(mContext));

                                            obj.put("UId", AppConfigManager.getLoggedUid(mContext));

                                            obj.put("Address1", creditModel.getAddress1());

                                            obj.put("Address2", creditModel.getAddress2());

                                            obj.put("CardHolderFirstName", creditModel.getFirstName());

                                            obj.put("CardHolderLastName", creditModel.getLastName());

                                            obj.put("CardNumber", creditModel.getCreditCardnumber());

                                            obj.put("IPADDRESS", Utils.getLocalIpAddress(mContext));

                                            if (creditModel.getCardType().equalsIgnoreCase("visa")) {
                                                obj.put("CardType", "1");
                                            } else if (creditModel.getCardType().equalsIgnoreCase("Master Card")) {
                                                obj.put("CardType", "2");
                                            } else if (creditModel.getCardType().equalsIgnoreCase("Discover")) {
                                                obj.put("CardType", "3");
                                            } else if (creditModel.getCardType().equalsIgnoreCase("American Express")) {
                                                obj.put("CardType", "4");
                                            } else {
                                                obj.put("CardType", "0");
                                            }

                                            obj.put("City", creditModel.getCity());

                                            obj.put("CountryId", creditModel.getCountry());

                                            obj.put("ExpiryMonth", creditModel.getExpirymonth());

                                            obj.put("ExpiryYear", creditModel.getExpiryyear());

                                            obj.put("IsForeignAddress", creditModel.getIsforadd());

                                            obj.put("SecurityCode", creditModel.getCvc());

                                            obj.put("State", creditModel.getStateorprovince());

                                            obj.put("StateId", creditModel.getState());

                                            obj.put("Zip", creditModel.getZip());

                                            obj1.put("MobCreditCard", obj);

                                            Log.i("Transmit Request", obj1.toString());

                                        } catch (Exception e) {

                                            e.printStackTrace();

                                            new SendException(mContext, e);


                                        }


                                        TransmitServiceURL transmitServiceURL = new TransmitServiceURL(obj1.toString(), mContext);

                                        transmitServiceURL.setOnResultListener(transmitInterface);

                                        transmitServiceURL.execute();

                                    }
                                }

        );


        sum_orgEditButton.setOnClickListener(new View.OnClickListener()

                                             {
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
                                             }

        );

        sum_formEditButton.setOnClickListener(new View.OnClickListener()

                                              {
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
                                              }

        );

        sum_typeEditButton.setOnClickListener(new View.OnClickListener()

                                              {
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
                                              }

        );

        sum_taxyearEditButton.setOnClickListener(new View.OnClickListener()

                                                 {
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
                                                 }

        );

        sum_otherEditButton.setOnClickListener(new View.OnClickListener()

                                               {
                                                   @Override
                                                   public void onClick(View v) {


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
                                               }

        );


        sum_bprincipalEditButton.setOnClickListener(new View.OnClickListener()

                                                    {
                                                        @Override
                                                        public void onClick(View v) {

                                                            //// TODO: 11/23/2015

                                                            String value = null;

                                                            try {

                                                                DecimalFormat df = new DecimalFormat("####0.00");
                                                                value = df.format(TotalFeeDouble);

                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                                new SendException(mContext, e);
                                                            }

                                                            Fragment newFragment = new PaymentFragment(mContext, value, getOrderDetailResponse.ISPAID());

                                                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                                                            transaction.replace(R.id.rightFragment, newFragment);

                                                            transaction.addToBackStack(FragmentNameConfig.PAYMENT_DETAIL_FRAGMENT);

// Commit the transaction
                                                            transaction.commit();

                                                        }
                                                    }

        );

        sum_taxEditButton.setOnClickListener(new View.OnClickListener()

                                             {
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
                                             }

        );


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


    Runnable getRunnable = new Runnable() {
        @Override
        public void run() {

            if (getSummaryResponse != null && getSummaryResponse.getOS() != null && getSummaryResponse.getOS().equalsIgnoreCase("Success")) {

                if (getSummaryResponse.getRN() != null && !getSummaryResponse.getRN().equalsIgnoreCase("null") && getSummaryResponse.getRN().length() > 0) {

                    sum_ReturnNumber.setText(getSummaryResponse.getRN());
                }


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

                    } else

                        sum_orgAddress.setText("");
                }


                String add1 = null;
                try {


                    add1 = utils.CityStateOnly(mContext, getSummaryResponse.getCITY(), getSummaryResponse.getSC(), getSummaryResponse.getSN(), getSummaryResponse.getBCOCountry(), getSummaryResponse.getZIP(), getSummaryResponse.ISFORADD());

                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);

                }

                if (add1 != null && !add1.equalsIgnoreCase("null")) {

                    sum_orgAddress.append(", " + add1);

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

                if (getSummaryResponse.getFC() != null && getSummaryResponse.getFC().length() > 0 && getSummaryResponse.getFC().equalsIgnoreCase("12")) {

                    nextText.setText("Pay to File");

                } else {

                    nextText.setText("Pay and Transmit to the IRS");

                }
                if (getSummaryResponse.getExtensionType() != null && getSummaryResponse.getExtensionType().length() > 0 && getSummaryResponse.getExtensionType().equalsIgnoreCase("THREEMONTH")) {

                    datesModel = new DatesModel();

                    String formCodeid = getSummaryResponse.getFC();

                    datesModel.setFORMCODEID(formCodeid);
                    datesModel.setTYED(getSummaryResponse.getTYED());
                    datesModel.setTY(getSummaryResponse.getTY());

                    sum_FormExtensionType.setText("Automatic 3 month Extension");

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

                if (getSummaryResponse.ISFC()) {

                    if (getSummaryResponse.getBN() != null && !getSummaryResponse.getBN().equalsIgnoreCase("null") && getSummaryResponse.getBN().length() > 0)

                        sum_otherDetails.setText(getSummaryResponse.getBN() + " is a Foreign Corporation.");

                } else {
                    if (getSummaryResponse.getBN() != null && !getSummaryResponse.getBN().equalsIgnoreCase("null") && getSummaryResponse.getBN().length() > 0)

                        sum_otherDetails.setText(getSummaryResponse.getBN() + " is a US Based Organization.");


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

            } else if (getSummaryResponse != null && getSummaryResponse.getOS().equalsIgnoreCase("Failure")) {

                if (getSummaryResponse.getEM() != null && !getSummaryResponse.getEM().equalsIgnoreCase("null")) {

                    if (getSummaryResponse.getEM().equalsIgnoreCase("Access Token is invalid")) {

                        utils.errorMessage(mContext, "Your session is Expired");
                        Logout.logout(mContext);

                    } else {

                        utils.errorMessage(mContext, getSummaryResponse.getEM());

                    }
                }
            } else {
                utils.errorMessage(mContext, "Something wrong. Please try again!");
            }
        }
    };


    private void addLeftFragment() {

//// TODO: 11/18/2015

        Fragment newFragment = new TaxLeftFragment(mContext, 5);

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

    private void getOrderDetailsById() {

        OrderDetailModel getOrderDetailModel = new OrderDetailModel();

        getOrderDetailModel.setAT(AppConfigManager.getAccessToken(mContext));

        getOrderDetailModel.setUId(AppConfigManager.getLoggedUid(mContext));

        getOrderDetailModel.setRID(AppConfigManager.getReturnRID(mContext));

        getOrderDetailModel.setDId(AppConfigManager.getDID(mContext));

        if (getPaymentDetail != null)

            getPaymentDetail.cancel(true);

        getPaymentDetail = new OrderDetailURL(getOrderDetailModel.getOrderDetails(mContext), mContext);

        getPaymentDetail.setOnResultListener(getOrderDetailAsync);

        getPaymentDetail.execute();

    }

    OrderDetailURL.OnAsyncResultGetOrderDetail getOrderDetailAsync = new OrderDetailURL.OnAsyncResultGetOrderDetail() {

        @Override
        public void onResultSuccess(OrderDetailModel message) {

            getOrderDetailResponse = message;

            handler.post(getPamentRunnable);

        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    Handler handler = new Handler();

    Runnable getPamentRunnable = new Runnable() {
        @Override
        public void run() {

            if (getOrderDetailResponse != null) {

                if (getOrderDetailResponse.getOS() != null && getOrderDetailResponse.getOS().equalsIgnoreCase("Success")) {

                    if (getOrderDetailResponse.getSKU_Name() != null && !getOrderDetailResponse.getSKU_Name().equalsIgnoreCase("null"))

                        sum_SKUName.setText(getOrderDetailResponse.getSKU_Name());


                    if (getOrderDetailResponse.getPrice() != null && !getOrderDetailResponse.getPrice().equalsIgnoreCase("null")) {
                        try {

                            sum_SKUPrice.setText(utils.decimalAmountValue(mContext, getOrderDetailResponse.getPrice()));

                        } catch (Exception e) {

                            e.printStackTrace();
                            new SendException(mContext, e);

                        }

                    }


                    if (getOrderDetailResponse.isTextAlertPaid()) {
                        if (getOrderDetailResponse.getTextAlertPrice() != null && !getOrderDetailResponse.getTextAlertPrice().equalsIgnoreCase("null"))
                            sum_textAlert.setText(utils.decimalAmountValue(mContext, getOrderDetailResponse.getTextAlertPrice()));

                        if (getOrderDetailResponse.getTextPhoneNumber() != null && !getOrderDetailResponse.getTextPhoneNumber().equalsIgnoreCase("null"))

                            textAlertText.append("  " + getOrderDetailResponse.getTextPhoneNumber());


                    } else {

                        sum_textAlert.setText(utils.decimalAmountValue(mContext, "0.00"));

                    }

                    if (getOrderDetailResponse.isFaxAlertPaid()) {

                        if (getOrderDetailResponse.getFaxAlertPrice() != null && !getOrderDetailResponse.getFaxAlertPrice().equalsIgnoreCase("null"))
                            sum_faxAlert.setText(utils.decimalAmountValue(mContext, getOrderDetailResponse.getFaxAlertPrice()));

                        if (getOrderDetailResponse.getFaxNumber() != null && !getOrderDetailResponse.getFaxNumber().equalsIgnoreCase("null"))

                            faxalerttext.append("  " + getOrderDetailResponse.getFaxNumber());

                    } else {

                        sum_faxAlert.setText(utils.decimalAmountValue(mContext, "0.00"));

                    }

                    setOtherFee();


                } else if (getOrderDetailResponse.getOS().equalsIgnoreCase("Failure")) {

                    if (getOrderDetailResponse.getEM() != null && !getOrderDetailResponse.getEM().equalsIgnoreCase("null")) {

                        if (getOrderDetailResponse.getEM().equalsIgnoreCase("Access Token is invalid")) {

                            utils.errorMessage(mContext, "Your session is Expired");
                            Logout.logout(mContext);

                        } else {

                            utils.errorMessage(mContext, getOrderDetailResponse.getEM());

                        }
                    }
                }


            } else {

                utils.errorMessage(mContext, "Please Try again Later");
            }

        }
    };

    private void setOtherFee() {

        AlertFeeDouble = 0.00;

        SummaryFeeDouble = 0.00;

        try {


            if (sum_textAlert != null && sum_textAlert.getText() != null && sum_textAlert.getText().toString().trim().length() > 0)

                AlertFeeDouble = AlertFeeDouble + Double.parseDouble(sum_textAlert.getText().toString().replace("$", ""));


        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

        try {

            if (sum_faxAlert != null && sum_faxAlert.getText() != null && sum_faxAlert.getText().toString().trim().length() > 0)

                AlertFeeDouble = AlertFeeDouble + Double.parseDouble(sum_faxAlert.getText().toString().replace("$", ""));
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
        try {

            if (sum_SKUPrice != null && sum_SKUPrice.getText() != null && sum_SKUPrice.getText().toString().trim().length() > 0)

                SummaryFeeDouble = SummaryFeeDouble + Double.parseDouble(sum_SKUPrice.getText().toString().replace("$", ""));

            SummaryFeeDouble = SummaryFeeDouble + AlertFeeDouble;

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

        try {

            TotalFeeDouble = SummaryFeeDouble;

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

        try {

            DecimalFormat df = new DecimalFormat("####0.00");
            String value = df.format(TotalFeeDouble);
            sum_total.setText(utils.decimalAmountValue(mContext, value));

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

    }

    private void getCreditCardDetails() {

        try {

            if (creditModel.getCardType().equalsIgnoreCase("Visa")) {
                card_type.setBackgroundResource(R.drawable.visa);
            } else if (creditModel.getCardType().equalsIgnoreCase("Master Card")) {
                card_type.setBackgroundResource(R.drawable.master);
            } else if (creditModel.getCardType().equalsIgnoreCase("Discover")) {
                card_type.setBackgroundResource(R.drawable.disnet);
            } else if (creditModel.getCardType().equalsIgnoreCase("American Express")) {
                card_type.setBackgroundResource(R.drawable.ae);
            } else {
                card_type.setBackgroundResource(R.drawable.creditempty);
            }


            String lastNum = null;

            if (creditModel.getCreditCardnumber() != null) {

                lastNum = creditModel.getCreditCardnumber().substring(creditModel.getCreditCardnumber().length() - 5);

            }
            sum_cardNumber.setText(lastNum);
            sum_nameOnCard.setText(creditModel.getFirstName());

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
    }

    TransmitServiceURL.TransmitInterface transmitInterface = new TransmitServiceURL.TransmitInterface() {

        @Override
        public void onResultSuccess(final Transmitmodel message) {

            responseModel = message;

            handler.post(transmitRunnable);
        }

    };

    Runnable transmitRunnable = new Runnable() {
        @Override
        public void run() {

            if (responseModel != null && responseModel.getosfield().equalsIgnoreCase("Success")) {

                AppConfigManager.saveFirstMode(mContext, "FIRST");

                AppConfigManager.saveMode(mContext, "VIEW");

                utils.successMessage(mContext, "Transmitted Successfully");

                TransmitSuccessFragmentPage();

//                Intent manage = new Intent(TransmittoIRSActivity.this, FinalScreen.class);
//
//                startActivity(manage);
//
//                finish();
            } else if (responseModel.getosfield().equalsIgnoreCase("Failure") && !responseModel.getEM().equalsIgnoreCase("null") && responseModel.getEM().equalsIgnoreCase("Access Token is invalid")) {

                utils.errorMessage(mContext, "Your session is Expired");
                Logout.logout(mContext);

            } else if (responseModel.getosfield().equalsIgnoreCase("Failure") && !responseModel.getEM().equalsIgnoreCase("null") && !responseModel.getEM().equalsIgnoreCase("Access Token is invalid")) {
                utils.errorMessage(mContext, responseModel.getEM());

            }

        }
    };

    private void TransmitSuccessFragmentPage() {

        Fragment newFragment = new TransmitSuccessFragment(mContext);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.TRANSMIT_FRAGMENT);

        transaction.commit();

    }

    private void setTypeFont() {

        Overridefonts.overrideFonts(mContext, WholeLayout);

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        text2.setTypeface(typeFaceClass.NotoSans_Bold());
        text3.setTypeface(typeFaceClass.NotoSans_Bold());
        text4.setTypeface(typeFaceClass.NotoSans_Bold());
        text5.setTypeface(typeFaceClass.NotoSans_Bold());
        text6.setTypeface(typeFaceClass.NotoSans_Italic());

        nextText.setTypeface(typeFaceClass.NotoSans_Regular());


    }

}

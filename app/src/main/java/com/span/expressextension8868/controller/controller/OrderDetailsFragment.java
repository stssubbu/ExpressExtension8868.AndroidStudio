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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.OrderDetailModel;
import com.span.expressextension8868.model.core.SummaryModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.OrderDetailURL;
import com.span.expressextension8868.webservice.webservices.SaveOrederDetailURL;
import com.span.expressextension8868.webservice.webservices.Summary_Detail_URL;

import java.text.DecimalFormat;

/**
 * Created by STS-099 on 12/9/2015.
 */
public class OrderDetailsFragment extends Fragment {

    Context mContext;

    View CommonTaxView;

    DatabaseHandler databasehandler;

    Utils utils;

    String mobilePrice, FaxPrice, EIN, BN;


    Double AlertFeeDouble, SummaryFeeDouble, TotalFeeDouble, DiscountFeeDouble;

    // progress

    private ProgressDialog pd;

    // component

    LinearLayout logolayout, DashboardOrgDetailsLayout, WholeLayout;

    TextView DashBoardTitle, DashboardOrgEin, DashboardOrgName;


    android.support.v7.widget.CardView orderNext, orderCancel;

    TextView orderNextText, orderCancelText, text1;

    TextView SKUName, SKUDescription, SKUPrice, SKUTotalPrice;

    CheckBox MobileAlertCheckBox, FaxAlertCheckBox;

    TextView MobileAlertPrice, MobileNumberImport, FaxAlertPrice, FaxAlertImport, AlertFee, SummaryFee;

    com.rengwuxian.materialedittext.MaterialEditText MobileNumberEditText, FaxNumberEditText;

    TextView DiscountIcon, DiscountTitle, DiscountFee, TotalFees;

    LinearLayout DiscountLayout;

    com.rengwuxian.materialedittext.MaterialEditText DiscountCode;

    android.support.v7.widget.CardView DiscountApplybutton;

    //

    OrderDetailURL getDetail;

    SaveOrederDetailURL saveDetail;

    OrderDetailModel getOrderDetailResponse, saveOrderDetails, saveOrderDetailResponse;

    public OrderDetailsFragment(Context mContext) {

        this.mContext = mContext;

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            EIN = AppConfigManager.getEIN(mContext);

            BN = AppConfigManager.getBusinessname(mContext);


            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.order_details, container, false);


                Initialization();

                onClick();

                setTypeFont();


                addLeftFragment();

                getOrderDetailsById();


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


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
        DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
        DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

        DashBoardTitle.setText("YOUR PAYMENT OPTIONS");

        if (EIN != null && BN != null) {

            DashboardOrgEin.setText(EIN);

            DashboardOrgName.setText(BN);
        }


        orderNext = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.orderNext);
        orderCancel = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.orderCancel);

        WholeLayout = (LinearLayout) CommonTaxView.findViewById(R.id.WholeLayout);

        orderNextText = (TextView) CommonTaxView.findViewById(R.id.orderNextText);
        orderCancelText = (TextView) CommonTaxView.findViewById(R.id.orderCancelText);
        text1 = (TextView) CommonTaxView.findViewById(R.id.text1);


        SKUName = (TextView) CommonTaxView.findViewById(R.id.SKUName);
        SKUDescription = (TextView) CommonTaxView.findViewById(R.id.SKUDescription);
        SKUPrice = (TextView) CommonTaxView.findViewById(R.id.SKUPrice);
        SKUTotalPrice = (TextView) CommonTaxView.findViewById(R.id.SKUTotalPrice);


        MobileAlertCheckBox = (CheckBox) CommonTaxView.findViewById(R.id.MobileAlertCheckBox);
        FaxAlertCheckBox = (CheckBox) CommonTaxView.findViewById(R.id.FaxAlertCheckBox);

        MobileAlertPrice = (TextView) CommonTaxView.findViewById(R.id.MobileAlertPrice);
        MobileNumberImport = (TextView) CommonTaxView.findViewById(R.id.MobileNumberImport);
        FaxAlertPrice = (TextView) CommonTaxView.findViewById(R.id.FaxAlertPrice);
        FaxAlertImport = (TextView) CommonTaxView.findViewById(R.id.FaxAlertImport);
        AlertFee = (TextView) CommonTaxView.findViewById(R.id.AlertFee);
        SummaryFee = (TextView) CommonTaxView.findViewById(R.id.SummaryFee);

        MobileNumberEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.MobileNumberEditText);
        FaxNumberEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.FaxNumberEditText);

        DiscountIcon = (TextView) CommonTaxView.findViewById(R.id.DiscountIcon);
        DiscountTitle = (TextView) CommonTaxView.findViewById(R.id.DiscountTitle);
        DiscountFee = (TextView) CommonTaxView.findViewById(R.id.DiscountFee);
        TotalFees = (TextView) CommonTaxView.findViewById(R.id.TotalFees);

        DiscountLayout = (LinearLayout) CommonTaxView.findViewById(R.id.DiscountLayout);

        DiscountCode = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.DiscountCode);

        DiscountApplybutton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.DiscountApplybutton);

    }

    private void textClear() {

        SKUName.setText("");
        SKUDescription.setText("");
        SKUPrice.setText("");
        SKUTotalPrice.setText("");

        MobileAlertCheckBox.setChecked(false);
        FaxAlertCheckBox.setChecked(false);

        MobileAlertPrice.setText("");
        MobileNumberImport.setVisibility(View.INVISIBLE);
        FaxAlertPrice.setText("");
        FaxAlertImport.setVisibility(View.INVISIBLE);
        AlertFee.setText("");
        SummaryFee.setText("");

        MobileNumberEditText.setText("");
        FaxNumberEditText.setText("");

        DiscountIcon.setBackgroundResource(R.drawable.show);
        DiscountFee.setText("");
        TotalFees.setText("");

        DiscountLayout.setVisibility(View.GONE);

        DiscountCode.setText("");

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

        orderNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                saveOrderDetails = new OrderDetailModel();


                if (validationCheck()) {


                    saveOrderDetails.setAT(AppConfigManager.getAccessToken(mContext));

                    saveOrderDetails.setUId(AppConfigManager.getLoggedUid(mContext));

                    saveOrderDetails.setRID(AppConfigManager.getReturnRID(mContext));

                    saveOrderDetails.setDId(AppConfigManager.getDID(mContext));

                    saveOrderDetails.setSKUId(getOrderDetailResponse.getSKUId());


                    if (saveDetail != null)

                        saveDetail.cancel(true);

                    saveDetail = new SaveOrederDetailURL(saveOrderDetails.saveOrderDetails(mContext), mContext);

                    saveDetail.setOnResultListener(saveOrderDetailAsync);

                    saveDetail.execute();

                }


            }
        });

        orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                FragmentManager fm = getActivity().getSupportFragmentManager();


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

        MobileAlertCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    mobilePrice = utils.decimalAmountValue(mContext, getOrderDetailResponse.getTextAlertPrice());

                    if (mobilePrice != null)
                        MobileAlertPrice.setText(mobilePrice);

                    else
                        MobileAlertPrice.setText("");

                    MobileNumberImport.setVisibility(View.VISIBLE);

                    MobileNumberEditText.requestFocus();

                } else {

                    MobileNumberEditText.clearFocus();
                    MobileNumberEditText.setText("");

                    mobilePrice = utils.decimalAmountValue(mContext, "0.00");

                    if (mobilePrice != null)
                        MobileAlertPrice.setText(mobilePrice);

                    else
                        MobileAlertPrice.setText("");

                    MobileNumberImport.setVisibility(View.INVISIBLE);
                }


                setOtherFee();

            }
        });


        FaxAlertCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {


                    FaxAlertImport.setVisibility(View.VISIBLE);

                    FaxNumberEditText.requestFocus();

                    FaxPrice = utils.decimalAmountValue(mContext, getOrderDetailResponse.getFaxAlertPrice());

                    if (FaxPrice != null)
                        FaxAlertPrice.setText(FaxPrice);

                    else
                        FaxAlertPrice.setText("");


                } else {

                    FaxNumberEditText.clearFocus();
                    FaxNumberEditText.setText("");

                    FaxPrice = utils.decimalAmountValue(mContext, "0.00");

                    if (FaxPrice != null)
                        FaxAlertPrice.setText(FaxPrice);

                    else
                        FaxAlertPrice.setText("");

                    FaxAlertImport.setVisibility(View.INVISIBLE);
                }

                setOtherFee();

            }
        });

        MobileNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s != null && s.length() > 0) {

                    MobileAlertCheckBox.setChecked(true);
                    MobileNumberImport.setVisibility(View.VISIBLE);

                } else {

                    MobileAlertCheckBox.setChecked(false);
                    MobileNumberImport.setVisibility(View.INVISIBLE);
                }

                setOtherFee();

            }

            @Override
            public void afterTextChanged(Editable s) {

                utils.MaskingPhone(mContext, MobileNumberEditText);

            }
        });

        FaxNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s != null && s.length() > 0) {

                    FaxAlertCheckBox.setChecked(true);
                    FaxAlertImport.setVisibility(View.VISIBLE);

                    utils.MaskingPhone(mContext, FaxNumberEditText);

                } else {

                    FaxAlertCheckBox.setChecked(false);
                    FaxAlertImport.setVisibility(View.INVISIBLE);
                }

                setOtherFee();

            }

            @Override
            public void afterTextChanged(Editable s) {

                utils.MaskingPhone(mContext, FaxNumberEditText);

            }
        });

        DiscountIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                discountClick();

            }
        });

        DiscountTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                discountClick();

            }
        });

        DiscountApplybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 12/9/2015
            }
        });

    }

    private void discountClick() {

        if (DiscountLayout.getVisibility() == View.VISIBLE) {

            DiscountLayout.setVisibility(View.GONE);
            DiscountIcon.setBackgroundResource(R.drawable.show);

        } else {

            DiscountLayout.setVisibility(View.VISIBLE);
            DiscountIcon.setBackgroundResource(R.drawable.hide);

        }

    }

    private void getOrderDetailsById() {

        OrderDetailModel getOrderDetailModel = new OrderDetailModel();

        getOrderDetailModel.setAT(AppConfigManager.getAccessToken(mContext));

        getOrderDetailModel.setUId(AppConfigManager.getLoggedUid(mContext));

        getOrderDetailModel.setRID(AppConfigManager.getReturnRID(mContext));

        getOrderDetailModel.setDId(AppConfigManager.getDID(mContext));

        if (getDetail != null)

            getDetail.cancel(true);

        getDetail = new OrderDetailURL(getOrderDetailModel.getOrderDetails(mContext), mContext);

        getDetail.setOnResultListener(getOrderDetailAsync);

        getDetail.execute();

    }

    OrderDetailURL.OnAsyncResultGetOrderDetail getOrderDetailAsync = new OrderDetailURL.OnAsyncResultGetOrderDetail() {

        @Override
        public void onResultSuccess(OrderDetailModel message) {

            getOrderDetailResponse = message;

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

            if (getOrderDetailResponse != null) {

                if (getOrderDetailResponse.getOS() != null && getOrderDetailResponse.getOS().equalsIgnoreCase("Success")) {

                    if (getOrderDetailResponse.getSKU_Name() != null && !getOrderDetailResponse.getSKU_Name().equalsIgnoreCase("null"))

                        SKUName.setText(getOrderDetailResponse.getSKU_Name());


                    if (getOrderDetailResponse.getSKU_Description() != null && !getOrderDetailResponse.getSKU_Description().equalsIgnoreCase("null"))

                        SKUDescription.setText(getOrderDetailResponse.getSKU_Description());

                    if (getOrderDetailResponse.getPrice() != null && !getOrderDetailResponse.getPrice().equalsIgnoreCase("null")) {
                        try {

                            SKUPrice.setText(utils.decimalAmountValue(mContext, getOrderDetailResponse.getPrice()));
                            SKUTotalPrice.setText(utils.decimalAmountValue(mContext, getOrderDetailResponse.getPrice()));

                        } catch (Exception e) {

                            e.printStackTrace();
                            new SendException(mContext, e);

                        }

                    }


                    MobileAlertCheckBox.setChecked(getOrderDetailResponse.isTextAlertPaid());
                    FaxAlertCheckBox.setChecked(getOrderDetailResponse.isTextAlertPaid());

                    if (getOrderDetailResponse.isTextAlertPaid()) {
                        if (getOrderDetailResponse.getTextAlertPrice() != null && !getOrderDetailResponse.getTextAlertPrice().equalsIgnoreCase("null"))
                            MobileAlertPrice.setText(utils.decimalAmountValue(mContext, getOrderDetailResponse.getTextAlertPrice()));

                        if (getOrderDetailResponse.getTextPhoneNumber() != null && !getOrderDetailResponse.getTextPhoneNumber().equalsIgnoreCase("null"))
                            MobileNumberEditText.setText(getOrderDetailResponse.getTextPhoneNumber());

                        MobileNumberImport.setVisibility(View.VISIBLE);
                    } else {
                        MobileAlertPrice.setText(utils.decimalAmountValue(mContext, "0.00"));
                        MobileNumberEditText.setText("");
                        MobileNumberImport.setVisibility(View.INVISIBLE);
                    }

                    if (getOrderDetailResponse.isFaxAlertPaid()) {
                        if (getOrderDetailResponse.getFaxAlertPrice() != null && !getOrderDetailResponse.getFaxAlertPrice().equalsIgnoreCase("null"))
                            FaxAlertPrice.setText(utils.decimalAmountValue(mContext, getOrderDetailResponse.getFaxAlertPrice()));

                        if (getOrderDetailResponse.getFaxNumber() != null && !getOrderDetailResponse.getFaxNumber().equalsIgnoreCase("null"))
                            FaxNumberEditText.setText(getOrderDetailResponse.getFaxNumber());

                        FaxAlertImport.setVisibility(View.VISIBLE);
                    } else {
                        FaxAlertPrice.setText(utils.decimalAmountValue(mContext, "0.00"));
                        FaxNumberEditText.setText("");
                        FaxAlertImport.setVisibility(View.INVISIBLE);
                    }

                    setOtherFee();

                    DiscountIcon.setBackgroundResource(R.drawable.show);
                    DiscountFee.setText("");

                    DiscountLayout.setVisibility(View.GONE);

                    DiscountCode.setText("");


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


            if (MobileAlertPrice != null && MobileAlertPrice.getText() != null && MobileAlertPrice.getText().toString().trim().length() > 0)

                AlertFeeDouble = AlertFeeDouble + Double.parseDouble(MobileAlertPrice.getText().toString().replace("$", ""));


        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

        try {

            if (FaxAlertPrice != null && FaxAlertPrice.getText() != null && FaxAlertPrice.getText().toString().trim().length() > 0)

                AlertFeeDouble = AlertFeeDouble + Double.parseDouble(FaxAlertPrice.getText().toString().replace("$", ""));
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
        try {

            if (SKUTotalPrice != null && SKUTotalPrice.getText() != null && SKUTotalPrice.getText().toString().trim().length() > 0)

                SummaryFeeDouble = SummaryFeeDouble + Double.parseDouble(SKUTotalPrice.getText().toString().replace("$", ""));

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
            String value = df.format(AlertFeeDouble);

            AlertFee.setText(utils.decimalAmountValue(mContext, value));

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

        try {

            DecimalFormat df = new DecimalFormat("####0.00");
            String value = df.format(SummaryFeeDouble);

            SummaryFee.setText(utils.decimalAmountValue(mContext, value));

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

        try {

            DecimalFormat df = new DecimalFormat("####0.00");
            String value = df.format(TotalFeeDouble);

            TotalFees.setText(utils.decimalAmountValue(mContext, value));

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
    }

    private boolean validationCheck() {

        boolean value = true;


        saveOrderDetails.setIsTextAlertPaid(MobileAlertCheckBox.isChecked());
        saveOrderDetails.setIsFaxAlertPaid(FaxAlertCheckBox.isChecked());

        if (MobileAlertCheckBox.isChecked()) {

            if (MobileNumberEditText.getText() != null && MobileNumberEditText.getText().toString().trim().length() > 0) {

                saveOrderDetails.setTextPhoneNumber(MobileNumberEditText.getText().toString());

            } else {
                value = false;

                MobileNumberEditText.setError("Mobile Number is required");
            }
        }

        if (FaxAlertCheckBox.isChecked()) {
            if (FaxNumberEditText.getText() != null && FaxNumberEditText.getText().toString().trim().length() > 0) {

                saveOrderDetails.setFaxNumber(FaxNumberEditText.getText().toString());

            } else {
                value = false;

                FaxNumberEditText.setError("Fax Number is required");
            }
        }


        return value;
    }

    SaveOrederDetailURL.OnAsyncResultSaveOrderDetail saveOrderDetailAsync = new SaveOrederDetailURL.OnAsyncResultSaveOrderDetail() {

        @Override
        public void onResultSuccess(OrderDetailModel message) {

            saveOrderDetailResponse = message;

            handler.post(saveRunnable);

        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };


    Runnable saveRunnable = new Runnable() {
        @Override
        public void run() {

            if (saveOrderDetailResponse != null) {
                if (getOrderDetailResponse.getOS() != null && getOrderDetailResponse.getOS().equalsIgnoreCase("Success")) {


                    if (getOrderDetailResponse.ISPAID() && getOrderDetailResponse.getShoppingCartId() > 0) {

                        TaxPageFragment();

                    } else {

                        utils.successMessage(mContext, "Your Payment detail saved successfully");
                        SummaryPageFragment();


                    }


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


    private void addLeftFragment() {

//// TODO: 11/18/2015

        Fragment newFragment = new TaxLeftFragment(mContext, 4);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.leftFragment, newFragment);


// Commit the transaction
        transaction.commit();

    }

    private void SummaryPageFragment() {

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

    private void TaxPageFragment() {


        Fragment newFragment = new Transmit_IRS_Page(mContext, null);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.REVIEW_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }


    private void setTypeFont() {

        Overridefonts.overrideFonts(mContext, WholeLayout);


        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        orderNextText.setTypeface(typeFaceClass.NotoSans_Regular());
        orderCancelText.setTypeface(typeFaceClass.NotoSans_Regular());

    }


}

package com.span.expressextension8868.controller.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.businesslogic.parsing.DeleteHoldingCompanyService;
import com.span.expressextension8868.model.core.DatesModel;
import com.span.expressextension8868.model.core.HoldingModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.DisableEdittext;
import com.span.expressextension8868.utils.utility.ExtensionTypeEnum;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.NetworkChangeReceiver;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.AllowReturnByIdURL;
import com.span.expressextension8868.webservice.webservices.DueDateURL;
import com.span.expressextension8868.webservice.webservices.GetHoldingCompanyURL;
import com.span.expressextension8868.webservice.webservices.GetReturnDetailsURL;
import com.span.expressextension8868.webservice.webservices.Save8868ExtensionType;
import com.span.expressextension8868.webservice.webservices.SaveHoldingCompanyURL;

import java.util.ArrayList;
import java.util.Vector;

import static com.span.expressextension8868.utils.utility.ExtensionTypeEnum.valueOf;

/**
 * Created by STS-099 on 11/19/2015.
 */
public class ExtensionTypeFragment extends Fragment {

    Context mContext;

    View CommonTaxView;


    Utils utils;

    String BId, EIN, BN, Mode;

    int FCId = 0;


    // progress

    private ProgressDialog pd;

    //get value

    ReturnModel getreturnModel, returnmodel, getAllowResponse;

    String formCodeid = "0", TYBD, TYED, TY, ExtensionType = "THREEMONTH";

    DatesModel datesModel, getdueDatesModel;

    Vector<ReturnModel> returnDetailsParseVector;

    //componenet


    LinearLayout logolayout, DashboardOrgDetailsLayout, wholeLayout;

    TextView DashBoardTitle, DashboardOrgEin, DashboardOrgName, selectreasontextview, text1, text2, textNote1, textNote2, autoHint, notAutoHint;

    android.support.v7.widget.CardView ETnext, ETCancelbutton, addgroupButton;

    RadioGroup extensionGroupRadioButton;

    RadioButton automaticradio, notautomaticradio, reasonunavoidradio, reasonreconstuctradio, reasonnotsufficientTimeradio, reasonOtherradio;

    EditText otherreasonEdittext;

    LinearLayout automatic, notautomaticlayout, selectreasonlayout;

    TextView autoactualduedatedata, autoextendedduedatedata, NotAutoactualduedatedata, NotAutoextendedduedatedata;

    //component 1

    LinearLayout groupReturnlayout, groupexapnd, isPartofGroupLayout, addPartofGroupLayout, countrySpinnerLayout;

    CheckBox isforeigncheckbox, isgroupreturnCheckBox;

    EditText groupexNumEditText;

    RadioGroup groupRadioGroup;

    RadioButton wholegroupRadio, partofgroupRadio;


    //add dialog

    AlertDialog deleteDialog;

    //TextView's

    TextView addButtonText;
    // EditText's

    com.rengwuxian.materialedittext.MaterialEditText BusinessNameeditText, EineditText, addressEdittext1, addresseditText2, cityeditText, stateorprovince, zipeditText;

    // Spinners's
    com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner noeinspinner, stateSpinner, countrySpinner;

    //Button

    android.support.v7.widget.CardView addButton;

    // ListView
    ListView holdinglistView;

    //CheckBox

    CheckBox noeincheckbox, isaddoutsidecheckbox;

    // Database Helper class
    DatabaseHandler databasehandler;

    // Flag's for Masking EIN's
    Boolean flag = true;

    //Linear Layout

    LinearLayout rootlayout, norecordsfound;

    //String's

    String countryid = "0", stateid = "0";

    HoldingModel holdingModelSave = new HoldingModel();

    // ImageView's

    ImageView closeicon;

    int HoldingCount = 0, EditTag = 0;

    String HoldingCompanyID = "0";

    NetworkChangeReceiver receiver;

    ArrayList<String> receivedValue;

    HoldingModel getHoldingcompanyModel, saveHoldingcompanyModel;

    String LGT;


    ///   HoldingPopUpActivity.HoldingCountListener holdingCountListener;


    public ExtensionTypeFragment(Context mContext) {

        this.mContext = mContext;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));

        try {

            EIN = AppConfigManager.getEIN(mContext);

            BN = AppConfigManager.getBusinessname(mContext);

            BId = AppConfigManager.getBID(mContext);

            Mode = AppConfigManager.getMode(mContext);

            CommonTaxView = inflater.inflate(R.layout.extensiontype, container, false);

            InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

            in.hideSoftInputFromWindow(CommonTaxView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            Initialization();

            onClick();

            addLeftFragment(0);

            Overridefonts.overrideFonts(mContext, wholeLayout);

            setTypeFont();

            GetReturnDetailsByID();

            return CommonTaxView;

        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(getActivity(), e);
        }

        return null;
    }

    private void Initialization() {

        utils = new Utils();


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
        DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
        DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

        DashBoardTitle.setText("TYPE OF EXTENSION & OTHER OPTIONS");

        if (EIN != null && BN != null) {

            DashboardOrgEin.setText(EIN);

            DashboardOrgName.setText(BN);

            DashboardOrgDetailsLayout.setVisibility(View.VISIBLE);


        } else

            DashboardOrgDetailsLayout.setVisibility(View.GONE);

        wholeLayout = (LinearLayout) CommonTaxView.findViewById(R.id.wholeLayout);
        text1 = (TextView) CommonTaxView.findViewById(R.id.text1);
        text2 = (TextView) CommonTaxView.findViewById(R.id.text2);
        textNote1 = (TextView) CommonTaxView.findViewById(R.id.textNote1);
        textNote2 = (TextView) CommonTaxView.findViewById(R.id.textNote2);
        autoHint = (TextView) CommonTaxView.findViewById(R.id.autoHint);
        notAutoHint = (TextView) CommonTaxView.findViewById(R.id.notAutoHint);

        selectreasontextview = (TextView) CommonTaxView.findViewById(R.id.selectreasontextview);
        Utils.setfirstletterred(selectreasontextview.getText().toString(), selectreasontextview, mContext);

        ETCancelbutton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.ETCancelbutton);

        ETnext = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.ETnext);

        extensionGroupRadioButton = (RadioGroup) CommonTaxView.findViewById(R.id.extensionGroupRadioButton);
        automaticradio = (RadioButton) CommonTaxView.findViewById(R.id.automaticradio);
        notautomaticradio = (RadioButton) CommonTaxView.findViewById(R.id.notautomaticradio);

        reasonunavoidradio = (RadioButton) CommonTaxView.findViewById(R.id.reasonunavoidradio);
        reasonreconstuctradio = (RadioButton) CommonTaxView.findViewById(R.id.reasonreconstuctradio);
        reasonnotsufficientTimeradio = (RadioButton) CommonTaxView.findViewById(R.id.reasonnotsufficientTimeradio);
        reasonOtherradio = (RadioButton) CommonTaxView.findViewById(R.id.reasonOtherradio);

        otherreasonEdittext = (EditText) CommonTaxView.findViewById(R.id.otherreasonEdittext);

        automatic = (LinearLayout) CommonTaxView.findViewById(R.id.automatic);
        notautomaticlayout = (LinearLayout) CommonTaxView.findViewById(R.id.notautomaticlayout);
        automatic.setVisibility(View.VISIBLE);
        notautomaticlayout.setVisibility(View.GONE);

        selectreasonlayout = (LinearLayout) CommonTaxView.findViewById(R.id.selectreasonlayout);

        autoactualduedatedata = (TextView) CommonTaxView.findViewById(R.id.autoactualduedatedata);
        autoextendedduedatedata = (TextView) CommonTaxView.findViewById(R.id.autoextendedduedatedata);

        NotAutoactualduedatedata = (TextView) CommonTaxView.findViewById(R.id.NotAutoactualduedatedata);
        NotAutoextendedduedatedata = (TextView) CommonTaxView.findViewById(R.id.NotAutoextendedduedatedata);

        //component 1

        groupReturnlayout = (LinearLayout) CommonTaxView.findViewById(R.id.groupReturnlayout);
        groupReturnlayout.setVisibility(View.VISIBLE);

        groupexapnd = (LinearLayout) CommonTaxView.findViewById(R.id.groupexapnd);
        groupexapnd.setVisibility(View.GONE);

        isforeigncheckbox = (CheckBox) CommonTaxView.findViewById(R.id.isforeigncheckbox);
        isgroupreturnCheckBox = (CheckBox) CommonTaxView.findViewById(R.id.isgroupreturnCheckBox);

        groupexNumEditText = (EditText) CommonTaxView.findViewById(R.id.groupexNumEditText);
        groupRadioGroup = (RadioGroup) CommonTaxView.findViewById(R.id.groupRadioGroup);
        wholegroupRadio = (RadioButton) CommonTaxView.findViewById(R.id.wholegroupRadio);
        partofgroupRadio = (RadioButton) CommonTaxView.findViewById(R.id.partofgroupRadio);
        addgroupButton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.addgroupButton);

        isPartofGroupLayout = (LinearLayout) CommonTaxView.findViewById(R.id.isPartofGroupLayout);
        isPartofGroupLayout.setVisibility(View.GONE);
        addPartofGroupLayout = (LinearLayout) CommonTaxView.findViewById(R.id.addPartofGroupLayout);
    }

    private void onClick() {

        ETCancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


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
        wholeLayout.setOnTouchListener(new View.OnTouchListener() {

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


        ETnext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Mode != null && Mode.equalsIgnoreCase("View")) {

                    TaxPageFragment();

                } else {

                    InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    if (dialogValidationCheck()) {


                        allowReturnType();

                    }
                }

            }
        });

        extensionGroupRadioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.automaticradio:

                        automatic.setVisibility(View.VISIBLE);
                        notautomaticlayout.setVisibility(View.GONE);

                        ExtensionType = "THREEMONTH";

                        datesModel.setExtensionType("0");
                        datesModel.setFORMCODEID(formCodeid);
                        datesModel.setTYED(TYED);
                        datesModel.setTY(TY);

                        break;

                    case R.id.notautomaticradio:

                        automatic.setVisibility(View.GONE);
                        notautomaticlayout.setVisibility(View.VISIBLE);
                        ExtensionType = "ADDITIONALTHREEMONTH";
                        datesModel.setExtensionType("1");
                        datesModel.setFORMCODEID(formCodeid);
                        datesModel.setTYED(TYED);
                        datesModel.setTY(TY);

                        break;

                    default:

                        automatic.setVisibility(View.VISIBLE);
                        notautomaticlayout.setVisibility(View.GONE);
                        break;


                }

                DueDateRequstMethod();

            }
        });

        groupexapnd.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                return false;
            }
        });

        groupRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.wholegroupRadio:
                        isPartofGroupLayout.setVisibility(View.GONE);
                        break;

                    case R.id.partofgroupRadio:
                        isPartofGroupLayout.setVisibility(View.VISIBLE);
                        break;

                    default:
                        isPartofGroupLayout.setVisibility(View.GONE);
                        break;
                }

            }
        });

        reasonOtherradio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    otherreasonEdittext.setVisibility(View.VISIBLE);

                    reasonnotsufficientTimeradio.setChecked(false);

                    reasonreconstuctradio.setChecked(false);

                    reasonunavoidradio.setChecked(false);

                } else {

                    otherreasonEdittext.setVisibility(View.GONE);
                }

            }
        });


        reasonunavoidradio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reasonnotsufficientTimeradio.setChecked(false);

                    reasonreconstuctradio.setChecked(false);

                    reasonOtherradio.setChecked(false);
                }

            }
        });

        reasonreconstuctradio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reasonunavoidradio.setChecked(false);

                    reasonnotsufficientTimeradio.setChecked(false);

                    reasonOtherradio.setChecked(false);
                }

            }
        });


        reasonnotsufficientTimeradio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reasonunavoidradio.setChecked(false);

                    reasonreconstuctradio.setChecked(false);

                    reasonOtherradio.setChecked(false);

                }

            }
        });


        isgroupreturnCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    groupexapnd.setVisibility(View.VISIBLE);
                } else if (!isChecked) {
                    groupexapnd.setVisibility(View.GONE);
                }

            }
        });

        partofgroupRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addgroupButton.setVisibility(View.VISIBLE);

                    wholegroupRadio.setChecked(false);
                } else if (!isChecked) {
                    addgroupButton.setVisibility(View.GONE);
                }

            }
        });

        wholegroupRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    partofgroupRadio.setChecked(false);
                }
            }
        });

        addgroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addPopup();


                if (deleteDialog != null)

                    deleteDialog.show();

            }
        });
    }

    public void GetReturnDetailsByID() {

        ReturnModel returnModel = new ReturnModel();

        returnModel.setAT(AppConfigManager.getAccessToken(mContext));

        returnModel.setUId(AppConfigManager.getLoggedUid(mContext));

        returnModel.setRID(AppConfigManager.getReturnRID(mContext));

        returnModel.setDId(AppConfigManager.getDID(mContext));

        returnModel.setBId(BId);

        GetReturnDetailsURL returnURL = new GetReturnDetailsURL(returnModel.GetReturnDetailsRequest(), mContext);

        GetReturnDetailsURL.GetReturnDetailsInterface returnDetailsInterface = new GetReturnDetailsURL.GetReturnDetailsInterface() {

            @Override
            public void onResultSuccess(Vector<ReturnModel> returnDetailsParseVector) {

                getreturnModel = returnDetailsParseVector.get(0);

                handler.post(getReturnValue);
            }

        };
        returnURL.setOnResultListener(returnDetailsInterface);

        returnURL.execute();
    }

    Handler handler = new Handler();

    Runnable getReturnValue = new Runnable() {
        @Override
        public void run() {

            if (databasehandler == null)

                databasehandler = new DatabaseHandler(mContext);

            datesModel = new DatesModel();


            if (getreturnModel.getOS() != null && getreturnModel.getOS().equalsIgnoreCase("Success")) {

                if (getreturnModel.getTY() != null && !getreturnModel.getTY().equalsIgnoreCase("null")) {

                    TY = getreturnModel.getTY();

                }

                if (getreturnModel.getFC() != null && !getreturnModel.getFC().equalsIgnoreCase("null")) {

                    formCodeid = getreturnModel.getFC();

                    if (getreturnModel.getFC().equalsIgnoreCase("01")) {

                        isgroupreturnCheckBox.setVisibility(View.VISIBLE);

                        if (getreturnModel.getIsGroupReturn() != null && getreturnModel.getIsGroupReturn().equalsIgnoreCase("true")) {

                            isgroupreturnCheckBox.setChecked(true);

                            groupReturnlayout.setVisibility(View.VISIBLE);

                            if (getreturnModel.getGEN() != null && !getreturnModel.getGEN().equalsIgnoreCase("null"))

                                groupexNumEditText.setText(getreturnModel.getGEN());


                            if (getreturnModel.getGroupFilingType() != null && !getreturnModel.getGroupFilingType().equalsIgnoreCase("null")) {
                                try {

                                    int type = Integer.parseInt(getreturnModel.getGroupFilingType());


                                    switch (type) {

                                        case 0:
                                            wholegroupRadio.setChecked(true);
                                            partofgroupRadio.setChecked(false);
                                            isPartofGroupLayout.setVisibility(View.GONE);
                                            break;

                                        case 1:
                                            wholegroupRadio.setChecked(false);
                                            partofgroupRadio.setChecked(true);
                                            isPartofGroupLayout.setVisibility(View.VISIBLE);
                                            addPopup();
                                            break;

                                        default:
                                            wholegroupRadio.setChecked(false);
                                            partofgroupRadio.setChecked(false);
                                            isPartofGroupLayout.setVisibility(View.GONE);
                                            break;
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    new SendException(mContext, e);
                                }

                            }


                        } else

                            isgroupreturnCheckBox.setChecked(false);


                    } else {

                        isgroupreturnCheckBox.setVisibility(View.GONE);

                    }

                    if (getreturnModel.getFC().equalsIgnoreCase("07") || getreturnModel.getFC().equalsIgnoreCase("7")) {

                        notautomaticradio.setVisibility(View.GONE);

                        automaticradio.setText("Automatic 6 month Extension");

                    } else {

                        notautomaticradio.setVisibility(View.VISIBLE);

                        automaticradio.setText("Automatic 3 month Extension");

                    }
                    updatehint(formCodeid);

                }

                if (getreturnModel.getTYED() != null && !getreturnModel.getTYED().equalsIgnoreCase("null")) {
                    TYED = getreturnModel.getTYED();
                }

                if (getreturnModel.getTYBD() != null && !getreturnModel.getTYBD().equalsIgnoreCase("null")) {
                    TYBD = getreturnModel.getTYBD();
                }

                //	Log.i("returnModel.getIsAddThreeMonth()", getreturnModel.getIsAddThreeMonth());

                ExtensionTypeEnum type = valueOf(getreturnModel.getExtensionType());

                switch (type) {

                    case THREEMONTH:

                        automaticradio.setChecked(true);

                        ExtensionType = "THREEMONTH";
                        datesModel.setExtensionType("0");
                        datesModel.setFORMCODEID(formCodeid);
                        datesModel.setTYED(TYED);
                        datesModel.setTY(TY);
                        DueDateRequstMethod();
                        break;

                    case ADDITIONALTHREEMONTH:


                        notautomaticradio.setChecked(true);

                        ExtensionType = "ADDITIONALTHREEMONTH";
                        datesModel.setExtensionType("1");
                        datesModel.setFORMCODEID(formCodeid);
                        datesModel.setTYED(TYED);
                        datesModel.setTY(TY);
                        DueDateRequstMethod();
                        break;

                    default:

                        automaticradio.setChecked(true);

                        ExtensionType = "THREEMONTH";
                        datesModel.setExtensionType("0");
                        datesModel.setFORMCODEID(formCodeid);
                        datesModel.setTYED(TYED);
                        datesModel.setTY(TY);
                        DueDateRequstMethod();
                        break;


                }

                if (getreturnModel.getISFC() != null && getreturnModel.getISFC().equalsIgnoreCase("true")) {

                    isforeigncheckbox.setChecked(true);

                } else {

                    isforeigncheckbox.setChecked(false);

                }


            } else if (getreturnModel.getOS() != null && getreturnModel.getOS().equalsIgnoreCase("Failure")) {

                if (getreturnModel.getEM() != null && !getreturnModel.getEM().equalsIgnoreCase("null")) {

                    if (getreturnModel.getEM().equalsIgnoreCase("Access Token is invalid")) {

                        utils.errorMessage(mContext, "Your Session is Expired");
                        Logout.logout(mContext);

                    } else {

                        utils.errorMessage(mContext, getreturnModel.getEM());

                    }
                }
            }

            if (Mode != null && Mode.equalsIgnoreCase("View"))
                disableLayout();

        }
    };

    public void DueDateRequstMethod() {

        try {

            if (getreturnModel != null && getreturnModel.getReasonCode() != null && !getreturnModel.getReasonCode().equalsIgnoreCase("null")) {
                int type = Integer.parseInt(getreturnModel.getReasonCode());

                switch (type) {

                    case 0:
                        reasonunavoidradio.setChecked(false);
                        reasonreconstuctradio.setChecked(false);
                        reasonnotsufficientTimeradio.setChecked(false);
                        reasonOtherradio.setChecked(false);
                        break;

                    case 1:
                        reasonunavoidradio.setChecked(true);
                        break;

                    case 2:
                        reasonreconstuctradio.setChecked(true);
                        break;

                    case 3:
                        reasonnotsufficientTimeradio.setChecked(true);
                        break;

                    case 4:
                        reasonOtherradio.setChecked(true);
                        otherreasonEdittext.setVisibility(View.VISIBLE);
                        if (getreturnModel.getReason() != null && !getreturnModel.getReason().equalsIgnoreCase("null"))
                            otherreasonEdittext.setText(getreturnModel.getReason());
                        break;

                    default:
                        reasonunavoidradio.setChecked(false);
                        reasonreconstuctradio.setChecked(false);
                        reasonnotsufficientTimeradio.setChecked(false);
                        reasonOtherradio.setChecked(false);
                        break;


                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }

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

            if (getdueDatesModel.getExtensionType().equalsIgnoreCase("0")) {

                if (getdueDatesModel.getActualDueDate() != null && !getdueDatesModel.getActualDueDate().equalsIgnoreCase("null")) {
                    autoactualduedatedata.setText(getdueDatesModel.getActualDueDate());
                }

                if (getdueDatesModel.getExtendedDueDate() != null && !getdueDatesModel.getExtendedDueDate().equalsIgnoreCase("null")) {
                    autoextendedduedatedata.setText(getdueDatesModel.getExtendedDueDate());
                }

            } else if (getdueDatesModel.getExtensionType().equalsIgnoreCase("1")) {

                if (getdueDatesModel.getActualDueDate() != null && !getdueDatesModel.getActualDueDate().equalsIgnoreCase("null")) {
                    NotAutoactualduedatedata.setText(getdueDatesModel.getActualDueDate());
                }

                if (getdueDatesModel.getExtendedDueDate() != null && !getdueDatesModel.getExtendedDueDate().equalsIgnoreCase("null")) {
                    NotAutoextendedduedatedata.setText(getdueDatesModel.getExtendedDueDate());
                }

            }

        }
    };

    protected boolean dialogValidationCheck() {

        boolean validationFlag = true;

        returnmodel = new ReturnModel();

        returnmodel.setAT(AppConfigManager.getAccessToken(mContext));

        returnmodel.setDId(AppConfigManager.getDID(mContext));

        returnmodel.setUId(AppConfigManager.getLoggedUid(mContext));

        returnmodel.setRID(AppConfigManager.getReturnRID(mContext));

        returnmodel.setEODId(AppConfigManager.getBID(mContext));

        returnmodel.setBId(AppConfigManager.getBID(mContext));

        returnmodel.setFC(getreturnModel.getFC());

        returnmodel.setTY(getreturnModel.getTY());

        returnmodel.setTYBD(TYBD);

        returnmodel.setTYED(TYED);

//        if (getreturnModel.getFC() != null && !getreturnModel.getFC().equalsIgnoreCase("null")) {
//            returnmodel.setFC(getreturnModel.getFC());
//        }
//
//        if (getreturnModel.getTYBD() != null && !getreturnModel.getTYBD().equalsIgnoreCase("null")) {
//            returnmodel.setTYBD(getreturnModel.getTYBD());
//        }
//
//        if (getreturnModel.getTYED() != null && !getreturnModel.getTYED().equalsIgnoreCase("null")) {
//            returnmodel.setTYED(getreturnModel.getTYED());
//        }
//
//        if (getreturnModel.getTY() != null && !getreturnModel.getTY().equalsIgnoreCase("null")) {
//            returnmodel.setTY(getreturnModel.getTY());
//        }

        if (automaticradio.isChecked()) {

            returnmodel.setIsAddThreeMonth("false");

            ExtensionType = "THREEMONTH";

        } else if (notautomaticradio.isChecked()) {

            returnmodel.setIsAddThreeMonth("true");

            ExtensionType = "ADDITIONALTHREEMONTH";

        }

        returnmodel.setExtensionType(ExtensionType);

        if (getdueDatesModel.getExtendedDueDate() != null && !getdueDatesModel.getExtendedDueDate().equalsIgnoreCase("null")) {

            returnmodel.setExtendedDueDate(getdueDatesModel.getExtendedDueDate());

        }


        if (!automaticradio.isChecked() && !notautomaticradio.isChecked()) {
            validationFlag = false;

            utils.errorMessage(mContext, "Please Choose any one of the Extension Option");
        }

        if (isforeigncheckbox.isChecked()) {
            returnmodel.setISFC("true");
        } else {
            returnmodel.setISFC("false");
        }

        if (isgroupreturnCheckBox.isChecked()) {
            returnmodel.setIsGroupReturn("true");
        } else {
            returnmodel.setIsGroupReturn("false");
        }

        if (selectreasonlayout.isShown()) {
            if (!reasonunavoidradio.isChecked() && !reasonreconstuctradio.isChecked() && !reasonnotsufficientTimeradio.isChecked() && !reasonOtherradio.isChecked()) {

                validationFlag = false;

                utils.errorMessage(mContext, "Please Choose any one of the Reason");

            }

            if (reasonunavoidradio.isChecked()) {
                returnmodel.setReasonCode("1");
            }
            if (reasonreconstuctradio.isChecked()) {
                returnmodel.setReasonCode("2");
            }
            if (reasonnotsufficientTimeradio.isChecked()) {
                returnmodel.setReasonCode("3");
            }

            if (reasonOtherradio.isChecked() && otherreasonEdittext.getText().toString().length() <= 0) {

                validationFlag = false;

                otherreasonEdittext.setError("Please Enter Other Reason");

            } else if (reasonOtherradio.isChecked() && otherreasonEdittext.getText().toString().length() > 0) {
                returnmodel.setReasonCode("4");

                returnmodel.setReason(otherreasonEdittext.getText().toString());
            }

        }

        if (groupexapnd.isShown()) {

            if (groupexNumEditText.getText().toString().equalsIgnoreCase("")) {
                validationFlag = false;

                groupexNumEditText.setError("Please Enter Group Exemption Number ");
            } else if (groupexNumEditText.getText().toString().length() < 4) {
                validationFlag = false;

                groupexNumEditText.setError("Enter valid Group Exemption Number ");
            } else if (groupexNumEditText.getText().toString().equalsIgnoreCase("0000")) {

                validationFlag = false;

                groupexNumEditText.setError("Enter valid Group Exemption Number ");

            } else {

                groupexNumEditText.setError(null);

                returnmodel.setGEN(groupexNumEditText.getText().toString());
            }

            if (wholegroupRadio.isChecked() || partofgroupRadio.isChecked()) {

                if (wholegroupRadio.isChecked()) {
                    returnmodel.setGroupFilingType("0");
                }

                if (partofgroupRadio.isChecked()) {
                    if (HoldingCount > 0) {
                        returnmodel.setGroupFilingType("1");
                    } else {
                        validationFlag = false;

                        utils.errorMessage(mContext, "Please Add Part of Group");

                    }
                }
            } else {
                validationFlag = false;

                utils.errorMessage(mContext, "Please select the Type of Group");
            }


        }

        return validationFlag;


    }


    protected void allowReturnType() {
        AllowReturnByIdURL allowReturnByIdURL = new AllowReturnByIdURL(returnmodel.allowReturnTypeRequest(), mContext);


        AllowReturnByIdURL.OnAsyncResultAllowReturnDetail saveReturnDetailsInterface = new AllowReturnByIdURL.OnAsyncResultAllowReturnDetail() {

            @Override
            public void onResultSuccess(ReturnModel message) {

                getAllowResponse = message;
                handler.post(allowRunnable);

            }

            @Override
            public void onResultFail(int resultCode, String errorMessage) {

            }


        };

        allowReturnByIdURL.setOnResultListener(saveReturnDetailsInterface);

        allowReturnByIdURL.execute();

    }

    Runnable allowRunnable = new Runnable() {
        @Override
        public void run() {

            // if (getAllowResponse != null && getAllowResponse.getOS() != null && getAllowResponse.getOS().equalsIgnoreCase("Success")) {

            if (getAllowResponse != null && getAllowResponse.ISAF()) {

                SaveExtensionType();

            } else {

                utils.errorMessage(mContext, "You can not file more than one extension per EIN in the same tax year");
            }


//            } else if (getAllowResponse != null && getAllowResponse.getOS() != null && getAllowResponse.getOS().equalsIgnoreCase("Failure")) {
//
//                if (getAllowResponse.getEM() != null && !getAllowResponse.getEM().equalsIgnoreCase("null"))
//
//                    utils.errorMessage(mContext, getAllowResponse.getEM());
//
//            } else {
//
//                utils.errorMessage(mContext, "Please try again later!");
//
//            }

        }
    };

    protected void SaveExtensionType() {

        Save8868ExtensionType saveReturnURL = new Save8868ExtensionType(returnmodel.GetSaveExtensionTypeRequest(), mContext);

        Save8868ExtensionType.SaveExtensionTypeDetailsInterface saveReturnDetailsInterface = new Save8868ExtensionType.SaveExtensionTypeDetailsInterface() {

            @Override
            public void onResultSuccess(final Vector<ReturnModel> returnDetailsParseVector1) {

                returnDetailsParseVector = returnDetailsParseVector1;
                handler.post(saveRunnable);

            }

        };

        saveReturnURL.setOnResultListener(saveReturnDetailsInterface);

        saveReturnURL.execute();

    }

    Runnable saveRunnable = new Runnable() {
        @Override
        public void run() {

            if (returnDetailsParseVector != null && returnDetailsParseVector.get(0).getOS().equalsIgnoreCase("Success")) {

                utils.successMessage(mContext, "Extension Type Saved Successfully");

                TaxPageFragment();
            } else if (returnDetailsParseVector.get(0).getOS() != null && returnDetailsParseVector.get(0).getOS().equalsIgnoreCase("Failure")) {

                if (returnDetailsParseVector.get(0).getEM() != null && !returnDetailsParseVector.get(0).getEM().equalsIgnoreCase("null")) {

                    if (returnDetailsParseVector.get(0).getEM().equalsIgnoreCase("Access Token is invalid")) {

                        utils.errorMessage(mContext, "Your Session is Expired");
                        Logout.logout(mContext);

                    } else {

                        utils.errorMessage(mContext, returnDetailsParseVector.get(0).getEM());

                    }
                }
            }

        }
    };


    private void TaxPageFragment() {


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

    private void addPopup() {

        LayoutInflater factory = LayoutInflater.from(mContext);
        final View deleteDialogView = factory.inflate(
                R.layout.groupreturnpopup, null);

        dialogInitial(deleteDialogView);
        SetSpinnerInputsFromDB();
        dialogOnclick();
        GetHoldingCompanyListInView();

        deleteDialog = new AlertDialog.Builder(mContext).create();
        deleteDialog.setView(deleteDialogView);

    }

    private void dialogInitial(View deleteDialogView) {


        //Textview


        // EditText's

        BusinessNameeditText = (com.rengwuxian.materialedittext.MaterialEditText) deleteDialogView.findViewById(R.id.BusinessNameeditText);


        EineditText = (com.rengwuxian.materialedittext.MaterialEditText) deleteDialogView.findViewById(R.id.EineditText);


        addressEdittext1 = (com.rengwuxian.materialedittext.MaterialEditText) deleteDialogView.findViewById(R.id.addressEdittext1);


        addresseditText2 = (com.rengwuxian.materialedittext.MaterialEditText) deleteDialogView.findViewById(R.id.addresseditText2);

        cityeditText = (com.rengwuxian.materialedittext.MaterialEditText) deleteDialogView.findViewById(R.id.cityeditText);


        stateorprovince = (com.rengwuxian.materialedittext.MaterialEditText) deleteDialogView.findViewById(R.id.stateorprovince);


        zipeditText = (com.rengwuxian.materialedittext.MaterialEditText) deleteDialogView.findViewById(R.id.zipeditText);


        // Spinner's

        stateSpinner = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) deleteDialogView.findViewById(R.id.stateSpinner);

        countrySpinnerLayout = (LinearLayout) deleteDialogView.findViewById(R.id.countrySpinnerLayout);

        countrySpinnerLayout.setVisibility(View.GONE);

        countrySpinner = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) deleteDialogView.findViewById(R.id.countrySpinner);

        //CheckBox's

        isaddoutsidecheckbox = (CheckBox) deleteDialogView.findViewById(R.id.isaddoutsidecheckbox);

        // Button

        addButton = (android.support.v7.widget.CardView) deleteDialogView.findViewById(R.id.addButton);

        addButtonText = (TextView) deleteDialogView.findViewById(R.id.addButtonText);

        addButtonText.setText("Add");

        //ListView

        holdinglistView = (ListView) deleteDialogView.findViewById(R.id.holdinglistView);

        //Linear Layout's

        rootlayout = (LinearLayout) deleteDialogView.findViewById(R.id.rootlayout);

        norecordsfound = (LinearLayout) deleteDialogView.findViewById(R.id.norecordsfound);

        // ImageView's

        closeicon = (ImageView) deleteDialogView.findViewById(R.id.closeicon);


        TextView popuptext1 = (TextView) deleteDialogView.findViewById(R.id.popuptext1);
        TextView businessName = (TextView) deleteDialogView.findViewById(R.id.businessName);
        TextView AddressHeader = (TextView) deleteDialogView.findViewById(R.id.AddressHeader);
        TextView ActionViewListheader = (TextView) deleteDialogView.findViewById(R.id.ActionViewListheader);

        Overridefonts.overrideFonts(mContext, rootlayout);
        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        popuptext1.setTypeface(typeFaceClass.NotoSans_Bold());
        businessName.setTypeface(typeFaceClass.NotoSans_Bold());
        AddressHeader.setTypeface(typeFaceClass.NotoSans_Bold());
        ActionViewListheader.setTypeface(typeFaceClass.NotoSans_Bold());

    }

    private void dialogOnclick() {

        rootlayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                return false;
            }
        });

        closeicon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (holdinglistView != null && holdinglistView.getAdapter() != null)

                    HoldingCount = holdinglistView.getChildCount();

                else

                    HoldingCount = 0;

                if (deleteDialog != null)
                    deleteDialog.dismiss();
            }
        });


        EineditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                utils.MaskingEin(mContext, EineditText);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ValidationCheck() == 1 && EditTag == 0) {
                    SaveHoldingCompany("ADD");
                } else if (ValidationCheck() == 1 && EditTag == 1) {
                    SaveHoldingCompany("UPDATE");
                }

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });


        isaddoutsidecheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    stateSpinner.setVisibility(View.GONE);

                    stateorprovince.setVisibility(View.VISIBLE);

                    countrySpinnerLayout.setVisibility(View.VISIBLE);

                    InputFilter[] maxlengthzip = new InputFilter[1];

                    maxlengthzip[0] = new InputFilter.LengthFilter(16);

                    zipeditText.setHint("ZIP or Postal Code");

                    zipeditText.setFloatingLabelText("ZIP or Postal Code");

                    zipeditText.setInputType(InputType.TYPE_CLASS_TEXT);

                    zipeditText.setFilters(maxlengthzip);

                    zipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);


                } else {
                    stateSpinner.setVisibility(View.VISIBLE);

                    stateorprovince.setVisibility(View.GONE);

                    countrySpinnerLayout.setVisibility(View.GONE);


                    InputFilter[] maxlengthzip = new InputFilter[1];

                    maxlengthzip[0] = new InputFilter.LengthFilter(10);

                    zipeditText.setHint("ZIP Code");

                    zipeditText.setFloatingLabelText("ZIP Code");

                    zipeditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                    zipeditText.setFilters(maxlengthzip);

                    zipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

                }


            }
        });

        countrySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                countryid = databasehandler.getCountryIDList().get(position);

                if (position > 0)

                    countrySpinner.setError(null);

                else
                    countrySpinner.setError("Country is required");

            }
        });


        stateSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                stateid = databasehandler.getStateIDList().get(position);

                if (position > 0)
                    stateSpinner.setError(null);

                else
                    stateSpinner.setError("State is required");

            }
        });


        zipeditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isaddoutsidecheckbox.isChecked()) {
                    utils.maskingzipcode(mContext, zipeditText);
                }

            }
        });

    }

    private void SetSpinnerInputsFromDB() {

        databasehandler = new DatabaseHandler(mContext);

        setSpinnerAdapter(databasehandler.getCountrynameList(), countrySpinner);

        setSpinnerAdapter(databasehandler.getStatenameList(), stateSpinner);

    }

    public void setSpinnerAdapter(final ArrayList<String> list, com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner spinner) {

        try {
            ArrayAdapter<String> Spinneradapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, list) {

                public View getView(int position, View convertView, ViewGroup parent) {

                    try {


                        View v = super.getView(position, convertView, parent);

                        TextView tv = ((TextView) v.findViewById(R.id.spinnertext));

                        View divider = (View) v.findViewById(R.id.divider);

                        if (position == list.size() - 1)
                            divider.setVisibility(View.GONE);
                        else
                            divider.setVisibility(View.VISIBLE);

                        tv.setSingleLine();
                        tv.setEllipsize(TextUtils.TruncateAt.END);
                        tv.setTextSize(15);
                        tv.setTypeface(new TypeFaceClass(mContext).NotoSans_Regular());

                        return v;

                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);
                    }

                    return null;
                }


            };

            spinner.setAdapter(Spinneradapter);
        } catch (Exception e) {
            new SendException(mContext, e);

            e.printStackTrace();
        }

    }

    public void GetHoldingCompanyListInView() {

        Log.i("In Holding List", "In holding List");

        HoldingModel getholdingmodel = new HoldingModel();

        getholdingmodel.setAT(AppConfigManager.getAccessToken(mContext));

        getholdingmodel.setUID(AppConfigManager.getLoggedUid(mContext));

        getholdingmodel.setDID(AppConfigManager.getDID(mContext));

        getholdingmodel.setEODId(AppConfigManager.getEODIDD(mContext));

        getholdingmodel.setRID(AppConfigManager.getReturnRID(mContext));

        GetHoldingCompanyURL getHoldingcompanyURL = new GetHoldingCompanyURL(getholdingmodel.getHoldingListRequestByRID(), mContext);

        GetHoldingCompanyURL.GetHoldingCompanyInterface getHoldingCompanyInterface = new GetHoldingCompanyURL.GetHoldingCompanyInterface() {

            @Override
            public void onResultSuccess(final HoldingModel getHoldingcompanyModel1) {

                getHoldingcompanyModel = getHoldingcompanyModel1;

                handler1.post(holdingCmpList);

            }

        };

        getHoldingcompanyURL.setOnResultListener(getHoldingCompanyInterface);

        getHoldingcompanyURL.execute();

    }

    Runnable holdingCmpList = new Runnable() {
        @Override
        public void run() {

            if (getHoldingcompanyModel != null) {

                if ((getHoldingcompanyModel.getOS() != null && getHoldingcompanyModel.getOS().equalsIgnoreCase("Success")) || getHoldingcompanyModel.getHoldingmodelVector().size() > 0) {

                    if (getHoldingcompanyModel.getHoldingmodelVector() != null && getHoldingcompanyModel.getHoldingmodelVector().size() > 0) {

                        HoldingCount = getHoldingcompanyModel.getHoldingmodelVector().size();

                        Log.i("In list", "list");

                        holdinglistView.setVisibility(View.VISIBLE);

                        norecordsfound.setVisibility(View.GONE);

                        CustomAdapterHoldingList customAdapterHoldingList = new CustomAdapterHoldingList(mContext, getHoldingcompanyModel);

                        customAdapterHoldingList.notifyDataSetChanged();

                        holdinglistView.setAdapter(customAdapterHoldingList);

                    } else {
                        Log.i("In no list", "no list");

                        HoldingCount = 0;


                        holdinglistView.setAdapter(null);

                        holdinglistView.setVisibility(View.GONE);

                        norecordsfound.setVisibility(View.VISIBLE);
                    }

                    Log.i("outside", "outside");
                } else if (getHoldingcompanyModel.getOS().equalsIgnoreCase("Failure") && getHoldingcompanyModel.getEM().equalsIgnoreCase("Access Token is invalid")) {

                    utils.errorMessage(mContext, "Your Session is Expired");

                    Logout.logout(mContext);
                }

            } else

                holdinglistView.setAdapter(null);
        }
    };

    public class CustomAdapterHoldingList extends BaseAdapter {
        private final Context mContext;

        HoldingModel holdingmodel = new HoldingModel();

        private int selectedIndex;

        private final int[] colors = new int[]
                {0xFFc8e3f4, 0xFFD9EBF6};

        private final int selectedColor = Color.parseColor("#FFFFFF");

        public CustomAdapterHoldingList(Context activity, HoldingModel holdingModel) {
            this.holdingmodel = holdingModel;

            this.mContext = activity;

            selectedIndex = 0;
        }

        public void setSelectedIndex(int ind) {
            selectedIndex = ind;

            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (holdingmodel.getHoldingmodelVector().size() <= 0) {
                return 0;
            }
            return holdingmodel.getHoldingmodelVector().size();
        }

        @Override
        public Object getItem(int position) {
            return holdingmodel.getHoldingmodelVector().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Regular.ttf");

            View rowView = convertView;

            ViewHolder view = null;

            Log.i("Value", "getview");
            try {
                if (rowView == null) {
                    Log.i("if", "getview");
                    // Get a new instance of the row layout view

                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    rowView = inflater.inflate(R.layout.holdinglistinflate, null);

                    // Hold the view objects in an object, that way the don't need
                    // to be
                    // "re-  finded"
                    view = new ViewHolder();

                    view.businessName = (TextView) rowView.findViewById(R.id.businessName);

                    view.businessName.setTypeface(type);

                    view.ein = (TextView) rowView.findViewById(R.id.ein);

                    view.ein.setTypeface(type);

                    view.addressline1 = (TextView) rowView.findViewById(R.id.addressline1);

                    view.addressline2 = (TextView) rowView.findViewById(R.id.addressline2);

                    view.editholding = (ImageView) rowView.findViewById(R.id.editholding);

                    view.deleteholding = (ImageView) rowView.findViewById(R.id.deleteholding);

                    // view.managebusinesslistlayout.setBackgroundColor(0x003300);

                    rowView.setTag(view);
                } else {
                    Log.i("else", "getview");

                    view = (ViewHolder) rowView.getTag();
                }

                if (AppConfigManager.getMode(mContext) != null && AppConfigManager.getMode(mContext).equalsIgnoreCase("VIEW")) {
                    view.editholding.setEnabled(false);

                    view.deleteholding.setEnabled(false);
                } else {
                    view.editholding.setEnabled(true);

                    view.deleteholding.setEnabled(true);
                }


                /** Set data to your Views. */

//                if (position == selectedIndex) {
//                    view.ein.setTextColor(Color.parseColor("#ED7C18"));
//                } else {
//                    view.ein.setTextColor(Color.parseColor("#125F76"));
//                }

                if (holdingmodel.getHoldingmodelVector().size() != 0) {
                    view.businessName.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyName());

                    if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN().equalsIgnoreCase("") && !holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN().equalsIgnoreCase("null")) {
                        view.ein.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN());
                    }

                    String isforeignAddress = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyIsForeignAddress();

                    String Address1 = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress1();

                    String Address2 = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2();

                    String city = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCity();

                    String state = databasehandler.getStatenameFromSID(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyStateId());

                    String stateorProvince = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyState();

                    final String country = databasehandler.getCountryNameFromCID(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCountryId());

                    String zipcode = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyZip();

                    if (holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2().equalsIgnoreCase("") || holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2().equalsIgnoreCase("null")) {
                        view.addressline1.setText(Address1);
                    } else {
                        view.addressline1.setText(Address1 + ", " + Address2);
                    }

                    if (isforeignAddress.equalsIgnoreCase("false")) {
                        view.addressline2.setText(city + ", " + state + " " + zipcode);
                    } else if (isforeignAddress.equalsIgnoreCase("true")) {
                        view.addressline2.setText(city + ", " + stateorProvince + ", " + country + " - " + zipcode);
                    }

                    view.editholding.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            handler1.post(editRunnable);


                        }

                        Runnable editRunnable = new Runnable() {
                            @Override
                            public void run() {
                                HoldingCompanyID = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyId();

                                EditTag = 1;

                                addButtonText.setText("Update");

                                if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyName().equalsIgnoreCase("null")) {
                                    BusinessNameeditText.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyName());
                                }

                                if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN().equalsIgnoreCase("null")) {
                                    EineditText.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN());
                                }

                                if (holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyIsForeignAddress().equalsIgnoreCase("true")) {
                                    isaddoutsidecheckbox.setChecked(true);


                                } else {
                                    isaddoutsidecheckbox.setChecked(false);

                                }

                                if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress1().equalsIgnoreCase("null")) {
                                    addressEdittext1.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress1());
                                }

                                if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2().equalsIgnoreCase("null")) {
                                    addresseditText2.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2());
                                }

                                if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCity().equalsIgnoreCase("null")) {
                                    cityeditText.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCity());
                                }

                                if (stateorprovince.isShown()) {
                                    stateorprovince.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyState());
                                }

                                if (stateSpinner.isShown()) {
                                    stateSpinner.setText(databasehandler.getStatenameFromSID(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyStateId()));

                                    stateid = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyStateId();
                                }

                                if (countrySpinner.isShown()) {

                                    countrySpinner.setText(databasehandler.getCountryNameFromCID(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCountryId()));

                                    countryid = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCountryId();
                                }

                                if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyZip().equalsIgnoreCase("null")) {
                                    zipeditText.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyZip());
                                }
                            }
                        };
                    });


                    view.deleteholding.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            new android.app.AlertDialog.Builder(mContext).setTitle("Delete Part of Group").setMessage("Are you sure you want to Delete this Part of Group?").setNegativeButton("No", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final HoldingModel holdingModelDelete = new HoldingModel();

                                    holdingModelDelete.setAT(AppConfigManager.getAccessToken(mContext));

                                    holdingModelDelete.setDID(AppConfigManager.getDID(mContext));

                                    holdingModelDelete.setUID(AppConfigManager.getLoggedUid(mContext));

                                    holdingModelDelete.setHoldingCompanyId(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyId());

                                    new AsyncTask<Void, Void, ArrayList<String>>() {
                                        ProgressDialog pd;

                                        @Override
                                        protected void onPreExecute() {
                                            pd = MyCustomProgressDialog.ctor(mContext);

                                            pd.show();
                                        }

                                        ;

                                        @Override
                                        protected void onPostExecute(final ArrayList<String> receivedValue1) {

                                            pd.dismiss();

                                            receivedValue = receivedValue1;

                                            handler1.post(deleteRunnable);

                                        }

                                        @Override
                                        protected ArrayList<String> doInBackground(Void... params) {
                                            ArrayList<String> receivedValue = new ArrayList<String>();

                                            DeleteHoldingCompanyService deleteHoldingCompanyService = new DeleteHoldingCompanyService();

                                            receivedValue = deleteHoldingCompanyService.getosfieldvalue(holdingModelDelete.getHoldingCompanyDeleteRequest(), ApplicationConfig.UPDATEFORM8868PARTOFGROUP, mContext);

                                            return receivedValue;
                                        }
                                    }.execute();


                                }

                                Runnable deleteRunnable = new Runnable() {
                                    @Override
                                    public void run() {

                                        if (receivedValue.get(0).equalsIgnoreCase("Success")) {
                                            clearFields();

                                            utils.successMessage(mContext, "Part of Group Deleted");


                                            GetHoldingCompanyListInView();

                                        } else if (receivedValue.get(0).equalsIgnoreCase("Failure") && receivedValue.get(1) != null && !receivedValue.get(1).equalsIgnoreCase("null")) {

                                            utils.errorMessage(mContext, receivedValue.get(1));

                                        }

                                    }
                                };
                            }).setIcon(R.drawable.delete).show();

                        }
                    });


                }


            } catch (Exception e) {
                e.printStackTrace();

                new SendException(mContext, e);
            }
            return rowView;
        }

        protected class ViewHolder {
            protected TextView businessName;

            protected TextView ein;

            protected TextView addressline1;

            protected TextView addressline2;

            protected ImageView editholding;

            protected ImageView deleteholding;

        }

    }

    Handler handler1 = new Handler();


    public void clearFields() {

        BusinessNameeditText.setText("");

        EineditText.setText("");

        isaddoutsidecheckbox.setChecked(false);

        addressEdittext1.setText("");

        addresseditText2.setText("");

        cityeditText.setText("");

        stateSpinner.setText("");

        stateorprovince.setText("");

        countrySpinner.setText("");

        zipeditText.setText("");

    }

    public int ValidationCheck() {
        int flag_for_validation = 1;

        if (BusinessNameeditText.getText().toString().equalsIgnoreCase("")) {
            flag_for_validation = 0;

            BusinessNameeditText.setError("Organization Name Required");
        } else {
            BusinessNameeditText.setError(null);

            holdingModelSave.setHoldingCompanyName(BusinessNameeditText.getText().toString());

        }

        if (EineditText.isShown()) {
            if (EineditText.getText().toString().equalsIgnoreCase("")) {
                flag_for_validation = 0;
                EineditText.setError("EIN Required");
            } else if (EineditText.getText().toString().equalsIgnoreCase("00-0000000")) {
                flag_for_validation = 0;
                EineditText.setError("Invalid Entry");
            } else if (EineditText.getText().toString().length() < 10) {
                flag_for_validation = 0;
                EineditText.setError("Ein Should be 9 digits");
            } else {
                EineditText.setError(null);

                holdingModelSave.setHoldingCompanyEIN(EineditText.getText().toString());
            }
        } else {
            holdingModelSave.setHoldingCompanyEIN("");
        }

        if (isaddoutsidecheckbox.isChecked()) {
            holdingModelSave.setHoldingCompanyIsForeignAddress("true");
        } else {
            holdingModelSave.setHoldingCompanyIsForeignAddress("false");
        }

        if (addressEdittext1.getText().toString().equalsIgnoreCase("")) {
            flag_for_validation = 0;
            addressEdittext1.setError("Address 1 Required");
        } else {
            addressEdittext1.setError(null);
            holdingModelSave.setHoldingCompanyAddress1(addressEdittext1.getText().toString());
        }

        holdingModelSave.setHoldingCompanyAddress2(addresseditText2.getText().toString());

        if (cityeditText.getText().toString().equalsIgnoreCase("")) {
            flag_for_validation = 0;
            cityeditText.setError("City Required");
        } else {
            cityeditText.setError(null);
            holdingModelSave.setHoldingCompanyCity(cityeditText.getText().toString());
        }

        if (zipeditText.getText().toString().equalsIgnoreCase("")) {
            flag_for_validation = 0;
            zipeditText.setError("ZIP Code Required");
        }
        if (!isaddoutsidecheckbox.isChecked() && !Utils.isValidZipCode(zipeditText.getText().toString())) {
            flag_for_validation = 0;
            zipeditText.setError("Invalid ZIP Code");
        } else {
            zipeditText.setError(null);

            holdingModelSave.setHoldingCompanyZip(zipeditText.getText().toString());
        }

        if (stateSpinner.isShown()) {
            if (stateid != null && stateid.equalsIgnoreCase("0")) {
                flag_for_validation = 0;
                stateSpinner.setError("State Required");
            } else {

                stateSpinner.setError(null);
                holdingModelSave.setHoldingCompanyStateId(stateid);
            }
        } else {
            holdingModelSave.setHoldingCompanyStateId("0");
        }

        if (countrySpinner.isShown()) {
            if (countryid != null && countryid.equalsIgnoreCase("0")) {
                flag_for_validation = 0;
                countrySpinner.setError("Country Required");
            } else {
                countrySpinner.setError(null);
                holdingModelSave.setHoldingCompanyCountryId(countryid);
            }
        } else {
            holdingModelSave.setHoldingCompanyCountryId("0");
        }

        if (stateorprovince.isShown()) {
            if (stateorprovince.getText().toString().equalsIgnoreCase("")) {
                flag_for_validation = 0;
                stateorprovince.setError("State or Province Required");
            } else {
                holdingModelSave.setHoldingCompanyState(stateorprovince.getText().toString());
            }
        } else {
            holdingModelSave.setHoldingCompanyState("");
        }

        return flag_for_validation;

    }


    protected void SaveHoldingCompany(final String LGT1) {

        LGT = LGT1;

        holdingModelSave.setAT(AppConfigManager.getAccessToken(mContext));

        holdingModelSave.setDID(AppConfigManager.getDID(mContext));

        holdingModelSave.setUID(AppConfigManager.getLoggedUid(mContext));

        holdingModelSave.setRID(AppConfigManager.getReturnRID(mContext));

        holdingModelSave.setEODId(AppConfigManager.getEODIDD(mContext));

        if (LGT.equalsIgnoreCase("ADD")) {
            holdingModelSave.setHoldingCompanyId("0");
        } else {
            holdingModelSave.setHoldingCompanyId(HoldingCompanyID);
        }

        holdingModelSave.setLGT(LGT);

        SaveHoldingCompanyURL saveHoldingCompanyURL = new SaveHoldingCompanyURL(holdingModelSave.getHoldingCompanySaveRequest(), mContext);

        SaveHoldingCompanyURL.SaveHoldingCompanyInterface saveHoldingCompanyInterface = new SaveHoldingCompanyURL.SaveHoldingCompanyInterface() {

            @Override
            public void onResultSuccess(final HoldingModel holdingModel) {


                saveHoldingcompanyModel = holdingModel;

                handler1.post(saveHoldingCmpRunnable);


            }
        };

        saveHoldingCompanyURL.setOnResultListener(saveHoldingCompanyInterface);

        saveHoldingCompanyURL.execute();

    }

    Runnable saveHoldingCmpRunnable = new Runnable() {
        @Override
        public void run() {
            if (saveHoldingcompanyModel != null && saveHoldingcompanyModel.getOS() != null && saveHoldingcompanyModel.getOS().equalsIgnoreCase("Success")) {
                if (LGT.equalsIgnoreCase("UPDATE")) {
                    EditTag = 0;

                    addButtonText.setText("Add");

                }

                if (LGT.equalsIgnoreCase("ADD")) {
                    // Toast goes here...
                }

                clearFields();

                HoldingModel getholdingmodel = new HoldingModel();

                getholdingmodel.setAT(AppConfigManager.getAccessToken(mContext));

                getholdingmodel.setUID(AppConfigManager.getLoggedUid(mContext));

                getholdingmodel.setDID(AppConfigManager.getDID(mContext));

                getholdingmodel.setRID(AppConfigManager.getReturnRID(mContext));

                GetHoldingCompanyURL getHoldingcompanyURL = new GetHoldingCompanyURL(getholdingmodel.getHoldingListRequestByRID(), mContext);

                GetHoldingCompanyURL.GetHoldingCompanyInterface getHoldingCompanyInterface = new GetHoldingCompanyURL.GetHoldingCompanyInterface() {

                    @Override
                    public void onResultSuccess(final HoldingModel getHoldingcompanyModel1) {

                        getHoldingcompanyModel = getHoldingcompanyModel1;

                        handler1.post(getHoldingCompanyRunnable);

                    }
                };

                getHoldingcompanyURL.setOnResultListener(getHoldingCompanyInterface);

                getHoldingcompanyURL.execute();

            } else if (saveHoldingcompanyModel != null && saveHoldingcompanyModel.getOS() != null && saveHoldingcompanyModel.getOS().equalsIgnoreCase("Failure") && !saveHoldingcompanyModel.getEM().equalsIgnoreCase("Access Token is Invalid")) {

                utils.errorMessage(mContext, saveHoldingcompanyModel.getEM());

            } else if (saveHoldingcompanyModel != null && saveHoldingcompanyModel.getOS() != null && saveHoldingcompanyModel.getOS().equalsIgnoreCase("Failure") && saveHoldingcompanyModel.getEM().equalsIgnoreCase("Access Token is Invalid")) {

                utils.errorMessage(mContext, "Your Session is Expired");
                Logout.logout(mContext);
            }

        }
    };

    Runnable getHoldingCompanyRunnable = new Runnable() {
        @Override
        public void run() {

            if (getHoldingcompanyModel.getHoldingmodelVector().size() > 0) {

                holdinglistView.setVisibility(View.VISIBLE);

                norecordsfound.setVisibility(View.GONE);

                CustomAdapterHoldingList customAdapterHoldingList = new CustomAdapterHoldingList(mContext, getHoldingcompanyModel);

                holdinglistView.setAdapter(customAdapterHoldingList);

            } else {
                holdinglistView.setVisibility(View.GONE);

                norecordsfound.setVisibility(View.VISIBLE);
            }

        }
    };

    private void setTypeFont() {

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        text2.setTypeface(typeFaceClass.NotoSans_Bold());
        textNote1.setTypeface(typeFaceClass.NotoSans_BoldItalic());
        textNote2.setTypeface(typeFaceClass.NotoSans_BoldItalic());
        selectreasontextview.setTypeface(typeFaceClass.NotoSans_Bold());
        autoHint.setTypeface(typeFaceClass.NotoSans_Italic());
        notAutoHint.setTypeface(typeFaceClass.NotoSans_Italic());

    }

    private void updatehint(String id) {

        if (id.equalsIgnoreCase("1") || id.equalsIgnoreCase("01")) {
            autoHint.setText("You have chosen to extend Form 990/990-EZ - Return of Organization Exempt From Income Tax for additional 3 months.");
            notAutoHint.setText("You have chosen to extend Form 990/990-EZ - Return of Organization Exempt From Income Tax for additional 3 months.");
        } else if (id.equalsIgnoreCase("2") || id.equalsIgnoreCase("02")) {
            autoHint.setText("You have chosen to extend Form 990-BL - Information and Initial Excise Tax Return for Black Lung Benefit Trusts and Certain Related Persons for additional 3 months");
            notAutoHint.setText("You have chosen to extend Form 990-BL - Information and Initial Excise Tax Return for Black Lung Benefit Trusts and Certain Related Persons for additional 3 months.");
        } else if (id.equalsIgnoreCase("4") || id.equalsIgnoreCase("04")) {
            autoHint.setText("You have chosen to extend Form 990-PF - Return of Private Foundation for additional 3 months.");
            notAutoHint.setText("You have chosen to extend Form 990-PF - Return of Private Foundation for additional 3 months.");
        } else if (id.equalsIgnoreCase("5") || id.equalsIgnoreCase("05")) {
            autoHint.setText("You have chosen to extend Form 990-T (sec. 401(a) or 408(a) trust) - Exempt Organization Business Income Tax Return for additional 3 months.");
            notAutoHint.setText("You have chosen to extend Form 990-T (sec. 401(a) or 408(a) trust) - Exempt Organization Business Income Tax Return for additional 3 months.");
        } else if (id.equalsIgnoreCase("6") || id.equalsIgnoreCase("06")) {
            autoHint.setText("You have chosen to extend Form 990-T (trust other than above) - Exempt Organization Business Income Tax Return for additional 3 months.");
            notAutoHint.setText("You have chosen to extend Form 990-T (trust other than above) - Exempt Organization Business Income Tax Return for additional 3 months.");
        } else if (id.equalsIgnoreCase("7") || id.equalsIgnoreCase("07")) {
            autoHint.setText("You have chosen to extend Form 990-T (corporation) - Exempt Organization Business Income Tax Return automatically for 6 months.");
            notAutoHint.setText("You have chosen to extend Form 990-T (corporation) - IRS does not support Additional (Non Automatic) Extension Only Automatic 6 month Extension will granted.");
        } else if (id.equalsIgnoreCase("8") || id.equalsIgnoreCase("08")) {
            autoHint.setText("You have chosen to extend Form 1041-A - U.S. Information Return Trust Accumulation of Charitable Amounts for additional 3 months.");
            notAutoHint.setText("You have chosen to extend Form 1041-A - U.S. Information Return Trust Accumulation of Charitable Amounts for additional 3 months.");
        } else if (id.equalsIgnoreCase("9") || id.equalsIgnoreCase("09")) {
            autoHint.setText("You have chosen to extend Form 4720 - Return of Certain Excise Taxes Under Chapters 41 and 42 of the Internal Revenue Code for additional 3 months.");
            notAutoHint.setText("You have chosen to extend Form 4720 - Return of Certain Excise Taxes Under Chapters 41 and 42 of the Internal Revenue Code for additional 3 months.");
        } else if (id.equalsIgnoreCase("10")) {
            autoHint.setText("You have chosen to extend Form 5227 - Split-Interest Trust Information Return for additional 3 months.");
            notAutoHint.setText("You have chosen to extend Form 5227 - Split-Interest Trust Information Return for additional 3 months.");
        } else if (id.equalsIgnoreCase("11") || id.equalsIgnoreCase("25") || id.equalsIgnoreCase("03")) {
            autoHint.setText("You have chosen to extend Form 6069 - Return of Excise Tax on Excess Contributions to Black Lung Benefit Trust Under Section 4953 & 192 Deduction for additional 3 months.");
            notAutoHint.setText("You have chosen to extend Form 6069 - Return of Excise Tax on Excess Contributions to Black Lung Benefit Trust Under Section 4953 & 192 Deduction for additional 3 months.");
        } else if (id.equalsIgnoreCase("12")) {
            autoHint.setText("You have chosen to extend Form 8870 - Information Return for Transfers Associated With Certain Personal Benefit Contracts . The IRS does not support e-filing for this form, but you can continue the process and print and mail the paper version.");
            notAutoHint.setText("You have chosen to extend Form 8870 - Information Return for Transfers Associated With Certain Personal Benefit Contracts . The IRS does not support e-filing for this form, but you can continue the process and print and mail the paper version.");
        } else {
            autoHint.setText("IRS provides a 3 months extension for this form");
            notAutoHint.setText("IRS provides a 3 months extension for this form");
        }


    }

    private void disableLayout() {

        DisableEdittext.disableAll(mContext, wholeLayout);

    }

    private void addLeftFragment(int sId) {

        Fragment newFragment = new TaxLeftFragment(mContext, sId);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.leftFragment, newFragment);


// Commit the transaction
        transaction.commit();

    }

}
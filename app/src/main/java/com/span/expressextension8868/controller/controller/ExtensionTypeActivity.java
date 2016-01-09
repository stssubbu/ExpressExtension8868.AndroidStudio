package com.span.expressextension8868.controller.controller;

import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.span.expressextension8868.model.core.DatesModel;
import com.span.expressextension8868.model.core.HoldingModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.DueDateURL;
import com.span.expressextension8868.webservice.webservices.DueDateURL.DueDateInterface;
import com.span.expressextension8868.webservice.webservices.GetHoldingCompanyCountURL;
import com.span.expressextension8868.webservice.webservices.GetHoldingCompanyCountURL.GetHoldingCompanyCountInterface;
import com.span.expressextension8868.webservice.webservices.GetReturnDetailsURL;
import com.span.expressextension8868.webservice.webservices.GetReturnDetailsURL.GetReturnDetailsInterface;
import com.span.expressextension8868.webservice.webservices.SaveReturnURL;
import com.span.expressextension8868.webservice.webservices.SaveReturnURL.SaveReturnDetailsInterface;
import com.span.expressextension8868.R;

public class ExtensionTypeActivity extends CommonHeaderClass {

    Context mContext = ExtensionTypeActivity.this;

    View HeaderView;

    DatabaseHandler databasehandler;

    //TextView's

    TextView next, cancelbutton, groupexNumTextview;

    TextView actualduedatedata, extendedduedatedata, NotAutoactualduedatedata, NotAutoextendedduedatedata, selectreasontextview;

    RadioButton automaticradio, notautomaticradio, reasonunavoidradio, reasonreconstuctradio, reasonnotsufficientTimeradio, reasonOtherradio;

    EditText otherreasonEdittext;

    LinearLayout selectreasonlayout, rootlayout, groupexapnd, rightsidecontent;

    TableLayout AutoTableLayout, NotAutoTableLayout;

    CheckBox isforeigncheckbox, isgroupreturnCheckBox;

    EditText groupexNumEditText;

    RadioButton wholegroupRadio, partofgroupRadio;

    Button addgroupButton;

    ReturnModel returnmodel = new ReturnModel();

    ReturnModel getreturnModel;

    DatesModel datesModel = new DatesModel();

    int grouplistcount = 0, HoldingCount = 0;

    String formCodeid = "0", TYBD, TYED, TY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View viewToLoad = LayoutInflater.from(this).inflate(R.layout.extensiontype, null);

        this.setContentView(viewToLoad);

        HeaderView = viewToLoad.findViewById(R.id.home_headerView);

        Initialization();

        databasehandler = new DatabaseHandler(mContext);

        mappingWidgets(HeaderView);

        Overridefonts.overrideFonts(mContext, rootlayout);

        setOnclickListener();

        GetReturnDetailsByID();

    }

    public void GetReturnDetailsByID() {

        ReturnModel returnModel = new ReturnModel();

        returnModel.setAT(AppConfigManager.getAccessToken(mContext));

        returnModel.setUId(AppConfigManager.getLoggedUid(mContext));

        returnModel.setRID(AppConfigManager.getReturnRID(mContext));

        returnModel.setDId(AppConfigManager.getDID(mContext));

        GetReturnDetailsURL returnURL = new GetReturnDetailsURL(returnModel.GetReturnDetailsRequest(), mContext);

        GetReturnDetailsInterface returnDetailsInterface = new GetReturnDetailsInterface() {

            @Override
            public void onResultSuccess(Vector<ReturnModel> returnDetailsParseVector) {
                getreturnModel = returnDetailsParseVector.get(0);

                runOnUiThread(new Runnable() {
                    public void run() {
                        if (getreturnModel.getOS() != null && getreturnModel.getOS().equalsIgnoreCase("Success")) {
                            if (getreturnModel.getTY() != null && !getreturnModel.getTY().equalsIgnoreCase("null")) {
                                TY = getreturnModel.getTY();
                            }

                            if (getreturnModel.getFC() != null && !getreturnModel.getFC().equalsIgnoreCase("null")) {
                                formCodeid = databasehandler.getFormCodeIDByFormCode(getreturnModel.getFC());

                                if (getreturnModel.getFC().equalsIgnoreCase("01")) {
                                    isgroupreturnCheckBox.setVisibility(View.VISIBLE);
                                } else {
                                    isgroupreturnCheckBox.setVisibility(View.GONE);
                                }

                                if (getreturnModel.getFC().equalsIgnoreCase("07")) {
                                    notautomaticradio.setVisibility(View.GONE);

                                    automaticradio.setText("Automatic 6 month Extension");
                                } else {
                                    notautomaticradio.setVisibility(View.VISIBLE);

                                    automaticradio.setText("Automatic 3 month Extension");
                                }
                            }

                            if (getreturnModel.getTYED() != null && !getreturnModel.getTYED().equalsIgnoreCase("null")) {
                                TYED = getreturnModel.getTYED();
                            }

                            //	Log.i("returnModel.getIsAddThreeMonth()", getreturnModel.getIsAddThreeMonth());

                            if (getreturnModel.getIsAddThreeMonth() != null && getreturnModel.getIsAddThreeMonth().equalsIgnoreCase("false")) {
                                automaticradio.setChecked(true);

                                datesModel.setExtensionType("1");

                                datesModel.setFORMCODEID(formCodeid);

                                datesModel.setTYED(TYED);

                                datesModel.setTY(TY);

                                DueDateRequstMethod();
                            } else if (getreturnModel.getIsAddThreeMonth() != null && getreturnModel.getIsAddThreeMonth().equalsIgnoreCase("true")) {
                                notautomaticradio.setChecked(true);

                                datesModel.setExtensionType("2");

                                datesModel.setFORMCODEID(formCodeid);

                                datesModel.setTYED(TYED);

                                datesModel.setTY(TY);

                                DueDateRequstMethod();
                            }

                        } else if (getreturnModel.getOS() != null && getreturnModel.getOS().equalsIgnoreCase("Failure") && getreturnModel.getEM().equalsIgnoreCase("Access Token Invalid")) {
                            Toast.makeText(getBaseContext(), "Your Session is Expired", Toast.LENGTH_LONG).show();

                            Logout.logout(mContext);
                        }

                    }
                });
            }

        };
        returnURL.setOnResultListener(returnDetailsInterface);

        returnURL.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("In on onResume", "onResume");

        addgroupButton.setClickable(true);

        HoldingModel holdingmodelforCount = new HoldingModel();

        holdingmodelforCount.setAT(AppConfigManager.getAccessToken(mContext));

        holdingmodelforCount.setDID(AppConfigManager.getDID(mContext));

        holdingmodelforCount.setUID(AppConfigManager.getLoggedUid(mContext));

        holdingmodelforCount.setRID(AppConfigManager.getReturnRID(mContext));

        GetHoldingCompanyCountURL getHoldingCompanyCountURL = new GetHoldingCompanyCountURL(holdingmodelforCount.getHoldingListRequestByRID(), mContext);

        GetHoldingCompanyCountInterface getHoldingCompanyCountInterface = new GetHoldingCompanyCountInterface() {

            @Override
            public void onResultSuccess(HoldingModel getHoldingcompanyModel) {

                HoldingCount = Integer.parseInt(getHoldingcompanyModel.getHCCount());
            }
        };
        getHoldingCompanyCountURL.setOnResultListener(getHoldingCompanyCountInterface);

        getHoldingCompanyCountURL.execute();

    }

    public void DueDateRequstMethod() {
        DueDateURL dueDateURL = new DueDateURL(datesModel.getDuedateRequest(), mContext);

        DueDateInterface dueDateInterface = new DueDateInterface() {

            @Override
            public void onResultSuccess(final DatesModel getdatesModel) {

                runOnUiThread(new Runnable() {
                    public void run() {

                        if (getdatesModel.getExtensionType().equalsIgnoreCase("1")) {
                            if (getdatesModel.getActualDueDate() != null && !getdatesModel.getActualDueDate().equalsIgnoreCase("null")) {
                                actualduedatedata.setText(getdatesModel.getActualDueDate());
                            }

                            if (getdatesModel.getExtendedDueDate() != null && !getdatesModel.getExtendedDueDate().equalsIgnoreCase("null")) {
                                extendedduedatedata.setText(getdatesModel.getExtendedDueDate());
                            }
                        } else if (getdatesModel.getExtensionType().equalsIgnoreCase("2")) {
                            if (getdatesModel.getActualDueDate() != null && !getdatesModel.getActualDueDate().equalsIgnoreCase("null")) {
                                NotAutoactualduedatedata.setText(getdatesModel.getActualDueDate());
                            }

                            if (getdatesModel.getExtendedDueDate() != null && !getdatesModel.getExtendedDueDate().equalsIgnoreCase("null")) {
                                NotAutoextendedduedatedata.setText(getdatesModel.getExtendedDueDate());
                            }
                        }

                    }
                });
            }

        };

        dueDateURL.setOnResultListener(dueDateInterface);

        dueDateURL.execute();
    }

    public void setOnclickListener() {

        groupexapnd.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                return false;
            }
        });

        rightsidecontent.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                return false;
            }
        });

        addgroupButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent group = new Intent(ExtensionTypeActivity.this, HoldingPopUpActivity.class);

                startActivity(group);
            }
        });

        next.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ValidationCheck()) {
                    SaveExtensionType();
                }

            }
        });

        cancelbutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent back = new Intent(ExtensionTypeActivity.this, TaxYearActivity.class);

                startActivity(back);

                finish();
            }
        });


        automaticradio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notautomaticradio.setChecked(false);

                    AutoTableLayout.setVisibility(View.VISIBLE);

                    NotAutoTableLayout.setVisibility(View.GONE);

                    selectreasonlayout.setVisibility(View.GONE);

                    notautomaticradio.setChecked(false);

                    Log.i("automaticradio", "automaticradio");

                    datesModel.setExtensionType("1");

                    datesModel.setFORMCODEID(formCodeid);

                    datesModel.setTYED(TYED);

                    datesModel.setTY(TY);

                    DueDateRequstMethod();
                }

            }
        });

        notautomaticradio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    automaticradio.setChecked(false);

                    AutoTableLayout.setVisibility(View.GONE);

                    NotAutoTableLayout.setVisibility(View.VISIBLE);

                    selectreasonlayout.setVisibility(View.VISIBLE);

                    automaticradio.setChecked(false);

                    Log.i("notautomaticradio", "notautomaticradio");

                    datesModel.setExtensionType("2");

                    datesModel.setFORMCODEID(formCodeid);

                    datesModel.setTYED(TYED);

                    datesModel.setTY(TY);

                    DueDateRequstMethod();
                }

            }
        });

        reasonOtherradio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    otherreasonEdittext.setVisibility(View.VISIBLE);
                } else if (!isChecked) {
                    otherreasonEdittext.setVisibility(View.GONE);
                }

            }
        });


        reasonunavoidradio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reasonnotsufficientTimeradio.setChecked(false);

                    reasonreconstuctradio.setChecked(false);

                    reasonOtherradio.setChecked(false);
                }

            }
        });

        reasonreconstuctradio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reasonunavoidradio.setChecked(false);

                    reasonnotsufficientTimeradio.setChecked(false);

                    reasonOtherradio.setChecked(false);
                }

            }
        });


        reasonnotsufficientTimeradio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reasonunavoidradio.setChecked(false);

                    reasonreconstuctradio.setChecked(false);

                    reasonOtherradio.setChecked(false);

                }

            }
        });


        isgroupreturnCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    groupexapnd.setVisibility(View.VISIBLE);
                } else if (!isChecked) {
                    groupexapnd.setVisibility(View.GONE);
                }

            }
        });

        partofgroupRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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

        wholegroupRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    partofgroupRadio.setChecked(false);
                }
            }
        });

    }

    protected void SaveExtensionType() {
        SaveReturnURL saveReturnURL = new SaveReturnURL(returnmodel.GetSaveExtensionTypeRequest(), mContext);

        SaveReturnDetailsInterface saveReturnDetailsInterface = new SaveReturnDetailsInterface() {

            @Override
            public void onResultSuccess(final Vector<ReturnModel> returnDetailsParseVector) {
                runOnUiThread(new Runnable() {
                    public void run() {

                        if (returnDetailsParseVector.get(0).getOS().equalsIgnoreCase("Success")) {
                            Intent i = new Intent(ExtensionTypeActivity.this, DashboardActivity.class);

                            startActivity(i);

                            finish();
                        }

                    }
                });

            }

        };

        saveReturnURL.setOnResultListener(saveReturnDetailsInterface);

        saveReturnURL.execute();

    }

    protected boolean ValidationCheck() {

        boolean validationFlag = true;

        returnmodel.setAT(AppConfigManager.getAccessToken(mContext));

        returnmodel.setDId(AppConfigManager.getDID(mContext));

        returnmodel.setUId(AppConfigManager.getLoggedUid(mContext));

        returnmodel.setRID(AppConfigManager.getReturnRID(mContext));

        if (getreturnModel.getFC() != null && !getreturnModel.getFC().equalsIgnoreCase("null")) {
            returnmodel.setFC(getreturnModel.getFC());
        }

        if (getreturnModel.getTYBD() != null && !getreturnModel.getTYBD().equalsIgnoreCase("null")) {
            returnmodel.setTYBD(getreturnModel.getTYBD());
        }

        if (getreturnModel.getTYED() != null && !getreturnModel.getTYED().equalsIgnoreCase("null")) {
            returnmodel.setTYED(getreturnModel.getTYED());
        }

        if (getreturnModel.getTY() != null && !getreturnModel.getTY().equalsIgnoreCase("null")) {
            returnmodel.setTY(getreturnModel.getTY());
        }

        if (automaticradio.isChecked()) {
            returnmodel.setIsAddThreeMonth("false");
        } else if (notautomaticradio.isChecked()) {
            returnmodel.setIsAddThreeMonth("true");
        }

        if (!automaticradio.isChecked() && !notautomaticradio.isChecked()) {
            validationFlag = false;

            Toast.makeText(getBaseContext(), "Please Choose any one of the Extension Option", Toast.LENGTH_LONG).show();
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

                Toast.makeText(getBaseContext(), "Please Choose any one of the Reason", Toast.LENGTH_LONG).show();
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

                reasonOtherradio.setError("Please Enter Other Reason");
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
            } else {
                groupexNumEditText.setError(null);

                returnmodel.setGEN(groupexNumEditText.getText().toString());
            }

            if (wholegroupRadio.isChecked()) {
                returnmodel.setGroupFilingType("1");
            }

            if (partofgroupRadio.isChecked()) {
                if (HoldingCount > 0) {
                    returnmodel.setGroupFilingType("2");
                } else {
                    validationFlag = false;

                    Toast.makeText(getBaseContext(), "Pleas Add Part of Group", Toast.LENGTH_LONG).show();
                }
            }

        }


        return validationFlag;
    }

    public void Initialization() {

        //Textview's

        HeaderView.findViewById(R.id.FormInterviewtextView).setBackgroundResource(R.drawable.topfirstselected);

        ((TextView) HeaderView.findViewById(R.id.FormInterviewtextView)).setTextColor(Color.WHITE);

        actualduedatedata = (TextView) findViewById(R.id.actualduedatedata);

        extendedduedatedata = (TextView) findViewById(R.id.extendedduedatedata);

        NotAutoactualduedatedata = (TextView) findViewById(R.id.NotAutoactualduedatedata);

        NotAutoextendedduedatedata = (TextView) findViewById(R.id.NotAutoextendedduedatedata);

        selectreasontextview = (TextView) findViewById(R.id.selectreasontextview);

        next = (TextView) findViewById(R.id.next);

        cancelbutton = (TextView) findViewById(R.id.cancelbutton);

        groupexNumTextview = (TextView) findViewById(R.id.groupexNumTextview);

        Utils.setfirstletterred(groupexNumTextview.getText().toString(), groupexNumTextview, mContext);

        //Radio Button's

        automaticradio = (RadioButton) findViewById(R.id.automaticradio);

        notautomaticradio = (RadioButton) findViewById(R.id.notautomaticradio);

        reasonunavoidradio = (RadioButton) findViewById(R.id.reasonunavoidradio);

        reasonreconstuctradio = (RadioButton) findViewById(R.id.reasonreconstuctradio);

        reasonnotsufficientTimeradio = (RadioButton) findViewById(R.id.reasonnotsufficientTimeradio);

        reasonOtherradio = (RadioButton) findViewById(R.id.reasonOtherradio);

        wholegroupRadio = (RadioButton) findViewById(R.id.wholegroupRadio);

        partofgroupRadio = (RadioButton) findViewById(R.id.partofgroupRadio);

        //EditText

        otherreasonEdittext = (EditText) findViewById(R.id.otherreasonEdittext);

        //Linear Layout

        rightsidecontent = (LinearLayout) findViewById(R.id.rightsidecontent);

        selectreasonlayout = (LinearLayout) findViewById(R.id.selectreasonlayout);

        rootlayout = (LinearLayout) findViewById(R.id.rootlayout);

        groupexapnd = (LinearLayout) findViewById(R.id.groupexapnd);

        //TableLayout

        AutoTableLayout = (TableLayout) findViewById(R.id.AutoTableLayout);

        NotAutoTableLayout = (TableLayout) findViewById(R.id.NotAutoTableLayout);

        //CheckBox's

        isforeigncheckbox = (CheckBox) findViewById(R.id.isforeigncheckbox);

        isgroupreturnCheckBox = (CheckBox) findViewById(R.id.isgroupreturnCheckBox);

        //Button

        addgroupButton = (Button) findViewById(R.id.addgroupButton);

        //EditText

        groupexNumEditText = (EditText) findViewById(R.id.groupexNumEditText);

    }


}

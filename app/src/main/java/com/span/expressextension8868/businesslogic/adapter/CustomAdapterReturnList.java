package com.span.expressextension8868.businesslogic.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.span.expressextension8868.R;
import com.span.expressextension8868.controller.controller.CommonTaxFragment;
import com.span.expressextension8868.controller.controller.DashboardFragment;
import com.span.expressextension8868.controller.controller.UnCaughtException;
import com.span.expressextension8868.controller.controller.WebActivity;
import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.AuditModel;
import com.span.expressextension8868.model.core.BookIncareOfModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.ConnectionDetector;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.serviceclass.CountryStateAndFormOrganization;
import com.span.expressextension8868.webservice.webservices.DeleteReturn;
import com.span.expressextension8868.webservice.webservices.GetBusinessReturnListDetailsURL;
import com.span.expressextension8868.webservice.webservices.PdfURL;
import com.span.expressextension8868.webservice.webservices.SendEmailURL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Vector;


public class CustomAdapterReturnList extends BaseExpandableListAdapter {
    private final Context mContext;

    ConnectionDetector cd;

    AddBussinessInputModel businessListModel;


    AlertDialog errorDialog;

    Vector<ReturnModel> businessListReturnDetailVector;

    CountryStateAndFormOrganization countrylist;

    ListView lv;

    int selectedposition, businessSelection;

    String jsonstring;

    String url;


    boolean flag_for_view_edit;

    Utils utils;

    FragmentActivity fA;

    ArrayList<String> deleteDetails;

    int replaceId, TY;

    DrawerLayout mDrawerLayout;

    ImageView businesslistview;

    String emailResponse;

    //add dialog

    android.support.v7.app.AlertDialog emailDialog;

    android.support.v7.widget.CardView sendEmail, emailCancel;

    EditText emailTo, emailSubject, emailContent;

    JSONObject obj = new JSONObject();

    JSONObject obj1 = new JSONObject();

    ListView businesslist;

    DashboardFragment.BusinessClikInterface businessClikInterface;

    public enum Error {
        Create, TaxYear, Summary
    }

    public CustomAdapterReturnList(Context mcontext, Vector<ReturnModel> businessListReturnDetailVector, AddBussinessInputModel businessListModel,
                                   ListView lv, FragmentActivity fA, int replaceId, ImageView businesslistview, DrawerLayout mDrawerLayout, ListView businesslist, int businessSelection, DashboardFragment.BusinessClikInterface businessClikInterface) {
        super();

        utils = new Utils();

        this.mContext = mcontext;

        this.businessListReturnDetailVector = businessListReturnDetailVector;

        this.businessListModel = businessListModel;

        this.lv = lv;

        this.fA = fA;

        this.replaceId = replaceId;

        this.businesslistview = businesslistview;

        this.mDrawerLayout = mDrawerLayout;

        this.businesslist = businesslist;

        this.businessSelection = businessSelection;

        this.businessClikInterface = businessClikInterface;

        cd = new ConnectionDetector(mContext);
    }

    public void setSelectedIndex(int pos) {
        selectedposition = pos;

        notifyDataSetChanged();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return businessListReturnDetailVector.get(groupPosition).getRN();
    }

    @Override
    public int getGroupCount() {

        return businessListReturnDetailVector.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            TypeFaceClass typeface = new TypeFaceClass(mContext);

            if (convertView == null) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = inflater.inflate(R.layout.groupview, null);
            }

            ImageView groupindicator = (ImageView) convertView.findViewById(R.id.groupindicator);

            LinearLayout changecolorback = (LinearLayout) convertView.findViewById(R.id.changecolorback);

            ImageView returnstatusindicatorcolor = (ImageView) convertView.findViewById(R.id.returnstatusindicatorcolor);

            if (isExpanded == true) {
                Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


                groupindicator.setImageResource(R.drawable.uparrow);

                changecolorback.setBackgroundResource(R.drawable.expandablechildrectangleshapeshade);

            }
            if (isExpanded == false) {

                Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


                groupindicator.setImageResource(R.drawable.downarrow);

                changecolorback.setBackgroundResource(R.drawable.expandablechildrectangleshape);
            }
            TextView returnno = (TextView) convertView.findViewById(R.id.returnno);
            TextView extensionTypetextview = (TextView) convertView.findViewById(R.id.extensionTypetextview);
            TextView lastupdate = (TextView) convertView.findViewById(R.id.lastupdate);

            returnno.setTypeface(typeface.NotoSans_Regular());
            extensionTypetextview.setTypeface(typeface.NotoSans_Regular());
            lastupdate.setTypeface(typeface.NotoSans_Regular());

            TextView RN = (TextView) convertView.findViewById(R.id.returnnumber);

            TextView UDT = (TextView) convertView.findViewById(R.id.udt);

            TextView extensionType = (TextView) convertView.findViewById(R.id.extensionType);

            TextView Returnstatus = (TextView) convertView.findViewById(R.id.returnstatus);

            TextView returnstatustextview = (TextView) convertView.findViewById(R.id.returnstatustextview);

            RN.setTypeface(typeface.NotoSans_Bold());

            returnstatustextview.setTypeface(typeface.NotoSans_Regular());

            UDT.setTypeface(typeface.NotoSans_Regular());

            extensionType.setTypeface(typeface.NotoSans_Regular());

            Returnstatus.setTypeface(typeface.NotoSans_Bold());

            if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("1")) {

                if (businessListReturnDetailVector.get(groupPosition).getTES() != null && (businessListReturnDetailVector.get(groupPosition).getTES().equalsIgnoreCase("Form 8870")) && businessListReturnDetailVector.get(groupPosition).getISPAID().equalsIgnoreCase("true")) {

                    Returnstatus.setText("Paid - Not Transmitted");
                    Returnstatus.setTextColor(mContext.getResources().getColor(R.color.Returnyellow));

                } else {

                    Returnstatus.setText("In Progress");
                    Returnstatus.setTextColor(mContext.getResources().getColor(R.color.Black));
                }

                returnstatusindicatorcolor.setVisibility(View.INVISIBLE);

            } else if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("0")) {

                Returnstatus.setText("In Progress");
                returnstatusindicatorcolor.setVisibility(View.INVISIBLE);
                Returnstatus.setTextColor(mContext.getResources().getColor(R.color.Black));

            } else if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("2")) {

                returnstatusindicatorcolor.setVisibility(View.VISIBLE);
                returnstatusindicatorcolor.setImageResource(R.drawable.bluedot);
                Returnstatus.setText("Transmitted to the IRS");
                Returnstatus.setTextColor(mContext.getResources().getColor(R.color.Common_Titile_Bg));

            } else if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("3")) {

                returnstatusindicatorcolor.setVisibility(View.VISIBLE);
                returnstatusindicatorcolor.setImageResource(R.drawable.greendot);
                Returnstatus.setText("Accepted By the IRS");
                Returnstatus.setTextColor(mContext.getResources().getColor(R.color.Returngreen));

            } else if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("4")) {

                returnstatusindicatorcolor.setVisibility(View.VISIBLE);
                returnstatusindicatorcolor.setImageResource(R.drawable.reddot);
                Returnstatus.setText("Rejected By the IRS");
                Returnstatus.setTextColor(mContext.getResources().getColor(R.color.Returnred));

            }

            //  AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID());
            try {
                TY = Integer.parseInt(businessListReturnDetailVector.get(groupPosition).getTY());

            } catch (Exception e) {
                e.printStackTrace();
                new SendException(mContext, e);
            }

            if (!businessListReturnDetailVector.get(groupPosition).getRN().equalsIgnoreCase("") && !businessListReturnDetailVector.get(groupPosition).getRN().equalsIgnoreCase("null")) {
                RN.setText(businessListReturnDetailVector.get(groupPosition).getRN());
            }

            if (!businessListReturnDetailVector.get(groupPosition).getUDTS().equalsIgnoreCase("") && !businessListReturnDetailVector.get(groupPosition).getUDTS().equalsIgnoreCase("null")) {
                UDT.setText(businessListReturnDetailVector.get(groupPosition).getUDTS());
            }

            if (!businessListReturnDetailVector.get(groupPosition).getExtensionType().equalsIgnoreCase("") && !businessListReturnDetailVector.get(groupPosition).getExtensionType().equalsIgnoreCase("null") && businessListReturnDetailVector.get(groupPosition).getExtensionType().equalsIgnoreCase("THREEMONTH")) {
                extensionType.setText("Automatic");
            } else
                extensionType.setText("Not Automatic");

        } catch (
                Exception e
                )

        {
            e.printStackTrace();
            new SendException(mContext, e);
        }

        return convertView;
    }

    android.support.v7.widget.CardView appovalLetterbtn, formbtn, receiptbtn;

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));

        try {


            TypeFaceClass typeface = new TypeFaceClass(mContext);

            if (convertView == null) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = inflater.inflate(R.layout.copyofdashboard_expandablelistview_child, null);
            }

            android.support.v7.widget.CardView Delete = (android.support.v7.widget.CardView) convertView.findViewById(R.id.Delete);
            android.support.v7.widget.CardView viewreject = (android.support.v7.widget.CardView) convertView.findViewById(R.id.viewreject);
            android.support.v7.widget.CardView edit1 = (android.support.v7.widget.CardView) convertView.findViewById(R.id.edit1);
            final android.support.v7.widget.CardView edit2 = (android.support.v7.widget.CardView) convertView.findViewById(R.id.edit2);
            android.support.v7.widget.CardView continueleftof = (android.support.v7.widget.CardView) convertView.findViewById(R.id.continueleftof);

            appovalLetterbtn = (android.support.v7.widget.CardView) convertView.findViewById(R.id.appovalLetterbtn);
            formbtn = (android.support.v7.widget.CardView) convertView.findViewById(R.id.formbtn);
            receiptbtn = (android.support.v7.widget.CardView) convertView.findViewById(R.id.receiptbtn);
            TextView appovalLetterText = (TextView) convertView.findViewById(R.id.appovalLetterText);
            TextView formbtnText = (TextView) convertView.findViewById(R.id.formbtnText);
            TextView receiptbtnText = (TextView) convertView.findViewById(R.id.receiptbtnText);

            appovalLetterText.setTypeface(typeface.fontawesome_webfont());
            formbtnText.setTypeface(typeface.fontawesome_webfont());
            receiptbtnText.setTypeface(typeface.fontawesome_webfont());

            appovalLetterbtn.setVisibility(View.GONE);


            formbtn.setVisibility(View.GONE);
            receiptbtn.setVisibility(View.GONE);


            TextView viewrejectText = (TextView) convertView.findViewById(R.id.viewrejectText);
            viewrejectText.setTypeface(typeface.fontawesome_webfont());

            TextView DeleteText = (TextView) convertView.findViewById(R.id.DeleteText);
            DeleteText.setTypeface(typeface.fontawesome_webfont());


            TextView continueleftofText = (TextView) convertView.findViewById(R.id.continueleftofText);
            continueleftofText.setTypeface(typeface.NotoSans_Regular());


            TextView edit1Text = (TextView) convertView.findViewById(R.id.edit1Text);
            edit1Text.setTypeface(typeface.NotoSans_Regular());

            TextView edit2Text = (TextView) convertView.findViewById(R.id.edit2Text);
            edit2Text.setTypeface(typeface.NotoSans_Regular());

            TextView taxperoid = (TextView) convertView.findViewById(R.id.taxperoid);

            TextView terminated = (TextView) convertView.findViewById(R.id.terminated);

            LinearLayout principal_Details = (LinearLayout) convertView.findViewById(R.id.principal_Details);

            TextView principalofficetextView = (TextView) convertView.findViewById(R.id.principalofficetextView);

            TextView principalname = (TextView) convertView.findViewById(R.id.principalname);

            TextView principaladdress = (TextView) convertView.findViewById(R.id.principaladdress);

            final TextView principalstatezip = (TextView) convertView.findViewById(R.id.principalstatezip);

            TextView taxtitle = (TextView) convertView.findViewById(R.id.taxperoidtitle);

            TextView terminatedtextView = (TextView) convertView.findViewById(R.id.terminatedtextView);

            TextView FormType = (TextView) convertView.findViewById(R.id.FormType);

            FormType.setTypeface(typeface.NotoSans_Regular(), Typeface.BOLD);

            taxtitle.setTypeface(typeface.NotoSans_Regular());

            terminatedtextView.setTypeface(typeface.NotoSans_Regular());

            taxperoid.setTypeface(typeface.NotoSans_Regular());

            terminated.setTypeface(typeface.NotoSans_Regular());

            principalofficetextView.setTypeface(typeface.NotoSans_Regular(), Typeface.BOLD);

            principalname.setTypeface(typeface.NotoSans_Regular());

            principaladdress.setTypeface(typeface.NotoSans_Regular());

            principalstatezip.setTypeface(typeface.NotoSans_Regular());


            Log.e("FID", businessListReturnDetailVector.get(groupPosition).getFID());

            if (!businessListReturnDetailVector.get(groupPosition).getFNAME().equalsIgnoreCase("null")) {
                FormType.setText(businessListReturnDetailVector.get(groupPosition).getTES());
            }


            // 8868 Form
            if (businessListReturnDetailVector.get(groupPosition).getFID().equalsIgnoreCase("4") && !businessListReturnDetailVector.get(groupPosition).getFID().equalsIgnoreCase("null")) {
                taxperoid.setText(businessListReturnDetailVector.get(groupPosition).getTP());

                terminatedtextView.setText("Tax Due: ");

                terminated.setText(businessListReturnDetailVector.get(groupPosition).getGEN());

                principalofficetextView.setText("Books are in the care of Details:");

                edit1.setVisibility(View.VISIBLE);

                edit2.setVisibility(View.VISIBLE);

                if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("0") || businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("1") || businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("4")) {

                    if (businessListReturnDetailVector.get(groupPosition).getTES() != null && (businessListReturnDetailVector.get(groupPosition).getTES().equalsIgnoreCase("Form 8870")) && businessListReturnDetailVector.get(groupPosition).getISPAID().equalsIgnoreCase("true")) {

                        appovalLetterbtn.setVisibility(View.GONE);
                        formbtn.setVisibility(View.VISIBLE);
                        receiptbtn.setVisibility(View.VISIBLE);

                        continueleftof.setVisibility(View.GONE);

                    } else {

                        appovalLetterbtn.setVisibility(View.GONE);
                        formbtn.setVisibility(View.GONE);
                        receiptbtn.setVisibility(View.GONE);

                        continueleftof.setVisibility(View.VISIBLE);
                    }


                    edit1Text.setText("Edit");
                    edit2Text.setText("Edit");


                    AppConfigManager.saveMode(mContext, "Edit");

//                    edit1.setBackgroundResource(R.drawable.editbtnlarge);
//
                    //   edit2.setBackgroundResource(R.drawable.editbtnlarge);


                    flag_for_view_edit = true;


                } else {
                    continueleftof.setVisibility(View.GONE);

                    // edit1.setBackgroundResource(R.drawable.viewbtnlarge);

                    edit1Text.setText("View");
                    edit2Text.setText("View");

                    AppConfigManager.saveMode(mContext, "View");
                    // edit2.setBackgroundResource(R.drawable.viewbtnlarge);

                    if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("2")) {

                        appovalLetterbtn.setVisibility(View.GONE);

                    } else if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("3")) {

                        appovalLetterbtn.setVisibility(View.VISIBLE);
                    }

                    formbtn.setVisibility(View.VISIBLE);
                    receiptbtn.setVisibility(View.VISIBLE);

                    flag_for_view_edit = false;

                }

                if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("2") || businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("3") || businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("4")) {

                    Delete.setVisibility(View.INVISIBLE);

                } else {

                    if (businessListReturnDetailVector.get(groupPosition).getTES() != null && (businessListReturnDetailVector.get(groupPosition).getTES().equalsIgnoreCase("Form 8870")) && businessListReturnDetailVector.get(groupPosition).getISPAID().equalsIgnoreCase("true")) {

                        Delete.setVisibility(View.GONE);

                    } else {

                        Delete.setVisibility(View.VISIBLE);

                    }


                }

                if (businessListReturnDetailVector.get(groupPosition).getFSID().equalsIgnoreCase("4")) {
                    viewreject.setVisibility(View.VISIBLE);
                } else {
                    viewreject.setVisibility(View.GONE);
                }

                try {
                    taxperoid.setText(businessListReturnDetailVector.get(groupPosition).getTP());

                    if (businessListReturnDetailVector.get(groupPosition).getParseModel() != null) {

                        principal_Details.setVisibility(View.VISIBLE);

                        BookIncareOfModel bookModel = businessListReturnDetailVector.get(groupPosition).getParseModel();


                        if (bookModel.getBookInCareOf() != null && !bookModel.getBookInCareOf().equalsIgnoreCase("null")) {
                            principalname.setText(bookModel.getBookInCareOf());
                        }

                        String address = utils.addressOnly(mContext, bookModel.getBCOAddress1(), bookModel.getBCOAddress2());


                        if (address != null && !address.equalsIgnoreCase("null"))
                            principaladdress.setText(address);
                        try {

                            DatabaseHandler databaseHandler = new DatabaseHandler(mContext);


                            String countryName = null;

                            try {

                                countryName = databaseHandler.getCountryNameFromCID(bookModel.getBCOCountryId());


                            } catch (Exception e) {

                                e.printStackTrace();

                                new SendException(mContext, e);

                            }

                            String city = utils.CityStateOnly(mContext, bookModel.getBCOCity(), bookModel.getBCOStateCode(), bookModel.getBCOState(), countryName, bookModel.getBCOZip(), bookModel.isBCOIsForeignAddress());

                            principalstatezip.setText(city);
                        } catch (Exception e) {

                            e.printStackTrace();

                            new SendException(mContext, e);
                        }
                    } else {

                        principal_Details.setVisibility(View.GONE);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    new SendException(mContext, e);
                }

            }

            edit1.setOnClickListener(new

                                             OnClickListener() {

                                                 @Override
                                                 public void onClick(View v) {
                                                     AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID());
                                                     try {
                                                         if (cd.isConnectingToInternet()) {

                                                             AppConfigManager.saveFlag(mContext, 0);

                                                             TaxPageFragment();

//							Intent i = new Intent(mContext, TaxYearEligibility.class);
//
//							AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID(groupPosition));
//
//							System.out.println("EDIT RID " + businessListReturnDetailVector.get(groupPosition).getRID(groupPosition));
//
//							i.putExtra("RID", businessListReturnDetailVector.get(groupPosition).getRID(groupPosition));
//
//							i.putExtra("FLAG", "0");
//
//							if (businessListReturnDetailVector.get(groupPosition).getFSID(groupPosition).equalsIgnoreCase("0") || businessListReturnDetailVector.get(groupPosition).getFSID(groupPosition).equalsIgnoreCase("1") || businessListReturnDetailVector.get(groupPosition).getFSID(groupPosition).equalsIgnoreCase("4"))
//							{
//								i.putExtra("MODE", "edit");
//
//								AppConfigManager.saveMode(mContext, "edit");
//
//							}
//							else if (businessListReturnDetailVector.get(groupPosition).getFSID(groupPosition).equalsIgnoreCase("2"))
//							{
//								i.putExtra("MODE", "viewT");
//
//								AppConfigManager.saveMode(mContext, "viewT");
//
//								AppConfigManager.saveFirstMode(mContext, "SECOND");
//
//							}
//							else if (businessListReturnDetailVector.get(groupPosition).getFSID(groupPosition).equalsIgnoreCase("3"))
//							{
//								i.putExtra("MODE", "viewA");
//
//								AppConfigManager.saveMode(mContext, "viewA");
//
//							}
//
//							mContext.startActivity(i);
//
//							((Activity) mContext).finish();
                                                         } else {

                                                             utils.errorMessage(mContext, "No Internet Connection");

                                                         }
                                                     } catch (Exception e) {
                                                         e.printStackTrace();
                                                         new SendException(mContext, e);
                                                     }

                                                 }
                                             }

            );

            viewreject.setOnClickListener(new

                                                  OnClickListener() {

                                                      @Override
                                                      public void onClick(View v) {
                                                          try {
                                                              if (cd.isConnectingToInternet()) {
                                                                  AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID());

                                                                  Log.i("Dashboard", "rejected list size : " + businessListReturnDetailVector.get(groupPosition).getErrorVector().size());

                                                                  viewPopup(groupPosition);

                                                                  if (errorDialog != null)
                                                                      errorDialog.show();
//
//                            Intent returnrejected = new Intent(mContext, Rejectedreturnerror.class);
//
                                                                  //                         mContext.startActivity(returnrejected);
                                                              } else {
                                                                  utils.errorMessage(mContext, "No Internet Connection");

                                                              }
                                                          } catch (Exception e) {
                                                              e.printStackTrace();
                                                              new SendException(mContext, e);
                                                          }
                                                      }
                                                  }

            );

            edit2.setOnClickListener(new

                                             OnClickListener() {

                                                 @Override
                                                 public void onClick(View v) {
                                                     AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID());
                                                     try {
                                                         if (cd.isConnectingToInternet()) {

                                                             AppConfigManager.saveFlag(mContext, 3);

                                                             TaxPageFragment();

//                            if (flag_for_view_edit == false) {
//                                Intent i = new Intent(mContext, EditBusinessActivity.class);
//
//                                i.putExtra("BID", AppConfigManager.getReturnBID(mContext));
//
//                                i.putExtra("Dis", "1");
//
//                                mContext.startActivity(i);
//                            } else {
//                                Intent i = new Intent(mContext, EditBusinessActivity.class);
//
//                                i.putExtra("BID", AppConfigManager.getReturnBID(mContext));
//
//                                i.putExtra("Dis", "0");
//
//                                mContext.startActivity(i);
                                                             //  }
                                                         } else {

                                                             utils.errorMessage(mContext, "No Internet Connection");

                                                         }
                                                     } catch (Exception e) {
                                                         e.printStackTrace();
                                                         new SendException(mContext, e);
                                                     }

                                                 }
                                             }

            );

            appovalLetterbtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID());

                    approvalPopup();
                }
            });


            formbtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID());


                    formPopup();
                }
            });

            receiptbtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID());

                    receiptPopup();
                }
            });


            Delete.setOnClickListener(new

                                              OnClickListener() {

                                                  @Override
                                                  public void onClick(View v) {
                                                      AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID());

                                                      try {

                                                          if (cd.isConnectingToInternet()) {
                                                              new AlertDialog.Builder(mContext).setTitle("Delete Return").setMessage("Are you sure you want to delete this Return?").setPositiveButton("No", new DialogInterface.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(DialogInterface dialog, int which) {

                                                                  }
                                                              }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(DialogInterface dialog, int which) {
                                                                      try {
                                                                          DeleteReturn deletereturn = new DeleteReturn(businessListReturnDetailVector.get(groupPosition).getRID(), mContext, AppConfigManager.getDID(mContext), AppConfigManager.getLoggedUid(mContext), AppConfigManager.getAccessToken(mContext), selectedposition, lv);

                                                                          deletereturn.setOnResultListener(new DeleteReturn.DeleteInterface() {
                                                                              @Override
                                                                              public void onResultSuccess(ArrayList<String> datesModel) {
                                                                                  deleteDetails = datesModel;
                                                                                  viewpdfHandler.post(deleteRunnable);
                                                                              }
                                                                          });

                                                                          deletereturn.execute();

                                                                      } catch (Exception e) {
                                                                          e.printStackTrace();

                                                                          new SendException(mContext, e);
                                                                      }

                                                                  }
                                                              }).setIcon(R.drawable.delete).show();
                                                          } else {

                                                              utils.errorMessage(mContext, "No Internet Connection");

                                                          }
                                                      } catch (Exception e) {
                                                          e.printStackTrace();
                                                          new SendException(mContext, e);
                                                      }
                                                  }
                                              }

            );

            continueleftof.setOnClickListener(new

                                                      OnClickListener() {

                                                          @Override
                                                          public void onClick(View v) {
                                                              AppConfigManager.saveReturnRid(mContext, businessListReturnDetailVector.get(groupPosition).getRID());
                                                              try {
                                                                  if (cd.isConnectingToInternet()) {

                                                                      AppConfigManager.saveFlag(mContext, 2);
                                                                      TaxPageFragment();
                                                                  } else {

                                                                      utils.errorMessage(mContext, "No Internet Connection");
                                                                  }
                                                              } catch (Exception e) {
                                                                  e.printStackTrace();
                                                                  new SendException(mContext, e);
                                                              }

                                                          }
                                                      }

            );

        } catch (
                Exception e
                )

        {
            e.printStackTrace();
            new SendException(mContext, e);
        }

        return convertView;
    }

    protected void pdfapprovedview() {
        try {
//            PdfApprovedURL pdfurl = new PdfApprovedURL(jsonstring, mContext);
//
//            PdfApprovedOnAsyncResultByCondition pdfasync = new PdfApprovedOnAsyncResultByCondition() {
//
//                @Override
//                public void onResultSuccess(String message) {
//                    url = "http://docs.google.com/viewer?url=" + message;
//
//                    Intent web = new Intent(mContext, WebActivity.class);
//
//                    web.putExtra("URL", url);
//
//                    mContext.startActivity(web);
//
//                }
//
//                @Override
//                public void onResultFail(int resultCode, String errorMessage) {
//
//                }
//            };
//            pdfurl.setOnResultListener(pdfasync);
//            pdfurl.execute();
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

    }

    Handler viewpdfHandler = new Handler();

    Runnable pdfRunnable = new Runnable() {

        @Override
        public void run() {

        }
    };

    protected void pdfview(String pdfType) {
        try {
            String jsonString = null;
            try {
                obj = new JSONObject();

                obj1 = new JSONObject();

                try {

                    obj1 = new JSONObject();

                    obj.put("RID", AppConfigManager.getReturnRID(mContext));

                    obj.put("UId", AppConfigManager.getLoggedUid(mContext));

                    obj.put("DId", AppConfigManager.getDID(mContext));

                    obj.put("AT", AppConfigManager.getAccessToken(mContext));

                    obj1.put("Return", obj);

                    jsonString = obj1.toString();

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            } catch (Exception e) {
                new SendException(mContext, e);
            }

            PdfURL pdfurl = new PdfURL(pdfType, jsonString, mContext);

            PdfURL.PdfOnAsyncResultByCondition pdfasync = new PdfURL.PdfOnAsyncResultByCondition() {

                @Override
                public void onResultSuccess(String message) {

                    String url = "http://docs.google.com/viewer?url=" + message;

                    Intent web = new Intent(mContext, WebActivity.class);

                    web.putExtra("URL", url);

                    mContext.startActivity(web);

                }

                @Override
                public void onResultFail(int resultCode, String errorMessage) {

                }
            };
            pdfurl.setOnResultListener(pdfasync);

            pdfurl.execute();
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

    }

    private String down(String Url) {
        int count;
        String path;
        try {
            URL url = new URL(Url);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            OutputStream output = new FileOutputStream("/sdcard/downloadedfile.pdf");

            byte data[] = new byte[1024];

            while ((count = input.read(data)) != -1) {

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        path = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.pdf";
        return path;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private void viewPopup(int pos) {


        View[] rejectederros = GetRejectedreturnErrors(pos);

        LayoutInflater factory = LayoutInflater.from(mContext);
        final View deleteDialogView = factory.inflate(
                R.layout.rejectedbyirs, null);


        dialogInitial(deleteDialogView, rejectederros);

        dialogOnclick();

        errorDialog = new AlertDialog.Builder(mContext).create();
        errorDialog.setView(deleteDialogView);


    }

    LinearLayout rejectedlayout;

    TextView errorcodetextview, errormessagetextview, erroractiontextview, rejectedbyirstextview;

    ImageView closbtn;

    Vector<AuditModel> rejectedreturnList;

    private void dialogInitial(View errorDialogView, View[] rejectederrors) {
        try {
            TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

            rejectedlayout = (LinearLayout) errorDialogView.findViewById(R.id.rejectedlayout);

            rejectedbyirstextview = (TextView) errorDialogView.findViewById(R.id.rejectedbyirstextview);

            errorcodetextview = (TextView) errorDialogView.findViewById(R.id.errorcodetextview);

            errormessagetextview = (TextView) errorDialogView.findViewById(R.id.errormessagetextview);

            erroractiontextview = (TextView) errorDialogView.findViewById(R.id.erroractiontextview);

            closbtn = (ImageView) errorDialogView.findViewById(R.id.closbtn);

            rejectedbyirstextview.setTypeface(typeFaceClass.NotoSans_Regular());

            errorcodetextview.setTypeface(typeFaceClass.NotoSans_Regular());

            errormessagetextview.setTypeface(typeFaceClass.NotoSans_Regular());


            erroractiontextview.setTypeface(typeFaceClass.NotoSans_Regular());

            if (rejectederrors != null && rejectederrors.length > 0)

                for (int i = 0; i < rejectederrors.length; i++)

                    rejectedlayout.addView(rejectederrors[i]);

            cd = new ConnectionDetector(mContext);
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
    }

    private void dialogOnclick() {

        try {
            closbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (errorDialog != null && errorDialog.isShowing()) {
                        errorDialog.dismiss();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

    }

    private View[] GetRejectedreturnErrors(int pos) {

        View rejectederrors[] = null;

        try {


            if (businessListReturnDetailVector != null && businessListReturnDetailVector.get(pos) != null && businessListReturnDetailVector.get(pos).getErrorVector() != null && businessListReturnDetailVector.get(pos).getErrorVector().size() != 0) {

                rejectedreturnList = businessListReturnDetailVector.get(pos).getErrorVector();

                rejectederrors = new View[rejectedreturnList.size()];

                Log.e("SIZE OF REJECTED ERROR", "SIZE " + rejectedreturnList.size());

                for (int i = 0; i < rejectedreturnList.size(); i++) {


                    rejectederrors[i] = geterror(R.layout.error_adapter, i, "-", rejectedreturnList.get(pos).getEM(), "-", rejectedreturnList.size());


                }
            } else {
                rejectederrors[0] = geterror(R.layout.error_adapter, 0, "", "No Errors Found", "", 0);


            }

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

        return rejectederrors;
    }


    private View geterror(int rejectederrorlist, final int i, String EC, String EM, final String action, int size) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View errorview = inflater.inflate(rejectederrorlist, null);
        try {
            TypeFaceClass typeface = new TypeFaceClass(mContext);

            TextView errorcode = (TextView) errorview.findViewById(R.id.error_Code);

            errorcode.setTypeface(typeface.NotoSans_Regular());

            TextView errormessage = (TextView) errorview.findViewById(R.id.error_message);

            errormessage.setTypeface(typeface.NotoSans_Regular());

            final TextView erroraction = (TextView) errorview.findViewById(R.id.fix_me);

            erroraction.setTypeface(typeface.NotoSans_Regular());

            if (size > 0) {
                Log.i("Error", "Error Found");

                errorcode.setText(EC);

                errormessage.setText(EM);

                errormessage.setGravity(Gravity.LEFT);

                erroraction.setPaintFlags(erroraction.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                erroraction.setTextColor(mContext.getResources().getColor(R.color.red));

                if (action != null && action.equalsIgnoreCase("null") || action.equalsIgnoreCase("-")) {
                    if (erroraction.getText().toString().equalsIgnoreCase("-") == false) {
                        erroraction.setText("-");

                        erroraction.setPaintFlags(0);
                    }
                } else {
                    erroraction.setText("FIX ME");
                }

                erroraction.setTextColor(Color.parseColor("#D8432E"));
            } else {
                Log.i("No Error", "No Error");

                errorcode.setText("");

                errormessage.setText("No Errors Found");

                errormessage.setGravity(Gravity.CENTER);

                erroraction.setText("");

            }

            erroraction.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (erroraction.getText().toString().equalsIgnoreCase("FIX ME") == true) {

                        Error er = Error.valueOf(action);

                        switch (er) {
                            case Summary:


                                if (cd.isConnectingToInternet()) {


                                    if (errorDialog != null && errorDialog.isShowing()) {
                                        errorDialog.dismiss();
                                    }
                                    summary.post(summaryfragment);
                                } else {

                                    utils.errorMessage(mContext, "No Internet Connection");

                                }

                                break;

                            case TaxYear:

                                if (cd.isConnectingToInternet()) {
                                    if (errorDialog != null && errorDialog.isShowing()) {
                                        errorDialog.dismiss();
                                    }
                                    taxyear.post(tax);
                                } else {

                                    utils.errorMessage(mContext, "No Internet Connection");

                                }

                                break;

                            default:
                                break;
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
        return errorview;

    }

    Handler taxyear = new Handler();

    Runnable tax = new Runnable() {

        @Override
        public void run() {

            try {
                TaxPageFragment();
            } catch (Exception e) {
                e.printStackTrace();
                new SendException(mContext, e);
            }

        }
    };

    Handler summary = new Handler();

    Runnable summaryfragment = new Runnable() {

        @Override
        public void run() {

            try {

                AppConfigManager.saveFlag(mContext, 1);

                TaxPageFragment();

            } catch (Exception e) {
                e.printStackTrace();
                new SendException(mContext, e);
            }

        }
    };

    private void TaxPageFragment() {

        businesslistview.setEnabled(false);

        if (mDrawerLayout != null)
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        Fragment newFragment = new CommonTaxFragment(mContext);

        FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(replaceId, newFragment);

        transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }

    Runnable deleteRunnable = new Runnable() {

        @Override
        public void run() {
            if (deleteDetails != null && deleteDetails.get(0).equalsIgnoreCase("Success")) {

                try {

                    getBusineesListDetails(AppConfigManager.getBID(mContext));

                } catch (Exception e) {
                    new SendException(mContext, e);
                }

            }
            if (deleteDetails.get(1).equalsIgnoreCase("Failure") && deleteDetails.get(1) != null) {
                if (deleteDetails.get(1).equals("null") == false) {

                    utils.errorMessage(mContext, deleteDetails.get(1));
                } else {

                    utils.errorMessage(mContext, "Please try again later");
                }

            }
        }
    };


    AddBussinessInputModel BusinessListDetailRequestModel;

    ReturnModel returnModel;

    public void getBusineesListDetails(String bid) {


        BusinessListDetailRequestModel = new AddBussinessInputModel();


        BusinessListDetailRequestModel.setAT(AppConfigManager.getAccessToken(mContext));

        BusinessListDetailRequestModel.setDID(AppConfigManager.getDID(mContext));

        BusinessListDetailRequestModel.setUId(AppConfigManager.getLoggedUid(mContext));

        BusinessListDetailRequestModel.setBId(bid);

        GetBusinessReturnListDetailsURL getBusinessReturnListDetailsURL = new GetBusinessReturnListDetailsURL(BusinessListDetailRequestModel.getBusinessReturnListDetailRequestModel(), mContext);

        getBusinessReturnListDetailsURL.setOnResultListener(onAsyncResultGetBusinessListDetailURL);

        getBusinessReturnListDetailsURL.execute();


    }

    GetBusinessReturnListDetailsURL.OnAsyncResultGetBusinessReturnListDetailURL onAsyncResultGetBusinessListDetailURL = new GetBusinessReturnListDetailsURL.OnAsyncResultGetBusinessReturnListDetailURL() {

        @Override
        public void onResultSuccess(ReturnModel Result) {


            returnModel = Result;


            businessDeatilHandler.post(businessListDetailRubbable);


        }


        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    Handler businessDeatilHandler = new Handler();

    Runnable businessListDetailRubbable = new Runnable() {
        @Override
        public void run() {

            int CY = Utils.getCurrentyear() - 1;
            int PY = Utils.getCurrentyear() - 2;


            Fragment newFragment = new DashboardFragment(mContext, businessListModel, returnModel, mDrawerLayout, businesslist, businessSelection, businessClikInterface);
            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

            transaction.setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed


            transaction.replace(R.id.wholevertical, newFragment);

            transaction.addToBackStack(FragmentNameConfig.DASHBOARD_FRAGMENT);
// Commit the transaction
            transaction.commit();

        }
    };

    private void addPopup(String emailType) {

        LayoutInflater factory = LayoutInflater.from(mContext);
        final View deleteDialogView = factory.inflate(
                R.layout.editsendemail, null);

        emailDialogInitial(deleteDialogView);

        emailDialogOnclick(emailType);

        emailDialog = new android.support.v7.app.AlertDialog.Builder(mContext).create();
        emailDialog.setView(deleteDialogView);

    }

    public void emailDialogInitial(View deleteDialogView) {


        try {

            sendEmail = (android.support.v7.widget.CardView) deleteDialogView.findViewById(R.id.sendEmail);

            emailCancel = (android.support.v7.widget.CardView) deleteDialogView.findViewById(R.id.emailCancel);

            emailTo = (EditText) deleteDialogView.findViewById(R.id.emailTo);

            emailSubject = (EditText) deleteDialogView.findViewById(R.id.emailSubject);

            emailContent = (EditText) deleteDialogView.findViewById(R.id.emailContent);
        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }


    }

    public void emailDialogOnclick(final String emailType) {

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int x = SetInputs(emailType);

                    Log.e("FLAG X", "X value" + x);

                    if (x == 1) {
                        SendEmailnow(emailType);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    new SendException(mContext, e);
                }

            }
        });

        emailCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    emailDialog.dismiss();

                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(emailTo.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(emailContent.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(emailSubject.getWindowToken(), 0);

                } catch (Exception e) {
                    e.printStackTrace();
                    new SendException(mContext, e);
                }


            }
        });
    }

    public int SetInputs(String emailType) {

        int flag = 1;

        try {
            obj = new JSONObject();

            obj1 = new JSONObject();

            obj.put("RID", AppConfigManager.getReturnRID(mContext));

            obj.put("UId", AppConfigManager.getLoggedUid(mContext));

            obj.put("EA", AppConfigManager.getUserName(mContext));

            if (emailTo.getText().toString().equalsIgnoreCase("")) {
                emailTo.setError("Enter email address");
                flag = 0;
            }

            if (isValidEmail(emailTo.getText().toString()) == true) {
                obj.put("SEA", emailTo.getText().toString());
            } else {
                emailTo.setError("Enter valid Email Address");
                flag = 0;
            }

            obj.put("SUB", emailSubject.getText().toString());

            obj.put("MES", emailContent.getText().toString());

            obj.put("AT", AppConfigManager.getAccessToken(mContext));

            obj.put("DId", AppConfigManager.getDID(mContext));


            if (emailType != null && emailType.equalsIgnoreCase("Form8868"))

                obj1.put("MobSendForm", obj);

            else
                obj1.put("SendForm", obj);


            Log.e("Request X", "Request value" + obj1.toString());
        } catch (JSONException e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return flag;
    }

    public final static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            // android Regex to check the email address Validation
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    void SendEmailnow(String emailType) {

        try {

            SendEmailURL sendemailurl = new SendEmailURL(emailType, obj1.toString(), mContext);

            SendEmailURL.OnAsyncResultByConditionEmail onasyncEmail = new SendEmailURL.OnAsyncResultByConditionEmail() {

                @Override
                public void onResultSuccess(final String message) {

                    emailResponse = message;

                    handler.post(emailRunnable);

                }

                @Override
                public void onResultFail(int resultCode, String errorMessage) {

                }

            };

            sendemailurl.setOnResultListener(onasyncEmail);
            sendemailurl.execute();
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
    }

    Handler handler = new Handler();

    Runnable emailRunnable = new Runnable() {
        @Override
        public void run() {

            try {
                if (emailResponse != null && !emailResponse.equalsIgnoreCase("null")) {
                    if (emailResponse.equalsIgnoreCase("Success")) {

                        utils.successMessage(mContext, "Email sent Successfully");

                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                        imm.hideSoftInputFromWindow(emailTo.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(emailContent.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(emailSubject.getWindowToken(), 0);

                        emailTo.setText("");

                        emailSubject.setText("");

                        emailContent.setText("");

                        if (emailDialog != null)

                            emailDialog.dismiss();

                    }

                    if (emailResponse.equalsIgnoreCase("Failure")) {

                        utils.errorMessage(mContext, "Email Sending Failure");

                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(emailTo.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(emailContent.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(emailSubject.getWindowToken(), 0);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                new SendException(mContext, e);
            }
        }
    };

    LayoutInflater mInflater;


    PopupWindow approvalDropDown;

    private PopupWindow approvalPopup() {

        try {

            mInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.popupwindow, null);

            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView viewPdf = (TextView) layout.findViewById(R.id.ItemA);


            final TextView viewEmail = (TextView) layout.findViewById(R.id.ItemB);

            TypeFaceClass typeface = new TypeFaceClass(mContext);
            viewPdf.setTypeface(typeface.fontawesome_webfont());
            viewEmail.setTypeface(typeface.fontawesome_webfont());

            viewPdf.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    approvalDropDown.dismiss();
                    pdfview("Approval");

                }
            });

            viewEmail.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    approvalDropDown.dismiss();
                    addPopup("Approval");
                    emailDialog.show();
                }
            });


            layout.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
            approvalDropDown = new PopupWindow(layout, 300,
                    200, true);


            approvalDropDown.setOutsideTouchable(true);

            approvalDropDown.setBackgroundDrawable(new BitmapDrawable());

            approvalDropDown.getContentView().setFocusableInTouchMode(true);
            approvalDropDown.getContentView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        approvalDropDown.dismiss();
                        return true;
                    }
                    return false;
                }
            });

            approvalDropDown.showAsDropDown(appovalLetterbtn, 0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvalDropDown;

    }


    PopupWindow formDropDown;

    private PopupWindow formPopup() {

        try {

            mInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.popupwindow, null);

            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView viewPdf = (TextView) layout.findViewById(R.id.ItemA);


            final TextView viewEmail = (TextView) layout.findViewById(R.id.ItemB);

            TypeFaceClass typeface = new TypeFaceClass(mContext);
            viewPdf.setTypeface(typeface.fontawesome_webfont());
            viewEmail.setTypeface(typeface.fontawesome_webfont());

            viewPdf.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    formDropDown.dismiss();
                    pdfview("Form8868");


                }
            });

            viewEmail.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    formDropDown.dismiss();
                    addPopup("Form8868");
                    emailDialog.show();
                }
            });


            layout.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
            formDropDown = new PopupWindow(layout, 300,
                    200, true);

            formDropDown.setOutsideTouchable(true);

            formDropDown.setBackgroundDrawable(new BitmapDrawable());

            formDropDown.getContentView().setFocusableInTouchMode(true);
            formDropDown.getContentView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        formDropDown.dismiss();
                        return true;
                    }
                    return false;
                }
            });

            formDropDown.showAsDropDown(formbtn, 0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return formDropDown;

    }

    PopupWindow receiptDropDown;

    private PopupWindow receiptPopup() {

        try {

            mInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = mInflater.inflate(R.layout.popupwindow, null);


            //If you want to add any listeners to your textviews, these are two //textviews.
            final TextView viewPdf = (TextView) layout.findViewById(R.id.ItemA);


            final TextView viewEmail = (TextView) layout.findViewById(R.id.ItemB);

            TypeFaceClass typeface = new TypeFaceClass(mContext);
            viewPdf.setTypeface(typeface.fontawesome_webfont());
            viewEmail.setTypeface(typeface.fontawesome_webfont());

            viewPdf.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    receiptDropDown.dismiss();
                    pdfview("Receipt");


                }
            });

            viewEmail.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    receiptDropDown.dismiss();
                    addPopup("Receipt");
                    emailDialog.show();

                }
            });


            layout.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
            receiptDropDown = new PopupWindow(layout, 300,
                    200, true);

            receiptDropDown.setOutsideTouchable(true);

            receiptDropDown.setBackgroundDrawable(new BitmapDrawable());

            receiptDropDown.getContentView().setFocusableInTouchMode(true);
            receiptDropDown.getContentView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        receiptDropDown.dismiss();
                        return true;
                    }
                    return false;
                }
            });

            receiptDropDown.showAsDropDown(receiptbtn, 0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiptDropDown;

    }


}
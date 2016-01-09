package com.span.expressextension8868.controller.controller;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.span.expressextension8868.R;
import com.span.expressextension8868.businesslogic.adapter.BusinessListAdapter;
import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyApplication;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.NetworkChangeReceiver;
import com.span.expressextension8868.utils.utility.RecyclerItemClickListener;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.SupportDialog;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.webservice.webservices.GetBusinessListURL;
import com.span.expressextension8868.webservice.webservices.GetBusinessReturnListDetailsURL;

import java.util.Vector;

public class DashboardActivity extends CommonTopHeaderClass implements DashboardFragment.BusinessClikInterface {

    //  private Toolbar mToolbar;

    Context mContext = DashboardActivity.this;

    String toLayout;

    private ProgressDialog pd;

    int listPosition = 0;


    ListView businesslist;

    BusinessListAdapter adapter;

    NetworkChangeReceiver receiver;

    private DrawerLayout mDrawerLayout;

    int prePostion = 0;

    boolean fromDashboard = true;

    TextView DashBoardTitle;

    TextView loginname, logout, dashboardlink, support, cpaedit, EmailAddressText;

    Button addnewbusinessdashboard;

    EditText listfilter;

    //PersonalModel getPersonaldetailmodel;

    //PersonalModel personalmodel=new PersonalModel();

    //final PersonalModel pmodel=new PersonalModel();

    DatabaseHandler databasehandler;

    //CustomAdapter listadapter;

    LinearLayout logolayout;

    LinearLayout businesslistlayout, myAccount_Layout;

    FrameLayout rootlayout;

    RelativeLayout menulayout;

    ImageView menu, businesslistview, refresh;


    String mode;

    String isoutofcountry = null;

    //get business List

    AddBussinessInputModel BusinessListRequestModel, BusinessListDetailRequestModel;

    Vector<AddBussinessInputModel> businessListVector;

    ReturnModel returnListModel;

    boolean listClick;

    DashboardActivity test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));

        super.onCreate(savedInstanceState);

        View viewToLoad = LayoutInflater.from(this).inflate(R.layout.newdashboard, null);

        setContentView(viewToLoad);

        receiver = new NetworkChangeReceiver();

        test = this;

        this.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        databasehandler = new DatabaseHandler(mContext);

        Initialization(viewToLoad);

        try {
            Slidingdrawerfunc();
        } catch (Exception e) {
            e.printStackTrace();
        }


        setOnclickListener();


        // Get a Tracker (should auto-report)
        ((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);


        toLayout = getIntent().getStringExtra("TO_LAYOUT");

        pageRedirection(toLayout);


        MyAccountEnableDisable();
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Get an Analytics tracker to report app starts & uncaught exceptions
        // etc.
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Stop the analytics tracking
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    private void MyAccountEnableDisable() {
        try {
            if (Integer.parseInt(AppConfigManager.getCPA(mContext)) == 2) {
                myAccount_Layout.setVisibility(View.GONE);
            } else {
                myAccount_Layout.setVisibility(View.GONE);
            }
        } catch (Exception e) {

            e.printStackTrace();
            new SendException(mContext, e);
        }

    }


//    public void clickOnListItem(final int position, final ListView listview) {
//        listview.(listview, position, listview.getItemIdAtPosition(position));
//        /*
//        listview.post(new Runnable()
//		{
//
//			@Override
//			public void run()performItemClick
//			{
//				listview.setSelection(0);
//
//			}
//		});*/
//
//    }


    private void setOnclickListener() {

        rootlayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });


        addnewbusinessdashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Log.i("dashboard Fragment", "oncreate");

                    listClick = false;

                    fromDashboard = true;

                    AppConfigManager.saveBID(mContext, "0");

                    addOrgDetailsFragment("0");


                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);
                }
            }
        });

        refresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                getBusineesList();

            }
        });


//        refresh.setOnTouchListener(new OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                if (personalmodel.getPersonalinfoList().size() > 0) {
//
//                    try {
//                        clickOnListItem(selectionposition, businesslist);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//
//                        new SendException(mContext, e);
//                    }
//                }
//
//
//                return false;
//            }
//        });


        logolayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (listClick) {

                    try {

                        if (mDrawerLayout != null)

                            mDrawerLayout.openDrawer(businesslistlayout);

                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }
                } else {

                    if (mDrawerLayout != null)
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                    listClick = true;

                    if (fromDashboard)

                        dashBoardFragment();

                    else {
                        FragmentManager fm = getSupportFragmentManager();

                        if (fm.getBackStackEntryCount() > 0) {
                            fm.popBackStack();
                        } else {
                            finish();
                        }
                    }

                }


            }
        });


        menu.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                try {
                    mDrawerLayout.openDrawer(menulayout);
                } catch (Exception e) {
                    e.printStackTrace();

                    new SendException(mContext, e);
                }

                return false;
            }
        });


        support.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(menulayout);

                SupportDialog support = new SupportDialog(mContext);

                support.showSupport();
            }
        });

        dashboardlink.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    mDrawerLayout.closeDrawer(menulayout);
                    dashBoardFragment();

                } catch (Exception e) {
                    e.printStackTrace();

                    new SendException(mContext, e);
                }

            }
        });


        logout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    mDrawerLayout.closeDrawer(menulayout);

                    new AlertDialog.Builder(mContext).setTitle("Log off?").setMessage("Are you sure you want to log off?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Logout.logout(mContext);
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
/*
                            final Animation animAlpha = AnimationUtils.loadAnimation(mContext, R.anim.anim_alpha);

							((View) dialog).startAnimation(animAlpha);*/


                        }
                    }).setIcon(R.drawable.logoff).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    new SendException(mContext, e);
                }
            }
        });


        listfilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {/*
                try
				{
					if (personalmodel.getPersonalinfoList().size()> 0)
					{
						String text = listfilter.getText().toString().toLowerCase(Locale.getDefault());

						listadapter.filter(text);

					}
				}
				catch (Exception e)
				{
					e.printStackTrace();

					new SendException(mContext, e);
				}
			*/
            }
        });


//        businesslist.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {/*
//
//				try
//				{
//					selectionposition = position;
//
//					listadapter.setSelectedIndex(position);
//
//					if(mDrawerLayout.isDrawerOpen(businesslistlayout))
//					{
//						mDrawerLayout.closeDrawer(businesslistlayout);
//				}
//
//					AppConfigManager.savePD(mContext, personalmodel.getPersonalinfoList().get(position).getPDId());
//
//					AppConfigManager.saveReturnRid(mContext, personalmodel.getPersonalinfoList().get(position).getRID());
//
//					GetPersonalInfoDetails();
//
//					GetIRSPaymentService(personalmodel.getPersonalinfoList().get(position).getRID());
//				}
//				catch (Exception e)
//				{
//
//				e.printStackTrace();
//					new SendException(mContext, e);
//				}
//
//			*/
//            }
//        });


        businesslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // do whatever

                listPosition = position;


                if (position != prePostion) {

                    if (adapter != null) {
                        adapter.dataSet(position);

                        adapter.notifyDataSetChanged();
                    } else {

                        adapter = new BusinessListAdapter(mContext, businessListVector, R.layout.business_list);

                        businesslist.setAdapter(adapter);

                        adapter.dataSet(position);

                        adapter.notifyDataSetChanged();
                    }


                    prePostion = position;

                    mDrawerLayout.closeDrawer(businesslistlayout);

                    Log.i("Clicked Position is ", "postion : " + String.valueOf(position));

                    AppConfigManager.saveBusinessNameEIN(mContext, businessListVector.get(position).getOrganizationName(), businessListVector.get(position).getEin());

                    AppConfigManager.saveBID(mContext, businessListVector.get(position).getBId());

                    getBusineesListDetails(businessListVector.get(position).getBId());

                    Log.i("List click", "position : " + position);

                }
            }
        });


        cpaedit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                try {

                    Intent i = new Intent(DashboardActivity.this, EditCPAactivity.class);

                    startActivity(i);

                    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

                    finish();

                } catch (Exception e) {
                    e.printStackTrace();

                    new SendException(mContext, e);
                }
            }
        });


    }


    private class ProgressTask extends AsyncTask<String, Void, Void> {


        @Override
        protected void onPreExecute() {
            pd = MyCustomProgressDialog.ctor(mContext);

            pd.show();
        }

        @Override
        protected Void doInBackground(String... args) {

            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));

            finish();

            return null;
        }

        @Override
        protected void onPostExecute(Void success) {
            super.onPostExecute(success);

            pd.dismiss();

        }
    }

    private void GetIRSPaymentService(String RId) {/*
        try
		{
			IRSModel iRSPaymentModel=new IRSModel();

			iRSPaymentModel.setAT(AppConfigManager.getAccessToken(mContext));

			iRSPaymentModel.setDID(AppConfigManager.getDID(mContext));

			iRSPaymentModel.setUID(AppConfigManager.getLoggedUid(mContext));

			iRSPaymentModel.setRID(RId);

			GetIRSPaymentURL getIRSPaymentURL=new GetIRSPaymentURL(iRSPaymentModel.getIRSPaymentDetailRequest(), mContext);

			GetIRSPaymentInterface getIRSPaymentInterface=new GetIRSPaymentInterface()
			{
				@Override
				public void onResultSuccess(final IRSModel iRSPaymentModel) 
				{

					runOnUiThread(new Runnable() 
					{
						public void run() 
						{
							try
							{
								if(iRSPaymentModel.getOS().equalsIgnoreCase("Success"))
								{
									if(iRSPaymentModel.getPBALOPTION()!=null && !iRSPaymentModel.getPBALOPTION().equalsIgnoreCase("null"))
									{
										if(iRSPaymentModel.getTY()!=null && !iRSPaymentModel.getTY().equalsIgnoreCase("null"))
										{
											taxyear.setText(iRSPaymentModel.getTY());
											
											TaxPeriod.setText("Calendar Tax Year: "+iRSPaymentModel.getTY());
											
											String year=String.valueOf(Integer.parseInt(iRSPaymentModel.getTY())+1);
											
											if(isoutofcountry!=null && !isoutofcountry.equalsIgnoreCase("null") && isoutofcountry.equalsIgnoreCase("true"))
											{
												duedatetextview.setText("The due date for Out of country user is:");
												
												extendedduedatedata.setText("6/15/"+year);
											}
											else if(isoutofcountry!=null && !isoutofcountry.equalsIgnoreCase("null") && isoutofcountry.equalsIgnoreCase("false"))
											{
												duedatetextview.setText("The due date for regular user is:");
												
												extendedduedatedata.setText("4/15/"+year);
											}
										}
										
										if(iRSPaymentModel.getPBALOPTION().equalsIgnoreCase("0"))
										{
											nobaldue.setVisibility(View.VISIBLE);
											
											taxliabilitylayout.setVisibility(View.GONE);
											
											amttoirslayout.setVisibility(View.GONE);
											
											irspaylayout.setVisibility(View.GONE);
										}
										else if(iRSPaymentModel.getPBALOPTION().equalsIgnoreCase("1"))
										{
											nobaldue.setVisibility(View.GONE);
											
											taxliabilitylayout.setVisibility(View.VISIBLE);
											
											amttoirslayout.setVisibility(View.VISIBLE);
											
											irspaylayout.setVisibility(View.VISIBLE);
											
											if(iRSPaymentModel.getPTAXLIABILITY()!=null && !iRSPaymentModel.getPTAXLIABILITY().equalsIgnoreCase("null"))
											{
												totaltaxduedata.setText("$"+iRSPaymentModel.getPTAXLIABILITY()+".00");
											}
											
											if(iRSPaymentModel.getPPAYMENTTOIRS()!=null && !iRSPaymentModel.getPPAYMENTTOIRS().equalsIgnoreCase("null"))
											{
												amtyouwantoirsdata.setText("$"+iRSPaymentModel.getPPAYMENTTOIRS()+".00");
											}
											
											if(iRSPaymentModel.getPPAYMENTTOIRS()!=null && !iRSPaymentModel.getPPAYMENTTOIRS().equalsIgnoreCase("null"))
											{
												if(Integer.parseInt(iRSPaymentModel.getPPAYMENTTOIRS())>0)
												{
													irspaylayout.setVisibility(View.VISIBLE);
													
													if(iRSPaymentModel.getIRSPaymentOptionId()!=null && !iRSPaymentModel.getIRSPaymentOptionId().equalsIgnoreCase("null"))
													{
														if(iRSPaymentModel.getIRSPaymentOptionId().equalsIgnoreCase("0"))
														{
															IRspaymentdata.setText("Check or Money Order");	
														}
														else
														{
															IRspaymentdata.setText("EFW");	
														}
													}
												}
												else
												{
													irspaylayout.setVisibility(View.GONE);
													
												}
											}
										}
											
									}
								}
								else if(iRSPaymentModel.getOS().equalsIgnoreCase("Failure") && iRSPaymentModel.getEM().equalsIgnoreCase("Access Token is Invalid"))
								{
									Toast.makeText(getBaseContext(), "Your Session is Expired", Toast.LENGTH_LONG).show();

									com.span.expressextension8868.Utility.Logout.logout(mContext);
								}
							}
							catch (Exception e)
							{
								
								e.printStackTrace();
								new SendException(mContext, e);
							}

						}
					});

				}

			};

			getIRSPaymentURL.setOnResultListener(getIRSPaymentInterface);

			getIRSPaymentURL.execute();
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
			new SendException(mContext, e);
		}
	*/
    }


    private void GetPersonalInfoDetails() {/*

		try
		{
			pmodel.setAT(AppConfigManager.getAccessToken(mContext));
			
			pmodel.setDID(AppConfigManager.getDID(mContext));
			
			pmodel.setUID(AppConfigManager.getLoggedUid(mContext));
			
			pmodel.setPDId(AppConfigManager.getPD(mContext));
			
			GetPersonalDetailsById getpersonaldetails=new GetPersonalDetailsById(pmodel.GetPersonalInfoDetailsRequest(), mContext);
			
			PersonalInterfaceDetails personalInterfaceDetails=new PersonalInterfaceDetails()
			{
				
				@Override
				public void onResultSuccess(final PersonalModel pmodell)
				{
					getPersonaldetailmodel=pmodell;
					
					runOnUiThread(new Runnable()
					{
						public void run()
						{
							
							try
							{
								if (pmodell.getOS()!=null && !pmodell.getOS().equalsIgnoreCase("null") && pmodell.getOS().equalsIgnoreCase("Success"))
								{
									if (pmodell.getTPMiddleInitial() != null && !pmodell.getTPMiddleInitial().equalsIgnoreCase("null"))
									{
										businessnametextview.setText(pmodell.getTPFirstName() + " " + pmodell.getTPMiddleInitial() + " " + pmodell.getTPLastName() + ", " + pmodell.getTPSSN());
									}
									else
									{
										businessnametextview.setText(pmodell.getTPFirstName() + " " + pmodell.getTPLastName() + ", " + pmodell.getTPSSN());
									}
									if (pmodell.getPersonalInfoType() != null && !pmodell.getPersonalInfoType().equalsIgnoreCase("null") && pmodell.getPersonalInfoType().equalsIgnoreCase("2"))
									{
										ein.setVisibility(View.VISIBLE);

										if (pmodell.getSPMiddleInitial() != null && !pmodell.getSPMiddleInitial().equalsIgnoreCase("null"))
										{
											ein.setText(pmodell.getSPFirstName() + " " + pmodell.getSPMiddleInitial() + " " + pmodell.getSPLastName() + ", " + pmodell.getSPSSN());
										}
										else
										{
											ein.setText(pmodell.getSPFirstName() + " " + pmodell.getSPLastName() + ", " + pmodell.getSPSSN());
										}
									}
									else
									{
										ein.setVisibility(View.GONE);
									}
									if (pmodell.getIsOutOfCountry() != null && !pmodell.getIsOutOfCountry().equalsIgnoreCase("null"))
									{

										isoutofcountry = pmodell.getIsOutOfCountry();
									}
									if (!pmodell.getAddress1().equalsIgnoreCase("null"))
									{
										address1.setText(pmodell.getAddress1().trim() + ",");
									}
									else
									{
										address1.setText("");
									}
									if (!pmodell.getAddress2().equalsIgnoreCase("null"))
									{
										address2.setText(pmodell.getAddress2().trim());
									}
									else
									{
										address2.setText("");
									}
									if (!pmodell.getCity().equalsIgnoreCase("null"))
									{
										city.setText(pmodell.getCity().trim() + ",");

									}
									else
									{
										city.setText("");
									}
									if (Integer.parseInt(pmodell.getStateId()) > 0 && pmodell.getIsForeignAddress().equalsIgnoreCase("false"))
									{
										state.setText(databasehandler.getStatenameFromSID(pmodell.getStateId()));
									}
									else
									{
										state.setText(pmodell.getState() + ",");
									}
									if (pmodell.getIsForeignAddress().equalsIgnoreCase("true"))
									{
										if (!pmodell.getCountryId().equalsIgnoreCase("null"))
										{
											country.setText(databasehandler.getCountryNameFromCID(pmodell.getCountryId()) + " - ");
										}
									}
									else
									{
										country.setVisibility(View.GONE);
									}
									if (!pmodell.getZip().equalsIgnoreCase("null"))
									{
										zip.setText(pmodell.getZip());
									}
									if (pmodell.getRN() != null && !pmodell.getRN().equalsIgnoreCase("null"))
									{
										returnnumber.setText(pmodell.getRN());
									}
									if (pmodell.getFSID() != null && !pmodell.getFSID().equalsIgnoreCase("null"))
									{
										if (pmodell.getFSID().equalsIgnoreCase("0") || pmodell.getFSID().equalsIgnoreCase("1"))
										{
											returnstatus.setText("In Progress");

											statusindicator.setImageResource(R.drawable.yellow);
											
											taxdueedit.setImageResource(R.drawable.editbtnlarge);

											taxyearedit.setImageResource(R.drawable.editbtnlarge);

											continuewhereuleftof.setVisibility(View.VISIBLE);

											Delete.setVisibility(View.VISIBLE);

											pdfbtn.setVisibility(View.GONE);

											email.setVisibility(View.GONE);

											viewerrors.setVisibility(View.GONE);

										}
										else if (pmodell.getFSID().equalsIgnoreCase("2"))
										{
											returnstatus.setText("Transmitted to the IRS");

											statusindicator.setImageResource(R.drawable.blue);
											
											taxdueedit.setImageResource(R.drawable.viewbtnlarge);

											taxyearedit.setImageResource(R.drawable.viewbtnlarge);

											continuewhereuleftof.setVisibility(View.INVISIBLE);

											Delete.setVisibility(View.INVISIBLE);

											pdfbtn.setVisibility(View.VISIBLE);

											email.setVisibility(View.VISIBLE);

											viewerrors.setVisibility(View.GONE);
										}
										else if (pmodell.getFSID().equalsIgnoreCase("3"))
										{
											returnstatus.setText("Accepted By the IRS");

											statusindicator.setImageResource(R.drawable.green);
											
											taxdueedit.setImageResource(R.drawable.viewbtnlarge);

											taxyearedit.setImageResource(R.drawable.viewbtnlarge);

											continuewhereuleftof.setVisibility(View.INVISIBLE);

											Delete.setVisibility(View.INVISIBLE);

											pdfbtn.setVisibility(View.VISIBLE);

											email.setVisibility(View.VISIBLE);

											viewerrors.setVisibility(View.GONE);
										}
										else if (pmodell.getFSID().equalsIgnoreCase("4"))
										{
											returnstatus.setText("Rejected By the IRS");
											
											statusindicator.setImageResource(R.drawable.red);

											taxdueedit.setImageResource(R.drawable.editbtnlarge);

											taxyearedit.setImageResource(R.drawable.editbtnlarge);

											continuewhereuleftof.setVisibility(View.VISIBLE);

											Delete.setVisibility(View.INVISIBLE);

											pdfbtn.setVisibility(View.GONE);

											email.setVisibility(View.GONE);

											viewerrors.setVisibility(View.VISIBLE);
										}
									}
									if (pmodell.getUDTS() != null && !pmodell.getUDTS().equalsIgnoreCase("null"))
									{
										udt.setText(pmodell.getUDTS());
									}
								}
								else if(pmodell.getOS()!=null && !pmodell.getOS().equalsIgnoreCase("null") && pmodell.getOS().equalsIgnoreCase("Failure"))
								{
									if(pmodell.getEM()!=null && !pmodell.getEM().equalsIgnoreCase("null") && pmodell.getEM().equalsIgnoreCase("Access Token is invalid"))
									{
										Toast.makeText(getBaseContext(), "Your session is Expired", Toast.LENGTH_LONG).show();
										
										Logout.logout(mContext);
									}
									else if(pmodell.getEM()!=null && !pmodell.getEM().equalsIgnoreCase("null") && !pmodell.getEM().equalsIgnoreCase("Access Token is invalid"))
									{
										Toast.makeText(getBaseContext(), pmodell.getEM(), Toast.LENGTH_LONG).show();
									}
								}
							}
							catch (Exception e)
							{
								
								e.printStackTrace();
								new SendException(mContext, e);
							}
						}
					});
					
				}
			};
			
			getpersonaldetails.setOnResultListener(personalInterfaceDetails);
			
			getpersonaldetails.execute();
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
			new SendException(mContext, e);
		}
		
	*/
    }

    public void PDFViewer() {/*
        String jsonString = null;
		try
		{
			JSONObject obj = new JSONObject();

			JSONObject obj1 = new JSONObject();

			try
			{

				obj1 = new JSONObject();

				obj.put("RID", AppConfigManager.getReturnRID(mContext));

				obj.put("UId", AppConfigManager.getLoggedUid(mContext));

				obj.put("DId", AppConfigManager.getDID(mContext));

				obj.put("AT", AppConfigManager.getAccessToken(mContext));

				obj1.put("Return", obj);

				jsonString = obj1.toString();

			}
			catch (Exception e)
			{
				e.printStackTrace();
				
				new SendException(mContext, e);
			}
			
			Log.i("PDF REQUEST", jsonString);
		}
		catch (Exception e)
		{
			new SendException(mContext, e);
		}

			PdfURL pdfurl = new PdfURL(jsonString, mContext);

			PdfOnAsyncResultByCondition pdfasync = new PdfOnAsyncResultByCondition()
			{

				@Override
				public void onResultSuccess(String message)
				{

					String url = "http://docs.google.com/viewer?url=" + message;

					Intent web = new Intent(Intent.ACTION_VIEW);

					web.setData(Uri.parse(url));

					web.putExtra("URL", url);

					startActivity(web);

				}

			};
			pdfurl.setOnResultListener(pdfasync);

			pdfurl.execute();

	*/
    }

    private void Initialization(View viewToLoad) {

        try {

            TypeFaceClass typeface = new TypeFaceClass(mContext);

            DashBoardTitle = (TextView) viewToLoad.findViewById(R.id.DashBoardTitle);


            logolayout = (LinearLayout) viewToLoad.findViewById(R.id.logolayout);

            refresh = (ImageView) viewToLoad.findViewById(R.id.refresh);


            businesslistview = (ImageView) viewToLoad.findViewById(R.id.businesslistview);


            loginname = (TextView) findViewById(R.id.loginname);

            loginname.setTypeface(typeface.RobotoCondensed());

            loginname.setText("Hi " + AppConfigManager.getContactname(mContext) + "!");

            support = (TextView) findViewById(R.id.Support);

            support.setTypeface(typeface.RobotoCondensed());

            dashboardlink = (TextView) findViewById(R.id.Dashboardlink);

            dashboardlink.setTypeface(typeface.RobotoCondensed());

            dashboardlink.setEnabled(false);

            logout = (TextView) findViewById(R.id.Logout);

            logout.setTypeface(typeface.RobotoCondensed());


            cpaedit = (TextView) findViewById(R.id.cpaedit);

            cpaedit.setTypeface(typeface.RobotoCondensed());

            EmailAddressText = (TextView) findViewById(R.id.EmailAddressText);

            EmailAddressText.setText(AppConfigManager.getUserName(mContext));

            EmailAddressText.setTypeface(typeface.RobotoCondensed());

            listfilter = (EditText) findViewById(R.id.listfilter);


            businesslistlayout = (LinearLayout) findViewById(R.id.businesslistlayout);


            rootlayout = (FrameLayout) findViewById(R.id.rootlayout);

            menulayout = (RelativeLayout) findViewById(R.id.menulayout);

            menulayout.setClickable(true);

            myAccount_Layout = (LinearLayout) findViewById(R.id.myAccount_Layout);


            menu = (ImageView) viewToLoad.findViewById(R.id.menu);


            businesslist = (ListView) findViewById(R.id.businesslist);

            addnewbusinessdashboard = (Button) findViewById(R.id.addnewbusinessdashboard);


        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
    }


    public void GetBusinessList(final Context mcontext, final ListView lv) {/*

		try
		{
			PersonalModel personalModel = new PersonalModel();

			personalModel.setUID(AppConfigManager.getLoggedUid(mcontext));

			personalModel.setAT(AppConfigManager.getAccessToken(mcontext));

			personalModel.setDID(AppConfigManager.getDID(mcontext));

			PersonalInfoListURL managebusinessurl = new PersonalInfoListURL(personalModel.GetPersonalInfoListRequest(), mcontext);

			PersonalInfoListInterface onasync5 = new PersonalInfoListInterface()
			{

				@Override
				public void onResultSuccess(final PersonalModel message)
				{

					personalmodel=message;

					runOnUiThread(new Runnable()
					{

						@Override
						public void run()
						{
							try
							{
								if (message.getOS().equalsIgnoreCase("Success"))
								{
									if(message.getPersonalinfoList().size()>0)
									{
										try
										{

											listadapter = new CustomAdapter(mContext, message);

											lv.setAdapter(listadapter);

											listadapter.notifyDataSetChanged();

											clickOnListItem(selectionposition, lv);
										}
										catch (Exception e)
										{
											e.printStackTrace();

											new SendException(mContext, e);
										}
									}
									else if(message.getPersonalinfoList().size()==0)
									{
										try
										{
											listadapter = new CustomAdapter(mcontext, message);

											lv.setAdapter(listadapter);

											listadapter.notifyDataSetChanged();

											Intent addbusiness=new Intent(DashboardActivity.this,AddPersonalInfoActivity.class).putExtra("ID", "0");

											startActivity(addbusiness);

											finish();
										}
										catch (Exception e)
										{

											e.printStackTrace();

											new SendException(mContext, e);
										}
									}

								}
								else if (message.getOS()!=null && message.getOS().equalsIgnoreCase("Failure") && !message.getEM().equalsIgnoreCase("null") && message.getEM()!=null && message.getEM().equalsIgnoreCase("Access Token is invalid"))
								{
									Toast.makeText(getBaseContext(), "Your Session is Expired", Toast.LENGTH_SHORT).show();

									Logout.logout(mcontext);
								}
							}
							catch (Exception e)
							{
								e.printStackTrace();

								new SendException(mContext, e);
							}
						}
					});

				}

				@Override
				public void onResultFail(int resultCode, String errorMessage)
				{

				}
			};
			managebusinessurl.setOnResultListener(onasync5);

			managebusinessurl.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			new SendException(mContext, e);
		}
	*/
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    private void Slidingdrawerfunc() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // set a custom shadow that overlays the main content when the drawer
        // opens
        //   mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);

    }

    @Override
    public void onBackPressed() {

    }

    public void getBusineesList() {

        BusinessListRequestModel = new AddBussinessInputModel();


        BusinessListRequestModel.setAT(AppConfigManager.getAccessToken(mContext));

        BusinessListRequestModel.setDID(AppConfigManager.getDID(mContext));

        BusinessListRequestModel.setUId(AppConfigManager.getLoggedUid(mContext));


        GetBusinessListURL getBusinessListURL = new GetBusinessListURL(BusinessListRequestModel.getJsonObjBusinessLookUP(), mContext);

        getBusinessListURL.setOnResultListener(onAsyncResultGetBusinessListURL);

        getBusinessListURL.execute();


    }

    GetBusinessListURL.OnAsyncResultGetBusinessListURL onAsyncResultGetBusinessListURL = new GetBusinessListURL.OnAsyncResultGetBusinessListURL() {

        @Override
        public void onResultSuccess(Vector<AddBussinessInputModel> Result) {

            if (Result != null && Result.size() > 0) {

                businessListVector = Result;


            }

            //   businesslist = Result;

            businessHandler.post(businessListRubbable);
        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    Handler businessHandler = new Handler();

    Runnable businessListRubbable = new Runnable() {
        @Override
        public void run() {

            if (businessListVector != null && businessListVector.size() > 0) {

                adapter = new BusinessListAdapter(mContext, businessListVector, R.layout.business_list);

                businesslist.setAdapter(adapter);

                adapter.dataSet(listPosition);

                adapter.notifyDataSetChanged();

                //     clickOnListItem(selectionposition, businesslist);

                AppConfigManager.saveBusinessNameEIN(mContext, businessListVector.get(listPosition).getOrganizationName(), businessListVector.get(listPosition).getEin());

                AppConfigManager.saveBID(mContext, businessListVector.get(listPosition).getBId());

                getBusineesListDetails(businessListVector.get(listPosition).getBId());

                Log.i("dashboard", "bid : " + AppConfigManager.getBID(mContext));

            }
        }
    };


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


            returnListModel = Result;


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

            Fragment newFragment = new DashboardFragment(mContext, businessListVector.get(listPosition), returnListModel, mDrawerLayout, businesslist, listPosition, test);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed


            transaction.replace(R.id.wholevertical, newFragment);

            transaction.addToBackStack(FragmentNameConfig.DASHBOARD_FRAGMENT);

            try {
                transaction.commit();

            } catch (IllegalStateException e) {


            }
// Commit the transaction


            //// TODO: 11/11/2015


        }
    };

    private void pageRedirection(String to) {

        if (to != null && to.equalsIgnoreCase("Dashboard")) {

            listClick = true;

            fromDashboard = true;

            dashBoardFragment();

        } else if (to != null && to.equalsIgnoreCase("AddOrgDetail")) {

            listClick = false;

            fromDashboard = false;

            addOrgDetailsFragment("0");

        } else {

            dashBoardFragment();
        }


    }


    private void dashBoardFragment() {

        DashBoardTitle.setText("DASHBOARD");

        refresh.setVisibility(View.VISIBLE);

        businesslistview.setVisibility(View.VISIBLE);

        getBusineesList();


    }

    private void addOrgDetailsFragment(String id) {

        listClick = false;


        mDrawerLayout.closeDrawer(businesslistlayout);

        refresh.setVisibility(View.GONE);

        businesslistview.setVisibility(View.GONE);

        DashBoardTitle.setText("ADD EXEMPT ORGANIZATION DETAILS");

        Fragment newFragment = new AddExemptOrganization(mContext, fromDashboard, false);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.wholevertical, newFragment);

        transaction.addToBackStack(FragmentNameConfig.ORG_DETAIL_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }

    private void TaxPageFragment(String id) {


        mDrawerLayout.closeDrawer(businesslistlayout);

        refresh.setVisibility(View.GONE);

        businesslistview.setVisibility(View.GONE);

        DashBoardTitle.setText("ADD EXEMPT ORGANIZATION DETAILS");

        AppConfigManager.saveFlag(mContext, 0);

        Fragment newFragment = new CommonTaxFragment(mContext);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.wholevertical, newFragment);

        transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);


// Commit the transaction
        transaction.commit();

    }

    @Override
    public void setOnBusinessClick(int position) {

        listPosition = position;

        getBusineesList();

    }


}

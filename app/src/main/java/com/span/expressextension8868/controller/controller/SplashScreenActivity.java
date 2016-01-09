package com.span.expressextension8868.controller.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.span.expressextension8868.R;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AndroidDeviceInfo;
import com.span.expressextension8868.utils.utility.MyApplication;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.NetworkChangeReceiver;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.CountryStateAndFormOrganizationURL;
import com.span.expressextension8868.webservice.webservices.CountryStateAndFormOrganizationURL.StaticDataInterface;
import com.span.expressextension8868.webservice.webservices.GetLatestVersion;
import com.span.expressextension8868.webservice.webservices.GetLatestVersion.GetlatestVersionInterface;
import com.span.expressextension8868.webservice.webservices.SelectForm8868StaticURL;
import com.span.expressextension8868.webservice.webservices.SelectForm8868StaticURL.StaticDataInterface8868;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

public class SplashScreenActivity extends Activity
{

	Context mContext = SplashScreenActivity.this;

	// Splash screen timer
	AndroidDeviceInfo androidDeviceInfo;

	private static int SPLASH_TIME_OUT = 3000;

	public static boolean isInternetPresent;

	JSONObject latestVersionResponse;

	int ServiceVersion;

	ProgressDialog progress;

	AlertDialog.Builder alert;

	String mApplicationVersion;

	public ProgressDialog progressBar;

	int ver;

	NetworkChangeReceiver receiver;

	public DatabaseHandler databasehandler;

	String DBNAME = "ExpressExtension.db";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setBackgroundDrawable(null);

		setContentView(R.layout.activity_splash_screen);

		if (Utils.isTablet(mContext))
		{

			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

			setContentView(R.layout.activity_splash_screen);
		}
		else
		{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

			setContentView(R.layout.activity_splash_screen);
		}

		try
		{

			receiver = new NetworkChangeReceiver();

			this.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

			progressBar = MyCustomProgressDialog.ctor(mContext);

			Log.e("INTERNET", " internet1");

			androidDeviceInfo = new AndroidDeviceInfo(mContext);

			androidDeviceInfo.addDeviceInfo(AndroidDeviceInfo.DEVICE_NAME, "ANDROID");

			androidDeviceInfo.addDeviceInfo(AndroidDeviceInfo.DEVICE_MODEL, Build.MODEL);

			androidDeviceInfo.addDeviceInfo(AndroidDeviceInfo.DEVICE_OSVERSION, Build.VERSION.RELEASE);

			androidDeviceInfo.addDeviceInfo(AndroidDeviceInfo.DEVICE_SDKVERSION, String.valueOf(Build.VERSION.SDK_INT));

			androidDeviceInfo.addDeviceInfo(AndroidDeviceInfo.DEVICE_MANUFACTURER, Build.MANUFACTURER);

			androidDeviceInfo.addDeviceInfo(AndroidDeviceInfo.DEVICE_ID, Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID));

			Log.e("start", "spalsh");

			androidDeviceInfo.saveAndroidDeviceInfo();

			Showprogress();

			new Handler().postDelayed(new Runnable()
			{

				@Override
				public void run()
				{

					Internetconnection();
				}
			}, SPLASH_TIME_OUT);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			new SendException(mContext, e);
		}

		// Get a Tracker (should auto-report)
		((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);

		// Insert inputs from static data to Db

		databasehandler = new DatabaseHandler(mContext);
		
		databasehandler.getWritableDatabase();

	}

	@Override
	protected void onPause()
	{
		super.onPause();

	}

	@Override
	protected void onStart()
	{
		super.onStart();
		// Get an Analytics tracker to report app starts & uncaught exceptions
		// etc.
		GoogleAnalytics.getInstance(this).reportActivityStart(this);
	}

	@Override
	protected void onStop()
	{
		super.onStop();

		// Stop the analytics tracking
		GoogleAnalytics.getInstance(this).reportActivityStop(this);

	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();

		try
		{

			if (progressBar.isShowing())
			{
				progressBar.dismiss();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();

			new SendException(mContext, e);
		}

		unregisterReceiver(receiver);
	}

	public void Internetconnection()
	{

		try
		{
			GetLatestVersion getLatestVersion = new GetLatestVersion(mContext);

			GetlatestVersionInterface getlatestVersionInterface = new GetlatestVersionInterface()
			{

				@Override
				public void onResultSuccess(int ver, int ServiceVersion)
				{
					if (ver < ServiceVersion)
					{
						if (progress != null)
						{
							if (progress.isShowing())
							{

								progress.dismiss();
							}
						}

						showAlert();
					}

					else
					{
						if (progress != null)
						{
							if (progress.isShowing())
							{
								progress.dismiss();
							}
						}

						SelectForm8868StaticURL selectForm8868StaticURL = new SelectForm8868StaticURL(mContext);

						StaticDataInterface8868 staticDataInterface8868 = new StaticDataInterface8868()
						{

							@Override
							public void onResultSuccess(final Vector<ArrayList<String>> GetFormTypestaticVector)
							{

								runOnUiThread(new Runnable()
								{
									public void run()
									{
										
										databasehandler.InsertFormsToDB(GetFormTypestaticVector);
										
										CountryStateAndFormOrganizationURL countryStateAndFormOrganizationURL = new CountryStateAndFormOrganizationURL(mContext);

										StaticDataInterface staticDataInterface = new StaticDataInterface()
										{

											@Override
											public void onResultSuccess(Vector<Vector<String>> returnobject)
											{

												databasehandler.InsertToDb(returnobject);

												loginhandler.post(runLoginPage);
											}
										};
										countryStateAndFormOrganizationURL.setOnResultListener(staticDataInterface);

										countryStateAndFormOrganizationURL.execute();
										
									}
								});
								
							}

						};

						selectForm8868StaticURL.setOnResultListener(staticDataInterface8868);

						selectForm8868StaticURL.execute();

					}

				}
			};

			getLatestVersion.setOnResultListener(getlatestVersionInterface);

			getLatestVersion.execute();
		}
		catch (Exception e)
		{

			e.printStackTrace();

			new SendException(mContext, e);
		}

	}

	Handler loginhandler = new Handler();

	Runnable runLoginPage = new Runnable()
	{

		@Override
		public void run()
		{

			Intent login = new Intent(mContext, LoginActivity.class);

			mContext.startActivity(login);

			((Activity) mContext).finish();
		}
	};

	public void showAlert()
	{

		alert = new AlertDialog.Builder(mContext);

		alert.create();

		alert.setTitle("Update Available");

		alert.setIcon(mContext.getResources().getDrawable(R.drawable.update));

		alert.setMessage("To access our new features download the latest version of our app");

		alert.setPositiveButton("Go To Market", new OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{

				try
				{
					dialog.dismiss();

					Intent intent = new Intent(Intent.ACTION_VIEW);

					intent.setData(Uri.parse("market://details?id=com.span.expressextension4868"));

					mContext.startActivity(intent);

					((Activity) mContext).finish();
				}
				catch (Exception e)
				{
					e.printStackTrace();

					new SendException(mContext, e);
				}

			}

		});
		alert.setOnCancelListener(new OnCancelListener()
		{

			@Override
			public void onCancel(DialogInterface dialog)
			{

				((Activity) mContext).finish();

			}
		});

		alert.show();

	}

	@SuppressWarnings("deprecation")
	public void Connection_error()
	{

		try
		{

			AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();

			alertDialog.setTitle("Internet connection");

			alertDialog.setMessage("No Internet");

			alertDialog.setButton("OK", new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					progressBar.dismiss();

					finish();
				}
			});

			alertDialog.show();

		}
		catch (Exception e)
		{
			new SendException(mContext, e);
		}

	}

	void Showprogress()
	{

		try
		{

			progressBar.getWindow().setGravity(Gravity.BOTTOM);

			progressBar.show();

		}
		catch (Exception e)
		{
			e.printStackTrace();

			new SendException(mContext, e);
		}

	}

}

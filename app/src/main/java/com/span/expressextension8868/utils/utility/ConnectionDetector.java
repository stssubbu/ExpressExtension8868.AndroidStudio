package com.span.expressextension8868.utils.utility;



import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class ConnectionDetector
{

	private final Context mContext;

	private View alertView;

	private AlertDialog myDialog;

	TextView timernumber, timertext;

	ConnectivityManager connectivity = null;

	public ConnectionDetector(Context context)
	{

		mContext = context;

		String className = mContext.getClass().getSimpleName();

		Log.i("className", "className" + className);

		Log.i("className", "className" + className.toString());
	}

	/** Checking for all possible internet providers **/
	public boolean isConnectingToInternet()
	{

		try
		{

			if (connectivity == null)
			{
				connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
			}
			if (connectivity != null)
			{

				NetworkInfo[] info = connectivity.getAllNetworkInfo();

				if (info != null)
				{
					for (int i = 0; i < info.length; i++)
					{
						if (info[i].getState() == NetworkInfo.State.CONNECTED)
						{

							return true;

						}
					}
				}
			}

			try
			{/*

				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

				LayoutInflater inflater = LayoutInflater.from(mContext);

				alertView = inflater.inflate(R.layout.connectionerrorlayout, null);

				// myDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

				builder.setView(alertView);

				Button ok = (Button) alertView.findViewById(R.id.msgok);

				Button settingsbtn = (Button) alertView.findViewById(R.id.settingsbtn);

				settingsbtn.setOnClickListener(new OnClickListener()
				{

					@Override
					public void onClick(View v)
					{
						Intent intent = new Intent(Settings.ACTION_SETTINGS);
						mContext.startActivity(intent);
					}
				});

				ok.setOnClickListener(new OnClickListener()
				{

					@Override
					public void onClick(View v)
					{

						if (mContext.getClass().getSimpleName().equalsIgnoreCase("SplashScreenActivity"))
						{
							if (myDialog.isShowing())
							{
								myDialog.dismiss();
							}
							((Activity) mContext).finish();
						}
						else if (mContext.getClass().getSimpleName().equalsIgnoreCase("LoginActivity"))
						{
							if (myDialog.isShowing())
							{
								myDialog.dismiss();
							}
							((Activity) mContext).finish();

						}
						else
						{
							if (myDialog.isShowing())
							{
								myDialog.dismiss();
							}
							Logout.logout(mContext);
						}
					}
				});

				builder.setCancelable(false);

				myDialog = builder.create();

				myDialog.show();
			*/}
			catch (Exception e)
			{

			}

			return false;

		}
		catch (Exception e)
		{

			e.printStackTrace();

			return false;
		}
	}

	// public void updateUI()
	// {
	// String numberString[] = timernumber.getText().toString().split(" ");
	//
	// int currentInt = Integer.parseInt(numberString[0]) - 1;
	//
	// if (currentInt >= 0)
	// {
	// mRedrawHandler.sleep(1000);
	//
	// String current = String.valueOf(currentInt);
	//
	// timernumber.setText(current + " Sec");
	//
	// if
	// (mContext.getClass().getSimpleName().equalsIgnoreCase("SplashScreenActivity"))
	// {
	// timertext.setText("Application will be Closed in");
	// }
	// else if
	// (mContext.getClass().getSimpleName().equalsIgnoreCase("LoginActivity"))
	// {
	// timertext.setText("Application will be Closed in");
	// }
	// else
	// {
	// timertext.setText("Application will be Logged out in");
	// }
	// }
	// else if
	// (mContext.getClass().getSimpleName().equalsIgnoreCase("SplashScreenActivity"))
	// {
	// timertext.setText("Application will be Closed in");
	// ((Activity) mContext).finish();
	// android.os.Process.killProcess(android.os.Process.myPid());
	// }
	// else
	// {
	// timertext.setText("Application will be Logged out in");
	// Logout.logout(mContext);
	// }
	// }
}

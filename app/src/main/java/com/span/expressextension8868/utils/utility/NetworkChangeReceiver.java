package com.span.expressextension8868.utils.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.span.expressextension8868.R;


public class NetworkChangeReceiver extends BroadcastReceiver
{

	Context mContext;
	
	private View alertView;

	private AlertDialog myDialog;

	@Override
	public void onReceive(final Context context, final Intent intent) 
	{

		mContext=context;
		
		NetworkUtil networkUtil = new NetworkUtil();

		Boolean status = networkUtil.getConnectivityStatusString(context);
		
		Log.e("status", "status "+status);
		
		if (!status) 
		{
			try 
			{
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
						else if (mContext.getClass().getSimpleName().equalsIgnoreCase("LoginActivityMobile"))
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
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		

		}
	}

}

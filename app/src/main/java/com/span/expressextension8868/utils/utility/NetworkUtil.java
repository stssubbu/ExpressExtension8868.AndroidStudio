package com.span.expressextension8868.utils.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

	 public static int TYPE_WIFI = 1;
	    public static int TYPE_MOBILE = 2;
	    public static int TYPE_NOT_CONNECTED = 0;
	     
	     
	    public static int getConnectivityStatus(Context context) {
	    	
	        ConnectivityManager cm = (ConnectivityManager) context
	                .getSystemService(Context.CONNECTIVITY_SERVICE);
	 
	        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
	        
	        if (null != activeNetwork) {
	        	
	            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
	            	
	                return TYPE_WIFI;
	             
	            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
	            	
	                return TYPE_MOBILE;
	            
	        } 
	        
	        return TYPE_NOT_CONNECTED;
	    }
	     
	    public Boolean getConnectivityStatusString(Context context) {
	    	
	        int conn = NetworkUtil.getConnectivityStatus(context);
	        
	        Boolean status = false;
	        
	        if (conn == NetworkUtil.TYPE_WIFI) {
	        	
	            status = true;
	            
	        } else if (conn == NetworkUtil.TYPE_MOBILE) {
	        	
	            status = true;
	            
	        } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
	        	
	            status = false;
	            
	        }
	        
	        return status;
	    }
	}
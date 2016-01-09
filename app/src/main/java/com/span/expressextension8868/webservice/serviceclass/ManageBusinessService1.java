package com.span.expressextension8868.webservice.serviceclass;

import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.span.expressextension8868.businesslogic.parsing.ManageBusinessParsing1;
import com.span.expressextension8868.model.core.ManageBusinessModel1;
import com.span.expressextension8868.utils.utility.SendException;


public class ManageBusinessService1 {

    String jsonString, osField;

    Context mContext;

    Vector<Vector<ManageBusinessModel1>> returnobject = new Vector<Vector<ManageBusinessModel1>>();

    public Vector<Vector<ManageBusinessModel1>> getbusinessdata(String datainput, String URL, Context mContext) {

        try {
            this.mContext = mContext;
            HttpClient httpclient = new DefaultHttpClient();
            HttpContext httpContext = new BasicHttpContext();
            HttpPost httppost = new HttpPost(URL);
            StringEntity se = new StringEntity(datainput);

            httppost.setEntity(se);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-type", "application/json");

            HttpResponse response = httpclient.execute(httppost, httpContext);
            HttpEntity entity = response.getEntity();

            jsonString = EntityUtils.toString(entity); // if response in JSON
            // format

            ManageBusinessParsing1 managebusinessparsing = new ManageBusinessParsing1();

            returnobject = managebusinessparsing.parse(jsonString);

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return returnobject;
    }

}

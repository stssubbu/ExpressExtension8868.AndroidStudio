package com.span.expressextension8868.webservice.serviceclass;

import android.content.Context;

import com.span.expressextension8868.businesslogic.parsing.AddBusinessGetLookUpParse;
import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.utils.utility.SendException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.util.Vector;


public class GetBusinessLookUpService {

    String jsonString, osField;

    String OS;

    Context mContext;

    Vector<AddBussinessInputModel> returnobject = new Vector<AddBussinessInputModel>();

    public Vector<AddBussinessInputModel> getbusinessdata(String datainput, String URL, Context mContext) {

        try {

            System.out.println("Business Look up URL " + URL);

            System.out.println("Business Look up i/p " + datainput);
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

            jsonString = EntityUtils.toString(entity);


            System.out.println("JSONSTRING " + jsonString);

            AddBusinessGetLookUpParse addbusinessgetlookupparse = new AddBusinessGetLookUpParse();

            returnobject = addbusinessgetlookupparse.parse(jsonString);

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return returnobject;
    }

}

package com.span.expressextension8868.webservice.webservices;

import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.businesslogic.parsing.AddBusinessGetLookUpParse;
import com.span.expressextension8868.businesslogic.parsing.AddressParse;
import com.span.expressextension8868.businesslogic.parsing.AuditParsing;
import com.span.expressextension8868.businesslogic.parsing.BookInCareOfParse;
import com.span.expressextension8868.businesslogic.parsing.BusinessDetailReturnListDetailParsing;
import com.span.expressextension8868.businesslogic.parsing.BusinessListParse;
import com.span.expressextension8868.businesslogic.parsing.Deleteparsing;
import com.span.expressextension8868.businesslogic.parsing.Emailparsing;
import com.span.expressextension8868.businesslogic.parsing.ExemptOrgParsing;
import com.span.expressextension8868.businesslogic.parsing.IRSRetunParsing;
import com.span.expressextension8868.businesslogic.parsing.OrderDetailParsing;
import com.span.expressextension8868.businesslogic.parsing.Pdfparsing;
import com.span.expressextension8868.businesslogic.parsing.ReturnListParse;
import com.span.expressextension8868.businesslogic.parsing.SummaryModelParsing;
import com.span.expressextension8868.businesslogic.parsing.TransmitParse;
import com.span.expressextension8868.businesslogic.parsing.AllowReturnByIdParse;
import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.AuditModel;
import com.span.expressextension8868.model.core.BookIncareOfModel;
import com.span.expressextension8868.model.core.ExemptModel;
import com.span.expressextension8868.model.core.IRSReturnModel;
import com.span.expressextension8868.model.core.OrderDetailModel;
import com.span.expressextension8868.model.core.ReturnListModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.model.core.SummaryModel;
import com.span.expressextension8868.model.core.Transmitmodel;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by STS-099 on 11/6/2015.
 */
public class CommonWebserviceMethods {

    Context mContext;

    String URL, jsonString, OS;

    Vector<AddBussinessInputModel> returnobject = new Vector<AddBussinessInputModel>();

    AddBussinessInputModel addBussinessInputModel;

    ReturnModel returnListModel;

    IRSReturnModel balanceDueModel;

    BookIncareOfModel bookIncareOfModel;

    SummaryModel getSummaryDetailModel;

    AuditModel getAuditDetailsModel;

    OrderDetailModel getOrderDetailsModel;

    Transmitmodel transmitmodelbject;


    public ExemptModel GetBusinessFromLookup(Context mContext, String UID, String AT, String Did, String Ein) {

        String jsonResponse = null;


        try {
            // added


            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(ApplicationConfig.GETBUSINESSFROMLOOKUP);

            httpPost.setHeader("Content-Type", "application/json");

            httpPost.setHeader("Accepet", "application/json");

            JSONObject jsonRequest = new JSONObject();

            jsonRequest.put("Uid", UID);

            jsonRequest.put("DId", Did);

            jsonRequest.put("AT", AT);

            // added, after need to chnage params SessionUserId to UserId

            jsonRequest.put("Ein", Ein);

            Log.i("Detail page ", " Request : " + jsonRequest.toString());

            httpPost.setEntity(new ByteArrayEntity(jsonRequest.toString().getBytes("UTF8")));

            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);

            jsonResponse = EntityUtils.toString(httpResponse.getEntity());

            ExemptModel exemptModel = new ExemptModel();

            ExemptOrgParsing exemptOrgParsing = new ExemptOrgParsing();

            exemptModel = exemptOrgParsing.parse(jsonResponse);

            return exemptModel;

        } catch (Exception e) {

            new SendException(mContext, e);
        }
        return null;


    }

    public Vector<AddBussinessInputModel> getbusinessList(String datainput, String URL, Context mContext) {

        returnobject = new Vector<AddBussinessInputModel>();

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


            BusinessListParse businessListParse = new BusinessListParse();

            returnobject = businessListParse.parse(EntityUtils.toString(entity));


        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return returnobject;
    }

    public Vector<AddBussinessInputModel> getbusinessListDetail(String datainput, String URL, Context mContext) {

        addBussinessInputModel = new AddBussinessInputModel();

        try {

            System.out.println("Business Return Detail URL " + URL);
            System.out.println("Business  Return Detail i/p " + datainput);

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


            AddBusinessGetLookUpParse addbusinessgetlookupparse = new AddBusinessGetLookUpParse();


            returnobject = addbusinessgetlookupparse.parse(EntityUtils.toString(entity));


        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return returnobject;
    }

    public ReturnModel getBusinessReturnListDetail(String datainput, String URL, Context mContext) {

        returnListModel = new ReturnModel();

        try {

            System.out.println("Business Return Detail URL " + URL);
            System.out.println("Business  Return Detail i/p " + datainput);

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


            BusinessDetailReturnListDetailParsing businessDetailReturnListDetailParsing = new BusinessDetailReturnListDetailParsing();

            returnListModel = businessDetailReturnListDetailParsing.parse(EntityUtils.toString(entity));

        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return returnListModel;
    }


    public IRSReturnModel getSaveIRSPaymentDetailById(String datainput, String URL, Context mContext) {

        balanceDueModel = new IRSReturnModel();

        try {

            System.out.println("IRS Return Detail URL " + URL);
            System.out.println("IRS  Return Detail i/p " + datainput);

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


            IRSRetunParsing iRSRetunParsing = new IRSRetunParsing();

            balanceDueModel = iRSRetunParsing.balanceReturnDueParsing(mContext, EntityUtils.toString(entity));

            return balanceDueModel;

        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return null;
    }

    public BookIncareOfModel getBookInCareOfByReturnId(String datainput, String URL, Context mContext) {

        bookIncareOfModel = new BookIncareOfModel();

        try {

            System.out.println("IRS Return Detail URL " + URL);
            System.out.println("IRS  Return Detail i/p " + datainput);

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


            BookInCareOfParse parse = new BookInCareOfParse();

            bookIncareOfModel = parse.parse(mContext, EntityUtils.toString(entity));

            return bookIncareOfModel;

        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return null;
    }

    public SummaryModel getSummaryReturnDetailsByReturnId(String datainput, String URL, Context mContext) {

        getSummaryDetailModel = new SummaryModel();

        try {

            System.out.println("IRS Return Detail URL " + URL);
            System.out.println("IRS  Return Detail i/p " + datainput);

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


            SummaryModelParsing parse = new SummaryModelParsing();

            getSummaryDetailModel = parse.summaryParse(mContext, EntityUtils.toString(entity));

            return getSummaryDetailModel;

        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return null;
    }

    public AuditModel auditForm8868(String datainput, String URL, Context mContext) {

        getAuditDetailsModel = new AuditModel();

        try {

            System.out.println("IRS Return Detail URL " + URL);
            System.out.println("IRS  Return Detail i/p " + datainput);

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


            AuditParsing parse = new AuditParsing();

            getAuditDetailsModel = parse.parsing(mContext, EntityUtils.toString(entity));

            return getAuditDetailsModel;

        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return null;
    }

    public OrderDetailModel getOrderDetailsByReturnId(String datainput, String URL, Context mContext) {

        getOrderDetailsModel = new OrderDetailModel();

        try {

            System.out.println("Order Detail URL " + URL);
            System.out.println("Order Detail i/p " + datainput);

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


            OrderDetailParsing parse = new OrderDetailParsing();

            getOrderDetailsModel = parse.orderParse(mContext, EntityUtils.toString(entity));

            return getOrderDetailsModel;

        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return null;
    }


    public Transmitmodel getTransmitData(String datainput, String URL, Context mContext) {

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

            String jsonString = EntityUtils.toString(entity); // if response in JSON
            // format

            Log.i("TimeZone Response", jsonString);

            TransmitParse transmitParse = new TransmitParse();

            transmitmodelbject = transmitParse.parse(jsonString);

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return transmitmodelbject;
    }


    public String getpdfpath(String datainput, String URL, Context mContext) {

        try {

            this.mContext = mContext;
            HttpClient httpclient = new DefaultHttpClient();
            HttpContext httpContext = new BasicHttpContext();
            HttpPost httppost = new HttpPost(URL);
            StringEntity se = new StringEntity(datainput);

            Log.i("Pdf", "URL : " + URL);

            Log.i("Pdf", "Request : " + datainput);

            httppost.setEntity(se);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-type", "application/json");

            HttpResponse response = httpclient.execute(httppost, httpContext);
            HttpEntity entity = response.getEntity();

            jsonString = EntityUtils.toString(entity); // if response in JSON
            // format

            Pdfparsing pdfparsing = new Pdfparsing();

            Log.i("Pdf", "Response : " + pdfparsing);

            URL = pdfparsing.parse(jsonString);

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return URL;
    }


    public String getEmailOS(String datainput, String URL, Context mContext) {

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

            System.out.println("URL Email " + URL);

            System.out.println("JSONSTRING Email " + jsonString);

            Emailparsing emailparsing = new Emailparsing();

            OS = emailparsing.parse(jsonString);

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return OS;
    }


    Vector<ReturnListModel> returnListobject = new Vector<ReturnListModel>();

    public Vector<ReturnListModel> getreturnlist(String datainput, String URL, Context mContext) {

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

            jsonString = EntityUtils.toString(entity);

            ReturnListParse returnListParse = new ReturnListParse();

            returnListobject = returnListParse.parse(jsonString);

            System.out.println("JSONSTRING Response Return List" + jsonString);

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return returnListobject;
    }

    ArrayList<String> osField;

    public ArrayList<String> getosfieldvalue(String datainput, String URL,
                                             Context mContext) {

        try {
            this.mContext = mContext;
            HttpClient httpclient = new DefaultHttpClient();
            HttpContext httpContext = new BasicHttpContext();
            HttpPost httppost = new HttpPost(URL);
            StringEntity se = new StringEntity(datainput);

            httppost.setEntity(se);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-type", "application/json");

            HttpResponse response = httpclient.execute(httppost, httpContext); // execute
            // your
            // request
            // and
            // parse
            // response
            HttpEntity entity = response.getEntity();

            jsonString = EntityUtils.toString(entity); // if response in JSON
            // format

            Deleteparsing deleteparsing = new Deleteparsing();

            Log.i("Delete", "URL : " + URL);

            Log.i("Delete", "Request : " + datainput);

            Log.i("Delete", "Response : " + jsonString);

            osField = deleteparsing.parse(jsonString);

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return osField;
    }


    public ReturnModel allowReturnById(String datainput, String URL, Context mContext) {

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

            jsonString = EntityUtils.toString(entity);

            AllowReturnByIdParse returnListParse = new AllowReturnByIdParse();

            returnListModel = returnListParse.parse(jsonString);

            System.out.println("JSONSTRING Response Return List" + jsonString);

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return returnListModel;
    }


    public AddBussinessInputModel GetBusinessAddressDetailById(String datainput, String URL, Context mContext) {

        AddBussinessInputModel addbussinessinputmodel = null;

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

            jsonString = EntityUtils.toString(entity);

            AddressParse returnListParse = new AddressParse();

            System.out.println("JSONSTRING Response Address url" + URL);
            System.out.println("JSONSTRING Response Address request" + datainput);

            System.out.println("JSONSTRING Response Address List" + jsonString);

            addbussinessinputmodel = returnListParse.parse(jsonString);


        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return addbussinessinputmodel;
    }

}

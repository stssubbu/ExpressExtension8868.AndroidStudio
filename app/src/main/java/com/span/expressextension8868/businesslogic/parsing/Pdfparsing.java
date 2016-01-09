package com.span.expressextension8868.businesslogic.parsing;

import org.json.JSONObject;

public class Pdfparsing {
    String URL, sendURL, EM;

    public String parse(String jsstring) {
        if (jsstring != null) {
            try {
                System.out.println("PDF JSON STRING " + jsstring);

                JSONObject jsonObj = new JSONObject(jsstring);

                URL = jsonObj.getString("Path");

                EM = jsonObj.getString("EM");

                System.out.println("URL RECEIVED " + URL);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return URL;

    }
}

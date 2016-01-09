package com.span.expressextension8868.businesslogic.parsing;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.content.Context;

import com.span.expressextension8868.utils.utility.SendException;

public class XMLParser {

	// constructor
	public XMLParser() {

	}

	/**
	 * Getting XML from URL making HTTP request
	 * 
	 * @param url
	 *            string
	 * */
	public String getXmlFromUrl(Context mContext, String strUrl) {

		String data = "";
		try {

			try {
				// defaultHttpClient
				DefaultHttpClient httpClient = new DefaultHttpClient();

				HttpGet httpGet = new HttpGet(strUrl);

				HttpResponse httpResponse = httpClient.execute(httpGet);

				HttpEntity httpEntity = httpResponse.getEntity();

				data = EntityUtils.toString(httpEntity);

			} catch (Exception e) {

				e.printStackTrace();
			}
			// return XML

			return data;

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			

		}

		return data;
	}
	/**
	 * Getting XML DOM element
	 * 
	 * @param XML
	 *            string
	 * */
	public Document getDomElement(Context context, String xml)
	{
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			doc = db.parse(is);

		} catch (ParserConfigurationException e) {
			new SendException(context, e);
			return null;
		} catch (SAXException e) {
			new SendException(context, e);
			return null;
		} catch (IOException e) {
			new SendException(context, e);
			return null;
		}

		return doc;
	}

	/**
	 * Getting node value
	 * 
	 * @param elem
	 *            element
	 */
	public final String getElementValue(Context context, Node elem) {
		Node child;
		try {
			if (elem != null) {
				if (elem.hasChildNodes()) {
					for (child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
						if (child.getNodeType() == Node.TEXT_NODE) {
							return child.getNodeValue();
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception

			new SendException(context, e);
		}
		return "";
	}

	/**
	 * Getting node value
	 * 
	 * @param Element
	 *            node
	 * @param key
	 *            string
	 * */
	public String getValue(Context context, Element item, String str) {
		NodeList n = item.getElementsByTagName(str);
		return this.getElementValue(context, n.item(0));
	}
}

package com.matrix.smartclean;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SmartCleanRestClient {
	 private static final String BASE_URL = "http://smart-clean.herokuapp.com/api/";

	  private static AsyncHttpClient client = null;

	  public static AsyncHttpClient getInstance() {
	    if (client == null) {
	      client = new AsyncHttpClient();
	      client.addHeader("Content-Type", "application/json");
	      client.addHeader("Accept",
	          "application/vnd.smart-clean+json;version=1");
	    }
	    return client;
	  }

	  public static void get(String url, AsyncHttpResponseHandler responseHandler) {
	    getInstance().get(getAbsoluteUrl(url), new RequestParams(), responseHandler);
	  }

	  private static String getAbsoluteUrl(String relativeUrl) {
	    return BASE_URL + relativeUrl;
	  }
	}


package com.matrix.smartclean;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ProductDetailsMainActivity extends Activity {
	public static ArrayList<ProductDetails> Product_details;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_details_listview);
		listView = (ListView) findViewById(R.id.ProductDetailsListView);
		 
		getCategoriesFromServer();
		
	}

	private void getCategoriesFromServer() {
		long productid = getIntent().getLongExtra("idp", -1);
		long categoryid = getIntent().getLongExtra("cateId", -1);
		final ProgressDialog dialog = new ProgressDialog(this);
		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
			@Override
			public void onStart() {
				super.onStart();
				dialog.setMessage("opening product Details...");
				dialog.setCancelable(false);
				dialog.show();
				
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				getlist(response);
				
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				Toast.makeText(getApplicationContext(), throwable.getMessage(),
						Toast.LENGTH_SHORT).show();
		
				
			}

			@Override
			public void onFinish() {
				super.onFinish();
				dialog.dismiss();
			
			}
		};

		SmartCleanRestClient.get("categories/" + categoryid + "/products/"
				+ productid + ".json", handler);
	}

	private void getlist(JSONObject jobject) {
		
			Product_details = new ArrayList<ProductDetails>();

			try {

			// JSONObject ob = new JSONObject(); was the error line unwanted.
			ProductDetails p = ProductDetails.parse(jobject);
			Product_details.add(p);
			Log.i("PDETAILS", "" + Product_details);
			
			} catch (JSONException e) {

			}

			ProductDetailsAdapter adapter = new ProductDetailsAdapter(
			getApplicationContext(), Product_details);

			listView.setAdapter(adapter);

			}


}
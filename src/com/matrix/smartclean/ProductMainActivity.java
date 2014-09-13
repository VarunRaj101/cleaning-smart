package com.matrix.smartclean;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ProductMainActivity extends Activity implements
		OnItemClickListener {
	private ArrayList<Product> products;
	private ListView listView;
	public long categoryId;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_listview);
		listView = (ListView) findViewById(R.id.ProductListView);
		listView.setOnItemClickListener(this);
		getDetailssFromServer();
		
	}

	private void getDetailssFromServer() {
		long id = getIntent().getLongExtra("id", -1);
		categoryId = id;
		final ProgressDialog dialog = new ProgressDialog(this);
		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
			@Override
			public void onStart() {
				super.onStart();
				dialog.setMessage("opening Product list...");
				dialog.setCancelable(false);
				dialog.show();
				
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				super.onSuccess(statusCode, headers, response);
				getArraylist(response);

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
				Log.e("ProductDetailsMainActivity","hell mannnn4");
			}
		};

		SmartCleanRestClient
				.get("categories/" + categoryId + "/products.json", handler);
	}

	public void getArraylist(JSONArray jArray) {
		products = new ArrayList<Product>();
		try {
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject ob = jArray.getJSONObject(i);
				Product p = Product.parse(ob);
				products.add(p);
			}
		} catch (JSONException e) {
		}

		ProductAdapter adapter = new ProductAdapter(getApplicationContext(),
				products);
		listView.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			long position) {
		Intent intent = new Intent(getApplicationContext(),
				ProductDetailsMainActivity.class);

		Product p = products.get((int) position);
		intent.putExtra("idp", p.getId());
		intent.putExtra("cateId", categoryId);
		startActivity(intent);

	}

}
package com.matrix.smartclean.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.matrix.smartclean.R;
import com.matrix.smartclean.adapter.CategoryAdapter;
import com.matrix.smartclean.model.Category;
import com.matrix.smartclean.utils.SmartCleanRestClient;

public class CategoryMainActivity extends Activity implements
		OnItemClickListener {
	public static ArrayList<Category> categories;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.itemListView);
		listView.setOnItemClickListener(this);
		listView.setEmptyView(findViewById(R.id.image));
		getCategoriesFromServer();
	}

	private void getCategoriesFromServer() {
		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
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
		};

		SmartCleanRestClient.get("categories.json", handler);
	}

	public void getArraylist(JSONArray jArray) {
		categories = new ArrayList<Category>();
		try {
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject ob = jArray.getJSONObject(i);
				Category c = Category.parse(ob);
				categories.add(c);
			}
		} catch (JSONException e) {
		}

		CategoryAdapter adapter = new CategoryAdapter(getApplicationContext(),
				categories);
		listView.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			long position) {
		Intent intent = new Intent(getApplicationContext(),
				ProductsActivity.class);
		Category c = categories.get((int) position);
		intent.putExtra("id", c.getId());
		startActivity(intent);

	}

}
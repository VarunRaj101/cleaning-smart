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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;


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
		getCategoriesFromServer();
		int loader=R.drawable.loader;
		 ImageView image = (ImageView) findViewById(R.id.image);
		String image_url = "";
		ImageLoader imgLoader = new ImageLoader(getApplicationContext());
		 imgLoader.DisplayImage(image_url, loader, image);
    }
	

	private void getCategoriesFromServer() {
		final ProgressDialog dialog = new ProgressDialog(this);
				JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
			/*@Override
			public void onStart() {
				super.onStart();
				dialog.setMessage("Opening Categories...");
				dialog.setCancelable(false);
				dialog.show();
			}*/

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
			if(dialog!=null)
			{
			   try{	
			   if(dialog.isShowing());
			dialog.dismiss();
			   }catch (Exception e){}
			}
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
				ProductMainActivity.class);
		Category c = categories.get((int) position);
		intent.putExtra("id", c.getId());
		startActivity(intent);

	}

}
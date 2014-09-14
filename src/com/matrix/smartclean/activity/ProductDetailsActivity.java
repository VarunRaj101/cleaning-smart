package com.matrix.smartclean.activity;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.matrix.smartclean.R;
import com.matrix.smartclean.model.Product;
import com.matrix.smartclean.utils.SmartCleanRestClient;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends Activity {
	private TextView nameTextView;
	private TextView modelTextView;
	private TextView quantityTextView;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_textview_list);

		nameTextView = (TextView) findViewById(R.id.tvProductName);
		modelTextView = (TextView) findViewById(R.id.tvProductModel);

		quantityTextView = (TextView) findViewById(R.id.tvProductQuantity);
		imageView = (ImageView) findViewById(R.id.imageTextviewProduct);
		getProductFromServer();

	}

	private void getProductFromServer() {
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
		try {
			Product product = Product.parse(jobject);
			nameTextView.setText(product.getName());
			modelTextView.setText(product.getModel());
			quantityTextView.setText(product.getQuantity());
			String url = product.getImgThumbUrl();
			Picasso.with(this).load(url).placeholder(R.drawable.ic_launcher)
					.into(imageView);
		} catch (JSONException e) {
		}
	}

}
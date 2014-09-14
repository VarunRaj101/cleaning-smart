package com.matrix.smartclean;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailsAdapter extends ArrayAdapter<ProductDetails> {

	public ProductDetailsAdapter(Context context, List<ProductDetails> objects) {
		super(context, android.R.layout.simple_list_item_1, objects);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	private LayoutInflater inflater;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.product_details_textview,
					null);
			viewHolder = new ViewHolder();

			viewHolder.nameTextView = (TextView) convertView
					.findViewById(R.id.tvProductDetailsName);
			viewHolder.modelTextView = (TextView) convertView
					.findViewById(R.id.tvProductDetailsModel);

			viewHolder.quantityTextView = (TextView) convertView
					.findViewById(R.id.tvProductDetailsQuantity);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.imageTextviewProductDetails);

			convertView.setTag(viewHolder);

		} else
			viewHolder = (ViewHolder) convertView.getTag();
		ProductDetails product = getItem(position);
		viewHolder.nameTextView.setText(product.getName());
		viewHolder.modelTextView.setText(product.getModel());
		viewHolder.quantityTextView.setText(product.getQuantity());
		viewHolder.imageView.setImageResource(R.drawable.ic_launcher);
		String url = product.getPhotoUrl();
		Picasso.with(getContext()).load(url).into(viewHolder.imageView);
		return convertView;

	}

	public static class ViewHolder {
		public TextView nameTextView, modelTextView, quantityTextView;
		ImageView imageView;
	}
}

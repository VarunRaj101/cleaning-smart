package com.matrix.smartclean;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductAdapter extends ArrayAdapter<Product> {

	public ProductAdapter(Context context, List<Product> objects) {
		super(context, android.R.layout.simple_list_item_1, objects);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private LayoutInflater inflater;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.product_textview_list, null);
			viewHolder = new ViewHolder();
			
			viewHolder.nameTextView = (TextView) convertView
					.findViewById(R.id.tvProductName);
			viewHolder.modelTextView = (TextView) convertView
					.findViewById(R.id.tvProductModel);
			
			viewHolder.quantityTextView = (TextView) convertView
					.findViewById(R.id.tvProductQuantity);
			viewHolder.imageView=(ImageView)convertView.findViewById(R.id.imageTextviewProduct);
			convertView.setTag(viewHolder);

		} else
			viewHolder = (ViewHolder) convertView.getTag();
		Product product = getItem(position);
		viewHolder.nameTextView.setText(product.getName());
		viewHolder.modelTextView.setText(product.getModel());
		viewHolder.quantityTextView.setText(product.getQuantity());
		viewHolder.imageView.setImageResource(R.drawable.ic_launcher);
		return convertView;

	}

	public static class ViewHolder {
		public TextView nameTextView, modelTextView,quantityTextView;
		ImageView imageView;
	}
}
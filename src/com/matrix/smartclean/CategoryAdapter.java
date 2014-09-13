package com.matrix.smartclean;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<Category> {

	public CategoryAdapter(Context context, List<Category> objects) {
		super(context, android.R.layout.simple_list_item_1, objects);
		inflater = (LayoutInflater) context                             //correction git :missing coments :p
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private LayoutInflater inflater;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.main_textview_list, null);
			
			viewHolder = new ViewHolder();
			viewHolder.nameTextView = (TextView) convertView
					.findViewById(R.id.listItemTetView);
			
			
			convertView.setTag(viewHolder); // corection git :missing line :p

		} else
			viewHolder = (ViewHolder) convertView.getTag();
		Category catogory = getItem(position);
		viewHolder.nameTextView.setText(catogory.getName());
		
		return convertView;
	}

	private static class ViewHolder {
		public TextView nameTextView;
		;
	}

}
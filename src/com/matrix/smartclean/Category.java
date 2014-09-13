package com.matrix.smartclean;

import org.json.JSONException;
import org.json.JSONObject;

public class Category {
	private long id;
	private String name;

	public Category(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static Category parse(JSONObject jsonObject) throws JSONException {

		String name = jsonObject.getString("name");
		long id = jsonObject.getLong("id");
		Category c = new Category(id, name);
		return c;
	}

}
package com.matrix.smartclean;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
	private long id;
	private long category_id;
	private String name;
	private String model;
	private long code;
	private String standard;
	private String quantity;

	public Product(long id, long category_id, String name, String model,
			 String standard, long code,String quantity) {
		this.id = id;
		this.category_id = category_id;
		this.name=name;
		this.model = model;
		this.standard = standard;
		this.code=code;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public long getCategory_id() {
		return category_id;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public String getStandard() {
		return standard;
	}

	public long getCode() {
		return code;
	}

	public String getQuantity() {
		return quantity;
	}

	public static Product parse(JSONObject response) throws JSONException {
		long idp = response.getLong("id");
		long category_id = response.getLong("category_id");
		String name = response.getString("name");
		String model = response.getString("model");
		String standard = response.getString("standard");
		long code=response.getLong("code");
		String quantity = response.getString("quantity");
		Product p = new Product(idp, category_id, name, model, standard,code,
				quantity);
		return p;

	}

}
package com.matrix.smartclean.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
	private String name;
	private String model;
	private String quantity;
	private long id;
	private long category_id;
	private long code;
	private String standard;
	private String imgOriginalUrl;
	private String imgThumbUrl;

	public Product(String name, String model, String quantity, long id,
			long category_id, long code, String standard,
			String imgOriginalUrl, String imgThumbUrl) {

		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.model = model;
		this.standard = standard;
		this.code = code;
		this.quantity = quantity;
		this.imgOriginalUrl = imgOriginalUrl;
		this.imgThumbUrl = imgThumbUrl;

	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public String getQuantity() {
		return quantity;
	}

	public long getId() {
		return id;
	}

	public long getCategory_id() {
		return category_id;
	}

	public long getCode() {
		return code;
	}

	public String getStandard() {
		return standard;
	}

	public String getImgOriginalUrl() {
		return imgOriginalUrl;
	}

	public String getImgThumbUrl() {
		return imgThumbUrl;
	}

	public static Product parse(JSONObject jsonobject) throws JSONException {
		long idp = jsonobject.getLong("id");
		long category_id = jsonobject.getLong("category_id");
		String name = jsonobject.getString("name");
		String model = jsonobject.getString("model");
		String standard = jsonobject.getString("standard");
		long code = jsonobject.getLong("code");
		String quantity = jsonobject.getString("quantity");
		JSONObject imageurl = jsonobject.getJSONObject("image_url");
		String originalimage = imageurl.getString("original");
		String thumbImage = imageurl.getString("thumb");
		Product p = new Product(name, model, quantity, idp, code, category_id,
				standard, originalimage, thumbImage);
		return p;

	}

}
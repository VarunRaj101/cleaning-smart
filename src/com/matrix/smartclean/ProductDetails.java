package com.matrix.smartclean;

import java.util.Map;

import org.json.JSONObject;
import org.json.JSONException;
public class ProductDetails {
	private String name;
	private String model;
	private String quantity;
	private long id;
	private long category_id;
	private long code;
	private String standard;
	//private String imageurl;
	

	public ProductDetails(String name, String model, String quantity, long id,
			long category_id, long code, String standard) {

		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.model = model;
		this.standard = standard;
		this.code = code;
		this.quantity = quantity;

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

	public long getIdp() {
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

	

	public static ProductDetails parse(JSONObject jsonobject)throws JSONException {
		long idp = jsonobject.getLong("id");
		long category_id = jsonobject.getLong("category_id");
		String name = jsonobject.getString("name");
		String model = jsonobject.getString("model");
		String standard = jsonobject.getString("standard");
		long code=jsonobject.getLong("code");
		String quantity = jsonobject.getString("quantity");
		String variable = null;
//		JSONObject imageurl=new JSONObject(variable);
//		JSONObject image=imageurl.getJSONObject("image_url");
//		String originalimage=image.getString("original");
		ProductDetails p = new ProductDetails(name,model,quantity,idp,code,category_id,
				standard);
		return p;
		
	}

	public String getPhotoUrl() {
	
		String	url="http://blog.inner-active.com/wp-content/uploads/2013/08/AndroidWallpaper.jpg";
		return url;
	}

	

}
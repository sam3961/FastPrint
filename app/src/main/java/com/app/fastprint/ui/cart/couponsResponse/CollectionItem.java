package com.app.fastprint.ui.cart.couponsResponse;

import com.google.gson.annotations.SerializedName;

public class CollectionItem{

	@SerializedName("href")
	private String href;

	public String getHref(){
		return href;
	}
}
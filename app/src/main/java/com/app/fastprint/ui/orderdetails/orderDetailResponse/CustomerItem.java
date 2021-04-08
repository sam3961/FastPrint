package com.app.fastprint.ui.orderdetails.orderDetailResponse;

import com.google.gson.annotations.SerializedName;

public class CustomerItem{

	@SerializedName("href")
	private String href;

	public String getHref(){
		return href;
	}
}
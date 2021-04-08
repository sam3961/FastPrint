package com.app.fastprint.ui.payment.orderCreateResponse;

import com.google.gson.annotations.SerializedName;

public class TaxesItem{

	@SerializedName("total")
	private String total;

	@SerializedName("subtotal")
	private String subtotal;

	@SerializedName("id")
	private int id;

	public String getTotal(){
		return total;
	}

	public String getSubtotal(){
		return subtotal;
	}

	public int getId(){
		return id;
	}
}
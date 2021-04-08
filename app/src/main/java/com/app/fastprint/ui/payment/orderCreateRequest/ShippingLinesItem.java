package com.app.fastprint.ui.payment.orderCreateRequest;

import com.google.gson.annotations.SerializedName;

public class ShippingLinesItem{

	@SerializedName("total")
	private String total;

	@SerializedName("method_id")
	private String methodId;

	@SerializedName("method_title")
	private String methodTitle;

	public String getTotal(){
		return total;
	}

	public String getMethodId(){
		return methodId;
	}

	public String getMethodTitle(){
		return methodTitle;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public void setMethodTitle(String methodTitle) {
		this.methodTitle = methodTitle;
	}
}
package com.app.fastprint.ui.payment.orderCreateRequest;

import com.google.gson.annotations.SerializedName;

public class CouponsItem {

	@SerializedName("code")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
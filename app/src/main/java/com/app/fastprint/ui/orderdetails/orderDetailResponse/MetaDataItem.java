package com.app.fastprint.ui.orderdetails.orderDetailResponse;

import com.google.gson.annotations.SerializedName;

public class MetaDataItem{

	@SerializedName("display_value")
	private String displayValue;

	@SerializedName("display_key")
	private String displayKey;

	@SerializedName("id")
	private int id;

	@SerializedName("value")
	private String value;

	@SerializedName("key")
	private String key;

	public String getDisplayValue(){
		return displayValue;
	}

	public String getDisplayKey(){
		return displayKey;
	}

	public int getId(){
		return id;
	}

	public String getValue(){
		return value;
	}

	public String getKey(){
		return key;
	}
}
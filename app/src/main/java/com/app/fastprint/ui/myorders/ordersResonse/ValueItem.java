package com.app.fastprint.ui.myorders.ordersResonse;

import com.google.gson.annotations.SerializedName;

public class ValueItem{

	@SerializedName("size")
	private int size;

	@SerializedName("name")
	private String name;

	@SerializedName("type")
	private String type;

	@SerializedName("error")
	private int error;

	@SerializedName("tmp_name")
	private String tmpName;

	public int getSize(){
		return size;
	}

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}

	public int getError(){
		return error;
	}

	public String getTmpName(){
		return tmpName;
	}
}
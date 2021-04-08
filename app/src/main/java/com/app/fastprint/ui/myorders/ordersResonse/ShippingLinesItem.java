package com.app.fastprint.ui.myorders.ordersResonse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShippingLinesItem{

	@SerializedName("total")
	private String total;

	@SerializedName("instance_id")
	private String instanceId;

	@SerializedName("method_id")
	private String methodId;

	@SerializedName("meta_data")
	private List<MetaDataItem> metaData;

	@SerializedName("taxes")
	private List<Object> taxes;

	@SerializedName("id")
	private int id;

	@SerializedName("total_tax")
	private String totalTax;

	@SerializedName("method_title")
	private String methodTitle;

	public String getTotal(){
		return total;
	}

	public String getInstanceId(){
		return instanceId;
	}

	public String getMethodId(){
		return methodId;
	}

	public List<MetaDataItem> getMetaData(){
		return metaData;
	}

	public List<Object> getTaxes(){
		return taxes;
	}

	public int getId(){
		return id;
	}

	public String getTotalTax(){
		return totalTax;
	}

	public String getMethodTitle(){
		return methodTitle;
	}
}
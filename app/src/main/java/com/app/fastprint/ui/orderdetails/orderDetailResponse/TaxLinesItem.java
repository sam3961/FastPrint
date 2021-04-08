package com.app.fastprint.ui.orderdetails.orderDetailResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TaxLinesItem{

	@SerializedName("tax_total")
	private String taxTotal;

	@SerializedName("rate_id")
	private int rateId;

	@SerializedName("rate_percent")
	private int ratePercent;

	@SerializedName("meta_data")
	private List<Object> metaData;

	@SerializedName("id")
	private int id;

	@SerializedName("label")
	private String label;

	@SerializedName("rate_code")
	private String rateCode;

	@SerializedName("compound")
	private boolean compound;

	@SerializedName("shipping_tax_total")
	private String shippingTaxTotal;

	public String getTaxTotal(){
		return taxTotal;
	}

	public int getRateId(){
		return rateId;
	}

	public int getRatePercent(){
		return ratePercent;
	}

	public List<Object> getMetaData(){
		return metaData;
	}

	public int getId(){
		return id;
	}

	public String getLabel(){
		return label;
	}

	public String getRateCode(){
		return rateCode;
	}

	public boolean isCompound(){
		return compound;
	}

	public String getShippingTaxTotal(){
		return shippingTaxTotal;
	}
}
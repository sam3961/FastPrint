package com.app.fastprint.ui.myorders.ordersResonse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LineItemsItem{

	@SerializedName("parent_name")
	private String parentName;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("tax_class")
	private String taxClass;

	@SerializedName("product_image")
	private String productImage;

	@SerializedName("taxes")
	private List<TaxesItem> taxes;

	@SerializedName("total_tax")
	private String totalTax;

	@SerializedName("total")
	private String total;

	@SerializedName("variation_id")
	private int variationId;

	@SerializedName("subtotal")
	private String subtotal;

	@SerializedName("price")
	private double price;

	@SerializedName("product_id")
	private int productId;

	@SerializedName("name")
	private String name;

	@SerializedName("meta_data")
	private List<MetaDataItem> metaData;

	@SerializedName("id")
	private int id;

	@SerializedName("subtotal_tax")
	private String subtotalTax;

	@SerializedName("sku")
	private String sku;

	public String getParentName(){
		return parentName;
	}

	public int getQuantity(){
		return quantity;
	}

	public String getTaxClass(){
		return taxClass;
	}

	public String getProductImage(){
		return productImage;
	}

	public List<TaxesItem> getTaxes(){
		return taxes;
	}

	public String getTotalTax(){
		return totalTax;
	}

	public String getTotal(){
		return total;
	}

	public int getVariationId(){
		return variationId;
	}

	public String getSubtotal(){
		return subtotal;
	}

	public Double getPrice(){
		return price;
	}

	public int getProductId(){
		return productId;
	}

	public String getName(){
		return name;
	}

	public List<MetaDataItem> getMetaData(){
		return metaData;
	}

	public int getId(){
		return id;
	}

	public String getSubtotalTax(){
		return subtotalTax;
	}

	public String getSku(){
		return sku;
	}
}
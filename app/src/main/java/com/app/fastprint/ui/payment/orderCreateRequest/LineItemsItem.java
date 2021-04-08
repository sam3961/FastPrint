package com.app.fastprint.ui.payment.orderCreateRequest;

import com.google.gson.annotations.SerializedName;

public class LineItemsItem{

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("variation_id")
	private int variationId;

	@SerializedName("product_id")
	private int productId;

	public int getQuantity(){
		return quantity;
	}

	public int getVariationId(){
		return variationId;
	}

	public int getProductId(){
		return productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setVariationId(int variationId) {
		this.variationId = variationId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
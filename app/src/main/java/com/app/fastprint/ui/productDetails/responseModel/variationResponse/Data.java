package com.app.fastprint.ui.productDetails.responseModel.variationResponse;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("variation_price")
	private String variationPrice;

	@SerializedName("currency_symbol")
	private String currencySymbol;

	@SerializedName("variation_id")
	private int variationId;

	@SerializedName("currency")
	private String currency;

	@SerializedName("variation_regular_price")
	private String variationRegularPrice;

	@SerializedName("variation_sale_price")
	private String variationSalePrice;

	public String getVariationPrice(){
		return variationPrice;
	}

	public String getCurrencySymbol(){
		return currencySymbol;
	}

	public int getVariationId(){
		return variationId;
	}

	public String getCurrency(){
		return currency;
	}

	public String getVariationRegularPrice(){
		return variationRegularPrice;
	}

	public String getVariationSalePrice(){
		return variationSalePrice;
	}
}
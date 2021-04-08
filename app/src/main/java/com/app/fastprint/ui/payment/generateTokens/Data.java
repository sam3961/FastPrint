package com.app.fastprint.ui.payment.generateTokens;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("consumer_key")
	private String consumerKey;

	@SerializedName("consumer_secret")
	private String consumerSecret;

	public String getConsumerKey(){
		return consumerKey;
	}

	public String getConsumerSecret(){
		return consumerSecret;
	}
}
package com.app.fastprint.ui.notification.notificationResponse;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("date")
	private String date;

	@SerializedName("link")
	private String link;

	@SerializedName("title")
	private String title;

	@SerializedName("message")
	private String message;

	public String getDate(){
		return date;
	}

	public String getLink(){
		return link;
	}

	public String getTitle(){
		return title;
	}

	public String getMessage(){
		return message;
	}
}
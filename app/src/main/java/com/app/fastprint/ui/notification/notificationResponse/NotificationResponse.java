package com.app.fastprint.ui.notification.notificationResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class NotificationResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public List<DataItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public int getStatus(){
		return status;
	}
}
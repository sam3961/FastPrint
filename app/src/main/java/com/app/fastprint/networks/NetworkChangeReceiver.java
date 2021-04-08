package com.app.fastprint.networks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class NetworkChangeReceiver extends BroadcastReceiver {

	public static String change_Status = "";
	@Override
	public void onReceive(final Context context, final Intent intent) {

		change_Status = NetworksStatus.getConnectivityStatusString(context);
		Toast.makeText(context, change_Status, Toast.LENGTH_LONG).show();
	}

}

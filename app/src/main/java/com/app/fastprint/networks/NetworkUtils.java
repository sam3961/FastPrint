package com.app.fastprint.networks;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
public class NetworkUtils {

	public static boolean isNetworkConnectionAvailable(Context context) {
		ConnectivityManager cm =
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		@SuppressWarnings("deprecation")
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		@SuppressWarnings("deprecation")

		boolean isConnected = activeNetwork != null &&
				activeNetwork.isConnected();

		if (isConnected) {
			Log.d("Network", "Connected");

			return true;
		} else {
			networkDialog(context);
			Log.d("Network", "Not Connected");
			return false;
		}
	}



	private static void networkDialog(final Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("No Internet Connection");
		builder.setMessage("Please check your network setting");

		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				isNetworkConnectionAvailable(context);
				dialog.dismiss();
			}

		});

		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}
}

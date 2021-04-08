package com.app.fastprint.networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.app.fastprint.R;

public class NetworksStatus {

    public static final int TYPE_NOT_CONNECTED = 0;
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_CELLULAR = 2;
    public static final int TYPE_ETHERNET = 3;

    public static int isNetworkAvailable(Context context) {

        if(context == null)  return TYPE_NOT_CONNECTED;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.d("update_statut", "Network is available : true TRANSPORT_CELLULAR ");
                        return TYPE_CELLULAR;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.d("update_statut", "Network is available : true TRANSPORT_WIFI");
                        return TYPE_WIFI;
                    }  else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                        Log.d("update_statut", "Network is available : true TRANSPORT_ETHERNET");
                        return TYPE_ETHERNET;
                    }
                }
            }

            else {

                try {
                    NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                    if (null != activeNetwork && activeNetwork.isConnected()) {

                        if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                            return TYPE_WIFI;

                        if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                            return TYPE_CELLULAR;

                    }
                    return TYPE_NOT_CONNECTED;

                } catch (Exception e) {
                    Log.d("update_statut", "" + e.getMessage());
                }
            }
        }
        Log.d("update_statut","Network is available : FALSE ");
        return TYPE_NOT_CONNECTED;
    }


    public static String getConnectivityStatusString(Context context) {

        int conn = NetworksStatus.isNetworkAvailable(context);
        String status = null;
        if (conn == NetworksStatus.TYPE_WIFI) {
            status = context.getString(R.string.connected_with_wifi);
        } else if (conn == NetworksStatus.TYPE_CELLULAR) {
            status = context.getString(R.string.connected_with_mobile);
            //status =  getNetworkClass(context);
        } else if (conn == NetworksStatus.TYPE_ETHERNET) {
            status = context.getString(R.string.connected_with_ethernet);
        } else if (conn == NetworksStatus.TYPE_NOT_CONNECTED) {
            status = context.getString(R.string.you_are_offline);
        }
        return status;
    }

    private static String getNetworkClass(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info == null || !info.isConnected())
            return "-"; //not connected
        if(info.getType() == ConnectivityManager.TYPE_WIFI)
            return "WIFI";
        if(info.getType() == ConnectivityManager.TYPE_MOBILE){
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                    return "4G";
                default:
                    return "UNKNOWN";
            }
        }
        return "UNKNOWN";
    }
}

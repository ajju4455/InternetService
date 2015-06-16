package aze.samples.internetservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

	private Context mContext;

	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;
		Toast.makeText(mContext, "NetworkChangeReceiver called", Toast.LENGTH_SHORT).show();
		try {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			// should check null because in air plan mode it will be null
			if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
				Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show();
				Intent mIntent = new Intent(context, CusotmService.class);
				context.startService(mIntent);
			} else {
				Toast.makeText(mContext, "Net disconnected", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Toast.makeText(mContext, "from catch Net disconnected", Toast.LENGTH_SHORT).show();
		}

	}

	public boolean isOnline(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		// should check null because in air plan mode it will be null
		return (netInfo != null && netInfo.isConnected());

	}

}
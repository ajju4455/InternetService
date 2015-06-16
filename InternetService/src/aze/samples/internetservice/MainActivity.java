package aze.samples.internetservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((Button) findViewById(R.id.activity_btn_net_connetivity_service_test)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isNetworkConnected()) {
					Toast.makeText(MainActivity.this, "Internet connected", Toast.LENGTH_SHORT).show();
					Intent mIntent = new Intent(MainActivity.this, CusotmService.class);
					startService(mIntent);
				} else {
					Toast.makeText(MainActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	public boolean isNetworkConnected() {
		try {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			if (mConnectivityManager != null) {
				NetworkInfo[] mNetworkInfos = mConnectivityManager.getAllNetworkInfo();
				if (mNetworkInfos != null) {
					for (int i = 0; i < mNetworkInfos.length; i++) {
						if (mNetworkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}

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

	public String ping(String url) {
		String str = "";
		try {
			Process process = Runtime.getRuntime().exec("/system/bin/ping -c 8 " + url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			int i;
			char[] buffer = new char[4096];
			StringBuffer output = new StringBuffer();
			while ((i = reader.read(buffer)) > 0)
				output.append(buffer, 0, i);
			reader.close();

			// body.append(output.toString()+"\n");
			str = output.toString();
			// Log.d(TAG, str);
		} catch (IOException e) {
			// body.append("Error\n");
			e.printStackTrace();
		}
		return str;
	}

	public boolean isNetworkConnected() {
		try {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			if (mConnectivityManager != null) {
				NetworkInfo[] mNetworkInfos = mConnectivityManager.getAllNetworkInfo();
				if (mNetworkInfos != null) {
					for (int i = 0; i < mNetworkInfos.length; i++) {
						if (mNetworkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
							System.out.println("Azhar " + System.currentTimeMillis() + "  true");
							return true;
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Azhar " + System.currentTimeMillis() + "  false");
		return false;
	}

}

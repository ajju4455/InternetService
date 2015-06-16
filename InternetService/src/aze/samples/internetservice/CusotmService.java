package aze.samples.internetservice;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class CusotmService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		showToast("onStartCommand");
		new myAsyncTask().execute();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		showToast("onDestroy");
		super.onDestroy();
	}

	public class myAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			showToast("onPreExecute");
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			showToast("onPostExecute");
			super.onPostExecute(result);
			stopSelf();
		}

	}

	private void showToast(String mMessage) {
		Toast.makeText(this, mMessage, Toast.LENGTH_SHORT).show();
	}

}
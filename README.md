# InternetService

CustomService automatically starts on change of WiFi or Mobile Network.

NetworkChangeReceiver is a BroadcastReceiver which 
listen broadcast of any change of connectivity with in the device 
whether it is WiFi state ON-OFF or MobileNetwork state.

after receiving the connectivity state it checks for internet connection 
and show respective toast and if device found internet connection 
then  it will start CustomService which will invoke an AsyncTask 
which also show respective toast of "onPreExecute" and "onPostExecute"

main feature of this sample is that service will not remain working 
in background as its task done it will stop itself.

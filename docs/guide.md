AnyStore SDK Integration Guide
====

###1. Package

SDK: AnyStore SDK integration files

Demo: A Demo project demostrates how to integrate AnyStore SDK

docs: Documentations

###2. Setup
Copy every thing under SDK folder to your porject folder

###3. Edit AndroidManifest
Adding the following permission to AndroidManifest.xml

```
<uses-permissionandroid:name="android.permission.INTERNET"/><uses-permissionandroid:name="android.permission.ACCESS_NETWORK_STATE"/><uses-permissionandroid:name="android.permission.ACCESS_WIFI_STATE"/><uses-permissionandroid:name="android.permission.ACCESS_COARSE_LOCATION"/><uses-permissionandroid:name="android.permission.ACCESS_FINE_LOCATION"/><uses-permissionandroid:name="android.permission.WRITE_EXTERNAL_STORAGE"/><uses-permissionandroid:name="android.permission.SEND_SMS"/><uses-permissionandroid:name="android.permission.READ_PHONE_STATE"/><uses-permissionandroid:name="android.permission.READ_CONTACTS"/><uses-permissionandroid:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/><uses-permissionandroid:name="android.permission.GET_TASKS"/>```
Reigister the following services and activities```
<serviceandroid:name="com.ckmobilling.CkService"android:exported="true"android:process=":remote"><intent-filter><actionandroid:name="com.ckmobilling.ICkService"/></intent-filter></service><activityandroid:name="com.ckmobilling.MdoPayActivity"android:configChanges="orientation|keyboardHidden"android:excludeFromRecents="true"></activity><activityandroid:name="com.umpay.huafubao.ui.BillingActivity"android:configChanges="orientation|keyboardHidden"android:excludeFromRecents="true"></activity>
<serviceandroid:name="com.umpay.huafubao.service.AppUpgradeService"/>```Make sure set the android:name

```
<applicationandroid:allowBackup="true"android:name=".App"android:icon="@drawable/ic_launcher"android:label="@string/app_name">
</application>```
###4. Initialize AnyStore SDK

####Add the following line to your `Application` Class

```
import com.ckmobilling.CkSdkApi;

public class App extends Application {	@Override	public void onCreate() {		super.onCreate();
		CkSdkApi.getInstance().loadLibrary(this);	}}
```
####Add the folloing line to your `Activity` Class

```
import com.ckmobilling.CkSdkApi;
import com.ckmobilling.PaymentCallback;
import com.ckmobilling.PaymentResult;

protected void onCreate(Bundle savedInstanceState) {	super.onCreate(savedInstanceState);
	CkSdkApi.getInstance().initialize(this);}
	
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	CkSdkApi.getInstance().onActivityResult(requestCode, resultCode, data);
}

@Override
protected void onDestroy() {
	super.onDestroy();
	CkSdkApi.getInstance().onDestroy();
}```

###5. Send Payment


Use `doPayment` perform IAP

```
public void doPayment(Context context, String PayCode, PaymentCallback callback);
```
Context: The `Activity` where it was created

PayCode: The ID for the IAP item

Callback: The callback for this transaction

```
		CkSdkApi.getInstance().doPayment(MainActivity.this, "0007", new PaymentCallback() {

			@Override
			public void paySuccess(PaymentResult result) {
			}

			@Override
			public void payFailed(PaymentResult result) {
			}

			@Override
			public void payCanceled(PaymentResult result) {
			}
			
		});
```

The `PaymentResult` returns from the callback will contain the PayCode indicating which item is associated with the payment

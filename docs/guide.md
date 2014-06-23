AnyStore SDK Integration Guide
====

##1. Package

SDK: AnyStore SDK integration files

Demo: A Demo project demostrates how to integrate AnyStore SDK

docs: Documentations

---

##2. Setup

Copy everything under SDK folder to your porject folder

---
##3. Edit AndroidManifest

Adding the following permission to AndroidManifest.xml

```xml
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.GET_TASKS" />
```

Reigister the following services and activities

```xml
        <service android:name="com.ckmobilling.CkService"
		    android:exported="true"
		    android:process=":remote" >
            <intent-filter>
                <action android:name="com.ckmobilling.ICkService" />
            </intent-filter>
        </service>
        
		<activity
            android:name="com.ckmobilling.MdoPayActivity"
            android:configChanges="orientation|keyboardHidden"
            android:excludeFromRecents="true" >
       	</activity>
		
        <activity
            android:name="com.umpay.huafubao.ui.BillingActivity"
            android:configChanges="orientation|keyboardHidden"
            android:excludeFromRecents="true" >
        </activity>
        
        <service android:name="com.umpay.huafubao.service.AppUpgradeService" />
```

---

##4. Initialize AnyStore SDK

####Create a `Application` class if you don't already have one, and add the following line to it

```java
import com.ckmobilling.CkSdkApi;
import android.app.Application;

public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
        
        //Load AnyStore API
		CkSdkApi.getInstance().loadLibrary(this);
	}
}
```
Update the AndroidManifest and make sure `android:name` was set correctly
```xml
<application
android:allowBackup="true"
android:name=".App"
android:icon="@drawable/ic_launcher"
android:label="@string/app_name">

</application>
```

####Add the folloing line to your `Activity` Class

```java
import com.ckmobilling.CkSdkApi;
import com.ckmobilling.PaymentCallback;
import com.ckmobilling.PaymentResult;

protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
    
    //Initialize
	CkSdkApi.getInstance().initialize(this);
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	
	//Register onActivityResult
	CkSdkApi.getInstance().onActivityResult(requestCode, resultCode, data);
}

@Override
protected void onDestroy() {
	super.onDestroy();
	
	//Clean up
	CkSdkApi.getInstance().onDestroy();
}

```

---
##5. Send Payment

Use `doPayment` perform IAP

```java
public void doPayment(Context context, String PayCode, PaymentCallback callback);
```
Context: The `Activity` where it was created

PayCode: The ID for the IAP item

Callback: The callback for this transaction

```java
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

---
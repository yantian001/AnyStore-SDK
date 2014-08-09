AnyStore SDK Integration Guide
====
#Overview

###Before you start
Make sure you have the following item from AnyStore

* The latest AnyStore SDK
* Submit your IAP items to AnyStore and get a list of **AnyStore Product IDs**

###Requirements
* Android 2.3 (API 10) and above

###Package contents

**SDK**: AnyStore SDK integration files

**Demo**: A Demo project demostrates how to integrate AnyStore SDK

**docs**: Documentations

---

#Integration
Integrating the AnyStore SDK is quick and easy, just a few steps

###1. Android Project Integration
Copy everything under `SDK` folder to your porject folder

---
###2. Edit AndroidManifest.xml

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
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.READ_SMS" />
    <uses-permission android:name="com.ut.permission.DEVICE_STATE" />
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.READ_SETTINGS" />
    <uses-permission android:name="android.permission.READ_LOGS" />
    <uses-permission android:name="android.permission.DISABLE_KEYGUARD" /> 
    <uses-permission android:name="com.alipay.mobile.command.trigger.permission" />   
    <permission android:name="com.ut.permission.DEVICE_STATE" />
    <permission android:name="com.alipay.mobile.command.trigger.permission" />
```

Reigister the following services and activities

```xml
        <activity
            android:name="cn.egame.terminal.paysdk.EgamePayActivity"
            android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" >
        </activity>        
        <activity
            android:name="com.umpay.huafubao.ui.BillingActivity"
            android:configChanges="orientation|keyboardHidden"
            android:excludeFromRecents="true" >
        </activity>
        <service android:name="com.umpay.huafubao.service.AppUpgradeService" /> 
        <service android:name="com.ckmobilling.CkService"
            android:process=":remote"
            android:exported="true">
            <intent-filter>
                <action android:name="com.ckmobilling.ICkService" />
            </intent-filter>
        </service>  
        <activity
            android:name="com.ck.android.app.upomp.CKUpompActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" >
        </activity>
        <activity
            android:name="com.unionpay.upomp.lthj.plugin.ui.SplashActivity"
            android:screenOrientation="portrait" >
            <intent-filter>
                <action android:name="com.unionpay.upomp.lthj.android.plugin.init.test" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.unionpay.upomp.lthj.plugin.ui.IndexActivityGroup"
            android:screenOrientation="portrait" >
            <intent-filter>
                <action android:name="com.unionpay.upomp.lthj.android.plugin.index.test" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.unionpay.upomp.lthj.plugin.ui.HomeActivity"
            android:screenOrientation="portrait" >
        </activity>
        <activity
            android:name="com.unionpay.upomp.lthj.plugin.ui.PayActivity"
            android:screenOrientation="portrait" >
        </activity>
        <activity
            android:name="com.unionpay.upomp.lthj.plugin.ui.AccountActivity"
            android:screenOrientation="portrait" >
        </activity>
        <activity
            android:name="com.unionpay.upomp.lthj.plugin.ui.BankCardInfoActivity"
            android:screenOrientation="portrait" >
        </activity>
        <activity
            android:name="com.unionpay.upomp.lthj.plugin.ui.SupportCardActivity"
            android:screenOrientation="portrait" >
        </activity>
        <activity
            android:name="com.unionpay.upomp.lthj.plugin.ui.UserProtocolActivity"
            android:screenOrientation="portrait" >
        </activity>
        <activity
            android:name="com.unionpay.upomp.lthj.plugin.ui.AboutActivity"
            android:screenOrientation="portrait" >
        </activity>
        <activity
            android:name="com.ck.android.app.szf.ShenZhouFuActivity"
            android:screenOrientation="portrait" >
        </activity>
        <activity             android:name="com.shenzhoufu.android.mobilegamerechargemain.MobileGameRechargeMain"
            android:screenOrientation="portrait" >
            <intent-filter>
                <action android:name="com.shenzhoufu.android.mobilegamerechargemain.MobileGameRechargeMain" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.shenzhoufu.android.mobilegamerechargeresult.MobileRecharging"
            android:screenOrientation="portrait" >
        </activity>
        <activity
            android:name="com.shenzhoufu.android.mobilegamerechargeresult.BaseActivity"
            android:screenOrientation="portrait" >
        </activity> 
        <activity
            android:name="com.alipay.android.mini.window.sdk.MiniLaucherActivity"
            android:configChanges="orientation"
            android:excludeFromRecents="true"
            android:exported="false"
            android:launchMode="singleTop"
            android:theme="@style/MspAppTheme" >
            <intent-filter>
                <action android:name="com.alipay.mobilepay.android" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            <meta-data
                android:name="com.taobao.android.ski.NODERIVATION"
                android:value="true" />
        </activity>
        <activity
            android:name="com.alipay.android.mini.window.sdk.TransContainer"
            android:configChanges="orientation"
            android:excludeFromRecents="true"
            android:exported="false"
            android:launchMode="singleTop"
            android:theme="@style/MspAppTheme" >
            <meta-data
                android:name="com.taobao.android.ski.NODERIVATION"
                android:value="true" />
        </activity>
        <activity
            android:name="com.alipay.android.mini.window.sdk.MiniPayActivity"
            android:configChanges="orientation"
            android:excludeFromRecents="true"
            android:exported="false"
            android:label="@string/msp_app_name"
            android:launchMode="singleTop"
            android:theme="@style/MspAppTheme"
            android:windowSoftInputMode="adjustResize" >
            <intent-filter android:priority="800" >
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            <meta-data
                android:name="com.taobao.android.ski.NODERIVATION"
                android:value="true" />
        </activity>
        <activity
            android:name="com.alipay.android.mini.window.sdk.MiniWebActivity"
            android:configChanges="orientation|keyboardHidden|navigation"
            android:excludeFromRecents="true"
            android:exported="false"
            android:launchMode="singleTop"
            android:theme="@style/MspAppTheme" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            <meta-data
                android:name="com.taobao.android.ski.NODERIVATION"
                android:value="true" />
        </activity>
        <service android:name="com.alipay.android.app.MspService" />
        <receiver
            android:name="com.ut.device.BQueryWhoHasOne"
            android:exported="true"
            android:permission="com.ut.permission.DEVICE_STATE" >
            <intent-filter>
                <action android:name="UT.QueryWhoHasOne" />
            </intent-filter>
        </receiver>
        <receiver
            android:name="com.ut.device.BFoundIt"
            android:exported="true"
            android:permission="com.ut.permission.DEVICE_STATE" >
            <intent-filter>
                <action android:name="UT.FoundIT" />
            </intent-filter>
        </receiver>

```
---

###3. Code Integration

Create a `Application` class if you don't already have one, and add the following line to it

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
Update the `AndroidManifest.xml` and make sure `android:name` under `application` was set. It should be the same as the class name on the last step. In this case we use `.App`
```xml
    <application
        android:allowBackup="true"
        android:name=".App"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >

    </application>
```

Add the folloing line to your `Activity` Class

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
#Usage

###Send payment request
Use `CkSdkApi.getInstance().doPayment` to send payment request

```java
public void doPayment(Context context, String productID, PaymentCallback callback);
```
**Parameters:**
```
Context: The `Activity` where it was created
ProductID: The productID assigned by AnyStore for the IAP item
Callback: The callback for this transaction
```

Example:
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
###Receive payment result
The `PaymentResult` returns from the callback will contain the `PayCode` indicating which item is associated with the payment

As well as other info about the product
```
getItemName()
getItemNumber()
getItemPrice()
getPayResult()
getPayCode()
```

Example:
```java
	public void paySuccess(PaymentResult result) {
		if(result.payCode.equals(mItemCode)){
			System.out.println("Success!");
		}
	}
```

---

#Support

Any Questions? [We're here to help](mailto:support@us.chukong-inc.com)
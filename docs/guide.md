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
    <permission android:name="com.alipay.mobile.command.trigger.permission" />
    <permission android:name="com.ut.permission.DEVICE_STATE" />
```

Reigister the following services and activities

```xml
        <!-- AnyStoreSDK Start -->
        <service
            android:name="com.cocospay.CocosPayService"
            android:exported="true"
            android:process=":remote" >
            <intent-filter>
                <action android:name="com.cocospay.ICocosPayService" />
            </intent-filter>
        </service>

        <activity android:name="com.cocospay.CocosPaySplashActivity" />
        <activity
            android:name="cn.cmgame.billing.api.GameOpenActivity"
            android:screenOrientation="sensor"
            android:theme="@android:style/Theme.NoTitleBar.Fullscreen" >
            <intent-filter>
                <action android:name="android.intent.action.CHINAMOBILE_OMS_GAME" />

                <category android:name="android.intent.category.CHINAMOBILE_GAMES" />
            </intent-filter>
        </activity>

        <!-- MM begin -->
        <service
            android:name="mm.purchasesdk.iapservice.PurchaseService"
            android:exported="true" >

            <!-- android:process="mm.iapServices" > -->
            <intent-filter android:priority="301" >
                <action android:name="com.aspire.purchaseservice.BIND" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            <intent-filter android:priority="301" >
                <action android:name="cn.play.egamesmsoffline.purchaseservice.BIND" /> <!-- Replace package name here -->

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            <intent-filter android:priority="301" >
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.SAFIAP.COMPONENT" >
                </category>
            </intent-filter>
        </service>
        <!-- android:excludeFromRecents="true" -->
        <!-- android:launchMode="singleInstance" -->
        <activity
            android:name="mm.purchasesdk.iapservice.BillingLayoutActivity"
            android:configChanges="orientation|keyboardHidden"
            android:theme="@android:style/Theme.Translucent" >
            <intent-filter android:priority="301" >
                <action android:name="cn.play.egamesmsoffline.com.mmiap.activity" /> <!-- Replace package name here -->

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

        <!-- android:process="safiap.framework.safframeworkmanager" begin -->
        <service
            android:name="safiap.framework.SafFrameworkManager"
            android:exported="true"
            android:process="safiap.framework" >
            <intent-filter android:priority="630" >

                <!-- ID for services declared in AIDL -->
                <action android:name="safiap.framework.sdk.ISAFFramework" />
            </intent-filter>
            <intent-filter android:priority="630" >

                <!-- ID for services declared in AIDL -->
                <action android:name="safiap.framework.ACTION_START_DOWNLOAD" />
            </intent-filter>
            <intent-filter android:priority="630" >

                <!-- ID for services declared in AIDL -->
                <action android:name="safiap.framework.ACTION_CHECK_UPDATE" />
            </intent-filter>
        </service>
        <!-- receivers -->
        <receiver android:name="safiap.framework.CheckUpdateReceiver" >
            <intent-filter>
                <action android:name="safiap.framework.ACTION_CANCEL_NOTIFICATION" />
            </intent-filter>
            <intent-filter>
                <action android:name="safiap.GET_SHARED_DATA" />
            </intent-filter>
            <intent-filter>
                <action android:name="safiap.framework.ACTION_SET_TIMER" />
            </intent-filter>
        </receiver>

        <activity
            android:name="safiap.framework.ui.UpdateHintActivity"
            android:configChanges="orientation"
            android:excludeFromRecents="true"
            android:launchMode="singleInstance"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" >
            <intent-filter>
                <action android:name="safiap.framework.ACTION_TO_INSTALL" />
            </intent-filter>
            <intent-filter>
                <action android:name="safiap.framework.ACTION_TO_INSTALL_IAP" />
            </intent-filter>
            <intent-filter>
                <action android:name="safiap.framework.ACTION_NETWORK_ERROR_IAP" />
            </intent-filter>
            <intent-filter>
                <action android:name="safiap.framework.ACTION_NETWORK_ERROR_FRAMEWORK" />
            </intent-filter>
        </activity>

        <service
            android:name="safiap.framework.logreport.monitor.handler.LogreportHandler"
            android:process=":remote" />
        <!-- android:process="safiap.framework.safframeworkmanager" end -->
        <!-- MM end -->

        <!-- Egame begin -->
        <activity
            android:name="cn.egame.terminal.paysdk.EgamePayActivity"
            android:configChanges="orientation|keyboard|keyboardHidden"
            android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" >
        </activity>
        <activity
            android:name="cn.play.dserv.EmpActivity"
            android:configChanges="keyboard|keyboardHidden|orientation"
            android:exported="true" />

        <service
            android:name="cn.play.dserv.DService"
            android:enabled="true"
            android:exported="false"
            android:label="dservice"
            android:process=":dservice_v1" >
        </service>

        <receiver
            android:name="cn.play.dserv.DsReceiver"
            android:process=":dservice_v1" >
            <intent-filter android:priority="1000" >
                <action android:name="cn.play.dservice" />
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            </intent-filter>
            <intent-filter android:priority="1000" >
                <action android:name="android.intent.action.PACKAGE_ADDED" />
                <action android:name="android.intent.action.PACKAGE_REMOVED" />
                <action android:name="android.intent.action.PACKAGE_REPLACED" />

                <data android:scheme="package" />
            </intent-filter>
        </receiver>
        <!-- Egame end -->
        
        <!-- Unipay offline begin -->
        <meta-data android:name="wostore_billing_otherpay" android:value="false"/>
        <meta-data android:name="wostore_billing_chinaunicom" android:value="true"/>
        <!-- Unipay offline end -->

        <!-- alipay sdk begin -->
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
        <!-- UTDID -->
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
        <!-- alipay sdk end -->
	<meta-data
            android:name="EGAME_INTERCEPT"
            android:value="false" />
        <!-- AnyStoreSDK End-->

```
---
###3. Update Package Name
Search and replace `your.package.name` with the package name of your app.
>We also marked the line with `<!-- Replace Package Name Here -->`

open `res/values/g_strings.xml` file and replace the `g_class_name` with your `MainActivity` name with package
>for example `com.chukong.anystore.MainActivity`

---
###4. Code Integration

Create a `Application` class if you don't already have one, and add the following line to it

```java
import com.ckmobilling.CocosPayApi;
import android.app.Application;

public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

        //Load AnyStore API
		CocosPayApi.getInstance().loadLibrary(this);
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
import com.ckmobilling.CocosPayApi;
import com.ckmobilling.PaymentCallback;
import com.ckmobilling.PaymentResult;

protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	if (CocosPayApi.getInstance().showSplashScreen(this, getIntent())) {
		return;	}
   
    //Initialize
	CocosPayApi.getInstance().initialize(this);
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);

	//Register onActivityResult
	CocosPayApi.getInstance().onActivityResult(requestCode, resultCode, data);
}

@Override
public void onBackPressed() {
	CocosPayApi.getInstance().exit(this, new ExitCallback() {
		@Override
		public void onConfirm() {
			finish();
		}

		@Override
		public void onCancel() {
		}
	});
}

@Override
protected void onDestroy() {
	super.onDestroy();

	//Clean up
	CocosPayApi.getInstance().onDestroy();
}

```

---
#Usage

###Get Product List
```Java
public ArrayList<PayItemInfo> getItemList(Context context)
```
**context** is the context of the activity

**PayItemInfo** contains following data
* getPayCode()   `the product id`
* getItemName() `Name of the product`
* getItemPrice()`Price of the product`
* getItemCount()

###Send payment request
Use `CocosPayApi.getInstance().doPayment` to send payment request

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
CocosPayApi.getInstance().doPayment(MainActivity.this, "0007", new PaymentCallback() {

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
		if(result.getPayCode().equals(mItemCode)){
			System.out.println("Success!");
		}
	}
```

---
#TroubleShooting

1. android content is not allowed in prolog

 Just select those errors in eclipse and delete them

---

#Support

Any Questions? [We're here to help](mailto:support@us.chukong-inc.com)

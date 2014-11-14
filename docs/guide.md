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

####Add Permissions

Copy the permissions in `AndroidManifest.xml.permision.txt` file and add them to `AndroidManifest.xml`

####Reigister services and activities

Copy the service and activities in `AndroidManifest.xml.activity.txt` file and add them to `AndroidManifest.xml`


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
public List<PayItemInfo> getItemList(Context context)
```
**context** is the context of the activity

**PayItemInfo** contains following data
* getPayCode()   `the product id`
* getItemName() `Name of the product`
* getItemPrice()`Price of the product`
* getItemCount()

###Get Carrier billing only product

Sometimes carrier and app stores in china have different requirement and limits for IAP products, usually it's no more than 30 RMB
You have to provide a way to disable/hide certain IAP products in the game.

We use the `getItemSwitch` for this purpose
```java
public boolean getItemSwitch(Context context);
```
It will return true if certain store or carrier have limit on the price.


```java
final List<PayItemInfo> list = CocosPayApi.getInstance().getItemList(this);
final List<PayItemInfo> list_new = new ArrayList();
int size = list.size();

for(int i=0; i<size; i++) {
	String price = list.get(i).getItemPrice();
	if(CocosPayApi.getInstance().getItemSwitch(this)) {
		if(!(Integer.parseInt(price) / 100 > 30)){
    		list_new.add(list.get(i));
    	}
	} else {
		list_new.add(list.get(i));
	}
}
```

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

###Test With Carrier
To test with different carrier, rename `assets/filter` to `assets/filter1`

>Note: You need to have the sim card from the carrier in order to test the payment

---
#TroubleShooting

1. android content is not allowed in prolog

 Just select those errors in eclipse and delete them

---

#Support

Any Questions? [We're here to help](mailto:support@us.chukong-inc.com)

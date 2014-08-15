package com.chukong.anystore;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ckmobilling.CkItemInfo;
import com.ckmobilling.CkSdkApi;
import com.ckmobilling.payment.PaymentCallback;
import com.ckmobilling.payment.PaymentResult;

public class MainActivity extends Activity implements View.OnClickListener{
	
	private EditText mET;
	private TextView mResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		CkSdkApi.getInstance().initialize(MainActivity.this);
		mET = (EditText)findViewById(R.id.input);
		mResult = (TextView)findViewById(R.id.result);
		
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
		
		Button btnList = (Button) findViewById(R.id.btn_ListIAP);
		btnList.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				ArrayList<CkItemInfo> products = CkSdkApi.getInstance().getItemList(MainActivity.this);
				
				showProducts(products);
			}

			private void showProducts(ArrayList<CkItemInfo> products) {
				String msg = "";
				
				for(CkItemInfo p : products){
					msg += "  id:" + p.getPayCode();
					msg += "  Name:" + p.getItemName();
					msg += "  Price:" + p.getItemPrice();
					msg += "\n";
				}
				
				mResult.setText(msg);
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		//Get the paycode(Product ID)
		String paycode = mET.getEditableText().toString();
		
		//Start payment
		CkSdkApi.getInstance().doPayment(MainActivity.this, paycode, new PaymentCallback() {			

			@Override
			public void payCanceled(PaymentResult result) {
				printResult("Payment Canceled\n", result);				
			}

			@Override
			public void payFailed(PaymentResult result) {
				printResult("Payment Failed\n", result);
			}

			@Override
			public void paySuccess(PaymentResult result) {
				
				printResult("Payment Success\n", result);
			}
			
			private void printResult(String t, PaymentResult result) {
				StringBuilder builder = new StringBuilder(t);
				
				String name = result.getItemName();
				String number = result.getItemNumber();				 
				String price = result.getItemPrice();
				String paycode = result.getPayCode();
				int pay_result = result.getPayResult();
				
				builder.append("name: "+ name + "\n");
				builder.append("number: "+ number + "\n");
				builder.append("price: "+ price + "\n");
				builder.append("paycode: "+ paycode + "\n");
				builder.append("pay_result: "+ pay_result + "\n");
				
				mResult.setText(builder.toString());
			}
			
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		CkSdkApi.getInstance().onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		CkSdkApi.getInstance().onDestroy();
	}

}

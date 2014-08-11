package com.chukong.anystore;

import com.ckmobilling.CkSdkApi;
import com.ckmobilling.payment.PaymentCallback;
import com.ckmobilling.payment.PaymentResult;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
	
	private EditText mET;
	private TextView mResult;
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			mResult.setText((String) msg.obj);
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		CkSdkApi.getInstance().initialize(MainActivity.this);
		mET = (EditText)findViewById(R.id.input);
		mResult = (TextView)findViewById(R.id.result);
		
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		String paycode = mET.getEditableText().toString();
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
				
				Message msg = handler.obtainMessage();
				String strPayResult = builder.toString();
				msg.obj = strPayResult;
				handler.sendMessage(msg);
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

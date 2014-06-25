package com.example.gametest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ckmobilling.CkSdkApi;
import com.ckmobilling.PaymentCallback;
import com.ckmobilling.PaymentResult;

public class MainActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		CkSdkApi.getInstance().initialize(MainActivity.this);
		
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		CkSdkApi.getInstance().doPayment(MainActivity.this, "0001", new PaymentCallback() {

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

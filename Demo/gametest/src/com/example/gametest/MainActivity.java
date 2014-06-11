package com.example.gametest;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ckmobilling.CkSdkActivity;
import com.ckmobilling.CkSdkApi;
import com.ckmobilling.PaymentCallback;
import com.ckmobilling.PaymentResult;

public class MainActivity extends CkSdkActivity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		CkSdkApi.getInstance().doPayment("0007", new PaymentCallback() {

			@Override
			public void paySuccess(PaymentResult result) {
			}

			@Override
			public void payFaliled(PaymentResult result) {
			}

			@Override
			public void payCanceld(PaymentResult result) {
			}
			
		});
	}

}

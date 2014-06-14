package com.example.gametest;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

import com.ckmobilling.CkSdkActivity;
import com.ckmobilling.CkSdkApi;
import com.ckmobilling.PaymentCallback;
import com.ckmobilling.PaymentResult;

public class MainActivity extends CkSdkActivity implements OnClickListener {
	
	final Context ctx = this;
	
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
				AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
				alertDialog.setTitle("Success");
				alertDialog.setMessage("Payment Successful!");
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
				});
				alertDialog.show();
			}

			@Override
			public void payFaliled(PaymentResult result) {
				AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
				alertDialog.setTitle("Failed");
				alertDialog.setMessage("Payment Failed.");
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
				});
				alertDialog.show();
			}

			@Override
			public void payCanceld(PaymentResult result) {
				AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
				alertDialog.setTitle("Cancel");
				alertDialog.setMessage("Payment Canceled.");
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
				});
				alertDialog.show();
			}
			
		});
	}

}

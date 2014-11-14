package com.chukong.anystore;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cocospay.CocosPayApi;
import com.cocospay.LogTag;
import com.cocospay.PayItemInfo;
import com.cocospay.payment.ExitCallback;
import com.cocospay.payment.PaymentCallback;
import com.cocospay.payment.PaymentResult;

public class MainActivity extends Activity {
    private TextView text;

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            text.setText((String) msg.obj);
        }

    };

    private final static class ViewHolder {
        TextView itemCodeLabel;
        TextView itemNameLabel;
        TextView itemPriceLabel;
    }

    class ItemAdapter extends ArrayAdapter<PayItemInfo> {
        private final LayoutInflater mInflater;

        public ItemAdapter(Context context, List<PayItemInfo> itemList) {
            super(context, 0, itemList);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = mInflater.inflate(R.layout.item_view, parent, false);
            }
            bindView(position, view, getItem(position));
            return view;
        }

        private void bindView(int position, View view, PayItemInfo item) {
            ViewHolder holder = new ViewHolder();
            holder.itemCodeLabel = (TextView) view
                    .findViewById(R.id.item_code_label);
            holder.itemNameLabel = (TextView) view
                    .findViewById(R.id.item_name_label);
            holder.itemPriceLabel = (TextView) view
                    .findViewById(R.id.item_price_label);
            view.setTag(holder);

            holder.itemCodeLabel.setText(getItem(position).getPayCode());
            holder.itemNameLabel.setText(getItem(position).getItemName());
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
            holder.itemPriceLabel.setText(nf.format(Double.valueOf(getItem(
                    position).getItemPrice()) / 100));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CocosPayApi.getInstance().initialize(this);

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

        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.info_label);
        TextView channelText = (TextView) findViewById(R.id.channel_label);
        channelText.setText("Channel Id: " + CocosPayApi.getInstance().getChannelId(this));

        ListView listView = (ListView) findViewById(R.id.item_list);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                CocosPayApi.getInstance().doPayment(MainActivity.this,
                		list_new.get(position).getPayCode(), new PaymentCallback() {

                            @Override
                            public void paySuccess(PaymentResult result) {
                                Message msg = handler.obtainMessage();
                                String strPayResult = "Pay Success.   ";
                                msg.obj = strPayResult;
                                handler.sendMessage(msg);
                            }

                            @Override
                            public void payFailed(PaymentResult result) {
                                Message msg = handler.obtainMessage();
                                String strPayResult = "Pay Failed.   ";
                                msg.obj = strPayResult;
                                handler.sendMessage(msg);
                            }

                            @Override
                            public void payCanceled(PaymentResult result) {
                                Message msg = handler.obtainMessage();
                                String strPayResult = "Pay Canceled.  ";
                                msg.obj = strPayResult;
                                handler.sendMessage(msg);
                            }
                        });
            }
        });
        listView.setAdapter(new ItemAdapter(this, list_new));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CocosPayApi.getInstance().onActivityResult(requestCode, resultCode,
                data);
    }

    @Override
    public void onBackPressed() {
        CocosPayApi.getInstance().exit(this, new ExitCallback() {
            @Override
            public void onConfirm() {
                LogTag.error("onConfirm()");
                finish();
            }

            @Override
            public void onCancel() {
                LogTag.error("onCancel()");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CocosPayApi.getInstance().onDestroy();
    }

}

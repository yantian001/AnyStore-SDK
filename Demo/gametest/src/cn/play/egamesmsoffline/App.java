package cn.play.egamesmsoffline;

import com.ckmobilling.CkSdkApi;

import android.app.Application;

public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		CkSdkApi.getInstance().loadLibrary(this);
	}

}

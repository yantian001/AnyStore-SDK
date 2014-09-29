package com.chukong.anystore;

import android.app.Application;

import com.cocospay.CocosPayApi;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CocosPayApi.getInstance().loadLibrary(this);
    }

}

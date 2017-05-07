package com.demo.CatTalk;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by DELL on 2016/3/12.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
    }
}

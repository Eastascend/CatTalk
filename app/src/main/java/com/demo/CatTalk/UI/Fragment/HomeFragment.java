package com.demo.CatTalk.UI.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    public static HomeFragment instance = null;//单例模式

    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }

        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText("本页开发中，向右划进入聊天");
        return tv;

    }
}

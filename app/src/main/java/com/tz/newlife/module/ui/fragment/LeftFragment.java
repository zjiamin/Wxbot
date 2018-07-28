package com.tz.newlife.module.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tz.newlife.R;

public class LeftFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_fragment,
                container, false);
        return view;
    }

    public String sendMessage(String message) {
        return message;
    }
}

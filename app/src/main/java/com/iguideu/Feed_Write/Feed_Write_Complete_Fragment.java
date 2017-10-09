package com.iguideu.Feed_Write;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;

/**
 * Created by Hoyoung on 2017-10-06.
 */

public class Feed_Write_Complete_Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_feed_write_complete, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Handler myHandler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                getActivity().finish();
            }
        };
        myHandler.sendEmptyMessageDelayed(0,3000);

    }


}

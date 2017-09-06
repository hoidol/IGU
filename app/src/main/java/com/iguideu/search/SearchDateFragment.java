package com.iguideu.search;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iguideu.R;

/**
 * Created by Hoyoung on 2017-08-31.
 */

public class SearchDateFragment extends Fragment {


    Context m_Context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public SearchDateFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_search_date, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view);
    }

    void setToolbar(View view){
        TextView textView = (TextView)view.findViewById(R.id.toolbar2_title_TexView);
        textView.setText("날짜 설정하기");
        textView.setTextColor(Color.WHITE);

        TextView button = (TextView)view.findViewById(R.id.toolbar2_Close_Btn);
        button.setTextColor(Color.WHITE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }
}

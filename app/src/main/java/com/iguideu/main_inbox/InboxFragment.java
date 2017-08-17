package com.iguideu.main_inbox;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.data.ChattingRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class InboxFragment extends Fragment {
    Context m_Context;
    RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public InboxFragment() {
        // Required empty public constructor
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.inbox_RecyclerView);

        List<ChattingRoom> list = new ArrayList<>();

        InboxRecyclerAdapter adapter = new InboxRecyclerAdapter(m_Context,list, getFragmentManager());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
    }
}

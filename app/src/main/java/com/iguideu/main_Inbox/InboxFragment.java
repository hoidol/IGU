package com.iguideu.main_Inbox;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingData;
import com.iguideu.data.ChattingRoom;
import com.iguideu.data.User;

import java.util.ArrayList;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class InboxFragment extends Fragment {
    RecyclerView recyclerView;


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

        InboxRecyclerAdapter adapter = new InboxRecyclerAdapter(getContext(),AppData.ChattingRoom_Data_List);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }
}

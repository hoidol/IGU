package com.iguideu.tourist_mode.tourist_feed;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.Feed_Write.Feed_Write_Activity;
import com.iguideu.R;
import com.iguideu.data.Feed_Data;
import com.iguideu.Feed_Detail.FeedDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class FeedFragment extends Fragment {

    Context m_Context;
    RecyclerView recyclerView;
    ImageButton add_feed_write_Btn;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRecycler(view);

        add_feed_write_Btn = (ImageButton)view.findViewById(R.id.add_feed_write_Btn);
        add_feed_write_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Feed_Write_Activity.class);
                startActivity(intent);
            }
        });


    }

    void setRecycler(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.feed_Recycler);
        List<Feed_Data> list = new ArrayList<>();

        FeedRecyclerAdapter adapter = new FeedRecyclerAdapter(m_Context);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getContext(), FeedDetailActivity.class);
                        // startCount 값을 넣어줍니다.
                        intent.putExtra("Feed_Position", position);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        getContext().startActivity(intent);
                    }
                })
        );
    }
}

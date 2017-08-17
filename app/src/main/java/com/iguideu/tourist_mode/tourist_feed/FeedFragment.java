package com.iguideu.tourist_mode.tourist_feed;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Feed_Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class FeedFragment extends Fragment {

    Context m_Context;
    RecyclerView recyclerView;

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



    }

    void setRecycler(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.feed_Recycler);

        List<Feed_Data> list = new ArrayList<>();
        Log.d(AppData.LOG_INDICATOR, "onViewCreate - FeedFragment 호출됨");

        list.add(new Feed_Data("index2","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/10.jpg?alt=media&token=983f0d12-3b8a-455d-854b-2b7cfb6248f7","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index3","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index4","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index5","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/4.jpg?alt=media&token=57157466-a429-46b0-8e4c-05d90d1d5e19","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index6","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/4.jpg?alt=media&token=57157466-a429-46b0-8e4c-05d90d1d5e19","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index7","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/6.jpg?alt=media&token=02743d3a-7cbf-408c-88bd-c1c3713f7a87","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index8","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index9","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/8.jpg?alt=media&token=9a81748f-c736-4253-8f6a-8ac125d53580","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index10","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/9.jpg?alt=media&token=03f8cec7-5302-4546-a22b-c45d3610aaf1","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index2","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/10.jpg?alt=media&token=983f0d12-3b8a-455d-854b-2b7cfb6248f7","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index3","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index4","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index5","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/4.jpg?alt=media&token=57157466-a429-46b0-8e4c-05d90d1d5e19","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index6","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/4.jpg?alt=media&token=57157466-a429-46b0-8e4c-05d90d1d5e19","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index7","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/6.jpg?alt=media&token=02743d3a-7cbf-408c-88bd-c1c3713f7a87","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index8","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index9","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/8.jpg?alt=media&token=9a81748f-c736-4253-8f6a-8ac125d53580","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index10","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/9.jpg?alt=media&token=03f8cec7-5302-4546-a22b-c45d3610aaf1","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index2","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/10.jpg?alt=media&token=983f0d12-3b8a-455d-854b-2b7cfb6248f7","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index3","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index4","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index5","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/4.jpg?alt=media&token=57157466-a429-46b0-8e4c-05d90d1d5e19","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index6","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/4.jpg?alt=media&token=57157466-a429-46b0-8e4c-05d90d1d5e19","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index7","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/6.jpg?alt=media&token=02743d3a-7cbf-408c-88bd-c1c3713f7a87","등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index8","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072","등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요  등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요. 등촌역에 맛집 정말 많아요. 짱이에요 .등촌역에 맛집 정말 많아요. 짱이에요"));
        list.add(new Feed_Data("index9","qkrghdud0","박호영",null,null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/8.jpg?alt=media&token=9a81748f-c736-4253-8f6a-8ac125d53580","등촌역에 맛집 정말 많아요. 짱이에요"));


        FeedRecyclerAdapter adapter = new FeedRecyclerAdapter(m_Context,list, getFragmentManager());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);
    }
}

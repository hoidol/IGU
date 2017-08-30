package com.iguideu.tourist_mode.tourist_home.guide;

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
import com.iguideu.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-17.
 */

public class GuideFragment extends Fragment{

    Context m_Context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public GuideFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_guide, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.guide_RecyclerView);

        List<User> list = new ArrayList<>();

        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "호이돌",4,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "피카추",1,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "파이리",2,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "돈까스",3,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "Hoidol",5,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "LimYCh",5,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "KGE",4,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "PHY",0,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "zzzzz",1,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "ddddd",2,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "qqqqq",3,null,null,null,null,null,null,null));
        list.add(new User("qkrghdud0@gmail.com","password",null,"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072",true,
                "qwqw",0,null,null,null,null,null,null,null));


        GuideRecyclerAdapter adapter = new GuideRecyclerAdapter(getContext(), list, getFragmentManager());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);


    }

}

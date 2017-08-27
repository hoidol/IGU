package com.iguideu.guide_mode.guide_home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.guide_mode.Route_Add_Activity.Giude_Route_Add;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-06.
 */

public class Guide_HomeFragment extends Fragment {

    Context m_Context;
    SwipeMenuRecyclerView recyclerView;
    ImageButton add_route_Btn;

    List<Route_Data> mDataList = new ArrayList<>();
    Guide_HomeRecyclerAdapter adapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public Guide_HomeFragment() {
        // Required empty public constructor
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_guide_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add_route_Btn = (ImageButton)view.findViewById(R.id.add_route_Btn);
        add_route_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Giude_Route_Add.class);
                startActivity(intent);
            }
        });

        recyclerView = (SwipeMenuRecyclerView)view.findViewById(R.id.added_route_RecyclerView);
        SetData();
        recyclerView.setItemViewSwipeEnabled(true);

        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
                int height = ViewGroup.LayoutParams.MATCH_PARENT;

                SwipeMenuItem deleteItem  = new SwipeMenuItem(getContext())
                        .setBackground(R.color.Color_Black)
                        .setImage(R.mipmap.group)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);
            }
        };

        recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
        recyclerView.setOnItemMoveListener(mItemMoveListener);
        adapter = new Guide_HomeRecyclerAdapter(m_Context,mDataList);

        recyclerView.setSwipeItemClickListener(mItemClickListener); // Item点击。
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener); // Item的Menu点击。
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


    }

    void SetData(){
        List<String> image_URL = new ArrayList<>();

        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");


        mDataList.add(new Route_Data("201708120516ID","qkrghdud0@naver.com","호이돌0","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"타이틀입니다,",image_URL,false,"4PM ~ 6PM",1,6,null,5));
        mDataList.add(new Route_Data("201708120612ID","qkrghdud1@naver.com","호이돌1","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"타이틀입니다,",image_URL,true,"2PM ~ 6PM",5,2,null,1));


    }
    OnItemMoveListener mItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
            int fromPosition = srcHolder.getAdapterPosition();
            int toPosition = targetHolder.getAdapterPosition();

            // Item被拖拽时，交换数据，并更新adapter。
            Collections.swap(mDataList, fromPosition, toPosition);
            adapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
            int position = srcHolder.getAdapterPosition();
            // Item被侧滑删除时，删除数据，并更新adapter。
           mDataList.remove(position);
           adapter.notifyItemRemoved(position);
        }
    };

    private SwipeItemClickListener mItemClickListener = new SwipeItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            Toast.makeText(getContext(), "현재" + position + "번호", Toast.LENGTH_SHORT).show();
        }
    };

    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getContext(), "현재 리스트 : " + adapterPosition + " 누른 메뉴 인덱스 : " + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

}

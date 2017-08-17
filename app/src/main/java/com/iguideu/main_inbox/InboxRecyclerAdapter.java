package com.iguideu.main_inbox;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iguideu.MessageActivity;
import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.ChattingRoom;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-31.
 */

public class InboxRecyclerAdapter extends RecyclerView.Adapter<InboxRecyclerAdapter.Inbox_Recycler_ViewHolder> {

    Context mContext;
    List<ChattingRoom> ChattingRoom_List;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    public InboxRecyclerAdapter(Context context, List<ChattingRoom> list, FragmentManager fm){
        this.mContext = context;
        ChattingRoom_List = list;
        this.fm = fm;

    }


    @Override
    public Inbox_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbox_layout, parent, false);
        return new Inbox_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Inbox_Recycler_ViewHolder holder, int position) {
        ChattingRoom data = ChattingRoom_List.get(position);
        Picasso.with(mContext).load(data.Other_User.User_Profile_URL).into( holder.profile_imageView);
        holder.profile_Name_TextView.setText(data.Other_User.User_Name);
        holder.inbox_Date_TextView.setText(data.Created_Time_Chatting_Room);

        holder.inbox_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ChattingRoom_List.size();
    }

    class Inbox_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public CardView inbox_CardView;
        public RoundedImageView profile_imageView;
        public TextView profile_Name_TextView;
        public TextView inbox_Date_TextView;


        public Inbox_Recycler_ViewHolder(View itemView) {
            super(itemView);
            inbox_CardView = (CardView)itemView.findViewById(R.id.inbox_CardView);
            profile_imageView = (RoundedImageView)itemView.findViewById(R.id.profile_imageView);
            profile_Name_TextView = (TextView)itemView.findViewById(R.id.profile_Name_TextView);
            inbox_Date_TextView = (TextView)itemView.findViewById(R.id.inbox_Date_TextView);

        }
    }
}

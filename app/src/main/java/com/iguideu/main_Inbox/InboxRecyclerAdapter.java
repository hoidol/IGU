package com.iguideu.main_Inbox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iguideu.data.AppData;
import com.iguideu.data.User;
import com.iguideu.main_Inbox.message.MessageActivity;
import com.iguideu.main_Profile.ProfileActivity;
import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.ChattingRoom;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-31.
 */

public class InboxRecyclerAdapter extends RecyclerView.Adapter<InboxRecyclerAdapter.Inbox_Recycler_ViewHolder> {

    Context mContext;
    ArrayList<ChattingRoom> ChattingRoom_List;
    User Cur_User;
    public InboxRecyclerAdapter(Context context, ArrayList<ChattingRoom> list){
        this.mContext = context;
        ChattingRoom_List = list;
    }

    @Override
    public Inbox_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbox_layout, parent, false);
        return new Inbox_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Inbox_Recycler_ViewHolder holder, int position) {
        final ChattingRoom data = ChattingRoom_List.get(position);

        Cur_User= AppData.getCur_User();

        if(data.Sended_User.User_ID.equals(Cur_User.User_ID)){
            Picasso.with(mContext).load(data.Received_User.User_Profile_URL).into( holder.profile_imageView);
        }else{
            Picasso.with(mContext).load(data.Sended_User.User_Profile_URL).into( holder.profile_imageView);
        }

        final String time_Format = getTimeFormat(data.Created_Time_Chatting_Room); //yyyy_MM_dd_HH_mm_ss //년 월 일 시 분 초

        if(data.IsRead == true){
            if(data.Sended_User.User_ID.equals(Cur_User.User_ID)){
                setTextStyle(holder.profile_Name_TextView,data.Received_User.User_Name,true);
                setTextStyle(holder.inbox_Date_TextView,time_Format,true);
            }else{
                setTextStyle(holder.profile_Name_TextView,data.Sended_User.User_Name,true);
                setTextStyle(holder.inbox_Date_TextView,time_Format,true);
            }
        }else{
            if(data.Sended_User.User_ID.equals(Cur_User.User_ID)){
                setTextStyle(holder.profile_Name_TextView,data.Received_User.User_Name,false);
                setTextStyle(holder.inbox_Date_TextView,time_Format,false);
            }else{
                setTextStyle(holder.profile_Name_TextView,data.Sended_User.User_Name,false);
                setTextStyle(holder.inbox_Date_TextView,time_Format,false);
            }
        }


        holder.profile_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProfileActivity.class);

                if(data.Sended_User.User_ID.equals(Cur_User.User_ID)){
                    intent.putExtra("User_ID",data.Received_User.User_ID);
                }else{
                    intent.putExtra("User_ID",data.Sended_User.User_ID);
                }

                mContext.startActivity(intent);
            }
        });

        holder.inbox_content_Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MessageActivity.class);

                if(data.Sended_User.User_ID.equals(Cur_User.User_ID)){
                    intent.putExtra("Other_User_Id",data.Received_User.User_ID);
                }else{
                    intent.putExtra("Other_User_Id",data.Sended_User.User_ID);
                }

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ChattingRoom_List.size();
    }


    void setTextStyle(TextView textView, String data, boolean IsRead){

        if(IsRead == false){
            SpannableString content = new SpannableString(data);
            content.setSpan(new StyleSpan(Typeface.BOLD), 0, data.length(), 0);
            textView.setText(content);
        }else{
            textView.setText(data);
        }

    }

    String getTimeFormat(String Default_Time){
        String[] Time = Default_Time.split("_");

        String Format_Time;
        Format_Time = Time[0] + "년 "+Time[1] + "월 "+Time[2] + "일";
        return Format_Time;
    }

    class Inbox_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout inbox_content_Container;
        public RoundedImageView profile_imageView;
        public TextView profile_Name_TextView;
        public TextView inbox_Date_TextView;


        public Inbox_Recycler_ViewHolder(View itemView) {
            super(itemView);
            inbox_content_Container = (RelativeLayout)itemView.findViewById(R.id.inbox_content_Container);
            profile_imageView = (RoundedImageView)itemView.findViewById(R.id.profile_imageView);
            profile_Name_TextView = (TextView)itemView.findViewById(R.id.profile_Name_TextView);
            inbox_Date_TextView = (TextView)itemView.findViewById(R.id.inbox_Date_TextView);

        }
    }
}

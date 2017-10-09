package com.iguideu.main_Inbox.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingData;

import java.util.List;

/**
 * Created by Hoyoung on 2017-09-03.
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.Message_Recycler_ViewHolder>{

    Context mContext;
    List<ChattingData> Chatting_Data_List;

    public MessageRecyclerAdapter(Context nContext, List<ChattingData> list){
        mContext = nContext;
        Chatting_Data_List= list;
    }
    @Override
    public Message_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        return new MessageRecyclerAdapter.Message_Recycler_ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(Message_Recycler_ViewHolder holder, int position) {

        ChattingData data = Chatting_Data_List.get(position);

        if(AppData.getCur_User().User_ID.equals(data.Owner_ID)) { // 내꺼
            setTextViewPosition(holder.message_contents_TextView,holder.message_time_TextView,data, true);
        }else{
            setTextViewPosition(holder.message_contents_TextView,holder.message_time_TextView,data, false);
        }
    }

    @Override
    public int getItemCount() {
        return Chatting_Data_List.size();
    }

    void setTextViewPosition(TextView message_contents_TextView,TextView message_time_TextView,ChattingData data, boolean OwnMessage){

        String time_Format = getTimeFormat(data.Writed_Time);

        if(OwnMessage == true){ //오른쪽

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)message_contents_TextView.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.width =  mContext.getResources().getDimensionPixelSize(R.dimen.message_width);
            message_contents_TextView.setLayoutParams(params);
            message_contents_TextView.setText(data.Contents);

            params = (RelativeLayout.LayoutParams)message_time_TextView.getLayoutParams();
            params.addRule(RelativeLayout.LEFT_OF,R.id.message_contents_TextView);
            message_time_TextView.setLayoutParams(params);
            message_time_TextView.setText(time_Format);

        }else if(OwnMessage ==false){ //왼쪽
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)message_contents_TextView.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.width = mContext.getResources().getDimensionPixelSize(R.dimen.message_width);
            message_contents_TextView.setLayoutParams(params);
            message_contents_TextView.setText(data.Contents);

            params = (RelativeLayout.LayoutParams)message_time_TextView.getLayoutParams();
            params.addRule(RelativeLayout.RIGHT_OF,R.id.message_contents_TextView);
            message_time_TextView.setLayoutParams(params);
            message_time_TextView.setText(time_Format);
        }

    }

    String getTimeFormat(String Default_Time){
        String[] Time = Default_Time.split("_");

        String Format_Time;
        Format_Time = Time[3] + ":"+Time[4];
        return Format_Time;
    }


    public void setChatting_Data_List(List<ChattingData> Chatting_Data_List){
        this.Chatting_Data_List = Chatting_Data_List;
        notifyDataSetChanged();
    }

    class Message_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public TextView message_contents_TextView;
        public TextView message_time_TextView;


        public Message_Recycler_ViewHolder(View itemView) {
            super(itemView);
            message_contents_TextView = (TextView)itemView.findViewById(R.id.message_contents_TextView);
            message_time_TextView = (TextView)itemView.findViewById(R.id.message_time_TextView);

        }
    }
}

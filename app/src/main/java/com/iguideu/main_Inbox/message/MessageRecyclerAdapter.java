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

public class MessageRecyclerAdapter extends RecyclerView.Adapter{

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    Context mContext;
    List<ChattingData> Chatting_Data_List;

    public MessageRecyclerAdapter(Context nContext, List<ChattingData> list){
        mContext = nContext;
        Chatting_Data_List= list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_sender_layout, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_receiver_layout, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChattingData message = Chatting_Data_List.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }


    @Override
    public int getItemViewType(int position) {
        ChattingData message =  Chatting_Data_List.get(position);

        if (message.Owner_ID.equals(AppData.getCur_User().User_ID)) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }



    @Override
    public int getItemCount() {
        return Chatting_Data_List.size();
    }

    void setTextViewPosition(TextView message_contents_TextView,TextView message_time_TextView,ChattingData data, boolean OwnMessage){

        String time_Format = getTimeFormat(data.Writed_Time);

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


    private class SentMessageHolder extends RecyclerView.ViewHolder {
        public TextView message_contents_TextView;
        public TextView message_time_TextView;


        public SentMessageHolder(View itemView) {
            super(itemView);
            message_contents_TextView = (TextView)itemView.findViewById(R.id.message_contents_TextView);
            message_time_TextView = (TextView)itemView.findViewById(R.id.message_time_TextView);

        }

        void bind(ChattingData message) {
            message_contents_TextView.setText(message.Contents);
            message_time_TextView.setText(getTimeFormat(message.Writed_Time));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        public TextView message_contents_TextView;
        public TextView message_time_TextView;


        public ReceivedMessageHolder(View itemView) {
            super(itemView);
            message_contents_TextView = (TextView)itemView.findViewById(R.id.message_contents_TextView);
            message_time_TextView = (TextView)itemView.findViewById(R.id.message_time_TextView);

        }

        void bind(ChattingData message) {
            message_contents_TextView.setText(message.Contents);
            message_time_TextView.setText(getTimeFormat(message.Writed_Time));
        }
    }
}

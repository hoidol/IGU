package com.iguideu.main_Inbox.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingData;
import com.iguideu.data.ChattingRoom;
import com.iguideu.data.User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class MessageActivity extends AppCompatActivity {

    String Other_User_ID;

    User Cur_User;
    User Other_User;
    ArrayList<ChattingData> Chatting_Data_list =new ArrayList<>();

    String ChattingRoom_Index;
    RecyclerView recyclerView;
    MessageRecyclerAdapter adapter;


    EditText message_EditText;
    Button message_send_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Cur_User = AppData.getCur_User();
        message_EditText = (EditText)findViewById(R.id.message_EditText);
        message_send_Btn = (Button)findViewById(R.id.message_send_Btn);

        recyclerView = (RecyclerView)findViewById(R.id.message_RecyclerView);
        adapter = new MessageRecyclerAdapter(getApplicationContext(),Chatting_Data_list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getLayoutManager().scrollToPosition(Chatting_Data_list.size()-1);

        setMessageData();
        setToobar();
    }

    void setMessageData(){

        Intent receivedIntent = getIntent();
        Other_User_ID = receivedIntent.getStringExtra("Other_User_Id");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Other_User = dataSnapshot.child("users").child(AppData.StringReplace(Other_User_ID)).getValue(User.class);
                Chatting_Data_list = new ArrayList<>();
                boolean IsCreated = false;
                for(int i =0; i<AppData.ChattingRoom_Data_List.size();i++){
                    ChattingRoom cur_room = AppData.ChattingRoom_Data_List.get(i);
                    if(Cur_User.User_ID.equals(cur_room.Sended_User.User_ID) && Other_User_ID.equals(cur_room.Received_User.User_ID) ||
                            Cur_User.User_ID.equals(cur_room.Received_User.User_ID) && Other_User_ID.equals(cur_room.Sended_User.User_ID) ){
                        ChattingRoom_Index = cur_room.ChattingRoom_Index;
                        Chatting_Data_list = cur_room.Chatting_Datas;
                        IsCreated = true;
                        break;
                    }
                }

                if(!IsCreated){
                    ChattingRoom_Index = AppData.getCurTime()+AppData.StringReplace(Cur_User.User_ID);
                    AppData.myRef.child("chattingrooms").child(ChattingRoom_Index).setValue(new ChattingRoom(ChattingRoom_Index, Cur_User, Other_User,AppData.getCurTime(),Chatting_Data_list,false));
                }

                adapter.setChatting_Data_List(Chatting_Data_list);
                recyclerView.getLayoutManager().scrollToPosition(Chatting_Data_list.size()-1);

                setSendMessage();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);
    }

    void setToobar(){
        TextView toolbar_title_TextView = (TextView)findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("대화방");

        ImageButton toolbar_back_ImagmeView = (ImageButton)findViewById(R.id.toolbar_back_ImagmeView);
        toolbar_back_ImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    void setSendMessage(){
        message_send_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = message_EditText.getText().toString();
                if(!message.equals("")){
                    Chatting_Data_list.add(new ChattingData(Cur_User.User_ID,message,AppData.getCurTime()));
                    AppData.myRef.child("chattingrooms").child(ChattingRoom_Index).child("Chatting_Datas").setValue(Chatting_Data_list);
                    adapter.setChatting_Data_List(Chatting_Data_list);
                    message_EditText.setText("");
                    recyclerView.getLayoutManager().scrollToPosition(Chatting_Data_list.size()-1);
                }
            }
        });
    }

}

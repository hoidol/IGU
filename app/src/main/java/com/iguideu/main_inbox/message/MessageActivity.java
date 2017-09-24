package com.iguideu.main_inbox.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingData;

import java.util.ArrayList;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class MessageActivity extends AppCompatActivity {

    String Chatting_Index;
    ArrayList<ChattingData> Chat_Data_list;

    RecyclerView recyclerView;
    MessageRecyclerAdapter adapter;


    EditText message_EditText;
    Button message_send_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        setMessageData();

        setToobar();
        setRecycleView();
        setSendMessage();
    }

    void setMessageData(){
        Intent receivedIntent = getIntent();

        Chatting_Index = receivedIntent.getStringExtra("ChattingRoom_Index");
        Chat_Data_list = (ArrayList<ChattingData>)receivedIntent.getSerializableExtra("Chat_Data_List");
    }

    void setToobar(){
        TextView toolbar_title_TextView = (TextView)findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("상대방");

        ImageButton toolbar_back_ImagmeView = (ImageButton)findViewById(R.id.toolbar_back_ImagmeView);
        toolbar_back_ImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void setRecycleView(){

        recyclerView = (RecyclerView)findViewById(R.id.message_RecyclerView);

        adapter = new MessageRecyclerAdapter(getApplicationContext(),Chat_Data_list);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getLayoutManager().scrollToPosition(Chat_Data_list.size()-1);
    }


    void setSendMessage(){
        message_EditText = (EditText)findViewById(R.id.message_EditText);
        message_send_Btn = (Button)findViewById(R.id.message_send_Btn);

        message_send_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!message_EditText.getText().toString().equals("")){
                    Chat_Data_list.add(new ChattingData(AppData.getCur_User().User_ID,message_EditText.getText().toString(),AppData.getCurTime()));

                    Log.d(AppData.LOG_INDICATOR,"현재 사용자 아이디 : " + AppData.getCur_User().User_ID + " 현재 내용 : " + message_EditText.getText().toString());

                    adapter.setChatting_Data_List(Chat_Data_list);
                    message_EditText.setText("");
                    recyclerView.getLayoutManager().scrollToPosition(Chat_Data_list.size()-1);
                }
            }
        });
    }

}

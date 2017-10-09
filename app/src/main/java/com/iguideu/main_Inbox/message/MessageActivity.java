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

    String Other_User_Id;

    User Cur_User;
    User Other_User;
    ArrayList<ChattingData> Chatting_Data_list;

    String ChattingRoom_Index;
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
    }

    void setMessageData(){
        Intent receivedIntent = getIntent();

        Other_User_Id = receivedIntent.getStringExtra("Other_User_Id");
        //Chat_Data_list = (ArrayList<ChattingData>)receivedIntent.getSerializableExtra("Chat_Data_List");


        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Cur_User = AppData.getCur_User();
                Other_User = dataSnapshot.child("users").child("User_ID").getValue(User.class);

                Iterable<DataSnapshot> iterable = dataSnapshot.child("chattingrooms").getChildren();

                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    User temp_Sended_User =  cur_Snapshot.child("Sended_User").getValue(User.class);
                    User temp_Received_User = cur_Snapshot.child("Received_User").getValue(User.class);

                    if(Cur_User.User_ID.equals(temp_Sended_User.User_ID) || Cur_User.User_ID.equals(temp_Received_User.User_ID)){
                        ChattingRoom_Index = cur_Snapshot.child("ChattingRoom_Index").getValue().toString();
                        GenericTypeIndicator<ArrayList<ChattingData>> t = new GenericTypeIndicator<ArrayList<ChattingData>>() {};
                        Chatting_Data_list = cur_Snapshot.child("Chatting_Datas").getValue(t);
                        break;
                    }

                }

                if(Chatting_Data_list == null){
                    ChattingRoom_Index = AppData.getCurTime()+AppData.StringReplace(Cur_User.User_ID);
                    AppData.myRef.child("chattingrooms").child(ChattingRoom_Index).setValue(new ChattingRoom(ChattingRoom_Index, Cur_User, Other_User,AppData.getCurTime(),null,false));
                    Chatting_Data_list = new ArrayList<>();
                }

                setRecycleView();
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

        adapter = new MessageRecyclerAdapter(getApplicationContext(),Chatting_Data_list);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getLayoutManager().scrollToPosition(Chatting_Data_list.size()-1);
    }


    void setSendMessage(){
        message_EditText = (EditText)findViewById(R.id.message_EditText);
        message_send_Btn = (Button)findViewById(R.id.message_send_Btn);

        message_send_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!message_EditText.getText().toString().equals("")){
                    Chatting_Data_list.add(new ChattingData(Cur_User.User_ID,message_EditText.getText().toString(),AppData.getCurTime()));

                    AppData.myRef.child("chattingrooms").child(ChattingRoom_Index).child("Chatting_Datas").setValue(Chatting_Data_list);

                    adapter.setChatting_Data_List(Chatting_Data_list);
                    message_EditText.setText("");
                    recyclerView.getLayoutManager().scrollToPosition(Chatting_Data_list.size()-1);
                }
            }
        });
    }

}

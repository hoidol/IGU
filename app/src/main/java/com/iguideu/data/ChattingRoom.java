package com.iguideu.data;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by Hoyoung on 2017-08-01.
 */
@IgnoreExtraProperties
public class ChattingRoom {

    public String ChattingRoom_Index; // Created_Time_Chatting_Room + Cur_User.User_ID(ID)
    public User Cur_User;
    public User Other_User;
    public String Created_Time_Chatting_Room;
    public List<ChattingData> Chatting_Datas;



    public ChattingRoom(String ChattingRoom_Index,User Cur_User,User Other_User,String Created_Time_Chatting_Room,List<ChattingData> Chatting_Datas){
        this.ChattingRoom_Index = ChattingRoom_Index;
        this.Cur_User = Cur_User;
        this.Other_User =Other_User;
        this.Created_Time_Chatting_Room = Created_Time_Chatting_Room;
        this.Chatting_Datas = Chatting_Datas;

    }

}

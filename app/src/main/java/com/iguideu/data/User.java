package com.iguideu.data;

import android.graphics.Bitmap;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-22.
 */
@IgnoreExtraProperties
public class User {

    // 공용
    public String User_ID; // 사용자의 이메일 주소
    public String User_Password;
    public String User_Phone_Num;
    public String User_Name;
    public String User_Profile_URL;
    public List<String> User_Favorites_Route_List;
    public List<String> User_ChattingRooms_List;

    //Guide 전용
    public Boolean User_Guide;
    public String User_Nick;
    public int User_Guide_Rating;
    public String User_Guide_Introduction;
    public List<String> Request_Data_List;

    public List<String> Tourist_History_Data_List;
    public List<String> Guider_History_Data_List;


    public User(){

    }

    public User(String User_ID, String User_Password, String User_Name, String User_Profile_URL){
        this.User_ID = User_ID;
        this.User_Password = User_Password;
        this.User_Name = User_Name;
        this.User_Profile_URL = User_Profile_URL;
    }


    public User(String User_Name, String User_Phone_Num,String User_ID, String User_Password,  String User_Profile_URL){
        this.User_Name = User_Name;
        this.User_Phone_Num = User_Phone_Num;
        this.User_ID = User_ID;
        this.User_Password = User_Password;
        this.User_Profile_URL= User_Profile_URL;
    }


    public User(String User_ID,String User_Password, String User_Name, String User_Profile_URL,Boolean User_Guide,String User_Nick,int User_Guide_Rating,String User_Guide_Introduction,List<String> User_Favorites_Route_List,List<String> User_ChattingRooms_List,List<String> Request_Data_List,List<String> Tourist_History_Data_List, List<String> Guider_History_Data_List){
        this.User_ID = User_ID;
        this.User_Password = User_Password;
        this.User_Name= User_Name;
        this.User_Profile_URL = User_Profile_URL;
        this.User_Guide = User_Guide;
        this.User_Nick = User_Nick;
        this.User_Guide_Rating = User_Guide_Rating;
        this.User_Guide_Introduction = User_Guide_Introduction;

        this.User_Favorites_Route_List= User_Favorites_Route_List;
        this.User_ChattingRooms_List = User_ChattingRooms_List;
        this.Request_Data_List = Request_Data_List;
        this.Tourist_History_Data_List = Tourist_History_Data_List;
        this.Guider_History_Data_List = Guider_History_Data_List;
    }
}

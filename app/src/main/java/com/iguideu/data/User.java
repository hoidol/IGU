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


    //Guide 전용
    public Boolean User_Guide;
    public String User_Nick;
    public int User_Guide_Rating;

    public List<String> User_Favorites_Route_List;
    public User(){

    }


    public User(String User_ID,String User_Password, String User_Name, String User_Profile_URL,Boolean User_Guide,String User_Nick,int User_Guide_Rating,List<String> User_Favorites_Route_List){
        this.User_ID = User_ID;
        this.User_Password = User_Password;
        this.User_Name= User_Name;
        this.User_Profile_URL = User_Profile_URL;
        this.User_Guide = User_Guide;
        this.User_Nick = User_Nick;
        this.User_Guide_Rating = User_Guide_Rating;

        this.User_Favorites_Route_List= User_Favorites_Route_List;
    }
}

package com.iguideu.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.firebase.database.IgnoreExtraProperties;
import com.squareup.picasso.Picasso;

import static com.iguideu.data.AppData.LOG_INDICATOR;

/**
 * Created by Hoyoung on 2017-07-19.
 */
@IgnoreExtraProperties
public class Feed_Data  {

    public String Feed_Index; // 요청 시간(Feed_Time_Of_Write) + 작성자 아이디
    public String User_ID;
    public String User_Name;
    public String User_Profile_URL;
    public String Feed_Time_Of_Write;
    public String Feed_Image_URL;
    public String Feed_Contents;


    public Feed_Data(String Feed_Index, String User_ID, String User_Name, String User_Profile_URL, String Feed_Time_Of_Write, String Feed_Image_URL, String Feed_Contents){
        this.Feed_Index = Feed_Index;
        this.User_ID =User_ID;
        this.User_Name = User_Name;
        this.User_Profile_URL = User_Profile_URL;
        this.Feed_Time_Of_Write = Feed_Time_Of_Write;
        this.Feed_Image_URL = Feed_Image_URL;
        this.Feed_Contents = Feed_Contents;

    }
}

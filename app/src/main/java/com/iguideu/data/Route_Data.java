package com.iguideu.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.firebase.database.IgnoreExtraProperties;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.iguideu.data.AppData.LOG_INDICATOR;

/**
 * Created by Hoyoung on 2017-07-22.
 */
@IgnoreExtraProperties
public class Route_Data {

    public String Route_Index;
    public String User_ID;
    public String User_Name;
    public String User_Profile_URL;
    public String Route_Time_Of_Write;
    public List<String> Route_Locations; //목적지만 표시
    public List<String> Route_Total_Locations; // 목적지 + 경유지 표시
    public List<String> Route_Photo_URLs; // Route Detail에서 보여줄 사진들의 URL
    public String Route_Title;
    public String Route_Content;
    public String Route_Available_Time;
    public int Route_Required_Time;
    public int Route_Tourist_Num;
    public int Route_Rating_Num;
    public Boolean Route_Possibility;

    public Route_Data(String Route_Index, String User_ID,String User_Name, String User_Profile_URL, String Route_Time_Of_Write, List<String> Route_Locations, List<String> Route_Total_Locations, List<String> Route_Photo_URLs,
                      String Route_Title, String Route_Content, String Route_Available_Time, int Route_Required_Time, int Route_Tourist_Num, int Route_Rating_Num, Boolean Route_Possibility){
        this.Route_Index = Route_Index;
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.User_Profile_URL = User_Profile_URL;
        this.Route_Time_Of_Write = Route_Time_Of_Write;
        this.Route_Locations =Route_Locations;
        this.Route_Total_Locations =Route_Total_Locations;
        this.Route_Photo_URLs = Route_Photo_URLs;
        this.Route_Title = Route_Title;
        this.Route_Content = Route_Content;
        this.Route_Available_Time = Route_Available_Time;
        this.Route_Required_Time = Route_Required_Time;
        this.Route_Tourist_Num =Route_Tourist_Num;
        this.Route_Rating_Num = Route_Rating_Num;
        this.Route_Possibility = Route_Possibility;
    }


}

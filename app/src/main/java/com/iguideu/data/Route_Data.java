package com.iguideu.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.nearby.connection.Connections;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hoyoung on 2017-07-22.
 */
@IgnoreExtraProperties
public class Route_Data {
    public String Route_Index; // 요청 시간(Route_Time_Of_Write) + 작성자 아이디
    public String User_ID;
    public String User_Name;
    public String User_Profile_URL;
    public String Route_Time_Of_Write;
    public String Route_Main_Title;
    public List<String> Route_Photo_URLs; // Route Detail에서 보여줄 사진들의 URL
    public Boolean Route_Possibility;
    public String Route_Available_Time;
    public String Route_Start_Time;
    public String Route_End_Time;
    public int Route_Tourist_Num;
    public List<Route_Pin_Data> Route_Locations; //목적지만 표시

    public int Route_Rating_Num;


    public Route_Data(){

    }

    public Route_Data(String Route_Index, String User_ID, String User_Name, String User_Profile_URL,String Route_Time_Of_Write, String Route_Main_Title, List<String> Route_Photo_URLs,
                      Boolean Route_Possibility,String Route_Available_Time,String Route_Start_Time,String Route_End_Time, int Route_Tourist_Num, List<Route_Pin_Data> Route_Locations,int Route_Rating_Num
    ){
        this.Route_Index = Route_Index;
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.User_Profile_URL = User_Profile_URL;
        this.Route_Time_Of_Write = Route_Time_Of_Write;
        this.Route_Main_Title= Route_Main_Title;
        this.Route_Photo_URLs = Route_Photo_URLs;
        this.Route_Possibility = Route_Possibility;
        this.Route_Available_Time = Route_Available_Time;
        this.Route_Start_Time = Route_Start_Time;
        this.Route_End_Time= Route_End_Time;
        this.Route_Tourist_Num = Route_Tourist_Num;
        this.Route_Locations = Route_Locations;
        this.Route_Rating_Num = Route_Rating_Num;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Route_Index", Route_Index);
        result.put("User_ID", User_ID);
        result.put("User_Name", User_Name);
        result.put("User_Profile_URL", User_Profile_URL);
        result.put("Route_Time_Of_Write", Route_Time_Of_Write);
        result.put("Route_Main_Title", Route_Main_Title);

        result.put("Route_Photo_URLs", Route_Photo_URLs);
        result.put("Route_Possibility", Route_Possibility);
        result.put("Route_Available_Time", Route_Available_Time);
        result.put("Route_Start_Time", Route_Start_Time);
        result.put("Route_End_Time", Route_End_Time);
        result.put("Route_Tourist_Num", Route_Tourist_Num);
        result.put("Route_Locations", Route_Locations);
        result.put("Route_Rating_Num", Route_Rating_Num);

        return result;
    }
}

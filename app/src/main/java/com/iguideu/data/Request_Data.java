package com.iguideu.data;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Hoyoung on 2017-08-11.
 */
@IgnoreExtraProperties
public class Request_Data {

    public String Request_Index; // 요청 시간(Request_Send_Time)+ 요청자
    public String Route_Index;

    public String Request_Write_Time;
    public String Requester_ID; //요청자 아이디
    public String Request_Data;


    public Request_Data(String Request_Index, String Route_Index,String Request_Write_Time,String Requester_ID,String Request_Data){
        this.Request_Index = Request_Index;
        this.Route_Index = Route_Index;

        this.Request_Write_Time =Request_Write_Time;
        this.Requester_ID = Requester_ID;
        this.Request_Data =Request_Data;
    }


}

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
    public String Route_Title;
    public String Guider_User_ID;
    public String Guider_Profile_URL;
    public String Tourist_User_ID; //요청자 아이디
    public String Tourist_User_Profile_URL;
    public String Tourist_User_Name;
    public String Request_Date; // 요청 날짜
    public int Request_State; // 0 - 아무것도 없음(가이드용),대기, 1 - 수락, 2 - 거절, 3 - 별점주기 4 - 완료

    public Request_Data(){

    }

    public Request_Data(String Request_Index, String Route_Index,String Request_Write_Time,String Route_Title, String Guider_User_ID,String Guider_Profile_URL,String Tourist_User_ID,String Tourist_User_Profile_URL,String Tourist_User_Name,String Request_Date,int Request_State){
        this.Request_Index = Request_Index;
        this.Route_Index = Route_Index;

        this.Request_Write_Time =Request_Write_Time;
        this.Route_Title = Route_Title;
        this.Guider_User_ID =Guider_User_ID;
        this.Guider_Profile_URL = Guider_Profile_URL;

        this.Tourist_User_ID = Tourist_User_ID;
        this.Tourist_User_Profile_URL = Tourist_User_Profile_URL;
        this.Tourist_User_Name = Tourist_User_Name;
        this.Request_Date =Request_Date;
        this.Request_State = Request_State;
    }


}

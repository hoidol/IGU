package com.iguideu.data;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Hoyoung on 2017-08-30.
 */
@IgnoreExtraProperties
public class HistoryData {

    String HistroyIndex; // 생성일자 + 유저 아이디

    String Route_Index;
    String Guider_User_ID; // Route 작성자 - 가이드
    String Tourist_User_ID; // Route 참여했던 - Tourist

    String Route_Data; //날짜

    int History_State; // 0 - 아무것도 없음(가이드용), 1 - 대기, 2 - 수락, 3 - 거절, 4 - 별점주기 5 - 완료

    public HistoryData(){

    }
    public HistoryData(String HistroyIndex,String Route_Index,String Guider_User_ID,String Tourist_User_ID,String Route_Data,int History_State){
        this.HistroyIndex= HistroyIndex;
        this.Route_Index = Route_Index;
        this.Guider_User_ID = Guider_User_ID;
        this.Tourist_User_ID = Tourist_User_ID;
        this.Route_Data = Route_Data;
        this.History_State = History_State;
    }



}

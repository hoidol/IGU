package com.iguideu.data;

import com.google.firebase.database.IgnoreExtraProperties;

import java.security.acl.Owner;

/**
 * Created by Hoyoung on 2017-08-01.
 */
@IgnoreExtraProperties
public class ChattingData {

    public String ChattingData_Index; // Writed_Time + Owner_ID
    public String Owner_ID;
    public String Contents;
    public String Writed_Time;

    public ChattingData(String ChattingData_Index, String Owner_ID, String Contents, String Writed_Time){
        this.ChattingData_Index =ChattingData_Index;
        this.Owner_ID = Owner_ID;
        this.Contents = Contents;
        this.Writed_Time = Writed_Time;
    }
}

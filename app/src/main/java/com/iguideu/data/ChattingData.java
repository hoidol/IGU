package com.iguideu.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.security.acl.Owner;

/**
 * Created by Hoyoung on 2017-08-01.
 */
@IgnoreExtraProperties
public class ChattingData implements Serializable {

    public String Owner_ID;
    public String Contents;
    public String Writed_Time;

    public ChattingData(String Owner_ID, String Contents, String Writed_Time){
        this.Owner_ID = Owner_ID;
        this.Contents = Contents;
        this.Writed_Time = Writed_Time;
    }
}

package com.iguideu.data;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Hoyoung on 2017-08-11.
 */
@IgnoreExtraProperties
public class Request_Data {

    public Route_Data routeData;
    public String Requester_ID;

    public Request_Data(Route_Data routeData,String Requester_ID){
        this.routeData = routeData;
        this.Requester_ID = Requester_ID;
    }


}

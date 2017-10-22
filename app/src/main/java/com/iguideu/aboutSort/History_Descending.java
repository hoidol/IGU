package com.iguideu.aboutSort;

import com.iguideu.data.Request_Data;
import com.iguideu.data.User;

import java.util.Comparator;

/**
 * Created by Hoyoung on 2017-10-20.
 */

public class History_Descending implements Comparator<Request_Data> {

    @Override
    public int compare(Request_Data o1, Request_Data o2) {
        String[] o1_dates = o1.Request_Date.split("_");
        String[] o2_dates = o2.Request_Date.split("_");

        Integer year_o1 = Integer.parseInt(o1_dates[0]);
        Integer year_o2 = Integer.parseInt(o2_dates[0]);
        Integer month_o1 = Integer.parseInt(o1_dates[1]);
        Integer month_o2 = Integer.parseInt(o2_dates[1]);
        Integer day_o1 = Integer.parseInt(o1_dates[2]);
        Integer day_o2 = Integer.parseInt(o2_dates[2]);

        if(year_o1 == year_o1){
            if(month_o1 == month_o2){
                return day_o1.compareTo(day_o2);
            }else{
                return month_o1.compareTo(month_o2);
            }
        }else{
            return year_o1.compareTo(year_o2);
        }
    }
}
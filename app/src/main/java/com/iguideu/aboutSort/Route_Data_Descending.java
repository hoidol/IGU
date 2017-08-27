package com.iguideu.aboutSort;

import com.iguideu.data.Route_Data;

import java.util.Comparator;

/**
 * Created by Hoyoung on 2017-08-22.
 */

// 내림차순
public class Route_Data_Descending implements Comparator<Route_Data> {

    @Override
    public int compare(Route_Data o1, Route_Data o2) {
        Integer Rating_0 = o1.Route_Rating_Num;
        Integer Rating_1 = o2.Route_Rating_Num;
        return Rating_0.compareTo(Rating_1);
    }
}

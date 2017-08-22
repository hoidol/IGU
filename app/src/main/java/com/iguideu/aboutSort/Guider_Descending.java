package com.iguideu.aboutSort;

import com.iguideu.data.User;

import java.util.Comparator;

/**
 * Created by Hoyoung on 2017-08-22.
 */

public class Guider_Descending implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        Integer Rating_0 = o1.User_Guide_Rating;
        Integer Rating_1 = o2.User_Guide_Rating;
        return Rating_0.compareTo(Rating_1);
    }
}

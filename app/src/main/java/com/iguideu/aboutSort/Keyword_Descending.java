package com.iguideu.aboutSort;

import com.iguideu.data.KeywordData;

import java.util.Comparator;

/**
 * Created by Hoyoung on 2017-09-08.
 */

public class Keyword_Descending implements Comparator<KeywordData> {
    @Override
    public int compare(KeywordData o1, KeywordData o2) {
        Integer amount1 = o1.Keyword_Amount_Used;
        Integer amount2 = o2.Keyword_Amount_Used;

        return amount1.compareTo(amount2);
    }
}

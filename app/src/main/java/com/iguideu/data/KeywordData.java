package com.iguideu.data;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Hoyoung on 2017-09-08.
 */
@IgnoreExtraProperties
public class KeywordData {
    public String Keyword;
    public int Keyword_Amount_Used;

    public KeywordData(String Keyword, int keyword_Amount_Used){
        this.Keyword = Keyword;
        this.Keyword_Amount_Used = keyword_Amount_Used;
    }
}

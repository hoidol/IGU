package com.iguideu.search;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iguideu.R;

/**
 * Created by Hoyoung on 2017-08-31.
 */

public class SearchActivity extends AppCompatActivity {
    int search_index;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setProfileData();
        setFrameLayout();

    }

    void setProfileData(){
        Intent receivedIntent = getIntent();

        search_index = receivedIntent.getIntExtra("search_index",0); //0 - 키워드 검색 1 - 날짜 검색
    }

    void setFrameLayout(){
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();

        if(search_index == 0){
            fragmentTransaction.add(R.id.search_FrameLayout,new SearchKeywordFragment());
            fragmentTransaction.commit();
        }else if(search_index == 1){
            fragmentTransaction.add(R.id.search_FrameLayout,new SearchDateFragment());
            fragmentTransaction.commit();
        }
    }
}

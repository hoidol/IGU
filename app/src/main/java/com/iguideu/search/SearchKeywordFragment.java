package com.iguideu.search;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.R;
import com.iguideu.aboutSort.Keyword_Descending;
import com.iguideu.data.AppData;
import com.iguideu.data.KeywordData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hoyoung on 2017-08-31.
 */

public class SearchKeywordFragment extends Fragment {

    Context m_Context;
    EditText keyword_EditText;
    RecyclerView recycler;
    SearchRecyclerAdapter adapter;

    String Searched_Keyword ="";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public SearchKeywordFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_search_keyword, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view);
        setRecycler(view);
        keyword_EditText = (EditText)view.findViewById(R.id.search_keyword_EditText);
        keyword_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String cur_Keyword = keyword_EditText.getText().toString();
                        KeywordData data = dataSnapshot.child("keywords").child(cur_Keyword).getValue(KeywordData.class);
                        if(data != null){
                            adapter.setData(cur_Keyword);
                            Searched_Keyword = cur_Keyword;
                        }else{
                            adapter.setData("");
                            Searched_Keyword = "";
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                };
                AppData.myRef.addListenerForSingleValueEvent(listener);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    void setToolbar(View view){
        TextView textView = (TextView)view.findViewById(R.id.toolbar2_title_TexView);
        textView.setText("");

        TextView button = (TextView)view.findViewById(R.id.toolbar2_Close_Btn);
        button.setTextColor(Color.WHITE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        TextView complete_Btn =(TextView)view.findViewById(R.id.complete_Btn);
        complete_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Searched_Keyword.equals("")){
                    // 검색하려고함
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("Keyword", Searched_Keyword);
                    getActivity().setResult(RESULT_OK, resultIntent);
                }
                getActivity().finish();
            }
        });

    }
    void setRecycler(View view){
        recycler = (RecyclerView)view.findViewById(R.id.search_auto_keyword_RecyclerView);
        adapter = new SearchRecyclerAdapter(getContext());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));


    }
}

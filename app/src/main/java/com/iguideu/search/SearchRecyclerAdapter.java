package com.iguideu.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iguideu.R;

import java.util.List;

/**
 * Created by Hoyoung on 2017-10-09.
 */

public class SearchRecyclerAdapter extends  RecyclerView.Adapter<SearchRecyclerAdapter.Search_Recycler_ViewHolder>  {

    Context mContext;
    String keyword;

    public SearchRecyclerAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public Search_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_keyword_layout, parent, false);
        return new SearchRecyclerAdapter.Search_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Search_Recycler_ViewHolder holder, int position) {
        holder.searched_keyword_TextView.setText(keyword);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void setData(String keyword){
        this.keyword = keyword;
        notifyDataSetChanged();

    }

    class Search_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public TextView searched_keyword_TextView;


        public Search_Recycler_ViewHolder(View itemView) {
            super(itemView);
            searched_keyword_TextView =(TextView)itemView.findViewById(R.id.searched_keyword_TextView);
        }
    }
}

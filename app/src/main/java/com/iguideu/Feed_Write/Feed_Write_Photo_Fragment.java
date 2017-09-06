package com.iguideu.Feed_Write;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.Transaction;
import com.iguideu.R;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.AppData;
import com.steelkiwi.cropiwa.CropIwaView;

import java.util.ArrayList;

/**
 * Created by Hoyoung on 2017-09-02.
 */

public class Feed_Write_Photo_Fragment extends Fragment {
    Context m_Context;

    CropIwaView feed_photo_ImageView;
    RecyclerView feed_photo_Recycler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }


    public Feed_Write_Photo_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_feed_write_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        feed_photo_ImageView = (CropIwaView)view.findViewById(R.id.feed_photo_ImageView);


        feed_photo_Recycler = (RecyclerView)view.findViewById(R.id.feed_photo_Recycler);

        Handler myHandler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),4);
                feed_photo_Recycler.setLayoutManager(layoutManager);

                PhotoPicker_RecyclerAdapter adapter = new PhotoPicker_RecyclerAdapter(getContext(),getPathOfAllImages(),feed_photo_ImageView);
                feed_photo_Recycler.setAdapter(adapter);
            }
        };
        myHandler.sendEmptyMessage(0);
    }

    private ArrayList<String> getPathOfAllImages()
    {
        ArrayList<String> result = new ArrayList<>();
        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };

        Cursor cursor = getContext().getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int columnDisplayname = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        int lastIndex;
        while (cursor.moveToNext())
        {
            String absolutePathOfImage = cursor.getString(columnIndex);
            String nameOfFile = cursor.getString(columnDisplayname);
            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile);
            lastIndex = lastIndex >= 0 ? lastIndex : nameOfFile.length() - 1;

            if (!TextUtils.isEmpty(absolutePathOfImage))
            {
                result.add(absolutePathOfImage);
            }
        }
        return result;
    }
}

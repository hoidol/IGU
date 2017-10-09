package com.iguideu.Feed_Write;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.steelkiwi.cropiwa.CropIwaView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Hoyoung on 2017-09-02.
 */

public class Feed_Write_Photo_Fragment extends Fragment {


    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    PhotoPicker_RecyclerAdapter PhotoPicker_Adapter;
    CropIwaView feed_photo_CropIwaView;
    RecyclerView feed_photo_Recycler;

    ArrayList<String> ImagePathes;
    Bitmap myBitmap;
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

        feed_photo_CropIwaView = (CropIwaView)view.findViewById(R.id.feed_photo_ImageView);

        feed_photo_Recycler = (RecyclerView)view.findViewById(R.id.feed_photo_Recycler);

        Handler myHandler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),4);
                feed_photo_Recycler.setLayoutManager(layoutManager);

                ImagePathes = getPathOfAllImages();
                PhotoPicker_Adapter = new PhotoPicker_RecyclerAdapter(getContext(),ImagePathes,feed_photo_CropIwaView);
                feed_photo_Recycler.setAdapter(PhotoPicker_Adapter);
            }
        };
        myHandler.sendEmptyMessage(0);
        setToolbar(view);
    }

    void setToolbar(View view){
        TextView toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar2_title_TexView);
        toolbar_title_TextView.setText("Photo");
        toolbar_title_TextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.sp_16_7));
        TextView toolbar_back_TextView = (TextView)view.findViewById(R.id.toolbar2_Close_Btn);

        toolbar_back_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        TextView complete_Btn = (TextView)view.findViewById(R.id.complete_Btn);
        complete_Btn.setText("다음");
        complete_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(AppData.LOG_INDICATOR,"호출은 되니??");
                String Path =ImagePathes.get(PhotoPicker_Adapter.getSelected_Index());
                Log.d(AppData.LOG_INDICATOR,"호출은 되니??" + PhotoPicker_Adapter.getSelected_Index());
                File imgFile = new File(Path);

                if(imgFile.exists()) {
                    myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                    Feed_Write_Contents_Fragment fragment = new Feed_Write_Contents_Fragment();
                    fragment.set_Feed_Write_Contents_Data(fragment,myBitmap);

                    fm = getFragmentManager();
                    fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.add(R.id.feed_write_Fragment,fragment);
                    fragmentTransaction.commit();
                }else{
                }

            }
        });
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

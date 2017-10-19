package com.iguideu.Feed_Write;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.AppData;
import com.steelkiwi.cropiwa.CropIwaView;
import com.steelkiwi.cropiwa.config.CropIwaSaveConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-09-02.
 */

public class PhotoPicker_RecyclerAdapter extends  RecyclerView.Adapter<PhotoPicker_RecyclerAdapter.PhotoPicker_Recycler_ViewHolder> {

    Context mContext;
    List<String> PhotoPath;
    CropIwaView Selected_ImageView;

    int selected_index =0;
    ImageView selected_Checker_ImageView;
    List<Bitmap> Photo_Bitmap_List = new ArrayList<>();

    public PhotoPicker_RecyclerAdapter(Context nContext, List<String> PhotoPath, CropIwaView Selected_ImageView){
        this.mContext = nContext;
        this.PhotoPath = PhotoPath;
        this.Selected_ImageView = Selected_ImageView;

    }
    @Override
    public PhotoPicker_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_picker_layout, parent, false);

        return new PhotoPicker_RecyclerAdapter.PhotoPicker_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PhotoPicker_Recycler_ViewHolder holder, final int position) {
        String Path = PhotoPath.get(position);

        File imgFile = new File(Path);

        if(imgFile.exists()){
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = 16;
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),o2);
            holder.photo_picker_ImageView.setImageBitmap(myBitmap);
            Photo_Bitmap_List.add(myBitmap);
        }else {
            return;
        }

        if(position == 0 && Photo_Bitmap_List.size() >0){
            selected_Checker_ImageView = holder.photo_picker_Checker;
            selected_Checker_ImageView.setBackground(ContextCompat.getDrawable(mContext,R.mipmap.checked_icon));
            Selected_ImageView.setImage(Photo_Bitmap_List.get(selected_index));
        }

        holder.photo_picker_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected_Checker_ImageView != null){
                    selected_Checker_ImageView.setBackground(ContextCompat.getDrawable(mContext,R.mipmap.nonchecked_icon));
                }
                selected_index = position;
                selected_Checker_ImageView = holder.photo_picker_Checker;
                selected_Checker_ImageView.setBackground(ContextCompat.getDrawable(mContext,R.mipmap.checked_icon));

                Selected_ImageView.setImage(Photo_Bitmap_List.get(selected_index));
                Selected_ImageView.invalidate();
            }
        });

    }

    // 30개씩 가져오자
    @Override
    public int getItemCount() {
        int Photo_Count = PhotoPath.size();
        return PhotoPath.size() ;
    }

    public int getSelected_Index(){
        return  selected_index;
    }

    class PhotoPicker_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public SquareImageView photo_picker_ImageView;
        public ImageView photo_picker_Checker;


        public PhotoPicker_Recycler_ViewHolder(View itemView) {
            super(itemView);
            photo_picker_ImageView = (SquareImageView)itemView.findViewById(R.id.photo_picker_ImageView);
            photo_picker_Checker = (ImageView)itemView.findViewById(R.id.photo_picker_Checker);
        }
    }
}

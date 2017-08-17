package com.iguideu.guide_mode.Route_Add_Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iguideu.R;
import com.iguideu.guide_mode.Write_Activity.RouteAddActivity;

import java.io.IOException;

import static com.iguideu.R.id.btn_route_add_image_upload;
import static com.iguideu.R.id.textbtn_route_add;

public class Giude_Route_Add extends AppCompatActivity {

    Button yes;
    Button no;
    boolean guideAble=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giude__route__add);
        yes=(Button)findViewById(R.id.btn_route_add_able_yes);
        no=(Button)findViewById(R.id.btn_route_add_able_No);
    }

    public void AddRouteClick(View v)
    {
        switch(v.getId())
        {
            case textbtn_route_add:
                startActivity(new Intent(this, RouteAddActivity.class));
                break;
        }
    }
    public void ImageUpLoadClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_route_add_image_upload:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("사진 선택")
                        .setMessage("사진을 업로드 할 방법을 선택해주세요.")
                        .setPositiveButton("갤러리", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            }
                        })
                        .setNegativeButton("사진 촬영", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog=builder.create();
                dialog.show();
                break;
        }
    }

    public void GuideAbleClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_route_add_able_yes:
                InitAbleBtn();
                yes.setTextColor(getResources().getColor(R.color.Color_Route_Add_Text_Point));
                guideAble=true;
                break;
            case R.id.btn_route_add_able_No:
                InitAbleBtn();
                no.setTextColor(getResources().getColor(R.color.Color_Route_Add_Text_Point));
                guideAble=false;
                break;

        }
    }

    public void InitAbleBtn()
    {

        yes.setTextColor(getResources().getColor(R.color.Color_Route_Add_Text_Normal));
        no.setTextColor(getResources().getColor(R.color.Color_Route_Add_Text_Normal));
    }
}

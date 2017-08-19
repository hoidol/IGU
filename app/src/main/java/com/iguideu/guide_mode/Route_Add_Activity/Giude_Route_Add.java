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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.iguideu.Adapter.RouteAddListAdapter;
import com.iguideu.R;
import com.iguideu.guide_mode.Write_Activity.RouteAddActivity;

import java.io.IOException;

import static com.iguideu.R.id.btn_route_add_image_upload;

public class Giude_Route_Add extends AppCompatActivity {


    public static final int RESULT_OK=0;
    public static final int RESULT_FAIL=1;

    Button yes;
    Button no;
    String result;

    ListView list;

    boolean guideAble=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giude__route__add);
        yes=(Button)findViewById(R.id.btn_route_add_able_yes);
        no=(Button)findViewById(R.id.btn_route_add_able_No);
        list=(ListView)findViewById(R.id.list_route_add_info);
        RouteAddListAdapter listAdapter=new RouteAddListAdapter();

        listAdapter.addItem(0,"경복궁","상세내용상세내용상세내용상세내용상세내용상세내용상세내용상세");
        listAdapter.addItem(1,"경복궁","상세내용상세내용상세내용상세내용상세내용상세내용상세내용상세");
        listAdapter.addItem(2,"경복궁","상세내용상세내용상세내용상세내용상세내용상세내용상세내용상세");
        listAdapter.addItem(3,"경복궁","상세내용상세내용상세내용상세내용상세내용상세내용상세내용상세");
        listAdapter.addItem(4,"경복궁","상세내용상세내용상세내용상세내용상세내용상세내용상세내용상세");
        list.setAdapter(listAdapter);
        setListViewHeightBasedOnChildren(list);


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
    public void RouteAddClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_route_add:
                Intent intent=new Intent(this,RouteAddActivity.class);
                startActivityForResult(intent,RESULT_OK);
                break;
        }
    }

    //Data값 넘어가는지 체크하기 위함.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK)
        {
            Toast.makeText(this,"데이터 값 있음",Toast.LENGTH_SHORT).show();
        }
    }

    //ListView ScrollView안에서도 스크롤링 가능하도록 하는 함수.
    public static void setListViewHeightBasedOnChildren(ListView list)
    {
        ListAdapter listAdapter=list.getAdapter();

        if(listAdapter==null)
        {
            return ;
        }
        int totalheight=0;

        for(int i=0; i <listAdapter.getCount();i++)
        {
            View listitem=listAdapter.getView(i,null,list);
            listitem.measure(0,0);
            totalheight +=listitem.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params=list.getLayoutParams();
        params.height=totalheight+(list.getDividerHeight()*(listAdapter.getCount()-1));
        list.setLayoutParams(params);
        list.requestLayout();
    }
}

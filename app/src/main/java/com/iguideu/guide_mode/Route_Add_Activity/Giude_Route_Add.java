package com.iguideu.guide_mode.Route_Add_Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iguideu.Adapter.RouteAddListAdapter;
import com.iguideu.R;
import com.iguideu.aboutBringGallery.PicassoImageLoader;
import com.iguideu.aboutBringGallery.PicassoPauseOnScrollListener;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;
import com.iguideu.guide_mode.Write_Activity.RouteAddActivity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class Giude_Route_Add extends AppCompatActivity {


    public static final int RESULT_OK=0;
    public static final int RESULT_FAIL=1;

    Button yes;
    Button no;
    String result;

    ListView list;
    RouteAddListAdapter listAdapter;
    EditText RouteTitle;
    Spinner AM_PM,StartTiem,EndTime,Member;
    //Route_Data route=Route_Data();
    String Title,AmPm,Start,End;
    String member;

    EditText[] PlaceName,PlaceDetail;
    ArrayList<String> textPlaceName,textPlaceDetail;
    private List<PhotoInfo> mPhotoList;

    View view;
    List<ImageView> route_image=new ArrayList<>();

    boolean guideAble=true;



    List<String> Route_Photo_URLs = new ArrayList<>();// Route Detail에서 보여줄 사진들의 URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giude__route__add);
        SetAboutGellay();
        yes=(Button)findViewById(R.id.btn_route_add_able_yes);
        no=(Button)findViewById(R.id.btn_route_add_able_No);
        list=(ListView)findViewById(R.id.list_route_add_info);
        listAdapter=new RouteAddListAdapter();
        RouteTitle=(EditText)findViewById(R.id.edit_route_add_title);
        AM_PM=(Spinner)findViewById(R.id.spinner_route_add_am_pm);
        StartTiem=(Spinner)findViewById(R.id.spinner_route_add_start_time);
        EndTime=(Spinner)findViewById(R.id.spinner_route_add_finish_time);
        Member=(Spinner)findViewById(R.id.spinner_route_add_people);

        route_image.add((ImageView)findViewById(R.id.btn_route_add_image_1));
        route_image.add((ImageView)findViewById(R.id.btn_route_add_image_2));
        route_image.add((ImageView)findViewById(R.id.btn_route_add_image_3));
        route_image.add((ImageView)findViewById(R.id.btn_route_add_image_4));
        route_image.add((ImageView)findViewById(R.id.btn_route_add_image_5));

        AM_PM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 AmPm=(String)AM_PM.getAdapter().getItem(AM_PM.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        StartTiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Start=(String)StartTiem.getAdapter().getItem(StartTiem.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        EndTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                End=(String)EndTime.getAdapter().getItem(EndTime.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Member.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                member=(String)Member.getAdapter().getItem(Member.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void SetAboutGellay(){
        mPhotoList = new ArrayList<>();

        initImageLoader(this);
        initFresco();
        x.Ext.init(getApplication());
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
                                OpenGallery();

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
    void OpenGallery(){

        ThemeConfig themeConfig = null;

        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.IGU_Point_Color))
                .setTitleBarTextColor(Color.WHITE)
                .setTitleBarIconColor(Color.BLACK)
                .setFabNornalColor(Color.RED)
                .setFabPressedColor(Color.BLUE)
                .setCheckNornalColor(Color.WHITE)
                .setCheckSelectedColor(Color.BLACK)
                .setIconBack(R.mipmap.back_icon)
                .setIconRotate(R.mipmap.ic_action_repeat)
                .setIconCrop(R.mipmap.ic_action_crop)
                .setIconCamera(R.mipmap.ic_action_camera)
                .build();
        themeConfig = theme;

        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        cn.finalteam.galleryfinal.ImageLoader imageLoader;
        PicassoPauseOnScrollListener pauseOnScrollListener = null;
        imageLoader = new PicassoImageLoader();
        pauseOnScrollListener = new PicassoPauseOnScrollListener(false, true);

        boolean muti = false;
        muti = false;

           /* if (mRbSingleSelect.isChecked()) {

            } if() { // 여러개 지정
                muti = true;
                if (TextUtils.isEmpty(mEtMaxSize.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "请输入MaxSize", Toast.LENGTH_SHORT).show();
                    return;
                }
                int maxSize = Integer.parseInt(mEtMaxSize.getText().toString());
                functionConfigBuilder.setMutiSelectMaxSize(maxSize);
            }*/

        final boolean mutiSelect = muti;

        functionConfigBuilder.setEnableEdit(true); //수정할지 여부 결정
        functionConfigBuilder.setEnableRotate(true); // 회전
        functionConfigBuilder.setRotateReplaceSource(true);


        functionConfigBuilder.setEnableCrop(true); //짜르기
        functionConfigBuilder.setCropSquare(true);
        functionConfigBuilder.setCropReplaceSource(true);
        functionConfigBuilder.setForceCrop(true);
        functionConfigBuilder.setForceCropEdit(true);

        functionConfigBuilder.setEnableCamera(true);
        functionConfigBuilder.setEnablePreview(true);


        functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
        final FunctionConfig functionConfig = functionConfigBuilder.build();


        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, themeConfig)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(pauseOnScrollListener)
                .setNoAnimcation(false)
                .build();
        GalleryFinal.init(coreConfig);

        GalleryFinal.openGallerySingle(AppData.REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
    }
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.addAll(resultList);

                for(int i=0;i<mPhotoList.size();i++)
                {
                    DisplayImageOptions options = new DisplayImageOptions.Builder()
                            .showImageOnFail(R.drawable.ic_gf_default_photo)
                            .showImageForEmptyUri(R.drawable.ic_gf_default_photo)
                            .showImageOnLoading(R.drawable.ic_gf_default_photo).build();
                    route_image.get(i).setScaleType(ImageView.ScaleType.FIT_XY);

                    com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage("file:/" + mPhotoList.get(i).getPhotoPath(), route_image.get(i), options);

                }

            }
        }
        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
        }
    };
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
    public void SaveRouteAddClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_route_add:
                Intent intent=new Intent(this,RouteAddActivity.class);
                startActivityForResult(intent,RESULT_OK);
                break;
            case R.id.btn_route_add_save:
                getData();
                PostRouteData();
                break;
        }
    }


    //Data값 넘어가는지 체크하기 위함.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int positions=0;

        if(requestCode == RESULT_OK)
        {
            if(AppData.PinPointData.size()==0){
                Toast.makeText(this, "This Device Don't Have a Log Data", Toast.LENGTH_SHORT).show();
            }else{
                 positions= AppData.PinPointData.size();
                Toast.makeText(this, "Make a " + positions + "data", Toast.LENGTH_SHORT).show();
                for(int i=0;i<positions;i++)
                {
                    listAdapter.addItem(i,"장소의 이름을 작성해 주세요","장소의 상세 설명을 작성해주세요");

                }
                list.setAdapter(listAdapter);
                setListViewHeightBasedOnChildren(list);

            }
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
        int addHeight=0;
        double measuerHeight=0;

        addHeight=listAdapter.getCount();

        if(addHeight<3 && addHeight>0)
        {
            measuerHeight=0.5;
        }
        if(addHeight>=3 && addHeight<6)
        {
            measuerHeight=0.2;
        }
        for(int i=0; i <listAdapter.getCount();i++)
        {
            View listitem=listAdapter.getView(i,null,list);
            listitem.measure(0,0);
            totalheight +=listitem.getMeasuredHeight()+(listitem.getMeasuredHeight()*measuerHeight);

        }

        ViewGroup.LayoutParams params=list.getLayoutParams();
        params.height=totalheight+(list.getDividerHeight()*(listAdapter.getCount()-1));
        list.setLayoutParams(params);
        list.requestLayout();
    }
    public void getData()
    {
        int count=listAdapter.getCount();
        PlaceName=new EditText[listAdapter.getCount()];
        PlaceDetail=new EditText[listAdapter.getCount()];

        for(int i=0;i<count;i++)
        {
            PlaceName[i]=(EditText)findViewById(AppData.ListEditId.get(i).getId());
            PlaceDetail[i]=(EditText)findViewById(AppData.ListEditId.get(i).getId());

            textPlaceName.add(PlaceName[i].getText().toString());
            textPlaceDetail.add(PlaceDetail[i].getText().toString());

        }
    }
    private void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
    private void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(this, config);
    }
    private void PostRouteData()
    {



        User user= AppData.getCur_User();
        String Route_Index=AppData.getCurTime() + AppData.StringReplace(user.User_ID); // 요청 시간(Route_Time_Of_Write) + 작성자 아이디
        uploadURL(Route_Index);

    }
    int i;
    void uploadURL(final String Route_Index){

        for(i = 0; i < mPhotoList.size(); i++){
            StorageReference mountainsRef = AppData.storageRef.child("routes").child(Route_Index).child(Route_Index+mPhotoList.get(i).getPhotoPath() + ".jpg");
            route_image.get(i).setDrawingCacheEnabled(true);
            route_image.get(i).buildDrawingCache();
            Bitmap bitmap = route_image.get(i).getDrawingCache();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = mountainsRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Route_Photo_URLs.add(downloadUrl.toString());

                    if(i == Route_Photo_URLs.size()){
                        User user= AppData.getCur_User();

                        String User_ID;
                        String User_Name;
                        String User_Profile_URL;

                        User_ID= user.User_ID;
                        User_Name=user.User_Name;
                        User_Profile_URL=user.User_Profile_URL;


                        int memParse;
                        String Route_Time_Of_Write=AppData.getCurTime();

                        String Route_Main_Title;

                        Boolean Route_Possibility;
                        String Route_Available_Time;
                        String Route_Start_Time;
                        String Route_End_Time;

                        int Route_Tourist_Num;
                        List<LatLng> Route_Locations; //목적지만 표시
                        int Route_Rating_Num=0;

                        memParse=Integer.parseInt(member);


                        Route_Main_Title=RouteTitle.getText().toString();
                        Route_Possibility=guideAble;
                        Route_Available_Time=AmPm;
                        Route_Start_Time=Start;
                        Route_End_Time=End;
                        Route_Tourist_Num=memParse;
                        Route_Locations=AppData.PinPointData;

                        Route_Data route_data=new Route_Data(Route_Index,User_ID,User_Name,User_Profile_URL
                                ,Route_Time_Of_Write,Route_Main_Title,Route_Photo_URLs,Route_Possibility,Route_Available_Time,Route_Start_Time,Route_End_Time
                                ,Route_Tourist_Num,Route_Locations,Route_Rating_Num);

                        AppData.myRef.child("routes").child(Route_Index).setValue(route_data);

                    }
                }
            });
        }



    }

}

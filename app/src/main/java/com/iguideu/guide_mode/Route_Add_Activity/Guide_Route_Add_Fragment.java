package com.iguideu.guide_mode.Route_Add_Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iguideu.Adapter.RouteAdapterItem;
import com.iguideu.Adapter.RouteAddListAdapter;
import com.iguideu.R;
import com.iguideu.Signup_Guider.SignUpGuider_Complete_Fragment;
import com.iguideu.aboutBringGallery.PicassoImageLoader;
import com.iguideu.aboutBringGallery.PicassoPauseOnScrollListener;
import com.iguideu.data.AppData;
import com.iguideu.data.KeywordData;
import com.iguideu.data.Route_Data;
import com.iguideu.data.Route_Pin_Data;
import com.iguideu.data.User;
import com.iguideu.guide_mode.Route_Add_Map.RouteAddMapActivity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.io.stream.ByteArrayOutputStream;

public class Guide_Route_Add_Fragment extends Fragment  {

    Context mContext;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    public static final int RESULT_OK=0;
    public static final int RESULT_FAIL=1;

    Button b_Yes,b_No,b_Add,b_Save,b_AddPicture;
    String s_Result;

    RecyclerView recyclerView;
    RouteAddListAdapter listAdapter;
    EditText e_RouteTitle;
    Spinner AMPM,StartTime,EndTime,Member;
    String s_Title, s_AmPm,s_StartTime,s_EndTime,s_Member;

    private List<PhotoInfo> mPhotoList;
    View v;
    List<ImageView> route_image=new ArrayList<>();
    boolean Guideable=true;

    List<String> Route_Photo_URLs=new ArrayList<>();
    List<Route_Pin_Data> Pin_Data_List=new ArrayList<>();
    ArrayList<RouteAdapterItem> routeAdapterItems = new ArrayList<>();
    ArrayList<RouteAddListAdapter.ViewHolder> RouteView=new ArrayList<>();

    boolean IsFirstGuide = false;

    public void SetIsFirstGuide(boolean IsFirstGuide){
        this.IsFirstGuide =IsFirstGuide;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide_route_add,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetAboutGellay();
        b_Yes=(Button)view.findViewById(R.id.btn_route_add_able_yes);
        b_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitAbleBtn();
                b_Yes.setTextColor(getResources().getColor(R.color.Color_Route_Add_Text_Point));
                Guideable=true;

            }
        });

        b_No=(Button)view.findViewById(R.id.btn_route_add_able_No);
        b_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitAbleBtn();
                b_No.setTextColor(getResources().getColor(R.color.Color_Route_Add_Text_Point));
                Guideable=false;
            }
        });
        b_AddPicture=(Button)view.findViewById(R.id.btn_route_add_image_upload);

        recyclerView=(RecyclerView)view.findViewById(R.id.list_route_add_info);
        listAdapter = new RouteAddListAdapter(mContext.getApplicationContext());
        recyclerView.setAdapter(listAdapter);

        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));


        e_RouteTitle=(EditText)view.findViewById(R.id.edit_route_add_title);
        AMPM=(Spinner)view.findViewById(R.id.spinner_route_add_am_pm);
        StartTime=(Spinner)view.findViewById(R.id.spinner_route_add_start_time);
        EndTime=(Spinner)view.findViewById(R.id.spinner_route_add_finish_time);
        Member=(Spinner)view.findViewById(R.id.spinner_route_add_people);
        route_image.add((ImageView)view.findViewById(R.id.btn_route_add_image_1));
        route_image.add((ImageView)view.findViewById(R.id.btn_route_add_image_2));
        route_image.add((ImageView)view.findViewById(R.id.btn_route_add_image_3));
        route_image.add((ImageView)view.findViewById(R.id.btn_route_add_image_4));
        route_image.add((ImageView)view.findViewById(R.id.btn_route_add_image_5));
        b_Add=(Button)view.findViewById(R.id.btn_route_add);
        b_Save=(Button)view.findViewById(R.id.btn_route_add_save);

        b_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,RouteAddMapActivity.class);
                startActivityForResult(intent,RESULT_OK);
            }
        });

        b_AddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        b_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteView=listAdapter.getViewHolder();
                s_Title=e_RouteTitle.getText().toString();



                  for(int i =0;i<AppData.PinPointData.size();i++){
                      AppData.PinPointData.get(i).Route_Title = RouteView.get(i).Route_Title.getText().toString();
                      AppData.PinPointData.get(i).Route_Content = RouteView.get(i).Route_Detail.getText().toString();
                }
        PostRouteData();

            }
        });

        AMPM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s_AmPm=(String)AMPM.getAdapter().getItem(AMPM.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        StartTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s_StartTime=(String)StartTime.getAdapter().getItem(StartTime.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        EndTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s_EndTime=(String)EndTime.getAdapter().getItem(EndTime.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Member.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s_Member=(String)Member.getAdapter().getItem(Member.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void SetAboutGellay(){
        mPhotoList = new ArrayList<>();

        initImageLoader(mContext);
        initFresco();
        x.Ext.init(getActivity().getApplication());
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


        functionConfigBuilder.setSelected(mPhotoList);
        final FunctionConfig functionConfig = functionConfigBuilder.build();


        CoreConfig coreConfig = new CoreConfig.Builder(mContext, imageLoader, themeConfig)
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

    public void InitAbleBtn()
    {
        b_Yes.setTextColor(getResources().getColor(R.color.Color_Route_Add_Text_Normal));
        b_No.setTextColor(getResources().getColor(R.color.Color_Route_Add_Text_Normal));
    }


    //Data값 넘어가는지 체크하기 위함.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int positions=0;

        if(requestCode == RESULT_OK)
        {

            if(AppData.PinPointData.size()==0){

                Toast.makeText(mContext, "This Device Don't Have a Log Data", Toast.LENGTH_SHORT).show();

            }else{

                positions= AppData.PinPointData.size();

                Toast.makeText(mContext, "Make a " + positions + "data", Toast.LENGTH_SHORT).show();

                for(int i=0;i<positions;i++)
                {

                    routeAdapterItems.add(new RouteAdapterItem(i,"장소명을 입력해주세요","장소의 상세내용을 입력해주세요"));
                }

                //list.setAdapter(listAdapter);

                // setListViewHeightBasedOnChildren(list);

            }
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
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(mContext)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(mContext, config);
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

                                String User_ID= user.User_ID;
                                String User_Name=user.User_Name;
                                String User_Profile_URL=user.User_Profile_URL;

                                String Route_Time_Of_Write=AppData.getCurTime();


                                int Route_Rating_Num=0;

                                String Route_Main_Title=s_Title;
                                Boolean Route_Possibility=Guideable;
                                String Route_Available_Time=s_AmPm;
                                String Route_Start_Time=s_StartTime;
                                String Route_End_Time=s_EndTime;
                                int Route_Tourist_Num=Integer.parseInt(s_Member);;
                                List<Route_Pin_Data> Route_Locations = AppData.PinPointData; //목적지만 표시

                                Route_Data route_data=new Route_Data(Route_Index,User_ID,User_Name,User_Profile_URL
                                        ,Route_Time_Of_Write,Route_Main_Title,Route_Photo_URLs,Route_Possibility,Route_Available_Time,Route_Start_Time,Route_End_Time
                                        ,Route_Tourist_Num,Route_Locations,Route_Rating_Num);


                                AppData.myRef.child("routes").child(Route_Index).setValue(route_data); //★


                                saveKeyWord(Route_Locations); // 키워드 데이터 저장

                            }
                        }
                    });
                }
    }

    void saveKeyWord(final List<Route_Pin_Data> Route_Locations){

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("keywords").getChildren();

                boolean IsExist[] = new boolean[Route_Locations.size()];
                for (boolean exist:IsExist) {
                    exist = false;
                }

                for(int i =0 ; i<Route_Locations.size();i++){
                    while (iterable.iterator().hasNext()) {
                        DataSnapshot cur_Snapshot = iterable.iterator().next();
                        String Keyword = cur_Snapshot.child("Keyword").getValue().toString();
                        if (Keyword == Route_Locations.get(i).Route_Title) {
                            int amount_Used = cur_Snapshot.child("Keyword_Amount_Used").getValue(Integer.class);
                            ++amount_Used;
                            AppData.myRef.child("keywords").child(Keyword).child("Keyword_Amount_Used").setValue(amount_Used);
                            IsExist[i] = true;
                        }
                    }
                    // 기존에 등록되었을 키워드가 없음.
                    if(IsExist[i] == false){
                        KeywordData keywordData = new KeywordData(Route_Locations.get(i).Route_Title,1);
                        AppData.myRef.child("keywords").child(keywordData.Keyword).setValue(keywordData);
                    }
                }



                //여기에 화면 넘어가는거 하기
                fm=getFragmentManager();
                fragmentTransaction = fm.beginTransaction();

                SignUpGuider_Complete_Fragment fragment=new SignUpGuider_Complete_Fragment();

                if(IsFirstGuide){
                    fragmentTransaction.replace(R.id.signup_guider_FrameLayout,fragment);
                    fragmentTransaction.commit();
                }else{
                    fragmentTransaction.replace(R.id.route_add_frame,fragment);
                    fragmentTransaction.commit();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(AppData.LOG_INDICATOR,"onResume");
        if(listAdapter != null){
            listAdapter.notifyDataSetChanged();
        }
    }
}


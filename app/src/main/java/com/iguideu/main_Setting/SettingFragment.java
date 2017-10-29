package com.iguideu.main_Setting;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iguideu.MainActivity;
import com.iguideu.R;
import com.iguideu.Signup.SignUpProgress6;
import com.iguideu.Signup_Guider.SignUpGuiderActivity;
import com.iguideu.aboutBringGallery.PicassoImageLoader;
import com.iguideu.aboutBringGallery.PicassoPauseOnScrollListener;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.AppData;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;

import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class SettingFragment extends Fragment {
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    RoundedImageView profile_Change_Btn;

    TextView profile_Name_Text;
    Button toGuide_Btn;
    Button Language_Btn;
    Button Envir_Btn;
    Button Logout_Btn;


    MainActivity mainActivity;

    private List<PhotoInfo> mPhotoList;
    PhotoInfo photoInfo;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mainActivity = (MainActivity)activity;


    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_setting, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profile_Name_Text = (TextView)view.findViewById(R.id.profile_Name_Text);
        profile_Name_Text.setText(AppData.getCur_User().User_Name);
        profile_Change_Btn = (RoundedImageView)view.findViewById(R.id.profile_Change_Btn);
        Picasso.with(getContext()).load(AppData.getCur_User().User_Profile_URL).into(profile_Change_Btn);
        profile_Change_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery(v);
            }
        });

        SetAboutGellay();

        fm = getFragmentManager();

        toGuide_Btn =(Button)view.findViewById(R.id.setting_toGuide);
        setTouchEvent(toGuide_Btn);

        toGuide_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitClick(v);
            }
        });
        Language_Btn = (Button)view.findViewById(R.id.setting_Language);
        Language_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitClick(v);
            }
        });
        setTouchEvent(Language_Btn);

        Envir_Btn = (Button)view.findViewById(R.id.setting_Envir);
        Envir_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitClick(v);
            }
        });
        setTouchEvent(Envir_Btn);

        Logout_Btn = (Button)view.findViewById(R.id.setting_Logout);
        Logout_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitClick(v);
            }
        });
        setTouchEvent(Logout_Btn);

    }

    public void InitClick(View v)
    {
        switch (v.getId())
        {
            case R.id.setting_toGuide:

                boolean Is_User_Guider = AppData.getCur_User().User_Guide;


                if(Is_User_Guider == true){
                    int mode = AppData.getApp_Mode();

                    switch (mode){
                        case 0:
                            // 0 -> 1로 바꾸기
                            AppData.setApp_Mode(1);
                            mainActivity.changeMode(1);
                            toGuide_Btn.setText(getString(R.string.setting_change_tourist_kr));

                            break;
                        case 1:
                            // 1 -> 0로 바꾸기
                            AppData.setApp_Mode(0);
                            mainActivity.changeMode(0);
                            toGuide_Btn.setText(getString(R.string.setting_change_guide_kr));
                            break;
                    }
                }else{
                    Intent intent = new Intent(getContext(), SignUpGuiderActivity.class);
                    getContext().startActivity(intent);
                }
                break;
            case R.id.setting_Language:

                fragmentTransaction = fm.beginTransaction();
                SettingLanguageFragment fragment = new SettingLanguageFragment();
                fragmentTransaction.replace(R.id.main_Fragment,fragment);
                fragmentTransaction.commit();
                break;

            case R.id.setting_Envir:
                fragmentTransaction = fm.beginTransaction();
                SettingEnvirFragment fragment1 = new SettingEnvirFragment();
                fragmentTransaction.replace(R.id.main_Fragment,fragment1);
                fragmentTransaction.commit();
                break;
            case R.id.setting_Logout:
                AppData.setApp_Mode(0);
                AppData.mAuth.signOut();
                AppData.setCur_User(null);
                AppData.setApp_AutoLogin(false);
                getActivity().finish();
                break;

        }
    }


    void setTouchEvent(final Button btn){
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ||  event.getAction() == MotionEvent.ACTION_BUTTON_PRESS || event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {

                    btn.setBackground(getResources().getDrawable(R.color.IGU_Point_Color));
                    btn.setTextColor(getResources().getColor(R.color.Color_White));
                    btn.setElevation(0);

                } else if(event.getAction() == MotionEvent.ACTION_HOVER_EXIT || event.getAction() == MotionEvent.ACTION_UP){
                    btn.setBackground(getResources().getDrawable(R.color.Color_White));
                    btn.setTextColor(getResources().getColor(R.color.Color_Setting_Text));
                    btn.setElevation(0);
                }
                return false;
            }
        });
    }

    // 겔러리 불러오기
    void SetAboutGellay(){
        mPhotoList = new ArrayList<>();

        initImageLoader(getContext());
        initFresco();
        x.Ext.init(getActivity().getApplication());
    }


    void OpenGallery(View v){

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
        PauseOnScrollListener pauseOnScrollListener = null;
        imageLoader = new PicassoImageLoader();
        pauseOnScrollListener = new PicassoPauseOnScrollListener(false, true);

        functionConfigBuilder.setEnableEdit(true); //수정할지 여부 결정
        functionConfigBuilder.setEnableRotate(true); // 회전
        functionConfigBuilder.setRotateReplaceSource(true);


        functionConfigBuilder.setEnableCrop(true); //짜르기
        functionConfigBuilder.setCropSquare(true);
        functionConfigBuilder.setCropReplaceSource(true);
        functionConfigBuilder.setForceCrop(true);
        functionConfigBuilder.setForceCropEdit(true);

        functionConfigBuilder.setEnableCamera(false);
        functionConfigBuilder.setEnablePreview(true);


        functionConfigBuilder.setSelected(mPhotoList);
        final FunctionConfig functionConfig = functionConfigBuilder.build();


        CoreConfig coreConfig = new CoreConfig.Builder(getActivity(), imageLoader, themeConfig)
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
                photoInfo = mPhotoList.get(0);

                DisplayImageOptions options = new DisplayImageOptions.Builder()
                        .showImageOnFail(R.drawable.ic_gf_default_photo)
                        .showImageForEmptyUri(R.drawable.ic_gf_default_photo)
                        .showImageOnLoading(R.drawable.ic_gf_default_photo).build();


                profile_Change_Btn.setScaleType(ImageView.ScaleType.FIT_XY);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().setDefaultLoadingListener(new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        SaveURL();
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                });
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), profile_Change_Btn, options);



            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
        }
    };

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
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(getContext())
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(getContext(), config);
    }

    void SaveURL(){
        final String user_key = AppData.StringReplace(AppData.getCur_User().User_ID);
        StorageReference mountainsRef = AppData.storageRef.child("users").child(user_key).child("profile_image.jpg");
        profile_Change_Btn.setDrawingCacheEnabled(true);
        profile_Change_Btn.buildDrawingCache();
        Bitmap bitmap = profile_Change_Btn.getDrawingCache();
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
                AppData.getCur_User().User_Profile_URL = downloadUrl.toString();
                AppData.myRef.child("users").child(user_key).child("User_Profile_URL").setValue(downloadUrl.toString());
            }
        });
    }
}

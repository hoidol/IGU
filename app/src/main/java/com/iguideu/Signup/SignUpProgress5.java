package com.iguideu.Signup;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iguideu.R;
import com.iguideu.aboutBringGallery.PicassoImageLoader;
import com.iguideu.aboutBringGallery.PicassoPauseOnScrollListener;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.User;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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


public class SignUpProgress5 extends Fragment {
    Context context;
    RoundedImageView add_profile_Btn;
    Button btn_pg5_next;

    private List<PhotoInfo> mPhotoList;


    Activity activity;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    User cur_User;
    PhotoInfo photoInfo;



    public void set_Cur_User(User user){
        this.cur_User = user;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (SignUpActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up_progress5, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SetAboutGellay();
        add_profile_Btn =(RoundedImageView)view.findViewById(R.id.add_profile_Btn);
        add_profile_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Progress5Clock(v);
            }
        });

        btn_pg5_next = (Button)view.findViewById(R.id.btn_pg5_next);
        btn_pg5_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Progress5Clock(v);
            }
        });

    }


    void SetAboutGellay(){
        mPhotoList = new ArrayList<>();

        initImageLoader(context);
        initFresco();
        x.Ext.init(activity.getApplication());
    }

    public void Progress5Clock(View v)
    {
        switch (v.getId())
        {
            case R.id.add_profile_Btn:
                OpenGallery(v);
                break;

            case R.id.btn_pg5_next:
                btn_pg5_next.setEnabled(false);
                if(photoInfo != null){
                    uploadURL();
                }else{
                    fm = getFragmentManager();
                    fragmentTransaction = fm.beginTransaction();
                    cur_User.User_Profile_URL = null;

                    SignUpProgress6 fragment = new SignUpProgress6();
                    fragment.set_Cur_User(cur_User);

                    fragmentTransaction.replace(R.id.sign_up_framelayout,fragment);
                    fragmentTransaction.commit();

                }

                break;
        }
    }

    void uploadURL(){
        showProgressDialog();
        String user_key = AppData.StringReplace(cur_User.User_ID);
        StorageReference mountainsRef = AppData.storageRef.child("users").child(user_key).child("profile_image.jpg");

        String imgpath = photoInfo.getPhotoPath();
        Bitmap bm = BitmapFactory.decodeFile(imgpath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
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


                cur_User.User_Profile_URL = downloadUrl.toString();
                hideProgressDialog();
                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();

                SignUpProgress6 fragment = new SignUpProgress6();
                fragment.set_Cur_User(cur_User);

                fragmentTransaction.replace(R.id.sign_up_framelayout,fragment);
                fragmentTransaction.commit();

            }
        });
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

            boolean muti = false;
            muti = false;

           /* // 여러개 지정
                muti = true;
                if (TextUtils.isEmpty(mEtMaxSize.getText().toString())) {
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

            functionConfigBuilder.setEnableCamera(false);
            functionConfigBuilder.setEnablePreview(true);


            functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
            final FunctionConfig functionConfig = functionConfigBuilder.build();


            CoreConfig coreConfig = new CoreConfig.Builder(activity, imageLoader, themeConfig)
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


                add_profile_Btn.setScaleType(ImageView.ScaleType.FIT_XY);

                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), add_profile_Btn, options);

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
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(context, config);
    }

    @VisibleForTesting

    public ProgressDialog mProgressDialog;



    public void showProgressDialog() {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);

        }
        mProgressDialog.show();
    }



    public void hideProgressDialog() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {

            mProgressDialog.dismiss();

        }

    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}

package com.iguideu.Feed_Write;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iguideu.R;
import com.iguideu.aboutBringGallery.PicassoImageLoader;
import com.iguideu.aboutBringGallery.PicassoPauseOnScrollListener;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.AppData;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

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
 * Created by Hoyoung on 2017-09-02.
 */

public class Feed_Write_Photo_Fragment extends Fragment {


    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    SquareImageView feed_photo_ImageView;


    private List<PhotoInfo> mPhotoList;
    PhotoInfo photoInfo;

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

        feed_photo_ImageView = (SquareImageView)view.findViewById(R.id.feed_photo_ImageView);
        feed_photo_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery(v);
            }
        });
        SetAboutGellay();
        OpenGallery(view);
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
                //myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                if(myBitmap != null){
                    Feed_Write_Contents_Fragment fragment = new Feed_Write_Contents_Fragment();
                    fragment.set_Feed_Write_Contents_Data(fragment,myBitmap);

                    fm = getFragmentManager();
                    fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.feed_write_Fragment,fragment);
                    fragmentTransaction.commit();
                }
            }
        });
    }

   /* private ArrayList<String> getPathOfAllImages()
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
    }*/

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


                feed_photo_ImageView.setScaleType(ImageView.ScaleType.FIT_XY);
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
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), feed_photo_ImageView, options);



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
        feed_photo_ImageView.setDrawingCacheEnabled(true);
        feed_photo_ImageView.buildDrawingCache();
        myBitmap = feed_photo_ImageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    }


}

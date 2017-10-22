package com.iguideu.Feed_Write;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Feed_Data;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.data.Route_Pin_Data;
import com.iguideu.data.User;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Hoyoung on 2017-09-02.
 */

public class Feed_Write_Contents_Fragment extends Fragment{

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    Feed_Write_Contents_Fragment Cur_Fragment;
    Bitmap seletecBitmap;

    EditText feed_contents_EditText;
    ImageView selected_ImageView;
    TextView search_route_Btn;

    Request_Data selected_Request_Data;

    public Feed_Write_Contents_Fragment() {
        // Required empty public constructor
    }

    public void set_Feed_Write_Contents_Data(Feed_Write_Contents_Fragment fragment, Bitmap seletecBitmap){
        Cur_Fragment= fragment;
        this.seletecBitmap = seletecBitmap;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_feed_write_contents, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view);
        feed_contents_EditText = (EditText)view.findViewById(R.id.feed_contents_EditText);
        selected_ImageView = (ImageView)view.findViewById(R.id.selected_ImageView);
        search_route_Btn = (TextView)view.findViewById(R.id.search_route_Btn);

        selected_ImageView.setImageBitmap(seletecBitmap);

        search_route_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Feed_Write_Search_Route_Fragment fragment = new Feed_Write_Search_Route_Fragment();
                fragment.set_Feed_Write_Search_Route_Data(Cur_Fragment,fragment);
                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.add(R.id.feed_write_Fragment,fragment);
                fragmentTransaction.commit();
            }
        });

    }

    void setToolbar(View view){
        TextView toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar2_title_TexView);
        toolbar_title_TextView.setText("작성");
        toolbar_title_TextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.sp_16_7));

        TextView toolbar2_Close_Btn = (TextView)view.findViewById(R.id.toolbar2_Close_Btn);

        toolbar2_Close_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        TextView complete_Btn = (TextView)view.findViewById(R.id.complete_Btn);
        complete_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Feed_Contents = feed_contents_EditText.getText().toString();
                if(!Feed_Contents.equals("")){
                    SaveFeedData(Feed_Contents);
                }
            }
        });
    }

    void SaveFeedData(final String Feed_Contents){
        final User cur_User = AppData.getCur_User();
        final String feed_Index = AppData.getCurTime()+AppData.StringReplace(cur_User.User_ID);

        StorageReference mountainsRef = AppData.storageRef.child("feeds").child(feed_Index).child(feed_Index+ ".jpg");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        seletecBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
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
                String url = downloadUrl.toString();

                Feed_Data data = null;
                if(selected_Request_Data == null){
                    data = new Feed_Data(feed_Index,cur_User.User_ID,cur_User.User_Name,cur_User.User_Profile_URL,AppData.getCurTime(),url,Feed_Contents,"-1");
                }else{
                    data = new Feed_Data(feed_Index,cur_User.User_ID,cur_User.User_Name,cur_User.User_Profile_URL,AppData.getCurTime(),url,Feed_Contents,selected_Request_Data.Route_Index);
                }

                AppData.myRef.child("feeds").child(feed_Index).setValue(data);

                Feed_Write_Complete_Fragment fragment = new Feed_Write_Complete_Fragment();
                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.feed_write_Fragment,fragment);
                fragmentTransaction.commit();

            }
        });
    }



    public void set_Searched_Route(Request_Data selected_Request_Data){
        if(selected_Request_Data != null && search_route_Btn !=null){
            this.selected_Request_Data = selected_Request_Data;
            search_route_Btn.setText(this.selected_Request_Data.Route_Title);
        }
    }
}
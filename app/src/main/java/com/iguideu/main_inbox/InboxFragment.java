package com.iguideu.main_inbox;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingData;
import com.iguideu.data.ChattingRoom;
import com.iguideu.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class InboxFragment extends Fragment {
    RecyclerView recyclerView;


    public InboxFragment() {
        // Required empty public constructor
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.inbox_RecyclerView);

        AppData.setCur_User( new User("my_id", "pass","my_id","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",false,null,0,null,null,null,null,null,null,null));

        ArrayList<ChattingData> Chat_Data_List1 = new ArrayList<ChattingData>();
        Chat_Data_List1.add(new ChattingData("my_id","안녕하세요. 나1  안녕하세요. 나1  안녕하세요. 나1  안녕하세요. 나1  ",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("other_Id1","안녕하세요.2 안녕하세요.2 안녕하세요.2 안녕하세요.2 안녕하세요.2 안녕하세요.2 안녕하세요.2 안녕하세요.2 ",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("my_id","안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  ",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("other_Id1","안녕하세요.4",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("my_id","안녕하세요. 나5",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("other_Id1","안녕하세요.6",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("other_Id1","안녕하세요.7",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("other_Id1","안녕하세요.8",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("other_Id1","안녕하세요.9",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("other_Id1","안녕하세요.10   안녕하세요.10   안녕하세요.10   안녕하세요.10   안녕하세요.10   안녕하세요.10   안녕하세요.10   안녕하세요.10   안녕하세요.10   안녕하세요.10   안녕하세요.10   ",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("my_id","안녕하세요. 나11",AppData.getCurTime()));
        Chat_Data_List1.add(new ChattingData("my_id","안녕하세요. 나12",AppData.getCurTime()));

        ArrayList<ChattingData> Chat_Data_List2 = new ArrayList<ChattingData>();
        Chat_Data_List2.add(new ChattingData("my_id","안녕하세요. 나1",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("other_Id2","안녕하세요.2",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("my_id","안녕하세요. 나3 ",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("other_Id2","안녕하세요4.",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("my_id","안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 안녕하세요. 나5 ",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("other_Id2","안녕하세요6. ",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("other_Id2","안녕하세요7 안녕하세요7 안녕하세요7 안녕하세요7 안녕하세요7 안녕하세요7 안녕하세요7 안녕하세요7 .",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("other_Id2","안녕하세요8.",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("other_Id2","안녕하세요9.",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("other_Id2","안녕하세요10.",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("my_id","안녕하세요. 나11 안녕하세요. 나11 안녕하세요. 나11 안녕하세요. 나11 안녕하세요. 나11 안녕하세요. 나11 안녕하세요. 나11 안녕하세요. 나11 안녕하세요. 나11 안녕하세요. 나11 ",AppData.getCurTime()));
        Chat_Data_List2.add(new ChattingData("my_id","안녕하세요. 나12     안녕하세요. 나12     안녕하세요. 나12     안녕하세요. 나12     안녕하세요. 나12     안녕하세요. 나12     안녕하세요. 나12     안녕하세요. 나12     안녕하세요. 나12     안녕하세요. 나12     ",AppData.getCurTime()));

        ArrayList<ChattingData> Chat_Data_List3 = new ArrayList<ChattingData>();
        Chat_Data_List3.add(new ChattingData("my_id","안녕하세요. 나1   안녕하세요. 나1   안녕하세요. 나1   안녕하세요. 나1   안녕하세요. 나1   안녕하세요. 나1   ",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("other_Id3","안녕하세요.2  안녕하세요.2  안녕하세요.2  안녕하세요.2  안녕하세요.2  안녕하세요.2  ",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("my_id","안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3  안녕하세요. 나3   ",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("other_Id3","안녕하세요.4    안녕하세요.4    안녕하세요.4    안녕하세요.4    안녕하세요.4    안녕하세요.4    ",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("my_id","안녕하세요. 나5",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("other_Id3","안녕하세요6.     안녕하세요6.     안녕하세요6.     안녕하세요6.     안녕하세요6.     안녕하세요6.     ",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("other_Id3","안녕하세요7.",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("other_Id3","안녕하세요8.",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("other_Id3","안녕하세요9.",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("other_Id3","안녕하세요10.",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("my_id","안녕하세요. 나11",AppData.getCurTime()));
        Chat_Data_List3.add(new ChattingData("my_id","안녕하세요. 나11",AppData.getCurTime()));

        ArrayList<ChattingData> Chat_Data_List4 = new ArrayList<ChattingData>();
        Chat_Data_List4.add(new ChattingData("my_id","안녕하세요.1",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("other_Id4","안녕하세요2.",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("my_id","안녕하세요.3",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("other_Id4","안녕하세요4.   안녕하세요4.   안녕하세요4.   안녕하세요4.   안녕하세요4.   안녕하세요4.   안녕하세요4.   안녕하세요4.     ",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("my_id","안녕하세요.5   안녕하세요.5   안녕하세요.5   안녕하세요.5   안녕하세요.5   안녕하세요.5   ",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("other_Id4","안녕하세요.6",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("other_Id4","안녕하세요.7 안녕하세요.7 안녕하세요.7 안녕하세요.7 안녕하세요.7 안녕하세요.7 안녕하세요.7 ",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("other_Id4","안녕하세요.8   안녕하세요.8   안녕하세요.8   안녕하세요.8   안녕하세요.8   안녕하세요.8   ",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("other_Id4","안녕하세요.9",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("other_Id4","안녕하세요.10  안녕하세요.10  안녕하세요.10  안녕하세요.10  안녕하세요.10  안녕하세요.10  안녕하세요.10  안녕하세요.10  안녕하세요.10  안녕하세요.10  안녕하세요.10  안녕하세요.10  ",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("my_id","안녕하세요.11",AppData.getCurTime()));
        Chat_Data_List4.add(new ChattingData("my_id","안녕하세요.12",AppData.getCurTime()));

        ArrayList<ChattingRoom> list = new ArrayList<>();

        list.add(new ChattingRoom("chattingroom_index1",
                new User("my_id", "pass","my_id","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",false,null,0,null,null,null,null,null,null,null)
                , new User("other_Id1", "pass","other_Id1","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/8.jpg?alt=media&token=9a81748f-c736-4253-8f6a-8ac125d53580",false,null,0,null,null,null,null,null,null,null), AppData.getCurTime(),Chat_Data_List1,false));

        list.add(new ChattingRoom("chattingroom_index2",
                new User("my_id", "pass","my_id","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",false,null,0,null,null,null,null,null,null,null)
                , new User("other_Id2", "pass","other_Id2","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/8.jpg?alt=media&token=9a81748f-c736-4253-8f6a-8ac125d53580",false,null,0,null,null,null,null,null,null,null), AppData.getCurTime(),Chat_Data_List2,true));

        list.add(new ChattingRoom("chattingroom_index3",
                new User("my_id", "pass","my_id","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",false,null,0,null,null,null,null,null,null,null)
                , new User("other_Id3", "pass","other_Id3","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/8.jpg?alt=media&token=9a81748f-c736-4253-8f6a-8ac125d53580",false,null,0,null,null,null,null,null,null,null), AppData.getCurTime(),Chat_Data_List3,true));

        list.add(new ChattingRoom("chattingroom_index4",
                new User("my_id", "pass","my_id","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",false,null,0,null,null,null,null,null,null,null)
                , new User("other_Id4", "pass","other_Id4","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/8.jpg?alt=media&token=9a81748f-c736-4253-8f6a-8ac125d53580",false,null,0,null,null,null,null,null,null,null), AppData.getCurTime(),Chat_Data_List4,false));


        InboxRecyclerAdapter adapter = new InboxRecyclerAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }
}

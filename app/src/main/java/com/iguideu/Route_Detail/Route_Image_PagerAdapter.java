package com.iguideu.Route_Detail;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.custom_view.SquareImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-08-20.
 */

public class Route_Image_PagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mInflater;
    List<String> Image_URL;

    public Route_Image_PagerAdapter(Context context, LayoutInflater inflater, List<String> Image_URL) {
        this.mContext = context;
        this.mInflater = inflater;
        this.Image_URL = Image_URL;
    }


    @Override

    public int getCount() {

        return Image_URL.size(); //이미지 개수 리턴(그림이 10개라서 10을 리턴)
    }


    @Override

    public Object instantiateItem(ViewGroup container, int position) {
        View view=null;

        view= mInflater.inflate(R.layout.viewpager_route_image, null);
        //만들어진 View안에 있는 ImageView 객체 참조
        //위에서 inflated 되어 만들어진 view로부터 findViewById()를 해야 하는 것에 주의.

        SquareImageView img= (SquareImageView)view.findViewById(R.id.route_ImageView);
        Picasso.with(mContext).load(Image_URL.get(position)).into(img);

        //ViewPager에 만들어 낸 View 추가
        container.addView(view);

        //Image가 세팅된 View를 리턴
        return view;
    }


    //화면에 보이지 않은 View는파쾨를 해서 메모리를 관리함.
    //첫번째 파라미터 : ViewPager
    //두번째 파라미터 : 파괴될 View의 인덱스(가장 처음부터 0,1,2,3...)
    //세번째 파라미터 : 파괴될 객체(더 이상 보이지 않은 View 객체)
    @Override

    public void destroyItem(ViewGroup container, int position, Object object) {
        //ViewPager에서 보이지 않는 View는 제거
        //세번째 파라미터가 View 객체 이지만 데이터 타입이 Object여서 형변환 실시
        container.removeView((View)object);
    }



    //instantiateItem() 메소드에서 리턴된 Ojbect가 View가  맞는지 확인하는 메소드
    @Override

    public boolean isViewFromObject(View v, Object obj) {
        // TODO Auto-generated method stub
        return v==obj;
    }

}

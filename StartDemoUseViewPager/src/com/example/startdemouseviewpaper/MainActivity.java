package com.example.startdemouseviewpaper;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.adapter.MyViewPagerAdapter;


public class MainActivity extends Activity {  
  
    private ViewPager mViewPager;  
    private MyViewPagerAdapter mAdapter;  
    private LinearLayout mLinearLayout;  
    private List<View> mLists = new ArrayList<View>();  
    private Integer[] img_ids = new Integer[] { R.drawable.guide_01,  
            R.drawable.guide_02, R.drawable.guide_03, R.drawable.guide_04,  
            R.drawable.guide_05 };  
    private ImageView[] points = new ImageView[img_ids.length];  
  
    private int current=0;//默认在第一页   
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        mViewPager = (ViewPager) this.findViewById(R.id.myViewpager);  
        mLinearLayout = (LinearLayout) this.findViewById(R.id.linearlayout);  
  
        //把需要显示的图片加入到ViewPager页面集合中  
        for (int i = 0; i < img_ids.length; i++) {  
            ImageView iv = new ImageView(this);  
            iv.setLayoutParams(new LinearLayout.LayoutParams(  
                    LinearLayout.LayoutParams.FILL_PARENT,  
                    LinearLayout.LayoutParams.FILL_PARENT));  
            iv.setImageResource(img_ids[i]);  
            mLists.add(iv);  
        }  
          
        mAdapter=new MyViewPagerAdapter(mLists);  
        mViewPager.setAdapter(mAdapter);  
          
        //为ViewPager选项卡添加  切换时候的监听  
        mViewPager.setOnPageChangeListener(new MyOnPagerChangeListener());  
        //初始化下面的小圆点  
        initPoints();     
    }  
   
      //初始化下面的小圆点  
      private void initPoints()  
      {  
          for(int i=0;i<img_ids.length;i++)  
          {  
              points[i]=(ImageView)mLinearLayout.getChildAt(i);  
              points[i].setImageResource(R.drawable.point_normal);  
                
          }  
          current=0;//默认在第一页  
          points[current].setImageResource(R.drawable.point_select);//此刻处于第一页，把第一页的小圆圈设置为unenabled  
      }  
         
       class MyOnPagerChangeListener implements OnPageChangeListener  
       {  
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
                          
        }  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
                  
        }  
        @Override  
        public void onPageSelected(int arg0) {  
              
            points[arg0].setImageResource(R.drawable.point_select);  
            points[current].setImageResource(R.drawable.point_normal);  
            current=arg0;  
        }  
             
       }
}

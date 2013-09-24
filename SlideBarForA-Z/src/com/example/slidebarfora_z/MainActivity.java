package com.example.slidebarfora_z;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.slidebarfora_z.SlideBar.OnTouchLetterChangeListener;

public class MainActivity extends Activity {

	private TextView float_letter = null;
	private ListView listView = null;
	private SlideBar slideBar = null;
	
	private ArrayAdapter<String> adapter = null;
	
	String data[] = {"A","B","宝鸡","保定","北京","C","D","E","F","G","H","I",
			"J","K","L","M","N","南京","O","P","Q","R","S","T","U","V","W","X","西安"
			,"咸阳","Y","Z","郑州"};
	
	List<String> array = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		float_letter = (TextView)findViewById(R.id.float_letter);
		listView = (ListView)findViewById(android.R.id.list);
		slideBar = (SlideBar)findViewById(R.id.slidebar);
		
		Collections.addAll(array, data);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		listView.setAdapter(adapter);
		
		slideBar.setOnTouchLetterChangeListener(new OnTouchLetterChangeListener() {
			
			@Override
			public void onTouchLetterChange(boolean isTouched, String s) {
				float_letter.setText(s);  
				if (isTouched) {  
                    float_letter.setVisibility(View.VISIBLE);  
                } else {  
                    float_letter.postDelayed(new Runnable() {  

                        @Override  
                        public void run() {  
                            float_letter.setVisibility(View.GONE);  
                        }  
                    }, 100);  
                }    
               int position  = array.indexOf(s);//这个array就是传给自定义Adapter的  
               listView.setSelection(position);//调用ListView的setSelection()方法就可实现了  
            }  
		}) ;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

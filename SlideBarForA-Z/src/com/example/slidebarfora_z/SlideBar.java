package com.example.slidebarfora_z;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SlideBar extends View {
	
	private Paint paint = new Paint();
	private OnTouchLetterChangeListener listener = null;
	//是否画出背景
	private boolean showBg = false;
	//选中的项
	private int choose = -1;
	//准备好A-Z的字母数组
	public static String[] letters = {"#","A","B","C","D","E","F","G","H","I","J","K",
									  "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	public SlideBar(Context context) {
		super(context);
	}
	
	public SlideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//获取高度和宽度
		int width = getWidth();
		int height = getHeight() - 20;
		//每个字母的高度
		int singleHeight = height/letters.length;
		
		if (showBg) {
			//画出背景
			canvas.drawColor(Color.parseColor("#55000000"));
		}
		//画字母
		for (int i = 0; i < letters.length; i++) {
			paint.setColor(Color.BLACK);
			//设置字体格式
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			// 是否抗锯齿
			paint.setAntiAlias(true);
			paint.setTextSize(20f);
			//如果此项被选中，则换一种颜色
			if (i == choose) {
				paint.setColor(Color.parseColor("#F88701"));
				//使用TextPaint的仿“粗体”设置setFakeBoldText为true。目前还无法支持仿“斜体”方法
				paint.setFakeBoldText(true);
			}
			//要画字母的坐标
			float posX = width/2 - paint.measureText(letters[i])/2;
			float posY = i*singleHeight + singleHeight;
			//画出字母
			canvas.drawText(letters[i], posX, posY, paint);
			//重置画笔
			paint.reset();
		}
	}	
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final float y = event.getY();
		//算出点击的字母索引
		final int index = (int)(y / getHeight()*letters.length);
		//保存上次点击的字母索引到oldchoose
		final int oldChoose = choose;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			showBg = true;
			if (oldChoose != index && listener != null && index > 0 && index <letters.length) {
				choose = index;
				listener.onTouchLetterChange(showBg, letters[index]);
				invalidate();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (oldChoose != index && listener != null && index > 0 && index < letters.length) {
				choose = index;
				listener.onTouchLetterChange(showBg, letters[index]);
				invalidate();
			}
			break;
		case MotionEvent.ACTION_UP:
		default:
			showBg = false;
			choose = -1;
			if (listener != null) {  
                if (index <= 0) {  
                	listener.onTouchLetterChange(showBg, "A");  
                } else if (index > 0 && index < letters.length) {  
                	listener.onTouchLetterChange(showBg, letters[index]);  
                } else if (index >= letters.length) {  
                	listener.onTouchLetterChange(showBg, "Z");  
                }  
            }  
			invalidate();
			break;
		}
		return true;
	}
	
	public void setOnTouchLetterChangeListener(OnTouchLetterChangeListener listener){
		this.listener = listener;
	}

	public interface OnTouchLetterChangeListener {
		void onTouchLetterChange(boolean isTouched, String s);
	}
}

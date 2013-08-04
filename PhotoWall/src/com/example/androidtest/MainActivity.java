package com.example.androidtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

    /** 
     * 用于展示照片墙的GridView 
     */  
    private GridView mPhotoWall;  
  
    /** 
     * GridView的适配器 
     */  
    private PhotoWallAdapter adapter;  
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        mPhotoWall = (GridView) findViewById(R.id.photo_wall);  
        adapter = new PhotoWallAdapter(this,mPhotoWall);  
        mPhotoWall.setAdapter(adapter); 
        
    }  
  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        // 退出程序时结束所有的下载任务   
        adapter.cancelAllTasks();  
    }
	
	
	
//	private void readSDCard(){
//		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//			Environment.getExternalStorageDirectory();
//			imageFile = new File(Environment.getExternalStorageDirectory().toString() + File.separator
//					+ "DCIM" + File.separator + "100LGDSC");
//			int i = 0;
//			for(File temp:imageFile.listFiles()){
//				System.out.println(++i);
//				BitmapFactory.Options options = new BitmapFactory.Options();
//				options.inJustDecodeBounds = true;
//				BitmapFactory.decodeFile(temp.toString(), options);
//				System.out.println(options.outHeight + ":" + options.outWidth + "," + options.outMimeType);
//			}
//		}
//	}
	
	public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,  
	        int reqWidth, int reqHeight) {  
	    // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
	    final BitmapFactory.Options options = new BitmapFactory.Options();  
	    options.inJustDecodeBounds = true;  
	    BitmapFactory.decodeResource(res, resId, options);  
	    // 调用上面定义的方法计算inSampleSize值  
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);  
	    // 使用获取到的inSampleSize值再次解析图片  
	    options.inJustDecodeBounds = false;  
	    return BitmapFactory.decodeResource(res, resId, options);  
	}
	
	private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
		final int height = options.outHeight;  
	    final int width = options.outWidth;  
	    int inSampleSize = 1;  
	    if (height > reqHeight || width > reqWidth) {  
	        // 计算出实际宽高和目标宽高的比率  
	        final int heightRatio = Math.round((float) height / (float) reqHeight);  
	        final int widthRatio = Math.round((float) width / (float) reqWidth);  
	        // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高  
	        // 一定都会大于等于目标的宽和高。  
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;  
	    }  
	    return inSampleSize;  
	}
	
	  
}

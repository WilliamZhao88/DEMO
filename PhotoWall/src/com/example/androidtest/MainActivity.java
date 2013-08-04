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
     * ����չʾ��Ƭǽ��GridView 
     */  
    private GridView mPhotoWall;  
  
    /** 
     * GridView�������� 
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
        // �˳�����ʱ�������е���������   
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
	    // ��һ�ν�����inJustDecodeBounds����Ϊtrue������ȡͼƬ��С  
	    final BitmapFactory.Options options = new BitmapFactory.Options();  
	    options.inJustDecodeBounds = true;  
	    BitmapFactory.decodeResource(res, resId, options);  
	    // �������涨��ķ�������inSampleSizeֵ  
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);  
	    // ʹ�û�ȡ����inSampleSizeֵ�ٴν���ͼƬ  
	    options.inJustDecodeBounds = false;  
	    return BitmapFactory.decodeResource(res, resId, options);  
	}
	
	private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
		final int height = options.outHeight;  
	    final int width = options.outWidth;  
	    int inSampleSize = 1;  
	    if (height > reqHeight || width > reqWidth) {  
	        // �����ʵ�ʿ�ߺ�Ŀ���ߵı���  
	        final int heightRatio = Math.round((float) height / (float) reqHeight);  
	        final int widthRatio = Math.round((float) width / (float) reqWidth);  
	        // ѡ���͸�����С�ı�����ΪinSampleSize��ֵ���������Ա�֤����ͼƬ�Ŀ�͸�  
	        // һ��������ڵ���Ŀ��Ŀ�͸ߡ�  
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;  
	    }  
	    return inSampleSize;  
	}
	
	  
}

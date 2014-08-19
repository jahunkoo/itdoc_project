package com.kmbridge.itdoc.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageManager {

	public static int screenWidth;
	public static int screenHeight;
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {
		Log.d("koo", "image decodeSampledBitmapFromResource start");
	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);
	   try{
		   bitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, true);
	   }catch(IllegalArgumentException e){
		   return bitmap;
	   }
	   
	    return bitmap;
	}
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
	
	/*private Bitmap setBitmapResize(Resources res,int resId){
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
		Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap, ImageManager.screenWidth, ImageManager.screenHeight/2, true);
		return resizeBitmap;
	}
	*/
	
}

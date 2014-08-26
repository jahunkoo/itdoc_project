package com.kmbridge.itdoc.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

public class ImageManager {

	public static int screenWidth;
	public static int screenHeight;
	
	// 가로길이에 맞춰서 세로길이를 자동으로 얻는 메서드
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth) {
		
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		// 위에서 options객체 안에 실제 이미지의 가로 세로 길이가 초기화된다.(outWidth,outHeight)
		Log.d("koo", "image type:" + options.outMimeType);
		
		//*****원본 이미지와 같은 비율의 reqHeight구함***************
		float rawWidth = options.outWidth;
		float rawHeight = options.outHeight;
		float rawRatio =  rawWidth/rawHeight;
		
		int reqHeight = (int) ((float)reqWidth/rawRatio);
		Log.d("koo", "original ratio:"+Float.toString(rawRatio)+"changed ratio:"+Float.toString(((float)reqWidth/(float)reqHeight)));
		//*********************************************
		
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);

		try {
			bitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, true);
		} catch (IllegalArgumentException e) {
			return bitmap;
		}
		return bitmap;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		// 위에서 options객체 안에 실제 이미지의 가로 세로 길이가 초기화된다.(outWidth,outHeight)
		Log.d("koo", "image type:" + options.outMimeType);
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);

		try {
			bitmap = Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, true);
		} catch (IllegalArgumentException e) {
			return bitmap;
		}
		return bitmap;
	}

	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			// 720,560 /360,280 /
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

/*	public static Bitmap getBitmapPart(Bitmap bitmap){
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int newWidth = width;
		int newHeight = 560;

		// calculate the scale - in this case = 0.4f
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// createa matrix for the manipulation
		Matrix matrix = new Matrix();
		matrix.
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);

		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, 
		                  width, height, matrix, true); 

		// make a Drawable from Bitmap to allow to set the BitMap 
		// to the ImageView, ImageButton or what ever
		//BitmapDrawable bmd = new BitmapDrawable(resizedBitmap);
		
		return resizedBitmap;
	}*/
	
	public static Bitmap getRoundedBitmap(Bitmap bmp, int radius) {
		Bitmap sbmp;

		if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
			float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
			float factor = smallest / radius;
			sbmp = Bitmap.createScaledBitmap(bmp,
					(int) (bmp.getWidth() / factor),
					(int) (bmp.getHeight() / factor), false);
		} else {
			sbmp = bmp;
		}

		Bitmap output = Bitmap.createBitmap(radius, radius, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xffa19774;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, radius, radius);

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.parseColor("#BAB399"));
		canvas.drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f,
				radius / 2 + 0.1f, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(sbmp, rect, rect, paint);

		return output;
	}
}

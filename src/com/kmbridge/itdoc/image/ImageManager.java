package com.kmbridge.itdoc.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

}

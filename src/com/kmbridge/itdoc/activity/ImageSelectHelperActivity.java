package com.kmbridge.itdoc.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kmbridge.itdoc.exception.RecordNotFoundException;
import com.kmbridge.itdoc.thread.ConnectionThread;
import com.kmbridge.itdoc.thread.ProfilePictureConnectThread;
import com.kmbridge.itdoc.util.ItDocConstants;
import com.kmbridge.itdoc.util.Sentence;
import com.kmbridge.itdoc.util.SharedPreferenceUtil;

/**
 * 안드로이드에서 이미지를 불러오는 것을 도와주는  
 * @author kein
 * 
 */
public class ImageSelectHelperActivity extends Activity {
	/** Buttons for selector dialog */
	private View mBtnGallery = null, mBtnCamera = null, mBtnCancel = null;

	private final int REQ_CODE_PICK_GALLERY = 900001;
	private final int REQ_CODE_PICK_CAMERA = 900002;
	private final int REQ_CODE_PICK_CROP = 900003;

	
	/**
	 * Call this to start!
	 */
	public void startSelectImage() {
		if (!checkWriteExternalPermission()) {
			showAlert("we need android.permission.WRITE_EXTERNAL_STORAGE");
			return;
		}
		if (!checkSDisAvailable()) {
			showAlert("Check External Storage.");
			return;
		}
		if (mBtnGallery == null) {
			setDefaultButtons();
		}
		setButtonsClick();
		showSelectDialog();
		Log.d("koo", "startSelectImage()");
	}

	public File getSelectedImageFile() {
		return getTempImageFile();
	}

	private boolean mCropRequested = false;

	/**
	 * crop 이 필요한 경우 설정함. 설정하지 않으면 crop 하지 않음.
	 * 
	 * @param width
	 *            crop size width.
	 * @param height
	 *            crop size height.
	 */
	private int mCropAspectWidth = 1, mCropAspectHeight = 1;

	public void setCropOption(int aspectX, int aspectY) {
		mCropRequested = true;
		mCropAspectWidth = aspectX;
		mCropAspectHeight = aspectY;
	}

	/**
	 * 사용할 이미지의 최대 크기 설정. 가로, 세로 지정한 크기보다 작은 사이즈로 이미지 크기를 조절함. default size :
	 * 500
	 * 
	 * @param sizePixel
	 *            기본 500
	 */
	private int mImageSizeBoundary = 500;

	public void setImageSizeBoundary(int sizePixel) {
		mImageSizeBoundary = sizePixel;
	}

	private boolean checkWriteExternalPermission() {
		String permission = "android.permission.WRITE_EXTERNAL_STORAGE";
		int res = checkCallingOrSelfPermission(permission);
		return (res == PackageManager.PERMISSION_GRANTED);
	}

	private boolean checkSDisAvailable() {
		String state = Environment.getExternalStorageState();
		return state.equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * Set your own button views for selector dialog.
	 */
	public void setCustomButtons(View btnGallery, View btnCamera, View btnCancel) {
		if (btnGallery == null || btnCamera == null || btnCancel == null) {
			showAlert("All buttons should not null.");
		} else {
			mBtnGallery = btnGallery;
			mBtnCamera = btnCamera;
			mBtnCancel = btnCancel;
		}
	}

	/**
	 * Set default buttons for selector dialog, unless you set.
	 */
	private void setDefaultButtons() {
		Button btn1 = new Button(this);
		Button btn2 = new Button(this);
		Button btn3 = new Button(this);
		btn1.setText("사진 선택하기");
		btn2.setText("사진 촬영하기");
		btn3.setText("취소");
		mBtnGallery = btn1;
		mBtnCamera = btn2;
		mBtnCancel = btn3;
	}

	public File getTempImageFile() {
		File path = new File(Environment.getExternalStorageDirectory()
				+ "/Android/data/" + getPackageName() + "/temp/");
		if (!path.exists()) {
			path.mkdirs();
		}
		File file = new File(path, "tempimage.png");
		return file;
	}

	private void setButtonsClick() {
		mBtnGallery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSelectDialog.dismiss();
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_GALLERY);
			}
		});
		mBtnCamera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSelectDialog.dismiss();
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(getTempImageFile()));
				intent.putExtra("return-data", true);
				startActivityForResult(intent, REQ_CODE_PICK_CAMERA);
			}
		});
		mBtnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mSelectDialog != null && mSelectDialog.isShowing()) {
					mSelectDialog.dismiss();
				}
			}
		});
	}

	private Dialog mSelectDialog;

	private void showSelectDialog() {
		if (mSelectDialog == null) {
			mSelectDialog = new Dialog(this);
			mSelectDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			LinearLayout linear = new LinearLayout(this);
			linear.setOrientation(LinearLayout.VERTICAL);
			linear.addView(mBtnGallery);
			linear.addView(mBtnCamera);
			linear.addView(mBtnCancel);
			int dialogWidth = getResources().getDisplayMetrics().widthPixels / 2;
			if (dialogWidth / 2 > 700) {
				dialogWidth = 700;
			}
			mSelectDialog.setContentView(linear, new LayoutParams(dialogWidth,
					LayoutParams.WRAP_CONTENT));
		}
		mSelectDialog.show();
	}

	private void showAlert(String msg) {
		new AlertDialog.Builder(this).setTitle("Error").setMessage(msg)
				.setPositiveButton(android.R.string.ok, null).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("koo", "onActivityResult requestCode:"+requestCode+"/resultCode:"+resultCode);
		if (requestCode == REQ_CODE_PICK_GALLERY
				&& resultCode == Activity.RESULT_OK) {
			Log.d("koo", "PICK_GALLERY");
			// 갤러리의 경우 곧바로 data 에 uri가 넘어옴.
			Uri uri = data.getData();
			copyUriToFile(uri, getTempImageFile());
			if (mCropRequested) {
				cropImage();
			} else {
				doFinalProcess();
			}
		} else if (requestCode == REQ_CODE_PICK_CAMERA
				&& resultCode == Activity.RESULT_OK) {
			Log.d("koo", "PICK_CAMERA");
			// 카메라의 경우 file 로 결과물이 돌아옴.
			// 카메라 회전 보정.
			correctCameraOrientation(getTempImageFile());
			if (mCropRequested) {
				cropImage();
			} else {
				doFinalProcess();
			}
		} else if (requestCode == REQ_CODE_PICK_CROP
				&& resultCode == Activity.RESULT_OK) {
			Log.d("koo", "PICK_CROP");
			// crop 한 결과는 file로 돌아옴.
			doFinalProcess();
		} else {
			Log.d("koo", "ELSE");
			super.onActivityResult(requestCode, resultCode, data);
		}
		Log.d("koo", "onActivityResult");
	}

	protected Activity callActivity;
	/**
	 * 서버로 이미지를 업로드 한 후, 업로드에 성공하면 사용자 뷰에 해당 이미지를 보여줌. 
	 */
	private void doFinalProcess() {
		
		// 저장소 객체를 생성
		//SharedPreferences shared_user_info = getSharedPreferences("user_info", 0);
		//String email = shared_user_info.getString("user_email", "defaultemail@email.com");
		String email = null;
		
		try {
			email = SharedPreferenceUtil.getData(this, ItDocConstants.SHARED_KEY_EMAIL);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//ConnectionBridge bridge = new ConnectionBridge();
		//bridge.insertImage("insertPicture", getTempImageFile(), this, email);
		//ProfilePictureConnectThread thread = new ProfilePictureConnectThread("insertPicture", getTempImageFile(), this, email);
		//thread.start();
		
		// sample size 를 적용하여 bitmap load.popo
		Bitmap bitmap = loadImageWithSampleSize(getTempImageFile());
		// image boundary size 에 맞도록 이미지 축소.
		bitmap = resizeImageWithinBoundary(bitmap);
		// 결과 file 을 얻어갈 수 있는 메서드 제공.
		saveBitmapToFile(bitmap);
		Bitmap bm = BitmapFactory.decodeFile(getTempImageFile().getAbsolutePath());
		//(ImageView) findViewById(R.id.ivImageSelected)).setImageBitmap(bm);
		
		//Toast.makeText(this, Sentence.successProfileImage,Toast.LENGTH_SHORT).show();
		
		//Intent intent = new Intent(this,UserManagerActivity.class);
		//startActivity(intent);
		
		Log.d("koo", "doFinalProcess()");
	}

	public void saveBitmapToFile(Bitmap bitmap) {
		File target = getTempImageFile();
		try {
			FileOutputStream fos = new FileOutputStream(target, false);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 이미지 사이즈 수정 후, 카메라 rotation 정보가 있으면 회전 보정함. */
	private void correctCameraOrientation(File imgFile) {
		Bitmap bitmap = loadImageWithSampleSize(imgFile);
		try {
			ExifInterface exif = new ExifInterface(imgFile.getAbsolutePath());
			int exifOrientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			int exifRotateDegree = exifOrientationToDegrees(exifOrientation);
			bitmap = rotateImage(bitmap, exifRotateDegree);
			saveBitmapToFile(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Bitmap rotateImage(Bitmap bitmap, int degrees) {
		if (degrees != 0 && bitmap != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) bitmap.getWidth() / 2,
					(float) bitmap.getHeight() / 2);
			try {
				Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), m, true);
				if (bitmap != converted) {
					bitmap.recycle();
					bitmap = converted;
				}
			} catch (OutOfMemoryError ex) {
			}
		}
		return bitmap;
	}

	/**
	 * EXIF정보를 회전각도로 변환하는 메서드
	 * 
	 * @param exifOrientation
	 *            EXIF 회전각
	 * @return 실제 각도
	 */
	private int exifOrientationToDegrees(int exifOrientation) {
		if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
			return 90;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
			return 180;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
			return 270;
		}
		return 0;
	}

	/** 원하는 크기의 이미지로 options 설정. */
	public Bitmap loadImageWithSampleSize(File file) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getAbsolutePath(), options);
		int width = options.outWidth;
		int height = options.outHeight;
		int longSide = Math.max(width, height);
		int sampleSize = 1;
		if (longSide > mImageSizeBoundary) {
			sampleSize = longSide / mImageSizeBoundary;
		}
		options.inJustDecodeBounds = false;
		options.inSampleSize = sampleSize;
		options.inPurgeable = true;
		options.inDither = false;

		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),
				options);
		return bitmap;
	}

	/**
	 * mImageSizeBoundary 크기로 이미지 크기 조정. mImageSizeBoundary 보다 작은 경우 resize하지
	 * 않음.
	 */
	public Bitmap resizeImageWithinBoundary(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		if (width > height) {
			if (width > mImageSizeBoundary) {
				bitmap = resizeBitmapWithWidth(bitmap, mImageSizeBoundary);
			}
		} else {
			if (height > mImageSizeBoundary) {
				bitmap = resizeBitmapWithHeight(bitmap, mImageSizeBoundary);
			}
		}
		return bitmap;
	}

	private Bitmap resizeBitmapWithHeight(Bitmap source, int wantedHeight) {
		if (source == null)
			return null;

		int width = source.getWidth();
		int height = source.getHeight();

		float resizeFactor = wantedHeight * 1f / height;

		int targetWidth, targetHeight;
		targetWidth = (int) (width * resizeFactor);
		targetHeight = (int) (height * resizeFactor);

		Bitmap resizedBitmap = Bitmap.createScaledBitmap(source, targetWidth,
				targetHeight, true);

		return resizedBitmap;
	}

	private Bitmap resizeBitmapWithWidth(Bitmap source, int wantedWidth) {
		if (source == null)
			return null;

		int width = source.getWidth();
		int height = source.getHeight();

		float resizeFactor = wantedWidth * 1f / width;

		int targetWidth, targetHeight;
		targetWidth = (int) (width * resizeFactor);
		targetHeight = (int) (height * resizeFactor);

		Bitmap resizedBitmap = Bitmap.createScaledBitmap(source, targetWidth,
				targetHeight, true);

		return resizedBitmap;
	}

	private void copyUriToFile(Uri srcUri, File target) {
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel fcin = null;
		FileChannel fcout = null;
		try {
			// 스트림 생성
			inputStream = (FileInputStream) getContentResolver()
					.openInputStream(srcUri);
			outputStream = new FileOutputStream(target);

			// 채널 생성
			fcin = inputStream.getChannel();
			fcout = outputStream.getChannel();

			// 채널을 통한 스트림 전송
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fcout.close();
			} catch (IOException ioe) {
			}
			try {
				fcin.close();
			} catch (IOException ioe) {
			}
			try {
				outputStream.close();
			} catch (IOException ioe) {
			}
			try {
				inputStream.close();
			} catch (IOException ioe) {
			}
		}
	}

	/**
	 *  cropImage()메서드가 resultCode를 0으로 반환하기 때문에 crop이후 dofinalprocess()로 넘어가지지 않음. 이유를 알아내자. 
	 *  0 == Activity.RESULT_CANCELED
	 *  Standard activity result: operation canceled. 
	 */
	private void cropImage() {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setType("image/*");
		List<ResolveInfo> cropToolLists = getPackageManager()
				.queryIntentActivities(intent, 0);
		int size = cropToolLists.size();
		if (size == 0) {
			// crop 을 처리할 앱이 없음. 곧바로 처리.
			doFinalProcess();
		} else {
			intent.setData(Uri.fromFile(getTempImageFile()));
			intent.putExtra("aspectX", mCropAspectWidth);
			intent.putExtra("aspectY", mCropAspectHeight);
			intent.putExtra("output", Uri.fromFile(getTempImageFile()));
			Intent i = new Intent(intent);
			ResolveInfo res = cropToolLists.get(0);
			i.setComponent(new ComponentName(res.activityInfo.packageName,
					res.activityInfo.name));
			startActivityForResult(i, REQ_CODE_PICK_CROP);
		}
	}
}

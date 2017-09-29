package sbs.com;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import sbs.com.Scene.SushiMainMenuScene;
import sbs.com.object.Define;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class GameActivity extends Activity {
	private CCGLSurfaceView mGLSurfaceView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		mGLSurfaceView = new CCGLSurfaceView(this);
		//CCDirector.sharedDirector().setScreenSize(Define.DEFAULT_WIDTH, Define.DEFAULT_HEIGHT);
		CCDirector.sharedDirector().setScreenSize(CCDirector.sharedDirector().winSize().width, 
				CCDirector.sharedDirector().winSize().height);
 		CCDirector.sharedDirector().getActivity().setContentView(mGLSurfaceView, createLayoutParams());
		
		// Following is the most important, so added by KS. Kil
		Define.mySharedPreferenceWrite = getSharedPreferences(Define.KeyForHighScoreInLocal, Activity.MODE_WORLD_WRITEABLE);
		Define.mySharedPreferenceRead = getSharedPreferences(Define.KeyForHighScoreInLocal, Activity.MODE_WORLD_READABLE);
	}
	
	@Override public void onStart() {
		super.onStart();
		CCDirector.sharedDirector().attachInView(mGLSurfaceView);
		CCDirector.sharedDirector().setDeviceOrientation(CCDirector.kCCDeviceOrientationPortrait);
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 60);
		CCDirector.sharedDirector().setDisplayFPS(false);
//	   CCTexture2D.setDefaultAlphaPixelFormat(Config.ARGB_8888);
		Log.d("nala", "0");

		CCDirector.sharedDirector().runWithScene(SushiMainMenuScene.scene());
	};

	@Override public void onPause() {
		super.onPause();
		CCDirector.sharedDirector().pause();
		finish();
	}

	@Override public void onResume() {
		super.onResume();
		CCDirector.sharedDirector().resume();
	}

	@Override public void onDestroy() {
		super.onDestroy();
		Define.stopSound();
		CCDirector.sharedDirector().end();
	}

	private LayoutParams createLayoutParams() {
		final DisplayMetrics pDisplayMetrics = new DisplayMetrics();
		CCDirector.sharedDirector().getActivity().getWindowManager().getDefaultDisplay().getMetrics(pDisplayMetrics);
		
		final float mRatio = (float)Define.DEFAULT_WIDTH / Define.DEFAULT_HEIGHT;
		final float realRatio = (float)pDisplayMetrics.widthPixels / pDisplayMetrics.heightPixels;

		final int width;
		final int height;
		if(realRatio < mRatio) {
			width = pDisplayMetrics.widthPixels;
			height = Math.round(width / mRatio);
		} else {
			height = pDisplayMetrics.heightPixels;
			width = Math.round(height * mRatio);
		}

		final LayoutParams layoutParams = new LayoutParams(width, height);

		layoutParams.gravity = Gravity.CENTER;
		return layoutParams;
	}
	
	public void InitGameCenter() {
	}
}

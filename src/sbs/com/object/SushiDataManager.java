package sbs.com.object;

import android.content.SharedPreferences;

import org.cocos2d.nodes.CCDirector;

import sbs.com.object.Define;

public class SushiDataManager {
	public int easyScore;
	public int normalScore;
	public int hardScore;
	public int playCount;
	public int glassIn;
	public int maxCombo;
	public int maxBounce;
	public boolean backAudio;
	public boolean effectAudio;
	public int difficulty;
	public float screenWidth;
	public float screenHeight;
	public int throwCount;
	public int volumeInfo;
	public int twitterInfo;
	
	public SushiDataManager() {
		super();
		screenWidth = CCDirector.sharedDirector().winSize().width;	screenHeight = CCDirector.sharedDirector().winSize().height;
		Define.scaleX = screenWidth / Define.DEFAULT_WIDTH;			Define.scaleY = screenHeight / Define.DEFAULT_HEIGHT;
		
		easyScore = 0;		normalScore = 0;		hardScore = 0;
		maxCombo = 0;
		maxBounce = 0;
		glassIn = 0;
		playCount = 0;
		difficulty = 0;
		throwCount = 0;
		volumeInfo = 0;
		twitterInfo = 1;
		backAudio = true;		effectAudio = true;
		loadHighScoreInfo();
	}
	
	public void loadHighScoreInfo() {
		easyScore = Define.mySharedPreferenceRead.getInt("easyScore", easyScore);
		normalScore = Define.mySharedPreferenceRead.getInt("normalScore", normalScore);
		hardScore = Define.mySharedPreferenceRead.getInt("hardScore", hardScore);
		playCount = Define.mySharedPreferenceRead.getInt("playCount", playCount);
		glassIn = Define.mySharedPreferenceRead.getInt("glassIn", glassIn);
		throwCount = Define.mySharedPreferenceRead.getInt("throwCount", throwCount);
		maxCombo = Define.mySharedPreferenceRead.getInt("maxCombo", maxCombo);
		maxBounce = Define.mySharedPreferenceRead.getInt("maxBounce", maxBounce);
		volumeInfo = Define.mySharedPreferenceRead.getInt("volumeInfo", volumeInfo);
		twitterInfo = Define.mySharedPreferenceRead.getInt("twitterInfo", twitterInfo);
	}

	public void saveHighScoreInfo() {
		SharedPreferences.Editor editor = Define.mySharedPreferenceWrite.edit();
		
		editor.putInt("easyScore", easyScore);
		editor.putInt("normalScore", normalScore);
		editor.putInt("hardScore", hardScore);
		editor.putInt("playCount", playCount);
		editor.putInt("glassIn", glassIn);
		editor.putInt("throwCount", throwCount);
		editor.putInt("maxCombo", maxCombo);
		editor.putInt("maxBounce", maxBounce);
		editor.putInt("volumeInfo", volumeInfo);
		editor.putInt("twitterInfo", twitterInfo);
		
		editor.commit();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////// Singleton ///////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	private static SushiDataManager sharedDataManager = null;
	
	public static SushiDataManager sharedManager() {
		if (sharedDataManager == null){
			sharedDataManager = new SushiDataManager();
			SushiDataManager.sharedManager().screenWidth = CCDirector.sharedDirector().winSize().width;
			SushiDataManager.sharedManager().screenHeight = CCDirector.sharedDirector().winSize().height;
		}
		return sharedDataManager;
	}
}
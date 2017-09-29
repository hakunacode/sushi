package sbs.com.object;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

import android.content.SharedPreferences;

public class Define {
	public final static int DEFAULT_WIDTH = 768;
	public final static int DEFAULT_HEIGHT = 1024;
	
	public static final int MAIN_BACK = 10;
	public static final int MENU_SEL = 11;
	public static final int GLASSIN = 12;
	public static final int GLASSHIT = 13;
	public static final int BALLDOWN = 14;

	public static float scaleX = 1f;
	public static float scaleY = 1f;

	public static int ddz = 6;

	public static float LAST_VIEW_RATE = 0.2f;
	public static int DESK_LENGTH = 600;//213
	public static int DESK_LEFT_POSITION = -100;
	public static int DESK_RIGHT_POSITION = 500;

	public static int CUP_HEIGHT = 110;
	public static int CUP_R = 30;

	public static int BALL_R = 20;

	public static String HighScoreFileName = "LuckyBallHighScoreFile";
	public static String KeyForHighScoreInLocal = "HighScores";

	public static int DIFFICULTY_EASY = 1;
	public static int DIFFICULTY_NORMAL = 2;
	public static int DIFFICULTY_HARD = 3;

	public static int OPTION_ON = 0;
	public static int OPTION_OFF = 1;

	public static float pi = 3.141592f;

	public static String VERSION = "2.0";

	public static SharedPreferences mySharedPreferenceWrite = null;
	public static SharedPreferences mySharedPreferenceRead = null;

	public static void setScale(CCNode node) {
		SushiDataManager.sharedManager();
		node.setScaleX(scaleX);
		node.setScaleY(scaleY);
	}
	public static CGPoint ccp(float fx, float fy){
		return CGPoint.ccp(fx * SushiDataManager.sharedManager().screenWidth / DEFAULT_WIDTH, fy * SushiDataManager.sharedManager().screenHeight / DEFAULT_HEIGHT);
	}
	public static void setScaleDelta(CCNode node, float delta) {
		node.setScaleX(scaleX * delta);
		node.setScaleY(scaleY * delta);
	}
	//for Sound and Effect
	public static void playSound(int id) {
	    if (SushiDataManager.sharedManager().volumeInfo == Define.OPTION_OFF)
	        return;
		stopSound();
		if (SushiDataManager.sharedManager().backAudio == true){
			SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), id, true);
		}
	}

	public static void playEffect(int effID) {
	    if (SushiDataManager.sharedManager().volumeInfo == Define.OPTION_OFF)
	        return;
		if (SushiDataManager.sharedManager().backAudio == true) {
			SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), effID);
		}
	}

	public static void stopSound() {
		SoundEngine.sharedEngine().realesAllSounds();
	}

	public static void pauseSound() {
		SoundEngine.sharedEngine().pauseSound();
	}

	public static void resumeSound() {
		SoundEngine.sharedEngine().resumeSound();
	}
};

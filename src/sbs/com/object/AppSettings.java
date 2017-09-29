package sbs.com.object;

import android.content.SharedPreferences;

public class AppSettings {

	public AppSettings() {
	}

	public static int getIntValue(String str){
		return Define.mySharedPreferenceRead.getInt(str, 0);
	}
	public static void setIntValueWithName(int i, String str){
		SharedPreferences.Editor editor = Define.mySharedPreferenceWrite.edit();
		editor.putInt(str, i);
		editor.commit();
	}
	public static boolean getBoolValue(String str){
		return Define.mySharedPreferenceRead.getBoolean(str, true);
	}
	public static void setBoolValueWithName(boolean b, String str){
		SharedPreferences.Editor editor = Define.mySharedPreferenceWrite.edit();
		editor.putBoolean(str, b);
		editor.commit();
	}
}

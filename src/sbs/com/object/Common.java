package sbs.com.object;

import java.util.ArrayList;

import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCTextureCache;

public class Common {

	public Common() {
		// TODO Auto-generated constructor stub
	}
	//
	
//	#import "AppDelegate.h"
	
//	@interface  Common()
	
	public static float adjustScale() {
		
		float fScale = 1.0f;
		
		return fScale;
	}
	//------------------------------- Get the Animate by the animation string. -------------------//
	public static CCAnimate getAnimate(String strAnimName,int count,float fDelay){
		return getAnimate(strAnimName,1,count,1,fDelay);
	}
	public static CCAnimate getAnimate(String strAnimName,int first,int last,float fDelay){
		return getAnimate(strAnimName,first,last,first,fDelay);
	}
	
	public static CCAnimate getAnimate(String strAnimName,int first,int last,int start,float fDelay){
		return getAnimate(strAnimName,first,last,first,fDelay,true);
	}
	
	public static CCAnimate getAnimate(String strAnimName,int first,int last,int start,float fDelay,boolean highguality){
		ArrayList<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>(0);
		
		String fileName;
		CCAnimation animation;
		
		animation = CCAnimation.animation(strAnimName, fDelay, frames);
		
		for (int i = start; i <= last; i++){
			fileName = String.format("%s%02d", strAnimName, i);
			addFrameWithName(animation, fileName, highguality);
		}
		
		for (int i = first; i < start; i++)
		{
			fileName = String.format("%s%02d", strAnimName, i);
			addFrameWithName(animation , fileName ,highguality);
		}
		return CCAnimate.action(animation);
	}
	
	public static void addFrameWithName(CCAnimation animation, String fileName ,boolean highguality){
		ResourceManager resManager = ResourceManager.sharedResourceManager();
		if (highguality) {
			animation.addFrame(resManager.getSpriteFrameWithName(fileName));
	//		animation addFrameWithFilename,resManager makeFileName,fileName ext,"png";
		}
		else {
		//	animation addFrame,resManager getSpriteFrameWithName_Zombie,fileName;
	//		animation addFrameWithFilename,resManager makeFileName,fileName ext,"png";
		}
	}
	
	public void dealloc(){
		CCTextureCache.sharedTextureCache().removeUnusedTextures();	
	}
	
	public static void purgeCachedData(){
	//	CCDirector sharedDirector purgeCachedData;
		CCTextureCache.sharedTextureCache().removeUnusedTextures();	
	}
}

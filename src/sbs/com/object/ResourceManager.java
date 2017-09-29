package sbs.com.object;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteFrameCache;

///		#import "AppDelegate.h"
public class ResourceManager {
	private static ResourceManager _sharedResource = null;

	public static ResourceManager sharedResourceManager() {
		if (_sharedResource == null){
			_sharedResource = new ResourceManager();
		}
		return _sharedResource;
	}
	public ResourceManager(){
		
	}
	public static void releaseResourceManager(){
		if (_sharedResource == null) 
		{
			_sharedResource = null;
		}
	}
	
	public String makeFileName(String name, String ext){
		String	strSuffix = null;
		String	strFile = null;
		
/*		AppDelegate* app = [AppDelegate getDelegate];
		
		if( [app isIPad] ) {
			strSuffix = @"@3x";
		} else if( [app isIPhone4] ) {
			strSuffix = @"@2x";
		} else {
			strSuffix = @"";
		}*/
		strSuffix = "@3x";
		strFile = String.format("%s%s.%s", name, strSuffix, ext);
		return strFile;
	}		
	public void loadData(String strName){
		CCSpriteFrameCache.purgeSharedSpriteFrameCache();
		CCSpriteFrameCache frameCache = CCSpriteFrameCache.sharedSpriteFrameCache();
		String	strFile = makeFileName(strName, "plist");
		frameCache.addSpriteFrames(strFile);
		//	NSLog(@"Resource Manager completed loading");
		//	[self print_memory_size];
	}
		
	public void unloadData(String strName){
		CCSpriteFrameCache frameCache = CCSpriteFrameCache.sharedSpriteFrameCache();
		String	strFile = makeFileName(strName, "plist");
		frameCache.removeSpriteFrame(strFile);
		//	CCLOG(@"Resource Manager completed unloading");
		//	[self print_memory_size];
	}
	public CCSprite getTextureWithName(String textureName){
		String	strFile = makeFileName(textureName ,"png");
		CCSprite sprite = CCSprite.sprite(strFile, true);
		return sprite;
	}
	
	public CCSpriteFrame getSpriteFrameWithName(String frameName) {
		CCSpriteFrameCache frameCache = CCSpriteFrameCache.sharedSpriteFrameCache();
		String	strFile = makeFileName(frameName, "png");
		CCSpriteFrame frame = frameCache.getSpriteFrame(strFile);
		return frame;
	}
	
	public CCSprite getSpriteWithName(String strSpriteName){
		String	strFile = makeFileName(strSpriteName, "png");
		CCSprite sprite = CCSprite.sprite(strFile);
		return sprite;
	}
		
/*	private CCSprite getTextureWithId(int textureId){
		return textures[textureId];
	}
	*/
}

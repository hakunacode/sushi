package sbs.com.Scene;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;

import sbs.com.gamecomponent.SushiGameLayer;

public class SushiGameScene extends CCScene {
	public static CCScene scene(){
		return new SushiGameScene();
	}
	private SushiGameScene(){
		CCLayer layer = new SushiGameLayer(this);
		addChild(layer);
	}
}

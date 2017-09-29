package sbs.com.Scene;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;

import sbs.com.Layer.SushiMainMenuLayer;

public class SushiMainMenuScene extends CCScene {
	private SushiMainMenuLayer menuLayer;

	public static CCScene scene(){
		return new SushiMainMenuScene();
	}
	private SushiMainMenuScene() {
		super();
		menuLayer = new SushiMainMenuLayer(this);
		addChild(menuLayer);
	}
	public void highScores() {
	}
	
	public void startGame() {
		CCDirector.sharedDirector().replaceScene (SushiSelectLevelScene.scene());
	}
	
	public void onOption() {
		CCDirector.sharedDirector().replaceScene(SushiOptionScene.scene());
	}
	
	public void gameCenter() {
	}
}

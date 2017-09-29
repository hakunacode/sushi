package sbs.com.Layer;




import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;

import sbs.com.R;
import sbs.com.Scene.SushiMainMenuScene;
import sbs.com.ccframework.GrowButton;
import sbs.com.object.Define;
import sbs.com.object.ResourceManager;

public class SushiMainMenuLayer extends CCLayer {
	private SushiMainMenuScene parentScene;

	public SushiMainMenuLayer(CCScene scene){
		super();
		parentScene = (SushiMainMenuScene)scene;

		Define.setScale(this);
		setAnchorPoint(0, 0);
		setPosition(0,  0);

		createBG();
		createTitle();
		createMovingBar();
		createButton();
		Define.playSound(R.raw.title_bg);
	}
	private void createBG(){
		CCSprite background = ResourceManager.sharedResourceManager().getSpriteWithName("menu/fish_256_256");
		background.setPosition(384, 512);
		addChild(background);
	}
	public void  createMovingBar()
	{
		MainBarLayer  bar1 = new MainBarLayer();
		bar1.moveBar(-1);
		bar1.setPosition(384, 962);
	   
		addChild(bar1);
		
		MainBarLayer  bar2 = new MainBarLayer();
		bar2.moveBar(1);
		bar2.setPosition(384, 62);
		addChild(bar2);
	}
	private void  createTitle()
	{
		 MainTitleLayer title = new MainTitleLayer();
		 title.setPosition(384, 700);
		 addChild(title);
	}
	private void  createButton()
	{
		 float fx = 384, fy = 350;
		 
		GrowButton  btnPlay = GrowButton.buttonWithSpriteFrame("menu/botton_play1", "menu/botton_play2", this, "restartGame");
		btnPlay.setPosition(fx, fy);
		addChild(btnPlay);

		fy -= 100;
		GrowButton  btnOptions = GrowButton.buttonWithSpriteFrame("menu/botton_options1", "menu/botton_options2", this, "onOption");
		btnOptions.setPosition(fx, fy);
		addChild(btnOptions);

		fy -= 100;
		GrowButton  btnHighscores = GrowButton.buttonWithSpriteFrame("menu/botton_highscore1", "menu/botton_highscore2", this, "highScores");
		btnHighscores.setPosition(fx, fy);
		addChild(btnHighscores);
	}

	////Method/////////////////////////////////////
	public void onOption(Object sender) {
		Define.playEffect(R.raw.buttonclick);
		parentScene.onOption();
	}
	public void highScores(Object sender) {
		Define.playEffect(R.raw.buttonclick);
		parentScene.highScores();
	}
	public void restartGame(Object sender) {
		Define.playEffect(R.raw.buttonclick);
		parentScene.startGame();
	}
}

package sbs.com.Scene;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCTransitionScene;

import sbs.com.R;
import sbs.com.ccframework.Button;
import sbs.com.ccframework.GrowButton;
import sbs.com.object.AppSettings;
import sbs.com.object.Define;
import sbs.com.object.GameInfo;
import sbs.com.object.ResourceManager;

public class SushiSelectLevelScene extends CCScene {

	public static SushiSelectLevelScene scene(){
		return new SushiSelectLevelScene();
	}
	private SushiSelectLevelScene() {
		super();
		SushiSelectLevelLayer layerSelectLevel = new SushiSelectLevelLayer(this);
		addChild(layerSelectLevel);
		layerSelectLevel.parentScene = this;
	}

	public void gotoMain() {		
	    CCScene scene = SushiMainMenuScene.scene();
	    CCTransitionScene ts = CCFadeTransition.transition(1.0f, scene);
	    CCDirector.sharedDirector().replaceScene(ts);
	}
	public class SushiSelectLevelLayer extends CCLayer {
		public SushiSelectLevelScene parentScene;
		private String path = "menu/";
		
		private int m_nFinishLevel = 0;
		public SushiSelectLevelLayer(CCScene scene) {
			super();
			Define.playSound(R.raw.levelselect_bg);

			parentScene = (SushiSelectLevelScene)scene;
			
			Define.setScale(this);
			setAnchorPoint(0, 0);
			setPosition(0, 0);

			int nLevel = AppSettings.getIntValue("CurLevel");
			m_nFinishLevel = nLevel;
			
			createBG();
			createBtns();
		}
		private void createBG(){
			CCSprite spBack = ResourceManager.sharedResourceManager().getSpriteWithName(path + "LevelSelect_background");
			spBack.setPosition(384, 512);
			addChild(spBack);
		}
		private void createBtns(){
			float fx, fy;
			GrowButton btnBack = GrowButton.buttonWithSpriteFrame(path + "back", path + "back", this, "actionBack");
			fx = 637.5f; fy = 75;
			btnBack.setPosition(fx, fy);
			addChild(btnBack);
			for (int i = 0; i < GameInfo.LEVEL_COUNT; i++){
				fx = GameInfo.g_nLevelPos[i][0];
				fy = GameInfo.g_nLevelPos[i][1];
				int nType = GameInfo.g_nLevelPos[i][2];
				String str = null;
				float fIndentX = 0;
				float fIndentY = 0;
				switch (nType){
					case 0:
						str = "btn_level_nor";
						fIndentX = 0;	fIndentY = 5;
						break;
					case 1:
						str = "btn_house_1";
						fIndentX = 0;	fIndentY = 15;
						break;
					case 2:
						str = "btn_house_2";
						fIndentX = 0;	fIndentY = 15;
						break;
					case 3:
						str = "btn_house_3";
						fIndentX = 15;	fIndentY = 15;
						break;
					default:
						fIndentX = 0;	fIndentY = 0;
						break;
				}
				Button button = Button.buttonWithSpriteID(path + str, path + str ,this, "actionButtons", i);
				button.setPosition(fx, fy);
				addChild(button);
				if(i <= m_nFinishLevel){
					CCSprite spSel = ResourceManager.sharedResourceManager().getSpriteWithName(path + "btn_level_sel");
					spSel.setPosition(fx + fIndentX, fy + fIndentY);
					addChild(spSel);
				}
			}
		}
		public void actionButtons(Object sender) {
			Define.playEffect(R.raw.buttonclick);

		    CCMenuItem btn = (CCMenuItem)sender;
		    int nLevel = btn.getTag();
		    
		    if(nLevel > m_nFinishLevel + 1)
		    	return;
		    AppSettings.setIntValueWithName(nLevel, "CurLevel");
		    
		    CCScene scene = SushiGameScene.scene();
		    CCTransitionScene ts = CCFadeTransition.transition(1.0f ,scene);
		    CCDirector.sharedDirector().replaceScene(ts);
		}

		public void actionBack(Object sender) {
			Define.playEffect(R.raw.buttonclick);
			parentScene.gotoMain();
		}
	}
}

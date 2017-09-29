package sbs.com.Scene;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCTransitionScene;

import sbs.com.R;
import sbs.com.ccframework.Button;
import sbs.com.ccframework.GrowButton;
import sbs.com.object.AppSettings;
import sbs.com.object.Define;
import sbs.com.object.ResourceManager;
import sbs.com.object.SushiDataManager;

public class SushiOptionScene extends CCScene {

	public static CCScene scene(){
		return new SushiOptionScene();
	}
	public SushiOptionScene() {
		SushiOptionLayer helpLayer = new SushiOptionLayer(this);
		addChild(helpLayer);
	}

	public void gotoMain() {
	    CCScene scene = SushiMainMenuScene.scene();
	    CCTransitionScene ts = CCFadeTransition.transition(1.0f, scene);
	    CCDirector.sharedDirector().replaceScene(ts);
	}
	public class SushiOptionLayer extends CCLayer {
		private String path = "menu/";
		private SushiOptionScene parentScene;

		private Button m_btnBGM, m_btnEffect;
		
		boolean m_bBGM, m_bEffect;
		public SushiOptionLayer(CCScene scene) {
			super();
			parentScene = (SushiOptionScene)scene;

			Define.setScale(this);
			setAnchorPoint(0, 0);
			setPosition(0, 0);
			
	        m_bBGM = AppSettings.getBoolValue("BGM");
	        m_bEffect = AppSettings.getBoolValue("Effect");

			createBack();
			createButtons();
	 	}
		private void createBack(){
			CCSprite spBG = ResourceManager.sharedResourceManager().getSpriteWithName(path + "options_background");
			spBG.setPosition(384, 512);
			addChild(spBG);
		}
		private void createButtons(){
			float fx, fy;
			String str;
			////////////BPM
			if(m_bBGM){
				str = "select1";
			} else {
				str = "select2";
			}
			m_btnBGM = Button.buttonWithSprite(path + str, path + str, this, "actionBGM");
			fx = 600;	fy = 565;
			m_btnBGM.setPosition(fx, fy);
			addChild(m_btnBGM);
			
			//////effect
			if(m_bEffect){
				str = "select1";
			}else{
				str = "select2";
			}
			m_btnEffect = Button.buttonWithSprite(path + str, path + str, this, "actionEffect");
			fy -= 90;
			m_btnEffect.setPosition(fx, fy);
			addChild(m_btnEffect);
			///////////back
			GrowButton btnBack = GrowButton.buttonWithSpriteFrame(path + "back", path + "back", this, "actionBack");
			fx = 680;	fy = 80;
			btnBack.setPosition(fx, fy);
			addChild(btnBack);
		}
		public void actionBack(Object sender) {
			Define.playEffect(R.raw.buttonclick);

			parentScene.gotoMain();
		}
		public void actionBGM(Object sender) {
			m_bBGM = !m_bBGM;
			if (m_bBGM){
				SoundEngine.sharedEngine().setSoundVolume(1f);
			}else{
				SoundEngine.sharedEngine().setSoundVolume(0f);

			}
			AppSettings.setBoolValueWithName(m_bBGM , "BGM");
			if(m_bBGM){
				m_btnBGM.changeSpriteDefault(path + "select1");
//				Define.playSound(R.raw.title_bg);
			} else {
				m_btnBGM.changeSpriteDefault(path + "select2");		
			}
		}
		public void actionEffect(Object sender) {
			m_bEffect = !m_bEffect;
			if (m_bEffect){
				SoundEngine.sharedEngine().setEffectsVolume(1f);
			}else{
				SoundEngine.sharedEngine().setEffectsVolume(0f);
			}
			AppSettings.setBoolValueWithName(m_bEffect , "Effect");
			if(m_bEffect){
				m_btnEffect.changeSpriteDefault(path + "select1");
			} else {
				m_btnEffect.changeSpriteDefault(path + "select2");		
			}
		}
	}

}

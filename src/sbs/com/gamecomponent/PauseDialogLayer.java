package sbs.com.gamecomponent;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCTransitionScene;

import sbs.com.Scene.SushiMainMenuScene;
import sbs.com.ccframework.GrowButton;
import sbs.com.object.Define;
import sbs.com.object.ResourceManager;

public class PauseDialogLayer extends BaseDialog {
	public PauseDialogLayer() {
		super();
	}
	///////////////////////////////////////////////////////////////////////////////
	protected void createSprites(){
		super.createSprites();
		CCSprite spTitle = ResourceManager.sharedResourceManager().getSpriteWithName("game/dialog/pause/Pause_background");
		spTitle.setVisible (false);
		spTitle.setScale(0);
		this. addChild(spTitle);
	}
	///////////////////////////////////////////////////////////////////////////////
	protected void createButtons(){
		super.createButtons();
		float fx, fy;
		
		GrowButton btnRestart = GrowButton.buttonWithSpriteFrame("game/dialog/pause/restart_botton", "game/dialog/pause/restart_botton", this, "actionRestart");
		fx = 0;	fy = 60;		
		Define.setScaleDelta(btnRestart, 0);
		btnRestart.setPosition(fx, fy);
		this. addChild(btnRestart);
		
		GrowButton btnContinue = GrowButton.buttonWithSpriteFrame("game/dialog/pause/continue_botton", "game/dialog/pause/continue_botton", this, "actionContinue");
		fy -= 100;
		Define.setScaleDelta(btnContinue, 0);
		btnContinue.setPosition(fx, fy);
		this. addChild(btnContinue);
		
		GrowButton btnMainMenu = GrowButton.buttonWithSpriteFrame("game/dialog/pause/mainmenubotton", "game/dialog/pause/mainmenubotton", this, "actionMainMenu");
		fy -= 100;	
		Define.setScaleDelta(btnMainMenu, 0);
		btnMainMenu.setPosition(fx, fy);
		this. addChild(btnMainMenu);
	}
	public void actionRestart(Object sender){
		this.hide();
		SushiGameLayer layer = (SushiGameLayer)this.getParent();
		layer.actionRestart(sender);
	}
	public void actionContinue(Object sender){
		this.hide();
		SushiGameLayer layer = (SushiGameLayer)this.getParent();
		layer.resumeGame();
	}
	public void actionMainMenu(Object sender){
		CCScene scene = SushiMainMenuScene.scene();
		CCTransitionScene ts = CCFadeTransition.transition(1.0f, scene);
		CCDirector.sharedDirector().replaceScene(ts);
	}
}

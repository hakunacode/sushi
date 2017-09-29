package sbs.com.gamecomponent;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCTransitionScene;

import sbs.com.Scene.SushiMainMenuScene;
import sbs.com.ccframework.GrowButton;
import sbs.com.object.ResourceManager;

public class LevelCompleteDlg extends BaseDialog {

	public LevelCompleteDlg() {
		super();
	}
	//
	@Override
	protected void createSprites(){
	    float fx, fy;
	    
	    CCSprite spTitle = ResourceManager.sharedResourceManager().getSpriteWithName("game/dialog/levelcom");
	    
	    fx = 0;	    fy = 150;
	    
	    spTitle.setScale(0);
	    spTitle.setVisible(false);
	    spTitle.setPosition(fx, fy);
	    super.addChild(spTitle);
	}

	protected void createButtons(){
	    float fx, fy;
	    
	    GrowButton btnRestart = GrowButton.buttonWithSpriteFrame("game/dialog/next_button", "game/dialog/next_button", this, "actionNext");
	    fx = 0;	    fy = -120;
	    
	    btnRestart.setScale(0);
	    btnRestart.setPosition(fx, fy);
	    super.addChild(btnRestart);
	    
	    GrowButton btnContinue = GrowButton.buttonWithSpriteFrame("game/dialog/pause/continue_botton", "game/dialog/pause/continue_botton", this, "actionRestart");
	    fy -= 100;
	    btnContinue.setScale(0);
	    btnContinue.setPosition(fx, fy);
	    super.addChild(btnContinue);
	    
	    GrowButton btnMainMenu = GrowButton.buttonWithSpriteFrame("game/dialog/pause/mainmenubotton", "game/dialog/pause/mainmenubotton", this, "actionMainMenu");
	    fy -= 100;
	    btnMainMenu.setScale(0);
	    btnMainMenu.setPosition(fx, fy);
	    super.addChild(btnMainMenu);
	}

	public void actionRestart(Object sender){
	    this.hide();
	    SushiGameLayer game = (SushiGameLayer)this.getParent();
	    game.actionRestart(sender);
	}

	public void actionNext(Object sender){
	    this.hide();
	    SushiGameLayer game = (SushiGameLayer)this.getParent();
	    game.actionNextLevel(sender);
	}

	public void actionMainMenu(Object sender){
	    CCScene scene = SushiMainMenuScene.scene();
	    CCTransitionScene ts = CCFadeTransition.transition(1.0f, scene);
	    CCDirector.sharedDirector().replaceScene(ts);    
	}
}

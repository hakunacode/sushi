package sbs.com.gamecomponent;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCTransitionScene;

import sbs.com.Scene.SushiMainMenuScene;
import sbs.com.ccframework.GrowButton;
import sbs.com.object.ResourceManager;

public class GameOverDialog extends BaseDialog {

	public GameOverDialog() {
		super();
	}

	@Override
	protected void createSprites(){
	    float fx, fy;
	    
	    CCSprite spTitle = ResourceManager.sharedResourceManager().getSpriteWithName("game/dialog/levelfailed");
	    
	    fx = 0;
	    fy = 150;
	    
	    spTitle.setScale(0);
	    spTitle.setVisible(true);
	    spTitle.setPosition(fx, fy);
	    super.addChild(spTitle);
	}

	@Override
	protected void createButtons(){
	    float fx, fy;
	    
	    GrowButton btnRestart = GrowButton.buttonWithSpriteFrame("game/dialog/pause/restart_botton", "game/dialog/pause/restart_botton", this, "actionRestart");
	    fx = 0;
	    fy = -200;
	    
	    btnRestart.setScale(0);
	    btnRestart.setPosition(fx, fy);
	    super.addChild(btnRestart);
	    
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
	public void actionMainMenu(Object sender){
	    CCScene scene = SushiMainMenuScene.scene();
	    CCTransitionScene ts = CCFadeTransition.transition(1.0f, scene);
	    CCDirector.sharedDirector().replaceScene(ts);    
	}

}

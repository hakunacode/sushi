package sbs.com.gamecomponent;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCTransitionScene;

import sbs.com.Scene.SushiMainMenuScene;
import sbs.com.ccframework.GrowButton;
import sbs.com.object.ResourceManager;

public class CongratulationDiglog extends BaseDialog {

	public CongratulationDiglog() {
		super();
	}
	@Override
	protected void createBack(){
	    m_spBack = ResourceManager.sharedResourceManager().getSpriteWithName("game/dialog/congratulation_bg");
	    super.addChild(m_spBack);
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

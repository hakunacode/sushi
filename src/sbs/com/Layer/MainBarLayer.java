package sbs.com.Layer;

import org.cocos2d.actions.UpdateCallback;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;

import sbs.com.object.ResourceManager;
import sbs.com.object.SushiDataManager;

public class MainBarLayer extends CCLayer{
	private int m_direction;
	private CCSprite banner1, banner2;
	private static final int RIGHT_BAR = 1;
	private static int LEFT_BAR = -1;
	private BarMoveUpdateCallback m_MoveUpdateCallback;
	SushiDataManager dataManager = SushiDataManager.sharedManager();
	
	public MainBarLayer() {
	
		banner1 = ResourceManager.sharedResourceManager().getSpriteWithName("menu/sushiBanner");
		banner1.setVisible(false);
		banner2 = ResourceManager.sharedResourceManager().getSpriteWithName("menu/sushiBanner");
		banner2.setVisible(false);
		addChild(banner1);
		addChild(banner2);
		
		m_MoveUpdateCallback = new BarMoveUpdateCallback();
	
	}


	private void moveBar(){
		 float fSpeed = 3;
		
		if(m_direction == RIGHT_BAR)
		{
			if(banner1.getPosition().x > banner1.getContentSize().width){
				banner1.setPosition(-banner1.getContentSize().width, banner1.getPosition().y);
			}else{
				banner1.setPosition(banner1.getPosition().x + fSpeed, banner1.getPosition().y);
			}
			if(banner2.getPosition().x > banner1.getContentSize().width){
				banner2.setPosition(-banner1.getContentSize().width, banner2.getPosition().y);
			}else{
				banner2.setPosition(banner2.getPosition().x + fSpeed, banner2.getPosition().y);
			}
		}else{
			if(banner1.getPosition().x < -banner1.getContentSize().width){
				banner1.setPosition(banner1.getContentSize().width, banner1.getPosition().y);
			}else{
				banner1.setPosition(banner1.getPosition().x - fSpeed, banner1.getPosition().y);
			}
			
			if(banner2.getPosition().x < - banner1.getContentSize().width){
				banner2.setPosition(banner1.getContentSize().width, banner2.getPosition().y);
			}else{
				banner2.setPosition(banner2.getPosition().x - fSpeed, banner2.getPosition().y);
			}
		}
	}
	public void moveBar(int direction){
		m_direction = direction;
		if(m_direction == RIGHT_BAR){
			banner2.setPosition(-banner1.getContentSize().width, 0);
		}else {
			banner2.setPosition(banner1.getContentSize().width, 0);
		}
		banner1.setVisible(true);
		banner2.setVisible(true);
		this.schedule(m_MoveUpdateCallback, 0.02f);
	}
	@Override
	public void onExit() {
		this.unschedule(m_MoveUpdateCallback);
		super.onExit();
	}
	class BarMoveUpdateCallback implements UpdateCallback{
		public void update(float arg0) {
			moveBar();
		}
	}
}

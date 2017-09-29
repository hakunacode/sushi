package sbs.com.Layer;

import org.cocos2d.actions.UpdateCallback;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.utils.javolution.MathLib;

import sbs.com.object.Common;
import sbs.com.object.Define;
import sbs.com.object.ResourceManager;

public class MainTitleLayer  extends CCLayer{
	
		private CCSprite m_Mainlogo;
		private SparkleUpdateCallback m_UpdateCallback;
		
		
		
		MainTitleLayer(){
			m_Mainlogo  =  ResourceManager.sharedResourceManager().getSpriteWithName("menu/mainLogo_320_240");
			
			//m_Mainlogo = CCSprite.sprite("menu/mainLogo_320_240.png");
			addChild(m_Mainlogo);  
			ResourceManager.sharedResourceManager().loadData("menu/sparkle");
			m_UpdateCallback = new SparkleUpdateCallback();
			
	        schedule(m_UpdateCallback,0.8f);
	        
	
		}
		
	 public void createSparkle(){
		
		    CCSprite  spSparkle = CCSprite.sprite("menu/Sparkle_01@3x.png");
		    CCAnimate aAnim = Common.getAnimate("Sparkle_", 4, 0.2f);
		    CCCallFuncN aRemove = CCCallFuncN.action(this, "removeSparkle"); 
		    CCSequence aSeq = CCSequence.actions(aAnim, aRemove);
		    float fx, fy;
		    
		    int nDirectionX = MathLib.random(0, 1) > 0.5 ? 1 : -1;
		    int nDirectionY = MathLib.random(0, 1) > 0.5 ? 1 : -1;
		    fx = nDirectionX * m_Mainlogo.getContentSize().width/3 * MathLib.random(0, 1);
		    fy = nDirectionY * m_Mainlogo.getContentSize().height/4 * MathLib.random(0, 1); 
		    
		   
		    spSparkle.setPosition(Define.ccp(fx, fy));
		    spSparkle.runAction(aSeq);
		    addChild(spSparkle);
		}
		
	
	 @Override
	public void onExit() {
		// TODO Auto-generated method stub
		this.unschedule(m_UpdateCallback);
		super.onExit();
	}
	public void  removeSparkle(Object  sender)
	{
	    
	     removeChild((CCNode) sender,true);
	 }
	class SparkleUpdateCallback implements UpdateCallback{
	
		public void update(float arg0) {
			// TODO Auto-generated method stub
			createSparkle();
		}
		
	}
}

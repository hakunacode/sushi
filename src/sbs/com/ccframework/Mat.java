package sbs.com.ccframework;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCRepeat;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGRect;

import sbs.com.object.Common;
import sbs.com.object.GameInfo;
import sbs.com.object.ResourceManager;

public class Mat extends CCLayer{
	
	private CCSprite   m_spMat;
	private CCSprite   m_spBottle1;
	private CCSprite  m_spBottle2;
	private int		m_nItemCounter;
		
	private CCSprite	  m_spWait;
	private CCAnimate	m_animWait;
		
	public boolean	 m_bChargeFlag;
	public Mat(){
		m_nItemCounter= GameInfo.MAX_BOTTLE_COUNT;
		m_bChargeFlag = false;
		createWait();
		createMat();
		createBottles();
	}
	public void createWait(){
		m_spWait = CCSprite.sprite("game/telephone/wait/wait01@3x.png");
		m_spWait.setVisible(false);
		addChild(m_spWait,1);
		m_animWait = Common.getAnimate("wait", 16, 0.05f);
	}
	public void createMat(){
		m_spMat = ResourceManager.sharedResourceManager().getSpriteWithName("game/bottle/sakeMat");
		addChild(m_spMat);
	}
	public void createBottles(){
		float fx, fy;
		m_spBottle1 = ResourceManager.sharedResourceManager().getSpriteWithName("game/bottle/sakeBottle");
		fx = 0; fy = 25;
		m_spBottle1.setPosition(fx, fy);
		addChild(m_spBottle1);
		
		m_spBottle2 = ResourceManager.sharedResourceManager().getSpriteWithName("game/bottle/sakeBottle");
		fy -= 35;
		m_spBottle2.setPosition(fx, fy);
		addChild(m_spBottle2);
	}
	public CGRect boundingBox(){
		float fx, fy;
		fx = this.getPosition().x - m_spMat.getContentSize().width / 2;
		fy = this.getPosition().y - m_spMat.getContentSize().height / 2;
		CGRect rect = new CGRect();
		rect.set(fx, fy,fx + m_spMat.getContentSize().width,fy + m_spMat.getContentSize().height);
		return rect;
	}
	
	public boolean decreaseBottle(){
		m_nItemCounter--;
		 if(m_nItemCounter < 0)
			 return false; 
		 changeSprite();
		 return true;
		
	}
	private void changeSprite(){
		 if(m_nItemCounter == 1)
		 {
			 m_spBottle1.setVisible(false);
			 m_spBottle2.setVisible(true);
		 }
		 else if(m_nItemCounter == 0)
		 {
			 m_spBottle1.setVisible(false);
			 m_spBottle2.setVisible(false);
		 } else 
		 {
			 m_spBottle1.setVisible(true);
			 m_spBottle2.setVisible(true);
		 }
		
	}
	
	public void charge(int nAction){
		float fTime;
		
		if(nAction == GameInfo.Free_Charge)
		{
			fTime = GameInfo.FREE_CHARGE_TIME;
		} 
		else {
			fTime =  GameInfo.EXPRESS_CHARGE_TIME;
		}
		m_bChargeFlag = true;
		m_spWait.setVisible(true);
		m_spWait.runAction(CCRepeatForever.action(m_animWait));
		int nRepeatNum = GameInfo.MAX_BOTTLE_COUNT - m_nItemCounter;
		
		if(nRepeatNum == 0) return;
		
		CCDelayTime aDelay = CCDelayTime.action(fTime);
		CCCallFuncN aCall = CCCallFuncN.action(this, "actionCharge");
		CCSequence aSeq = CCSequence.actions(aDelay, aCall);
		CCRepeat aRepeat = CCRepeat.action(aSeq, nRepeatNum);
		CCCallFuncN aRemove = CCCallFuncN.action(this, "actionRemoveWait");
		CCSequence aSeq2 = CCSequence.actions(aRepeat, aRemove);
		runAction(aSeq2);
	}
	public void actionCharge(Object sender)
	{
		m_nItemCounter++;
		changeSprite();
	}
	public void actionRemoveWait(Object sender)	{
		m_spWait.stopAllActions();
		m_spWait.setVisible(false);
		m_bChargeFlag = false;
	}
	public boolean isChargeAvaliable(){
		return m_nItemCounter < GameInfo.MAX_BOTTLE_COUNT;
	}
}

package sbs.com.gamecomponent;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCRepeat;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;

import sbs.com.object.Common;
import sbs.com.object.GameInfo;
import sbs.com.object.ResourceManager;


public class TrayButton extends CCMenu {
	public int m_nCurItem;
	public int m_nTrayIndex;
		
	private CCSprite m_spWait;
	private CCAnimate m_animWait;
		
	public boolean m_bChargeFlag;
	
	public TrayButton(CCMenuItem... items) {
		super(items);
		
//		m_bChargeFlag = false;
	}

	enum Tag_Index
	{
	Tag_Item,
	} ;
	public static TrayButton buttonWithSprite(String normalImage,String selectImage, CCNode target,String sel){
		CCSprite normalSprite = ResourceManager.sharedResourceManager().getSpriteWithName(normalImage);
		CCSprite selectSprite = ResourceManager.sharedResourceManager().getSpriteWithName(selectImage);
		CCMenuItemSprite menuItem = CCMenuItemSprite.item(normalSprite, selectSprite, target, sel);

		menuItem.setTag(Tag_Index.Tag_Item.ordinal());
		TrayButton menu = new TrayButton(menuItem);
		menu.m_nCurItem = GameInfo.MAX_TRAY_ITEM_COUNT;
		menu.m_nTrayIndex = 0;
	

		return menu;	
	
	
	}
	
	public static TrayButton buttonWithSpriteID(String normalImage,String selectImage, CCNode target,String sel,int index){
		CCSprite normalSprite = ResourceManager.sharedResourceManager().getSpriteWithName(normalImage);
		CCSprite selectSprite = ResourceManager.sharedResourceManager().getSpriteWithName(selectImage);
		CCMenuItemSprite menuItem = CCMenuItemSprite.item(normalSprite, selectSprite, target, sel);

		menuItem.setTag(index);
		
		TrayButton menu = new TrayButton(menuItem);
		menu.m_nCurItem = GameInfo.MAX_TRAY_ITEM_COUNT;
		menu.m_nTrayIndex = index;
		menu.createWait();

		return menu;
	}
	private void createWait(){
		CCMenuItemSprite item = (CCMenuItemSprite)this.getChildByTag(m_nTrayIndex);
		m_spWait = CCSprite.sprite("game/telephone/wait/wait01@3x.png");
		m_spWait.setPosition(item.getContentSize().width/2, item.getContentSize().height/2);
		m_spWait.setVisible(false);
		item.addChild(m_spWait,1);
		m_animWait = Common.getAnimate("wait", 16, 0.05f);
	}
	public void changeSprite(int nIndex){
		CCMenuItemSprite menuItem = (CCMenuItemSprite)this.getChildByTag(nIndex);
		ResourceManager resManager = ResourceManager.sharedResourceManager();
		int nNum = m_nCurItem;
		String str = "";
	
			if(nIndex == GameInfo.TrayType.Meat.ordinal())
				str = "game/traysIngredients/trayMeat/trayMeat";
			if(nIndex == GameInfo.TrayType.Laver.ordinal()) 
				str = "game/traysIngredients/trayLaver/trayLaver";
				
			if(nIndex == GameInfo.TrayType.Col.ordinal())	
				str = "game/traysIngredients/trayCol/trayCol";
				
			if(nIndex == GameInfo.TrayType.Rice.ordinal())	
				str = "game/traysIngredients/trayRice/trayRice";
				
			if(nIndex == GameInfo.TrayType.Zam.ordinal())	
				str = "game/traysIngredients/trayZam/trayZam";
				
			if(nIndex == GameInfo.TrayType.Sausage.ordinal())	
				str = "game/traysIngredients/traySausage/traySausage";
				
		
		String str1 = String.format("%s%02d", str,nNum);
		menuItem.setNormalImage(resManager.getSpriteWithName(str1));
		menuItem.setSelectedImage(resManager.getSpriteWithName(str1));
	}
	public void charge(int nAction){
		float fTime;
		if(nAction == GameInfo.TrayChargeAction.Free_Charge.ordinal())
		{
			fTime = GameInfo.FREE_CHARGE_TIME;
		} 
		else {
			fTime = GameInfo.EXPRESS_CHARGE_TIME;
		}
		int nRepeatNum = GameInfo.MAX_TRAY_ITEM_COUNT - m_nCurItem;
		m_spWait.setVisible(true);
		m_spWait.runAction(CCRepeatForever.action(m_animWait));
		CCDelayTime aDelay = CCDelayTime.action(fTime);
		CCCallFuncN aCall = CCCallFuncN.action(this, "actionCharge");
		CCSequence aSeq = CCSequence.actions(aDelay, aCall);

		CCRepeat aRepeat = CCRepeat.action(aSeq, nRepeatNum);
		CCCallFuncN aRemove = CCCallFuncN.action(this, "actionRemoveWait");
		CCSequence aSeq2 = CCSequence.actions(aRepeat, aRemove);
		runAction(aSeq2);
	}
	public boolean isCharge(){
		int nRepeatNum = GameInfo.MAX_TRAY_ITEM_COUNT - m_nCurItem;
		if(nRepeatNum == 0) return false;

		if(m_bChargeFlag)
			return false;
		m_bChargeFlag = true;

		return true;
	}
	public void actionCharge(Object sender){
		m_nCurItem++;
		changeSprite(m_nTrayIndex);
	}
	public void actionRemoveWait(Object sender){
		m_spWait.stopAllActions();
		m_spWait.setVisible(false);
		m_bChargeFlag = false;
	}
	public boolean isChargeAvaliable(){
		return m_nCurItem < GameInfo.MAX_TRAY_ITEM_COUNT;
	}
	public void touchEnable(boolean bFlag){
	    this.setIsTouchEnabled(bFlag);
	}
}


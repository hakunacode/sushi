package sbs.com.ccframework;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;

import sbs.com.object.ResourceManager;

public class Button extends CCMenu {

	private static final int TAG_INDEX = 2;
	public int m_nIndex;
	public Button(CCMenuItem... items) {
		super(items);
		// TODO Auto-generated constructor stub
	}

	public static Button buttonWithSprite(String normalImage, String selectImage, CCNode target, String sel){
		CCSprite normalSprite = ResourceManager.sharedResourceManager().getSpriteWithName(normalImage);
		CCSprite selectSprite = ResourceManager.sharedResourceManager().getSpriteWithName(selectImage);
	
		CCMenuItem menuItem = CCMenuItemSprite.item(normalSprite, selectSprite, target ,sel);
		menuItem.setTag(TAG_INDEX);
		Button menu = new Button(menuItem);
		return menu;	
	}
	public static Button buttonWithSpriteID(String normalImage, String selectImage, CCNode target, String sel, int index){
		CCSprite normalSprite = ResourceManager.sharedResourceManager().getSpriteWithName(normalImage);
		CCSprite selectSprite = ResourceManager.sharedResourceManager().getSpriteWithName(selectImage);

		CCMenuItem menuItem = CCMenuItemSprite.item(normalSprite, selectSprite, target ,sel);
		menuItem.setTag(index);
		Button menu = new Button(menuItem);
		menu.m_nIndex = index;
		return menu;
	}
	

	
	public void changeSprite(String strSpriteName){
		CCMenuItemSprite menuItem = (CCMenuItemSprite)getChildByTag(m_nIndex);
	    
		ResourceManager resManager = ResourceManager.sharedResourceManager();
	    
	    menuItem.setNormalImage(resManager.getSpriteWithName(strSpriteName));
	    menuItem.setSelectedImage(resManager.getSpriteWithName(strSpriteName));     
	}
	
	public void changeSpriteDefault(String strSpriteName){
		CCMenuItemSprite menuItem = (CCMenuItemSprite)getChildByTag(TAG_INDEX);
		ResourceManager resManager = ResourceManager.sharedResourceManager();
	    
	    menuItem.setNormalImage(resManager.getSpriteWithName(strSpriteName));
	    menuItem.setSelectedImage(resManager.getSpriteWithName(strSpriteName));     
	}
	
	public void touchEnable(boolean flag){
	    this.setIsTouchEnabled(flag);
	}
}

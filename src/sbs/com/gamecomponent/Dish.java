package sbs.com.gamecomponent;

import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import sbs.com.ccframework.Button;
import sbs.com.object.ResourceManager;

public class Dish extends Button{
	public int     m_nDishType;
    public int     m_nBeltPos;
    public Dish(CCMenuItem... items){
    	super(items);
    }
    public static Dish buttonWithSpriteID(String normalImage,String  selectImage, CCNode target, String sel,int index){
    	CCSprite normalSprite = ResourceManager.sharedResourceManager(). getSpriteWithName(normalImage);
    	CCSprite selectSprite = ResourceManager.sharedResourceManager(). getSpriteWithName(selectImage);
    	CCMenuItem  menuItem = CCMenuItemSprite.item(normalSprite,selectSprite,target,sel);
    	menuItem.setTag(index);
    	Dish menu = new Dish(menuItem);
    	menu.m_nDishType = index;
   	return menu;
    }
    public CGRect boundingBox(){
    	return this.getChildByTag(m_nDishType).getBoundingBox();
    }
    public CGSize contentSize(){
    	return this.getChildByTag(m_nDishType).getContentSize();
    }
}


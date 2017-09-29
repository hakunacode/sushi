package sbs.com.ccframework;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemFont;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;

import android.view.MotionEvent;

import sbs.com.object.ResourceManager;

public class GrowButton extends CCMenu {
    private static final int TAG_ITEM = 1;
    public boolean m_bTouchEnable;

	public GrowButton(CCMenuItem... items) {
		super(items);
	}
	
	public static GrowButton buttonWithSprite(String normalImage, String selectImage, CCNode parentNode, String methodName){
		CCMenuItemImage menuItem = CCMenuItemImage.item(normalImage, selectImage, parentNode, methodName);
		GrowButton menu = new GrowButton(menuItem);
		menu.m_bTouchEnable = true;
		return menu;
	}
	
	public static GrowButton buttonWithSpriteFrame(String frameName, String selectframeName, CCNode parentNode, String methodName){
		ResourceManager resManager = ResourceManager.sharedResourceManager();
		CCSprite normalSprite = resManager.getSpriteWithName(frameName);
		CCSprite selectSprite = resManager.getSpriteWithName(selectframeName);
		assert(normalSprite != null);
		assert(selectSprite != null);
		CCMenuItem menuItem = (CCMenuItem)CCMenuItemSprite.item(normalSprite, selectSprite, parentNode, methodName);
		menuItem.setTag(TAG_ITEM);
		GrowButton menu = new GrowButton(menuItem);
		menu.m_bTouchEnable = true;
		return menu;
	}
	public static GrowButton buttonWithLabel(String str, CCNode parentNode, String methodName,
			String strFontName, int fFontSize){
		CCMenuItemFont menuItem = CCMenuItemFont.item(str, parentNode, methodName);
		menuItem.setFontName(strFontName);
		menuItem.setFontSize(fFontSize);

		menuItem.setTag(TAG_ITEM);
		GrowButton menu = new GrowButton(menuItem);
		menu.m_bTouchEnable = true;
		return menu;	
	}
	
	public static GrowButton buttonWithLabelAndSprite(String labelWithString, CCNode target, String methodName,
			String strFontName, float fFontSize, String strNormalSprite, String strSelSprite){
		ResourceManager resManager = ResourceManager.sharedResourceManager();
	
		CCSprite normalSprite = resManager.getSpriteWithName(strNormalSprite);
		CCSprite selectSprite = resManager.getSpriteWithName(strSelSprite);
	
		CCLabel label1 = CCLabel.makeLabel(labelWithString, strFontName, fFontSize);
		CCLabel label2 = CCLabel.makeLabel(labelWithString, strFontName, fFontSize);
	
		label1.setPosition(normalSprite.getContentSize().width/2, normalSprite.getContentSize().height/2);
		label2.setPosition(selectSprite.getContentSize().width/2, selectSprite.getContentSize().height/2);
	
		normalSprite.addChild(label1);
		selectSprite.addChild(label2);
	
		assert(normalSprite!= null);
		assert(selectSprite!= null);
	
		CCMenuItem menuItem = CCMenuItemSprite.item(normalSprite, selectSprite, target, methodName);
		menuItem.setTag(TAG_ITEM);
		GrowButton menu = new GrowButton(menuItem);
		menu.m_bTouchEnable = true;
		return menu;	
	}
	
/*	private void setColor(ccColor3B color){
		CCMenuItemFont item = (CCMenuItemFont)(getChildByTag(TAG_ITEM));
		item.setColor(color);
	}
	*/
	public void setLabelString(String str){
		CCMenuItemFont item = (CCMenuItemFont)(getChildByTag(TAG_ITEM));
		item.setString(str);
	}

	
/*	private CCMenuItem itemForTouch(CCTouch touch){
		CGPoint touchLocation = touch locationInView: [touch view]);
		touchLocation = [[CCDirector sharedDirector] convertToGL: touchLocation);
		
		CCMenuItem* item;
		CCARRAY_FOREACH(children_, item){
		// ignore invisible and disabled items: issue #779, #866
		if ( [item visible] && [item isEnabled] ) {
		
			CGPoint local = [item convertToNodeSpace:touchLocation);
			CGRect r = [item rect);
			r.origin = CGPointZero;
			
			if( CGRectContainsPoint( r, local ) )
				return item;
		}
		return null;
	}
	*/
/*	private void animateFocusMenuItem(CCMenuItem menuItem){
		id movetozero = [CCScaleTo actionWithDuration:0.1f scale:1.2f);
		id ease = [CCEaseBackOut actionWithAction:movetozero);
		id movetozero1 = [CCScaleTo actionWithDuration:0.1f scale:1.15f);
		id ease1 = [CCEaseBackOut actionWithAction:movetozero1);
		id movetozero2 = [CCScaleTo actionWithDuration:0.1f scale:1.2f);
		id ease2 = [CCEaseBackOut actionWithAction:movetozero2);
		id sequence = [CCSequence actions: ease, ease1, ease2, null);
		[menuItem runAction:sequence);	
	}
	
	private void animateFocusLoseMenuItem(CCMenuItem  menuItem){
		id movetozero = [CCScaleTo actionWithDuration:0.1f scale:1.0f);
		id ease = [CCEaseBackOut actionWithAction:movetozero);
		id movetozero1 = [CCScaleTo actionWithDuration:0.1f scale:1.05);
		id ease1 = [CCEaseBackOut actionWithAction:movetozero1);
		id movetozero2 = [CCScaleTo actionWithDuration:0.1f scale:1.0);
		id ease2 = [CCEaseBackOut actionWithAction:movetozero2);
		id movetozero3 = [CCScaleTo actionWithDuration:0.1f scale:1.0f);
		id ease3 = [CCEaseBackOut actionWithAction:movetozero3);
		id sequence = [CCSequence actions: ease,ease1, ease2, ease3, null);
		[menuItem runAction:sequence);
	}
	*/
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		if(!m_bTouchEnable)
			return false;
//		if( this..state_ != kCCMenuStateWaiting || !this.getVisible() )
//			return false;
/*		if(this.getVisible())
			return false;
		this.
		CCMenuItem selectedItem_ = this.itemForTouch(visible_);
		super.setSelectedItem(selectedItem_);
		selectedItem_.selected();
		
		if(selectedItem_ != null) {
			
			this.animateFocusMenuItem(selectedItem_);
			state_ = kCCMenuStateTrackingTouch;
			return true;
		}
	*/
		return super.ccTouchesBegan(event);
	}
	
/*	-(void) ccTouchEnded:(UITouch *)touch withEvent:(UIEvent *)event
	{
	NSAssert(state_ == kCCMenuStateTrackingTouch, @"[Menu ccTouchEnded] -- invalid state");
	
	[selectedItem_ unselected);
	[selectedItem_ activate);
	state_ = kCCMenuStateWaiting;
	
	this. animateFocusLoseMenuItem: selectedItem_);
	}
	
	-(void) ccTouchCancelled:(UITouch *)touch withEvent:(UIEvent *)event
	{
	NSAssert(state_ == kCCMenuStateTrackingTouch, @"[Menu ccTouchCancelled] -- invalid state");
	
	[selectedItem_ unselected);
	
	this. animateFocusLoseMenuItem: selectedItem_);
	state_ = kCCMenuStateWaiting;
	}
	
	-(void) ccTouchMoved:(UITouch *)touch withEvent:(UIEvent *)event
	{
	NSAssert(state_ == kCCMenuStateTrackingTouch, @"[Menu ccTouchMoved] -- invalid state");
	
	CCMenuItem *currentItem = this. itemForTouch:touch);
	
	if (currentItem != selectedItem_) {
	this. animateFocusLoseMenuItem: selectedItem_);
	this. animateFocusMenuItem: currentItem);
	[selectedItem_ unselected);
	selectedItem_ = currentItem;
	[selectedItem_ selected);
	}
	}
	
	- (void) changeSprite: (String) strSpriteName
	{
	CCMenuItemSprite* menuItem = (CCMenuItemSprite*)this. getChildByTag: Tag_Item);
	
	ResourceManager* resManager = [ResourceManager sharedResourceManager);
	
	[menuItem setNormalImage: [resManager getSpriteWithName: strSpriteName]);
	[menuItem setSelectedImage: [resManager getSpriteWithName: strSpriteName]);     
	}
	@end
	*/
}

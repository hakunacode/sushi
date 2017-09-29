package sbs.com.gamecomponent;

import java.util.List;

import org.cocos2d.actions.base.CCFiniteTimeAction;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;

import sbs.com.object.Define;
import sbs.com.object.GameInfo;
import sbs.com.object.ResourceManager;

public class BaseDialog extends CCLayer {
	boolean m_bOpenFlag;
	private static int TAG_BACKGROUND = 2;
 
	public CCSprite m_spBack;

	public BaseDialog() {
		super();
		init();
	}
	
	private void init(){
		m_bOpenFlag = false;
		createUI();
	}
	///////////////////////////////////////////////////////////////////////////
	private void createUI(){
		this.createBack();
		this.createSprites();
		this.createButtons();
	}
	///////////////////////////////////////////////////////////////////////////
	protected void createBack(){
		m_spBack =ResourceManager.sharedResourceManager().getSpriteWithName("game/dialog/dialog_bg");
		Define.setScaleDelta(m_spBack, 0);
		m_spBack.setVisible(false);
		m_spBack.setTag(TAG_BACKGROUND);
		this.addChild(m_spBack);
	}
	///////////////////////////////////////////////////////////////////////////
	protected void createButtons(){
	}
	///////////////////////////////////////////////////////////////////////////
	protected void createSprites(){
	}
	///////////////////////////////////////////////////////////////////////////
	public void show(){
		if(m_bOpenFlag)
			return;
		m_bOpenFlag = true;
		
		m_spBack.setVisible(true);
		CCFiniteTimeAction aScale = CCScaleTo.action(GameInfo.DIALOG_SHOW_TIME , 1);
		CCFiniteTimeAction aCall = CCCallFuncN.action(this , "restoreSprite");
		CCSequence aSeq = CCSequence.actions(aScale, aCall);
		
		m_spBack.runAction(aSeq);
	}
	
	///////////////////////////////////////////////////////////////////////////
	public void hide(){
		if(!m_bOpenFlag)
			return;
		m_bOpenFlag = false;
		
		this.hideSprite();
		
		CCFiniteTimeAction aScale = CCScaleTo.action(GameInfo.DIALOG_SHOW_TIME , 0);
		CCFiniteTimeAction aCall = CCCallFuncN.action(this, "hideBack");
		CCSequence aSeq = CCSequence.actions(aScale, aCall);
		
		m_spBack.runAction(aSeq);
	}
	///////////////////////////////////////////////////////////////////////////
	private void hideSprite(){
		List<CCNode> listNode = this.getChildren();
		CCNode node;
		for (int i = 0; i < listNode.size(); i++){
			node = listNode.get(i);
			if(node.getTag() != TAG_BACKGROUND) 
			{
				node.setVisible(false);
				node.setScale(0);
			}
		}
	}
	///////////////////////////////////////////////////////////////////////////
	public void restoreSprite(Object sender){
		List<CCNode> listNode = this.getChildren();
		CCNode node;
		for (int i = 0; i < listNode.size(); i++){
			node = listNode.get(i);
			node.setVisible(true);
			node.setScale(1);
		}
	}
	///////////////////////////////////////////////////////////////////////////
	public void hideBack(Object sender){
		m_spBack.setVisible(false);
		m_spBack.setScale(0);
	}
}

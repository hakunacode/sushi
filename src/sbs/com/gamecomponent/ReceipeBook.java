package sbs.com.gamecomponent;

import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import sbs.com.ccframework.GrowButton;
import sbs.com.object.GameInfo;
import sbs.com.object.ResourceManager;

public class ReceipeBook extends CCLayer {
	private int  m_nCurPage;
	private boolean  m_bOpenFlag;
	CCSprite   m_spPages[];   
	boolean        m_bActionFlag;
	public ReceipeBook(){
		m_nCurPage = 0;
	    m_bOpenFlag = false;
	    m_bActionFlag = false;
	    m_spPages = new CCSprite[GameInfo.RECEIPE_BOOK_PAGE];
	    createUI();
	}
	
	public void createUI(){
		createPages();
		createButtons();
	}
	public void createPages(){
		String str;
		for(int i = 0; i < GameInfo.RECEIPE_BOOK_PAGE; i++ ){
			str = "game/receipeBook/page_";
			str += String.valueOf(i+1);
			str +="_english";
			m_spPages[i]= ResourceManager.sharedResourceManager().getSpriteWithName(str);
			m_spPages[i].setVisible(false);
			addChild(m_spPages[i]);
		}
		m_spPages[m_nCurPage].setVisible(true);
	}
	
	public void createButtons(){
		GrowButton btnQuit = GrowButton.buttonWithSpriteFrame("game/telephone/telephoneBackButton", "game/telephone/telephoneBackButton", this, "actionQuit");
		btnQuit.setPosition( 30, -180);
		addChild(btnQuit);
		
		GrowButton btnLeft = GrowButton.buttonWithSpriteFrame("game/receipeBook/arrowLeft", "game/receipeBook/arrowLeft", this, "actionLeft");
		btnLeft.setPosition( -335, -130);
		addChild(btnLeft);
		
		GrowButton btnRight = GrowButton.buttonWithSpriteFrame("game/receipeBook/arrowRight", "game/receipeBook/arrowRight", this, "actionRight");
		btnRight.setPosition( 335, -130);
		addChild(btnRight);
	}
	public void open(){
		show();
		m_bOpenFlag = true;
	}
	public void show(){
		if(m_bOpenFlag) return;

		if(m_bActionFlag) return;
		
		this.setVisible(true);
		
		CCMoveTo aMove = CCMoveTo.action(0.5f, CGPoint.ccp(this.getPosition().x, this.getPosition().y + 480 ));
		CCCallFuncN aRestore = CCCallFuncN.action(this, "actionOpenDone");
		CCSequence aSeq = CCSequence.actions(aMove, aRestore);
		m_bActionFlag = true;
		runAction(aSeq);
	}
	
	public void hide(){
		if(m_bActionFlag) return;
		this.setVisible(true);
		CCMoveTo aMove = CCMoveTo.action(0.5f, CGPoint.ccp(this.getPosition().x, this.getPosition().y - 480 ));
		CCCallFuncN aRestore = CCCallFuncN.action(this, "actionCloseDone");
		CCSequence aSeq = CCSequence.actions(aMove, aRestore);
		m_bActionFlag = true;
		runAction(aSeq);
	}
	public void actionOpenDone(Object sender){
		m_bActionFlag = false;
	}
	public void  actionCloseDone(Object sender){
	m_bActionFlag = false;
	m_bOpenFlag = false;
	}
	public void actionQuit(Object sender){
		hide();
		SushiGameLayer layer = (SushiGameLayer)this.getParent();
		layer.touchEnable();
	}
	public void changePage(){
		for (int i = 0; i<GameInfo.RECEIPE_BOOK_PAGE; i++){
			m_spPages[i].setVisible(false);
			m_spPages[m_nCurPage].setVisible(true);
			//[[SoundManager sharedSoundManager] playEffectWithID: PageTurn_Effect bForce: YES];
		}
	}
	public void actionLeft(Object sender){
		if(m_nCurPage == 0) return;
		m_nCurPage--;
		changePage();
	}
	public void actionRight(Object sender){
		if(m_nCurPage == GameInfo.RECEIPE_BOOK_PAGE - 1) return;
		m_nCurPage++;
		changePage();
		
	}
}



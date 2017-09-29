package sbs.com.gamecomponent;

import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import sbs.com.ccframework.Button;
import sbs.com.object.GameInfo;
import sbs.com.object.ResourceManager;


public class Telephone extends CCLayer{
	 	private float   m_fIntendX;
	 	private boolean    m_bActionFlag;
	 	private boolean    m_bOpenFlag;
	 	private boolean    m_bFlags[];
	 	private CCSprite  m_spSels[];
	 	
	 	
	 	
	 	public Telephone(){
	 		m_bFlags = new boolean[GameInfo.TELEPHONE_BUTTON_COUNT];
	 		m_spSels = new CCSprite[GameInfo.TELEPHONE_BUTTON_COUNT];
	 		createUI();
	 		m_bOpenFlag = false;
	 		m_bActionFlag = false;
	 		m_fIntendX = 0;//m_fIntendX = 20
	 		
	 		
	 	}

	 	public void createUI(){
	 		createBack();
	 		createButtons();
	 		createDishes();
	 		createSelectSprites();
	 	}
	 	public void createBack(){
	 		CCSprite spBack = ResourceManager.sharedResourceManager().getSpriteWithName("game/telephone/orderMenu_english");
	 		addChild(spBack);
	 	}

	 	public void createButtons(){
	 		float fx, fy;
	 		Button btnReturn = Button.buttonWithSprite("game/telephone/telephoneBackButton", "game/telephone/telephoneBackButton", this, "actionReturn");
	 		fx = 245; fy = -155;
	 		btnReturn.setPosition(fx, fy);
	 		addChild(btnReturn,1);
	 	
	 	
	 	
	 	}

	 	public void createDishes(){
	 		float fx, fy;
	 		float fIntendX = 190+ m_fIntendX * 2;
		 	float fIntendY = 155;
		 	Button btnMeat = Button.buttonWithSpriteID("game/telephone/shtimp_tele", "game/telephone/shtimp_tele", this, "actionDishes",GameInfo.TrayType.Meat.ordinal());
	 	
		 	fx = -190 - m_fIntendX * 2;
		 	fy = 122;
		 	btnMeat.setPosition(fx, fy);
		 	addChild(btnMeat);
		 	
		 	Button btnLaver = Button.buttonWithSpriteID("game/telephone/nori_tele", "game/telephone/nori_tele", this, "actionDishes",GameInfo.TrayType.Laver.ordinal());
		 	fx +=fIntendX;
		 	btnLaver.setPosition(fx, fy);
		 	addChild(btnLaver);
		 	
		 	Button btnCol = Button.buttonWithSpriteID("game/telephone/salmon_tele", "game/telephone/salmon_tele", this, "actionDishes",GameInfo.TrayType.Col.ordinal());
		 	fx +=fIntendX;
		 	btnCol.setPosition(fx, fy);
		 	addChild(btnCol);
		 	
		 	
		 	Button btnRice = Button.buttonWithSpriteID("game/telephone/rice_tele", "game/telephone/rice_tele", this, "actionDishes",GameInfo.TrayType.Rice.ordinal());
		 	fx = -190 - m_fIntendX * 2;
		 	fy -= fIntendY;
		 	btnRice.setPosition(fx, fy);
		 	addChild(btnRice);
		 	
		 	Button btnZam = Button.buttonWithSpriteID("game/telephone/fish_tele", "game/telephone/fish_tele", this, "actionDishes",GameInfo.TrayType.Zam.ordinal());
		 	fx +=fIntendX;
		 	btnZam.setPosition(fx, fy);
		 	addChild(btnZam);
		 	
		 	
		 	Button btnSau = Button.buttonWithSpriteID("game/telephone/unagi_tele", "game/telephone/unagi_tele", this, "actionDishes",GameInfo.TrayType.Sausage.ordinal());
		 	fx +=fIntendX;
		 	btnSau.setPosition(fx, fy);
		 	addChild(btnSau);
		 	
			Button btnExtraBottle = Button.buttonWithSpriteID("game/telephone/sake_tele", "game/telephone/sake_tele", this, "actionExtraBottle",GameInfo.TrayType.ExtraBottle.ordinal());
			  fx = -170 - m_fIntendX;
		 	  fy = -156;
		 	 btnExtraBottle.setPosition(fx, fy);
		 	addChild(btnExtraBottle);
		 	
		 	
		 	
		 	Button btnFree = Button.buttonWithSpriteID("game/telephone/free_button", "game/telephone/free_button", this, "actionButtons", GameInfo.TrayType.FreeButton.ordinal());
		 	  fx = 80 + m_fIntendX/2;
		 	  fy = -135;
		 	 btnFree.setPosition(fx, fy);
		 	addChild(btnFree);
		 	
		 	
		 	Button btnExpress = Button.buttonWithSpriteID("game/telephone/express_button", "game/telephone/express_button", this, "actionButtons",GameInfo.TrayType.ExpreeButton.ordinal());
		    fy -= 45;
		 	 btnExpress.setPosition(fx, fy);
		 	addChild(btnExpress);
		 	
	 	}
	 	
	 	public void createSelectSprites(){
	 		for(int i = 0; i < GameInfo.TELEPHONE_BUTTON_COUNT; i++){
	 			m_bFlags[i] = false;
	 			m_spSels[i] = ResourceManager.sharedResourceManager().getSpriteWithName("game/telephone/deliveryButton_hit");
	 			m_spSels[i].setVisible(false);
	 			addChild(m_spSels[i],1);
	 		
	 		}
	 	    float fx, fy;
	 	    float fIntendX = 190;
	 	    float fIntendY = 155;
		    fx = -130;
	 	    fy = 74;
	 	    
	 	    m_spSels[0].setPosition(fx, fy);
	 	    
	 	    fx += fIntendX;
	 	    m_spSels[1].setPosition(fx, fy);
	 	    
	 	    fx += fIntendX;
	 	    m_spSels[2].setPosition(fx, fy);
	 	    
	 	    fx = -130;
	 	    fy -= fIntendY;
	 	    m_spSels[3].setPosition(fx, fy);
	 	    
	 	    fx += fIntendX;
	 	    m_spSels[4].setPosition(fx, fy);
	 	    
	 	    
	 	    fx += fIntendX;
	 	    m_spSels[5].setPosition(fx, fy);
	 	    
	 	    
	 	    fx = -90;
	 	    fy = -167;
	 	    m_spSels[6].setPosition(fx, fy);
	 	    
	 	    fx += 255;
	 	    fy += 35;
	 	    m_spSels[7].setPosition(fx, fy);
	 	    fy -= 43;
	 	    m_spSels[8].setPosition(fx, fy);
	 	    
	 	    
	 		
	 	}
	 	public void actionDishes(Object sender){
	 		CCMenuItem button = (CCMenuItem)sender;
	 		int nIndex = button.getTag();
	 		  for(int i = 0; i < GameInfo.TRAY_ITEM_COUNT; i++)
		 	    {
		 	        m_bFlags[i] = false;
		 	        m_spSels[i].setVisible(false);
		 	    }
		 	    m_bFlags[nIndex] = true;
		 	    m_spSels[nIndex].setVisible(true);
	 	}
	 	public void actionExtraBottle(Object sender){
	 		 if(m_bFlags[GameInfo.TrayType.ExtraBottle.ordinal()])
		 	    {
		 	        m_bFlags[GameInfo.TrayType.ExtraBottle.ordinal()] = false;
		 	        m_spSels[GameInfo.TrayType.ExtraBottle.ordinal()].setVisible(false);
		 	    } 
		 	    else 
		 	    {
		 	        m_bFlags[GameInfo.TrayType.ExtraBottle.ordinal()] = true;
		 	        m_spSels[GameInfo.TrayType.ExtraBottle.ordinal()].setVisible(true);
		 	    }
	 	}
	 
	 	 


	   public void actionButtons(Object sender){
		   CCMenuItem button = (CCMenuItem)sender;
	 	    int nIndex = button.getTag();
	 	    int nAction;
	 	    int nTrayIndex = -1;
	 	    
	 	    for(int i = 0; i < GameInfo.TELEPHONE_BUTTON_COUNT; i++)
	 	    {
	 	        if(m_bFlags[i])
	 	        {
	 	            nTrayIndex = i;
	 	            break;
	 	        }
	 	    }    
	 	    
	 	    if(nTrayIndex == -1) return;
	 	    
	 	    if(nIndex == GameInfo.TrayType.FreeButton.ordinal())
	 	    {
	 	        m_spSels[GameInfo.TrayType.FreeButton.ordinal()].setVisible(true);
	 	        m_spSels[GameInfo.TrayType.FreeButton.ordinal()].setVisible(false);
	 	        nAction = GameInfo.TrayChargeAction.Free_Charge.ordinal();
	 	    } 
	 	    else 
	 	    {
	 	    	 m_spSels[GameInfo.TrayType.FreeButton.ordinal()].setVisible(false);
		 	     m_spSels[GameInfo.TrayType.ExpreeButton.ordinal()].setVisible(true);
	 	        nAction = GameInfo.TrayChargeAction.Express_Charge.ordinal();
	 	    }
	 	    
	 	   SushiGameLayer layer = (SushiGameLayer)this.getParent();
	 	   layer.chargeTray(nTrayIndex, nTrayIndex);
	 	   hide();
	 	}

	 	
	   public void show(){
	 	
	 	    if(m_bActionFlag)
	 	    	return;
	 	    m_bActionFlag = true;
	 	    if(m_bOpenFlag)
	 	    	return;
	 	    m_bOpenFlag = true;
	 	    

	 	    for(int i = 0; i < GameInfo.TELEPHONE_BUTTON_COUNT; i++)
	 	    {
	 	        m_bFlags[i] = false;
	 	        m_spSels[i].setVisible(false);
	 	    }
	 	    
	 	    float fx, fy;
	 	    
	 	    fx = this.getPositionRef().x;
	 	    fy = this.getPositionRef().y; 
	 	    
	 	   CCMoveTo aMove = CCMoveTo.action(0.5f, CGPoint.ccp(fx - 700, fy));
	 	   CCCallFuncN aCall = CCCallFuncN.action(this, "freeActionFlag");
	 	   CCSequence aSeq = CCSequence.actions(aMove, aCall);
	 	   runAction(aSeq);
	 	  
	 	}
	 	public void freeActionFlag(Object sender){
	 	    m_bActionFlag = false;
	 	}
	 	/////////////////////////////////////////////////////////////////////////////////////////////
	 	public void hide(){
	 	
	 	    if(m_bActionFlag)
	 	    	return;
	 	    if(!m_bOpenFlag)
	 	    	return;
	 	    
	 	    m_bActionFlag = true;
	 	    m_bOpenFlag = false;
	 	    
	 	    float fx, fy;
	 	    
	 	    fx = this.getPosition().x;
	 	    fy = this.getPosition().y;
	 	    
	 	   CCMoveTo aMove = CCMoveTo.action(0.5f, CGPoint.ccp(fx + 700, fy));
	 	   CCCallFuncN aCall = CCCallFuncN.action(this, "freeActionFlag");
	 	   CCSequence aSeq = CCSequence.actions(aMove, aCall) ;
	 	   runAction(aSeq);
	 	    
	 	   SushiGameLayer layer = (SushiGameLayer)this.getParent();
	 	   layer.touchEnable();
	 	   
	 	   ////////

	 	    
	 	}

	 	/////////////////////////////////////////////////////////////////////////////////////////////
	 	public void  actionReturn(Object sender){
	 		hide();
	 	}
}
	 	
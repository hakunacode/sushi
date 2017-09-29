package sbs.com.gamecomponent;

import java.util.Vector;

import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import sbs.com.object.GameInfo;
import sbs.com.object.ResourceManager;

public class RollingMat extends CCLayer {
	private CCSprite m_spTop;
	private CCSprite m_spBottom;
	
	public int m_nItemIndex;
	private int m_nMakedDish;
	
	public int m_nCurAction;
	
	private Vector<Integer> m_arrFillItems;
	public boolean m_bMakingDish;

	public static final int MAX_TRAY_COUNT = 7;
	enum TagType{
		Tag_Dish,
	};
	enum ActionType{
		RollingMat_Wait_Tray,
		RollingMat_Make_Dish,
		
	} ;
	public RollingMat() {
		super();
		m_bMakingDish = false;
		m_nItemIndex = 0;
		m_arrFillItems = new Vector<Integer>();
		m_nCurAction = ActionType.RollingMat_Wait_Tray.ordinal();
		this.createUI();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	private void createUI(){
		float fx, fy;
		
		m_spBottom = ResourceManager.sharedResourceManager().getSpriteWithName("game/rollingMat/rollingMat_bottom");
		this.addChild(m_spBottom);
		m_spTop = ResourceManager.sharedResourceManager().getSpriteWithName("game/rollingMat/rollingMat_top");
		
		fy = m_spBottom.getContentSize().height/2;
		m_spTop.setPosition(m_spTop.getPosition().x, fy);
		this.addChild( m_spTop);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public CGRect boundingBox(){
		float fx, fy;
		
		fx = this.getPosition().x - m_spBottom.getContentSize().width/2;
		fy = this.getPosition().y - m_spBottom.getContentSize().height/2;
		
		CGRect rect = CGRect.make(fx, fy, fx + m_spBottom.getContentSize().width, fy + m_spBottom.getContentSize().height);
		
		return rect;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public void addItem(int nIndex){
		m_arrFillItems.add(nIndex);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public void makeDish(){
		m_bMakingDish = true;
		
		float fx, fy;
		
		fx = 0;		fy = m_spBottom.getContentSize().height;
		CCMoveTo aMove1 = CCMoveTo.action(GameInfo.MOVE_ROLLING_MAT_INTERVAL, CGPoint.ccp(fx, fy));
		CCCallFuncN aMakeDish = CCCallFuncN.action(this, "doneMakeDish");
		
		fy = 0;
		CCMoveTo aMove2 = CCMoveTo.action(GameInfo.MOVE_ROLLING_MAT_INTERVAL, CGPoint.ccp(fx, fy));
		CCCallFuncN aRemove = CCCallFuncN.action(this, "removeDish");
		CCSequence aSeq = CCSequence.actions(aMove1, aMakeDish, aMove2, aRemove);
		m_spBottom.runAction(aSeq);
		
		m_nCurAction = ActionType.RollingMat_Make_Dish.ordinal();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public void doneMakeDish(Object sender){
		int nMeat = 0, nLaver = 0, nCol = 0, nRice = 0, nZam = 0, nSausage = 0; 
		
		for(int i = 0; i < m_arrFillItems.size(); i++){
			int nType = m_arrFillItems.get(i).intValue();
			if (nType == GameInfo.TrayType.Meat.ordinal()){
				nMeat++;
			}else if(nType == GameInfo.TrayType.Laver.ordinal()){
				nLaver++;
			}else if(nType == GameInfo.TrayType.Col.ordinal()){
				nCol++;;
			}else if(nType == GameInfo.TrayType.Rice.ordinal()){
				nRice++;
			}else if(nType == GameInfo.TrayType.Zam.ordinal()){
				nZam++;
			}else if(nType == GameInfo.TrayType.Sausage.ordinal()){
				nSausage++;
			}
		}
		
		int nDishType = this.getDish(nMeat, nLaver, nCol, nRice, nZam, nSausage);
		
		CCSprite spDish = this.getDishSprite(nDishType);
		spDish.setTag(TagType.Tag_Dish.ordinal());
		m_nMakedDish = nDishType;
		spDish.setPosition (m_spBottom.getContentSize().width/2, m_spBottom.getContentSize().height/2);
		m_spBottom.addChild(spDish);
		
		m_nItemIndex = 0;
		m_arrFillItems.removeAllElements();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	private int getDish(int nMeat, int nLaver, int nCol, int nRice, int nZam, int nSausage){
		int nDishType;
		//Onigiri.
		if(nRice == 2 && nLaver == 1 && nMeat == 0 && nCol == 0 && nZam == 0 && nSausage == 0){
			nDishType = GameInfo.BubbleType.Onigiri.ordinal();
		}else if(nRice == 1 && nLaver == 1 && nZam == 1 && nMeat == 0 && nCol == 0 && nSausage == 0){//CaliforniaRoll.
			nDishType = GameInfo.BubbleType.CaliforniaRoll.ordinal();
		}else if(nRice == 1 && nLaver == 1 && nZam == 2 && nMeat == 0 && nCol == 0 && nSausage == 0){//GunkanMaki.
			nDishType = GameInfo.BubbleType.GunkanMaki.ordinal();
		}else if(nRice == 1 && nLaver == 1 && nCol == 2 && nMeat == 0 && nZam == 0 && nSausage == 0){//MegaMeal.
			nDishType = GameInfo.BubbleType.MegaMeal.ordinal();
		}else if(nRice == 1 && nLaver == 1 && nMeat == 2 && nCol == 0 && nZam == 0 && nSausage == 0){//ShirmpSushi.
			nDishType = GameInfo.BubbleType.ShirmpSushi.ordinal();
		}else if(nRice == 1 && nLaver == 1 && nSausage == 2 && nCol == 0 && nZam == 0 && nMeat == 0){//UnAgiRoll.
			nDishType = GameInfo.BubbleType.UnAgiRoll.ordinal();
		}else if(nRice == 2 && nLaver == 1 && nZam == 1 && nSausage == 2 && nMeat == 0 && nCol == 0){//DragonRoll.
			nDishType = GameInfo.BubbleType.DragonRoll.ordinal();
		}else if(nRice == 2 && nLaver == 1 && nZam == 1 && nCol == 1 && nSausage == 1 && nMeat == 1){//Combo.
			nDishType = GameInfo.BubbleType.Combo.ordinal();
		}else if(nRice == 1 && nLaver == 1 && nZam == 1 && nMeat == 1 && nCol == 1 && nSausage == 0){//SuperFish.
			nDishType = GameInfo.BubbleType.SuperFish.ordinal();
		}else if(nRice == 1 && nLaver == 1 && nSausage == 3 && nZam == 0 && nCol == 0 && nMeat == 0){//SalmonRoll.
			nDishType = GameInfo.BubbleType.SalmonRoll.ordinal();
		}else{
			nDishType = GameInfo.BubbleType.Mess.ordinal();
		}
		return nDishType;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	private CCSprite getDishSprite(int nDishType){
		SushiGameLayer game = (SushiGameLayer)this.getParent();
		
		String str = game.getDishString(nDishType);
		CCSprite spDish = ResourceManager.sharedResourceManager().getSpriteWithName(str);
		
		return spDish;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public void removeDish(Object sender){
		m_spBottom.removeChildByTag(TagType.Tag_Dish.ordinal(), true);
		
		SushiGameLayer layer = (SushiGameLayer)this.getParent();
		layer.addDishInBelt(m_nMakedDish);
		
		m_nCurAction = ActionType.RollingMat_Wait_Tray.ordinal();
		m_bMakingDish = false;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public int movingHeight(){
		return (int)m_spBottom.getContentSize().height;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
}

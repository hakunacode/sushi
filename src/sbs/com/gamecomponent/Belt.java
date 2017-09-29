package sbs.com.gamecomponent;

import java.util.ArrayList;
import java.util.List;

import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.types.CGPoint;

import android.util.Log;

import sbs.com.object.GameInfo;

public class Belt extends FloatingBar{
		private ArrayList<Dish> m_arrDishes;
		private ArrayList<Dish> m_arrTemp;
		
		private int	 m_nCurIndex;
		private float 	m_fIndent;
		private int	 m_fPosTemp;
		private int	 m_nFullItemCount;
		
		
		public Belt(){
			m_nCurIndex = 0;
			m_fIndent = 768 / GameInfo.BELT_DISH_COUNT;
			m_fPosTemp = 0;
			m_nFullItemCount = 0;
			m_arrDishes = new ArrayList<Dish>();
			m_arrTemp = new ArrayList<Dish>();
		}
		public boolean isFull(){
			if(m_nFullItemCount == GameInfo.BELT_DISH_COUNT){
				return true;
			}
			return false;
		}
		private boolean checkDishInBelt(int nPos){
			for(int i = 0; i < m_arrDishes.size(); i++)
			{
				Dish dish = (Dish)m_arrDishes.get(i);
				if(dish.m_nBeltPos == nPos){
					return true;
				}
			}
			
			return false;
		}
		public void addDish(int nDishType ){
			Dish dish = this.getDishSprite(nDishType);
			dish.setTag(nDishType);
			dish.m_nDishType = nDishType;
			m_nFullItemCount++;
			m_arrTemp.add(dish);
			dish.setPosition(CGPoint.ccp(1000, 0));
			addChild(dish);
		}
		@Override
		public void movingBar(float f){
			m_fPosTemp += super.m_nSpeed;
			if(m_fPosTemp >= m_fIndent){
				m_fPosTemp = 0;
				if(m_arrTemp.size() != 0){
					Log.d("Nala", "123");
					Dish dish2 = (Dish)m_arrTemp.get(0);
					float fx = -384;
					float fy = -7;
					m_arrDishes.add(dish2);
					m_arrTemp.remove(dish2);
					dish2.setPosition(CGPoint.ccp(fx, fy));
				}
			}
			for(int i = 0; i < m_arrDishes.size(); i++){
				Dish dish = (Dish)m_arrDishes.get(i);
				CGPoint ptPos = dish.getPosition();
				
				if(ptPos.x >( 384 + 100 ))
				{
					m_arrDishes.remove(dish);
					m_arrTemp.add(dish);
				}
				else 
				{
					ptPos.x += super.m_nSpeed;
			 		dish.setPosition(ptPos);
				}
			}
			checkCustomerWithDish();
			super.movingBar(f);
		}
		private void checkCustomerWithDish(){
			SushiGameLayer layer = (SushiGameLayer)this.getParent();
			float fx, fy;
			int nDishType;
			for (int i = 0; i < m_arrDishes.size(); i++)
			{
				Dish spDish = (Dish) m_arrDishes.get(i);
				
				fx = spDish.getPosition().x + 384;
				fy = spDish.getPosition().y;
				
				nDishType = spDish.m_nDishType;
				
				if(layer.checkWithDish(CGPoint.ccp(fx, fy), nDishType ))
				{
					m_nFullItemCount--;
					this.removeChild(spDish, true);
					m_arrDishes.remove(i);
					return;
				}
			}
		}
		private Dish getDishSprite(int nDishType){
			SushiGameLayer game = (SushiGameLayer)this.getParent();
			String str = game.getDishString(nDishType);
			Dish spDish = Dish.buttonWithSpriteID(str, str, this, "actionDishes", nDishType);
			return spDish;
		}
		public void actionDishes(Object sender){
			CCMenuItem dish= (CCMenuItem)sender;
			int nIndex = dish.getTag();
			if(nIndex == GameInfo.BubbleType.Mess.ordinal())
			{
				m_nFullItemCount--;
				m_arrDishes.remove(dish.getParent());
			

				CCMoveTo aMove = CCMoveTo.action(0.2f, CGPoint.ccp(800, dish.getPosition().y));
				CCCallFuncN aRemove = CCCallFuncN .action(this, "actionRemove");
				CCSequence aSeq = CCSequence.actions(aMove, aRemove) ;
				dish.runAction(aSeq);
			
			}
		}
		public void actionRemove(Object sender){
			CCMenuItem dish = (CCMenuItem)sender;
			this.removeChild(dish.getParent(),true);
//			this.removeChild(dish,true);
		}
		public void pause(){
		
			List<CCNode> listNode = this.getChildren();
			CCNode node;
			for (int i = 0; i < listNode.size(); i++){
				node = listNode.get(i);
				node.pauseSchedulerAndActions();
			}

			pauseSchedulerAndActions();
		}
		public void resume(){
			List<CCNode> listNode = this.getChildren();
			CCNode node;
			for (int i = 0; i < listNode.size(); i++){
				node = listNode.get(i);
				node.resumeSchedulerAndActions();
			}

			resumeSchedulerAndActions();
		}
}


 


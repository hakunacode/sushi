package sbs.com.gamecomponent;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import sbs.com.object.ResourceManager;

public class FloatingBar extends CCLayer{

	public static final int LEFT_BAR = 1;
	public static final int RIGHT_BAR = 1;
	public CCSprite m_spBar1;
	public CCSprite m_spBar2;
	public int	m_nDirection;
	public int	 m_nSpeed;
	public FloatingBar(){
		
	}

	public void moveBar(int nDirection , String strSpriteName, int nSpeed){
		m_nSpeed = nSpeed;
		
		m_spBar1 = ResourceManager.sharedResourceManager().getSpriteWithName( strSpriteName);
		m_spBar1.setVisible(false);
		addChild(m_spBar1);
		
		m_spBar2 = ResourceManager.sharedResourceManager().getSpriteWithName( strSpriteName);
		m_spBar2.setVisible(false);
		addChild(m_spBar2);

		m_nDirection = nDirection;
		
		if(m_nDirection == RIGHT_BAR){
			m_spBar2.setPosition(CGPoint.ccp(-m_spBar1.getContentSize().width, m_spBar2.getPosition().y));
		}else{
			m_spBar2.setPosition(CGPoint.ccp(m_spBar1.getContentSize().width, m_spBar2.getPosition().y));
		}
		
		m_spBar1.setVisible(true);
		m_spBar2.setVisible(true);
		this.schedule("movingBar",0.01f);
//		this.schedule(new UpdateBelt(), 0.01f);
	}
	public void movingBar(float f){
		if(m_nDirection == RIGHT_BAR){
			if(m_spBar1.getPosition().x > m_spBar1.getContentSize().width - 20){
				m_spBar1.setPosition(-m_spBar1.getContentSize().width, m_spBar1.getPosition().y);
			}else{
				m_spBar1.setPosition(CGPoint.ccp(m_spBar1.getPosition().x + m_nSpeed, m_spBar1.getPosition().y));
			}
			if(m_spBar2.getPosition().x > m_spBar1.getContentSize().width - 20){
				m_spBar2.setPosition(CGPoint.ccp(-m_spBar1.getContentSize().width, m_spBar2.getPosition().y));
			}else {
				m_spBar2.setPosition(CGPoint.ccp(m_spBar2.getPosition().x + m_nSpeed, m_spBar2.getPosition().y));
			}
		}else{
			if(m_spBar1.getPosition().x < -m_spBar1.getContentSize().width){
				m_spBar1.setPosition(CGPoint.ccp(m_spBar1.getContentSize().width, m_spBar1.getPosition().y));
			}else{
				m_spBar1.setPosition(CGPoint.ccp(m_spBar1.getPosition().x - m_nSpeed, m_spBar1.getPosition().y));
			}
			if(m_spBar2.getPosition().x < -m_spBar1.getContentSize().width){
				m_spBar2.setPosition(CGPoint.ccp(m_spBar1.getContentSize().width, m_spBar2.getPosition().y));
			}else{
				m_spBar2.setPosition(CGPoint.ccp(m_spBar2.getPosition().x - m_nSpeed, m_spBar2.getPosition().y));
			}
		}
	}
/*	public class UpdateBelt implements UpdateCallback{
		public void update(float arg0) {
			movingBar();
		}
	}*/
}


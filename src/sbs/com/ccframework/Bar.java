package	sbs.com.ccframework;

import	org.cocos2d.layers.CCLayer;
import	org.cocos2d.nodes.CCSprite;
import	org.cocos2d.types.CGRect;

import	sbs.com.object.ResourceManager;


public	class	Bar	extends	CCLayer	{
	private CCSprite	m_spBarBack;
	private CCSprite	m_spSel;
	private float	m_fMaxValue;
	private float	m_fCurValue;
	private float	m_fMaxLength;
	public	void	barWithSprite(String normalImage, String selectImage, float fMaxValue, float fCurValue){
		m_spBarBack	=	ResourceManager.sharedResourceManager().getSpriteWithName(normalImage);
		addChild(m_spBarBack);
		m_spSel = ResourceManager.sharedResourceManager().getSpriteWithName(selectImage);
		m_spSel.setAnchorPoint(0, 0.5f);
		m_spSel.setPosition(m_spSel.getPosition().x	-	m_spSel.getContentSize().width/2, m_spSel.getPosition().y);
		addChild(m_spSel);
		m_fMaxValue = fMaxValue;
		m_fCurValue = fCurValue;
		m_fMaxLength =	m_spSel.getContentSize().width;
		float	percent	= m_fCurValue	/	m_fMaxValue;
		float	fWidth = m_fMaxLength	*	percent;
		CGRect	rect = new CGRect ();
		rect.set(0, 0, fWidth, m_spSel.getContentSize().height);
		m_spSel.setTextureRect(rect);
	}
	public	void changeProgress(float fCurValue){
		m_fCurValue	=	fCurValue;
		if(m_fCurValue	>	m_fMaxValue){
			m_fCurValue	=	m_fMaxValue;
		}else if(m_fCurValue	<	0){
			m_fCurValue	=	0;
		}
		float	percent	=	m_fCurValue	/	m_fMaxValue;
		float	fWidth	=	m_fMaxLength	*	percent;
		CGRect	rect	=	new	CGRect();
		rect.set(0,	0,	fWidth,	m_spSel.getContentSize().height);
		m_spSel.setTextureRect(rect);
	}
}

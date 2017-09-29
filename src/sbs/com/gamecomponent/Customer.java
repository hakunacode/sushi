package sbs.com.gamecomponent;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRepeat;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import sbs.com.object.Common;
import sbs.com.object.GameInfo;
import sbs.com.object.LevelManager;
import sbs.com.object.ResourceManager;

public class Customer extends CCSprite {
	//CustomerType
	private final static int CHUNK = 0;
	private final static int CRITIC = 1;
	private final static int HOJI = 2;
	private final static int PAIN = 3;
	private final static int PrettyGirl = 4;
	private final static int KIKI = 5;
	private final static int kManTypeCount = 6;
		
	private final static int Anim_Stand = 0;
	private final static int Anim_Menu = 1;
	private final static int Anim_Eat = 2;
	private final static int Anim_Drink = 3;
	private final static int Anim_Bye = 4;
	private final static int Anim_Angle = 5;
	private final static int Anim_Order = 6;
	private final static int Anim_Wait = 7;

	private final static int State_None = 0;
	public final static int State_Need_Menu = 1;
	public final static int State_Receving_Menu = 2;
	private final static int State_Read_Menu = 3;
	public final static int State_OrderDish = 4;
	private final static int State_Eat = 5;
	private final static int State_Drink = 6;
	
	private CCAnimate		  m_animStand;
	private	CCAnimate		  m_animMenu;
	private	CCAnimate		  m_animEat;
	private	CCAnimate		  m_animDrink;
	private	CCAnimate		  m_animBye;
	private	CCAnimate		  m_animAngle;
	private CCAnimate		  m_animOrder;
	private	CCAnimate		  m_animWait;
		
	private	int	m_nCustomerType;
	private	int	m_nAnimID;
		
	private	int				 m_nSeat;
	public	int				 m_nOrderDish;
	private	int				 m_nHappy;
	private	int					m_nDishCost;
	private	float			   m_fIntendX;

	public	int				 m_curState;
	private	int				 m_prevState;
		
	private	CCSprite m_spHappyBar;
		
	public Customer(String filepath) {
		super(filepath);
	}

	private final int Tag_Bubble = 2;

	//////////////////////////////////////////////////////////////////////////////////////////////
	public static Customer customerWithType(int type, int nSeat){
		String str = null;
		switch(type){
		case PrettyGirl:
			str = "game/man/PrettyGirl/prettyGirl_stand01@3x.png";
			break;
		case CHUNK:
			str = "game/man/chunk/chuck_stand_01@3x.png";
			break;
		case CRITIC:
			str = "game/man/critic/critic_stand_01@3x.png";
			break;
		case HOJI:
			str = "game/man/hoji/hoji_stand_01@3x.png";
			break;
		case KIKI:
			str = "game/man/kiki/kiki_stand_01@3x.png";
			break;
		case PAIN:
			str = "game/man/pain/pain_stand_01@3x.png";
			break;
		}
		Customer customer = new Customer(str);
		customer.m_nCustomerType = type;
		customer.m_nSeat = nSeat;
		customer.m_curState = State_None;
		
		customer.loadRes();
		customer.createAnims();
		
		customer.setAnim(Anim_Stand);
		customer.createHappyBar();
		customer.setActionByType(State_Need_Menu);
		return customer;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	private void loadRes(){
		String pathman = "game/man/";
		switch (m_nCustomerType){
			case PrettyGirl:
				ResourceManager.sharedResourceManager().loadData(pathman + "PrettyGirl/prettygirl");
				break;
			case CHUNK:
				ResourceManager.sharedResourceManager().loadData(pathman + "chunk/chunk");
				break;
			case CRITIC:
				ResourceManager.sharedResourceManager().loadData(pathman + "critic/critic");
				break;
			case HOJI:
				ResourceManager.sharedResourceManager().loadData(pathman + "hoji/hoji");
				break;
			case KIKI:
				ResourceManager.sharedResourceManager().loadData(pathman + "kiki/kiki");
				break;
			case PAIN:
				ResourceManager.sharedResourceManager().loadData(pathman + "pain/pain");
				break;
			default:
				break;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	private void unloadRes(){
		String pathman = "game/man/";

		switch (m_nCustomerType)
		{
		case PrettyGirl:
			ResourceManager.sharedResourceManager().unloadData(pathman + "PrettyGirl/prettygirl");
		break;
		case CHUNK:
			ResourceManager.sharedResourceManager().unloadData(pathman + "chunk/chuck");			break;
		case CRITIC:
			ResourceManager.sharedResourceManager().unloadData(pathman + "critic/critic");			break;
		case HOJI:
			ResourceManager.sharedResourceManager().unloadData(pathman + "hoji/hoji");				break;
		case KIKI:
			ResourceManager.sharedResourceManager().unloadData(pathman + "kiki/kiki");				break;
		case PAIN:
			ResourceManager.sharedResourceManager().unloadData(pathman + "pain/pain");				break;
		default:
			break;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	private void createAnims(){
/*		if(AppDelegate getDelegate] isIPad])
			m_fIntendX = 0;
		else 
			m_fIntendX = 20);
	*/
		if (false){
			m_fIntendX = 0;
		}else{
			m_fIntendX = 20;
		}
		switch (m_nCustomerType) {
			case PrettyGirl:
				m_animStand = Common.getAnimate("prettyGirl_stand", 4, 0.2f);//;//.retain;
				m_animMenu  = Common.getAnimate("prettyGirl_menu", 5, 0.3f);//;//.retain;
				m_animEat   = Common.getAnimate("prettyGirl_eat", 5, 0.3f);//;//.retain;
				m_animBye   = Common.getAnimate("prettyGirl_bye", 5, 0.2f);//;//.retain;
				m_animAngle = Common.getAnimate("prettyGirl_angle", 5, 0.2f);//;//.retain;
				m_animOrder = Common.getAnimate("prettyGirl_order", 5, 0.4f);//;//.retain;
				m_animWait  = Common.getAnimate("prettyGirl_order", 5, 0.3f);//;//.retain;
				m_animDrink = Common.getAnimate("prettyGirl_stand", 5, 0.2f);//.retain;
				break;
			case CHUNK:
				m_animStand = Common.getAnimate("chuck_stand_", 5, 0.2f);//.retain;
				m_animMenu  = Common.getAnimate("chuck_menu_", 5, 0.2f);//.retain;
				m_animEat   = Common.getAnimate("chuck_eat_", 5, 0.2f);//.retain;
				m_animBye   = Common.getAnimate("chuck_bye_", 4, 0.2f);//.retain;
				m_animAngle = Common.getAnimate("chuck_angry_", 5, 0.2f);//.retain;
				m_animOrder = Common.getAnimate("chuck_order_", 5, 0.2f);//.retain;
				m_animWait  = Common.getAnimate("chuck_order_", 5, 0.3f);//.retain;
				m_animDrink = Common.getAnimate("chuck_stand_", 5, 0.2f);//.retain;
				break;
			case CRITIC:
				m_animStand = Common.getAnimate("critic_stand_", 5, 0.2f);//.retain;
				m_animMenu  = Common.getAnimate("critic_menu_", 5, 0.2f);//.retain;
				m_animEat   = Common.getAnimate("critic_eat_", 5, 0.2f);//.retain;
				m_animBye   = Common.getAnimate("critic_bye_", 5, 0.2f);//.retain;
				m_animAngle = Common.getAnimate("critic_angry_", 5, 0.2f);//.retain;
				m_animOrder = Common.getAnimate("critic_wait_", 5, 0.2f);//.retain;
				m_animWait  = Common.getAnimate("critic_wait_", 5, 0.3f);//.retain;
				m_animDrink = Common.getAnimate("critic_stand_", 5, 0.2f);//.retain;
				break;
			case HOJI:
				m_animStand = Common.getAnimate("hoji_stand_", 5, 0.2f);//.retain;
				m_animMenu  = Common.getAnimate("hoji_menu_", 5, 0.2f);//.retain;
				m_animEat   = Common.getAnimate("hoji_eat_", 5, 0.2f);//.retain;
				m_animBye   = Common.getAnimate("hoji_bye_", 5, 0.2f);//.retain;
				m_animAngle = Common.getAnimate("hoji_angry_", 5, 0.2f);//.retain;
				m_animOrder = Common.getAnimate("hoji_order_", 5, 0.2f);//.retain;
				m_animWait  = Common.getAnimate("hoji_order_", 5, 0.3f);//.retain;
				m_animDrink = Common.getAnimate("hoji_stand_", 5, 0.2f);//.retain;
				break;			
			case KIKI:
				m_animStand = Common.getAnimate("kiki_wait_", 6, 0.2f);//.retain;
				m_animMenu  = Common.getAnimate("kiki_menu_", 10, 0.1f);//.retain;
				m_animEat   = Common.getAnimate("kiki_eat_", 3, 0.2f);//.retain;
				m_animBye   = Common.getAnimate("kiki_bye_", 5, 0.2f);//.retain;
				m_animAngle = Common.getAnimate("kiki_angry_", 7, 0.2f);//.retain;
				m_animOrder = Common.getAnimate("kiki_order_", 6, 0.2f);//.retain;
				m_animWait  = Common.getAnimate("kiki_wait_", 6, 0.3f);//.retain;
				m_animDrink = Common.getAnimate("kiki_wait_", 4, 0.2f);//.retain;
				break;
			case PAIN:
				m_animStand = Common.getAnimate("pain_stand_", 5, 0.2f);//.retain;
				m_animMenu = Common.getAnimate("pain_menu_", 5, 0.2f);//.retain;
				m_animEat = Common.getAnimate("pain_eat_", 5, 0.2f);//.retain;
				m_animBye = Common.getAnimate("pain_bye_", 5, 0.2f);//.retain;
				m_animAngle = Common.getAnimate("pain_angry_", 5, 0.2f);//.retain;
				m_animOrder = Common.getAnimate("pain_order_", 5, 0.2f);//.retain;
				m_animWait = Common.getAnimate("pain_order_", 5, 0.3f);//.retain;
				m_animDrink = Common.getAnimate("pain_stand_", 5, 0.2f);//.retain;
				break;
			default:
				break;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
/*	private void deleteAnims()
	{
		m_animStand.release();
		m_animMenu.release);
		m_animEat.release);
		m_animDrink.release);
		m_animBye.release);
		m_animAngle.release);
		m_animOrder.release);
		m_animWait.release);
	}
*/	//////////////////////////////////////////////////////////////////////////////////////////////
	private void setAnim(int nAnimType){
		this.stopAllActions();
		switch (nAnimType){
			case Anim_Stand:
				super.runAction(CCRepeatForever.action(m_animStand));
				break;
			case Anim_Menu:{
					CCRepeat aAnim = CCRepeat.action(m_animMenu, 2);
					CCCallFuncN aOrder = CCCallFuncN.action(this, "orderDish");
					CCSequence aSeq = CCSequence.actions(aAnim, aOrder);
					this.runAction(CCRepeatForever.action(aSeq));
			}
				break;
			case Anim_Order:
			{
				CCRepeat aRepeat = CCRepeat.action(m_animOrder, 3);
				CCCallFuncN aCallWait = CCCallFuncN.action(this, "actionWait");
				CCSequence aSeq = CCSequence.actions(aRepeat,aCallWait);
				this.runAction(aSeq);
			}
				break;
			case Anim_Wait:
				this.runAction(CCRepeatForever.action(m_animWait));			
				break;
			case Anim_Bye:
				this.runAction(CCRepeatForever.action(m_animBye));			
				break;
				
			case Anim_Drink:
				this.runAction(CCRepeatForever.action(m_animDrink));			
				break;
				
			case Anim_Angle:
				this.runAction(CCRepeatForever.action(m_animAngle));			
				break;
				
			case Anim_Eat:
			{
				m_curState = State_Eat;
				this.removeBubble();
				CCRepeat aAnim = CCRepeat.action(m_animEat, 2);
				CCCallFuncN aPay = CCCallFuncN.action(this, "actionPay");
				CCSequence aSeq = CCSequence.actions(aAnim, aPay);
				this.runAction(aSeq);
			}
				break;
				
			default:
				break;
		}
	}
	public void setActionByType(int nType){
		switch (nType) 
		{
			case State_None:
				break;
			case State_Need_Menu:
				this.needMenuAction();
				break;
			case State_Receving_Menu:
				this.recevingMenuAction();
				break;
			case State_Read_Menu:
				break;
			case State_OrderDish:
				break;
			case State_Drink:
				break;
			default:
				break;
		}
		m_prevState = m_curState;
		m_curState = nType;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	private void needMenuAction(){
		float fx, fy;
		
		CCSprite spOrderDish = this.createButtle(GameInfo.BubbleType.Menu.ordinal());
		spOrderDish.setTag(Tag_Bubble);
		
		fx = this.getPosition().x + 80;
		fy = this.getPosition().y + 150;
		
		spOrderDish.setPosition(CGPoint.ccp(fx, fy));
		spOrderDish.setScale(0);

		CCScaleTo aScale1 = CCScaleTo.action(0.2f, 1.3f);
		CCScaleTo aScale2 = CCScaleTo.action(0.2f, 1.0f);
		
		CCSequence aSeq = CCSequence.actions(aScale1, aScale2);
		
		spOrderDish.runAction(aSeq);
		this.addChild(spOrderDish);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	private void recevingMenuAction(){
		increaseHappy();
		schedule("readMenuAction", 0.5f);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	private void increaseHappy(){
		m_nHappy++;
		if(m_nHappy > GameInfo.HAPPY_COUNT)
			m_nHappy = GameInfo.HAPPY_COUNT;
		this.changeHappySprite();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public void readMenuAction(float f){
		this.unschedule("readMenuAction");
		this.m_curState = State_Read_Menu;
		
		try {
			this.removeChildByTag(Tag_Bubble, true);
		} catch (Exception e) {
		}
		this.setAnim(Anim_Menu);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public void orderDish(Object sender){
		this.stopAllActions();
		
		int nCount = LevelManager.sharedLevelManager().m_arrDish.size();
		int nRand = (int)(Math.random()* nCount);
		
		Integer number = LevelManager.sharedLevelManager().m_arrDish.get(nRand);
		int nIndex = number.intValue();
		
		m_nOrderDish = nIndex;
		
		CCSprite spOrderDish = this.createButtle(nIndex);
		spOrderDish.setTag(Tag_Bubble);
		
		float fx, fy;
		
		fx = 80;
		fy = 150;
		
		spOrderDish.setPosition(CGPoint.ccp(fx, fy));
		spOrderDish.setScale(0);
		
		this.addChild(spOrderDish);
		
		CCScaleTo aScale1 = CCScaleTo.action(0.2f, 1.3f);
		CCScaleTo aScale2 = CCScaleTo.action(0.2f, 1.0f);
		CCSequence aSeq = CCSequence.actions(aScale1, aScale2);
		
		spOrderDish.runAction(aSeq);
		this.setAnim(Anim_Order);
		m_curState = State_OrderDish;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	private CCSprite createButtle(int bubbleType){
		String strBubble = null;
		String path = "game/bubble/";
		if (bubbleType == GameInfo.BubbleType.Mess.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_empty";
		}else if (bubbleType == GameInfo.BubbleType.Menu.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_menu";
		}else if (bubbleType == GameInfo.BubbleType.CaliforniaRoll.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_californiaRoll";
		}else if (bubbleType == GameInfo.BubbleType.Combo.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_combo";
		}else if (bubbleType == GameInfo.BubbleType.DragonRoll.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_dragonRoll";
		}else if (bubbleType == GameInfo.BubbleType.GunkanMaki.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_gunkanMaki";
		}else if (bubbleType == GameInfo.BubbleType.MegaMeal.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_megaMeal";
		}else if (bubbleType == GameInfo.BubbleType.Onigiri.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_onigiri";
		}else if (bubbleType == GameInfo.BubbleType.SalmonRoll.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_salmonRoll";
		}else if (bubbleType == GameInfo.BubbleType.ShirmpSushi.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_shrimpSushi";
		}else if (bubbleType == GameInfo.BubbleType.SuperFish.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_superFish";
		}else if (bubbleType == GameInfo.BubbleType.UnAgiRoll.ordinal()){
			strBubble = "sushi_restaurant_speech_bubble_unagiRoll";
		}else{
			return null;
		}
		CCSprite spBubble = ResourceManager.sharedResourceManager().getSpriteWithName(path + strBubble);
		return spBubble;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	private void removeBubble(){
		try {
			this.removeChildByTag(Tag_Bubble, true);
			this.removeChildByTag(Tag_Bubble, true);
		} catch (Exception e) {
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	private void createHappyBar(){
		float fx, fy;
		
		m_spHappyBar = ResourceManager.sharedResourceManager().getSpriteWithName("game/meterFrame/meterFrame_06");
		
		fx = 75 + m_fIntendX;
		fy = 20;
		
		m_spHappyBar.setPosition(CGPoint.ccp(fx, fy));
		this.addChild(m_spHappyBar);
		
		m_nHappy = GameInfo.HAPPY_COUNT;
		schedule("changeHappyState", GameInfo.HAPPY_TIME);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	public void changeHappyState(float f){
		m_nHappy--;
		
		if(m_nHappy <= 0){
			SushiGameLayer game = (SushiGameLayer)this.getParent();
			game.addPoint(-GameInfo.CUSTOMER_DISAPPEAR_COST);

			unschedule("changeHappyState");
			this.disappearCustomer(m_nSeat);
			return;
		}
		this.changeHappySprite();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	private void changeHappySprite(){
		String str = String.format("game/meterFrame/meterFrame_0%d", m_nHappy);
		
		CCTexture2D texture = CCTextureCache.sharedTextureCache().addImage(ResourceManager.sharedResourceManager().makeFileName(str, "png"));
		m_spHappyBar.setTexture(texture);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	private void disappearCustomer(int nSeat){
		SushiGameLayer game = (SushiGameLayer)this.getParent();
		game.disappearCustomer(nSeat);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	public void eatDish(){
		this.increaseHappy();
		
		this.setAnim(Anim_Eat);
		m_curState = State_Eat;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public void drinkBottle(){
		m_nHappy += 2;
		if(m_nHappy > GameInfo.HAPPY_COUNT)
			m_nHappy = GameInfo.HAPPY_COUNT;
		this.changeHappySprite();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	public void actionPay(Object sender){
		this.stopAllActions();
		
		this.setAnim(Anim_Bye);
		
		//change the dish is empty.
		SushiGameLayer game = (SushiGameLayer)this.getParent();
		game.emptyDish(m_nSeat);
		
		//create the score sprite.
		m_nDishCost = GameInfo.g_nDishCost[m_nOrderDish - 2];
		String str = this.getCostString(m_nDishCost);
		
		float fx, fy;
		
		CCSprite spCost = ResourceManager.sharedResourceManager().getSpriteWithName(str);
		
		fx = 100;
		fy = 100;
		
		spCost.setPosition(CGPoint.ccp(fx, fy));
		
		fy += 300;
		
		CCMoveTo aMove = CCMoveTo.action(0.8f, CGPoint.ccp(fx, fy));
		CCScaleTo aScale1 = CCScaleTo.action(0.4f, 1.5f);
		CCScaleTo aScale2 = CCScaleTo.action(0.4f, 1);
		CCSequence aSeq1 = CCSequence.actions(aScale1, aScale2);
		CCSpawn aSpa = CCSpawn.actions(aMove, aSeq1);
		CCCallFuncN aAdd = CCCallFuncN.action(this, "actionAddPoint");
		CCSequence aSeq2 = CCSequence.actions(aSpa, aAdd);
		
		spCost.runAction(aSeq2);
		this.addChild(spCost, 2);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	public void actionAddPoint(Object sender){
		SushiGameLayer SushiGameLayer = (SushiGameLayer)(this.getParent());
		SushiGameLayer.addPoint(m_nDishCost);
		this.disappearCustomer(m_nSeat);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	private String getCostString(int nCost){
		String str = null;
		String path = "game/custcash/";
		switch (nCost)
		{
			case 60:
				str = "custcash060";				break;
			case 80:
				str = "custcash080";				break;
			case 120:
				str = "custcash120";				break;
			case 280:
				str = "custcash280";				break;
			case 320:
				str = "custcash320";				break;
			case 340:
				str = "custcash340";				break;
			case 380:
				str = "custcash380";				break;
			case 460:
				str = "custcash460";				break;
			case 480:
				str = "custcash480";				break;
			default:
				return null;
		}
		return path + str;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public void actionWait(Object sender){
		this.setAnim(Anim_Wait);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public CGRect boundingBox(){
		float fx, fy;
		
		fx = this.getPosition().x - GameInfo.CUSTOMER_WIDTH / 2;
		fy = this.getPosition().y - GameInfo.CUSTOMER_HEIGHT / 2;
		
		CGRect rect = CGRect.make(fx, fy, fx + GameInfo.CUSTOMER_WIDTH, fy + GameInfo.CUSTOMER_HEIGHT);
		
		return rect;
	}
}

package sbs.com.gamecomponent;

import java.util.Vector;

import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import sbs.com.R;
import sbs.com.ccframework.Bar;
import sbs.com.ccframework.Button;
import sbs.com.ccframework.GrowButton;
import sbs.com.ccframework.Mat;
import sbs.com.object.AppSettings;
import sbs.com.object.Define;
import sbs.com.object.GameInfo;
import sbs.com.object.LevelManager;
import sbs.com.object.ResourceManager;
import android.util.Log;
import android.view.MotionEvent;

public class SushiGameLayer extends CCLayer {
//	private SushiGameScene parentScene;
	
	private Bar m_timeBar;
	
	private Belt m_belt;
	private Mat m_mat;
	private RollingMat	 m_rollingMat;
	private GrowButton	m_btnMenu;
	private GrowButton	m_btnReceipe;
	private GrowButton	m_btnTelephone;
	
	private TrayButton	m_btnTrays[] = new TrayButton[GameInfo.MAX_TRAYS];
	private Customer	m_customers[] = new Customer[GameInfo.MAX_SEATS];

	private Button m_dishes[] = new Button[GameInfo.MAX_SEATS];
	
	private ReceipeBook	m_receipeBook;
	private Telephone  m_telephone;

	private PauseDialogLayer m_pauseDlg;	

	private CCLabelAtlas m_lblBudget;
	private CCLabelAtlas m_lblTarget;
	private CCLabelAtlas m_lblTotal;
	private CCSprite m_spBottle;

	private boolean			m_bSeatFlags[] = new boolean[GameInfo.MAX_SEATS];
	private boolean			m_bPauseFlag;
	private boolean			m_bTouchBottleMat;
	private boolean			m_bTouchEnabled;
	private boolean			m_bCompleteFlag;
	
	private float		m_fIndentX;
	private int			m_nThrowTrays;
	private int			m_nReceviedTrays;
	private int			m_nCustomerCount;
	private int			m_nBudget;
	private int			m_nTarget;
	private int			m_nTotal;
	private int			m_nCurTime;
	private int			m_nCurLevel;
	private Vector<CCSprite>  m_arrTrays;

	private static class ZOrder{
		public static int Z_Back = 1;		public static int Z_Pause = 2;		public static int Z_InfoBar = 3;		public static int Z_Seat = 4;		public static int Z_Customer = 5;		public static int Z_Belt = 6;		public static int Z_Coundar = 7;
		public static int Z_MatOverLay = 8;		public static int Z_Menu = 9;		public static int Z_Receipe = 10;		public static int Z_Telephone = 11;		public static int Z_Dish = 12;		public static int Z_Bottle = 13;		public static int Z_Dialog = 14;
	}
	
	public SushiGameLayer(CCScene scene) {
		super();
//		parentScene = (SushiGameScene)scene;
		
		this.setIsTouchEnabled(true);

		if (false){
            m_fIndentX = 20;
		}else{
            m_fIndentX = 0;
		}
		Define.setScale(this);
		setAnchorPoint(0, 0);
		setPosition(0, 0);

		
		m_nTotal = AppSettings.getIntValue("TotalCost");
		int nLevel = AppSettings.getIntValue("CurLevel");
		this.startLevel(nLevel);
	}

	private void startLevel( int nLevel){
		m_nCurLevel = nLevel;
		m_arrTrays = new Vector<CCSprite>();
		
		m_nBudget = 0;
		m_nCurTime = 0;
		m_bPauseFlag = false;
		m_bTouchBottleMat = false;
		m_bTouchEnabled = true;
		m_bCompleteFlag = false;
		
		this. loadRes();
		this. createUI();
		this. playBGM();
		
		m_nThrowTrays = 0;
		m_nReceviedTrays = 0;
		m_nCustomerCount = 0;
		
		LevelManager.sharedLevelManager().setLevel(nLevel);
		
		m_nTarget = GameInfo.g_nTargetCost[m_nCurLevel];
		String strTarget = String.format("%d", m_nTarget);
		m_lblTarget.setString( strTarget);
		
//		this. showGoSprite();
		this. createCustomers();
		this. schedule("timerCounter", 1.0f);
	}
	private void loadRes(){
		ResourceManager.sharedResourceManager().loadData("game/telephone/wait/wait");
	}
	private void createUI(){
		this. createBackground();
		this. createDialogs();
		this. createInfoBar();
		this. createDishFloatingBar();
		this. createMat();
		this. createMenu();
		this. createRollingMat();
		this. createReceipeBook();
		this. createTelephone();
		this. createPauseButton();
		this. createTrays();
		this. createSeat();
	}
/*
	enum 
	{
		Tag_Tray,
		
	} Tag;

*/
	///////////////////////////////////////////////////////////////////////////////////////////////////
	private void clearLevel(){
		m_arrTrays.removeAllElements();
		super.unscheduleAllSelectors();
		super.removeAllChildren(true);
		this.unloadRes();
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	private void unloadRes(){
		ResourceManager.sharedResourceManager().unloadData("wait");
	}	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private void createBackground(){
		float fx, fy;
		
		int nBigLevel = (m_nCurLevel + 1) / 5;
		
		if(nBigLevel == 4)
			nBigLevel = 3;
		String strBack = String .format( "game/restaurantBackground/restaurantBackground_%d", nBigLevel + 1);
		CCSprite spBack = ResourceManager.sharedResourceManager().getSpriteWithName( strBack);
		
		fx = 384;
		fy = 1024 - spBack.getContentSize().height / 2;

		spBack.setPosition(fx, fy);
		this. addChild( spBack, ZOrder.Z_Back);
		
		CCSprite spBack2 = ResourceManager.sharedResourceManager(). getSpriteWithName( "game/restaurantBackground/workTop_v2");
		
		fy = spBack2.getContentSize().height /2;
		
		spBack2.setPosition(fx, fy);
		this. addChild( spBack2);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private void createInfoBar(){
		float fx, fy;
		
//		float fScale;			//= SCALE_SCREEN_HEIGHT;
		
		CCSprite spInfoBack = ResourceManager.sharedResourceManager().getSpriteWithName("game/bar/info_bar_back");
		fx = 384;			fy = 974;
		spInfoBack.setPosition(fx, fy);
		this. addChild( spInfoBack ,ZOrder.Z_InfoBar);
		
		//Timer bar	
		m_timeBar = new Bar();
		m_timeBar.barWithSprite("game/bar/bar_back", "game/bar/bar_sel", GameInfo.LEVEL_TIME, m_nCurTime);
		fx = (200) - m_fIndentX;		fy = (965);
		m_timeBar.setPosition(fx, fy);
		this. addChild( m_timeBar , ZOrder.Z_InfoBar);	
		
		//Budget.
		String strBudget = String.format( "%d", m_nBudget);
		m_lblBudget = CCLabelAtlas.label( strBudget, "game/bar/info_font.png", 18, 25,'0');
		
		fx += (210);
		m_lblBudget.setPosition(fx, fy);
		this. addChild( m_lblBudget , ZOrder.Z_InfoBar);
		
		//Total.
		String strTotal = String .format( "%d", m_nTotal);
		m_lblTotal =  CCLabelAtlas.label( strTotal, "game/bar/info_font.png", 18, 25,'0');
		fx += (170) + m_fIndentX * 3;
		m_lblTotal.setPosition(fx, fy);
		this. addChild( m_lblTotal , ZOrder.Z_InfoBar);
		
		//Target.
		CCSprite spTarget = ResourceManager.sharedResourceManager().getSpriteWithName( "game/bar/info_target");
		fx += (25);		fy -= (35);
		spTarget.setPosition(fx, fy);
		this. addChild( spTarget , ZOrder.Z_InfoBar);
		
		String strTarget = String .format( "%d", m_nTarget);
		m_lblTarget =  CCLabelAtlas.label( strTarget, "game/bar/target_font.png", 15, 19,'0');
		fx += (5);		fy -= (10);
		m_lblTarget.setPosition(fx, fy);
		this. addChild( m_lblTarget, ZOrder.Z_InfoBar);

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private void createDishFloatingBar(){
		float fx, fy;
		
		m_belt = new Belt();
		m_belt.moveBar(FloatingBar.RIGHT_BAR, "game/restaurantBackground/belt", 3);
		
		fx = (384);		fy = (532);
		m_belt.setPosition(fx, fy);
		this.addChild(m_belt, ZOrder.Z_Belt);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	private void createMat(){
		float fx, fy;
		
		m_mat = new Mat();
		fx = (112) - m_fIndentX;	fy = (290);
		m_mat.setPosition(fx, fy);
		this. addChild(m_mat);
		
		m_spBottle = ResourceManager.sharedResourceManager().getSpriteWithName( "game/bottle/sakeBottle");
		this. addChild(m_spBottle, ZOrder.Z_Bottle);
		m_spBottle.setVisible(false);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private void createMenu(){
		float fx, fy;
		
		m_btnMenu = GrowButton.buttonWithSpriteFrame( "game/dish_menu/menu", "game/dish_menu/menu", this, "actionMenu");
		
		fx = (112) - m_fIndentX;
		fy = (395);
		
		m_btnMenu.setPosition(fx, fy);
		this. addChild( m_btnMenu);
	}

		////////////////////////////////////////////////////////////////////////////////////////////////////
	private void createRollingMat(){
		float fx, fy;
		
		m_rollingMat = new RollingMat();
		fx = (285) - m_fIndentX/2;		fy = (340);
		m_rollingMat.setPosition (fx, fy);
		this. addChild(m_rollingMat);
		
		CCSprite spOverLay = ResourceManager.sharedResourceManager().getSpriteWithName("game/restaurantBackground/mat_overlay");
		fx += - m_fIndentX/2;	fy += (84.5);
		spOverLay.setPosition(fx, fy);
		this.addChild(spOverLay, ZOrder.Z_MatOverLay);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private void createReceipeBook(){
		float fx, fy;
		
		m_btnReceipe = GrowButton.buttonWithSpriteFrame( "game/receipeBook/recipeBook_english", "game/receipeBook/recipeBook_english", this, "actionRecipe");
		
		fx = (298);	fy = (140);
		
		m_btnReceipe.setPosition(fx, fy);

		this.addChild( m_btnReceipe);
		
		m_receipeBook = new ReceipeBook();
		
		fx = (384);
		fy = - (240);
		
		m_receipeBook.setPosition (fx, fy);	
		this.addChild(m_receipeBook, ZOrder.Z_Receipe);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private void createTelephone(){
		float fx, fy;
		
		m_btnTelephone = GrowButton.buttonWithSpriteFrame("game/telephone/telephone", "game/telephone/telephone", this, "actionTelephone");
		
		fx = (154) - m_fIndentX;
		fy = (140);
		
		m_btnTelephone.setPosition(fx, fy);
	
		this. addChild( m_btnTelephone);
		
		m_telephone = new Telephone();
		
		fx = (1180);
		fy = (210);
		
		m_telephone.setPosition (fx, fy);
		this.addChild(m_telephone, ZOrder.Z_Telephone);
	}

	///////////////////////////////////////////////////////////////////////////////////////////

	private void createDialogs(){
		m_pauseDlg = new PauseDialogLayer();
		
		m_pauseDlg.setPosition(384, 512);
		this. addChild(m_pauseDlg, ZOrder.Z_Dialog);
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	private void createPauseButton(){
		float fx, fy;
		
		GrowButton btnPause = GrowButton.buttonWithSpriteFrame("game/dialog/pause/pauseButton", "game/dialog/pause/pauseButton", this, "actionPause");
		
		fx = (104);
		fy = (926);
		
		btnPause.setPosition (fx, fy);
		
		this. addChild(btnPause, ZOrder.Z_InfoBar);
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	private void createTrays(){
		float fx, fy;
		
		fx = (490) + m_fIndentX/2;	fy = (360);
		m_btnTrays[0]   = TrayButton.buttonWithSpriteID("game/traysIngredients/trayMeat/trayMeat07", "game/traysIngredients/trayMeat/trayMeat07", this, "actionTrays", GameInfo.TrayType.Meat.ordinal());
		m_btnTrays[0].setPosition(fx, fy);
		this.addChild(m_btnTrays[0]);

		fy -= (140);
		m_btnTrays[1] = TrayButton.buttonWithSpriteID("game/traysIngredients/trayLaver/trayLaver07", "game/traysIngredients/trayLaver/trayLaver07", this, "actionTrays", GameInfo.TrayType.Laver.ordinal());
		m_btnTrays[1].setPosition(fx, fy);
		this.addChild(m_btnTrays[1]);
		
		fy -= (140);
		m_btnTrays[2] = TrayButton.buttonWithSpriteID("game/traysIngredients/trayCol/trayCol07", "game/traysIngredients/trayCol/trayCol07", this, "actionTrays", GameInfo.TrayType.Col.ordinal());
		m_btnTrays[2].setPosition(fx, fy);
		this.addChild(m_btnTrays[2]);
		
		fx += (150) + m_fIndentX;	fy = (360);
		m_btnTrays[3] = TrayButton.buttonWithSpriteID("game/traysIngredients/trayRice/trayRice07", "game/traysIngredients/trayRice/trayRice07", this, "actionTrays", GameInfo.TrayType.Rice.ordinal());
		m_btnTrays[3].setPosition(fx, fy);
		this.addChild(m_btnTrays[3]);
		
		fy -= (140);
		m_btnTrays[4] = TrayButton.buttonWithSpriteID("game/traysIngredients/trayZam/trayZam07", "game/traysIngredients/trayZam/trayZam07", this, "actionTrays", GameInfo.TrayType.Zam.ordinal());
		m_btnTrays[4].setPosition(fx, fy);
		this.addChild(m_btnTrays[4]);
		
		fy -= (140);
		m_btnTrays[5] = TrayButton.buttonWithSpriteID("game/traysIngredients/traySausage/traySausage07", "game/traysIngredients/traySausage/traySausage07", this, "actionTrays", GameInfo.TrayType.Sausage.ordinal());
		m_btnTrays[5].setPosition(fx, fy);
		this. addChild(m_btnTrays[5]);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	private void createSeat(){
		float fx, fy;
		
		int nBigLevel = (m_nCurLevel + 1) / 5;
		if(nBigLevel == 4)
			nBigLevel = 3;
		String str = String.format( "game/restaurantBackground/coundar_bar%d", nBigLevel + 1);
		CCSprite spCounderBar = ResourceManager.sharedResourceManager().getSpriteWithName(str);
		fx = (384);		fy = (610);
		spCounderBar.setPosition(fx, fy);
		this.addChild(spCounderBar, ZOrder.Z_Coundar);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private void playBGM(){
		int nBigLevel = m_nCurLevel / 5;
		
		if(nBigLevel == 4) nBigLevel = 3;
		int nBGMIndex = 0;
		
		switch (nBigLevel)
		{
			case 0:
				nBGMIndex = R.raw.gamebg1;				break;
			case 1:
				nBGMIndex = R.raw.gamebg2;				break;
			case 2:
				nBGMIndex = R.raw.gamebg3;				break;
			case 3:
				nBGMIndex = R.raw.gamebg4;				break;
			default:
				nBGMIndex = R.raw.gamebg1;
		}
		Define.playSound(nBGMIndex);
	}

	//initialize all member values.
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private void initMembers(){
		float fx, fy;
		
		for(int i = 0; i < GameInfo.MAX_SEATS; i++){
			m_bSeatFlags[i] = false;
			
			fx = 60 + i * 130;			fy = 680;
			
			int nBigLevel = m_nCurLevel / 5;
			if(nBigLevel == 4) nBigLevel = 3;
			String str = String.format( "game/restaurantBackground/seat%d", nBigLevel + 1);
			
			CCSprite spSeat = ResourceManager.sharedResourceManager().getSpriteWithName(str);
			spSeat.setPosition(fx, fy);
			this.addChild(spSeat, ZOrder.Z_Seat);
		}
	}

	public void timerCounter(float f){
		m_nCurTime++;
		if(m_nCurTime > GameInfo.LEVEL_TIME)
		{
			if(!m_bCompleteFlag) 
			{
				this.unschedule("appearCustomer");
				m_bCompleteFlag = true;		   
			}
			
			if(m_nCustomerCount != 0)
				return;
			
			this. unschedule("timerCounter");		
			if(m_nBudget >= m_nTarget)
			{
				this.levelComplete();
			} else {
				this.gameover();
			}
			return;
		}
		
		m_timeBar.changeProgress(m_nCurTime);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////

	private void levelComplete(){
		float fx, fy;
		fx = (384);		fy = (512);
		
		if(m_nCurLevel >= GameInfo.LEVEL_COUNT - 1){
			CongratulationDiglog dlg = new CongratulationDiglog();	
			dlg.setPosition(fx, fy);
			this.addChild(dlg, ZOrder.Z_Dialog);
			dlg.show();
		}
		else {
			LevelCompleteDlg dlg = new LevelCompleteDlg();	
			dlg.setPosition(fx, fy);
			this.addChild(dlg, ZOrder.Z_Dialog);
			dlg.show();
		}
		Define.playSound(R.raw.levelcomplete);
	}


	////////////////////////////////////////////////////////////////////////////////////////////////
	private void gameover(){
		float fx, fy;
		
		GameOverDialog dlg = new GameOverDialog();
		
		fx = (384);		fy = (512);
		
		dlg.setPosition(fx, fy);
		this.addChild(dlg, ZOrder.Z_Dialog);
		
		dlg.show();
		
		Define.playSound(R.raw.levelfail_bg);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	private void createCustomers(){
		float nInterval = 3.0f;
		
		this. initMembers();
		this. schedule("appearCustomer", nInterval);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public void appearCustomer(float f){
		int nBigLevel = (m_nCurLevel + 1) / 5;
		int nTypeLimit = 2;
		
		if(nBigLevel == 4) nBigLevel = 3;
		switch (nBigLevel) 
		{
			case 0:
				nTypeLimit = 2;			break;
			case 1:
				nTypeLimit = 3;			break;
			case 2:
				nTypeLimit = 4;			break;
			case 3:
				nTypeLimit = 5;			break;
		}
		int nType = (int)(nTypeLimit * Math.random());
		int nSeat = (int)(GameInfo.MAX_SEATS * Math.random());
		
		if(m_bSeatFlags[nSeat])
		 	return;
		
		float fx, fy;
		
		m_customers[nSeat] = Customer.customerWithType(nType, nSeat);
		m_bSeatFlags[nSeat] = true;
		
		fx = (60) + nSeat * (130);		fy = (570);	

		m_customers[nSeat].setPosition(fx, fy);
		
		CCMoveTo aMove = CCMoveTo.action(0.5f,CGPoint.ccp(fx, fy + (GameInfo.CUSTOMER_UP_HEIGHT)));
		CCCallFuncN aNeedMenu = CCCallFuncN.action(this, "actionNeedMenu");
		CCSequence aSeq = CCSequence.actions(aMove, aNeedMenu);
		
		this.addChild(m_customers[nSeat], ZOrder.Z_Customer);
		m_customers[nSeat].runAction(aSeq);
		
		m_nCustomerCount++;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	public void actionNeedMenu(Object sender)
	{
		Customer customer = (Customer)sender;
		customer.setActionByType(Customer.State_Need_Menu);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////

	public void disappearCustomer(int nSeat){
		this. removeChild(m_customers[nSeat], true);
		m_customers[nSeat] = null;
		
		m_nCustomerCount--;
	}

/*	//show the go sprite in the level first start.
	/////////////////////////////////////////////////////////////////////////////////////////////////
	private void showGoSprite(){
		float fx, fy;
		fx = (850);	fy = (750);
		
		CCSprite spGo = ResourceManager.sharedResourceManager().getSpriteWithName("prompt_go_english");
		
		spGo.setPosition = (fx, fy);
		this. addChild, spGo);
		
		fx = (384);
		
		id aMove1 = [CCMoveTo actionWithDuration, 0.8 setPosition, (fx, fy));
		id aScale1 = [CCScaleTo actionWithDuration, 0.15 scale, 1.5f);
		id aScale2 = [CCScaleTo actionWithDuration, 0.15 scale, 1);
		id aDelay = [CCDelayTime actionWithDuration, 2.0f);
		
		fx = private (200);
		
		id aMove2 = [CCMoveTo actionWithDuration, 0.8 setPosition, (fx, fy));
		id aRemove = [CCCallFuncN actionWithTarget, this. selector, selector(removeSprite,));
		id aSeq = [CCSequence actions, aMove1, aScale1, aScale2, aDelay, aMove2, aRemove, null);
		
		spGo.runAction(aSeq);
	}

*/	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void removeSprite(Object sender){
		this.removeChild((CCNode)sender, true);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void actionMenu(Object sender)
	{
		for(int i = 0; i < GameInfo.MAX_SEATS; i++)
		{
			if(m_bSeatFlags[i] && m_customers[i] != null && m_customers[i].m_curState == Customer.State_Need_Menu)
			{
				m_customers[i].setActionByType(Customer.State_Receving_Menu);
				
				CCSprite spMenu = ResourceManager.sharedResourceManager().getSpriteWithName("game/dish_menu/menu");
				
				spMenu.setPosition(m_btnMenu.getPosition());
				
				CCMoveTo aMove = CCMoveTo.action(0.5f, m_customers[i].getPosition());
				CCRotateBy aRotate = CCRotateBy.action(0.5f, 360);
				CCSpawn aSpa = CCSpawn.actions(aMove, aRotate);
				CCCallFuncN aRemove = CCCallFuncN.action(this, "removeSprite");
				CCSequence aSeq = CCSequence.actions(aSpa, aRemove);
				
				this.addChild(spMenu, ZOrder.Z_Menu);
				spMenu.runAction(aSeq);
				
				return;
			}
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void actionRecipe(Object sender){
		Define.playEffect(R.raw.buttonclick);
		
		this.touchDisable();

		m_receipeBook.open();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void actionTelephone(Object sender){
		Define.playEffect(R.raw.buttonclick);
		
		this.touchDisable();
		m_telephone.show();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void actionPause(Object sender){
		Define.playEffect(R.raw.buttonclick);
		
		this. pauseGame();
		m_pauseDlg.show();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	//action process such as meat, rice, zam.
	public void actionTrays(Object sender){
		if(m_belt.isFull())
			return;
		if(m_rollingMat.m_nItemIndex == RollingMat.MAX_TRAY_COUNT)
			return;
		if(m_rollingMat.m_bMakingDish)
			return;
		
		CCMenuItem button = (CCMenuItem)sender;
		int nIndex = button.getTag();

		
		int nTrayIndex = m_btnTrays[nIndex].m_nTrayIndex;
		int nRemainItem = m_btnTrays[nIndex].m_nCurItem;
		
		if(nRemainItem == 1)
			return;
		
		//if tray is charging, return.
		if(m_btnTrays[nIndex].m_bChargeFlag)
			return;
		
		m_btnTrays[nIndex].m_nCurItem--;
		m_btnTrays[nIndex].changeSprite(nTrayIndex);
		
		String str = null;
		if (nTrayIndex == GameInfo.TrayType.Meat.ordinal()){
			str = "trayMeat/trayMeat08";
		}else if (nTrayIndex == GameInfo.TrayType.Laver.ordinal()){
			str = "trayLaver/trayLaver08";
		}else if (nTrayIndex == GameInfo.TrayType.Col.ordinal()){
			str = "trayCol/trayCol08";
		}else if (nTrayIndex == GameInfo.TrayType.Rice.ordinal()){
			str = "trayRice/trayRice08";
		}else if (nTrayIndex == GameInfo.TrayType.Zam.ordinal()){
			str = "trayZam/trayZam08";
		}else if (nTrayIndex == GameInfo.TrayType.Sausage.ordinal()){
			str = "traySausage/traySausage08";
		}
		str = "game/traysIngredients/" + str;
		m_nThrowTrays++;
		CCSprite spTray = ResourceManager.sharedResourceManager().getSpriteWithName(str);
		spTray.setPosition(m_btnTrays[nTrayIndex].getPosition());
		this.addChild(spTray);
		m_rollingMat.addItem(button.getTag());
		
		float fx, fy;
		
		fx = m_rollingMat.getPosition().x - (GameInfo.TRAY_ITEM_POS) + m_rollingMat.m_nItemIndex * (GameInfo.TRAY_ITEM_WIDTH);
		fy = m_rollingMat.getPosition().y;
		
		CCMoveTo aMove = CCMoveTo.action(GameInfo.FLY_TRAY_TIME,CGPoint.ccp (fx, fy));
		CCRotateBy aRotate = CCRotateBy.action(GameInfo.FLY_TRAY_TIME, 360);
		CCCallFuncN aFreeFlag = CCCallFuncN.action(this, "actionFreeThrowFlag");
		CCSpawn aSpa = CCSpawn.actions(aMove, aRotate);
		CCSequence aSeq = CCSequence.actions(aSpa, aFreeFlag);
		
		spTray.runAction(aSeq);
		m_arrTrays.add(spTray);
		m_rollingMat.m_nItemIndex++;
	}
		////////////////////////////////////////////////////////////////////////////////////////////////////
	public void actionFreeThrowFlag(Object sender){
		m_nReceviedTrays++;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private void moveTrays(){
		float fx, fy;
		
		int nOffset = m_rollingMat.movingHeight();
		
		for(int i = 0; i < m_arrTrays.size(); i++) 
		{
			CCSprite spTray = m_arrTrays.get(i);
			
			fx = spTray.getPosition().x;
			fy = spTray.getPosition().y + nOffset;

			CCMoveTo aMove = CCMoveTo.action(GameInfo.MOVE_ROLLING_MAT_INTERVAL, CGPoint.ccp(fx, fy));
			CCCallFuncN aRemove = CCCallFuncN.action(this, "removeTray");
			CCSequence aSeq = CCSequence.actions(aMove, aRemove);
			
			spTray.runAction(aSeq);
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void removeTray(Object sender){
		this.removeChild((CCNode)sender, true);
		m_arrTrays.remove((CCNode)sender);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public void addDishInBelt(int nDishType){
		m_belt.addDish(nDishType);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////

	public String getDishString(int nDishType){
		String path = "game/dish/";
		String str = null;
		if (nDishType == GameInfo.BubbleType.Mess.ordinal()){
			str = "plate_mess";
		}else if (nDishType == GameInfo.BubbleType.CaliforniaRoll.ordinal()){
			str = "plate_californiaRoll";
		}else if (nDishType == GameInfo.BubbleType.Combo.ordinal()){
			str = "plate_combo";
		}else if (nDishType == GameInfo.BubbleType.DragonRoll.ordinal()){
			str = "plate_dragonRoll";
		}else if (nDishType == GameInfo.BubbleType.GunkanMaki.ordinal()){
			str = "plate_gunkanMaki";
		}else if (nDishType == GameInfo.BubbleType.MegaMeal.ordinal()){
			str = "plate_megaMeal";
		}else if (nDishType == GameInfo.BubbleType.Onigiri.ordinal()){
			str = "plate_onigiri";
		}else if (nDishType == GameInfo.BubbleType.SalmonRoll.ordinal()){
			str = "plate_salmonRoll";
		}else if (nDishType ==  GameInfo.BubbleType.ShirmpSushi.ordinal()){
			str = "plate_shrimpSushi";
		}else if (nDishType == GameInfo.BubbleType.SuperFish.ordinal()){
			str = "plate_superFish";
		}else if (nDishType == GameInfo.BubbleType.UnAgiRoll.ordinal()){
			str = "plate_unagiRoll";
		}
		return path + str;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	private CCSprite getDishSprite(int nDishType){
		
		CCSprite spDish = ResourceManager.sharedResourceManager().getSpriteWithName(this.getDishString(nDishType));
		
		return spDish;
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////

	public boolean checkWithDish(CGPoint ptDishPos, int nDishType){
		for(int i = 0; i < GameInfo.MAX_SEATS; i++)
		{
			if(m_bSeatFlags[i] && m_customers[i] != null && m_customers[i].m_curState == Customer.State_OrderDish)
			{
				float fx1 = m_customers[i].getPosition().x - (10);
				float fx2 = m_customers[i].getPosition().x + (10);
				
				if(fx1 <= ptDishPos.x && fx2 >= ptDishPos.x && nDishType == m_customers[i].m_nOrderDish)
				{
					m_customers[i].eatDish();
					
					String strDish = this.getDishString(nDishType);
					
					m_dishes[i] = Button.buttonWithSpriteID(strDish , strDish , this, "actionDishes", i);
					
					m_dishes[i].setPosition(ptDishPos.x, ptDishPos.y + m_belt.getPosition().y);
					
					CCMoveTo aMove = CCMoveTo.action(0.2f, CGPoint.ccp(m_dishes[i].getPosition().x, m_dishes[i].getPosition().y + 120));
					m_dishes[i].runAction(aMove);
					this.addChild(m_dishes[i], ZOrder.Z_Dish);		  
					return true;
				}
			}
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	public void actionDishes(Object sender){
		CCMenuItem dish = (CCMenuItem)sender;
		int nSeat = dish.getTag();
		
		if(m_bSeatFlags[nSeat] && m_customers[nSeat] == null){
			float fx, fy;
			
			fx = dish.getPosition().x;
			fy = dish.getPosition().y;
			
			CCMoveTo aMove = CCMoveTo.action(0.5f, CGPoint.ccp(1500, fy));
			CCCallFuncN aRemoveDish = CCCallFuncN.action(this, "actionRemoveDish");
			CCSequence aSeq = CCSequence.actions(aMove, aRemoveDish);
			
			dish.runAction(aSeq);
		}	
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	public void actionRemoveDish(Object sender){
		Dish dish = (Dish)sender;
		int nSeat = dish.getTag();
		
		this.removeChild(dish, true);
		m_dishes[nSeat] = null;
		
		m_bSeatFlags[nSeat] = false;	
	}
	//---------- Charge the Tray buttons. ---------------------------------------------------//
	///////////////////////////////////////////////////////////////////////////////////////////
	public void chargeTray(int nIndex, int nAction){
		if(nIndex == GameInfo.TrayType.ExtraBottle.ordinal()){
			if(m_mat.isChargeAvaliable()){
				m_mat.charge(nAction);
			}
			return;
		} 
		
		if(m_btnTrays[nIndex].isChargeAvaliable())
			m_btnTrays[nIndex].charge(nAction);
		
		int nExtra = 0;
		if(nAction != GameInfo.TrayChargeAction.Free_Charge.ordinal())
		{
			nExtra = 50;
		} 
		
		int nCost = GameInfo.g_nTrayCost[nIndex] + nExtra;
		m_nBudget -= nCost;
		
		if(m_nBudget < 0)
			m_nBudget = 0;
		m_lblBudget.setString(String.format("%d", m_nBudget));
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	public void emptyDish(int nSeat){
		if(m_dishes[nSeat] == null)
			return;
		
		String str = "game/dish/plate_empty";
		m_dishes[nSeat].changeSprite(str);
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	public void addPoint(int nCost){
		m_nBudget += nCost;
		Log.d("Nala", "cost = " + nCost);
		if(m_nBudget < 0) m_nBudget = 0;
		String str = String.format( "%d", m_nBudget);
		m_lblBudget.setString(str);
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	private void pauseGame(){
		if(m_bPauseFlag)
			return;
		m_bPauseFlag = true;
		this.pauseSchedulerAndActions();
		
		for(int i = 0; i < GameInfo.MAX_SEATS; i++){
			if(m_bSeatFlags[i] && m_customers[i] != null){
				m_customers[i].pauseSchedulerAndActions();
			}
		}
		m_belt.pause();
		this.touchDisable();
	}
	public void resumeGame(){
		m_bPauseFlag = false;
		this. resumeSchedulerAndActions();
		m_belt.resume();
		for(int i = 0; i < GameInfo.MAX_SEATS; i++){
			if(m_bSeatFlags[i])
			{
				m_customers[i].resumeSchedulerAndActions();
			}
		}
		this.touchEnable();
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	private void touchDisable(){
		if(!m_bTouchEnabled)
			return;
		m_bTouchEnabled = false;
		
		m_btnMenu.m_bTouchEnable = false;
		m_btnReceipe.m_bTouchEnable = false;
		m_btnTelephone.m_bTouchEnable = false;
		
		for(int i = 0; i < GameInfo.MAX_TRAYS; i++)
		{
			if(m_btnTrays[i] != null)
				m_btnTrays[i].touchEnable(false);
		}
		
		for(int i = 0; i < GameInfo.MAX_SEATS; i++){
			if(m_dishes[i] != null)
				m_dishes[i].touchEnable(false);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	public void touchEnable(){
		if(m_bTouchEnabled)
			return;

		m_bTouchEnabled = true;
		m_btnMenu.m_bTouchEnable = true;
		m_btnReceipe.m_bTouchEnable = true;
		m_btnTelephone.m_bTouchEnable = true;
		
		for(int i = 0; i < GameInfo.MAX_TRAYS; i++)
		{
			if(m_btnTrays[i] != null)
				m_btnTrays[i].touchEnable(true);
		}
		
		for(int i = 0; i < GameInfo.MAX_SEATS; i++){
			if(m_dishes[i] != null)
				m_dishes[i].touchEnable(true);
		}	
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	public void actionNextLevel(Object sender){
		m_nCurLevel++;
		this. saveSettings();	
		this. clearLevel();
		this. startLevel(m_nCurLevel);
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	public void actionRestart(Object sender){
		this.saveSettings();
		this.clearLevel();
		this.startLevel(m_nCurLevel);
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	private void saveSettings(){
		m_nTotal += m_nBudget;
		String str2 = String.format("%d", m_nTotal);
		m_lblTotal.setString(str2);
		
		AppSettings.setIntValueWithName(m_nTotal, "TotalCost");
		AppSettings.setIntValueWithName(m_nCurLevel, "CurLevel");
	}
///////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void registerWithTouchDispatcher() {
		CCTouchDispatcher.sharedDispatcher().addTargetedDelegate(this, 0, true);
		super.registerWithTouchDispatcher();
	}

	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		if(!m_bTouchEnabled)
			return false;
		if(m_bPauseFlag)
			return false;
	    CGPoint location = CGPoint.ccp(event.getX(), event.getY());
		location = CCDirector.sharedDirector().convertToGL(location);
		location = CGPoint.ccp(location.x / Define.scaleX, location.y / Define.scaleY);
		
		this.checkTouchRollingMat(location);
		this.checkTouchBottleMat(location);
		return true;
	}
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		if(m_bPauseFlag)
			return false;
		if(!m_bTouchBottleMat)
			return false;

	    CGPoint location = CGPoint.ccp(event.getX(), event.getY());
		location = CCDirector.sharedDirector().convertToGL(location);
		location = CGPoint.ccp(location.x / Define.scaleX, location.y / Define.scaleY);

		m_spBottle.setPosition(location);
		
		return super.ccTouchesMoved(event);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean ccTouchesEnded(MotionEvent event) {
		if(m_bPauseFlag)
			return false;
		if(!m_bTouchBottleMat)
			return false;
		
		m_bTouchBottleMat = false;
		m_spBottle.setVisible(false);
		
	    CGPoint location = CGPoint.ccp(event.getX(), event.getY());
		location = CCDirector.sharedDirector().convertToGL(location);
		location = CGPoint.ccp(location.x / Define.scaleX, location.y / Define.scaleY);
		
		for(int i = 0; i < GameInfo.MAX_SEATS; i++){
			if(m_bSeatFlags[i] && m_customers[i]!= null){
				CGRect rtRect = m_customers[i].boundingBox();
				if(CGRect.containsPoint(rtRect, location)){
					if(m_mat.decreaseBottle()){
						m_customers[i].drinkBottle();
						
						CCSprite spBottle = ResourceManager.sharedResourceManager().getSpriteWithName("game/bottle/sakeBottle");
						CGPoint ptPos = m_customers[i].getPosition();
						
						spBottle.setPosition(CGPoint.ccp(ptPos.x - 10,  ptPos.y - 30));
						CCDelayTime aDelay = CCDelayTime.action(1.0f);
						CCCallFuncN aDel = CCCallFuncN.action(this, "removeBottle");
						CCSequence aSeq = CCSequence.actions(aDelay, aDel);
						spBottle.runAction(aSeq);
						
						this.addChild(spBottle, ZOrder.Z_Dish);

					}
					return true;
				}
			}
		}
		return super.ccTouchesEnded(event);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void removeBottle(Object sender){
		this.removeChild((CCNode)sender, true);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	private void checkTouchRollingMat(CGPoint location){
		CGRect rect = m_rollingMat.boundingBox();
		
		if(CGRect.containsPoint(rect, location))
		{
			if(m_nThrowTrays == 0 || m_nThrowTrays != m_nReceviedTrays)
				return;
			if(m_rollingMat.m_nCurAction == RollingMat.ActionType.RollingMat_Make_Dish.ordinal())
				return;
			
			m_rollingMat.makeDish();
			this.moveTrays();
			
			m_nThrowTrays = 0;
			m_nReceviedTrays = 0;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	private void checkTouchBottleMat(CGPoint location){
		CGRect rect = m_mat.boundingBox();
		
		if(CGRect.containsPoint(rect, location))
		{
			m_bTouchBottleMat = true;
			m_spBottle.setPosition(location);
			m_spBottle.setVisible(true);
		}	
	}
}
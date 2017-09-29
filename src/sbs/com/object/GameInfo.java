package sbs.com.object;


public class GameInfo {
	public static final float MOVE_ROLLING_MAT_INTERVAL = 0.5f;
	public static final int MAX_TRAY_ITEM_COUNT = 7;
	public static final int RECEIPE_BOOK_PAGE = 5;
	public static final int TELEPHONE_BUTTON_COUNT = 9;

	public static final int MAX_TRAYS = 6;
	public static final int MAX_SEATS = 6;

	public static final int TRAY_ITEM_COUNT = 6;
	public static final float FREE_CHARGE_TIME = 1.5f;
	public static final float EXPRESS_CHARGE_TIME = 0.5f;

	public static final int DISH_COUNT = 10;
	public static final int MAX_BOTTLE_COUNT = 2;
	public static final float FLY_TRAY_TIME = 0.5f;
	/////////////////////////////////////////////////////////////////////////////
	public static final int HAPPY_COUNT = 6;
	public static final int HAPPY_TIME = 10;
	/////////////////////////////////////////////////////////////////////////////
	public static final int LEVEL_TIME = 60;
	public static final int LEVEL_COUNT = 20;
	////////////////////////////////////////////////////////////////////////////
	public static final int BELT_DISH_COUNT=  6;
	////////////////////////////////////////////////////////////////////////////
	public static final float DIALOG_SHOW_TIME = 0.5f;
	public static final int CUSTOMER_WIDTH = 80;
	public static final int Free_Charge = 0;
	public static final int Express_Charge = 1;
	public static final int CUSTOMER_HEIGHT = 80;
	public static final int CUSTOMER_UP_HEIGHT = 142;
	public static final int CUSTOMER_DISAPPEAR_COST = 50;
	public static final int TRAY_ITEM_POS = 60;
	public static final int TRAY_ITEM_WIDTH = 20;

	public static final int g_nDishCost[] = {
			280,  480,  480,  340,  280,
			120,  380,  280,  320,  460
	};
	public static final int g_nLevelPos[][] = {
			{392, 436, 0}, {328, 408, 0}, {250, 400, 0}, {156, 415, 0}, {164, 505, 2},
			{251, 491, 0}, {340, 496, 0}, {417, 526, 0}, {487, 551, 0}, {568, 582, 2},
			{510, 633, 0}, {437, 630, 0}, {374, 596, 0}, {291, 590, 0}, {194, 609, 3},
			{279, 654, 0}, {326, 698, 0}, {276, 724, 0}, {205, 727, 0}, {132, 757, 1},
	};
	public static final int g_nTargetCost[] = {
		    200,  400,  600,  800,  900,
		    600,  700,  750,  900,  1000,
		    1000, 1200, 1500, 1750, 2000,
		    1700, 1800, 2000, 2200, 2500,
	};
	public static final int g_nTrayCost[] = {
			350, 100, 300, 350, 350, 350, 100
	};
	public enum BubbleType{
		Menu,
		Mess,
		CaliforniaRoll,
		Combo,
		DragonRoll,
		GunkanMaki,
		MegaMeal,
		Onigiri,
		SalmonRoll,
		ShirmpSushi,
		SuperFish,
		UnAgiRoll,
		kBubbleCount,
	};
	public enum TrayType{
		Meat,
		Laver,
		Col,
		Rice,
		Zam,
		Sausage,
		ExtraBottle,
		FreeButton,
		ExpreeButton,
	} ;
	public enum TrayChargeAction{
		Free_Charge ,
		Express_Charge, 
	} ;
}

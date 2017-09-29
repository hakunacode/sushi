package sbs.com.object;

import java.util.ArrayList;

public class LevelManager extends Object{
	public int  m_nCurLevel;
		
	public int	m_nTarget;
	public ArrayList<Integer>  m_arrDish = null;
	
	private static LevelManager _sharedLevelManager = null;
	public static LevelManager sharedLevelManager(){
		if (_sharedLevelManager == null){
			_sharedLevelManager = new LevelManager();
		}
		return _sharedLevelManager;
	}
	
	public LevelManager(){
		m_arrDish = new ArrayList<Integer>();
		initGameInfo();
		createObjects();
	}
	private void createObjects(){
		createLevelInfo(m_nCurLevel);
	}
	private void deleteObjects(){
		m_arrDish.clear();
	}
	private void resetObjects(){
		deleteObjects();
		createObjects();
		
	}
	private void initGameInfo(){
		
	}
	public void setLevel(int level){
		m_nCurLevel = level;
		this.resetObjects();
	}
	private void createLevelInfo(int level){
		m_arrDish.clear(); 
		switch (level) 
		{
		case 0:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_nTarget = 200;
			break;
		case 1:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_nTarget = 200;
			break;
		case 2:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_nTarget = 200;
			break;
		case 3:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_nTarget = 200;
			break;
		case 4:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_nTarget = 200;
			break;
		case 5:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_nTarget = 200;
			break;
		case 6:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_nTarget = 200;
			break;
		case 7:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_nTarget = 200;
			break;
		case 8:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_nTarget = 200;
			break;
		case 9:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_nTarget = 200;
			break;
		case 10:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_nTarget = 200;
			break;
		case 11:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
		 	m_nTarget = 200;
			break;
		case 12:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_nTarget = 200;
			break;
		case 13:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SalmonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_nTarget = 200;
			break;
		case 14:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SalmonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_nTarget = 200;
			break;
		case 15:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SalmonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.UnAgiRoll.ordinal()));
			m_nTarget = 200;
			break;
		case 16:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.DragonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SalmonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.UnAgiRoll.ordinal()));
			m_nTarget = 200;
			break;
		case 17:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.DragonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SalmonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.UnAgiRoll.ordinal()));
			m_nTarget = 200;
			break;
		case 18:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.DragonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SalmonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.UnAgiRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Combo.ordinal()));
			m_nTarget = 200;
			break;
		case 19:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.DragonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SalmonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.UnAgiRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Combo.ordinal()));		
			m_nTarget = 200;
			break;
		case 20:
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Onigiri.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.CaliforniaRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.DragonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.GunkanMaki.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.MegaMeal.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SalmonRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.ShirmpSushi.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.SuperFish.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.UnAgiRoll.ordinal()));
			m_arrDish.add(Integer.valueOf(GameInfo.BubbleType.Combo.ordinal()));
			m_nTarget = 200;
			break;
		default:
			break;
		}
	}
}



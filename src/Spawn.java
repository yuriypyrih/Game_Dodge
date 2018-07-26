import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Player player1;
	public int difficulty; // 0 = EASY, 1 = NORMAL, 2 = HARD
	private static int outterLevel = 1; // 0 = PLAY AGAIN
	
	private Boolean keyStar1 = false, keyStar2 = false, keyStar3 = false;
	private boolean[] alreadyExecuted = new boolean[20];
	
	
	private int spawnTimer = 0;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		
		difficulty = 1; //NORMAL by default
		
		for(int i = 0; i<20 ; i++) alreadyExecuted[i] = false;
		
	}
	
	public void WinKeyStar(int stars) {
		switch (stars) {
		case 1 : keyStar1 = true;break;
		case 2 : keyStar2 = true;break;
		case 3 : keyStar3 = true;break;
		default: break;
		}
	}
	
	private boolean checkKeyStar( Boolean keyStar) {
		if(keyStar) {
			keyStar1 = false;
			keyStar2 = false;
			keyStar3 = false;
			return true;
		}return false;
	}
	
	public boolean Victory() {
		if(keyStar3) {
			return true;
		}else return false;
	}
	
	public static int getOutterLevel() {
		return outterLevel;
	}
	public int getDifficulty() {
		return difficulty;
	}
	
	public boolean nextLevel() {
		if(outterLevel < 5) {
			startLevel(++outterLevel);
			return true;
		}else
		return false;
	}
	
	
	
	public void startLevel(int outterLevel) {
		reset();
		hud.reset();
		
		spawnTimer = 0;
		if(outterLevel != 0) {
			this.outterLevel = outterLevel;
		}
		
		player1 = new Player((Game.WIDTH/2-32),(Game.HEIGHT/2-32),ID.Player, handler, this);
		handler.addObject( player1 );
		
		if(this.outterLevel == 1 || this.outterLevel == 2) {
		handler.addObject( new BasicEnemy(0,0,ID.BasicEnemy,handler));
		
		}else if(this.outterLevel == 3 ) {
			handler.addObject( new SmartEnemy(Game.WIDTH - 16, 0,ID.SmartEnemy,handler));
		}else if(this.outterLevel == 4 ) {
			handler.addObject( new Healer(Game.WIDTH-25,Game.HEIGHT-50,ID.Healer,handler));
			handler.addObject( new Healer(Game.WIDTH-25,15,ID.Healer,handler));
			handler.addObject( new VirusEnemy(0,30, ID.VirusEnemy,handler));
		
		}
	}
	
	
	
	public void reset() {
		keyStar1 = false;
		keyStar2 = false;
		keyStar3 = false;
		for(int i = 0; i<20 ; i++) alreadyExecuted[i] = false;
	}
	
	

	public void tick() {
		hud.setStars(player1.getStars());
		spawnTimer++;	
		if(spawnTimer>=20) {
			spawnTimer = 0;
			hud.setStageTimer(hud.getStageTimer() + 1);
			
			/*HEALRS!*/
			if(hud.getStageTimer() % 12 == 0){
				handler.addObject( new Healer(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-100)+50,ID.Healer,handler));
			}
			
			if(outterLevel==1) {/*OUTTER MISSION LEVEL 1*/
				
				
				
				/*ENEMIES*/
				if(hud.getStageTimer() == 10) {
					
					handler.addObject( new BasicEnemy(0,30, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 20){
					handler.addObject( new BasicEnemy(0,0, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 50 ){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				else if( checkKeyStar(keyStar1) && !alreadyExecuted[0]){
					handler.addObject( new FastEnemy(0,0,ID.FastEnemy,handler));	//FAST ENEMY
					hud.setStageTimer(51);
					alreadyExecuted[0] = true;
				}
				else if(hud.getStageTimer() >= 70 && !alreadyExecuted[1] && alreadyExecuted[0]){/*STAR*/
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
					alreadyExecuted[1] = true;
				}
				else if(checkKeyStar(keyStar2)&& !alreadyExecuted[2]) {
					handler.clearEnemy();
					handler.addObject( new BossEnemy((Game.WIDTH/2-50),-70,ID.BossEnemy,handler)); //BOSS ENEMY
					hud.setStageTimer(71);
					alreadyExecuted[2] = true;
				}
				else if(hud.getStageTimer() >= 100 && !alreadyExecuted[3] && alreadyExecuted[2]){/*STAR*/
					alreadyExecuted[3] = true;
					handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
				}
			
			}//end of LVL 1
			else if (outterLevel == 2) { /*MISSION LEVEL 2*/
				
				
				
				if(hud.getStageTimer() == 10) {
					handler.addObject( new FastEnemy(0,30, ID.FastEnemy,handler)); //FAST ENEMY
				}
				else if(hud.getStageTimer() == 20){
					handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
				}
				else if(hud.getStageTimer() == 30){
					handler.addObject( new BasicEnemy(0,0, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 50 ){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				else if( checkKeyStar(keyStar1) && !alreadyExecuted[0]){
					handler.addObject( new SmartEnemy(Game.WIDTH - 16, 0,ID.SmartEnemy,handler));	//SMART ENEMY
					hud.setStageTimer(51);
					alreadyExecuted[0] = true;
				}
				else if(hud.getStageTimer() == 90 && alreadyExecuted[0]){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				else if(checkKeyStar(keyStar2)&& !alreadyExecuted[1]) {
					handler.clearEnemy();
					handler.addObject( new FastBossEnemy((Game.WIDTH/2-50),-70,ID.FastBossEnemy,handler)); //BOSS ENEMY
					hud.setStageTimer(91);
					alreadyExecuted[1] = true;
				}
				else if(hud.getStageTimer() >= 110 && !alreadyExecuted[2] && alreadyExecuted[1]){/*STAR*/
					alreadyExecuted[2] = true;
					handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
				}
				
			}//end of LVL2
			else if (outterLevel == 3) { /*MISSION LEVEL 3*/
				
				
				
				 if(hud.getStageTimer() == 10) {
					handler.addObject( new WormEnemy(1,1, ID.WormEnemy,handler)); //WORM ENEMY
				}
				 else if(hud.getStageTimer() == 35 ){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH-27),16,ID.Star,handler)); //STAR
				}
				 else if( checkKeyStar(keyStar1) && !alreadyExecuted[0]){
					 	handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
						hud.setStageTimer(36);
						alreadyExecuted[0] = true;
				}
				 else if(hud.getStageTimer() == 40 &&  alreadyExecuted[0]){ 
					 	handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
				}
				 else if(hud.getStageTimer() == 44 &&  alreadyExecuted[0]){ 
					 	handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
				}
				 else if(hud.getStageTimer() == 60 &&  alreadyExecuted[0]){ /*STAR*/
					 handler.addObject( new Star((Game.WIDTH-27),16,ID.Star,handler)); //STAR
				}
				 else if(checkKeyStar(keyStar2)&& !alreadyExecuted[1]) {
						handler.clearEnemy();
						handler.addObject( new SmartBossEnemy((Game.WIDTH/2-50),-70,ID.SmartBossEnemy,handler)); //BOSS ENEMY
						hud.setStageTimer(61);
						alreadyExecuted[1] = true;
				}
				 else if(hud.getStageTimer() >= 110 && !alreadyExecuted[2] && alreadyExecuted[1]){/*STAR*/
						alreadyExecuted[2] = true;
						handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
					}
				 
			}//end of LVL3
			else if (outterLevel == 4) { /*MISSION LEVEL 4*/
				
				
				if(hud.getStageTimer() == 30) {
					handler.addObject( new BasicEnemy(0,0, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 40){
					handler.addObject( new BasicEnemy(0,0, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 60 ){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				else if( checkKeyStar(keyStar1) && !alreadyExecuted[0]){
				 	handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
				 	handler.addObject( new SmartEnemy(Game.WIDTH - 16, 0,ID.SmartEnemy,handler));	//SMART ENEMY
					hud.setStageTimer(61);
					alreadyExecuted[0] = true;
				}
				else if(hud.getStageTimer() == 80 && alreadyExecuted[0] ){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				 else if(checkKeyStar(keyStar2)&& !alreadyExecuted[1]) {
						handler.clearEnemy();
						handler.addObject( new WormBossEnemy((Game.WIDTH/2-50),-70,ID.WormBossEnemy,handler)); //BOSS ENEMY
						hud.setStageTimer(81);
						alreadyExecuted[1] = true;
				}else if(hud.getStageTimer() >= 126 && !alreadyExecuted[2] && alreadyExecuted[1]){/*STAR*/
					alreadyExecuted[3] = true;
					handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
				}
				
				
				
			}//end of LVL 4
			
			else if(outterLevel == 5) { /*MISSION LVL 5*/
				
				if(hud.getStageTimer() == 1) {
					handler.addObject( new BasicEnemy(0,0, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 5) {
					handler.addObject( new BasicEnemy(0,0, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 10) {
					handler.addObject( new BasicEnemy(0,0, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 15) {
					handler.addObject( new BasicEnemy(0,0, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 34) {
				handler.addObject(new WarningEffect(0,handler));
				}
				else if(hud.getStageTimer() == 40) {
					handler.addObject( new SmartEnemy(Game.WIDTH, 0,ID.SmartEnemy,handler));	//SMART ENEMY
					handler.addObject( new SmartEnemy(Game.WIDTH, Game.HEIGHT/2 - 32,ID.SmartEnemy,handler));	//SMART ENEMY
					handler.addObject( new SmartEnemy(Game.WIDTH, Game.HEIGHT - 32,ID.SmartEnemy,handler));	//SMART ENEMY
					handler.addObject( new SmartEnemy(-32, 0,ID.SmartEnemy,handler));	//SMART ENEMY
					handler.addObject( new SmartEnemy(-32, Game.HEIGHT/2 - 32,ID.SmartEnemy,handler));	//SMART ENEMY
					handler.addObject( new SmartEnemy(-32, Game.HEIGHT - 32,ID.SmartEnemy,handler));	//SMART ENEMY
				}
				else if(hud.getStageTimer() == 42 ){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH-50),(Game.HEIGHT/2-32),ID.Star,handler)); //STAR
				}
				else if(checkKeyStar(keyStar1 && !alreadyExecuted[0]) ){ 
					alreadyExecuted[0] = true;
					handler.clearEnemy();
					hud.setStageTimer(50);
					handler.addObject( new VirusEnemy(0,30, ID.VirusEnemy,handler));
				}
				else if(hud.getStageTimer() == 55 && alreadyExecuted[0] ) {
					handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
				}
				else if(hud.getStageTimer() == 59 && alreadyExecuted[0] ) {
					handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
				}
				else if(hud.getStageTimer() == 63 && alreadyExecuted[0] ) {
					handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
				}
				else if(hud.getStageTimer() == 67 && alreadyExecuted[0] ) {
					handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
				}
				else if(hud.getStageTimer() == 71 && alreadyExecuted[0] ) {
					handler.addObject( new FastEnemy(0,0, ID.FastEnemy,handler)); //FAST ENEMY
				}
				else if(hud.getStageTimer() == 105 && alreadyExecuted[0]){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH-50),(Game.HEIGHT/2-32),ID.Star,handler)); //STAR
				}
				
				else if(checkKeyStar(keyStar2 && !alreadyExecuted[1])) {
					hud.setStageTimer(500);
					alreadyExecuted[1] = true;
					handler.clearEnemy();
					
				}
				else if( hud.getStageTimer() >= 500 && hud.getStageTimer() <= 530 & alreadyExecuted[1]) {
					handler.addObject( new HealthRain(r.nextInt(Game.WIDTH -7),-70,ID.HealthRain,handler)); //HEALTH RAIN DROP
					handler.addObject( new HealthRain(r.nextInt(Game.WIDTH -7),-70,ID.HealthRain,handler)); //HEALTH RAIN DROP
					if(hud.getStageTimer() == 525) {
						handler.addObject( new VirusBossEnemy((Game.WIDTH/2-50),-70,ID.VirusBossEnemy,handler)); //VIRUS BOSS
						
					}
				}
				else if(hud.getStageTimer() == 620 && alreadyExecuted[1]){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
				}
				
				/*PUT THE 3rd STAR TO APPEAR AT 200*/
			}//end of LVL 5
		
		
		}//stageTimer
	}//tick()
}
		
	
	


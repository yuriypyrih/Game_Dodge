import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Player player1;
	public int difficulty; // 0 = EASY, 1 = NORMAL, 2 = HARD
	private static int outterLevel = 1; // 0 = PLAY AGAIN
	
	private Boolean keyStar1 = false, keyStar2 = false, keyStar3 = false;
	private boolean health_pack_enabled = true;
	private boolean[] alreadyExecuted = new boolean[20];
	
	
	private int spawnTimer = 0;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		
		if(FileStore.conf[12] == '0') difficulty = 1; 
		else if(FileStore.conf[12] == '1') difficulty = 2; 
		
		for(int i = 0; i<20 ; i++) alreadyExecuted[i] = false;
		
	}
	
	public void WinKeyStar(int stars) {
		
		if(stars > Character.getNumericValue(FileStore.conf[outterLevel]) ) {
			FileStore.update(outterLevel,Character.forDigit(stars, 10) );
			System.out.println(" Updates with " + Character.forDigit(stars, 10) + " stars");
		}else {
			System.out.println(" Didn't updated with " + Character.forDigit(stars, 10) + " stars out of " + Character.getNumericValue(FileStore.conf[outterLevel]) );
		}
		
		switch (stars) {
		case 1 : keyStar1 = true;break;
		case 2 : keyStar2 = true;
		
		if(FileStore.conf[outterLevel + 1] == '-' && outterLevel != 11) {
			FileStore.update(outterLevel + 1, '0');
		}
		break;
		
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
		if(outterLevel < 11) { //11 is currently the highest level
			AudioPlayer.playMusic("music_lvl_" + Integer.toString(++outterLevel));
			startLevel(outterLevel);
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
		else if(this.outterLevel == 6) { 
			handler.addObject( new BouncerEnemy(1,1,ID.BouncerEnemy,handler));
		}
		else if(this.outterLevel == 7) { 
			handler.addObject( new ExplosiveEnemy(1,1,ID.ExplosiveEnemy,handler));
		}
		else if(this.outterLevel == 8) { 
			//handler.addObject( new VenomEnemy(1,1,ID.VenomEnemy,handler));
		}
		else if(this.outterLevel == 9) { 
			handler.addObject( new VoidEnemy(1,1,ID.VoidEnemy,handler));
		}
		else if(this.outterLevel == 10) { 
			handler.addObject( new ShadowEnemy(1,1,ID.ShadowEnemy,handler));
		}
		else if(this.outterLevel == 11) { 
			health_pack_enabled = false;
		}
		
	
	}
	
	
	
	public void reset() {
		keyStar1 = false;
		keyStar2 = false;
		keyStar3 = false;
		health_pack_enabled = true;
		SpookyStar.locked = false;
		for(int i = 0; i<20 ; i++) alreadyExecuted[i] = false;
	}
	
	

	public void tick() {
		try {
			hud.setStars(player1.getStars());
		}catch(NullPointerException e) {}
		
		spawnTimer++;	
		if(spawnTimer>=20) {
			spawnTimer = 0;
			hud.setStageTimer(hud.getStageTimer() + 1);
			
			/*HEALRS!*/
			if(hud.getStageTimer() % 12 == 0 && health_pack_enabled){
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
				else if(hud.getStageTimer() >= 120 && !alreadyExecuted[2] && alreadyExecuted[1]){/*STAR*/
					alreadyExecuted[2] = true;
					handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
				}
				
			}//end of LVL2
			else if (outterLevel == 3) { /*MISSION LEVEL 3*/
				
				
				
				 if(hud.getStageTimer() == 13) {
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
					alreadyExecuted[2] = true;
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
				handler.addObject(new WarningEffect(0,0,0,handler));
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
				
				
			}//end of LVL 5
			
			else if(outterLevel == 6) { /*MISSION LVL 6*/
				
				if(hud.getStageTimer() == 12) {
					handler.addObject( new WormEnemy(1,1, ID.WormEnemy,handler)); //WORM ENEMY
				}
				else if(hud.getStageTimer() == 35) {
					handler.addObject( new Star((Game.WIDTH-40),(Game.HEIGHT-60),ID.Star,handler)); //STAR
				}
				else if( checkKeyStar(keyStar1) && !alreadyExecuted[0]){
				 	handler.addObject( new BasicEnemy(0, 0,ID.BasicEnemy,handler));	//BASIC ENEMY
					hud.setStageTimer(36);
					alreadyExecuted[0] = true;
				}
				else if(hud.getStageTimer() == 38 && alreadyExecuted[0]) {
					handler.addObject( new FastEnemy(1, 1, ID.FastEnemy,handler)); //FAST ENEMY
				}
				else if(hud.getStageTimer() == 40  && alreadyExecuted[0]) {
					handler.addObject( new BasicEnemy(0, 0,ID.BasicEnemy,handler));	//BASIC ENEMY
					
				}
				else if(hud.getStageTimer() == 120 && !alreadyExecuted[1] && alreadyExecuted[0]){
					handler.addObject( new Star((Game.WIDTH-40),(Game.HEIGHT-60),ID.Star,handler)); //STAR
					alreadyExecuted[1] = true;
				}
				else if( checkKeyStar(keyStar2) && !alreadyExecuted[2]) { //HYBRID BOSS ENEMY SCOUNT-FAST-SMART
					handler.clearEnemy();
					handler.addObject( new HybridBossEnemy((Game.WIDTH/2-50),-70,ID.HybridBossEnemy,handler));
					hud.setStageTimer(121);
					alreadyExecuted[2] = true;
				}
				else if( hud.getStageTimer() == 180  && alreadyExecuted[2]) { 
					handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
				}
			}//end of mission 6
			
			else if(outterLevel ==7) { /*MISSION LVL 7*/
				
				if(hud.getStageTimer() == 14) {
					//handler.addObject( new BouncerBossEnemy((Game.WIDTH/2-50),-70, ID.BouncerBossEnemy,handler)); 
					handler.addObject( new BasicEnemy(20,1, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 20) {
					handler.addObject( new BasicEnemy(40,1, ID.BasicEnemy,handler)); //BASIC ENEMY
				}
				else if(hud.getStageTimer() == 40) {
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				else if( checkKeyStar(keyStar1) && !alreadyExecuted[0]){
					handler.addObject( new VirusEnemy(60,1, ID.VirusEnemy,handler)); //VIRUS ENEMY
					handler.addObject( new FastEnemy(20,1, ID.FastEnemy,handler)); //FAST ENEMY
					hud.setStageTimer(41);
					alreadyExecuted[0] = true;
				}
				else if(hud.getStageTimer() == 80 && alreadyExecuted[0]) {
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				else if( checkKeyStar(keyStar2) && !alreadyExecuted[1]) { //BOUNCER BOSS
					handler.clearEnemy();
					handler.addObject( new BouncerBossEnemy((Game.WIDTH/2-50),-70,ID.BouncerBossEnemy,handler));
					hud.setStageTimer(81);
					alreadyExecuted[1] = true;
				}
				else if( hud.getStageTimer() == 132  && alreadyExecuted[1]) { 
					handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
				}
			
				
			}//end of lvl 7
			
			else if(outterLevel == 8) { /*MISSION LVL 8*/
				
				
				if(hud.getStageTimer()== 5) {
					handler.addObject( new VenomEnemy(1,1,ID.VenomEnemy,handler)); // VENOM ENEMY
				}
				if(hud.getStageTimer()== 15) {
					handler.addObject( new SmartEnemy(Game.WIDTH - 16, 0,ID.SmartEnemy,handler));	//SMART ENEMY
				}
				else if(hud.getStageTimer()== 30) {
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				else if(checkKeyStar(keyStar1) && !alreadyExecuted[0]) {
					handler.addObject( new ExplosiveEnemy(20,1,ID.ExplosiveEnemy,handler)); //EXPLOSIVE ENEMY
					alreadyExecuted[0] = true;
					hud.setStageTimer(31);
				}
				else if(hud.getStageTimer()==35 && alreadyExecuted[0]) {
					handler.addObject( new BouncerEnemy(1,1,ID.BouncerEnemy,handler)); //BOUNCER ENEMY
				
				}
				else if(hud.getStageTimer()==45 && alreadyExecuted[0]) {
					handler.addObject( new ExplosiveEnemy(40,1,ID.ExplosiveEnemy,handler)); //EXPLOSIVE ENEMY
				
				}
				else if(hud.getStageTimer()==60 && alreadyExecuted[0]) {
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				
				}
				else if(checkKeyStar(keyStar2) && !alreadyExecuted[1]) {
					handler.clearEnemy();
					handler.addObject( new ExplosiveBossEnemy((Game.WIDTH/2-50),-70,ID.ExplosiveBossEnemy,handler));
					alreadyExecuted[1] = true;
					hud.setStageTimer(61);
				}
				else if(hud.getStageTimer()==100 && alreadyExecuted[1]) {
					handler.addObject( new Star((Game.WIDTH/2-8),415,ID.Star,handler)); //STAR
				
				}
			}//end of lvl 8
			else if(outterLevel == 9) { /*MISSION LVL 9*/
				 if(hud.getStageTimer() == 25 ){ /*STAR*/
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				 else if(checkKeyStar(keyStar1) && !alreadyExecuted[0]) {
					 handler.addObject( new VenomEnemy(1,1,ID.VenomEnemy,handler)); //VENOM ENEMY
					 alreadyExecuted[0] = true;
					 hud.setStageTimer(26);
				 }
				 else if(hud.getStageTimer() == 30 && alreadyExecuted[0]) {
					 handler.addObject( new VirusEnemy(20,1,ID.VirusEnemy,handler)); //VIRUS ENEMY
				 }
				 else if(hud.getStageTimer() == 80 && alreadyExecuted[0]) {
					 handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				 }
				 else if(checkKeyStar(keyStar2) && !alreadyExecuted[1]) {
					 handler.clearEnemy();
					 handler.addObject( new VenomBossEnemy((Game.WIDTH/2-50),-70,ID.VenomBossEnemy,handler)); //VENOM ENEMY
					 alreadyExecuted[1] = true;
					 hud.setStageTimer(81);
				 }
				 else if(hud.getStageTimer() == 200 && alreadyExecuted[1]) {
					 handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
				 }
			}//end of level 9
			
			else if(outterLevel == 10) { /*MISSION LVL 10*/
				
				if(hud.getStageTimer() == 5) {
					AudioPlayer.getSound("sound_hide_seek").play(1f,0.55f);
					handler.addObject( new ShadowEnemy(20,1,ID.ShadowEnemy,handler));
				}
				else  if(hud.getStageTimer() == 25 ){ 
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}
				else if(checkKeyStar(keyStar1) && !alreadyExecuted[0]) {
					handler.addObject( new ExplosiveEnemy(40,1,ID.ExplosiveEnemy,handler)); //EXPLOSIVE ENEMY
					alreadyExecuted[0] = true;
					hud.setStageTimer(26);
					
				}
				else  if(hud.getStageTimer() == 30 && alreadyExecuted[0]){ 
					handler.addObject( new VenomEnemy(60,1,ID.VenomEnemy,handler)); //VENOM ENEMY
				}
				else  if(hud.getStageTimer() == 35 && alreadyExecuted[0]){ 
					handler.addObject( new WormEnemy(1, 1,ID.WormEnemy,handler));	//WORM ENEMY
				}
				else  if(hud.getStageTimer() == 100 && alreadyExecuted[0]){ 
					handler.addObject( new Star((Game.WIDTH/2-8),(Game.HEIGHT/2-8),ID.Star,handler)); //STAR
				}	
				
				else if(checkKeyStar(keyStar2) && !alreadyExecuted[1]) {
					handler.clearEnemy();
					handler.addObject( new VoidBossEnemy((Game.WIDTH/2-20),-10,ID.VoidBossEnemy,handler)); //VOID BOSS
					alreadyExecuted[1] = true;
					hud.setStageTimer(101);
				}
				 else if(hud.getStageTimer() == 200 && alreadyExecuted[1]) {
					 handler.addObject( new Star((Game.WIDTH/2-8),50,ID.Star,handler)); //STAR
				 }
			}//end of level 10
			
			else if(outterLevel == 11) { /*MISSION LVL 11*/
				
				if(hud.getStageTimer() == 5) {
					AudioPlayer.getSound("sound_lonely_girl").play(1f,0.55f);
					handler.addObject( new ShadowEnemy(1,1,ID.ShadowEnemy,handler));//SHADOW ENEMY
				}
				
				else if(hud.getStageTimer() == 10) {
					
					handler.addObject( new ShadowEnemy(20,1,ID.ShadowEnemy,handler));//SHADOW ENEMY
				}
				else if(hud.getStageTimer() == 15) {
					
					handler.addObject( new ShadowEnemy(40,1,ID.ShadowEnemy,handler));//SHADOW ENEMY
				}
				else if(hud.getStageTimer() == 20) {
					
					handler.addObject( new ShadowEnemy(60,1,ID.ShadowEnemy,handler));//SHADOW ENEMY
				}
				else if(hud.getStageTimer() == 85) {
					
					 handler.addObject( new Star((Game.WIDTH/2-8)+50,40,ID.Star,handler)); //STAR
				}
				else if(checkKeyStar(keyStar1) && !alreadyExecuted[0]) {
	
					handler.clearEnemy();
					AudioPlayer.getSound("sound_thunder").play(1f,0.35f);
					
					alreadyExecuted[0] = true;
					hud.setStageTimer(86);
					
				}
				else if(hud.getStageTimer() == 88 && alreadyExecuted[0]) {
					
					handler.addObject(new WarningEffect(0,0,5,handler));	//SCARY FACE JUMPSCARE!
				}
				
				else if(hud.getStageTimer() == 95 && alreadyExecuted[0]) {
					AudioPlayer.getSound("sound_gona_die").play(1f,0.5f);
					handler.addObject( new ShadowEnemy(1,1,ID.ShadowEnemy,handler));//SHADOW ENEMY
				}
				else if(hud.getStageTimer() == 100 && alreadyExecuted[0]) {
					
					handler.addObject( new ShadowEnemy(20,1,ID.ShadowEnemy,handler));//SHADOW ENEMY
				}
				else if(hud.getStageTimer() == 105 && alreadyExecuted[0]) {
					
					handler.addObject( new ShadowEnemy(40,1,ID.ShadowEnemy,handler));//SHADOW ENEMY
				}
				else if(hud.getStageTimer() == 110 && alreadyExecuted[0]) {
					
					handler.addObject( new ShadowEnemy(60,1,ID.ShadowEnemy,handler));//SHADOW ENEMY
				}
				else if(hud.getStageTimer() == 115 && alreadyExecuted[0]) {
					
					handler.addObject( new SmartEnemy(Game.WIDTH - 16, 0,ID.SmartEnemy,handler));	//SMART ENEMY
				}
				else if(hud.getStageTimer() == 160 && alreadyExecuted[0]) {
					
					 handler.addObject( new Star((Game.WIDTH/2-8)+50,40,ID.Star,handler)); //STAR
					 System.out.println(keyStar1 + " " + keyStar2 + " " + keyStar3);
				}
				else if(checkKeyStar(keyStar2) && !alreadyExecuted[1]) {
					
					System.out.println("else if works fine");
					handler.clearEnemy();
					AudioPlayer.getSound("sound_woman_laugh").play(1f,0.5f);
					alreadyExecuted[1] = true;
					hud.setStageTimer(161);
				}
				else if(hud.getStageTimer() == 170 && alreadyExecuted[1]) {
					
					
					handler.addObject( new SpookyEnemy(Game.WIDTH - 16, 0,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(Game.WIDTH - 16, 112,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(Game.WIDTH - 16, 224,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(Game.WIDTH - 16, 336,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(Game.WIDTH - 16, 448,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					
					handler.addObject( new SpookyEnemy(-23, 0,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(-23, 112,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(-23, 224,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(-23, 336,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(-23, 448,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					
					handler.addObject( new SpookyEnemy(	116, -16,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(	216, -16,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy( 316, -16,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy( 416, -16,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(	516, -16,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					
					handler.addObject( new SpookyEnemy(	116, 450,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(	216, 450,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy( 316, 450,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy( 416, 450,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
					handler.addObject( new SpookyEnemy(	516, 460,ID.SpookyEnemy,handler));	//SPOOKY ENEMY
				}
				
				 
				
				if(hud.getStageTimer() == 185 && alreadyExecuted[1]) {
					 AudioPlayer.getSound("sound_woman_laugh_1").play(1f,0.6f);
					 
				 }
				else if(hud.getStageTimer() == 189 && alreadyExecuted[1]) {
					handler.addObject( new ShadowBossEnemy((Game.WIDTH/2-115),-200,ID.ShadowBossEnemy,handler)); //VOID BOSS
					
					 
				 }
				else if(hud.getStageTimer() == 209 && alreadyExecuted[1]) {
					handler.addObject( new SpookyStar((Game.WIDTH/2-8)+50,40,ID.SpookyStar,handler)); //STAR
					
				 }
				 
				
				
				
			
			}//end of level 11
			
		
		}//stageTimer
	}//tick()
}
		
	
	


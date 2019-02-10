package engine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import entities.Trail;

public class Player extends GameObject{
	
	private TaskManager task_manager = new TaskManager();
	private Handler handler;
	private Spawn spawner;
	private int stars = 0 ;
	public static boolean poisoned = false;
	public static boolean immune = false; /*CHANGE THIS TO TRUE WHENEVER YOU WANT TO TEST A LEVEL*/


	//Contructor
	public Player(float  x, float y, ID id, Handler handler, Spawn spawner) {
		super(x, y, id);
		this.handler = handler;
		this.spawner = spawner;
		
	}
	
	
	
	public int getStars() {
		return stars;
	}
	
	public void setStart(int stars) {
		this.stars = stars;
	}
	
	
	public Rectangle getBounds() {
		//that's what the hitbox is, not the actuall shape of the sprite
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	
	
	/*Timer timer = new Timer();
	TimerTask task_immune_after_dmg = new TimerTask() {
		public void run() {
			immune = false;
			System.out.println("Player damaged");
		}
	};
	
	public void player_damaged() {
		immune = true;
		timer.schedule(task_immune_after_dmg, 1000);
	}*/
	

	public void tick() {
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.2f, handler));
		//Behavior
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH - 35);
		y = Game.clamp((int)y, 0, Game.HEIGHT - 60);
		
		
		
		 collision(); //to check if there is any collision happening
		
		if(poisoned) {
			HUD.HEALTH -= 0.08f;
		}
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy 
					|| tempObject.getId() == ID.WormEnemy|| tempObject.getId() == ID.VirusEnemy || tempObject.getId() == ID.BouncerEnemy
					|| tempObject.getId() == ID.BossEnemy || tempObject.getId() == ID.WormBossEnemy
					|| tempObject.getId() == ID.ExplosiveEnemy || tempObject.getId() == ID.ExplosiveBossEnemy
					|| tempObject.getId() == ID.SmartBossEnemy|| tempObject.getId() == ID.FastBossEnemy
					|| tempObject.getId() == ID.VirusBossEnemy || tempObject.getId() == ID.BouncerBossEnemy
					|| tempObject.getId() == ID.VenomBossEnemy || tempObject.getId() == ID.VenomEnemy
					|| tempObject.getId() == ID.VoidEnemy || tempObject.getId() == ID.VoidBossEnemy 
					|| tempObject.getId() == ID.ShadowEnemy || tempObject.getId() == ID.ShadowBossEnemy) {//tempObject is now basic enemy
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					if(immune != true) {
						 if(spawner.difficulty == 1) {
								HUD.HEALTH -= 8;
							}
							else if(spawner.difficulty == 2) {
								HUD.HEALTH -= 20;
							}
							
						 task_manager.startTask_player_damaged();
					}
			
				}
			}else if(tempObject.getId() == ID.BossBullet || tempObject.getId() == ID.FastBossBullet 
					|| tempObject.getId() == ID.SmartBossBullet || tempObject.getId() == ID.VirusBullet 
					|| tempObject.getId() == ID.WormBossBullet|| tempObject.getId() == ID.BouncerBossBullet
					|| tempObject.getId() == ID.ExplosiveBossBullet || tempObject.getId() == ID.VoidBossBullet
					|| tempObject.getId() == ID.ShadowBossBullet) {	//tempObject is now BULLET
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					 if(spawner.difficulty == 1) {
						HUD.HEALTH -= 20;
					}
					else if(spawner.difficulty == 2) {
						HUD.HEALTH -= 25;
					}
	
					handler.removeObject(tempObject);
				}
			}
				else if(tempObject.getId() == ID.DeathTrap) {	//DEATH TRAP
					if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
						
						AudioPlayer.getSound("sound_deathtrap_collect").play(1f,0.6f);
						
						//collision
						if(spawner.difficulty == 0) {
							HUD.HEALTH -= 30;
						}else if(spawner.difficulty == 1) {
							HUD.HEALTH -= 40;
						}
						else if(spawner.difficulty == 2) {
							HUD.HEALTH -= 50;
						}
		
						handler.removeObject(tempObject);
					}
					
				}
				else if(tempObject.getId() == ID.ExplosiveBoom) {	//BOOM
					if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
						//collision
						 if(spawner.difficulty == 1) {
							HUD.HEALTH -= 5;
						}
						else if(spawner.difficulty == 2) {
							HUD.HEALTH -= 7;
						}
		
					}
				}
				else if(tempObject.getId() == ID.VenomRain) {	//tempObject is now basic enemy
					if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
						//collision
						 if(spawner.difficulty == 1) {
							HUD.HEALTH -= 5;
						}else if(spawner.difficulty == 2) {
							HUD.HEALTH -= 10;
						}
						
						handler.removeObject(tempObject);
					}
					
				}
			
				
				 if(tempObject.getId() == ID.Healer) {	//tempObject is now basic enemy
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					if(spawner.difficulty == 0) {
						HUD.HEALTH += 40;
					}else if(spawner.difficulty == 1) {
						HUD.HEALTH += 12;
					}else if(spawner.difficulty == 2) {
						HUD.HEALTH += 10;
					}
					
					handler.removeObject(tempObject);
					AudioPlayer.getSound("sound_collect").play(2,0.2f);
				}
				
			}else if(tempObject.getId() == ID.HealthRain) {	//tempObject is now basic enemy
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					 if(spawner.difficulty == 1) {
						HUD.HEALTH += 5;
					}else if(spawner.difficulty == 2) {
						HUD.HEALTH += 3;
					}
					
					handler.removeObject(tempObject);
				}
				
			}
			
			else if(tempObject.getId() == ID.Star) {	//tempObject is now basic enemy
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					stars++;
					spawner.WinKeyStar(stars);
					handler.removeObject(tempObject);
					AudioPlayer.getSound("sound_collect").play(2,0.2f);
					
				}
			}
			
			if(tempObject.getId() == ID.VenomRain || tempObject.getId() == ID.VenomEnemy
				|| tempObject.getId() == ID.VenomBossEnemy|| tempObject.getId() == ID.VenomTrail) {
				if(getBounds().intersects(tempObject.getBounds())) {
					
					if(!poisoned) {
						poisoned = true;
						AudioPlayer.getSound("venom_activation").play(1,1);
						handler.addObject(new WarningEffect(0,0,4,handler));
					}
				}
			}//end of poison
		}//end of for loop()
		
	}//end of collision();
	
	public void render(Graphics g) {
		//Appearance
		
		//g.setColor(Color.WHITE);
		//g.fillRect((int)x,(int) y, 32, 32);
	}
	
	
	
}//end of Player class

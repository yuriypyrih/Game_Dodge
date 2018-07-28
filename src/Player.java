import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	private Handler handler;
	private Spawn spawner;
	private int stars = 0 ;

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

	public void tick() {
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.2f, handler));
		//Behavior
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH - 35);
		y = Game.clamp((int)y, 0, Game.HEIGHT - 60);
		
		
		
		collision(); //to check if there is any collision happening
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy 
					|| tempObject.getId() == ID.WormEnemy|| tempObject.getId() == ID.VirusEnemy
					|| tempObject.getId() == ID.BossEnemy || tempObject.getId() == ID.WormBossEnemy
					|| tempObject.getId() == ID.SmartBossEnemy|| tempObject.getId() == ID.FastBossEnemy
					|| tempObject.getId() == ID.VirusBossEnemy ) {//tempObject is now basic enemy
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					if(spawner.difficulty == 0) {
						HUD.HEALTH -= 4;
					}else if(spawner.difficulty == 1) {
						HUD.HEALTH -= 8;
					}
					else if(spawner.difficulty == 2) {
						HUD.HEALTH -= 12;
					}
					
				}
			}else if(tempObject.getId() == ID.BossBullet || tempObject.getId() == ID.FastBossBullet || tempObject.getId() == ID.SmartBossBullet
					|| tempObject.getId() == ID.VirusBullet || tempObject.getId() == ID.WormBossBullet) {	//tempObject is now BULLET
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					if(spawner.difficulty == 0) {
						HUD.HEALTH -= 10;
					}else if(spawner.difficulty == 1) {
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
			}else if(tempObject.getId() == ID.Healer) {	//tempObject is now basic enemy
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
				}
				
			}else if(tempObject.getId() == ID.HealthRain) {	//tempObject is now basic enemy
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					if(spawner.difficulty == 0) {
						HUD.HEALTH += 5;
					}else if(spawner.difficulty == 1) {
						HUD.HEALTH += 4;
					}else if(spawner.difficulty == 2) {
						HUD.HEALTH += 3;
					}
					
					handler.removeObject(tempObject);
				}
				
			}else if(tempObject.getId() == ID.Star) {	//tempObject is now basic enemy
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					stars++;
					spawner.WinKeyStar(stars);
					handler.removeObject(tempObject);
					
				}
			}
		}
	}
	
	public void render(Graphics g) {
		//Appearance
		
		g.setColor(Color.WHITE);
		g.fillRect((int)x,(int) y, 32, 32);
	}
	
	
	
}//end of Player class

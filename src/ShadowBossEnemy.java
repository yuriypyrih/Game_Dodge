import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class ShadowBossEnemy extends GameObject{
	
	private Handler handler;
	private float timer;
	private int scoreKeep = 0;
	private double angle = 90;
	private double speed = 0.015;
	private int bulletRate = 0;
	private boolean[] executedRound = new boolean[10];
	
	private Image boss_icon_0 = new  ImageIcon("res/ghost_boss_0.png").getImage();
	private Image boss_icon_1 = new  ImageIcon("res/ghost_boss_1.png").getImage();
	private Image boss_icon = boss_icon_0;
	
	public ShadowBossEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			timer = -500;
		
			
			for(int i = 0; i < 10; i++) executedRound[i] = false;
			
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x+60,(int)y+10, 100, 120);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		
		
		scoreKeep++;
		
		y = (float) ((150+timer) * Math.cos(angle)) + (150+timer);
		angle+= speed;
		
		
		if(timer>= 0) {
		
			timer = 0;
		}else timer++;
		
		
		if(SpookyStar.locked && !executedRound[0]) {
		
			scoreKeep = 0;
			executedRound[0] = true;
			
		}
		else if(scoreKeep >= 300 && !executedRound[1] && executedRound[0]) {
			handler.addObject(new ShadowBossBullet(x+170,y+90,ID.ShadowBossBullet,5,5,handler));
			handler.addObject(new ShadowBossBullet(x+40,y+90,ID.ShadowBossBullet,-5,5,handler));
			

			executedRound[1] = true;
		}
		else if(scoreKeep >= 350 && !executedRound[2] && executedRound[1]) {
			handler.addObject(new ShadowBossBullet(x+170,y+90,ID.ShadowBossBullet,5,5,handler));
			handler.addObject(new ShadowBossBullet(x+40,y+90,ID.ShadowBossBullet,-5,5,handler));
			

			executedRound[2] = true;
		}
		else if(scoreKeep >= 1000 && !executedRound[3] && executedRound[2]) {
			AudioPlayer.getSound("sound_realise").play(0.75f,0.4f);

			executedRound[3] = true;
		}
		else if(scoreKeep >= 1500 && !executedRound[4] && executedRound[3]) {
			AudioPlayer.getSound("sound_final_form").play(0.75f,0.4f);

			executedRound[4] = true;
		}
		else if(scoreKeep >= 1800 && !executedRound[5] && executedRound[4]) {
			AudioPlayer.getSound("sound_thunder").play(1f,0.35f);
			executedRound[5] = true;
		}
		
		else if(scoreKeep >= 1840 && !executedRound[6] && executedRound[5]) {
			handler.addObject(new WarningEffect(0,0,7,handler));
			boss_icon = boss_icon_1;
			speed *= 2;
			executedRound[6] = true;
		}
		else if(scoreKeep >= 2050  && executedRound[6]) {
			bulletRate++;
			if(bulletRate > 150) {
				handler.addObject(new ShadowBossBullet(x+170,y+90,ID.ShadowBossBullet,5,5,handler));
				handler.addObject(new ShadowBossBullet(x+40,y+90,ID.ShadowBossBullet,-5,5,handler));
				bulletRate = 0;
			}
			if(scoreKeep >= 2350 && !executedRound[7] ) {
				
				SpookyStar.getUnlocked();
			
				executedRound[7] = true;
			}
		}
		
		
		
		
		
	
		
		
		//Collision
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //our object is 16 px tall, and our center of mass is left-down at the bottom
		//if(x <= 0 || x >= (Game.WIDTH - 64)) velX *= -1;
		
	 
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		
		g.drawImage(boss_icon,(int)x,(int)y,null);
		
		//g.setColor(Color.yellow);
		//g.drawRect((int)x+60,(int)y+10, 100, 120);
	}
}

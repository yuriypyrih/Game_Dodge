import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class VenomBossEnemy extends GameObject{
	

	private Handler handler;
	private Random r = new Random();
	private float timer;
	private int shakeTimer = 0, shakeHz = 0, venomRainHz = 0;
	private boolean[] rattle = new boolean[2];
	private boolean releaseVirus = false;
	private boolean dropRain = false;
	
	
	private Image VenomBoss_icon = new  ImageIcon("res/spider_icon.jpg").getImage();
	
	
	public VenomBossEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			timer = 160;
			
			rattle[0] = true;
			rattle[1] = true;
			
			
			velY = 0.6f;
			velX = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}
	
	
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		if(dropRain) {
			venomRainHz++;
			if(venomRainHz>6) {
				handler.addObject( new VenomRain(r.nextInt(Game.WIDTH -7),-70,ID.VenomRain,handler)); //VENOM RAIN DROP
				venomRainHz = 0;
			}
		}
		
		
		
		if(timer<= 0) {
			velY = 0;
			shakeTimer++;
			if(velX==0) { 
				velX = 5;
			}
			if(shakeTimer>=120 && shakeTimer <= 270) {
				handler.addObject( new HealthRain(r.nextInt(Game.WIDTH -7),-70,ID.HealthRain,handler)); //HEALTH RAIN DROP
				if(rattle[0]) {
					AudioPlayer.getSound("sound_snake").play(1f,1f);
					rattle[0] = false;
				}
				shakeHz++;
				if(shakeHz>=5) {
					velX *= -1;
					shakeHz = 0;
				}
			}else if(shakeTimer>=505 && shakeTimer <= 655) {
				if(rattle[1]) {
					AudioPlayer.getSound("sound_snake").play(1f,1f);
					rattle[1] = false;
					rattle[0] = true;
					dropRain = true;
				}
				shakeHz++;
				if(shakeHz>=5) {
					velX *= -1;
					shakeHz = 0;
				}
			}
			else if(shakeTimer>=1350 && shakeTimer <= 1500) {
				
				handler.addObject( new HealthRain(r.nextInt(Game.WIDTH -7),-70,ID.HealthRain,handler)); //HEALTH RAIN DROP
				if(rattle[0]) {
					AudioPlayer.getSound("sound_snake").play(1f,1f);
					rattle[0] = false;
					rattle[1] = true;
				}
				shakeHz++;
				if(shakeHz>=5) {
					velX *= -1;
					shakeHz = 0;
				}
			}
			else if(shakeTimer>=1725 && shakeTimer <= 1875) {
				
				
				if(rattle[1]) {
					AudioPlayer.getSound("sound_snake").play(1f,1f);
					
					rattle[1] = false;
					
				}
				if(shakeTimer>1850 && !releaseVirus) {
					handler.addObject( new VirusEnemy(50,1, ID.VirusEnemy,handler)); //VIRUS ENEMY
					releaseVirus = true;
				}
				shakeHz++;
				if(shakeHz>=5) {
					velX *= -1;
					shakeHz = 0;
				}
			}
			
			
		}else timer--;
		
		
		
		
	
		
		
		//Collision
		
			if(x <= 0 || x >= (Game.WIDTH - 64)) velX *= -1;
		
		
	 // handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.white);// OUTSIDE COLOR
		g.fillRect((int)x, (int)y, 64, 64);
		
		g.setColor(new Color (20, 0, 20));//INSIDE COLOR
		g.fillRect((int)x+4, (int)y, 64-8, 64);
		g.fillRect((int)x, (int)y+4, 64, 64-8);
		
		
		g.drawImage(VenomBoss_icon,(int)x,(int)y,null);
	}
}

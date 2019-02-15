package entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import engine.AudioPlayer;
import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;
import engine.Player;
import engine.WarningEffect;

public class ChameleonBossEnemy extends GameObject{
	
	
	private Image boss_icon_1 = new  ImageIcon("res/scout_boss_icon.png").getImage();
	private Image boss_icon_3_center = new  ImageIcon("res/smart_boss_icon_center.png").getImage();
	private Image boss_icon_3_left = new  ImageIcon("res/smart_boss_icon_left.png").getImage();
	private Image boss_icon_3_right = new  ImageIcon("res/smart_boss_icon_right.png").getImage();
	private Image boss_icon_4 = new ImageIcon("res/worm_boss_icon.png").getImage();
	private Image boss_icon_5 = new  ImageIcon("res/virus_boss_icon_100px.png").getImage();
	private Image boss_icon_6 = new ImageIcon("res/slime_boss_icon.png").getImage();
	private Image boss_icon_7 = new  ImageIcon("res/explosive_boss_icon.png").getImage();
	private Image boss_icon_8 = new  ImageIcon("res/spider_icon.jpg").getImage();
	private Image boss_icon_9 = new ImageIcon("res/black_hole_120.png").getImage();
	
	private Handler handler;
	private GameObject player;
	private int chameleon_timer = -100; // preset so it will wait before changing
	private int changing_delay = 0; // This is for clarity of the sprite
	private int bulletTimer = -10;
	private float timer = 160;
	
	private boolean dropRain = false;
	private int shakeTimer = 0, shakeHz = 0, venomRainHz = 0;
	private boolean rattle = true;
	
	private double angle = 270;
	private float x_prev;
	
	private int width = 64 , height = 64;
	
	private Random r = new Random();
	private int chameleon_form = 0;
	private int chameleon_prev_form = 0;
	private boolean just_changed_form = false;
	
	private Color chameleon_color = Color.WHITE;
	private Color light_pink = new Color(255,153,255);
	private Color light_blue = new Color(128, 212, 255);
	private Color virus_purple = new Color(153, 0, 204);
	private Color Brown = new Color(201,112,48);
	private Color void_color = new Color(77, 77, 255);
	
	public ChameleonBossEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			//code for getting the position of player
			for(int i=0 ; i<handler.getObjectList().size(); i++) {
				if(handler.getObjectList().get(i).getId() == ID.Player) player = handler.getObjectList().get(i);
			}
			
			velY = 0.6f;
			velX = 0;
	}
	

	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
	}
	
	private void check_For_Health_Packs() {
		for(int i = 0; i < handler.getObjectList().size(); i++) {
			GameObject tempObject = handler.getObjectList().get(i);
			
			if(tempObject.getId() == ID.Healer) {
				handler.addObject( new VirusBullet(x,y,ID.VirusBullet,handler));
				return;
			}
		}
	}
	
	//This methods fixes any positional problems upon transofrmation of te chameleon
	private void debug_position() {
		
		if( x < 0)  x = 10;
		if( x > Game.WIDTH - width) x =  Game.WIDTH - (width+10);
		 y = 30;
		//if( y > Game.HEIGHT - width*2) y = Game.HEIGHT - width*2;
	}
	private void void_collision() {
		for(int i = 0; i < handler.getObjectList().size(); i++) {
			
			GameObject tempObject = handler.getObjectList().get(i);
			
			if(tempObject.getId() == ID.VoidBossBullet) {
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					
						if(Player.poisoned) { // just in case you needed that.. *winky face*
							handler.addObject( new HealthRain(r.nextInt(Game.WIDTH -7),-70,ID.HealthRain,handler)); //HEALTH RAIN DROP
							handler.addObject( new HealthRain(r.nextInt(Game.WIDTH -7),-70,ID.HealthRain,handler)); //HEALTH RAIN DROP
						}
					
					handler.removeObject(tempObject);
				}
			}
		}
	}
	
	
	
	public void tick() {
		
		changing_delay ++;
		chameleon_timer++;
		bulletTimer++;
		
		if((chameleon_timer >= 200 && velX < 0) && 
				((chameleon_form == 6 )||(x > 420 && x < 560 && velY == 0))) {

			
			//debug_position();
			chameleon_form = r.nextInt(9) + 1;
			if(chameleon_form != chameleon_prev_form) {
				handler.addObject(new ChameleonChanging(x,y,ID.ChameleonChanging,this, handler));
				changing_delay = 0;
				chameleon_prev_form = chameleon_form;
				bulletTimer = -30;
				just_changed_form = true;
				dropRain = false;
				shakeTimer = 0;
				rattle = true;
				debug_position();
				
			}
			
			chameleon_timer = 0;
			System.out.println("Chameleon form " + chameleon_form);
		}
		
			
		//that's HOW OUR ENEMY BEHAVES
		if(chameleon_form == 0) {
			if(timer<= 0) {
				velY = 0;
				if(velX==0) { 
					velX = 5;
				}
			}else timer--;
			
			x+=velX;
			y+=velY;
				
		//Collision
		//if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
		
		} 
		else if(chameleon_form == 1) { // Basic Boss form
			
			
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 0) velY = 0;
			
			x+=velX;
			y+=velY;
			
			if(bulletTimer>= 10) {
				bulletTimer = 0;
				handler.addObject(new BossBullet(x+30,y+60,ID.BossBullet,handler));
			}
			
			//Collision
			//if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
		}
		
		else if(chameleon_form == 2) { //Fast Boss form
			
			if( velY != 0) velY = 0;
			
			
			handler.addObject(new Trail(x, y, ID.Trail, light_blue, 64, 64, 0.1f, handler));
			
			x+=velX;
			y+=velY;
			
			if(bulletTimer>=50) {
				bulletTimer = 0;
				handler.addObject(new FastBossBullet(x+30,y+60,ID.FastBossBullet,handler));
			}
			
			//Collision
			if(x <= 0 && velX < 0) {
				velX *= -1;
				// INCREASING SPEED
				if(velX < 25 ) velX += 5;
			}
			if( x >= (Game.WIDTH - 64) && velX > 0) {
					velX *= -1;
					// INCREASING SPEED
				if(velX > -25 ) velX -= 5;			
			}
		}
		
		else if(chameleon_form == 3) { //Smart Boss form

			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 0) velY = 0;
			
			x+=velX;
			y+=velY;
			
			if(bulletTimer>=35) {
				bulletTimer = 0;
				handler.addObject(new SmartBossBullet(x+30,y+70,ID.SmartBossBullet,player,handler));
			}
			
			//Collision
			//if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
		}
		else if(chameleon_form == 4) { //Worm Boss form
			 if( y > 70 ) handler.addObject(new Trail(x, y, ID.Trail, light_pink, 64, 64, 0.08f, handler));
			
			//if( velX != 5 && velX != -5 && velX != 0 && velX != 12) {
			//	if( velX < 0) velX = -5;
			//	else velX = 5;	
			//}
			
			
			if(bulletTimer>=5 && velX == -5) {
				bulletTimer = 0;
				handler.addObject(new WormBossBullet(x+30,y+25,ID.WormBossBullet,handler));
			}
			
			
			if(x <= 10 ) {
				x++;
				velY = 12;
				velX = 0;
			}
			else if( y >= Game.HEIGHT - 99) {
				y--;
				velY = 0;
				velX = 12;
			}
			else if( x >= Game.WIDTH - 80) {
				x--;
				velY = -12;
				velX = 0;
			}	
			else if(y <= 30 ) {
				y++;
				velY = 0;
				velX = -5;
			}
			
			x+=velX;
			y+=velY;
			
		}
		else if(chameleon_form == 5) { //Virus Boss form
				if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 0) velY = 0;
			
			x+=velX;
			y+=velY;
			
			if(bulletTimer>= 50) {
				bulletTimer = 0;
				check_For_Health_Packs();
				handler.addObject( new Healer(r.nextInt(Game.WIDTH-50),r.nextInt(250)+(Game.HEIGHT - 300),ID.Healer,handler));
			}
			
			//Collision
			//if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
			
		}
		else if(chameleon_form == 6) { //Bouncer Boss form
			if( velX != 2.5f && velX != -2.5f) {
				if( velX < 0) velX = -2.5f;
				else velX = 2.5f;	
			}
			
			velY += 0.12f;
			if(velX==0) { 
				velY = 1;
				velX = 2.5f;
			}
			
			x+=velX;
			y+=velY;
			
			if(bulletTimer>= 14) {
				bulletTimer = 0;
				handler.addObject(new BouncerBossBullet(x+64,y+32,ID.BouncerBossBullet,5,handler));
				handler.addObject(new BouncerBossBullet(x,y+32,ID.BouncerBossBullet,-5,handler));
			}
			
			//Collision
			if( y >= Game.HEIGHT - 64) velY = -10; 
			if(x <= 0 || x >= Game.WIDTH - 64) velX *= -1;
			
		}
		else if(chameleon_form == 7) { //Explosive Boss form
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 0) velY = 0;
			
			x+=velX;
			y+=velY;
			
			if(bulletTimer>= 15) {
				bulletTimer = 0;
				handler.addObject(new ExplosiveBossBullet(x+30,y+60,ID.ExplosiveBossBullet,handler));
			}
			
			//Collision
			//if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
			
		}
		else if(chameleon_form == 8) { //VenomEnemy form
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 0) velY = 0;
			
			x+=velX;
			y+=velY;
			
			if(dropRain) {
				venomRainHz++;
				if(venomRainHz>6) {
					handler.addObject( new VenomRain(r.nextInt(Game.WIDTH -7),-70,ID.VenomRain,handler)); //VENOM RAIN DROP
					venomRainHz = 0;
				}
			}
			
			velY = 0;
			shakeTimer++;
			if(velX==0) { 
				velX = 5;
			}
			 if(shakeTimer>= 160 && shakeTimer <= 310) {
				 if(rattle) {
						AudioPlayer.getSound("sound_snake").play(1f,1f);
						rattle = false;
					}
					dropRain = true;
				
				shakeHz++;
				if(shakeHz>=5) {
					velX *= -1;
					shakeHz = 0;
				}
			 }
			
			//Collision
			//if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
			
		}
		else if(chameleon_form == 9) { //VoidEnemy form
			
			
			velY = 0;
			
			x = (float) ((300) * Math.cos(angle)) + (300-width/2);
			if( x_prev != x) x_prev = x;
			if( x_prev - x < 0) velX = 5;
			if( x_prev - x > 0) velX = -5;
			angle+= 0.02;
			
			
			if(bulletTimer>= 120) {
				bulletTimer = 0;
				handler.addObject(new VoidBossBullet((float)r.nextInt(633),450,ID.VoidBossBullet,width,handler));
				handler.addObject(new VoidBossBullet((float)r.nextInt(633),450,ID.VoidBossBullet,width,handler));
				handler.addObject(new VoidBossBullet(-10,(float)r.nextInt(298)+150,ID.VoidBossBullet,width,handler));
				handler.addObject(new VoidBossBullet(640,(float)r.nextInt(298)+150,ID.VoidBossBullet,width,handler));
			}
		
			void_collision();
			
		}
		
		
	  
	}//end of tick()
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		if(chameleon_form == 0) {
			g.setColor(Color.WHITE);
			g.fillRect((int)x,(int) y, width, height);
		}
		else if(chameleon_form == 1 && changing_delay > 10) {
			g.drawImage(boss_icon_1, (int)x,(int) y, null);
		}
		else if(chameleon_form == 2 && changing_delay > 10) {
			//g.setColor(Color.CYAN);
			//g.fillRect((int)x,(int) y, width, height);
		}
		else if(chameleon_form == 3 && changing_delay > 10) {
			g.drawImage(boss_icon_3_center, (int)x-16,(int) y-16, null);
		}
		else if(chameleon_form == 4 && changing_delay > 10 && y<= 70) {
			g.drawImage(boss_icon_4, (int)x-16,(int) y-16, null);
		}
		else if(chameleon_form == 5 && changing_delay > 10) {
			g.drawImage(boss_icon_5, (int)x-16,(int) y-16, null);
		}
		else if(chameleon_form == 6 && changing_delay > 10) {
			g.drawImage(boss_icon_6, (int)x-16,(int) y-16, null);
		}
		else if(chameleon_form == 7 && changing_delay > 10) {
			g.drawImage(boss_icon_7, (int)x-16,(int) y-16, null);
		}
		else if(chameleon_form == 8 && changing_delay > 10) {
			g.drawImage(boss_icon_8, (int)x-16,(int) y-16, null);
		}
		else if(chameleon_form == 9 && changing_delay > 10) {
			g.drawImage(boss_icon_9, (int)x-30,(int) y-30, null);
		}
		
	}
}

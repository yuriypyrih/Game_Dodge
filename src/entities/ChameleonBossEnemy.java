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
import engine.WarningEffect;

public class ChameleonBossEnemy extends GameObject{
	
	
	private Image boss_icon_1 = new  ImageIcon("res/scout_boss_icon.png").getImage();
	private Image boss_icon_3_center = new  ImageIcon("res/smart_boss_icon_center.png").getImage();
	private Image boss_icon_3_left = new  ImageIcon("res/smart_boss_icon_left.png").getImage();
	private Image boss_icon_3_right = new  ImageIcon("res/smart_boss_icon_right.png").getImage();
	private Image boss_icon_4 = new ImageIcon("res/worm_boss_icon.png").getImage();
	
	private Handler handler;
	private GameObject player;
	private int chameleon_timer = -100; // preset so it will wait before changing
	private int changing_delay = 0; // This is for clarity of the sprite
	private int bulletTimer = -10;
	private float timer = 160;
	
	private float shadowAlpha = 1;
	private boolean goStealth = true;
	private int stealthTimer = 0;
	
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
	
	
	
	public void tick() {
		
	
		
		
		
		
		
		
		changing_delay ++;
		chameleon_timer++;
		bulletTimer++;
		
		if(chameleon_timer >= 200 && x > 420 && x < 560 && velX < 0 && velY == 0) {

			
			//debug_position();
			chameleon_form = r.nextInt(4) + 1;
			if(chameleon_form != chameleon_prev_form) {
				handler.addObject(new ChameleonChanging(x,y,ID.ChameleonChanging,this, handler));
				changing_delay = 0;
				chameleon_prev_form = chameleon_form;
				bulletTimer = -30;
				just_changed_form = true;
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
			
			if( velY != 0) {
				if(velY <= 15) velY ++;
				else velY --;	
			}
	
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
			
			if( velY != 0) {
				if(velY <= 10) velY ++;
				else velY --;	
			}
			
			
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
				if(velX < 25 && velX > -25) velX *= 1.4;
			}
			if( x >= (Game.WIDTH - 64) && velX > 0) {
					velX *= -1;
					// INCREASING SPEED
				if(velX < 25 && velX > -25) velX *= 1.4;			
			}
		}
		
		else if(chameleon_form == 3) { //Smart Boss form

			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 0) {
				if(velY <= 10) velY ++;
				else velY --;	
			}
			
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
			System.out.println("velY:" + velY);
			 if( y > 70 ) handler.addObject(new Trail(x, y, ID.Trail, light_pink, 64, 64, 0.08f, handler));
			
			/*if( velX != 5 && velX != -5 && velX != 0 && velX != 12) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}*/
			
				
			x+=velX;
			y+=velY;
			
			if(bulletTimer>=5 && velX == -5) {
				bulletTimer = 0;
				handler.addObject(new WormBossBullet(x+30,y+25,ID.WormBossBullet,handler));
			}
			
			
			if(x <= 10 ) {
				System.out.println("Fuking hitted it");
				x++;
				velY = 12;
				velX = 0;
			}
			else if( y >= Game.HEIGHT - 99) {
				System.out.println("BUT I AM IDIOT 1");
				y--;
				velY = 0;
				velX = 12;
			}
			else if( x >= Game.WIDTH - 80) {
				System.out.println("BUT I AM IDIOT 2");
				x--;
				velY = -12;
				velX = 0;
			}	
			else if(y <= 30 ) {
				System.out.println("BUT I AM IDIOT 3");
				y++;
				velY = 0;
				velX = -5;
			}
		}
		/*
		else if(chameleon_form == 5) { //Virus Boss form
			chameleon_color = virus_purple;
			handler.addObject(new Trail(x, y, ID.Trail,new  Color(133, 0, 184) ,new Color(153, 0, 204), 16, 16, 0.04f, handler));
			
			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			x+=velX;
			y+=velY;
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			bulletTimer++;
			
			if(bulletTimer >80) {
				bulletTimer = 0;
				check_For_Health_Packs() ;
				
			}
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
			
		}
		else if(chameleon_form == 6) { //Bouncer Boss form
			chameleon_color = Color.green;
			handler.addObject(new Trail(x, y, ID.Trail, chameleon_color, width, height, 0.04f, handler));
			
			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			x+=velX;
			y+=velY;
			
			velY += 0.11f;
			
			//Collision
			if( y >= Game.HEIGHT - width * 2) velY = -9; 
			if( y <= 0) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
			
		}
		else if(chameleon_form == 7) { //Explosive Boss form
			chameleon_color = Brown;
			handler.addObject(new Trail(x, y, ID.Trail, chameleon_color, width, height, 0.04f, handler));
			
			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			
			//that's HOW OUR ENEMY BEHAVES
			x+=velX;
			y+=velY;
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT -  width * 2) {
				handler.addObject(new ExplosiveBoom(x+16,y+16,ID.ExplosiveBoom, 0.2f, handler));
				velY *= -1; 
			}
			if(x <= 0 || x >= Game.WIDTH - width) {
				handler.addObject(new ExplosiveBoom(x+16,y+16,ID.ExplosiveBoom, 0.2f, handler));
				velX *= -1;
			}
			
		}
		else if(chameleon_form == 8) { //VenomEnemy form
		
			if(width > 16 || height > 16){
				width--;
				height--;
			}
		
			if(just_changed_form == true) {
				AudioPlayer.getSound("sound_snake").play(1.2f,0.4f);
				just_changed_form = false;
			}
			
			handler.addObject(new Trail(x, y, ID.VenomTrail, Color.white, new Color (20, 0, 20), width, height, 0.025f, handler));
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			
			//that's HOW OUR ENEMY BEHAVES
			x+=velX;
			y+=velY;
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT - width * 2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
			
		}
		else if(chameleon_form == 9) { //VoidEnemy form
			handler.addObject(new Trail(x, y, ID.Trail, void_color, width, height, 0.04f, handler));
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			//Collision
			if(y <= 0 && velY< 0) {
				width += 15;
				height += 15;
				velY *= -1 ; 
			}
			if(y >= Game.HEIGHT - (height+32) && velY>0) {
				width += 15;
				height += 15;
				velY *= -1 ;
			}
			if(x <= 0 && velX< 0) {
				width += 15;
				height += 15;
				velX *= -1;
			}
			if(x >= Game.WIDTH - (width+16) && velX>0) {
				width += 15;
				height += 15;
				velX *= -1;
			}
			
		
			
			
			//that's HOW OUR ENEMY BEHAVES
			x+=velX;
			y+=velY;
			
			
		}
		else if(chameleon_form == 10) { // ShadowEnemy form
			
			handler.addObject(new Trail(x, y, ID.Trail, Color.gray, width, height, 0.03f,shadowAlpha, handler));
			

			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			x+=velX;
			y+=velY;
			
			stealthTimer++;
			
			if(goStealth && stealthTimer > 10) {
				shadowAlpha -= 0.01f;
				if(shadowAlpha<0) {
					shadowAlpha = 0;
					stealthTimer = 0;
					goStealth = false;
				}
			}else if(!goStealth && stealthTimer > 10){
				shadowAlpha += 0.01f;
				if(shadowAlpha>1) {
					shadowAlpha = 1;
					stealthTimer = 0;
					goStealth = true;
				}
			}
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT - width * 2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
		}
		
		*/
		
	  
	}//end of tick()
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		if(chameleon_form == 0) {
			g.setColor(Color.WHITE);
			g.fillRect((int)x,(int) y, width, height);
		}
		else if(chameleon_form ==1 && changing_delay > 10) {
			g.drawImage(boss_icon_1, (int)x,(int) y, null);
		}
		else if(chameleon_form ==2 && changing_delay > 10) {
			//g.setColor(Color.CYAN);
			//g.fillRect((int)x,(int) y, width, height);
		}
		else if(chameleon_form ==3 && changing_delay > 10) {
			g.drawImage(boss_icon_3_center, (int)x-16,(int) y-16, null);
		}
		else if(chameleon_form ==4 && changing_delay > 10 && y<= 70) {
			g.drawImage(boss_icon_4, (int)x-16,(int) y-16, null);
		}
		
	}
}

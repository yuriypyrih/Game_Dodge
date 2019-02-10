package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;

public class HybridBossEnemy extends GameObject{
	

	private Handler handler;
	private float timer;
	private int blueBullets = 0, redBullets = 0, orangeBullets = 0, pinkBullets = 0;
	
	private Image tetris_icon = new  ImageIcon("res/tetris_boss.png").getImage();
	
	private GameObject player;
	
	public HybridBossEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			timer = 160;
			
			for(int i=0 ; i<handler.getObjectList().size(); i++) {
				if(handler.getObjectList().get(i).getId() == ID.Player) player = handler.getObjectList().get(i);
			}
			
			
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
		
		redBullets++;
		pinkBullets++;
		blueBullets++;
		orangeBullets++;
		
		
		if(timer<= 0) {
			velY = 0;
			if(velX==0) { 
				velX = 5;
				redBullets = 90;
				blueBullets = 290;
				orangeBullets = 320;
			}
			if(redBullets >= 100) {
				redBullets = 0;
				
					handler.addObject(new BossBullet(x+30,y+60,ID.BossBullet,handler));
					
			}
			if(pinkBullets >= 50) {
				pinkBullets = 0;
				
					handler.addObject(new WormBossBullet(x+30,y+60,ID.WormBossBullet,handler));
					
			}
			if(blueBullets >= 300) {
				blueBullets = 0;
				
					handler.addObject(new FastBossBullet(x+30,y+60,ID.FastBossBullet,handler));
					
			}
			if(orangeBullets >= 320) {
				orangeBullets = 0;
				
					handler.addObject(new SmartBossBullet(x+30,y+60,ID.SmartBossBullet,player,handler));
					
			}
				
		}else timer--;
		
		

		
	
		
		
		//Collision
		
			if(x <= 0 || x >= (Game.WIDTH - 64)) velX *= -1;
		
		
	 // handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.drawImage(tetris_icon,(int)x,(int)y,null);
		
		
	}
}

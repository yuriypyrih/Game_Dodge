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

public class BossEnemy extends GameObject{
	
	private Handler handler;
	private float timer;
	private int scoreKeep = 0;
	
	private Image boss_icon_0 = new  ImageIcon("res/scout_boss_icon.png").getImage();
	
	public BossEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			timer = 160;
		
			
			
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
		
		scoreKeep++;
		
		
		if(timer<= 0) {
			velY = 0;
			if(velX==0) { 
				velX = 5;
			}
			if(scoreKeep>= 6) {
				scoreKeep = 0;
				handler.addObject(new BossBullet(x+30,y+60,ID.BossBullet,handler));
			}
		}else timer--;
		
		

		
	
		
		
		//Collision
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //our object is 16 px tall, and our center of mass is left-down at the bottom
		if(x <= 0 || x >= (Game.WIDTH - 64)) velX *= -1;
		
	 // handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.drawImage(boss_icon_0,(int) x, (int) y, null);
	}
}

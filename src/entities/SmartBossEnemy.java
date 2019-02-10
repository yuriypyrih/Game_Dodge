package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import engine.GameObject;
import engine.Handler;
import engine.ID;

public class SmartBossEnemy extends GameObject{
	
	private Handler handler;
	private float timer, diffX;
	private int scoreKeep = 0;
	
	private GameObject player;
	
	private Image boss_icon_center = new  ImageIcon("res/smart_boss_icon_center.png").getImage();
	private Image boss_icon_left = new  ImageIcon("res/smart_boss_icon_left.png").getImage();
	private Image boss_icon_right = new  ImageIcon("res/smart_boss_icon_right.png").getImage();
	private Image boss_icon;
	
	public SmartBossEnemy(float  x, float  y, ID id, Handler handler) {
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
		
		scoreKeep++;
		
		//this is gonna help to position the EYE in the boss
		diffX = x - player.getX();
		
		if(diffX < - 150) {
			boss_icon = boss_icon_right;
		}
		else if(diffX > 150) {
			boss_icon = boss_icon_left;
		}
		else {
			boss_icon = boss_icon_center;
		}
		
		
		if(timer<= 0) {
			velY = 0;
			if(velX==0) { 
				velX = 5;
			}
			if(scoreKeep>=25) {
				scoreKeep = 0;
				handler.addObject(new SmartBossBullet(x+30,y+70,ID.SmartBossBullet,player,handler));
			}
		}else timer--;
		
		

		
	
		
		
		//Collision
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //our object is 16 px tall, and our center of mass is left-down at the bottom
		if(x <= 20 || x >= 546) velX *= -1;
		
	 // handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.drawImage(boss_icon,(int)x-18,(int)y-18,null);
	}
}

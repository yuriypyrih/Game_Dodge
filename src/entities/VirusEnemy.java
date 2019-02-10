package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;

public class VirusEnemy extends GameObject{
	
	private Handler handler;
	private int bulletTimer = 0;
	private Random r = new Random();
	
	
	
	public VirusEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			bulletTimer = 130;
			
			velY = 5;
			velX = 5;
			
			
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
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
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		bulletTimer++;
		
		if(bulletTimer >200) {
			bulletTimer = 0;
			check_For_Health_Packs() ;
			
		}
		
		//Collision
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		
		//Color(153, 0, 204)
		handler.addObject(new Trail(x, y, ID.Trail,new  Color(133, 0, 184) ,new Color(153, 0, 204), 16, 16, 0.04f, handler));
		
	  
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		
	}
}


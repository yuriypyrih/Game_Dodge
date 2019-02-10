package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;

public class ShadowBossBullet extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private Color gray1 = new Color(204, 204, 204);
	private Color gray2 = new Color(166, 166, 166);
	
	public ShadowBossBullet(float  x, float  y, ID id, float velX, float velY, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			this.velY = velY;
			this.velX = velX;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,12,12);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		//Collision
				if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; 
				if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	  handler.addObject(new Trail(x, y, ID.Trail, gray1, gray2, 12, 12, 0.08f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		//g.setColor(Color.orange);
		//g.fillRect((int)x,(int) y, 8, 8);
	}
}

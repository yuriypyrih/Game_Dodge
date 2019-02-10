package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;

public class FastEnemy extends GameObject{
	
	private Handler handler;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 10;
			velX = 1;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		//Collision
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //our object is 16 px tall, and our center of mass is left-down at the bottom
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	   handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 16, 16, 0.04f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 16, 16);
	}
}



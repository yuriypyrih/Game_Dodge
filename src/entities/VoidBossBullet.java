package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import engine.GameObject;
import engine.Handler;
import engine.ID;

public class VoidBossBullet extends GameObject{
	
	private Handler handler;
	private Color color_4 = new Color(102, 153, 255);
	private float size;
	
	private GameObject boss;
	
	public VoidBossBullet(float  x, float  y, ID id, float size, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			//code for getting the position of player
			for(int i=0 ; i<handler.getObjectList().size(); i++) {
				if(handler.getObjectList().get(i).getId() == ID.VoidBossEnemy) boss = handler.getObjectList().get(i);
			}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		
		
		float diffY = y - boss.getY() - size;
		float diffX = x - boss.getX() - size;
		float distance = (float) Math.sqrt( (x-boss.getX())*(x-boss.getX()) + (y-boss.getY())*(y-boss.getY())  );
		
		velX = ((-3/distance) * diffX);
		velY = ((-2/distance) * diffY);
		
		//Collision
		
		
	  handler.addObject(new Trail(x, y, ID.Trail, color_4, 8, 8, 0.08f, handler));
	}
	

	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		//g.setColor(Color.orange);
		//g.fillRect((int)x,(int) y, 8, 8);
	}
}

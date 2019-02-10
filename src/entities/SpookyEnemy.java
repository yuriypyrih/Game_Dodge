package entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.GameObject;
import engine.Handler;
import engine.ID;

public class SpookyEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	private int timer = 0;
	
	public SpookyEnemy(float x, float y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			//code for getting the position of player
			for(int i=0 ; i<handler.getObjectList().size(); i++) {
				if(handler.getObjectList().get(i).getId() == ID.Player) player = handler.getObjectList().get(i);
			}

	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		
		timer++;
		
		x += velX;
		y += velY;
		
		float diffY = y - player.getY() -16;
		float diffX = x - player.getX() -16;
		float distance = (float) Math.sqrt( (x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY())  );
		
		velX = ((-1.2f/distance) * diffX);
		velY = ((-1/distance) * diffY);
		
		
		if(distance<20 || timer>240) handler.removeObject(this);
		
	  handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 16, 16, 0.04f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.orange);
		g.fillRect((int)x,(int) y, 16, 16);
	}
}

package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;

public class WormEnemy extends GameObject{
	
	private Handler handler;
	
	private Color light_pink = new Color(255,153,255);
	
	
	public WormEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 4;
			velX = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,30,30);
	}
	
	public void tick() {
	
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		
		//Collision
		if(y <= 0 ) {
			y++;
			velY = 0;
			velX = -10;
			
		}
		else if(x <= 0 ) {
			x++;
			velY = 10;
			velX = 0;
		
			
		}
		else if( y >= Game.HEIGHT - 59) {
			y--;
			velY = 0;
			velX = 10;
		
			
		}
		else if( x >= Game.WIDTH - 37) {
			x--;
			velY = -10;
			velX = 0;
			
			
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, light_pink, 30, 30, 0.04f, handler));
		
		
	  
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.PINK);
		g.fillRect((int)x ,(int) y, 30, 30);
	}
}

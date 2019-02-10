package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import engine.AudioPlayer;
import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;
import engine.WarningEffect;

public class ExplosiveEnemy extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private int thatRandom = 100;
	private int explosiveTimer = 0;
	private boolean alreadyExecuted = false;
	private Color Brown = new Color(201,112,48);
	
	public ExplosiveEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, ID.BasicEnemy);
			
			this.handler = handler;
			
			
			
			velY = 5;
			velX = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		handler.addObject(new Trail(x, y, ID.Trail, Brown , 16, 16, 0.04f, handler));
		
		explosiveTimer++;
		
		if(explosiveTimer == 50 + thatRandom) {
			AudioPlayer.getSound("sound_alarm").play(1.5f,0.1f);;
			handler.addObject(new WarningEffect(this,3, handler));
		}
		else if(explosiveTimer > 130 + thatRandom) {
			handler.addObject(new ExplosiveBoom(x,y,ID.ExplosiveBoom, 0.2f, handler));
			thatRandom = r.nextInt(500) + 1;
			explosiveTimer = 0;
		}
		
		
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		//Collision
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	  
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 16, 16);
	}
}

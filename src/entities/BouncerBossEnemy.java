package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import engine.AudioPlayer;
import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;

public class BouncerBossEnemy extends GameObject{
	
	private Handler handler;
	private float timer;
	private int scoreKeep = 0, slimeSoundTimer=0;
	
	private Image boss_icon = new ImageIcon("res/slime_boss_icon.png").getImage();
	
	public BouncerBossEnemy(float  x, float  y, ID id, Handler handler) {
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
		slimeSoundTimer++;
		
		
		
		
		if(timer<= 0) {
			velY += 0.12f;
			if(velX==0) { 
				velY = 1;
				velX = 2.5f;
			}
			if(scoreKeep>= 12) {
				scoreKeep = 0;
				handler.addObject(new BouncerBossBullet(x+64,y+32,ID.BouncerBossBullet,5,handler));
				handler.addObject(new BouncerBossBullet(x,y+32,ID.BouncerBossBullet,-5,handler));
			}
		}else timer--;
			
		
		if( velY >= 9 && slimeSoundTimer >50) {
			AudioPlayer.getSound("sound_slime").play(0.6f,0.8f);
			slimeSoundTimer = 0;
		}
		
		//Collision
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //our object is 16 px tall, and our center of mass is left-down at the bottom
		if( y >= Game.HEIGHT - 64) velY = -10; 
		if(x <= 0 || x >= Game.WIDTH - 64) velX *= -1;
		
	 // handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.drawImage(boss_icon,(int)x, (int)y, null);
	}
}

package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import engine.GameObject;
import engine.Handler;
import engine.ID;
import engine.WarningEffect;

public class DeathTrap extends GameObject{
	
	private Handler handler;
	
	private int warningTimer = 0;
	private Image deathtrap_img = new ImageIcon("res/deathtrap_1.png").getImage();
	
	public DeathTrap(float x, float y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			handler.addObject(new WarningEffect(x-8,y-8,6,handler)); //WARNING EFFECT
			
			
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	
	public void tick() {
		
		warningTimer++;
		
		if(warningTimer > 50) {
			warningTimer = 0;
			handler.addObject(new WarningEffect(x-8,y-8,6,handler)); //WARNING EFFECT 
		}
		
		
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR HEALER SHOULD LOOK LIKE
		
		g.drawImage(deathtrap_img,(int)x-8, (int)y-8,null);
		
		//g.setColor(Color.yellow);
		//g.drawRect((int)x,(int)y,16,16);
		
	}
}

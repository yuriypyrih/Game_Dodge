import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class DeathTrap extends GameObject{
	
	private Handler handler;
	private Image deathtrap_img = new ImageIcon("res/deathtrap.png").getImage();
	
	public DeathTrap(float x, float y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		
		
		//Collision
		/*if(y <= 0 || y >= Game.HEIGHT - 60) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 30) velX *= -1;*/
		
	  /* //handler.addObject(new Trail(x, y, ID.Trail, Color.green, 20, 20, 0.1f, handler));*/
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR HEALER SHOULD LOOK LIKE
		
		g.drawImage(deathtrap_img,(int)x, (int)y,null);
		
	}
}

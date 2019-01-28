import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Healer extends GameObject{
	
	private Handler handler;
	private Image health_pack = new ImageIcon("res/health_pack.png").getImage();
	
	public Healer(float x, float y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x-6,(int)y-6,22,22);
	}
	
	
	
	public void tick() {
		
	
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR HEALER SHOULD LOOK LIKE
		g.drawImage(health_pack,(int)x,(int)y,null);

		
	}
}

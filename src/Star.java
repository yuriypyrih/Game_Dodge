
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Star extends GameObject{
	
	private Handler handler;
	
	public Star(float x, float y, ID id, Handler handler) {
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
		
	   //handler.addObject(new Trail(x, y, ID.Trail, Color.green, 20, 20, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR HEALER SHOULD LOOK LIKE
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 16, 16); //x,y,width,height
		
		
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, 16, 2);
		g.fillRect((int)x, (int)y+2, 6, 2);
		g.fillRect((int)x, (int)y+4, 4, 2);
		g.fillRect((int)x, (int)y+6, 2, 4);
		g.fillRect((int)x, (int)y+10, 4, 2);
		g.fillRect((int)x, (int)y+12, 6, 2);
		g.fillRect((int)x, (int)y+14, 16, 2);
		
		g.fillRect((int)x+10, (int)y+2, 6, 2);
		g.fillRect((int)x+12, (int)y+4, 4, 2);
		g.fillRect((int)x+14, (int)y+6, 2, 4);
		g.fillRect((int)x+12, (int)y+10, 4, 2);
		g.fillRect((int)x+10, (int)y+12, 6, 2);
		
		g.setColor(Color.yellow);
		g.drawOval((int)x-2, (int)y-3, 20, 20);
		
		
		
	}
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DeathTrap extends GameObject{
	
	private Handler handler;
	
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
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
		
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, 5, 5);
		g.fillRect((int)x+11, (int)y, 5, 5);
		g.fillRect((int)x, (int)y+11, 5, 5);
		g.fillRect((int)x+11, (int)y+11, 5, 5);
		
		g.setColor(Color.red);
		g.fillRect((int)x +6, (int)y, 4, 16);
		g.fillRect((int)x, (int)y+ 6, 16, 4);
		
		g.drawOval((int)x-4, (int)y-4, 23, 22);
		
		
	}
}

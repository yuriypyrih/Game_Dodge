import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthRain extends GameObject{
	
	private Handler handler;
	
	public HealthRain(float x, float y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			velX = 0;
			velY = 10;
			
			
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x-14,(int)y-14,30,30);
	}
	
	public void tick() {
		
		x+=velX;
		y+=velY;
		
		
		//Collision
		if( y >= Game.HEIGHT - 60) handler.removeObject(this); 
		//if(x <= 0 || x >= Game.WIDTH - 30) velX *= -1;*/
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 4, 4, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR HEALER SHOULD LOOK LIKE
		
	}
}

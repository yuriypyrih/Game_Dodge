import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BouncerEnemy extends GameObject{
	
	private Handler handler;
	
	
	public BouncerEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 1;
			velX = 1.2f;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.04f, handler));
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		velY += 0.11f;
		
		//Collision
		if( y >= Game.HEIGHT - 32) velY = -9; 
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	  
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		
	}
}

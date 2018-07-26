import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	
	private Handler handler;
	
	public BasicEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 5;
			velX = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.04f, handler));
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

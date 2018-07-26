import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FastBossBullet extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	
	
	public FastBossBullet(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 1;
			velX = 10;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		//Collision
		if(y <= 0 || y >= Game.HEIGHT - 32) handler.removeObject(this); //our object is 16 px tall, and our center of mass is left-down at the bottom
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	  handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 8, 8, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.orange);
		g.fillRect((int)x,(int) y, 8, 8);
	}
}

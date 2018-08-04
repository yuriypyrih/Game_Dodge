import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BouncerBossBullet extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private Color slime_green = new Color(102, 255, 102);
	
	public BouncerBossBullet(float  x, float  y, ID id, int velX, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 0;
			this.velX = velX;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		
		
		//Collision
		
		if(x <= 0 || x >= Game.WIDTH - 16) handler.removeObject(this);
		
	  handler.addObject(new Trail(x, y, ID.Trail, slime_green, 8, 8, 0.08f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		
	}
}

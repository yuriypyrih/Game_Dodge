import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossBullet extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	//private Color bloody = new Color(204, 0, 0);
	
	public BossBullet(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = r.nextInt((5 - 2) + 1) + 2;
			velX = r.nextInt((5 - -5) + 1) -5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		//Collision
		if(y <= 0 || y >= Game.HEIGHT - 32) handler.removeObject(this); 
		if(x <= 0 || x >= Game.WIDTH - 16) handler.removeObject(this);
		
	  handler.addObject(new Trail(x, y, ID.Trail, Color.red, 8, 8, 0.08f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.orange);
		g.fillRect((int)x,(int) y, 8, 8);
	}
}

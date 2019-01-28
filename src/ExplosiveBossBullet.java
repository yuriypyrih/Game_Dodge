import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class ExplosiveBossBullet extends GameObject{
	
	private Handler handler;
	
	private Color Brown = new Color(201,112,48);
	
	
	public ExplosiveBossBullet(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 0.5f;
			velX = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		handler.addObject(new Trail(x, y, ID.Trail, Brown, 8, 8, 0.08f, handler));
		
		x+=velX;
		y+=velY;
		
		velY *= 1.04f;
		
		//Collision
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			handler.addObject(new ExplosiveBoom(x,y,ID.ExplosiveBoom,0.08f, handler));
			handler.removeObject(this); 
		}
		//if(x <= 0 || x >= Game.WIDTH - 16) handler.removeObject(this);
		
	  
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 8, 8);
	}
}

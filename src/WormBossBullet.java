import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class WormBossBullet extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private int recoilTimer = 0;
	
	
	public WormBossBullet(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 5;
			velX = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		
		recoilTimer++;
		
		
		
		if(recoilTimer <= 10) {
			velY = r.nextInt(7)+1;
			velX = 0;
		}else if(recoilTimer <= 30) {
			velY = 0;
			velX = r.nextInt(7)+1;
		}else if(recoilTimer <= 50) {
			velY = r.nextInt(7)+1;
			velX = 0;
		}else if(recoilTimer <= 70) {
			velY = 0;
			velX = - (r.nextInt(9) + 1);
		}else if(recoilTimer <= 90) {
			velY = r.nextInt(7)+1;
			velX = 0;
			recoilTimer = 0;
		}
		
		x+=velX;
		y+=velY;
		
		//Collision
		if(y <= 0 || y >= Game.HEIGHT - 32) handler.removeObject(this); 
		if(x <= 0 || x >= Game.WIDTH - 16) handler.removeObject(this);
		
	  handler.addObject(new Trail(x, y, ID.Trail, new Color(255, 153, 255), 8, 8, 0.08f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.pink);
		g.fillRect((int)x,(int) y, 8, 8);
	}
}

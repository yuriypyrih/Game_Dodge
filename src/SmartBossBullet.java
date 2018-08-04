
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class SmartBossBullet extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private GameObject player;
	
	
	public SmartBossBullet(float  x, float  y, ID id,GameObject player, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			this.player = player;
			
			
			
			velX = 0;
			velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,12,12);
	}
	
	
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		//Collision
		if(y <= 0 ) handler.removeObject(this); //our object is 16 px tall, and our center of mass is left-down at the bottom
		if( y >= Game.HEIGHT - 32) {
			float diffY = y - player.getY() -16;
			float diffX = x - player.getX() -16;
			float distance = (float) Math.sqrt( (x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY())  );
			velX = ((-10/distance) * diffX);
			velY = ((-10/distance) * diffY);
		}
		
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		
	  handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 12, 12, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.YELLOW);
		g.fillRect((int)x,(int) y, 12, 12);
	}
}

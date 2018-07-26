import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class WormBossEnemy extends GameObject{
	
	private Handler handler;
	private float timer;
	private int scoreKeep = 0;
	
	public WormBossEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			timer = 160;
		
			
			
			velY = 0.6f;
			velX = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		scoreKeep++;
		
		
		if(timer<= 0) {
			if(y <= 30 ) {
				y++;
				velY = 0;
				velX = -5;
				
			}
			else if(x <= 10 ) {
				x++;
				velY = 10;
				velX = 0;
			
				
			}
			else if( y >= Game.HEIGHT - 99) {
				y--;
				velY = 0;
				velX = 10;
			
				
			}
			else if( x >= Game.WIDTH - 80) {
				x--;
				velY = -10;
				velX = 0;
				
				
			}
			if(scoreKeep>= 5 && velX == -5) {
				scoreKeep = 0;
				handler.addObject(new WormBossBullet(x+30,y+60,ID.WormBossBullet,handler));
			}
		}else timer--;
		
		

		
	
		
		
		//Collision
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //our object is 16 px tall, and our center of mass is left-down at the bottom
		if(x <= 0 || x >= (Game.WIDTH - 64)) velX *= -1;
		
	 // handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.pink);
		g.fillRect((int)x,(int) y, 64, 64);
	}
}

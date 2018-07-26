import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastBossEnemy extends GameObject{
	
	private Handler handler;
	private float timer;
	private int scoreKeep = 0;
	private Color darkcyan = new Color(0,139,139);
	
	public FastBossEnemy(float  x, float  y, ID id, Handler handler) {
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
			velY = 0;
			if(velX==0) { 
				velX = 5;
			}
			if(scoreKeep>=15) {
				scoreKeep = 0;
				handler.addObject(new FastBossBullet(x+30,y+60,ID.FastBossBullet,handler));
			}
		}else timer--;
		
		
		//Collision
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //our object is 16 px tall, and our center of mass is left-down at the bottom
		if(x <= 0 || x >= (Game.WIDTH - 64)) velX *= -1;
		
	 // handler.addObject(new Trail(x, y, ID.Trail, darkcyan, 64, 64, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(darkcyan);
		g.fillRect((int)x,(int) y, 64, 64);
	}
}

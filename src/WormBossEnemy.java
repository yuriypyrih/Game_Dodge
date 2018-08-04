import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class WormBossEnemy extends GameObject{
	
	private Handler handler;
	private float timer;
	private int scoreKeep = 0;
	
	private Color light_pink = new Color(255,153,255);
	
	private Image boss_icon = new ImageIcon("res/worm_boss_icon.png").getImage();
	
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
				velY = 12;
				velX = 0;
			
				
			}
			else if( y >= Game.HEIGHT - 99) {
				y--;
				velY = 0;
				velX = 12;
			
				
			}
			else if( x >= Game.WIDTH - 80) {
				x--;
				velY = -12;
				velX = 0;
				
				
			}
			if(scoreKeep>= 5 && velX == -5) {
				scoreKeep = 0;
				handler.addObject(new WormBossBullet(x+30,y+25,ID.WormBossBullet,handler));
			}
		}else timer--;
		
		

		
	
		
		
		//Collision
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //our object is 16 px tall, and our center of mass is left-down at the bottom
		if(x <= 0 || x >= (Game.WIDTH - 64)) velX *= -1;
		
	   if( y > 70 ) handler.addObject(new Trail(x, y, ID.Trail, light_pink, 64, 64, 0.08f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		
		g.drawImage(boss_icon,(int)x,(int)y,null);
	}
}

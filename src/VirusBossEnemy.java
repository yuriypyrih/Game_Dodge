import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class VirusBossEnemy extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private float timer;
	private int scoreKeep = 0;
	
	private Image boss_icon_0 = new  ImageIcon("res/virus_boss_icon_100px.png").getImage();
	
	public VirusBossEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			timer = 160;
		
			
			
			velY = 0.6f;
			velX = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}
	
	private void check_For_Health_Packs() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Healer) {
				handler.addObject( new VirusBullet(x+30,y+64,ID.VirusBullet,handler));
				return;
			}
		}
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
			if(scoreKeep>= 20) {
				scoreKeep = 0;
				check_For_Health_Packs();
				handler.addObject( new Healer(r.nextInt(Game.WIDTH-50),r.nextInt(250)+(Game.HEIGHT - 300),ID.Healer,handler));
				
			}
		}else timer--;
		
		

		
	
		
		
		//Collision
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; //our object is 16 px tall, and our center of mass is left-down at the bottom
		if(x <= 0 || x >= (Game.WIDTH - 64)) velX *= -1;
		
	 // handler.addObject(new Trail(x, y, ID.Trail, Color.red, 64, 64, 0.1f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.drawImage(boss_icon_0,(int)x-18,(int)y-18,null);
		
		
	}
}

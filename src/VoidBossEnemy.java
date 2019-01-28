import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


public class VoidBossEnemy extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private float timer;
	private int scoreKeep = 0;
	private int width = 5 , height = 5, growingTimer = 0;
	private double angle = 180;
	
	//private Color color_4 = new Color(102, 153, 255);
	
	private Image black_hole_30 = new ImageIcon("res/black_hole_30.png").getImage();
	private Image black_hole_60 = new ImageIcon("res/black_hole_60.png").getImage();
	private Image black_hole_120 = new ImageIcon("res/black_hole_120.png").getImage();
	
	
	
	
	
	
	public VoidBossEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			timer = 100;
		
			
			
			
			velY = 0.4f;
			velX = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
	}
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		//x+=velX;
		y+=velY;
		
		scoreKeep++;
		growingTimer++;
		
		x = (float) ((300) * Math.cos(angle)) + (300-width/2);
		angle+= 0.02;
		
		
		
		if(timer<= 0) {
			velY = 0;
			
			if(scoreKeep>= 120) {
				scoreKeep = 0;
				handler.addObject(new VoidBossBullet((float)r.nextInt(633),450,ID.VoidBossBullet,width,handler));
				handler.addObject(new VoidBossBullet((float)r.nextInt(633),450,ID.VoidBossBullet,width,handler));
				handler.addObject(new VoidBossBullet(-10,(float)r.nextInt(298)+150,ID.VoidBossBullet,width,handler));
				handler.addObject(new VoidBossBullet(640,(float)r.nextInt(298)+150,ID.VoidBossBullet,width,handler));
			}
		}else timer--;
		
		if(growingTimer > 20 && width < 300) {
			width += 1;
			height += 1;
			growingTimer = 0;
		}
		
		
		
	
		
		
		//Collision
		if(x <= 0 && velX< 0) velX *= -1;
		if(x >= Game.WIDTH - (width+16) && velX>0) velX *= -1;
		collision();
		
		
		
	 
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.VoidBossBullet) {
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					//collision
					if(width<300) {
						width += 2;
						height += 2;
						if(Player.poisoned) { // just in case you needed that.. *winky face*
							handler.addObject( new HealthRain(r.nextInt(Game.WIDTH -7),-70,ID.HealthRain,handler)); //HEALTH RAIN DROP
							handler.addObject( new HealthRain(r.nextInt(Game.WIDTH -7),-70,ID.HealthRain,handler)); //HEALTH RAIN DROP
						}
					}
					handler.removeObject(tempObject);
				}
			}
		}
		
		
	
		
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
	
		
		if(width<40) {
			g.drawImage(black_hole_30, (int)x - width/5, (int)y -height/5, (int)x + width + width/5, (int)y + height + width/5, 0, 0, 30, 30, null);
		}else if(width<100) {
			g.drawImage(black_hole_60, (int)x - width/5, (int)y -height/5, (int)x + width + width/5, (int)y + height + width/5, 0, 0, 60, 60, null);
		}
		else if(width>=100) {
			g.drawImage(black_hole_120, (int)x - width/5, (int)y -height/5, (int)x + width + width/5, (int)y + height + width/5, 0, 0, 120, 120, null);
		}
		
		//g.setColor(Color.yellow);
		//g.drawRect((int)x,(int)y,width,height);
		
		
	}
}

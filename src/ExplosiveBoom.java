
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class ExplosiveBoom extends GameObject{
	
	private Handler handler;
	private int boom_animation_timer = 0;

	
	
	private Image boom_0 = new ImageIcon("res/explosive_0.png").getImage();
	private Image boom_1 = new ImageIcon("res/explosive_1.png").getImage();
	private Image boom_2 = new ImageIcon("res/explosive_3.png").getImage();
	private Image boom_3 = new ImageIcon("res/explosive_4.png").getImage();
	private Image boom_4 = new ImageIcon("res/explosive_5.png").getImage();
	private Image boom_5 = new ImageIcon("res/explosive_6.png").getImage();
	private Image boom_6 = new ImageIcon("res/explosive_7.png").getImage();
	private Image boom_7 = new ImageIcon("res/explosive_8.png").getImage();
	private Image boom_8 = new ImageIcon("res/explosive_9.png").getImage();
	private Image boom_9 = new ImageIcon("res/explosive_10.png").getImage();
	private Image boom_10 = new ImageIcon("res/explosive_11.png").getImage();
	private Image boom_11 = new ImageIcon("res/explosive_12.png").getImage();
	
	
	
	public ExplosiveBoom(float  x, float  y, ID id,float volume, Handler handler) {
			super(x, y, id);
			
			//volume as 0.2f is more than enough, and 0.1f is quiter
			AudioPlayer.getSound("sound_explosion").play(1f,volume);;
			this.handler = handler;
			
			
	}
	
	public Rectangle getBounds() {
		if(boom_animation_timer<14) {
			return new Rectangle((int)x-117, (int)y-117,250,250);
		}else {
			return new Rectangle((int)x, (int)y,16,16);
		}
	}
	
	
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		boom_animation_timer++;
		

	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		
	
		if(boom_animation_timer<2) {
			
			g.drawImage(boom_0,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<5) {
			
			g.drawImage(boom_1,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<8) {
			
			g.drawImage(boom_2,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<11) {
			
			g.drawImage(boom_3,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<14) {
			
			g.drawImage(boom_4,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<17) {
			
			g.drawImage(boom_5,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<20) {
			
			g.drawImage(boom_6,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<23) {
			
			g.drawImage(boom_7,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<26) {
			
			g.drawImage(boom_8,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<29) {
			
			g.drawImage(boom_9,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<32) {
			
			g.drawImage(boom_10,(int)x-142, (int)y-142,null);
			
		}
		else if(boom_animation_timer<34) {
			
			g.drawImage(boom_11,(int)x-142, (int)y-142,null);
			
		}
		
		else if(boom_animation_timer>34) {
			handler.removeObject(this);
		}
		
		//g.setColor(Color.orange);
		//g.drawRect((int)x-117, (int)y-117,250,250);
		
		
	}
}

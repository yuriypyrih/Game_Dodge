
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Star extends GameObject{
	
	private Handler handler;
	
	private int star_animation_timer = 0;
	
	private Image star0_img = new ImageIcon("res/star.png").getImage();
	private Image star1_img = new ImageIcon("res/star_animation_1.png").getImage();
	private Image star2_img = new ImageIcon("res/star_animation_2.png").getImage();
	private Image star3_img = new ImageIcon("res/star_animation_3.png").getImage();
	private Image star4_img = new ImageIcon("res/star_animation_4.png").getImage();
	private Image star5_img = new ImageIcon("res/star_animation_5.png").getImage();
	

	
	public Star(float x, float y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		
		
		star_animation_timer++;
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR HEALER SHOULD LOOK LIKE
		if(star_animation_timer<50) {
			g.drawImage(star0_img,(int)x, (int)y,null);
		}
		else if(star_animation_timer < 55) {
			g.drawImage(star1_img,(int)x, (int)y,null);
		}
		else if(star_animation_timer < 60) {
			g.drawImage(star2_img,(int)x, (int)y,null);
		}
		else if(star_animation_timer < 65) {
			g.drawImage(star3_img,(int)x, (int)y,null);
		}
		else if(star_animation_timer < 70) {
			g.drawImage(star4_img,(int)x, (int)y,null);
		}
		else if(star_animation_timer < 75) {
			g.drawImage(star5_img,(int)x, (int)y,null);
		}
		else {
			star_animation_timer = 0;
		}
	
	}
}

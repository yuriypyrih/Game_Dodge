

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class SpookyStar extends GameObject{
	
	private Handler handler;
	private GameObject player;
	public static boolean locked = false; 
	private static boolean becomeRealStar = false;
	
	
	
	private int star_animation_timer = 0, speakDelay = 0;
	
	private Image star0_img = new ImageIcon("res/star.png").getImage();
	private Image star1_img = new ImageIcon("res/star_animation_1.png").getImage();
	private Image star2_img = new ImageIcon("res/star_animation_2.png").getImage();
	private Image star3_img = new ImageIcon("res/star_animation_3.png").getImage();
	private Image star4_img = new ImageIcon("res/star_animation_4.png").getImage();
	private Image star5_img = new ImageIcon("res/star_animation_5.png").getImage();
	
	private Image star_cage= new ImageIcon("res/star_cage.png").getImage();
	

	
	public SpookyStar(float x, float y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			 locked = false; 
			
			AudioPlayer.getSound("sound_star_appear").play(1,0.3f);
			
			for(int i=0 ; i<handler.object.size(); i++) {
				if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
				
			}
			
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public static void getUnlocked() {
		becomeRealStar = true;
	}
	
	public void tick() {
		
		
		star_animation_timer++;
		
		if(getBounds().intersects(player.getBounds()) && !locked) {	//intersects() is a method of Rectangle library
			AudioPlayer.getSound("sound_get_locked").play(0.75f,0.4f);
			locked = true;
		}else if(locked) {
			speakDelay++;	
		}
		
		if(speakDelay==80) {
			AudioPlayer.getSound("sound_cant_do_that").play(0.75f,0.4f);
		}
		
		if(becomeRealStar) {
			 handler.addObject( new Star(x,y,ID.Star,handler)); //STAR
			 AudioPlayer.getSound("sound_get_unlocked").play(1f,1f);
			 handler.removeObject(this);
		}
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
		
		if(locked) {
			g.drawImage(star_cage,(int)x-17,(int)y-15,null);
		}
	
	}
}

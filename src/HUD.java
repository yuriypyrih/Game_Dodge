import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;

/*Heads Up Display class*/


public class HUD {
	
	public static float HEALTH = 100; 
	private float greenValue = 255; // Health bar changing color
	
	private int score = 0;
	private int innerLevel = 1;
	private int stars = 0;
	private String diff = "NORMAL";
	
	private Image poison_icon = new ImageIcon("res/poison_icon.png").getImage();
	private Image stars_0 = new ImageIcon("res/stars_0.png").getImage();
	private Image stars_1 = new ImageIcon("res/stars_1.png").getImage();
	private Image stars_2 = new ImageIcon("res/stars_2.png").getImage();
	private Image stars_img;
	
	//RoundRectangle2D roundedRectangle, roundedRectangleHP, roundedRectangleBorder;
	
			
	

	
	
	public void tick() {
		
		if(stars == 0) {
			stars_img = stars_0;
		}
		else if(stars == 1) {
			stars_img = stars_1;
		}
		else if(stars == 2) {
			stars_img = stars_2;
		}
		
		HEALTH = Game.clamp(HEALTH, 0, 100);//which means health cannot drop bellow 0 or above 100 
		greenValue =  Game.clamp(greenValue, 0, 255);
		
		greenValue = 255 * HEALTH /100;
		
		score++;
		
	}
	
	public void render(Graphics g) {
		
		/*
		 *  Graphics2D graphics2 = (Graphics2D) g;
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(100, 100, 240, 160, 10, 10);
        graphics2.draw(roundedRectangle);
		 **/
		Graphics2D graphics2 = (Graphics2D) g;
		
		if(!Player.poisoned) {
			graphics2.setColor(Color.gray);
		}else {
			graphics2.setColor(new Color(166,53,189));
		}
		//g.fillRect(15,15,200,32);
		
        //roundedRectangle = new RoundRectangle2D.Float(15, 15, 200, 25, 25, 25);
        graphics2.fill(new RoundRectangle2D.Float(15, 15, 200, 25, 25, 25));
		
		
		if(!Player.poisoned) {
			graphics2.setColor(new Color(100, (int)greenValue, 25));;
		}else {
			graphics2.setColor(new Color(75, 0,(int)greenValue ));
		}
		
		//graphics2.fillRect(15,15,(int)HEALTH * 2,32);
		//roundedRectangleHP = new RoundRectangle2D.Float(15, 15, (int)HEALTH * 2, 25, 25, 25);
        graphics2.fill(new RoundRectangle2D.Float(15, 15, (int)HEALTH * 2, 25, 25, 25));
		
		graphics2.setColor(Color.white);
		
		//g.drawRect(15, 15, 200, 32);///that gives a white border to the health bar
		//roundedRectangleBorder = new RoundRectangle2D.Float(15, 15, 200, 25, 25, 25);
        graphics2.draw(new RoundRectangle2D.Float(15, 15, 200, 25, 25, 25));
		
		
		//g.drawString("Score: " + score ,15 ,64);
		//graphics2.drawString("Stage Timer: " + innerLevel ,15, 90);
		//graphics2.drawString( diff + " MODE",15, 64);
		//graphics2.drawString( stars + "/3 Stars" ,15, 80);
		//graphics2.drawString("Level "+ Spawn.getOutterLevel(), 15, 96);
		
		if(Player.poisoned) {
			graphics2.drawImage(poison_icon,183,18,null);
		}
		
		//STARS
		graphics2.drawImage(stars_img,30,45,null);
		
		
	}
	
	public void reset() {
		HEALTH = 100;
		score = 0;
		innerLevel = 0;
		stars = 0;
		Player.poisoned = false;
	}
	
	public void insertDiff(int diff) {
		if(diff == 0 ) {
			this.diff = "EASY";
		}else if(diff == 1 ) {
			this.diff = "NORMAL";
		}else if(diff == 2 ) {
			this.diff = "HARD";
		}
	}
	
	public void setStars(int stars) {
		this.stars = stars;
	}
	
	
	
	
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setStageTimer(int level) {
		this.innerLevel = level;
	}
	
	public int getStageTimer() {
		return innerLevel;
	}
	


}








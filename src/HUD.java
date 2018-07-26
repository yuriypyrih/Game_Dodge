import java.awt.Color;
import java.awt.Graphics;

/*Heads Up Display class*/


public class HUD {
	
	public static float HEALTH = 100; 
	private float greenValue = 255; // Health bar changing color
	
	private int score = 0;
	private int innerLevel = 1;
	private int stars = 0;
	private String diff = "NORMAL";
			
	

	
	
	public void tick() {
		
		
		HEALTH = Game.clamp(HEALTH, 0, 100);//which means health cannot drop bellow 0 or above 100 
		greenValue =  Game.clamp(greenValue, 0, 255);
		
		greenValue = 255 * HEALTH /100;
		
		score++;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15,15,200,32);
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(15,15,(int)HEALTH * 2,32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);///that gives a white border to the health bar
		
		//g.drawString("Score: " + score ,15 ,64);
		//g.drawString("Stage Timer: " + innerLevel ,15, 80);
		g.drawString( diff + " MODE",15, 64);
		g.drawString( stars + "/3 Stars" ,15, 80);
		g.drawString("Level "+ Spawn.getOutterLevel(), 15, 96);
		
		
	}
	
	public void reset() {
		HEALTH = 100;
		score = 0;
		innerLevel = 0;
		stars = 0;
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








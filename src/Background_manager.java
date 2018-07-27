import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BufferCapabilities.FlipContents;
import java.util.Random;

import javax.swing.ImageIcon;


public class Background_manager  {
	
	private Game game;
	private STATE gameState = null;
	private boolean LetsChangeBackground = true ;
	
	
	private Image background_lvl_1= new ImageIcon("res/background_lvl_1.jpg").getImage();
	private Image background_lvl_2= new ImageIcon("res/background_lvl_2.jpg").getImage();
	private Image background_lvl_3= new ImageIcon("res/background_lvl_3.jpg").getImage();
	private Image background_lvl_4= new ImageIcon("res/background_lvl_4.jpg").getImage();
	private Image background_lvl_5= new ImageIcon("res/background_lvl_5.jpg").getImage();

	

	public Background_manager(Game game) {
		
		this.game = game;
		
		
		
	}

	
	
	

	
	public void render(Graphics g) {
		
		if(game.gameState == STATE.Game) {
			if( Spawn.getOutterLevel() == 1) {
				g.drawImage(background_lvl_1,0,0,null);
			}
			else if(Spawn.getOutterLevel() == 2) {
				g.drawImage(background_lvl_2,0,0,null);
			} 
			else if( Spawn.getOutterLevel() == 3) {
				g.drawImage(background_lvl_3,0,0,null);
			} 
			else if( Spawn.getOutterLevel() == 4) {
				g.drawImage(background_lvl_4,0,0,null);
			} 
			else if( Spawn.getOutterLevel() == 5) {
				g.drawImage(background_lvl_5,0,0,null);
			} 
		}
		
		
	}
	
}

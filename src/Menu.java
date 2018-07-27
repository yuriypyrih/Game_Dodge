import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.ImageIcon;


public class Menu extends MouseAdapter implements MouseMotionListener {
	
	private Game game;
	private Handler handler;
	private Spawn spawner;
	private HUD hud;
	
	
	private Image dodge_menu_img = new ImageIcon("res/dodge_menu.jpg").getImage();
	private Image dodge_menu_play_img = new ImageIcon("res/dodge_menu_play.jpg").getImage();
	private Image dodge_menu_options_img = new ImageIcon("res/dodge_menu_options.jpg").getImage();
	private Image dodge_menu_exit_img = new ImageIcon("res/dodge_menu_exit.jpg").getImage();
	private Image background_img = dodge_menu_img;
	
	private Image select_level_img = new ImageIcon("res/dodge_select_level.jpg").getImage();
	
	
	
	private Image easy_mode_img = new ImageIcon("res/easy_mode.jpg").getImage();
	private Image normal_mode_img = new ImageIcon("res/normal_mode.jpg").getImage();
	private Image hard_mode_img = new ImageIcon("res/hard_mode.jpg").getImage();
	
	

	public Menu(Game game, Handler handler, Spawn spawner, HUD hud) {
		
		this.game = game;
		this.handler = handler;
		this.spawner = spawner;
		this.hud = hud;
		
		
		
	}
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState==STATE.Menu) {
			
			/*PLAY button*/
			if(mouseOver(mx,my,220,130,195,82)) {
				game.gameState = STATE.Select;
				background_img = dodge_menu_img;
				
			}
			/*Difficulty button*/
			else if(mouseOver(mx,my,240,260,165,70)) {
				game.gameState = STATE.Difficulty;
				background_img = dodge_menu_img;
			
			}
			
			/*HELP button
			else if(mouseOver(mx,my,220,260,200,50)) {
				game.gameState = STATE.Help;
			
			}*/
			
			/*QUITE button*/
			else if(mouseOver(mx,my,260,375,130,57)) {
				System.exit(1);
			}
		}else if(game.gameState == STATE.Select) {
			
			
			/*LEVEL 1*/
			if(mouseOver(mx,my,40,115,90,90)){
				game.gameState = STATE.Game;
				spawner.startLevel(1);
			}
			/*LEVEL 2*/
			else if(mouseOver(mx,my,155,115,90,90)){
				game.gameState = STATE.Game;
				spawner.startLevel(2);
			}
			/*LEVEL 3*/
			else if(mouseOver(mx,my,270,115,90,90)){
				game.gameState = STATE.Game;
				spawner.startLevel(3);
			}
			/*LEVEL 4*/
			else if(mouseOver(mx,my,385,115,90,90)){
				game.gameState = STATE.Game;
				spawner.startLevel(4);
			
			}
			/*LEVEL 5*/
			else if(mouseOver(mx,my,495,115,90,90)){
				game.gameState = STATE.Game;
				spawner.startLevel(5);
				
			}
			/*LEVEL 6*/
			else if(mouseOver(mx,my,40,230,90,90)){
				//game.gameState = STATE.Game;
				//spawner.startLevel(6);
				
			}
			/*LEVEL 7*/
			else if(mouseOver(mx,my,155,225,90,90)){
				//game.gameState = STATE.Game;
				//spawner.startLevel(7);
				
			}
			/*LEVEL 8*/
			else if(mouseOver(mx,my,270,225,90,90)){
				//game.gameState = STATE.Game;
				//spawner.startLevel(8);
				
			}
			/*LEVEL 9*/
			else if(mouseOver(mx,my,382,225,90,90)){
				//game.gameState = STATE.Game;
				//spawner.startLevel(9);
				
			}
			/*LEVEL 10*/
			else if(mouseOver(mx,my,497,225,90,90)){
				//game.gameState = STATE.Game;
				//spawner.startLevel(10);
				
			}
			/*LEVEL 11*/
			else if(mouseOver(mx,my,40,340,90,90)){
				//game.gameState = STATE.Game;
				//spawner.startLevel(11);
				
			}
			/*LEVEL 12*/
			else if(mouseOver(mx,my,153,340,90,90)){
				//game.gameState = STATE.Game;
				//spawner.startLevel(12);
				
			}
			/*LEVEL 13*/
			else if(mouseOver(mx,my,270,340,90,90)){
				//game.gameState = STATE.Game;
				//spawner.startLevel(13);
				
			}
			/*BACK button*/
			else if(mouseOver(mx,my,400,345,180,80)){
				game.gameState = STATE.Menu;
			}
		}else if(game.gameState == STATE.Difficulty) {
			/*EASY*/
			if(mouseOver(mx,my,220,120,200,50)){
				spawner.difficulty = 0;
			}
			/*NORMAL*/
			else if(mouseOver(mx,my,220,220,200,50)){
				spawner.difficulty = 1;
			}
			/*HARD*/
			else if(mouseOver(mx,my,220,330,200,50)){
				spawner.difficulty = 2;
			}
			/*BACK button*/
			else if(mouseOver(mx,my,460,390,150,40)){
				hud.insertDiff(spawner.difficulty);
				game.gameState = STATE.Menu;
			}
		}
		else if(game.gameState == STATE.Help) {
			if(mouseOver(mx,my,460,390,150,40)){
				game.gameState = STATE.Menu;
			}
		}else if(game.gameState == STATE.Victory) {
			/*NEXT LEVEL*/
			if(mouseOver(mx,my,210,200,200,50)){
				if(spawner.nextLevel()) game.gameState = STATE.Game;
			}
			
			/* BACK BUTTON*/
			else if(mouseOver(mx,my,460,390,150,40)){
				game.gameState = STATE.Menu;
			}
		}
		else if(game.gameState == STATE.FinalVictory) {
			/* BACK BUTTON*/
			if(mouseOver(mx,my,460,390,150,40)){
				game.gameState = STATE.Menu;
			}
		}
		else if(game.gameState == STATE.End) {
			if(mouseOver(mx,my,460,390,150,40)){
				game.gameState = STATE.Menu;
			}
			/*PLAY AGAIN*/
			else if(mouseOver(mx,my,210,200,200,50)){
				 game.gameState = STATE.Game;
				 spawner.startLevel(0);
			}
			/* BACK BUTTON*/
			else if(mouseOver(mx,my,460,390,150,40)){
				game.gameState = STATE.Menu;
			}
		}
		
	}
	
	
	public void mouseReleassed(MouseEvent e) {
		
	}
	
	
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState==STATE.Menu) {
			
			/*PLAY button*/
			if(mouseOver(mx,my,220,130,195,82)) {
				background_img = dodge_menu_play_img;
			}
			/*Difficulty button*/
			else if(mouseOver(mx,my,240,260,165,70)) {
				background_img = dodge_menu_options_img;
			}
			/*QUITE button*/
			else if(mouseOver(mx,my,260,375,130,57)) {
				background_img = dodge_menu_exit_img;
			}
			/*IF NOT THEN THE DEFAULT BACKGROUND*/
			else {
				background_img = dodge_menu_img;
			}
		}
	}
	

	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		
		if(mx > x && mx < x + width) {
			if (my > y && my<y + height) {
				return true;
			}else {
				return false;
			}
		}else return false;
	}
	
	public void tick() {
		
		
	}
	
	public void render(Graphics g) {
		Font fnt1 = new Font("arial",1,50);
		Font fnt2 = new Font("arial",1,30);
		Font fnt3 = new Font("arial",1,18);
		Font fnt4 = new Font("arial",1,14);
		Font fntItalic = new Font ("arial", Font.ITALIC, 25);
		g.setColor(Color.white);
		
		if(game.gameState==STATE.Menu) {
				
			g.drawImage(background_img,0,0,null);
		
		}
		else if(game.gameState==STATE.Select) {
			
			g.drawImage(select_level_img,0,0,null);
			
		}
		else if(game.gameState==STATE.Difficulty) {
			
			
			g.setColor(Color.red);
			if(spawner.difficulty == 0) {
				g.drawRect(218,118,204,54);
				g.setColor(new Color(38,38,38));
				g.fillRect(220,120,200,50);
			}
			else if(spawner.difficulty == 1) {
				g.drawRect(218,218,204,54);
				g.setColor(new Color(38,38,38));
				g.fillRect(220,220,200,50);
			}
			else if(spawner.difficulty == 2) {
				g.drawRect(218,318,204,54);
				g.setColor(new Color(38,38,38));
				g.fillRect(220,320,200,50);
			}
			
			g.setColor(Color.white);
			g.setFont(fnt1);
			g.drawString("Difficulty", 220, 70);
			
			g.setFont(fnt2);
			g.drawRect(220,120,200,50);
			g.drawString("Easy", 280, 160);
			
			g.drawRect(220,220,200,50);
			g.drawString("Normal", 270, 260);
			
			g.drawString("Hard", 280, 360);
			g.drawRect(220,320,200,50);
			
			g.drawString("Back", 490, 420);
			g.drawRect(460,390,150,40);
		}
		else if(game.gameState==STATE.Help) {
			
			g.setFont(fnt3);
			g.drawString("Use WSAD to avoid enemies and collect the 3 stars to win each level.", 18, 50);
			g.drawString("Your goal?  to survive..", 220, 75);
			
			g.setFont(fnt4);
			g.drawString("Demo for personal use only.", 10, 400);
			g.drawString("Created entirely by Yuriy Pyrih", 10, 420);
			g.drawString("Muisc: ColBreakz - Arcade ", 10, 440);
			
			g.setFont(fnt2);
			g.drawString("Enemies", 25, 120);
			g.drawString("Objects", 420, 120);
			
			g.setFont(fntItalic);
			
			g.setColor(Color.red);
			g.fillRect(28, 146, 16, 16);
			g.setColor(Color.white);
			g.drawString("Scout",60,160);
			
			g.setColor(Color.cyan);
			g.fillRect(28, 186, 16, 16);
			g.setColor(Color.white);
			g.drawString("Speeder",60,200);
			
			g.setColor(Color.orange);
			g.fillRect(28, 226, 16, 16);
			g.setColor(Color.white);
			g.drawString("Tracer",60,240);
			
			g.setColor(Color.pink);
			g.fillRect(28, 266, 16, 16);
			g.setColor(Color.white);
			g.drawString("Worm",60,280);
			
			g.setColor(new Color(153, 0, 204));
			g.fillRect(28, 306, 16, 16);
			g.setColor(Color.white);
			g.drawString("Virus",60,320);
			
			g.setColor(Color.yellow);
			g.fillRect(420, 140, 16, 16); //x,y,width,height
			g.setColor(Color.black);
			g.fillRect(420, 140, 16, 2);
			g.fillRect(420, 140+2, 6, 2);
			g.fillRect(420, 140+4, 4, 2);
			g.fillRect(420, 140+6, 2, 4);
			g.fillRect(420, 140+10, 4, 2);
			g.fillRect(420, 140+12, 6, 2);
			g.fillRect(420, 140+14, 16, 2);
			g.fillRect(420+10, 140+2, 6, 2);
			g.fillRect(420+12, 140+4, 4, 2);
			g.fillRect(420+14, 140+6, 2, 4);
			g.fillRect(420+12, 140+10, 4, 2);
			g.fillRect(420+10, 140+12, 6, 2);
			g.setColor(Color.yellow);
			g.drawOval(420-2, 140-3, 20, 20);
			g.setColor(Color.white);
			g.drawString("Star", 450, 156);
			
			g.setColor(Color.green);
			g.fillRect(420, 170, 16, 16);
			g.setColor(Color.black);
			g.fillRect(420, 170, 5, 5);
			g.fillRect(420+11, 170, 5, 5);
			g.fillRect(420, 170+11, 5, 5);
			g.fillRect(420+11, 170+11, 5, 5);
			g.setColor(Color.white);
			g.drawString("Health Pack", 450, 186);
			
			g.setColor(Color.green);
			g.fillRect(420, 200, 16, 16);
			g.setColor(Color.black);
			g.fillRect(420, 200, 5, 5);
			g.fillRect(420+11, 200, 5, 5);
			g.fillRect(420, 200+11, 5, 5);
			g.fillRect(420+11, 200+11, 5, 5);
			g.setColor(Color.red);
			g.fillRect(420 +6,200, 4, 16);
			g.fillRect(420, 200+ 6, 16, 4);
			g.drawOval(420-4, 200-4, 23, 22);
			g.setColor(Color.white);
			g.drawString("Death Trap", 450, 216);
			
			
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Back", 490, 420);
			g.drawRect(460,390,150,40);
		}else if(game.gameState == STATE.Victory) {
			g.setFont(fnt2);
			g.drawString("Congratz!", 240, 160);
			
			g.drawString("Next level", 240, 235);
			g.drawRect(210,200,200,50);
			
			g.drawString("Back", 490, 420);
			g.drawRect(460,390,150,40);
		}
		else if(game.gameState == STATE.FinalVictory) {
			g.setFont(fnt2);
			
			if(spawner.getDifficulty()== 0) {
				g.drawString("GG EZ!", 270, 60);
				g.drawImage(easy_mode_img,195,70,null);
				g.setFont(fntItalic);
				g.drawString("\"Well... it's easy mode anyway\"", 150, 280);
			}
			else if(spawner.getDifficulty()== 1) {
				g.drawString("Good Job!", 240, 60);
				g.drawImage(normal_mode_img,195,70,null);
				g.setFont(fntItalic);
				g.drawString("\"Try hard mode, just to be sure..\"", 140, 280);
			}
			else if(spawner.getDifficulty()== 2) {
				g.drawString("Now we're talking!", 190, 60);
				g.drawImage(hard_mode_img,195,70,null);
				g.setFont(fntItalic);
				g.drawString("\"I knew I should have made it harder\"", 120, 280);
				
			}
			
			g.setFont(fnt2);
			g.drawString("Back", 490, 420);
			g.drawRect(460,390,150,40);
		}
		else if(game.gameState == STATE.End) {
			
			g.setFont(fnt2);
			g.drawString("Game Over!", 230, 160);
			
			g.drawString("Play Again", 240, 235);
			g.drawRect(210,200,200,50);
			
			g.drawString("Back", 490, 420);
			g.drawRect(460,390,150,40);
		}
	}
	
}

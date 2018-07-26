import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.ImageIcon;


public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private Spawn spawner;
	private HUD hud;
	
	private Image background_img = new ImageIcon("res/background_img.jpg").getImage();
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
			if(mouseOver(mx,my,220,100,200,50)) {
				game.gameState = STATE.Select;
				
			}
			/*Difficulty button*/
			else if(mouseOver(mx,my,220,180,200,50)) {
				game.gameState = STATE.Difficulty;
			
			}
			/*HELP button*/
			else if(mouseOver(mx,my,220,260,200,50)) {
				game.gameState = STATE.Help;
			
			}
			/*QUITE button*/
			else if(mouseOver(mx,my,220,340,200,50)) {
				System.exit(1);
			}
		}else if(game.gameState == STATE.Select) {
			/*LEVEL 1*/
			if(mouseOver(mx,my,70,120,50,50)){
				game.gameState = STATE.Game;
				spawner.startLevel(1);
			}
			/*LEVEL 2*/
			else if(mouseOver(mx,my,170,120,50,50)){
				game.gameState = STATE.Game;
				spawner.startLevel(2);
			}
			/*LEVEL 3*/
			else if(mouseOver(mx,my,270,120,50,50)){
				game.gameState = STATE.Game;
				spawner.startLevel(3);
			}
			/*LEVEL 4*/
			else if(mouseOver(mx,my,370,120,50,50)){
				game.gameState = STATE.Game;
				spawner.startLevel(4);
			
			}
			/*LEVEL 5*/
			else if(mouseOver(mx,my,470,120,50,50)){
				game.gameState = STATE.Game;
				spawner.startLevel(5);
				
			}
			/*BACK button*/
			else if(mouseOver(mx,my,460,390,150,40)){
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
			
			g.setFont(fnt1);
			g.drawString("Menu", 250, 70);
			
			g.setFont(fnt2);
			g.drawRect(220,100,200,50);
			g.drawString("Play", 280, 140);
			
			g.drawRect(220,180,200,50);
			g.drawString("Difficulty", 260, 220);
			
			g.drawRect(220,260,200,50);
			g.drawString("What?", 280, 300);
			
			g.drawString("Quit", 280, 380);
			g.drawRect(220,340,200,50);
			
		
		}
		else if(game.gameState==STATE.Select) {
			g.setFont(fnt1);
			g.drawString("Select Level", 170, 70);
			
			g.setFont(fnt2);
			g.setColor(Color.red);
			g.fillRect(70,120,50,50);
			g.setColor(Color.white);
			g.drawRect(70,120,50,50);
			g.drawString("1", 85, 160);
			//g.setFont(fnt3);
			//g.drawString("Scout",70, 190);
			//g.setFont(fnt2);
			
			g.setColor(new Color(0,139,139));
			g.fillRect(170,120,50,50);
			g.setColor(Color.white);
			g.drawRect(170,120,50,50);
			g.drawString("2", 185, 160);
			//g.setFont(fnt3);
			//g.drawString("Speeder", 160, 190);
			//g.setFont(fnt2);
			
			g.setColor(Color.orange);
			g.fillRect(270,120,50,50);
			g.setColor(Color.white);
			g.drawString("3", 285, 160);
			g.drawRect(270,120,50,50);
			//g.setFont(fnt3);
			//g.drawString("Tracer", 270, 190);
			//g.setFont(fnt2);
			
			g.setColor(Color.pink);
			g.fillRect(370,120,50,50);
			g.setColor(Color.gray);
			g.drawString("4", 385, 160);
			g.setColor(Color.white);
			g.drawRect(370,120,50,50);
			//g.setFont(fnt3);
			//g.drawString("Worm", 370, 190);
			//g.setFont(fnt2);
			
			g.setColor(new Color(153, 0, 204));
			g.fillRect(470,120,50,50);
			g.setColor(Color.white);
			g.drawString("5", 485, 160);
			g.drawRect(470,120,50,50);
			//g.setFont(fnt3);
			//g.drawString("Virus", 470, 190);
			//g.setFont(fnt2);
			
			
			g.drawString("Back", 490, 420);
			g.drawRect(460,390,150,40);
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
			g.drawString("Use WSAD to avoid enemies and collect the 3 starts to win each level.", 18, 50);
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

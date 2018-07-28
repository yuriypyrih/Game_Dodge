import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private Game game;
	
	private boolean[] keyDown = new boolean[4];
	
	//Constructor for KeyInput class
	public KeyInput(Handler handler, Game game ) {
		this.handler = handler;
		this.game = game;
		
		
		keyDown[0] = false; //W
		keyDown[1] = false; //S
		keyDown[2] = false; //A
		keyDown[3] = false; //D
	}
	
	/*It handles every key event on the keyboard*/
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//key events for player
				
				if(key == KeyEvent.VK_W) {tempObject.setVelY(-6); keyDown[0] = true;}
				if(key == KeyEvent.VK_S) {tempObject.setVelY(6);  keyDown[1] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-6); keyDown[2] = true;}
				if(key == KeyEvent.VK_D) {tempObject.setVelX(6);  keyDown[3] = true;}
				
				
			}
			
		}
		
	}//end of keyPressed method
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//key events for player
				
				if(key == KeyEvent.VK_W) keyDown[0]=false;//tempObject.setVelY(0); 
				if(key == KeyEvent.VK_S) keyDown[1]=false; //tempObject.setVelY(0); 
				if(key == KeyEvent.VK_A) keyDown[2]=false; //tempObject.setVelX(0); 
				if(key == KeyEvent.VK_D) keyDown[3]=false; //tempObject.setVelX(0); 
				
				//Vertical Movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				
				//Horizontal Movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
			
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			if(game.gameState == STATE.Select) {
				game.gameState = STATE.Menu;
			}
			else if(game.gameState == STATE.Difficulty) {
				game.gameState = STATE.Menu;
			}	
			else if(game.gameState == STATE.Game) {
				game.gameState = STATE.Pause;
			}
			else if(game.gameState == STATE.Pause) {
				game.gameState = STATE.Game;
			}
			else if(game.gameState == STATE.Menu) {
				System.exit(1);
			}
		}
		
	}//end of keyReleased Method

}//end of KeyInput class

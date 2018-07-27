import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -1442798787354930462L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; 
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Random r; 
	private HUD hud;//you may want to fix/delete this health bar later...
	private Spawn spawner;
	private Menu menu;
	private Background_manager BG_manager;
	
	
	public STATE gameState = STATE.Menu;
	
	//Contructor fot the Game class
	public Game() {
		
		//Here we create a Handler object that late will be passed to KeyInput
		handler = new Handler();
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		menu = new Menu(this,handler,spawner, hud);
		BG_manager = new Background_manager(this);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		
	
		AudioPlayer.load();
		
		AudioPlayer.getMusic("music").loop();
		AudioPlayer.getMusic("music").setVolume(0.1f);
		
		new Window(WIDTH, HEIGHT,"Mini Game",this);
		
		
		r = new Random();
		
		
	}

	//The method that start the thread?
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join(); 		//killing the thread
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//It was copy-pasted.. except the requestFocus()
	public void run() {
		this.requestFocus();
		/*this.requestFocus() means that you don't have to click on the window to have access to the keyboard
		 * as the game starts, so it's much easier than before.*/
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			if(System.currentTimeMillis() - timer >1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	//frame?
	private void tick() {
		//update each bevarior per frame
	
		if(gameState== STATE.Game) {
			handler.tick();
			spawner.tick();
			hud.tick();
			
			
			//INSIDE OF THE GAME CHANGING STATES
			if(hud.HEALTH<=0) {
				gameState = STATE.End;
				handler.clearGame();
				handler.addObject(new WarningEffect(1,handler));
				
			
			}else if (spawner.Victory()) {
				if(spawner.getOutterLevel() != 5) {
					gameState = STATE.Victory;
				}else {
					gameState = STATE.FinalVictory;
				}
				handler.clearGame();
			}
		}else if(gameState == STATE.Menu || gameState == STATE.Select || gameState == STATE.Difficulty 
				|| gameState == STATE.Help || gameState == STATE.Victory || gameState == STATE.FinalVictory|| gameState == STATE.End ) {
			handler.tick();
			menu.tick();
		}
		

	}
	
	//per frame?
	private void render() {
		//update each appearance per frame
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		BG_manager.render(g);
		handler.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Select || gameState == STATE.Difficulty  || gameState == STATE.Help
				|| gameState == STATE.Victory || gameState == STATE.FinalVictory || gameState == STATE.End) {
			BG_manager.render(g);
			menu.render(g);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	
	
	//Helpful method for our (collision effect) game
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();

	}

}

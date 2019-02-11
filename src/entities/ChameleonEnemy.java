package entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import engine.AudioPlayer;
import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;
import engine.WarningEffect;

public class ChameleonEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	private int chameleon_timer = -40; // preset so it will wait 
	private int bulletTimer = 0;
	
	private float shadowAlpha = 1;
	private boolean goStealth = true;
	private int stealthTimer = 0;
	
	private int width = 16 , height = 16;
	
	private Random r = new Random();
	private int chameleon_form = 0;
	private boolean just_changed_form = false;
	
	private Color chameleon_color = Color.WHITE;
	private Color light_pink = new Color(255,153,255);
	private Color virus_purple = new Color(153, 0, 204);
	private Color Brown = new Color(201,112,48);
	private Color void_color = new Color(77, 77, 255);
	
	public ChameleonEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			//code for getting the position of player
			for(int i=0 ; i<handler.getObjectList().size(); i++) {
				if(handler.getObjectList().get(i).getId() == ID.Player) player = handler.getObjectList().get(i);
			}
			
			velY = 5;
			velX = 5;
	}
	
	public ChameleonEnemy(float  x, float  y, ID id, int velX,int velY, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		
		this.velY = velY;
		this.velX = velX;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
	}
	
	private void check_For_Health_Packs() {
		for(int i = 0; i < handler.getObjectList().size(); i++) {
			GameObject tempObject = handler.getObjectList().get(i);
			
			if(tempObject.getId() == ID.Healer) {
				handler.addObject( new VirusBullet(x,y,ID.VirusBullet,handler));
				return;
			}
		}
	}
	
	
	
	public void tick() {
		
		
		chameleon_timer++ ;
		if(chameleon_timer >= 200 && shadowAlpha >= 0.9f) {
			shadowAlpha = 1;
			chameleon_form = r.nextInt(10) + 1;
			just_changed_form = true;
			chameleon_timer = 0;
			System.out.println("Chameleon form " + chameleon_form);
		}
		
			
		//that's HOW OUR ENEMY BEHAVES
		if(chameleon_form == 0) {
			handler.addObject(new Trail(x, y, ID.Trail, Color.WHITE, width, height, 0.04f, handler));
			chameleon_color = Color.WHITE;
			x+=velX;
			y+=velY;
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
		}
		else if(chameleon_form == 1) { // BasicEnemy form

			handler.addObject(new Trail(x, y, ID.Trail, Color.RED, width, height, 0.04f, handler));
			

			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			x+=velX;
			y+=velY;
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
		}
		else if(chameleon_form == 2) { //FastEnemy form
			chameleon_color = Color.CYAN;
			handler.addObject(new Trail(x, y, ID.Trail, chameleon_color, width, height, 0.04f, handler));
			

			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			if( velX != 1 && velX != -1) {
				if( velX < 0) velX = -1;
				else velX = 1;	
			}
			
			if( velY != 10 && velY != -10) {
				if(velY < 0) velY = - 10;
				else velY = 10;	
			}
				
			
			x+=velX;
			y+=velY;
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
		}
		else if(chameleon_form == 3) { //SmartEnemy form
			chameleon_color = Color.ORANGE;
			handler.addObject(new Trail(x, y, ID.Trail, chameleon_color, width, height, 0.04f, handler));
			
			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			x += velX;
			y += velY;
			
			float diffY = y - player.getY() -16;
			float diffX = x - player.getX() -16;
			float distance = (float) Math.sqrt( (x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY())  );
			
			velX = ((-2/distance) * diffX);
			velY = ((-2/distance) * diffY);
		}
		else if(chameleon_form == 4) { //WormEnemy form
			chameleon_color = light_pink;
			handler.addObject(new Trail(x, y, ID.Trail, chameleon_color, width, height, 0.04f, handler));
			
			if(width > 30 || height > 30){
				width--;
				height--;
			}else if(width < 30 || height < 30){
				width++;
				height++;
			}
			
			x+=velX;
			y+=velY;
			
			//Collision
			if(y <= 0 ) {
				y++;
				velY = 0;
				velX = -10;
			}
			else if(x <= 0 ) {
				x++;
				velY = 10;
				velX = 0;
			}
			else if( y >= Game.HEIGHT - width*2 ) {
				y--;
				velY = 0;
				velX = 10;
			}
			else if( x >= Game.WIDTH - (width + 5)) {
				x--;
				velY = -10;
				velX = 0;
			}
		}
		else if(chameleon_form == 5) { //VirusEnemy form
			chameleon_color = virus_purple;
			handler.addObject(new Trail(x, y, ID.Trail, chameleon_color, width, height, 0.04f, handler));
			
			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			x+=velX;
			y+=velY;
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			bulletTimer++;
			
			if(bulletTimer >100) {
				bulletTimer = 0;
				check_For_Health_Packs() ;
				
			}
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT - width*2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
			
		}
		else if(chameleon_form == 6) { //BouncerForm form
			chameleon_color = Color.green;
			handler.addObject(new Trail(x, y, ID.Trail, chameleon_color, width, height, 0.04f, handler));
			
			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			x+=velX;
			y+=velY;
			
			velY += 0.11f;
			
			//Collision
			if( y >= Game.HEIGHT - width * 2) velY = -9; 
			if( y <= 0) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
			
		}
		else if(chameleon_form == 7) { //ExplosiveEnemy form
			chameleon_color = Brown;
			handler.addObject(new Trail(x, y, ID.Trail, chameleon_color, width, height, 0.04f, handler));
			
			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			
			//that's HOW OUR ENEMY BEHAVES
			x+=velX;
			y+=velY;
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT -  width * 2) {
				handler.addObject(new ExplosiveBoom(x+16,y+16,ID.ExplosiveBoom, 0.2f, handler));
				velY *= -1; 
			}
			if(x <= 0 || x >= Game.WIDTH - width) {
				handler.addObject(new ExplosiveBoom(x+16,y+16,ID.ExplosiveBoom, 0.2f, handler));
				velX *= -1;
			}
			
		}
		else if(chameleon_form == 8) { //VenomEnemy form
		
			if(width > 16 || height > 16){
				width--;
				height--;
			}
		
			if(just_changed_form == true) {
				AudioPlayer.getSound("sound_snake").play(1.2f,0.4f);
				just_changed_form = false;
			}
			
			handler.addObject(new Trail(x, y, ID.VenomTrail, Color.white, new Color (20, 0, 20), width, height, 0.025f, handler));
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			
			//that's HOW OUR ENEMY BEHAVES
			x+=velX;
			y+=velY;
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT - width * 2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
			
		}
		else if(chameleon_form == 9) { //VoidEnemy form
			handler.addObject(new Trail(x, y, ID.Trail, void_color, width, height, 0.04f, handler));
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			//Collision
			if(y <= 0 && velY< 0) {
				width += 12;
				height += 12;
				velY *= -1 ; 
			}
			if(y >= Game.HEIGHT - (height+32) && velY>0) {
				width += 12;
				height += 12;
				velY *= -1 ;
			}
			if(x <= 0 && velX< 0) {
				width += 12;
				height += 12;
				velX *= -1;
			}
			if(x >= Game.WIDTH - (width+16) && velX>0) {
				width += 12;
				height += 12;
				velX *= -1;
			}
			
		
			
			
			//that's HOW OUR ENEMY BEHAVES
			x+=velX;
			y+=velY;
			
			
		}
		else if(chameleon_form == 10) { // ShadowEnemy form
			
			handler.addObject(new Trail(x, y, ID.Trail, Color.gray, width, height, 0.03f,shadowAlpha, handler));
			

			if(width > 16 || height > 16){
				width--;
				height--;
			}
			
			if( velX != 5 && velX != -5) {
				if( velX < 0) velX = -5;
				else velX = 5;	
			}
			
			if( velY != 5 && velY != -5) {
				if(velY < 0) velY = - 5;
				else velY = 5;	
			}
			
			x+=velX;
			y+=velY;
			
			stealthTimer++;
			
			if(goStealth && stealthTimer > 10) {
				shadowAlpha -= 0.01f;
				if(shadowAlpha<0) {
					shadowAlpha = 0;
					stealthTimer = 0;
					goStealth = false;
				}
			}else if(!goStealth && stealthTimer > 10){
				shadowAlpha += 0.01f;
				if(shadowAlpha>1) {
					shadowAlpha = 1;
					stealthTimer = 0;
					goStealth = true;
				}
			}
			
			//Collision
			if(y <= 0 || y >= Game.HEIGHT - width * 2) velY *= -1; 
			if(x <= 0 || x >= Game.WIDTH - width) velX *= -1;
		}
		
		
		
	  
	}//end of tick()
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		
		
	}
}

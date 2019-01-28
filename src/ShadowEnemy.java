import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ShadowEnemy extends GameObject{
	
	private Handler handler;
	private float shadowAlpha = 1;
	private boolean goStealth = true;
	private int stealthTimer = 0;
	
	public ShadowEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 5;
			velX = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		handler.addObject(new Trail(x, y, ID.Trail, Color.gray, 16, 16, 0.03f,shadowAlpha, handler));
		//that's HOW OUR ENEMY BEHAVES
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
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	  
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		//g.setColor(Color.red);
		//g.fillRect((int)x,(int) y, 16, 16);
	}
}

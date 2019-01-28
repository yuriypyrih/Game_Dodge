
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class VoidEnemy extends GameObject{
	
	private Handler handler;
	private int width = 5 , height = 5, growingTimer = 0;
	
	
	private Color color_4 = new Color(77, 77, 255);
	
	public VoidEnemy(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			
			velY = 7;
			velX = 7;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
	}
	
	public void tick() {
		handler.addObject(new Trail(x, y, ID.Trail, color_4, width, height, 0.1f, handler));
		//that's HOW OUR ENEMY BEHAVES
		
		
		growingTimer++;
		
		
		
		
	  
		if(growingTimer > 20 && width < 300) {
			width += 1;
			height += 1;
			velY -= 0.2f;
			velX -= 0.2f;
			
			growingTimer = 0;
		}
		
		
		//Collision
				if(y <= 0 && velY< 0) velY *= -1 ; 
				if(y >= Game.HEIGHT - (height+32) && velY>0) velY *= -1 ;
				if(x <= 0 && velX< 0) velX *= -1;
				if(x >= Game.WIDTH - (width+16) && velX>0) velX *= -1;
				
				x+=velX;
				y+=velY;
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		
	}
}

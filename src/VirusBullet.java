
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class VirusBullet extends GameObject{
	
	private Handler handler;
	
	public VirusBullet(float  x, float  y, ID id, Handler handler) {
			super(x, y, id);
			
			this.handler = handler;
			
			check_For_Health_Packs();
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	private void check_For_Health_Packs() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Healer) {
				float diffY = y - tempObject.getY() -8;
				float diffX = x - tempObject.getX() -8;
				float distance = (float) Math.sqrt( (x-tempObject.getX())*(x-tempObject.getX()) + (y-tempObject.getY())*(y-tempObject.getY())  );
				velX = ((-5/distance) * diffX);
				velY = ((-5/distance) * diffY);
				return;
			}
		}
	}
	
	public void tick() {
		
		//that's HOW OUR ENEMY BEHAVES
		x+=velX;
		y+=velY;
		
		//Collision

		//Collision
		if(y <= 0 || y >= Game.HEIGHT - 32) handler.removeObject(this); 
		if(x <= 0 || x >= Game.WIDTH - 16) handler.removeObject(this);
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Healer) {
				if(getBounds().intersects(tempObject.getBounds())) {	//intersects() is a method of Rectangle library
					handler.addObject( new DeathTrap(tempObject.getX(),tempObject.getY(),ID.DeathTrap,handler));
					handler.removeObject(tempObject);
					handler.removeObject(this);
				}
			}
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, new Color(204, 153, 255), 8, 8, 0.04f, handler));
	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 8, 8);
	}
}

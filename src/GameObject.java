import java.awt.Graphics;
import java.awt.Rectangle;

/*Everything in our game should be considered a GameObject
 * That's why is an abstract class
 * and therefore it will be inhretited by other types of mobs
 */

public abstract class GameObject {
	
	protected float  x, y;
	protected ID id;
	protected float  velX, velY;
	
	/*Contructor for GameObject , x and y are the coordinates of where it should be placed 
	 * and id indicates what type of mob this object is */
	public GameObject(float  x, float  y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();		//Hitbox
	
	/*Some basics methods to be inherited*/
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public float getVelX() {
		return velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public float getVelY() {
		return velY;
	}
	
	
	
	
	
	
}//end of GameObject class

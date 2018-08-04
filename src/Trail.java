import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{
	
	private float alpha = 1;
	
	private Handler handler;
	private Color color;
	private Color color2;
	
	private int width, height;
	private float life;
	
	//life = 0.001 BIG to 0.1 SMALL, 0.04f
	
	
	public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color ;
		this.color2 = null;
		this.width = width;
		this.height = height;
		this.life = life;
		
	}
	
	//SHADOW TRAIL
	public Trail(float x, float y, ID id, Color color, int width, int height, float life, float shadowAlpha, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color ;
		this.color2 = null;
		this.width = width;
		this.height = height;
		this.life = life;
		this.alpha = shadowAlpha;
		
	}
	
	//MULTIPLE COLOR TRAIL
	public Trail(float x, float y, ID id, Color color1,Color color2, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color1;
		this.color2 = color2;
		this.width = width;
		this.height = height;
		this.life = life;
		
		
	}
	
	public void tick() {
		if(alpha >life) {
			alpha -= life - 0.01f ;
		}else handler.removeObject(this);
		
	}
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		if(color2 == null) {
			g.setColor(color);
			g.fillRect((int)x, (int)y, width, height);
			
			
		}else {
			

			g.setColor(color);// OUTSIDE COLOR
			g.fillRect((int)x, (int)y, width, height);
			
			g.setColor(color2);//INSIDE COLOR
			g.fillRect((int)x+4, (int)y, width-8, height);
			g.fillRect((int)x, (int)y+4, width, height-8);
			
		
			
		}
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
		//g2d.setComposite(makeTransparent(1));
		
		
	}
	
	private AlphaComposite makeTransparent(float alhpa) { // the method that renders out the transparency 
		int type =  AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type,alpha));
	}
	
	public Rectangle getBounds() 
	{
		if(color2 == null) {
		return null;
		}else {
			return new Rectangle((int)x, (int)y, width, height);
		}
	}
	
}


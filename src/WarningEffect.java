import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


import javax.swing.ImageIcon;

public class WarningEffect extends GameObject{
	
	private float alpha = 0.06f;
	private int tripleWarning = 6; // 2 rounds per warning
	
	private Handler handler;
	
	private float life;
	private int whichWarning; // 0 = WARNING, 1 = GAME_OVER 
	
	private Image effect_img;
	
	//life = 0.001 BIG to 0.1 SMALL
	
	
	public WarningEffect(int whichWarning, Handler handler) {
		super(0, 0, ID.WarningEffect);
		this.whichWarning = whichWarning;
		
			if(whichWarning == 0) {
				effect_img = new ImageIcon("res/warning_red.png").getImage();
				alpha = 0.06f;
			}else if(whichWarning == 1) {
				effect_img = new ImageIcon("res/game_over_red.png").getImage();
				alpha = 1;
			}
		
		this.handler = handler;
		life = 0.05f;
		
		
		
	}
	
	public void tick() {
		/*RED WARNING*/
		if(whichWarning == 0) {
			if(tripleWarning > 0) {
				if(alpha >= 0.8 || alpha <= 0.01) {
					tripleWarning--;
					life *= -1;
				}
				alpha += life ;
				if(alpha >= 1) alpha = 1;
			
			}else handler.removeObject(this);
		}
		/*GAME OVER EFFECT*/
		else if(whichWarning == 1) {
			if(alpha >life) {
				alpha -= (life - 0.045f) ;
			}else handler.removeObject(this);
		}
		
	}
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		
		g.drawImage(effect_img, 0, 0, null);
		
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
		
		
	}
	
	private AlphaComposite makeTransparent(float alhpa) { // the method that renders out the transparency 
		int type =  AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type,alpha));
	}
	
	public Rectangle getBounds() {
		return null;
	}
	
}


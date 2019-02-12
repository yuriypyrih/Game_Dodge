package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.ImageIcon;

import engine.AudioPlayer;
import engine.GameObject;
import engine.Handler;
import engine.ID;

public class ChameleonChanging extends GameObject{
	
	private Handler handler;
	private GameObject boss;
	private float chameleon_changing_timer = 0;

	
	
	private Image chameleon_changing_img = new ImageIcon("res/chameleon_changing.png").getImage();
	
	
	
	
	public ChameleonChanging(float  x, float  y, ID id, GameObject boss, Handler handler) {
			super(x, y, id);
			
			//volume as 0.2f is more than enough, and 0.1f is quiter
			AudioPlayer.getSound("sound_chameleon").play(0.8f,0.15f);
			this.boss = boss;
			this.handler = handler;
			
			
	}
	
	public Rectangle getBounds() {
		return null;
	}
	
	
	
	public void tick() {
		//that's HOW OUR ENEMY BEHAVES
		
		chameleon_changing_timer += 0.5;
		

	}
	
	
	public void render(Graphics g) {
		//THAT'S HOW OUR ENEMY SHOULD LOOK LIKE
		
	
		if(chameleon_changing_timer<0.5) {
			// X1, Y1, X2, Y2
			
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					0,0,256,256,
					null);
			
		}
		else if(chameleon_changing_timer<1) {
			
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					256,0,512,256,
					null);
			
		}
		else if(chameleon_changing_timer<1.5) {
			
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					512,0,768,256,
					null);
			
		}
		else if(chameleon_changing_timer<2) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					768,0,1024,256,
					null);
		}else if(chameleon_changing_timer<2.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1024,0,1280,256,
					null);
		}
		else if(chameleon_changing_timer<3) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1280,0,1536,256,
					null);
		}
		else if(chameleon_changing_timer<3.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1536,0,1792,256,
					null);
		}
		else if(chameleon_changing_timer<4) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1792,0,2048,256,
					null);
		}
		else if(chameleon_changing_timer<4.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					0,256,256,512,
					null);
		}
		else if(chameleon_changing_timer<5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					256,256,512,512,
					null);
		}
		else if(chameleon_changing_timer<5.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					512,256,768,512,
					null);
		}
		else if(chameleon_changing_timer<6) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					768,256,1024,512,
					null);
		}
		else if(chameleon_changing_timer<6.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1024,256,1280,512,
					null);
		}
		else if(chameleon_changing_timer<7) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1280,256,1536,512,
					null);
		}
		else if(chameleon_changing_timer<7.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1536,256,1792,512,
					null);
		}
		else if(chameleon_changing_timer<8) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1792,256,2048,512,
					null);
		}
		else if(chameleon_changing_timer<8.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					0,512,256,768,
					null);
		}
		else if(chameleon_changing_timer<9) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					256,512,512,768,
					null);
		}
		else if(chameleon_changing_timer<9.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					512,512,768,768,
					null);
		}
		else if(chameleon_changing_timer<10) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					768,512,1024,768,
					null);
		}
		else if(chameleon_changing_timer<10.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1024,512,1280,768,
					null);
		}
		else if(chameleon_changing_timer<11) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1280,512,1536,768,
					null);
		}
		else if(chameleon_changing_timer<11.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1536,512,1792,768,
					null);
		}
		else if(chameleon_changing_timer<12) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1792,512,2048,768,
					null);
		}
		else if(chameleon_changing_timer<12.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					0,768,256,1024,
					null);
		}
		else if(chameleon_changing_timer<13) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					256,768,512,1024,
					null);
		}
		else if(chameleon_changing_timer<13.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					512,768,768,1024,
					null);
		}
		else if(chameleon_changing_timer<14) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					768,768,1024,1024,
					null);
		}
		else if(chameleon_changing_timer<14.5) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1024,768,1280,1024,
					null);
		}
		else if(chameleon_changing_timer<15) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1280,768,1536,1024,
					null);
		}
		else if(chameleon_changing_timer<15.4) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1536,768,1792,1024,
					null);
		}
		else if(chameleon_changing_timer<15.7) {
			g.drawImage(chameleon_changing_img,
					(int)boss.getX()-64, (int)boss.getY()-64, (int)boss.getX()+128, (int)boss.getY()+128,
					1792,768,2048,1024,
					null);
		}
		
		
		
		else if(chameleon_changing_timer>34) {
			handler.removeObject(this);
		}
		
		//g.setColor(Color.orange);
		//g.drawRect((int)x-117, (int)y-117,250,250);
		
		
	}
}

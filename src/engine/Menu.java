package engine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.ImageIcon;


public class Menu extends MouseAdapter implements MouseMotionListener {
	
	private Game game;
	private Handler handler;
	private Spawn spawner;
	private HUD hud;
	
	
	private Image dodge_menu_img = new ImageIcon("res/dodge_menu.jpg").getImage();
	private Image dodge_menu_play_img = new ImageIcon("res/dodge_menu_play.jpg").getImage();
	private Image dodge_menu_options_img = new ImageIcon("res/dodge_menu_options.jpg").getImage();
	private Image dodge_menu_exit_img = new ImageIcon("res/dodge_menu_exit.jpg").getImage();
	private Image background_img = dodge_menu_img;
	
	private Image dodge_select_level_img = new ImageIcon("res/dodge_select_level.jpg").getImage();
	private Image dodge_select_level_1_img = new ImageIcon("res/dodge_select_level_1.jpg").getImage();
	private Image dodge_select_level_2_img = new ImageIcon("res/dodge_select_level_2.jpg").getImage();
	private Image dodge_select_level_3_img = new ImageIcon("res/dodge_select_level_3.jpg").getImage();
	private Image dodge_select_level_4_img = new ImageIcon("res/dodge_select_level_4.jpg").getImage();
	private Image dodge_select_level_5_img = new ImageIcon("res/dodge_select_level_5.jpg").getImage();
	private Image dodge_select_level_6_img = new ImageIcon("res/dodge_select_level_6.jpg").getImage();
	private Image dodge_select_level_7_img = new ImageIcon("res/dodge_select_level_7.jpg").getImage();
	private Image dodge_select_level_8_img = new ImageIcon("res/dodge_select_level_8.jpg").getImage();
	private Image dodge_select_level_9_img = new ImageIcon("res/dodge_select_level_9.jpg").getImage();
	private Image dodge_select_level_10_img = new ImageIcon("res/dodge_select_level_10.jpg").getImage();
	private Image dodge_select_level_11_img = new ImageIcon("res/dodge_select_level_11.jpg").getImage();
	private Image dodge_select_level_12_img = new ImageIcon("res/dodge_select_level_12.jpg").getImage();
	private Image dodge_select_level_13_img = new ImageIcon("res/dodge_select_level_13.jpg").getImage();
	private Image dodge_select_level_back_img = new ImageIcon("res/dodge_select_level_back.jpg").getImage();
	private Image select_level_img = dodge_select_level_img;
	
	private Image info_icon_0 = new ImageIcon("res/info_icon_0.png").getImage();
	private Image info_icon_1 = new ImageIcon("res/info_icon_1.png").getImage();
	private Image info_icon = info_icon_0;
	
	private Image dodge_info_1 = new ImageIcon("res/dodge_info_1.jpg").getImage();
	private Image dodge_info_fix = new ImageIcon("res/dodge_info_1_fix.jpg").getImage();
	private Image dodge_info_1_back = new ImageIcon("res/dodge_info_1_back.jpg").getImage();
	private Image dodge_info_1_right = new ImageIcon("res/dodge_info_1_right.jpg").getImage();
	private Image dodge_info_1_img = dodge_info_1;
	
	private Image dodge_info_2 = new ImageIcon("res/dodge_info_2.jpg").getImage();
	private Image dodge_info_2_back = new ImageIcon("res/dodge_info_2_back.jpg").getImage();
	private Image dodge_info_2_left= new ImageIcon("res/dodge_info_2_left.jpg").getImage();
	private Image dodge_info_2_right = new ImageIcon("res/dodge_info_2_right.jpg").getImage();
	private Image dodge_info_2_img = dodge_info_2;
	
	private Image dodge_info_3 = new ImageIcon("res/dodge_info_3.jpg").getImage();
	private Image dodge_info_3_back = new ImageIcon("res/dodge_info_3_back.jpg").getImage();
	private Image dodge_info_3_left= new ImageIcon("res/dodge_info_3_left.jpg").getImage();
	private Image dodge_info_3_right = new ImageIcon("res/dodge_info_3_right.jpg").getImage();
	private Image dodge_info_3_img = dodge_info_3;
	
	private Image dodge_info_4 = new ImageIcon("res/dodge_info_4.jpg").getImage();
	private Image dodge_info_4_back = new ImageIcon("res/dodge_info_4_back.jpg").getImage();
	private Image dodge_info_4_left= new ImageIcon("res/dodge_info_4_left.jpg").getImage();
	private Image dodge_info_4_img = dodge_info_4;
	
	private Image star_indicator_1 = new ImageIcon("res/star_indicator_1.png").getImage();
	private Image star_indicator_2 = new ImageIcon("res/star_indicator_2.png").getImage();
	private Image star_indicator_3 = new ImageIcon("res/star_indicator_3.png").getImage();
	private Image coming_soon_img = new ImageIcon("res/coming_soon_img.png").getImage();
	private Image locker_img = new ImageIcon("res/locker_img.png").getImage();
	
	private Image paused_default_img = new ImageIcon("res/paused_img.png").getImage();
	private Image paused_resume_img = new ImageIcon("res/paused_resume_img.png").getImage();
	private Image paused_giveup_img = new ImageIcon("res/paused_giveup_img.png").getImage();
	private Image paused_img = paused_default_img;
	
	private Image dodge_settings_default = new ImageIcon("res/dodge_settings_default.jpg").getImage();
	private Image dodge_settings_diff = new ImageIcon("res/dodge_settings_diff.jpg").getImage();
	private Image dodge_settings_sound = new ImageIcon("res/dodge_settings_sound.jpg").getImage();
	private Image dodge_settings_controls = new ImageIcon("res/dodge_settings_controls.jpg").getImage();
	private Image dodge_settings_exit = new ImageIcon("res/dodge_settings_exit.jpg").getImage();
	private Image dodge_settings_img = dodge_settings_default;
	
	private Image settings_diff_normal = new ImageIcon("res/settings_diff_normal.png").getImage();
	private Image settings_diff_hard = new ImageIcon("res/settings_diff_hard.png").getImage();
	private Image settings_sound_0 = new ImageIcon("res/settings_sound_0.png").getImage();
	private Image settings_sound_1 = new ImageIcon("res/settings_sound_1.png").getImage();
	private Image settings_sound_2 = new ImageIcon("res/settings_sound_2.png").getImage();
	private Image settings_controls_arrows = new ImageIcon("res/settings_controls_arrows.png").getImage();
	private Image settings_controls_wasd = new ImageIcon("res/settings_controls_wasd.png").getImage();
	
	private Image victory_screen = new ImageIcon("res/victory_screen.jpg").getImage();
	private Image victory_screen_next = new ImageIcon("res/victory_screen_next.jpg").getImage();
	private Image victory_screen_exit = new ImageIcon("res/victory_screen_exit.jpg").getImage();
	private Image victory_screen_img = victory_screen;
	
	private Image ending_screen = new ImageIcon("res/ending_screen.jpg").getImage();
	private Image ending_screen_exit = new ImageIcon("res/ending_screen_exit.jpg").getImage();
	private Image ending_screen_img = ending_screen;
	
	private Image game_over = new ImageIcon("res/game_over.jpg").getImage();
	private Image game_over_again = new ImageIcon("res/game_over_again.jpg").getImage();
	private Image game_over_exit = new ImageIcon("res/game_over_exit.jpg").getImage();
	private Image game_over_img = game_over;
	
	

	public Menu(Game game, Handler handler, Spawn spawner, HUD hud) {
		
		this.game = game;
		this.handler = handler;
		this.spawner = spawner;
		this.hud = hud;
		
		
		
	}
	

	
	
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState==STATE.Menu) {
			
			/*PLAY button*/
			if(mouseOver(mx,my,220,130,195,82)) {
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Select;
				background_img = dodge_menu_img;
				
			}
			/*Difficulty button*/
			else if(mouseOver(mx,my,240,260,165,70)) {
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Settings;
				background_img = dodge_menu_img;
			
			}
			
			/*QUITE button*/
			else if(mouseOver(mx,my,260,375,130,57)) {
				System.exit(1);
			}
		}else if(game.gameState == STATE.Select) {
			
			
			/*LEVEL 1*/
			if(mouseOver(mx,my,40,115,90,90) && FileStore.conf[1] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_1");
				spawner.startLevel(1);
			}
			/*LEVEL 2*/
			else if(mouseOver(mx,my,155,115,90,90) && FileStore.conf[2] != '-' ){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_2");
				spawner.startLevel(2);
			}
			/*LEVEL 3*/
			else if(mouseOver(mx,my,270,115,90,90) && FileStore.conf[3] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_3");
				spawner.startLevel(3);
			}
			/*LEVEL 4*/
			else if(mouseOver(mx,my,385,115,90,90) && FileStore.conf[4] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_4");
				spawner.startLevel(4);
			
			}
			/*LEVEL 5*/
			else if(mouseOver(mx,my,495,115,90,90) && FileStore.conf[5] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_5");
				spawner.startLevel(5);
				
			}
			/*LEVEL 6*/
			else if(mouseOver(mx,my,40,230,90,90) && FileStore.conf[6] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_6");
				spawner.startLevel(6);
				
			}
			/*LEVEL 7*/
			else if(mouseOver(mx,my,155,225,90,90) && FileStore.conf[7] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_7");
				spawner.startLevel(7);
				
			}
			/*LEVEL 8*/
			else if(mouseOver(mx,my,270,225,90,90) && FileStore.conf[8] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_8");
				spawner.startLevel(8);
				
			}
			/*LEVEL 9*/
			else if(mouseOver(mx,my,382,225,90,90) && FileStore.conf[9] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_9");
				spawner.startLevel(9);
				
			}
			/*LEVEL 10*/
			else if(mouseOver(mx,my,497,225,90,90) && FileStore.conf[10] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_10");
				spawner.startLevel(10);
				
			}
			/*LEVEL 11*/
			else if(mouseOver(mx,my,40,340,90,90) && FileStore.conf[11] != '-'){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_11");
				spawner.startLevel(11);
				
			}
			/*LEVEL 12*/
			else if(mouseOver(mx,my,153,340,90,90)){
				game.gameState = STATE.Game;
				AudioPlayer.playMusic("music_lvl_12");
				spawner.startLevel(12);
				
			}
			/*LEVEL 13*/
			else if(mouseOver(mx,my,270,340,90,90)){
				//game.gameState = STATE.Game;
				//AudioPlayer.playMusic("music_lvl_13");
				//spawner.startLevel(13);
				
			}
			
			/*INFO button*/
			else if(mouseOver(mx,my,582, 10, 40, 40)) {
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Info_1;
			
			}
			
			/*BACK button*/
			else if(mouseOver(mx,my,400,345,180,80)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Menu;
				select_level_img = dodge_select_level_img;
			}
		}else if(game.gameState == STATE.Settings) {
			
			/*DIFFICULTY button*/
			if(mouseOver(mx,my,225,120,200,55)) {
				AudioPlayer.getSound("sound_click").play(1f,1f);
				//simple swap
				if(spawner.difficulty == 1) {
					spawner.difficulty = 2;
					FileStore.update(12, '1');
				}else if(spawner.difficulty == 2) {
					spawner.difficulty = 1;
					FileStore.update(12, '0');
				}
				
			}
			/*SOUND button*/
			else if(mouseOver(mx,my,280,205,70,70)) {
				AudioPlayer.getSound("sound_click").play(1f,1f);
				AudioPlayer.changeVolume();
			
			}
			/*CONTROLS button*/
			else if(mouseOver(mx,my,230,300,190,55)) {
				AudioPlayer.getSound("sound_click").play(1f,1f);
				//simple swap
				if(KeyInput.WASDkeys) {
					KeyInput.WASDkeys = !KeyInput.WASDkeys;
					FileStore.update(14, '1');
				}else {
					KeyInput.WASDkeys = !KeyInput.WASDkeys;
					FileStore.update(14, '0');
				}	
				
			}
			/*EXIT button*/
			else if(mouseOver(mx,my,482,375,130,55)) {
				AudioPlayer.getSound("sound_click").play(1f,1f);
				hud.insertDiff(spawner.difficulty);
				game.gameState = STATE.Menu;
				dodge_settings_img = dodge_settings_default;
			}
			
		}
		else if(game.gameState == STATE.Info_1) {
			
			/*BACK BUTTON*/
			if(mouseOver(mx,my,582, 10, 40, 40)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Select;
	
			}
			
			/*SWITCH PANEL TO RIGHT*/
			else if(mouseOver(mx,my,572, 395, 50, 42)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Info_2;
				info_icon = info_icon_0;
				dodge_info_1_img = dodge_info_1;
				dodge_info_2_img = dodge_info_2;
				dodge_info_3_img = dodge_info_3;
				dodge_info_4_img = dodge_info_4;
			}
			
		}
		else if(game.gameState == STATE.Info_2) {
			
			/*BACK BUTTON*/
			if(mouseOver(mx,my,582, 10, 40, 40)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Select;
			}
			
			/*SWITCH PANEL TO RIGHT*/
			else if(mouseOver(mx,my,572, 395, 50, 42)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Info_3;
				info_icon = info_icon_0;
				dodge_info_1_img = dodge_info_1;
				dodge_info_2_img = dodge_info_2;
				dodge_info_3_img = dodge_info_3;
				dodge_info_4_img = dodge_info_4;
			}
			/*SWITCH PANEL TO LEFT*/
			else if(mouseOver(mx,my,510, 395, 50, 42)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Info_1;
				info_icon = info_icon_0;
				dodge_info_1_img = dodge_info_1;
				dodge_info_2_img = dodge_info_2;
				dodge_info_3_img = dodge_info_3;
				dodge_info_4_img = dodge_info_4;
			}
		}
		else if(game.gameState == STATE.Info_3) {
			
			/*BACK BUTTON*/
			if(mouseOver(mx,my,582, 10, 40, 40)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Select;
			}
			
			/*SWITCH PANEL TO RIGHT*/
			else if(mouseOver(mx,my,572, 395, 50, 42)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Info_4;
				info_icon = info_icon_0;
				dodge_info_1_img = dodge_info_1;
				dodge_info_2_img = dodge_info_2;
				dodge_info_3_img = dodge_info_3;
				dodge_info_4_img = dodge_info_4;
			}
			/*SWITCH PANEL TO LEFT*/
			else if(mouseOver(mx,my,510, 395, 50, 42)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Info_2;
				info_icon = info_icon_0;
				dodge_info_1_img = dodge_info_1;
				dodge_info_2_img = dodge_info_2;
				dodge_info_3_img = dodge_info_3;
				dodge_info_4_img = dodge_info_4;
			}
			
		}
		else if(game.gameState == STATE.Info_4) {
			
			/*BACK BUTTON*/
			if(mouseOver(mx,my,582, 10, 40, 40)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Select;
			}
			
			/*SWITCH PANEL TO LEFT*/
			else if(mouseOver(mx,my,510, 395, 50, 42)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Info_3;
				info_icon = info_icon_0;
				dodge_info_1_img = dodge_info_1;
				dodge_info_2_img = dodge_info_2;
				dodge_info_3_img = dodge_info_3;
				dodge_info_4_img = dodge_info_4;
			}
			
		}
		else if(game.gameState==STATE.Pause) {
			
			/*RESUME BUTTON*/
			if(mouseOver(mx,my,215, 175, 200, 65)) {
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Game;
			}
			/*GIVE UP BUTTON*/
			else if(mouseOver(mx,my,240,260,165,70)) {
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game.gameState = STATE.Menu;
				handler.clearGame();
				
			}
			
		}
		else if(game.gameState == STATE.Victory) {
			
			
			/*NEXT LEVEL*/
			if(mouseOver(mx,my,200, 148, 270, 80)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				if(spawner.nextLevel()) game.gameState = STATE.Game;
				victory_screen_img = victory_screen;
			}
			
			/* BACK BUTTON*/
			else if(mouseOver(mx,my,486, 380, 140, 50)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				victory_screen_img = victory_screen;
				select_level_img = dodge_select_level_img;
				game.gameState = STATE.Select;
			}
		}
		else if(game.gameState == STATE.FinalVictory) {
			/* BACK BUTTON*/
			if(mouseOver(mx,my,480, 380, 150, 50)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				select_level_img = dodge_select_level_img;
				ending_screen_img = ending_screen;
				game.gameState = STATE.Select;
			}
		}
		else if(game.gameState == STATE.End) {
	
			/*PLAY AGAIN*/
			 if(mouseOver(mx,my,210,177,200,70)){
				 AudioPlayer.getSound("sound_click").play(1f,1f);
				 game.gameState = STATE.Game;
				 game_over_img = game_over;
				 spawner.startLevel(0);
			}
			/* BACK BUTTON*/
			else if(mouseOver(mx,my,210,247,200,70)){
				AudioPlayer.getSound("sound_click").play(1f,1f);
				game_over_img = game_over;
				select_level_img = dodge_select_level_img;
				game.gameState = STATE.Select;
				
			}
		}
		
	}
	
	
	public void mouseReleassed(MouseEvent e) {
		
	}
	
	
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState==STATE.Menu) {
			
			/*PLAY button*/
			if(mouseOver(mx,my,220,130,195,82)) {
				background_img = dodge_menu_play_img;
			}
			/*Difficulty button*/
			else if(mouseOver(mx,my,240,260,165,70)) {
				background_img = dodge_menu_options_img;
			}
			/*QUITE button*/
			else if(mouseOver(mx,my,260,375,130,57)) {
				background_img = dodge_menu_exit_img;
			}
			/*IF NOT THEN THE DEFAULT BACKGROUND*/
			else {
				background_img = dodge_menu_img;
			}
		}
else if(game.gameState == STATE.Select) {
			
			/*LEVEL 1*/
			if(mouseOver(mx,my,40,115,90,90) && FileStore.conf[1] != '-'){
				select_level_img = dodge_select_level_1_img;
			}
			/*LEVEL 2*/
			else if(mouseOver(mx,my,155,115,90,90) && FileStore.conf[2] != '-'){
				select_level_img = dodge_select_level_2_img;
			}
			/*LEVEL 3*/
			else if(mouseOver(mx,my,270,115,90,90) && FileStore.conf[3] != '-'){
				select_level_img = dodge_select_level_3_img;
			}
			/*LEVEL 4*/
			else if(mouseOver(mx,my,385,115,90,90) && FileStore.conf[4] != '-'){
				select_level_img = dodge_select_level_4_img;	
			}
			/*LEVEL 5*/
			else if(mouseOver(mx,my,495,115,90,90) && FileStore.conf[5] != '-'){
				select_level_img = dodge_select_level_5_img;
			}
			/*LEVEL 6*/
			else if(mouseOver(mx,my,40,230,90,90) && FileStore.conf[6] != '-'){
				select_level_img = dodge_select_level_6_img;
			}
			/*LEVEL 7*/
			else if(mouseOver(mx,my,155,225,90,90) && FileStore.conf[7] != '-'){
				select_level_img = dodge_select_level_7_img;
			}
			/*LEVEL 8*/
			else if(mouseOver(mx,my,270,225,90,90) && FileStore.conf[8] != '-'){
				select_level_img = dodge_select_level_8_img;
			}
			/*LEVEL 9*/
			else if(mouseOver(mx,my,382,225,90,90) && FileStore.conf[9] != '-'){
				select_level_img = dodge_select_level_9_img;
			}
			/*LEVEL 10*/
			else if(mouseOver(mx,my,497,225,90,90) && FileStore.conf[10] != '-'){
				select_level_img = dodge_select_level_10_img;
			}
			/*LEVEL 11*/
			else if(mouseOver(mx,my,40,340,90,90) && FileStore.conf[11] != '-'){
				select_level_img = dodge_select_level_11_img;
			}
			/*LEVEL 12*/
			else if(mouseOver(mx,my,153,340,90,90)){
				select_level_img = dodge_select_level_12_img;
			}
			/*LEVEL 13*/
			else if(mouseOver(mx,my,270,340,90,90)){
				select_level_img = dodge_select_level_13_img;
			}
			/*BACK button*/
			else if(mouseOver(mx,my,400,345,180,80)){
				select_level_img = dodge_select_level_back_img;
			}
			/*IF NOT THEN THE DEFAULT BACKGROUND*/
			else {
				select_level_img = dodge_select_level_img;
			}
			
			/*INFO BUTTON AND ALSO SEPARETED IMAGE, FOR FIXING REASONS..*/
			if(mouseOver(mx,my,582, 10, 40, 40)){
				info_icon = info_icon_1;
			}else {
				info_icon = info_icon_0;
			}
		}
else if(game.gameState == STATE.Info_1) {
	
	/*BACK BUTTON*/
	if(mouseOver(mx,my,582, 10, 40, 40)){
		dodge_info_1_img = dodge_info_1_back;
	}
	
	/*SWITCH PANEL TO RIGHT*/
	else if(mouseOver(mx,my,572, 395, 50, 42)){
		dodge_info_1_img = dodge_info_1_right;
	}
	/*IF NOT THEN THE DEFAULT BACKGROUND*/
	else {
		dodge_info_1_img = dodge_info_1;
	}
	
}
else if(game.gameState == STATE.Info_2) {
	
	/*BACK BUTTON*/
	if(mouseOver(mx,my,582, 10, 40, 40)){
		dodge_info_2_img = dodge_info_2_back;
	}
	
	/*SWITCH PANEL TO RIGHT*/
	else if(mouseOver(mx,my,572, 395, 50, 42)){
		dodge_info_2_img = dodge_info_2_right;
	}
	/*SWITCH PANEL TO LEFT*/
	else if(mouseOver(mx,my,510, 395, 50, 42)){
		dodge_info_2_img = dodge_info_2_left;
	}
	/*IF NOT THEN THE DEFAULT BACKGROUND*/
	else {
		dodge_info_2_img = dodge_info_2;
	}
}
else if(game.gameState == STATE.Info_3) {
	
	/*BACK BUTTON*/
	if(mouseOver(mx,my,582, 10, 40, 40)){
		dodge_info_3_img = dodge_info_3_back;
	}
	
	/*SWITCH PANEL TO RIGHT*/
	else if(mouseOver(mx,my,572, 395, 50, 42)){
		dodge_info_3_img = dodge_info_3_right;
	}
	/*SWITCH PANEL TO LEFT*/
	else if(mouseOver(mx,my,510, 395, 50, 42)){
		dodge_info_3_img = dodge_info_3_left;
	}
	/*IF NOT THEN THE DEFAULT BACKGROUND*/
	else {
		dodge_info_3_img = dodge_info_3;
	}
	
}
else if(game.gameState == STATE.Info_4) {
	
	/*BACK BUTTON*/
	if(mouseOver(mx,my,582, 10, 40, 40)){
		dodge_info_4_img = dodge_info_4_back;
	}
	
	/*SWITCH PANEL TO LEFT*/
	else if(mouseOver(mx,my,510, 395, 50, 42)){
		dodge_info_4_img = dodge_info_4_left;
	}
	/*IF NOT THEN THE DEFAULT BACKGROUND*/
	else {
		dodge_info_4_img = dodge_info_4;
	}
	
}
	
		else if(game.gameState==STATE.Settings) {
	
				/*DIFFICULTY button*/
				if(mouseOver(mx,my,225,120,200,55)) {
					dodge_settings_img = dodge_settings_diff;
					}
				/*SOUND button*/
				else if(mouseOver(mx,my,280,205,70,70)) {
					dodge_settings_img = dodge_settings_sound;
				}
				/*CONTROLS button*/
				else if(mouseOver(mx,my,230,300,190,55)) {
					dodge_settings_img = dodge_settings_controls;
				}
				/*EXIT button*/
				else if(mouseOver(mx,my,482,375,130,55)) {
					dodge_settings_img = dodge_settings_exit;
				}
				/*IF NOT THEN THE DEFAULT BACKGROUND*/
				else {
					dodge_settings_img = dodge_settings_default;
				}
		}
	else if(game.gameState==STATE.Pause) {
	
		/*RESUME BUTTON*/
		if(mouseOver(mx,my,215, 175, 200, 65)) {
			paused_img = paused_resume_img;
		}
		/*GIVE UP BUTTON*/
		else if(mouseOver(mx,my,240,260,165,70)) {
			paused_img = paused_giveup_img;
		}
		/*IF NOT THEN THE DEFAULT BACKGROUND*/
		else {
			paused_img = paused_default_img;		
			}
		}
	else if(game.gameState == STATE.Victory) {
		
		
		/*NEXT LEVEL*/
		if(mouseOver(mx,my,200, 148, 270, 80)){
			victory_screen_img = victory_screen_next;
		}
		
		/* BACK BUTTON*/
		else if(mouseOver(mx,my,486, 380, 140, 50)){
			victory_screen_img = victory_screen_exit;
		}
		else {
			victory_screen_img = victory_screen;
		}
	}
	else if(game.gameState == STATE.FinalVictory) {
		/* EXIT BUTTON*/
		if(mouseOver(mx,my,480, 380, 150, 50)){
			ending_screen_img = ending_screen_exit;
		}else {
			ending_screen_img = ending_screen;
		}
	}
	else if(game.gameState==STATE.End) {
		
		/*AGAIN BUTTON*/
		if(mouseOver(mx,my,210,177,200,70)) {
			game_over_img = game_over_again;
		}
		/*EXIT BUTTON*/
		else if(mouseOver(mx,my,210,247,200,70)) {
			game_over_img = game_over_exit;
		}
		/*IF NOT THEN THE DEFAULT BACKGROUND*/
		else {
			game_over_img = game_over;		
			}
		}
		
		
	}
	
	
	
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		
		if(mx > x && mx < x + width) {
			if (my > y && my<y + height) {
				return true;
			}else {
				return false;
			}
		}else return false;
	}
	
	public void tick() {
		
		
	}
	
	private void level_locker(Graphics g, int index, int x, int y) {
		
		if(FileStore.conf[index] == '-') {
			g.drawImage(locker_img,x,y,null);
		}else if(FileStore.conf[index] == '1') {
			g.drawImage(star_indicator_1,x,y,null);
		}else if(FileStore.conf[index] == '2') {
			g.drawImage(star_indicator_2,x,y,null);
		}else if(FileStore.conf[index] == '3') {
			g.drawImage(star_indicator_3,x,y,null);
		}
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		
		if(game.gameState==STATE.Menu) {
				
			g.drawImage(background_img,0,0,null);
		
		}
		else if(game.gameState==STATE.Select) {
			
			g.drawImage(select_level_img,0,0,null);
			
			g.drawImage(info_icon, 0,0,null);
			
			//Graphics Index X Y
			level_locker(g, 1, 100, 100);
			level_locker(g, 2, 210, 100);
			level_locker(g, 3, 330, 100);
			level_locker(g, 4, 440, 100);
			level_locker(g, 5, 560, 100);
			level_locker(g, 6, 100, 210);
			level_locker(g, 7, 210, 210);
			level_locker(g, 8, 330, 210);
			level_locker(g, 9, 440, 210);
			level_locker(g, 10, 560, 210);
			level_locker(g, 11, 100, 320);
			
			//g.drawImage(coming_soon_img,185,325,null);
			//g.drawImage(coming_soon_img,300,325,null);
			//g.drawRect(40, 180, 120, 120);
			
		}
		else if(game.gameState==STATE.Settings) {
			
			g.drawImage(dodge_settings_img,0,0,null);
			
			/*DIFFICULTY ICON*/
			if(spawner.difficulty == 0) {
				//THERE IS NO EASY MODE ANYMORE
			}
			else if(spawner.difficulty == 1) {
				g.drawImage(settings_diff_normal,0,0,null);
			}
			else if(spawner.difficulty == 2) {
				g.drawImage(settings_diff_hard,0,0,null);
			}
			
			/*SOUND ICON*/
			if(AudioPlayer.enumVolume == VOLUME.High) {
				g.drawImage(settings_sound_2,0,0,null);
			}
			else if(AudioPlayer.enumVolume == VOLUME.Low) {
				g.drawImage(settings_sound_1,0,0,null);
			}
			else if(AudioPlayer.enumVolume == VOLUME.Mute) {
				g.drawImage(settings_sound_0,0,0,null);
			}
			
			/*CONTROL ICON*/
			if(KeyInput.WASDkeys) {
				g.drawImage(settings_controls_wasd,0,0,null);
			}
			else {
				g.drawImage(settings_controls_arrows,0,0,null);
			}
			
			
			
		}
		else if(game.gameState==STATE.Info_1) {
			
			
			g.drawImage(dodge_info_1_img,0,0,null);
			g.drawImage(dodge_info_fix,0,0,null);
			
			
			
		}
		else if(game.gameState==STATE.Info_2) {
			
			
			g.drawImage(dodge_info_2_img,0,0,null);
			
		}
		else if(game.gameState==STATE.Info_3) {
	
	
			g.drawImage(dodge_info_3_img,0,0,null);
			
		}
		else if(game.gameState==STATE.Info_4) {
	
	
			g.drawImage(dodge_info_4_img,0,0,null);
		
		}	
		else if(game.gameState == STATE.Pause) {
			
			g.drawImage(paused_img,0,0,null);
			
			
		}else if(game.gameState == STATE.Victory) {
			
			g.drawImage(victory_screen_img,0,0,null);
			
			
			
		}
		else if(game.gameState == STATE.FinalVictory) {
			
			g.drawImage(ending_screen_img, 0, 0, null);
			
			
			
		}
		else if(game.gameState == STATE.End) {
			
			
			g.drawImage(game_over_img,0,0,null);
			
			
			
		}
	}
	
}

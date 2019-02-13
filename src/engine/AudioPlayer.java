package engine;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static VOLUME enumVolume = VOLUME.High;
	private static String currentMusic;
	

	public static void load() {
		
		try {
			
			musicMap.put("music_menu", new Music("res/music_menu.ogg"));
			musicMap.put("music_default", new Music("res/music_default.ogg"));
	
			musicMap.put("music_lvl_1", new Music("res/music_lvl_1.ogg"));
			musicMap.put("music_lvl_2", new Music("res/music_lvl_2.ogg"));
			musicMap.put("music_lvl_3", new Music("res/music_lvl_3.ogg"));
			musicMap.put("music_lvl_4", new Music("res/music_lvl_4.ogg"));
			musicMap.put("music_lvl_5", new Music("res/music_lvl_5.ogg"));
			musicMap.put("music_lvl_6", new Music("res/music_lvl_6.ogg"));
			musicMap.put("music_lvl_7", new Music("res/music_lvl_7.ogg"));
			musicMap.put("music_lvl_8", new Music("res/music_lvl_8.ogg"));
			musicMap.put("music_lvl_9", new Music("res/music_lvl_9.ogg"));
			musicMap.put("music_lvl_10", new Music("res/music_lvl_10.ogg"));
			musicMap.put("music_lvl_11", new Music("res/music_lvl_11.ogg"));
			musicMap.put("music_lvl_12", new Music("res/music_lvl_12.ogg"));
			musicMap.put("music_lvl_13", new Music("res/music_lvl_13.ogg"));
			musicMap.put("music_ending", new Music("res/music_ending.ogg"));
			
			
			soundMap.put("sound_damaged", new Sound("res/damaged_sound.ogg"));
			soundMap.put("sound_click", new Sound("res/sound_click.ogg"));
			soundMap.put("sound_collect", new Sound("res/sound_collect.ogg"));
			soundMap.put("sound_star_appear", new Sound("res/sound_star_appear.ogg"));
			soundMap.put("sound_explosion", new Sound("res/sound_explosion.ogg"));
			soundMap.put("sound_alarm", new Sound("res/sound_alarm.ogg"));
			soundMap.put("venom_activation", new Sound("res/venom_activation.ogg"));
			soundMap.put("sound_snake", new Sound("res/sound_snake.ogg"));
			soundMap.put("sound_slime", new Sound("res/sound_slime.ogg"));
			soundMap.put("sound_gona_die", new Sound("res/sound_gona_die.ogg"));
			soundMap.put("sound_hide_seek", new Sound("res/sound_hide_seek.ogg"));
			soundMap.put("sound_lonely_girl", new Sound("res/sound_lonely_girl.ogg"));
			soundMap.put("sound_woman_laugh", new Sound("res/sound_woman_laugh.ogg"));
			soundMap.put("sound_woman_laugh_1", new Sound("res/sound_woman_laugh_1.ogg"));
			soundMap.put("sound_thunder", new Sound("res/sound_thunder.ogg"));
			soundMap.put("sound_get_locked", new Sound("res/sound_get_locked.ogg"));
			soundMap.put("sound_get_unlocked", new Sound("res/sound_get_unlocked.ogg"));
			soundMap.put("sound_warning", new Sound("res/sound_warning.ogg"));
			soundMap.put("sound_deathtrap_collect", new Sound("res/sound_deathtrap_collect.ogg"));
			soundMap.put("sound_deathtrap_spawn", new Sound("res/sound_deathtrap_spawn.ogg"));
			
			soundMap.put("sound_cant_do_that", new Sound("res/sound_cant_do_that.ogg"));
			soundMap.put("sound_fools", new Sound("res/sound_fools.ogg"));
			soundMap.put("sound_final_form", new Sound("res/sound_final_form.ogg"));
			soundMap.put("sound_realise", new Sound("res/sound_realise.ogg"));
			
			soundMap.put("sound_chameleon", new Sound("res/sound_chameleoen_change.ogg"));
			
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void playMusic(String mapKey) {
		
		if(mapKey != currentMusic) {
			
		
			getMusic(mapKey).loop();
		
			if(enumVolume == VOLUME.High) {
				getMusic(mapKey).setVolume(0.18f);
			}else if(enumVolume == VOLUME.Low) {
				getMusic(mapKey).setVolume(0.05f);
			}else if(enumVolume == VOLUME.Mute){
				getMusic(mapKey).setVolume(0);
			}
		
			currentMusic = mapKey;
		
		}
		
	}
	
	public static void changeVolume() {
		if(enumVolume == VOLUME.High) {
			enumVolume = VOLUME.Low;
			getMusic(currentMusic).setVolume(0.05f);
			FileStore.update(13,'1');
			System.out.println("The volume is LOW");
		}
		else if(enumVolume == VOLUME.Low) {
			enumVolume = VOLUME.Mute;
			getMusic(currentMusic).setVolume(0);
			FileStore.update(13,'0');
			System.out.println("The volume is MUTED");
		}
		else if(enumVolume == VOLUME.Mute){
			enumVolume = VOLUME.High;
			getMusic(currentMusic).setVolume(0.1f);
			FileStore.update(13,'2');
			System.out.println("The volume is HIGH");
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
	
}

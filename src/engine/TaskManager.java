package engine;

import java.util.Timer;
import java.util.TimerTask;

import java.awt.Color;

public class TaskManager {

    private Timer timer = new Timer();
    private Handler handler;
    
    public TaskManager(Handler handler) {
    	
    	this.handler = handler;
    }

    

    public void startTask_player_damaged() {
    	if(Player.immune != true) {
    		Player.immune = true;
    		Player.PLAYER_COLOR = new Color(255, 100, 100);
    		AudioPlayer.getSound("sound_damaged").play(0.8f,0.1f);
    		handler.addObject(new WarningEffect(0,0,8,handler));
            timer.schedule(new Task_player_damaged(), 400);
    	}
    	
    }

    private class Task_player_damaged extends TimerTask {
        @Override
        public void run() {
            System.out.println("Task Running");  
            Player.immune = false;
            Player.PLAYER_COLOR = Color.WHITE;
          
        }
    }
}
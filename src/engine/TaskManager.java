package engine;

import java.util.Timer;
import java.util.TimerTask;

public class TaskManager {

    private Timer timer = new Timer();

    

    public void startTask_player_damaged() {
    	if(Player.immune != true) {
    		Player.immune = true;
            timer.schedule(new Task_player_damaged(), 1000);
    	}
    	
    }

    private class Task_player_damaged extends TimerTask {
        @Override
        public void run() {
            System.out.println("Task Running");  
            Player.immune = false;
          
        }
    }
}
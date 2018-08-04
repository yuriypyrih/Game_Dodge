import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	/*
	 * It seems there is no constructor for this class
	 */
	
	
	/*what it does it Renders all objects of the entire LinkedList in the Game
	 * UPDATES THE GAME ALL TOGETHER, almost every object has a tick() and render() method
	 * so the Handler class "handles" and updates the classes all-together
	*/
	LinkedList<GameObject> object = new LinkedList<GameObject>();	//A LinkedList to handle our GameObject objects
	
	
	public void clearGame() {
		object.clear();
	}
	
	public void clearEnemy() {
	 for(int i=0 ; i < object.size(); i++) {
		if(object.get(i).getId() != ID.Player && object.get(i).getId() != ID.Healer && object.get(i).getId() != ID.DeathTrap && object.get(i).getId() != ID.Trail) {
			removeObject(object.get(i));
			i-- ;
			}
	 	}
	}
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			try {
				GameObject tempObject = object.get(i);
				tempObject.render(g);
			}catch(NullPointerException e) {}
		}
	}
	
	public void addObject(GameObject object) {
		if(object.getId()!=ID.Trail && object.getId()!=ID.BossBullet)
			System.out.println(object + " object has been added");
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		if(object.getId()!=ID.Trail && object.getId()!=ID.BossBullet && object.getId()!=ID.Star && object.getId()!=ID.Healer)
			System.out.println(object + " object has been removed");
		this.object.remove(object);
	}
	
	
	
	
	
	
	
	
	
}//end of Handler class

package world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.GameManager;
import object.GameObject;
import object.Wall;
import object.physics.model.Model;

public class World {

	GameManager gm;
	
	public Model model;
	public ArrayList<GameObject> objects;
	
	public World (GameManager gm) {
		this.gm = gm;
		
		objects = new ArrayList<>();
		
		objects.add(new Wall(50, 50, 750, 50));
		objects.add(new Wall(50, 50, 50, 550));
		objects.add(new Wall(50, 550, 750, 550));
		objects.add(new Wall(750, 50, 750, 550));
		
		newModel();
	}
	
	public void newModel() {
		objects.remove(model);
		model = Model.RandomModel(200, 300, 200, 200, 5, this);
		objects.add(model);
	}
	
	public ArrayList<Wall> getWalls() {
		ArrayList<Wall> walls = new ArrayList<>();
		for (GameObject o : objects) {
			if (o instanceof Wall) {
				walls.add((Wall) o);
			}
		}
		return walls;
	}
	
	public void update() {
		for (GameObject o : objects) {
			o.update();
		}
	}
	
	public void render(Graphics g) {		
		for (GameObject o : objects) {
			o.render(g);
		}
	}

	public void keyPressedEvent(KeyEvent e) {	
	}

	public void keyReleasedEvent(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_R) {
			newModel();
		}
	}
}

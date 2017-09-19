package world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.GameManager;
import object.Wall;
import object.physics.model.Model;

public class World {

	GameManager gm;
	
	public Model model;
	public ArrayList<Wall> walls;
	
	public World (GameManager gm) {
		this.gm = gm;
		
		walls = new ArrayList<>();
		
		walls.add(new Wall(50, 50, 750, 50));
		walls.add(new Wall(50, 50, 50, 550));
		walls.add(new Wall(50, 550, 750, 550));
		walls.add(new Wall(750, 50, 750, 550));
		
		newModel();
	}
	
	public void newModel() {
		model = Model.RandomModel(200, 300, 200, 200, 4, this);
	}
	
	public void update() {
		for (Wall w : walls) {
			w.update();
		}
		model.update();
	}
	
	public void render(Graphics g) {		
		for (Wall w : walls) {
			w.render(g);
		}
		model.render(g);
	}

	public void keyPressedEvent(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_C) {
			model.contractMuscles();
		}
	}

	public void keyReleasedEvent(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_R) {
			newModel();
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			model.relaxMuscles();
		}
	}
}

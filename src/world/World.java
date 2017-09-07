package world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.GameManager;
import main.Input;
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
		if (Input.KeyDown(KeyEvent.VK_R)) {
			newModel();
		}
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
}

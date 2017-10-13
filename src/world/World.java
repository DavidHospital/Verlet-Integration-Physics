package world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.GameManager;
import object.scene.Scene;

public class World {

	GameManager gm;
	Scene scene;
	
	public World (GameManager gm) {
		this.gm = gm;
		this.scene = new Scene(gm.windowWidth, gm.windowWidth);
	}
	
	public void update() {
		scene.update();
	}
	
	public void render(Graphics g) {		
		scene.render(g);
	}

	public void keyPressedEvent(KeyEvent e) {	
	}

	public void keyReleasedEvent(KeyEvent e) {
	}
}

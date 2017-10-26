package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import object.scene.Scene;

public class GameManager {
	
	// window information
	public int windowWidth;
	public int windowHeight;
	
	private Scene scene;
	
	public GameManager(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		this.scene = new Scene(this);
	}
	
	void update() {
		scene.update();
	}
	
	void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, windowWidth, windowHeight);
		
		scene.render(g);
	}

	public void keyPressedEvent(KeyEvent e) {
		scene.keyPressedEvent(e);
	}

	public void keyReleasedEvent(KeyEvent e) {
		scene.keyReleasedEvent(e);
	}

}

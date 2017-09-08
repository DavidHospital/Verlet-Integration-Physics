package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import world.World;

public class GameManager {
	
	// window information
	public int windowWidth;
	public int windowHeight;
	
	private World world;
	
	public GameManager(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		this.world = new World(this);
	}
	
	void update() {
		world.update();
	}
	
	void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, windowWidth, windowHeight);
		
		world.render(g);
	}

	public void keyPressedEvent(KeyEvent e) {
		world.keyPressedEvent(e);
	}

	public void keyReleasedEvent(KeyEvent e) {
		world.keyReleasedEvent(e);
	}

}

package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import genetic.Population;
import object.scene.Scene;

public class GameManager {
	
	// window information
	public int windowWidth;
	public int windowHeight;
	
	Population p;
	
	public GameManager(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		p = new Population(new Scene(this), 100, 0.05);
	}
	
	void update() {
		p.nextGeneration();
	}
	
	void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, windowWidth, windowHeight);
		
		p.getBest().render(g);
	}

	public void keyPressedEvent(KeyEvent e) {
	}

	public void keyReleasedEvent(KeyEvent e) {
	}

}

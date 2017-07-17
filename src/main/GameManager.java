package main;

import java.awt.Color;
import java.awt.Graphics;

import object.physicsnew.model.Model;

public class GameManager {

	// Game information
	long seed;
	
	// window information
	int windowWidth;
	int windowHeight;
	
	// background drawing
	Color background = new Color(250, 238, 224);
	Color floor = new Color(35, 22, 5);
	
	// World information
	public int floorHeight;
	
	// Objects
	Model m;
	
	public GameManager(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		floorHeight = windowHeight - 100;
		
		seed = System.nanoTime();
		//seed = 5259227212735844L;
		System.out.println(seed);
		
		m = Model.RandomModel(300, 0, 300, 300, seed, this);
	}
	
	void update() {
		m.update();
	}
	
	void render(Graphics g) {
		g.setColor(background);
		g.fillRect(0, 0, windowWidth, windowHeight);
		
		g.setColor(floor);
		g.fillRect(0, floorHeight, windowWidth, windowHeight);
		
		// Objects
		m.render(g);
	}
}

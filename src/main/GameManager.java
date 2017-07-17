package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import object.physicsnew.model.Model;

public class GameManager {
	
	// window information
	int windowWidth;
	int windowHeight;
	
	// background drawing
	Color background = new Color(250, 238, 224);
	Color floor = new Color(35, 22, 5);
	
	// World information
	public int floorHeight;
	
	// Objects
	ArrayList<String> pastInfo;
	int counter;
	
	Model m;
	double startX;
	
	// Time
	long startTime;
	
	public GameManager(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		floorHeight = windowHeight - 100;
		
		pastInfo = new ArrayList<>();
		counter = 0;
		
		newModel();
	}
	
	private void recordModel() {
		pastInfo.add("Creature #: " + counter + "   Distance: " + (int)((m.getX() - startX) * 100) / 100.0);
		counter ++;
	}
	
	private void newModel() {
		m = Model.RandomModel(300, 300, 200, 200, this);
		startX = m.getX();
		
		startTime = System.currentTimeMillis();
	}
	
	void update() {
		if (System.currentTimeMillis() - startTime >= 1000) {
			recordModel();
			newModel();
		}
		m.update();
	}
	
	void render(Graphics g) {
		g.setColor(background);
		g.fillRect(0, 0, windowWidth, windowHeight);
		
		g.setColor(floor);
		g.fillRect(0, floorHeight, windowWidth, windowHeight);
		
		// Objects
		m.render(g);
		
		g.setColor(new Color(0, 0, 0, 0.1f));
		g.fillRect(windowWidth - 250, 10, 240, windowHeight - 300);
		
		g.setColor(Color.BLACK);
		for (int i = pastInfo.size() < 16 ? 0 : pastInfo.size() - 15; i < pastInfo.size(); i ++) {
			g.drawString(pastInfo.get(i), windowWidth - 250 + 5, 25 + (pastInfo.size() < 16 ? i : i - (pastInfo.size() - 15)) * 20);
		}
	}
}

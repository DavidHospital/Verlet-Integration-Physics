package object.physics;

import java.awt.Color;
import java.awt.Graphics;

public class Stick {

	// Stick properties
	public Point p1;
	public Point p2;
	public double length;
	
	private double stretch = 1.0;
	
	// Render properties
	public Color color;
	
	public Stick (Point p1, Point p2, double length) {
		this.p1 = p1;
		this.p2 = p2;
		this.length = length;
	}
	
	// constructor to set the distance what it already is
	// Use this one most of the time
	public Stick (Point p1, Point p2) {
		this(p1, p2, Distance(p1, p2));
	}
	
	public static double Distance(Point p1, Point p2) {
		double dx = p1.x - p2.x;
		double dy = p1.y - p2.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public void update() {
		// update points within the stick
		
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		double distance = Distance(p1, p2);
		double difference = length - distance;
		double percent = difference / distance / 2;
		double offsetX = dx * percent * stretch;
		double offsetY = dy * percent * stretch;
		
		p1.move(-offsetX, -offsetY);
		p2.move(offsetX, offsetY);

	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
	}
}

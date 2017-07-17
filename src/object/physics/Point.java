package object.physics;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class Point {

	public static final double DEFAULT_RADIUS = 5;
	public static final Color DEFAULT_COLOR = Color.BLACK;
	
	// position variables
	public double x;
	public double y;
	private double oldX;
	private double oldY;
	
	// physics variables
	private double gravity = 0.3;
	private double friction = 0.999;
	private double bounce = 0.1;
	
	// render variables
	private double radius;
	private Color color;
	
	// Use oldX and oldY for initial velocity and friction
	public Point(double x, double y, double oldX, double oldY, double radius, Color color) {
		this.x = x;
		this.y = y;
		this.oldX = oldX;
		this.oldY = oldY;
		this.radius = radius;
		this.color = color;
	}
	public Point(double x, double y, double oldX, double oldY) {
		this(x, y, oldX, oldY, DEFAULT_RADIUS, DEFAULT_COLOR);
	}
	
	// Initialize point with no velocity
	public Point(double x, double y) {
		this(x, y, x, y, DEFAULT_RADIUS, DEFAULT_COLOR);
	}
	public Point(double x, double y, double radius, Color color) {
		this(x, y, x, y, radius, color);
	}
	
	public void update() {
		
		// update position based on old position
		double dx = x - oldX;
		double dy = y - oldY;	
		oldX = x;
		oldY = y;
		x += dx * friction;
		y += dy * friction + gravity;
		
		// make sure point stays within the screen borders
		if (x > Game.WIDTH) {
			x = Game.WIDTH;
			oldX = x + bounce * dx;
		} else if (x < 0) {
			x = 0;
			oldX = x + bounce * dx;
		}
		if (y > Game.HEIGHT) {
			y = Game.HEIGHT;
			oldY = y + bounce * dy;
		} else if (y < 0) {
			y = 0;
			oldY = y + bounce * dy;
		}	
	}
	
	public void move(double dx, double dy) {
		x += dx;
		y += dy;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius/2), (int)(y - radius/2), (int)radius, (int)radius);
	}
	
}

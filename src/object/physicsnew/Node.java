package object.physicsnew;

import java.awt.Color;
import java.awt.Graphics;

import main.GameManager;

public class Node {

	public static final double RADIUS = 10;
	public static final double bounce = 0.2f;
	public static final double gravity = 1.0f;
	
	public double friction;
	
	// Position
	public double x;
	public double y;
	
	public double oldX;
	public double oldY;
	
	
	public Node(double x, double y, double friction) {
		this.x = x;
		this.y = y;
		oldX = x;
		oldY = y;
		
		this.friction = friction;
	}
	
	
	public void update() {
		
		// Movement of node each frame
		double dx = x - oldX;
		double dy = y - oldY;
		oldX = x;
		oldY = y;
		x += dx;
		y += dy;
		
		y += gravity;
	}
	
	
	/** Call at the end of the frame
	 */
	public void checkCollisions(GameManager gm) {
		
		// move node out of walls in direction of negative velocity
		if (y + RADIUS > gm.floorHeight) {
			double dy = y + RADIUS - gm.floorHeight;
			double a = Math.atan2(y - oldY, x - oldX);
			
			y -= dy;
			x -= (dy / Math.tan(a)) * friction;
			
			oldY = y + (y - oldY) * bounce;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.getHSBColor(0.0f, 1.0f, 1.0f - (float)friction));
		g.fillOval((int)(x - RADIUS), (int)(y - RADIUS), (int)(2 * RADIUS), (int)(2 * RADIUS));
	}
}

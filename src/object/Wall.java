package object;

import java.awt.Color;
import java.awt.Graphics;

import tools.Vector2;

public class Wall {

	public static final double THICKNESS = 3.0;
	public static final Color COLOR = Color.BLACK;
	public static final Color ALT_COLOR = Color.RED;
	
	public Vector2 p1;
	public Vector2 p2;
	
	public boolean collided;
	
	public Wall (Vector2 p1, Vector2 p2) {
		this.p1 = p1;
		this.p2 = p2;
		
		collided = false;
	}
	
	public Wall (double x1, double y1, double x2, double y2) {
		this.p1 = new Vector2(x1, y1);
		this.p2 = new Vector2(x2, y2);
	}
	
	public double length() {
		return p2.sub(p2).magnitude();
	}
	
	public void update() {
		collided = false;
	}
	
	public void render(Graphics g) {
		double x1 = p1.x;
		double y1 = p1.y;
		double x2 = p2.x;
		double y2 = p2.y;
		double a = Math.atan2(y2 - y1, x2 - x1);
		int[] xPoints = new int[4];
		int[] yPoints = new int[4];
		
		xPoints[0] = (int)(x1 + Math.sin(a) * THICKNESS);
		xPoints[1] = (int)(x1 - Math.sin(a) * THICKNESS);
		xPoints[2] = (int)(x2 - Math.sin(a) * THICKNESS);
		xPoints[3] = (int)(x2 + Math.sin(a) * THICKNESS);
		
		yPoints[0] = (int)(y1 - Math.cos(a) * THICKNESS);
		yPoints[1] = (int)(y1 + Math.cos(a) * THICKNESS);
		yPoints[2] = (int)(y2 + Math.cos(a) * THICKNESS);
		yPoints[3] = (int)(y2 - Math.cos(a) * THICKNESS);
		
//		if (collided) {
//			g.setColor(ALT_COLOR);
//		} else {
//			g.setColor(COLOR); 
//		}
		
		g.setColor(COLOR);
		g.fillPolygon(xPoints, yPoints, 4);
	}
}

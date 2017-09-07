package object.physics;

import java.awt.Color;
import java.awt.Graphics;

import tools.Vector2;

public class Muscle {

	public Node n1;
	public Node n2;
	
	public double length;
	
	public double thickness;
	
	private static Color color = new Color(243, 160, 160);
	
	public Muscle (Node n1, Node n2, double thickness) {
		this.n1 = n1;
		this.n2 = n2;
		
		this.thickness = thickness;
		
		this.length = getDistance();
	}
	
	public double getDistance() {
		return n2.pos.sub(n1.pos).magnitude();
	}
	
	public double getDifference() {
		return length - getDistance();
	}
	
	public void update() {		
		Vector2 line = n2.pos.sub(n1.pos);
		double distance = getDistance();

		double difference = length - distance;
		double percent = difference / distance / 2;
		Vector2 offset = line.mult(percent * thickness);
		
		n1.pos = n1.pos.sub(offset);
		n2.pos = n2.pos.add(offset);
	}
	
	public void render(Graphics g) {
		double x1 = n1.pos.x;
		double y1 = n1.pos.y;
		double x2 = n2.pos.x;
		double y2 = n2.pos.y;
		double a = Math.atan2(y2 - y1, x2 - x1);
		int[] xPoints = new int[4];
		int[] yPoints = new int[4];
		
		xPoints[0] = (int)(x1 + Math.sin(a) * Node.RADIUS * thickness);
		xPoints[1] = (int)(x1 - Math.sin(a) * Node.RADIUS * thickness);
		xPoints[2] = (int)(x2 - Math.sin(a) * Node.RADIUS * thickness);
		xPoints[3] = (int)(x2 + Math.sin(a) * Node.RADIUS * thickness);
		
		yPoints[0] = (int)(y1 - Math.cos(a) * Node.RADIUS * thickness);
		yPoints[1] = (int)(y1 + Math.cos(a) * Node.RADIUS * thickness);
		yPoints[2] = (int)(y2 + Math.cos(a) * Node.RADIUS * thickness);
		yPoints[3] = (int)(y2 - Math.cos(a) * Node.RADIUS * thickness);
		
		g.setColor(color);
		g.fillPolygon(xPoints, yPoints, 4);
	}
}

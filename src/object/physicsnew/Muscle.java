package object.physicsnew;

import java.awt.Color;
import java.awt.Graphics;

public class Muscle {

	public Node n1;
	public Node n2;
	
	public double length;
	private double oldLength;
	
	public double scale;
	
	public double thickness;
	
	
	
	private static final double MAX_SCALE = 2.0;
	private static final double MIN_SCALE = 0.5;
	
	private static Color color = new Color(243, 160, 160);
	
	public Muscle (Node n1, Node n2, double thickness) {
		this.n1 = n1;
		this.n2 = n2;
		
		this.thickness = thickness;
		
		this.length = getDistance();
		this.scale = 1.0;
	}
	
	public double getDistance() {
		double dx = n2.x - n1.x;
		double dy = n2.y - n1.y;
		return Math.sqrt(dy * dy + dx * dx);
	}
	
	public double getDifference() {
		return length - getDistance();
	}
	
	public double getEnergyLoss() {
		return Math.abs(oldLength - getDistance()) / 100.0;
	}
	
	public void update() {
		oldLength = getDistance();
		
		double dx = n2.x - n1.x;
		double dy = n2.y - n1.y;
		double distance = getDistance();

		double difference = (length * scale) - distance;
		double percent = difference / distance / 2;
		double offsetX = dx * percent * thickness;
		double offsetY = dy * percent * thickness;
		
		n1.x -= offsetX;
		n1.y -= offsetY;
		n2.x += offsetX;
		n2.y += offsetY;
	}
	
	public void update(double scale) {
		this.scale = scale * (MAX_SCALE - MIN_SCALE) + MIN_SCALE;
		if (scale > MAX_SCALE) {
			this.scale = MAX_SCALE;
		}

		if (scale < MIN_SCALE) {
			this.scale = MIN_SCALE;
		}

		this.update();
	}
	
	public void render(Graphics g) {
		double a = Math.atan2(n2.y - n1.y, n2.x - n1.x);
		int[] xPoints = new int[4];
		int[] yPoints = new int[4];
		
		xPoints[0] = (int)(n1.x + Math.sin(a) * Node.RADIUS * thickness);
		xPoints[1] = (int)(n1.x - Math.sin(a) * Node.RADIUS * thickness);
		xPoints[2] = (int)(n2.x - Math.sin(a) * Node.RADIUS * thickness);
		xPoints[3] = (int)(n2.x + Math.sin(a) * Node.RADIUS * thickness);
		
		yPoints[0] = (int)(n1.y - Math.cos(a) * Node.RADIUS * thickness);
		yPoints[1] = (int)(n1.y + Math.cos(a) * Node.RADIUS * thickness);
		yPoints[2] = (int)(n2.y + Math.cos(a) * Node.RADIUS * thickness);
		yPoints[3] = (int)(n2.y - Math.cos(a) * Node.RADIUS * thickness);
		
		g.setColor(color);
		g.fillPolygon(xPoints, yPoints, 4);
	}
}

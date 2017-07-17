package object.physics;

import java.awt.Color;
import java.awt.Graphics;

public class Muscle {

	Stick s1;
	Stick s2;
	double length;
	double stretch;
	
	double x1;
	double y1;
	double x2;
	double y2;
	
	final double MAX_LENGTH;
	final double MIN_LENGTH;
	
	public Muscle(Stick s1, Stick s2) {
		this.s1 = s1;
		this.s2 = s2;
		length = getDistance();
		
		MAX_LENGTH = (s1.length + s2.length) / 2;
		MIN_LENGTH = length / 2;
		
		stretch = 1;
	}
	
	private double getDistance() {
		x1 = (s1.p1.x + s1.p2.x) * 0.5;
		y1 = (s1.p1.y + s1.p2.y) * 0.5;
		x2 = (s2.p1.x + s2.p2.x) * 0.5;
		y2 = (s2.p1.y + s2.p2.y) * 0.5;
		
		double dx = x2 - x1;
		double dy = y2 - y1;
		
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public void setDistance(double percent) {
		length = percent * (MAX_LENGTH - MIN_LENGTH) + MIN_LENGTH;
		
		if (length < MIN_LENGTH) {
			length = MIN_LENGTH;
		}
		else if (length > MAX_LENGTH) {
			length = MAX_LENGTH;
		}
		
	}
	
	
	public void update() {
		double distance = getDistance();
		double difference = length - distance;
		
		double percent = difference / distance / 2;
		double offsetX = percent * (x2 - x1);
		double offsetY = percent * (y2 - y1);
		
		s1.p1.x -= offsetX * stretch;
		s1.p1.y -= offsetY * stretch;
		s1.p2.x -= offsetX * stretch;
		s1.p2.y -= offsetY * stretch;
		s2.p1.x += offsetX * stretch;
		s2.p1.y += offsetY * stretch;
		s2.p2.x += offsetX * stretch;
		s2.p2.y += offsetY * stretch;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}
}

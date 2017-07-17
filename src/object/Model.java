package object;

import java.awt.Graphics;

import object.physics.Muscle;
import object.physics.Point;
import object.physics.Stick;

public abstract class Model {

	public Point points[];
	public Stick sticks[];
	public Muscle muscles[];
	
	
	public double getX() {
		double sum = 0;
		for (int i = 0; i < points.length; i ++) {
			sum += points[i].x;
		}
		return sum / points.length;
	}
	
	public double getY() {
		double sum = 0;
		for (int i = 0; i < points.length; i ++) {
			sum += points[i].y;
		}
		return sum / points.length;
	}
	
	public void update() {
		for (Point p : points) {
			p.update();
		}
		for (int i = 0; i < 100; i ++) {	
			for (Muscle m : muscles) {
				m.update();
			}	
			for (Stick s : sticks) {
				s.update();
			}
		}
	}
	
	public void render(Graphics g) {
		for (Point p : points) {
			p.render(g);
		}
		for (Stick s : sticks) {
			s.render(g);
		}
		for (Muscle m : muscles) {
			m.render(g);
		}
	}
}

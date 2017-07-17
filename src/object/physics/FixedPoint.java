package object.physics;

import java.awt.Color;

public class FixedPoint extends Point {
	
	public double fixedX;
	public double fixedY;
	
	public FixedPoint(double x, double y, double radius, Color color) {
		super(x, y, radius, color);
		fixedX = x;
		fixedY = y;
	}
	
	public FixedPoint(double x, double y) {
		super(x, y);
		fixedX = x;
		fixedY = y;
	}
	
	@Override
	public void update() {
		x = fixedX;
		y = fixedY;
	}
	
	@Override
	public void move(double dx, double dy) {
		// do nothing
	}


}

package object;

import object.physics.FixedPoint;
import object.physics.Point;
import object.physics.Stick;

public class Chain extends Model {

	public Chain (double x, double y, int numPoints, double stickLength, double angle) {
		points = new Point[numPoints];
		sticks = new Stick[numPoints - 1];
		
		points[0] = new FixedPoint(x, y);
		
		double dx = Math.cos(angle) * stickLength;
		double dy = Math.sin(angle) * stickLength;
		for (int i = 1; i < numPoints; i ++) {
			points[i] = new Point(x + dx * i, y + dy * i);
			sticks[i - 1] = new Stick(points[i], points[i-1]);
		}
	}
	
	
	
	
	
}

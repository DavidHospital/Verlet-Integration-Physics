package object.physics;

public class Body extends Model {
	
	public Body(double x, double y, double size) {
		points = new Point[8];
		sticks = new Stick[10];
		muscles = new Muscle[4];
		
		// body
		points[0] = new Point(x - size, y - size);
		points[1] = new Point(x + size, y - size);
		points[2] = new Point(x + size, y + size);
		points[3] = new Point(x - size, y + size);
		
		sticks[0] = new Stick(points[0], points[1]);
		sticks[1] = new Stick(points[1], points[2]);
		sticks[2] = new Stick(points[2], points[3]);
		sticks[3] = new Stick(points[3], points[0]);
		sticks[4] = new Stick(points[0], points[2]);
		sticks[5] = new Stick(points[1], points[3]);
		
		// Legs
		points[4] = new Point(x - size * 1.2, y + size * 1.5);
		points[5] = new Point(x - size * 1.2, y + size * 2);
		
		sticks[6] = new Stick(points[3], points[4]);
		sticks[7] = new Stick(points[4], points[5]);
		
		muscles[0] = new Muscle(sticks[2], sticks[6]);
		muscles[1] = new Muscle(sticks[6], sticks[7]);
		
		points[6] = new Point(x + size * 1.2, y + size * 1.5);
		points[7] = new Point(x + size * 1.2, y + size * 2);
		
		sticks[8] = new Stick(points[2], points[6]);
		sticks[9] = new Stick(points[6], points[7]);
		
		muscles[2] = new Muscle(sticks[2], sticks[8]);
		muscles[3] = new Muscle(sticks[8], sticks[9]);
		
	}
	
}

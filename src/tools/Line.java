package tools;

public class Line {

	Vector2 p1;
	Vector2 p2;
	
	public Line (Vector2 p1, Vector2 p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Vector2 intersect(Line other) {
		Vector2 p = this.p1;
		Vector2 r = this.p2.sub(this.p1);
		Vector2 q = other.p1;
		Vector2 s = other.p2.sub(other.p1);
		
		double denom = r.cross(s);
		if (denom != 0) {
			double t = q.sub(p).cross(s) / denom;
			double u = q.sub(p).cross(r) / denom;
			
			// One intersecting point
			if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
				Vector2 intersection = p.add(r.mult(t));
				return intersection;
			} 
		} else {
			
			// Colinear
			if (q.sub(p).cross(r) == 0) {
				Line intersection;
			}
		}
		
		return null;
	}
}

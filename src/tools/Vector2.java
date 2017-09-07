package tools;

public class Vector2 {

	public double x;
	public double y;
	
	public Vector2 (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2 copy () {
		return new Vector2(x, y);
	}
	
	public Vector2 normalize () {
		if (magnitude() == 0) {
			return this;
		} else {
			return new Vector2(x / magnitude(), y / magnitude());
		}
	}
	
	public double magnitude () {
		return Math.sqrt(x * x + y * y);
	}
	
	public Vector2 add (Vector2 other) {
		return new Vector2(this.x + other.x, this.y + other.y);
	}
	
	public Vector2 sub (Vector2 other) {
		return new Vector2(this.x - other.x, this.y - other.y);
	}
	
	public double dot (Vector2 other) {
		return this.x * other.x + this.y * other.y;
	}
	
	public Vector2 mult (double k) {
		return new Vector2 (x * k, y * k);
	}
	
	public Vector2 proj (Vector2 other) {
		return this.mult( this.dot(other) / this.dot(this) ); 
	}
}

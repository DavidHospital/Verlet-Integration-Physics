package object.scene;

import tools.Vector2;

public class Camera {

	Vector2 position;
	Vector2 size;
	
	Vector2 focus;
	
	public Camera (Vector2 position, Vector2 size, Vector2 focus) {
		this.position = position;
		this.size = size;
	}
	
	public void update() {
		position = focus.sub(size.mult(0.5));
		System.out.println(this.position);
	}
	
	public void setFocus(Vector2 focus) {
		this.focus = focus;
		update();
	}
}

package object.scene;

import tools.Vector2;

public class Camera {

	Vector2 position;
	Vector2 size;
	
	public Camera (Vector2 position, Vector2 size) {
		this.position = position;
		this.size = size;
	}
}

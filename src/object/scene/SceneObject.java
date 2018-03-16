package object.scene;

import java.awt.Graphics;

import object.GameObject;

public abstract class SceneObject extends GameObject {
	
	Scene scene;
	Camera camera;
	
	protected SceneObject (Scene scene) {
		this.scene = scene;
		this.camera = scene.getCamera();
	}
	
	public abstract void renderC(Graphics g);
	
	public void render(Graphics g) {
		g.translate((int)-camera.position.x, (int)-camera.position.y);
		renderC(g);
		g.translate((int)camera.position.x, (int)camera.position.y);
	}
}

package object.scene;

import java.awt.Graphics;

import object.GameObject;

public abstract class SceneObject extends GameObject {
	
	Camera c;
	
	protected SceneObject (Camera c) {
		this.c = c;
	}
	
	public abstract void renderC(Graphics g);
	
	public void render(Graphics g) {
		g.translate((int)c.position.x, (int)c.position.y);
		renderC(g);
		g.translate((int)-c.position.x, (int)-c.position.y);
	}
}

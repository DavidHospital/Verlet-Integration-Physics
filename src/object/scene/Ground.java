package object.scene;

import java.awt.Color;
import java.awt.Graphics;

public class Ground extends SceneObject {

	public int height;
	
	public Ground(Scene scene, Camera camera, int height) {
		super(scene, camera);
		this.height = height;
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void renderC(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, (int)(camera.size.y - height), (int)camera.size.x, (int)camera.size.y);
	}
	
}

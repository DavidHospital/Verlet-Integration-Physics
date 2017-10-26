package object.scene;

import java.awt.Color;
import java.awt.Graphics;

public class Background extends SceneObject {

	public Background(Scene scene, Camera camera) {
		super(scene, camera);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {	
		renderC(g);
	}
	
	@Override
	public void renderC(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, (int)camera.size.x, (int)camera.size.y);
	}

	

}

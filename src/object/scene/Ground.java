package object.scene;

import java.awt.Color;
import java.awt.Graphics;

public class Ground extends SceneObject {

	public int height;
	
	public Ground(Camera camera, int height) {
		super(camera);
		this.height = height;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderC(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, (int)(c.size.y - height), (int)c.size.x, (int)c.size.y);
	}
	
}

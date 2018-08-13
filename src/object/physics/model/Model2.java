package object.physics.model;

import java.awt.Graphics;

import object.scene.Scene;
import object.scene.SceneObject;

public class Model2 extends SceneObject {

	private ModelGraph graph;
	
	public Model2(Scene scene, ModelGraph graph) {
		super(scene);
		this.graph = graph;
	}
	
	@Override
	public void update() {
		graph.update(scene);
	}

	@Override
	public void renderC(Graphics g) {
		// TODO Auto-generated method stub
		
	}	
	
}

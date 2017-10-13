package object.scene;

import java.awt.Graphics;
import java.util.ArrayList;

import tools.Vector2;

public class Scene {
	
	public Camera camera;
	
	public ArrayList<SceneObject> sceneObjects;
	public ArrayList<SceneObject> addQueue;
	public ArrayList<SceneObject> removeQueue;
	
	public Scene(int windowWidth, int windowHeight) {
		camera = new Camera(new Vector2(0, 0), 			// Position
				new Vector2(windowWidth, windowHeight), // Size
				Vector2.Zero);							// Focus (Later change to model position)
	
		sceneObjects = new ArrayList<>();
		addQueue = new ArrayList<>();
		
		initiate();
	}
	
	private void initiate() {
		addSceneObject(new Sky(camera));
		addSceneObject(new Ground(camera, 100));
	}
	
	public void addSceneObject(SceneObject o) {
		addQueue.add(o);
	}
	
	public void removeSceneObject(SceneObject o) {
		removeQueue.add(o);
	}
	
	public void update() {
		sceneObjects.addAll(addQueue);
		addQueue.clear();
		
		sceneObjects.removeAll(removeQueue);
		removeQueue.clear();
		
		for (SceneObject o : sceneObjects) {
			o.update();
		}
	}
	
	public void render(Graphics g) {
		for (SceneObject o : sceneObjects) {
			o.render(g);
		}
	}
}

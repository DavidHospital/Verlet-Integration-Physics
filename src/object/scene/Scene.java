package object.scene;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.GameManager;
import tools.Vector2;

public class Scene {

	GameManager gm;
	Camera camera;
	
	public ArrayList<SceneObject> sceneObjects;
	public ArrayList<SceneObject> addQueue;
	public ArrayList<SceneObject> removeQueue;
	
	public Scene(GameManager gm) {
		this.gm = gm;
		this.camera = new Camera(new Vector2(0, 0), 			// Position
				new Vector2(gm.windowWidth, gm.windowHeight), // Size
				Vector2.Zero);							// Focus (Later change to model position)
	
		this.sceneObjects = new ArrayList<>();
		this.addQueue = new ArrayList<>();
		this.removeQueue = new ArrayList<>();
		
		initiate();
	}
	
	private void initiate() {
		addSceneObject(new Background(this, camera));
		addSceneObject(new Ground(this, camera, 100));
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

	public void keyPressedEvent(KeyEvent e) {	
	}

	public void keyReleasedEvent(KeyEvent e) {
	}
}

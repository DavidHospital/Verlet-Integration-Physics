package object.scene;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import object.Wall;
import object.physics.model.Model;
import main.GameManager;
import tools.Vector2;

public class Scene {

	GameManager gm;
	Camera camera;
	
	public int MIN_X;
	public int MIN_Y;
	public int MAX_X;
	public int MAX_Y;
	
	public ArrayList<SceneObject> sceneObjects;
	public ArrayList<SceneObject> addQueue;
	public ArrayList<SceneObject> removeQueue;
	
	private Model model;
	
	public Scene(GameManager gm) {
		this.gm = gm;
		this.camera = new Camera(new Vector2(-400, -300), 			// Position
				new Vector2(gm.windowWidth, gm.windowHeight)); 	// Size
	
		this.sceneObjects = new ArrayList<>();
		this.addQueue = new ArrayList<>();
		this.removeQueue = new ArrayList<>();
		
		MIN_X = Integer.MIN_VALUE;
		MIN_Y = Integer.MIN_VALUE;
		MAX_X = Integer.MAX_VALUE;
		MAX_Y = Integer.MAX_VALUE;
		
		initiate();
	}
	
	private void initiate() {
		addSceneObject(new Wall(this, -350, -250, 350, -250));
		addSceneObject(new Wall(this, -350,  250, 350,  250));
		addSceneObject(new Wall(this, -350,  250, -350, -250));
		addSceneObject(new Wall(this,  350,  250,  350, -250));
		
		newModel();
	}
	
	public void newModel() {
		removeSceneObject(model);
		model = Model.RandomModel(0, 0, 200, 200, 5, this);
		addSceneObject(model);
	}
	
	public void newModel(Model m) {
		removeSceneObject(model);
		model = m;
		addSceneObject(model);
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
	
	public Camera getCamera() {
		return camera;
	}
}

package object.physics;

import java.awt.Color;
import java.awt.Graphics;

import object.Wall;
import object.scene.Scene;
import object.scene.SceneObject;
import tools.Vector2;

public class Node {

	public static final double RADIUS = 20;
	public static final double bounce = 0.2f;
	public static final Vector2 gravity = new Vector2(0, 0.2f);
	
	public double friction;
	
	private Scene scene;
	
	// Position	
	public Vector2 pos;
	public Vector2 oldPos;
	
	
	public Node(Scene scene, double x, double y, double friction) {
		this.pos = new Vector2(x, y);
		this.oldPos = pos.copy();
		
		this.friction = friction;
		this.scene = scene;
	}
	
	
	public void update(Scene scene) {
		// Movement of node each frame
		frameMove();
	}
	
	public void move(Vector2 vel) {
		double length = vel.magnitude();
		Vector2 nVel = vel.normalize().mult(RADIUS/4);
		if (length > RADIUS/4) {
			while (length >= 0) {
				pos = pos.add(nVel);
				length -= RADIUS/4;
				if (checkCollisions()) {
					break;
				}
			}
			if (length < 0) {
				Vector2 back = vel.normalize().mult(length);
				pos = pos.add(back);
			}
		} else {
			pos = pos.add(vel);
		}
	}
	
	public void frameMove() {
		Vector2 vel = pos.sub(oldPos);
		oldPos = pos.copy();
		move(vel);

		move(gravity);
		checkCollisions();
	}
	
	public boolean checkCollisions() {
		boolean ret = false;
		for (SceneObject o : scene.sceneObjects) {
			if (o instanceof Wall) {
				Wall w = (Wall) o;
				
				Vector2 line = w.p2.sub(w.p1);
				Vector2 p1Node = pos.sub(w.p1);
				Vector2 proj = line.proj(p1Node);
				Vector2 distance = proj.add(w.p1).sub(pos);
				
				if (distance.magnitude() < RADIUS 
						&& proj.dot(line) >= 0 
						&& proj.dot(line) <= line.dot(line)) {
					
					// Have collided
					ret = true;
					
					Vector2 velocity = pos.sub(oldPos);
					Vector2 translate = distance.normalize().mult(RADIUS).sub(distance);
					
					Vector2 moveAmount = translate.add(line.proj(velocity).mult(friction)).mult(-1);
					
					move(moveAmount);
					
					Vector2 newVelocity = velocity.proj(pos.sub(oldPos));
					oldPos = oldPos.add(translate.proj(newVelocity).mult(1 + bounce));
				}
			}
		}
		return ret;
	}
	
	public boolean isColliding(Wall w) {
		Vector2 line = w.p2.sub(w.p1);
		Vector2 p1Node = pos.sub(w.p1);
		Vector2 proj = line.proj(p1Node);
		Vector2 distance = proj.add(w.p1).sub(pos);
		
		if (distance.magnitude() <= RADIUS 
				&& proj.dot(line) >= 0 
				&& proj.dot(line) <= line.dot(line)) {
			return true;
		}		
		return false;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.getHSBColor(0.0f, 1.0f, 1.0f - (float)friction));
		g.fillOval((int)(pos.x - RADIUS), (int)(pos.y - RADIUS), (int)(2 * RADIUS), (int)(2 * RADIUS));
	}
}

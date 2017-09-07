package object.physics;

import java.awt.Color;
import java.awt.Graphics;

import object.Wall;
import tools.Vector2;
import world.World;

public class Node {

	public static final double RADIUS = 10;
	public static final double bounce = 0.9f;
	public static final Vector2 gravity = new Vector2(0, 0.2f);
	
	public double friction;
	
	// Position	
	public Vector2 pos;
	public Vector2 oldPos;
	
	
	public Node(double x, double y, double friction) {
		this.pos = new Vector2(x, y);
		this.oldPos = pos.copy();
		
		this.friction = friction;
	}
	
	
	public void update() {
		
		// Movement of node each frame
		Vector2 vel = pos.sub(oldPos);
		oldPos = pos.copy();
		pos = pos.add(vel);
		
		pos = pos.add(gravity);
		
	}
	
	
	/** Call at the end of the frame
	 */
	public void checkCollisions(World world) {
		for (Wall w : world.walls) {
			
			Vector2 line = w.p2.sub(w.p1);
			Vector2 p1Node = pos.sub(w.p1);
			Vector2 proj = line.proj(p1Node);
			Vector2 distance = proj.add(w.p1).sub(pos);
			
			if (distance.magnitude() <= RADIUS 
					&& proj.dot(line) >= 0 
					&& proj.dot(line) <= line.dot(line)) {
				w.collided = true;
				
				Vector2 velocity = pos.sub(oldPos);
				Vector2 translate = distance.normalize().mult(RADIUS).sub(distance);
				
				pos = pos.sub(translate.add(line.proj(velocity).mult(friction)));
				Vector2 newVelocity = velocity.proj(pos.sub(oldPos));
				oldPos = oldPos.add(translate.proj(newVelocity).mult(1 + bounce));
				
			}			
		}		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.getHSBColor(0.0f, 1.0f, 1.0f - (float)friction));
		g.fillOval((int)(pos.x - RADIUS), (int)(pos.y - RADIUS), (int)(2 * RADIUS), (int)(2 * RADIUS));
	}
}

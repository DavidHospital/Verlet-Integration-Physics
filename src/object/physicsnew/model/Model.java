package object.physicsnew.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import main.GameManager;
import object.physicsnew.Node;
import object.physicsnew.Stick;

public class Model {
	
	public Node[] nodes;
	public Stick[] sticks;
	
	private GameManager gm;
	
	public Model(Node[] nodes, Stick[] sticks, GameManager gm) {
		this.nodes = nodes;
		this.sticks = sticks;
		
		this.gm = gm;
	}
	
	public static Model RandomModel(int x, int y, int width, int height, long seed, GameManager gm) {
		Random r = new Random(seed);
		
		// Nodes
		ArrayList<Node> nodesList = new ArrayList<Node>();
		for (int i = 0; i < 3 + r.nextInt(3); i ++) {
			nodesList.add(new Node(r.nextInt(width) + x - width / 2, r.nextInt(height) + y - height / 2, r.nextDouble()));
		}
		
		// Sticks
		ArrayList<Stick> sticksList = new ArrayList<>();
		for (int i = 0; i < nodesList.size(); i ++) {
			double stickChance = 1.0;
			for (int k = i + 1; k < nodesList.size(); k ++) {
				if (i != k) {
					if (r.nextDouble() < stickChance) {
						sticksList.add(new Stick(nodesList.get(i), nodesList.get(k), r.nextDouble()));
						stickChance *= 0.75;
					}
				}
			}
		}
		
		Stick[] sticks = sticksList.toArray(new Stick[sticksList.size()]);
		Node[] nodes = nodesList.toArray(new Node[nodesList.size()]);
		return new Model(nodes, sticks, gm);	
	}
	
	public void update() {
		for (Node n : nodes) {
			n.update();
		}
		for (Stick s : sticks) {
			s.update();
		}
		for (Node n : nodes) {
			n.checkCollisions(gm);
		}
	}
	
	public void render(Graphics g) {
		for (Stick s : sticks) {
			s.render(g);
		}
		for (Node n : nodes) {
			n.render(g);
		}
		
	}
}

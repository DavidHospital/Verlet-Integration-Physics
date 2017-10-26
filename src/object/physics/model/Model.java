package object.physics.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import object.GameObject;
import object.physics.Muscle;
import object.physics.Node;
import object.scene.Scene;

public class Model extends GameObject {
	
	public Node[] nodes;
	public Muscle[] muscles;
	
	private Scene world;
	
	private boolean contract;
	
	public Model(Node[] nodes, Muscle[] muscles, Scene world) {
		this.nodes = nodes;
		this.muscles = muscles;
		
		this.world = world;
		
		contract = false;
	}
	
	public static Model RandomModel(int x, int y, int width, int height, int numNodes, Scene world) {
		Random r = new Random();
		
		// Nodes
		ArrayList<Node> nodesList = new ArrayList<Node>();
		for (int i = 0; i < numNodes; i ++) {
			nodesList.add(new Node(world, r.nextInt(width) + x - width / 2, r.nextInt(height) + y - height / 2, r.nextDouble()));
		}
		
		// Sticks
		ArrayList<Muscle> sticksList = new ArrayList<>();
		for (int i = 0; i < nodesList.size(); i ++) {
			double stickChance = 1.0;
			for (int k = i + 1; k < nodesList.size(); k ++) {
				if (i != k) {
					if (r.nextDouble() < stickChance) {
						sticksList.add(new Muscle(nodesList.get(i), nodesList.get(k), r.nextDouble(), r.nextDouble()));
						stickChance *= 0.75;
					}
				}
			}
		}
		
		Muscle[] sticks = sticksList.toArray(new Muscle[sticksList.size()]);
		Node[] nodes = nodesList.toArray(new Node[nodesList.size()]);
		return new Model(nodes, sticks, world);	
	}
	
	public double getX() {
		double sum = 0.0;
		for (int i = 0; i < nodes.length; i ++) {
			sum += nodes[i].pos.x;
		}
		return sum / nodes.length;
	}
	
	public double getY() {
		double sum = 0.0;
		for (int i = 0; i < nodes.length; i ++) {
			sum += nodes[i].pos.y;
		}
		return sum / nodes.length;
	}
	
	@Override
	public void update() {
		if (contract) {
			for (Muscle m : muscles) {
				m.contract();
			}
		} else {
			for (Muscle m : muscles) {
				m.relax();
			}
		}
		
		for (Node n : nodes) {
			n.update(world);
		}

		for (Muscle m : muscles) {
			m.update(world);
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		for (Muscle s : muscles) {
			s.render(g);
		}
		for (Node n : nodes) {
			n.render(g);
		}
		
	}

	public void contractMuscles() {
		contract = true;
	}
	
	public void relaxMuscles() {
		contract = false;
	}
}

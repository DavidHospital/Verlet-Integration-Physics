package object.physics.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import object.physics.Muscle;
import object.physics.Node;
import world.World;

public class Model {
	
	public Node[] nodes;
	public Muscle[] muscles;
	
	private World world;
	
	private boolean contract;
	
	public Model(Node[] nodes, Muscle[] muscles, World world) {
		this.nodes = nodes;
		this.muscles = muscles;
		
		this.world = world;
		
		contract = false;
	}
	
	public static Model RandomModel(int x, int y, int width, int height, int numNodes, World world) {
		Random r = new Random();
		
		// Nodes
		ArrayList<Node> nodesList = new ArrayList<Node>();
		for (int i = 0; i < numNodes; i ++) {
			nodesList.add(new Node(r.nextInt(width) + x - width / 2, r.nextInt(height) + y - height / 2, r.nextDouble()));
		}
		
		// Sticks
		ArrayList<Muscle> sticksList = new ArrayList<>();
		for (int i = 0; i < nodesList.size(); i ++) {
			double stickChance = 1.0;
			for (int k = i + 1; k < nodesList.size(); k ++) {
				if (i != k) {
					if (r.nextDouble() < stickChance) {
						sticksList.add(new Muscle(nodesList.get(i), nodesList.get(k), r.nextDouble(), 0.5));
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
		
		for (Node n : nodes) {
			n.checkCollisions(world);
		}
	}
	
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

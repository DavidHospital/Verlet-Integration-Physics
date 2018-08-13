package object.physics.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import genetic.DNA;
import object.physics.Muscle;
import object.physics.Node;
import object.scene.Scene;
import object.scene.SceneObject;
import tools.Vector2;

public class Model extends SceneObject implements DNA {
	
	public Node[] nodes;
	public Muscle[] muscles;
	
	private Vector2 startPosition;
	
	public Model(Node[] nodes, Muscle[] muscles, Scene scene) {
		super(scene);
		this.nodes = nodes;
		this.muscles = muscles;
	
		startPosition = getPos();
	}
	
	public static Model RandomModel(int x, int y, int width, int height, int numNodes, Scene scene) {
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
						sticksList.add(new Muscle(nodesList.get(i), nodesList.get(k), r.nextDouble(), r.nextDouble()));
						stickChance *= 0.75;
					}
				}
			}
		}
		
		Muscle[] sticks = sticksList.toArray(new Muscle[sticksList.size()]);
		Node[] nodes = nodesList.toArray(new Node[nodesList.size()]);
		return new Model(nodes, sticks, scene);	
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
	
	public Vector2 getPos() {
		return new Vector2(getX(), getY());
	}
	
	@Override
	public void update() {		
		for (Node n : nodes) {
			n.update(scene);
		}

		for (Muscle m : muscles) {
			m.update(scene);
		}
		
	}

	@Override
	public void renderC(Graphics g) {
		for (Muscle s : muscles) {
			s.render(g);
		}
		for (Node n : nodes) {
			n.render(g);
		}
		
	}

	@Override
	public double fitness() {
		return - getY() - startPosition.y;
	}

	@Override
	public DNA mutate(double mutationRate) {
		Node[] _nodes = new Node[this.nodes.length];
		Muscle[] _muscles = new Muscle[this.muscles.length];
			
		// Copy Nodes
		for (int i = 0; i < _nodes.length; i ++) {
			_nodes[i] = new Node(nodes[i].pos.x, nodes[i].pos.y, nodes[i].friction);
			if (Math.random() < mutationRate) {
				_nodes[i].setPos(_nodes[i].pos.add(new Vector2(Math.random() * 2 - 1, Math.random() * 2 - 1)));
				_nodes[i].friction += (Math.random() * 2 - 1) * 0.1;
				if (_nodes[i].friction < 0) {
					_nodes[i].friction = 0;
				} else if (_nodes[i].friction > 1) {
					_nodes[i].friction = 1;
				}
			}
		}
		
		// Copy Muscles
		for (int i = 0; i < _muscles.length; i ++) {
			Node n1 = null;
			Node n2 = null;
			
			for (int j = 0; j < nodes.length; j ++) {
				if (nodes[j] == muscles[i].n1) {
					n1 = _nodes[j];
				} else if (nodes[j] == muscles[i].n2) {
					n2 = _nodes[j];
				}
			}
			
			_muscles[i] = new Muscle(n1, n2, muscles[i].thickness, muscles[i].contractScale);
			if (Math.random() < mutationRate) {
				_muscles[i].thickness += (Math.random() * 2 - 1) * 0.1;
				if (_muscles[i].thickness < 0) {
					_muscles[i].thickness = 0;
				} else if (_muscles[i].thickness > 1) {
					_muscles[i].thickness = 1;
				}
				
				_muscles[i].contractScale += (Math.random() * 2 - 1) * 0.1;
				if (_muscles[i].contractScale < 0) {
					_muscles[i].contractScale = 0;
				} else if (_muscles[i].contractScale > 1) {
					_muscles[i].contractScale = 1;
				}
			}
		}
		
		return new Model(_nodes, _muscles, scene);
	}

	@Override
	public DNA child(DNA other) {
		// TODO Auto-generated method stub
		return null;
	}
}

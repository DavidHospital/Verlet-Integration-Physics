package object.physicsnew.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import main.GameManager;
import network.dynamic.Network;
import object.physicsnew.Node;
import object.physicsnew.Muscle;

public class Model {
	
	public Node[] nodes;
	public Muscle[] muscles;
	
	private GameManager gm;
	
	private Network network;
	
	public Model(Node[] nodes, Muscle[] sticks, GameManager gm) {
		this.nodes = nodes;
		this.muscles = sticks;
		
		this.gm = gm;
		
		int[] layerSizes = {nodes.length * 2, sticks.length, sticks.length};
		network = new Network(layerSizes, 30, 30, 200, 300);
		
		double[][][] stickValues = {
				new double[nodes.length * 2][sticks.length],
				new double[sticks.length][sticks.length]
		};
		for (int i = 0; i < stickValues.length; i ++) {
			for (int k = 0; k < stickValues[i].length; k ++) {
				for (int m = 0; m < stickValues[i][k].length; m ++) {
					stickValues[i][k][m] = new Random().nextGaussian();
				}
			}
		}
		
		network.updateSticks(stickValues);
	}
	
	public static Model RandomModel(int x, int y, int width, int height, GameManager gm) {
		Random r = new Random();
		
		// Nodes
		ArrayList<Node> nodesList = new ArrayList<Node>();
		for (int i = 0; i < 3 + r.nextInt(3); i ++) {
			nodesList.add(new Node(r.nextInt(width) + x - width / 2, r.nextInt(height) + y - height / 2, r.nextDouble()));
		}
		
		// Sticks
		ArrayList<Muscle> sticksList = new ArrayList<>();
		for (int i = 0; i < nodesList.size(); i ++) {
			double stickChance = 1.0;
			for (int k = i + 1; k < nodesList.size(); k ++) {
				if (i != k) {
					if (r.nextDouble() < stickChance) {
						sticksList.add(new Muscle(nodesList.get(i), nodesList.get(k), r.nextDouble()));
						stickChance *= 0.75;
					}
				}
			}
		}
		
		Muscle[] sticks = sticksList.toArray(new Muscle[sticksList.size()]);
		Node[] nodes = nodesList.toArray(new Node[nodesList.size()]);
		return new Model(nodes, sticks, gm);	
	}
	
	public double getX() {
		double sum = 0.0;
		for (int i = 0; i < nodes.length; i ++) {
			sum += nodes[i].x;
		}
		return sum / nodes.length;
	}
	
	public double getY() {
		double sum = 0.0;
		for (int i = 0; i < nodes.length; i ++) {
			sum += nodes[i].y;
		}
		return sum / nodes.length;
	}
	
	public double getEnergyLoss() {
		double sum = 0;
		for (Muscle m : muscles) {
			sum += m.getEnergyLoss();
		}
		return sum;
	}
	
	public void update() {
		for (Node n : nodes) {
			n.update();
		}
		
		network.update();
		double[] values = new double[nodes.length * 2];
		for (int i = 0; i < nodes.length; i ++) {
			values[2 * i] = nodes[i].x - this.getX();
			values[2 * i + 1] = nodes[i].y - this.getY();
		}
		double[] newValues = network.forward(values);
		for (int i = 0; i < muscles.length; i ++) {
			muscles[i].update(newValues[i]);
		}
		
		for (Node n : nodes) {
			n.checkCollisions(gm);
		}
	}
	
	public void render(Graphics g) {
		for (Muscle s : muscles) {
			s.render(g);
		}
		for (Node n : nodes) {
			n.render(g);
		}
		network.render(g);
		
	}
}

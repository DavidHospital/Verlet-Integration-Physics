package network.genetics;

import java.util.Random;

public class DNA {

	public double[] nodes;
	
	public DNA (double[] nodes) {
		this.nodes = nodes;
	}
	
	public static DNA Random(int size) {
		double[] nodes = new double[size];
		for (int i = 0; i < size; i ++) {
			nodes[i] = randomNode();
		}
		return new DNA(nodes);
	}
	
	private static double randomNode() {
		return new Random().nextGaussian() * 10;
	}
	
	public DNA splice (DNA other, double mutationRate) {
		Random r = new Random();
		double[] newNodes = new double[nodes.length];
		for (int i = 0; i < nodes.length; i ++) {
			if (r.nextBoolean()) {
				newNodes[i] = this.nodes[i];
			} else {
				newNodes[i] = other.nodes[i];
			}
			
			// Mutate current node
			if (r.nextDouble() < mutationRate) {
				newNodes[i] += r.nextGaussian() * 10;
			}
		}
		return new DNA(newNodes);
	}
}

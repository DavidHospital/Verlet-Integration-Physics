package object.physics;

import network.NeuralNetwork;
import network.genetics.DNA;
import tools.Matrix;

public class NetworkBody extends Body {

	public NeuralNetwork network;
	
	public NetworkBody(double x, double y, double size) {
		super(x, y, size);
		
		int inputSize = points.length * 2;
		int outputSize = muscles.length;
		int hiddenSize = (inputSize + outputSize) / 2;
		
		network = new NeuralNetwork(inputSize, hiddenSize, outputSize);
	}
	
	public NetworkBody(double x, double y, double size, DNA dna) {
		this(x, y, size);
		network.setWFromNodes(dna.nodes);
	}
	
	@Override
	public void update() {
		Matrix X = getPointsMatrix();
		Matrix yHat = network.forward(X);
		for (int i = 0; i < muscles.length; i ++) {
			muscles[i].setDistance(yHat.getValue(0, i));
		}
		super.update();
	}
	
	private Matrix getPointsMatrix() {
		double[] values = new double[points.length * 2];
		for (int i = 0; i < points.length; i ++) {
			values[2 * i] = points[i].x;
			values[2 * i + 1] = points[i].y;
		}
		return new Matrix(1, values.length, values);
	}

}

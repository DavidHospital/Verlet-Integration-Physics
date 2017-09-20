package object.physics.model.network;

import tools.Matrix;

public class Network {
	
	private Matrix w1;
	private Matrix w2;
	
	public Network(int inputSize, int hiddenSize, int outputSize) {
		w1 = Matrix.RandomMatrix(inputSize, hiddenSize);
		w2 = Matrix.RandomMatrix(hiddenSize, outputSize);
	}
	
	public Matrix forward(Matrix X) {
		Matrix a2 = sigmoid(X.dot(w1));
		Matrix a3 = sigmoid(a2.dot(w2));
		
		return a3;
	}
	
	private Matrix sigmoid(Matrix m) {
		Matrix ret = Matrix.Zero(m.rows, m.columns);
		for (int r = 0; r < m.rows; r ++) {
			for (int c = 0; c < m.columns; c ++) {
				ret.setValue(r, c, sigmoid(m.getValue(r, c)));
			}
		}
		return ret;
	}
	
	private double sigmoid(double k) {
		return 1.0 / (1.0 + Math.exp(- k));
	}
}
